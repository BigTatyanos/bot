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

    private final Map<String, Test> tests;
    private Player player;
    private Test currentTest;
    private TestProgress testProgress;

    Game(Player player) {
        this.player = player;
        tests = basicTests;
    }

    void noteHero(Hero hero) {
        this.player.heroes.put(currentTest, hero);
    }

    List<String> getTestsNames() {
        return List.copyOf(tests.keySet());
    }

    Player getPlayer() {
        return player;
    }

    void setCurrentTest(Test test) {
        if (test != null) {
            this.currentTest = test;
            this.testProgress = new TestProgress(test);
        } else {
            this.currentTest = null;
            this.testProgress = null;
        }
    }

    public TestProgress getTestProgress() {
        return testProgress;
    }

    Test findTest(String name) {
        return tests.get(name);
    }
}
