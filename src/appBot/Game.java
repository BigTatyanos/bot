package appBot;

import java.util.Set;

public class Game {
    public static Set<Test> tests;
    public Player player;
    private Test currentTest;

    public void noteHero(){
        this.player.heroes.put(currentTest, currentTest.getResult());
    }

    public boolean checkEndTest(){
        return currentTest.questions.isEmpty();
    }
    
}
