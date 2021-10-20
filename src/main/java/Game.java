import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

class Game {
    private static Map<String, Test> basicTests;

    static {
        basicTests = FileWorker.loadTestsFromFile()
                .stream()
                .map(TestMaker::makeTest)
                .collect(toMap(Test::getTestName, Function.identity()));
    }

    private Map<String, Test> tests;
    private Player player;
    private Test currentTest;

    Game(Player player) {
        this.player = player;
        tests = getCopyOfTests();
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
        return List.copyOf(tests.keySet());
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

    Test findTest(String name) {
        return tests.get(name);
    }
}
