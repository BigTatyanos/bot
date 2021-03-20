//package appBot;

import java.util.HashSet;
import java.util.Set;

public class Game {
    private Set<Test> tests;
    private Player player;
    private Test currentTest;

    public Game() {
        FileWorker.getTestsFromFile();
        tests = DataPutter.getDownloadedTests();
    }

    public void noteHero(Hero hero){
        this.player.heroes.put(currentTest, hero);
    }

    public boolean checkEndTest(){
        return currentTest.getQuestions().isEmpty();
    }

    public Set<Test> getTests() {
        return tests;
    }

    public Set<String> getTestsNames(){
        Set<String> testsNames = new HashSet<>();
        for(Test test : tests) {
            testsNames.add(test.getName());
        }
        return testsNames;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public Player getPlayer(){
        return player;
    }

    public void setCurrentTest(Test test){
        this.currentTest = test;
    }

    public Test getCurrentTest(){
        return this.currentTest;
    }

    public Test findTest(String name){
        for(Test test: tests){
            if (test.getName().equals(name))
                return test;
        }
        return null;
    }

    public void playTest(ConsoleCommunication cc){
        while (true) {
            Question quest = getCurrentTest().getQuestion();
            ConsoleCommunication.printText(quest.getQuestion());
            for (String answer : quest.getAnswers().keySet()) {
                ConsoleCommunication.printText(answer);
            }
            while (true) {
                String answer = cc.getUserInput();
                if (quest.checkValidAnswer(answer)) {
                    quest.getHeroFromAnswer(answer);
                    getCurrentTest().enterProgress(quest.getHeroFromAnswer(answer));
                    break;
                }
            }
            if (checkEndTest()){
                Hero resHero = getCurrentTest().getResult();
                ConsoleCommunication.printText(resHero.getName());
                ConsoleCommunication.printText(resHero.getDescription());
                noteHero(resHero);
                getCurrentTest().dropResultsTest();
                setCurrentTest(null);
                break;
            }
        }
    }
}
