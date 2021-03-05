package appBot;
import java.util.*;

public class Test {
    private String name;
    public Set<Question> questions;
    public Set<Question> usedQuestions;
    private Map<Hero, Integer> progress;

    public Test(String name, Set<Question> questions){
        this.name = name;
        this.questions = questions;
        this.progress = new HashMap<>();
    }

    public String getName() {
        return this.name;
    }

    private void enterProgress(Hero hero){
        if(this.progress.containsKey(hero))
            this.progress.put(hero, progress.get(hero) + 1);
        else
            this.progress.put(hero, 1);
    }

    private Question getQuestion(){
        for (Question question : questions) {
            this.usedQuestions.add(question);
            this.questions.remove(question);
            return question;
        }
        return null;
    }

    public Hero getResult(){
        Integer maxRes = Collections.max(this.progress.values());

        for (Hero hero : progress.keySet()) {
            Integer obj = progress.get(hero);
            if (hero != null) {
                if (maxRes.equals(obj)) {
                    return hero;
                }
            }
        }
        return null;
    }

    private void dropResultsTest(){
        this.questions.addAll(this.usedQuestions);
        this.usedQuestions.clear();
        this.progress.clear();
    }

}
