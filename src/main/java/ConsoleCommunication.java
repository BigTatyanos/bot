import java.util.Scanner;

public class ConsoleCommunication {
    private Scanner in = new Scanner(System.in);

    public static void printText(String text) {
        System.out.println(text);
    }

    public String getUserInput() {
        String input = in.nextLine();
        return input;
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
        printText("Введи /start, чтобы начать игру");
        printText("Введи /help, чтобы получить справку об игре");
        printText("Введи /exit, чтобы закончить игру");
        printText("Введи /heroes, чтобы вывести список своих полученных персонажей");
        printText("Это тест бот, в котором нужно выбирать тест и отвечать на вопросы, а в конце ты узнаешь, кто ты из персонажей");
    }
}
