import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        TestProgress testProgress = game.getTestProgress();
        if (testProgress != null) {
            if (testProgress.isValidInput(userInput)) {
                Question nextQuestion = testProgress.getCurrentQuestion();
                if (nextQuestion != null)
                    return sendQuestion(nextQuestion);
                else return endTest(game);
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
                    answer.text = Collections.singletonList("Какой тест хочешь пройти? Вот список тестов:");
                    answer.buttonText = game.getTestsNames();
                    return answer;
                }

                case "/exit": {
                    GameAnswer answer = new GameAnswer();
                    answer.hasKeyBoard = false;
                    answer.text = Collections.singletonList("Пока, до скорой встречи!");
                    answer.gameFinished = true;
                    return answer;
                }

                case "/heroes": {
                    GameAnswer answer = new GameAnswer();
                    answer.hasKeyBoard = false;
                    Set<Hero> heroes = game.getPlayer().getHeroes();
                    if (heroes.isEmpty()) {
                        answer.text = Collections.singletonList("Ты ещё не прошёл ни одного теста");
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
                        Question firstQuestion = game.getTestProgress().getCurrentQuestion();
                        return sendQuestion(firstQuestion);
                    } else {
                        GameAnswer answer = new GameAnswer();
                        answer.hasKeyBoard = false;
                        answer.text = Collections.singletonList("Некорректная строка");
                        return answer;
                    }
                }
            }
        }
        return null;
    }

    private static GameAnswer sendQuestion(Question question) {
        List<String> text = new ArrayList<>();
        GameAnswer answer = new GameAnswer();
        text.add(question.getQuestion());
        List<String> buttonText = List.copyOf(question.getAnswers());
        answer.text = text;
        answer.buttonText = buttonText;
        return answer;
    }

    private static GameAnswer endTest(Game game) {
        GameAnswer answer = new GameAnswer();
        ArrayList<String> text = new ArrayList<>();
        answer.hasKeyBoard = false;
        Hero resHero = game.getTestProgress().getResult();
        text.add(resHero.getName());
        text.add(resHero.getDescription());
        game.noteHero(resHero);
        game.setCurrentTest(null);

        answer.text = text;
        return answer;
    }
}
