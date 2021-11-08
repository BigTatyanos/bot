import java.util.*;

public class TestProgress {
    private Test test;
    private Queue<Question> questions;
    private Map<Hero, Integer> progress;

    public TestProgress(Test test) {
        this.test = test;
        questions = new LinkedList<>(test.getQuestions());
        progress = new HashMap<>();
    }

    public boolean isValidInput(String userInput) {
        if (!getCurrentQuestion().checkValidAnswer(userInput)) {
            return false;
        }
        Hero hero = getCurrentQuestion().getHeroFromAnswer(userInput);
        progress.compute(hero, (h, c) -> c == null ? 1 : c + 1);
        questions.poll();
        return true;
    }

    public Question getCurrentQuestion() {
        return questions.peek();
    }

    public Hero getResult() {
        return progress.entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .get().getKey();
    }
}
