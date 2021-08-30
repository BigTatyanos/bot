import java.util.HashSet;
import java.util.Set;

public class Game {
    private Set<Test> tests;
    private Player player;
    private Test currentTest;
    private boolean isNextQuestion;

    public Game() {
        FileWorker.loadTestsFromFile();
        Set<Test> basicTests = DataPutter.getDownloadedTests();
        tests = new HashSet<>();
        tests.addAll(basicTests);
        isNextQuestion = true;
    }

    public void noteHero(Hero hero) {
        this.player.heroes.put(currentTest, hero);
    }

    public boolean checkEndTest() {
        return currentTest.getQuestions().isEmpty();
    }

    public Set<Test> getTests() {
        return tests;
    }

    public Set<String> getTestsNames() {
        Set<String> testsNames = new HashSet<>();
        for (Test test : tests) {
            testsNames.add(test.getName());
        }
        return testsNames;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Test getCurrentTest() {
        return this.currentTest;
    }

    public void setCurrentTest(Test test) {
        this.currentTest = test;
    }

    public void changeIsNextQ() {
        this.isNextQuestion = !this.isNextQuestion;
    }

    public boolean getIsNext() {
        return isNextQuestion;
    }

    public Test findTest(String name) {
        for (Test test : tests) {
            if (test.getName().equals(name))
                return test;
        }
        return null;
    }
}
