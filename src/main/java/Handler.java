import java.util.ArrayList;
import java.util.List;
import java.util.Set;

enum Answer { NEXT_QUESTION, NO_SUCH_ANSWER}

public class Handler {
    public static Game getGame(String playerName, String playerId) {
        Game game = findGame(playerId);
        if(game.getPlayer() == null) {
            Player player = new Player(playerName, playerId);
            game.setPlayer(player);
            Main.gamesList.add(game);
            return game;
        }
        return game;
    }

    public static Game findGame(String playerId) {
        return Main.gamesList.stream()
                .filter(game -> game.getPlayer().getId().equals(playerId))
                .findFirst().orElse(new Game());
    }

    private static List<String> getHelp() {
        List<String> text = new ArrayList<>();
        text.add("Введи /start, чтобы начать игру");
        text.add("Введи /help, чтобы получить справку об игре");
        text.add("Введи /exit, чтобы закончить игру");
        text.add("Введи /heroes, чтобы вывести список своих полученных персонажей");
        text.add("Это тест бот, в котором нужно выбирать тест и отвечать на вопросы, а в конце ты узнаешь, кто ты из персонажей");
        return text;
    }

    public static TelegramAnswer getInput(String userInput, Game game) {
        List<String> text = new ArrayList<>();
        List<String> buttonText = new ArrayList<>();
        TelegramAnswer answer = new TelegramAnswer();
        if(game.getCurrentTest() != null) {
            if(game.getCurrentTest().getCurrentQuestion() != null) {
                Answer checkAnswerResult = checkAnswer(userInput, game);
                if(checkAnswerResult != null) {
                    if (checkAnswerResult.equals(Answer.NEXT_QUESTION)) {
                        TelegramAnswer returnedAnswer = sendQuestion(game);
                        text.addAll(returnedAnswer.text);
                        buttonText.addAll(returnedAnswer.buttonText);
                        game.changeIsNextQ();
                    }
                }
                else return endTest(game);
            }
        }
        else {
            if (userInput.equals("/help")) {
                answer.hasKeyBoard = false;
                text.addAll(getHelp());
            } else if (userInput.equals("/start")) {
                text.add("Какой тест хочешь пройти? Вот список тестов:");
                buttonText.addAll(game.getTestsNames());
            } else if (userInput.equals("/exit")) {
                answer.hasKeyBoard = false;
                text.add("Пока, до скорой встречи!");
            } else if (userInput.equals("/heroes")) {
                Set<Hero> heroes = game.getPlayer().getHeroes();
                answer.hasKeyBoard = false;
                if (heroes.isEmpty()) {
                    text.add("Ты ещё не прошёл ни одного теста");
                } else {
                    for (Hero hero : heroes) {
                        text.add(hero.getName());
                    }
                }
            } else if (game.getTestsNames().contains(userInput)) {
                game.setCurrentTest(game.findTest(userInput));
                Test currentTest = game.getCurrentTest();
                currentTest.setCurrentQuestion(currentTest.getQuestion());
                TelegramAnswer returnedAnswer = sendQuestion(game);
                text.addAll(returnedAnswer.text);
                buttonText.addAll(returnedAnswer.buttonText);
            } else {
                answer.hasKeyBoard = false;
                text.add("Некорректная строка");
            }
        }
        answer.text = text;
        answer.buttonText = buttonText;
        return answer;
    }

    private static TelegramAnswer sendQuestion(Game game) {
        List<String> text = new ArrayList<>();
        TelegramAnswer answer = new TelegramAnswer();
        if(!game.checkEndTest() || game.getCurrentTest().getCurrentQuestion() != null) {
            Question quest = game.getCurrentTest().getCurrentQuestion();
            text.add(quest.getQuestion());
            List<String> buttonText = new ArrayList<>(quest.getAnswers());
            answer.text = text;
            answer.buttonText = buttonText;
            return answer;
        }
        return endTest(game);
    }

    private static Answer checkAnswer(String userInput, Game game) {
        if(!game.checkEndTest()) {
            Question quest = game.getCurrentTest().getCurrentQuestion();
            if (quest.checkValidAnswer(userInput)) {
                quest.getHeroFromAnswer(userInput);
                game.getCurrentTest().enterProgress(quest.getHeroFromAnswer(userInput));
                game.changeIsNextQ();
                game.getCurrentTest().setCurrentQuestion(game.getCurrentTest().getQuestion());
                return Answer.NEXT_QUESTION;
            }
            return Answer.NO_SUCH_ANSWER;
       }
       else return null;
    }

    private static TelegramAnswer endTest(Game game) {
        TelegramAnswer answer = new TelegramAnswer();
        ArrayList<String> text = new ArrayList<>();
        if (game.checkEndTest()) {
            answer.hasKeyBoard = false;
            Hero resHero = game.getCurrentTest().getResult();
            text.add(resHero.getName());
            text.add(resHero.getDescription());
            game.noteHero(resHero);
            game.getCurrentTest().dropResultsTest();
            game.setCurrentTest(null);
        }

        answer.text = text;
        return answer;
    }
}
