import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Game {
    private static Map<String, Test> basicTests;

    static {
        TestMaker.makeTests(FileWorker.loadTestsFromFile());
        basicTests = TestMaker.getMadeTests();
    }

    private Map<String, Test> tests;
    private Player player;
    private Test currentTest;
    private boolean isNextQuestion;

    Game(Player player) {
        this.player = player;
        tests = getCopyOfTests();
        isNextQuestion = true;
    }

    private static Map<String, Test> getCopyOfTests() {
        Map<String, Test> result = new HashMap<>();
        basicTests.forEach((key, value) -> result.put(key, value.clone()));
        return result;
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
