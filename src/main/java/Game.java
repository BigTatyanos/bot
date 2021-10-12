import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Game {
    private Map<String, Test> tests;
    private Player player;
    private Test currentTest;
    private boolean isNextQuestion;

    private static Map<String, Test> basicTests = new HashMap<>();

    private Game() {
        if (basicTests.isEmpty()) {
            FileWorker.loadTestsFromFile();
            basicTests = DataPutter.getDownloadedTests();
        }
    }

    Game(Player player) {
        new Game();
        this.player = player;
        tests = new HashMap<>();
        tests.putAll(basicTests);
        isNextQuestion = true;
    }

    void noteHero(Hero hero) {
        this.player.heroes.put(currentTest, hero);
    }

    boolean checkEndTest() {
        return currentTest.getQuestions().isEmpty();
    }

    List<String> getTestsNames() {
        return new ArrayList<>(tests.keySet());
    }

    Player getPlayer() {
        return player;
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
        return tests.get(name);
    }
}
