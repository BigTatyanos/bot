import java.util.Set;

public class Handler {
    public static void handle(){
        for(Game game : Main.gamesList){

            System.out.println(game.getPlayer().getName());
            System.out.println(game.getCurrentTest().getName());
        }
    }

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
                String checkAnswerResult = checkAnswer(userInput, game);
                if(checkAnswerResult != null) {
                    sb.append(checkAnswerResult);
                    if (checkAnswerResult.equals("Следующий вопрос:")) {
                        sb.append(sendQuestion(game));
                        game.changeIsNextQ();
                    }
                }
                else return endTest(game);

            }
//            if(game.getIsNext()) {
//                sb.append(sendQuestion(game));
//                game.changeIsNextQ();
//            }
        }
        else {
            if (userInput.equals("/help")) {
                sb.append("To do: make help").append("\n");
            } else if (userInput.equals("/start")) {
                sb.append("Какой тест хочешь пройти? Вот список тестов:").append("\n");
                for (String testName : game.getTestsNames())
                    sb.append(testName).append("\n");
            } else if (userInput.equals("/exit")) {
                sb.append("Пока, до скорой встречи!").append("\n");
            } else if (userInput.equals("/heroes")) {
                Set<Hero> heroes = game.getPlayer().getHeroes();
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
                sb.append("Выгружаю тест...").append("\n");
                sb.append(sendQuestion(game));
            } else {
                sb.append("Некорректная строка").append("\n");
            }
        }
        return sb.toString();
    }

    private static String sendQuestion(Game game) {
        StringBuilder sb = new StringBuilder();
        if(!game.checkEndTest() || game.getCurrentTest().getCurrentQuestion() != null) {
            //Question quest = game.getCurrentTest().getQuestion();
            Question quest = game.getCurrentTest().getCurrentQuestion();
            //game.getCurrentTest().setCurrentQuestion(quest);
            sb.append(quest.getQuestion()).append("\n");
            for (String answer : quest.getAnswers().keySet()) {
                sb.append(answer).append("\n");
            }
            //game.getCurrentTest().setCurrentQuestion(game.getCurrentTest().getQuestion());
            return sb.toString();
        }
        return endTest(game);
    }

    private static String checkAnswer(String userInput, Game game) {
        if(!game.checkEndTest()) {
            //Question quest = game.getCurrentTest().getQuestion();
            Question quest = game.getCurrentTest().getCurrentQuestion();
            if(userInput.equals("/1"))
                userInput = (String) quest.getAnswers().keySet().toArray()[0];
            else if(userInput.equals("/2"))
                userInput = (String) quest.getAnswers().keySet().toArray()[1];
            else if(userInput.equals("/3"))
                userInput = (String) quest.getAnswers().keySet().toArray()[2];
            else if(userInput.equals("/4"))
                userInput = (String) quest.getAnswers().keySet().toArray()[3];

            if (quest.checkValidAnswer(userInput)) {
                quest.getHeroFromAnswer(userInput);
                game.getCurrentTest().enterProgress(quest.getHeroFromAnswer(userInput));
                game.changeIsNextQ();
                game.getCurrentTest().setCurrentQuestion(game.getCurrentTest().getQuestion());
                return "Следующий вопрос:";
            }
            return "Нет такого ответа";
       }
       else return null;
    }

    private static String endTest(Game game) {
        StringBuilder sb = new StringBuilder();
        if (game.checkEndTest()) {
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
