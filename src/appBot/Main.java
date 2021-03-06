package appBot;

public class Main {

    public static void main(String[] args) {
        FileWorker.getTestsFromFile();
        Game game = new Game();
        ConsoleCommunication.printHello();
        ConsoleCommunication cc = new ConsoleCommunication();
        cc.HandleUserCommand(game);
        ConsoleCommunication.printText(cc.HandleUserInput());

        cc.closeScanner();
    }
}
