import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class Game {
    private Map<String, Test> tests;
    private Player player;
    private Test currentTest;
    private boolean isNextQuestion;

    Game() {
        FileWorker.loadTestsFromFile();
        Map<String, Test> basicTests = DataPutter.getDownloadedTests();
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

    Set<String> getTestsNames() {
        return tests.keySet();
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
        return tests.get(name);
    }
}
