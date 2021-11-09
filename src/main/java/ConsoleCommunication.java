import java.util.List;
import java.util.Scanner;

class ConsoleCommunication {
    private Scanner in = new Scanner(System.in);

    static void printText(String text) {
        System.out.println(text);
    }

    String getUserInput() {
        return in.nextLine();
    }

    void closeScanner() {
        in.close();
    }

    void printHello() {
        String message = "Привет, это ТестБот.";
        printText(message);
    }

    String getPlayerName() {
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
