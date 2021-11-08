import java.util.*;

public class Test {
    private String name;
    private List<Question> questions;
    private List<Question> usedQuestions;
    private Map<Hero, Integer> progress;
    private Question currentQuestion;

    public Test(String name, List<Question> questions) {
        this.name = name;
        this.questions = questions;
        this.progress = new HashMap<>();
        this.usedQuestions = new ArrayList<>();
    }

    public Test clone() {
        return new Test(name, getCopyOfQuestions());
    }

    private List<Question> getCopyOfQuestions() {
        List<Question> copy = new ArrayList<>();
        this.questions.forEach(x -> copy.add(x.clone()));
        return copy;
    }

    public List<Question> getQuestions() {
        return Collections.unmodifiableList(questions);
    }

    public void enterProgress(Hero hero) {
        if (progress.containsKey(hero))
            progress.put(hero, progress.get(hero) + 1);
        else
            progress.put(hero, 1);
    }

    public Question getQuestion() {
        Question question = questions.get(0);
        usedQuestions.add(question);
        questions.remove(question);
        return question;
    }

    public Hero getResult() {
        return progress.entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .get().getKey();
    }

    public void dropResultsTest() {
        this.questions.addAll(usedQuestions);
        this.usedQuestions.clear();
        this.progress.clear();
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(Question quest) {
        currentQuestion = quest;
    }

    public String getTestName() {
        return name;
    }
}
