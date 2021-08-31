import java.util.HashSet;
import java.util.Set;

class Game {
    private Set<Test> tests;
    private Player player;
    private Test currentTest;
    private boolean isNextQuestion;

    Game() {
        FileWorker.loadTestsFromFile();
        Set<Test> basicTests = DataPutter.getDownloadedTests();
        tests = new HashSet<>();
        tests.addAll(basicTests);
        isNextQuestion = true;
    }

    void noteHero(Hero hero) {
        this.player.heroes.put(currentTest, hero);
    }

    boolean checkEndTest() {
        return currentTest.getQuestions().isEmpty();
    }

    Set<String> getTestsNames() {
        Set<String> testsNames = new HashSet<>();
        for (Test test : tests) {
            testsNames.add(test.getName());
        }
        return testsNames;
    }

    Player getPlayer() {
        return player;
    }

    void setPlayer(Player player) {
        this.player = player;
    }

    Test getCurrentTest() {
        return this.currentTest;
    }

    void setCurrentTest(Test test) {
        this.currentTest = test;
    }

    void changeIsNextQ() {
        this.isNextQuestion = !this.isNextQuestion;
    }

    Test findTest(String name) {
        for (Test test : tests) {
            if (test.getName().equals(name))
                return test;
        }
        return null;
    }
}
