import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TestMaker {
    public static Test makeTest(List<String> data) {
        String testName = data.get(0);
        String[] heroes = data.get(1).split("[|]");

        Map<String, Hero> resultHeroes;
        resultHeroes = Arrays.stream(heroes).collect(Collectors.toMap(hero -> hero, hero -> new Hero(hero, hero + " description")));

        List<Question> questions = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int i = 2;
        for (; i < data.size() && !data.get(i).equals("#"); i++)
            sb.append(data.get(i)).append("\n");

        String rawQA = sb.toString();

        String regex = getRegExp(heroes.length);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(rawQA);
        while (matcher.find()) {
            String questionName = matcher.group(1);
            Map<String, Hero> qAnswers = new HashMap<>();
            for (int k = 3; k < matcher.groupCount(); k += 3) {
                String answer = matcher.group(k);
                String heroName = matcher.group(k + 1);
                Hero hero = resultHeroes.get(heroName);
                qAnswers.put(answer, hero);
            }
            Question question = new Question(questionName, qAnswers);
            questions.add(question);
        }

        for (i++; i < data.size(); i++) {
            String heroName = data.get(i).split("[|]")[0];
            String description = data.get(i).split("[|]")[1];
            Hero hero = resultHeroes.get(heroName);
            hero.setDescription(description);
        }
        return new Test(testName, questions);
    }

    private static String getRegExp(int n) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(.*\\?)");

        for (int i = 0; i < n; i++)
            stringBuilder.append("\\|((.*)\\((.*)\\))");
        return stringBuilder.toString();
    }
}
