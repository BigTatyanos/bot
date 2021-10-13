import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class FileWorker {
    public static List<List<String>> loadTestsFromFile() {

        File path = new File("./src/main/resources/tests");
        File[] files = path.listFiles();
        List<List<String>> result = new ArrayList<>();
        for (File file : Objects.requireNonNull(files)) {
            if (file.isFile()) {
                List<String> rawData = FileWorker.getRawTests(file);
                result.add(rawData);
            }
        }

        return result;
    }

    private static List<String> getRawTests(File fileName) {
        try {
            return Files.lines(Paths.get(fileName.getAbsolutePath())).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static String getBotToken() {
        StringBuilder token = new StringBuilder();
        File fileName = new File("./src/main/resources/config.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null)
                token.append(line);
        } catch (IOException e) {
            String message = "Невозможно запустить бота, так как отсутствует конфигурационный файл";
            System.out.println(message);
        }
        return token.toString();
    }
}
