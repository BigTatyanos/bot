package appBot;
import java.util.Map;

public class Question {
    private String question;
    private Map<String, Hero> answers;

    public Hero getHeroFromAnswer(String answer){
        return this.answers.get(answer);
    }
}
