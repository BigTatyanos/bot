package appBot;
import java.util.Map;

public class Question {
    private String question;
    private Map<String, Hero> answers;

    public Hero getHeroFromAnswer(String answer){
        return this.answers.get(answer);
    }
    
    public Question(String question, Map<String, Hero> answers){
        this.question = question;
        this.answers = answers;
    }

    public boolean checkValidAnswer(String answer){
        return answers.containsKey(answer);
    }

    public String getQuestion() { return this.question; }

    public Map<String, Hero> getAnswers() { return this.answers; }
}
