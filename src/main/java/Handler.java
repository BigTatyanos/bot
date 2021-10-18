import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

enum Answer {NEXT_QUESTION, NO_SUCH_ANSWER}

public class Handler {
    public static List<String> getHelp() {
        List<String> text = new ArrayList<>();
        text.add("Введи /start, чтобы начать игру");
        text.add("Введи /help, чтобы получить справку об игре");
        text.add("Введи /exit, чтобы закончить игру");
        text.add("Введи /heroes, чтобы вывести список своих полученных персонажей");
        text.add("Это тест бот, в котором нужно выбирать тест и отвечать на вопросы, а в конце ты узнаешь, кто ты из персонажей");
        return text;
    }

    public static GameAnswer getInput(String userInput, Game game) {
        if (game.getCurrentTest() != null) {
            if (game.getCurrentTest().getCurrentQuestion() != null) {
                Answer checkAnswerResult = checkAnswer(userInput, game);
                if (checkAnswerResult != null) {
                    if (checkAnswerResult.equals(Answer.NEXT_QUESTION)) {
                        GameAnswer answer = sendQuestion(game);
                        game.changeIsNextQ();
                        return answer;
                    }
                } else return endTest(game);
            }
        } else {
            switch (userInput) {
                case "/help": {
                    GameAnswer answer = new GameAnswer();
                    answer.hasKeyBoard = false;
                    answer.text = getHelp();
                    return answer;
                }

                case "/start": {
                    GameAnswer answer = new GameAnswer();
                    answer.text = Arrays.asList("Какой тест хочешь пройти? Вот список тестов:");
                    answer.buttonText = game.getTestsNames();
                    return answer;
                }

                case "/exit": {
                    GameAnswer answer = new GameAnswer();
                    answer.hasKeyBoard = false;
                    answer.text = Arrays.asList("Пока, до скорой встречи!");
                    return answer;
                }

                case "/heroes": {
                    GameAnswer answer = new GameAnswer();
                    answer.hasKeyBoard = false;
                    Set<Hero> heroes = game.getPlayer().getHeroes();
                    if (heroes.isEmpty()) {
                        answer.text = Arrays.asList("Ты ещё не прошёл ни одного теста");
                        return answer;
                    } else {
                        answer.text = heroes.stream()
                                .map(Hero::getName)
                                .collect(Collectors.toList());
                        return answer;
                    }
                }

                default: {
                    if (game.getTestsNames().contains(userInput)) {
                        game.setCurrentTest(game.findTest(userInput));
                        Test currentTest = game.getCurrentTest();
                        currentTest.setCurrentQuestion(currentTest.getQuestion());
                        return sendQuestion(game);
                    } else {
                        GameAnswer answer = new GameAnswer();
                        answer.hasKeyBoard = false;
                        answer.text = Arrays.asList("Некорректная строка");
                        return answer;
                    }
                }
            }
        }
        return null;
    }

    private static GameAnswer sendQuestion(Game game) {
        if (game.checkEndTest() && game.getCurrentTest().getCurrentQuestion() == null)
            return endTest(game);
        List<String> text = new ArrayList<>();
        GameAnswer answer = new GameAnswer();
        Question quest = game.getCurrentTest().getCurrentQuestion();
        text.add(quest.getQuestion());
        List<String> buttonText = List.copyOf(quest.getAnswers());
        answer.text = text;
        answer.buttonText = buttonText;
        return answer;
    }

    private static Answer checkAnswer(String userInput, Game game) {
        if (!game.checkEndTest()) {
            Question quest = game.getCurrentTest().getCurrentQuestion();
            if (quest.checkValidAnswer(userInput)) {
                quest.getHeroFromAnswer(userInput);
                game.getCurrentTest().enterProgress(quest.getHeroFromAnswer(userInput));
                game.changeIsNextQ();
                game.getCurrentTest().setCurrentQuestion(game.getCurrentTest().getQuestion());
                return Answer.NEXT_QUESTION;
            }
            return Answer.NO_SUCH_ANSWER;
        } else return null;
    }

    private static GameAnswer endTest(Game game) {
        GameAnswer answer = new GameAnswer();
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
