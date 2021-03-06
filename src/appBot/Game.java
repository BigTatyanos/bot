package appBot;

import java.util.Set;

public class Game {
    private Set<Test> tests;
    public Player player;
    private Test currentTest;

    public Game(){
        tests = DataPutter.getDownloadedTests();
    }

    public void noteHero(){
        this.player.heroes.put(currentTest, currentTest.getResult());
    }

    public boolean checkEndTest(){
        return currentTest.getQuestions().isEmpty();
    }

    public Set<Test> getTests() {
        return tests;
    }
}
