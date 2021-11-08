import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test {
    private String name;
    private List<Question> questions;

    public Test(String name, List<Question> questions) {
        this.name = name;
        this.questions = questions;
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

    public String getTestName() {
        return name;
    }
}
