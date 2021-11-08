import java.util.Collections;
import java.util.List;

public class Test {
    private String name;
    private List<Question> questions;

    public Test(String name, List<Question> questions) {
        this.name = name;
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return Collections.unmodifiableList(questions);
    }

    public String getTestName() {
        return name;
    }
}
