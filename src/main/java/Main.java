import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {

    static java.util.List<Game> gamesList = new java.util.ArrayList<>();

    public static void main(String[] args) {

        ConsoleCommunication cc = new ConsoleCommunication();
        cc.printHello();
        ConsoleCommunication.printText("Запустить telegram версию y/n?");
        if (cc.getUserInput().equals("y")) {
            try {
                TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
                telegramBotsApi.registerBot(new TelegramBot());
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else {
            Game game = new Game();

            Game game1 = new Game();
            Player player1 = new Player("Vova", "123");
            game1.setCurrentTest(game.findTest("Кто ты из губки Боба"));
            game1.setPlayer(player1);
            game1.noteHero(new Hero("Гарри? Гермиона? Рон?", "Гарри Поттер", "HP description"));

            Game game2 = new Game();
            Player player2 = new Player("Masha", "987");
            game2.setCurrentTest(game.findTest("Гарри? Гермиона? Рон?"));
            game2.setPlayer(player2);
            game2.noteHero(new Hero("Кто ты из губки Боба", "Спанч Боб", "SB description"));
            gamesList.add(game1);
            gamesList.add(game2);


            String playerName = cc.getPlayerName();
            String playerId = "001";
            Player player = new Player(playerName, playerId);
            game.setPlayer(player);
            game = Handler.getGame(player.getName(), playerId);

            if (playerName.equals("Vova"))
                game.setPlayer(player1);
            else if (playerName.equals("Masha"))
                game.setPlayer(player2);

            while (true) {
                String userInput = cc.getUserInput();
                TelegramAnswer answer = Handler.getInput(userInput, game);
                if (answer.text != null && !answer.text.isEmpty()) {
                    answer.text.forEach(ConsoleCommunication::printText);
                    if (answer.text.get(0).equals("Пока, до скорой встречи!"))
                        break;
                }
                if (answer.buttonText != null)
                    answer.buttonText.forEach(ConsoleCommunication::printText);
            }
        }
        cc.closeScanner();
    }
}
