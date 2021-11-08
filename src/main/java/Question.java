import java.util.Map;
import java.util.Set;

public class Question {
    private String question;
    private Map<String, Hero> answers;

    public Question(String question, Map<String, Hero> answers) {
        this.question = question;
        this.answers = answers;
    }

    public Hero getHeroFromAnswer(String answer) {
        return this.answers.get(answer);
    }

    public boolean checkValidAnswer(String answer) {
        return answers.containsKey(answer);
    }

    public String getQuestion() {
        return this.question;
    }

    public Set<String> getAnswers() {
        return this.answers.keySet();
    }
}
