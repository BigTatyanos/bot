import java.util.Set;

enum Answer { NEXT_QUESTION, NO_SUCH_ANSWER}

public class Handler {
    public static Game getGame(String playerName, String playerId) {
        Game game = findGame(playerId);
        if(game == null) {
            game = new Game();
            Player player = new Player(playerName, playerId);
            game.setPlayer(player);
            Main.gamesList.add(game);
            return game;
        }
        return game;
    }

    public static Game findGame(String playerId) {
        for(Game game : Main.gamesList){
            if(game.getPlayer().getId().equals(playerId))
                return game;
        }
        return null;
    }

    public static String getInput(String userInput, Game game) {
        StringBuilder sb = new StringBuilder();
        if(game.getCurrentTest() != null) {
            if(game.getCurrentTest().getCurrentQuestion() != null) {
                Answer checkAnswerResult = checkAnswer(userInput, game);
                if(checkAnswerResult != null) {
                    if (checkAnswerResult.equals(Answer.NEXT_QUESTION)) {
                        sb.append(sendQuestion(game));
                        game.changeIsNextQ();
                    }
                }
                else return endTest(game);

            }
        }
        else {
            if (userInput.equals("/help")) {
                sb.append("0\n");
                sb.append("Введи /start, чтобы начать игру").append("\n");
                sb.append("Введи /help, чтобы получить справку об игре").append("\n");
                sb.append("Введи /exit, чтобы закончить игру").append("\n");
                sb.append("Введи /heroes, чтобы вывести список своих полученных персонажей").append("\n");
                sb.append("Это тест бот, в котором нужно выбирать тест и отвечать на вопросы, а в конце ты узнаешь, кто ты из персонажей").append("\n");
            } else if (userInput.equals("/start")) {
                sb.append("Какой тест хочешь пройти? Вот список тестов:").append("\n");
                for (String testName : game.getTestsNames())
                    sb.append(testName).append("\n");
            } else if (userInput.equals("/exit")) {
                sb.append("0\n");
                sb.append("Пока, до скорой встречи!").append("\n");
            } else if (userInput.equals("/heroes")) {
                Set<Hero> heroes = game.getPlayer().getHeroes();
                sb.append("0\n");
                if (heroes.isEmpty()) {
                    sb.append("Ты ещё не прошёл ни одного теста").append("\n");
                } else {
                    for (Hero hero : heroes) {
                        sb.append(hero.getName()).append("\n");
                    }
                }
            } else if (game.getTestsNames().contains(userInput)) {
                game.setCurrentTest(game.findTest(userInput));
                Test currentTest = game.getCurrentTest();
                currentTest.setCurrentQuestion(currentTest.getQuestion());
                sb.append(sendQuestion(game));
            } else {
                sb.append("0\n");
                sb.append("Некорректная строка").append("\n");
            }
        }
        return sb.toString();
    }

    private static String sendQuestion(Game game) {
        StringBuilder sb = new StringBuilder();
        if(!game.checkEndTest() || game.getCurrentTest().getCurrentQuestion() != null) {
            Question quest = game.getCurrentTest().getCurrentQuestion();
            sb.append(quest.getQuestion()).append("\n");
            for (String answer : quest.getAnswers()) {
                sb.append(answer).append("\n");
            }
            return sb.toString();
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

    private static String endTest(Game game) {
        StringBuilder sb = new StringBuilder();
        if (game.checkEndTest()) {
            sb.append("0\n");
            Hero resHero = game.getCurrentTest().getResult();
            sb.append(resHero.getName()).append("\n");
            sb.append(resHero.getDescription()).append("\n");
            game.noteHero(resHero);
            game.getCurrentTest().dropResultsTest();
            game.setCurrentTest(null);
        }
        return sb.toString();
    }
}
