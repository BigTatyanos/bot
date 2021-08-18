import java.util.*;
import java.util.stream.Collectors;

public class DataPutter {

    private static Set<Test> downloadedTests = new HashSet<>();
    private static Hero findHeroByName(Map<String, Hero> heroes, String heroName){
        return heroes.getOrDefault(heroName, null);
    }

    public static void addData(List<String> data){

        String testName = data.get(0);
        String[] heroes = data.get(1).split("[|]");

        Map<String, Hero> resultHeroes;
        resultHeroes = Arrays.stream(heroes).collect(Collectors.toMap(hero -> hero, hero -> new Hero(testName, hero, hero + " description")));

        List<Question> questions = new ArrayList<>();
        String questionName;
        int i = 2;
        for(; i < data.size() && !data.get(i).equals("#"); i++) {
            Map<String, Hero> qAnswers = new HashMap<>();
            String[] questionData = data.get(i).split("[|]");
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
        for(i++; i < data.size(); i++) {
            String heroName = data.get(i).split("[|]")[0];
            String description = data.get(i).split("[|]")[1];
            Hero hero = findHeroByName(resultHeroes, heroName);
            hero.setDescription(description);
        }
        downloadedTests.add(new Test(testName, questions));
    }

    public static Set<Test> getDownloadedTests() { return downloadedTests; }
}
