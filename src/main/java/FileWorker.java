import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class FileWorker{
    public static void loadTestsFromFile() {

        File path = new File("./src/main/resources/tests");
        File [] files = path.listFiles();
        for (File file : Objects.requireNonNull(files)) {
            if (file.isFile()) {
                List<String> rawData = FileWorker.getRawTests(file);
                DataPutter.addData(rawData);
            }
        }
    }

    public static List<String> getRawTests(File fileName) {
        try {
            return Files.lines(Paths.get(fileName.getAbsolutePath())).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static String getBotToken() {
        File fileName = new File("./src/main/resources/config.txt");

        try {
            return Files.lines(Paths.get(fileName.getAbsolutePath())).collect(Collectors.joining());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
