package appBot;

import java.io.*;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class FileWorker{
    public static void getTestsFromFile() {

        File path = new File(".\\src");
        File [] files = path.listFiles();
        for (int i = 0; i < files.length; i++){
            if (files[i].isFile()){
                //System.out.println(files[i].getName());
                if(i == 1) {
                    String rawData = FileWorker.getRawTests(files[i]);
                    addData(rawData);
                }
            }
        }
    }

    public static String getRawTests(File fileName) {
        StringBuilder result = new StringBuilder();
        try
        {
            FileReader fr = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fr);
            String line = "";
            while (line != null)
            {
                line = reader.readLine();
                if (line != null)
                    result.append(line).append("\n");
            }
            reader.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Exception: File not found");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return result.toString();
    }

    private static Hero findHeroByName(Hero[] heroes, String heroName){
        for(Hero hero : heroes){
            if(hero.getName().equals(heroName))
                return hero;
        }
        return null;
    }

    public static void addData(String data){
        String[] parsedData = data.split("\n");
        //for(int i = 0; i < parsedData.length; i++) {
        //    System.out.println(parsedData[i]);
        //}
        //System.out.println();

        String testName = parsedData[0];
        String[] heroes = parsedData[1].split("[|]");

        Hero[] resultHeroes = new Hero[heroes.length];
        int k = 0;
        for(int i = 0; i < heroes.length; i++) {
            Hero hero = new Hero(testName, heroes[i], heroes[i] + " discription");
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
                //System.out.println(questionName);
                //System.out.println(answer + " : " + hero.getName());
                //System.out.println();
                qAnswers.put(answer, hero);
            }
            Question question = new Question(questionName, qAnswers);
            questions.add(question);

        }
        Test test = new Test(testName, questions);

        System.out.println(test.getName());

    }

}
