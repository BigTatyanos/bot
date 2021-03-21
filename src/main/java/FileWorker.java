import java.io.*;
import java.io.BufferedReader;


public class FileWorker{
    public static void getTestsFromFile() {

        File path = new File(".\\src\\main\\resources\\tests");
        File [] files = path.listFiles();
        for (int i = 0; i < files.length; i++){
            if (files[i].isFile()){
                String rawData = FileWorker.getRawTests(files[i]);
                DataPutter.addData(rawData);
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

    public static String getBotToken() {
        File fileName = new File(".\\src\\main\\resources\\config.txt");
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
                    result.append(line);
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

}
