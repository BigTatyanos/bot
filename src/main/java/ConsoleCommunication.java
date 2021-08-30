import java.util.List;
import java.util.Scanner;

public class ConsoleCommunication {
    private Scanner in = new Scanner(System.in);

    public static void printText(String text) {
        System.out.println(text);
    }

    public String getUserInput() {
        return in.nextLine();
    }

    public void closeScanner() {
        in.close();
    }

    public void printHello() {
        String message = "Привет, это ТестБот.";
        printText(message);
    }

    public String getPlayerName() {
        printText("Как Вас зовут?");
        String playerName = getUserInput();
        getHelp();
        return playerName;
    }

    private void getHelp() {
        List<String> helpMessage = Handler.getHelp();
        helpMessage.forEach(ConsoleCommunication::printText);
    }
}
