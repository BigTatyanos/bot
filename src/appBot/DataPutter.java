package appBot;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DataPutter {

    private static Set<Test> downloadedTests = new HashSet<>();
    private static Hero findHeroByName(Hero[] heroes, String heroName){
        for(Hero hero : heroes){
            if(hero.getName().equals(heroName))
                return hero;
        }
        return null;
    }

    public static void addData(String data){
        String[] parsedData = data.split("\n");

        String testName = parsedData[0];
        String[] heroes = parsedData[1].split("[|]");

        Hero[] resultHeroes = new Hero[heroes.length];
        int k = 0;
        for(int i = 0; i < heroes.length; i++) {
            Hero hero = new Hero(testName, heroes[i], heroes[i] + " description");
            resultHeroes[k] = hero;
            k++;
        }

        Set<Question> questions = new HashSet<>();
        String questionName = "";
        for(int i = 2; i < parsedData.length; i++) {
            Map<String, Hero> qAnswers = new HashMap<>();
            String[] questionData = parsedData[i].split("[|]");
            questionName = questionData[0];
            for(int j = 1; j < questionData.length; j++) {
                String answer = questionData[j].split("[(]")[0];
                String heroName = questionData[j].split("[(]")[1].split("[)]")[0];
                Hero hero = findHeroByName(resultHeroes, heroName);
                qAnswers.put(answer, hero);
            }
            Question question = new Question(questionName, qAnswers);
            questions.add(question);

        }
        downloadedTests.add(new Test(testName, questions));
    }

    public static Set<Test> getDownloadedTests() { return downloadedTests; }
}
