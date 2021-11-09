import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {

    private static java.util.Map<String, Game> gamesMap = new java.util.HashMap<>();

    public static Game getGame(String playerName, String playerId) {
        return gamesMap.computeIfAbsent(playerId, x -> new Game(new Player(playerName, playerId)));
    }

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
            String playerName = cc.getPlayerName();
            String playerId = "001";
            Player player = new Player(playerName, playerId);
            Game game;
            switch (playerName) {
                case "Vova": {
                    Game newGame = new Game(new Player(playerName, "123"));
                    newGame.noteHero(new Hero("Гарри Поттер", "HP description"));
                    gamesMap.put(newGame.getPlayer().getId(), newGame);
                    game = newGame;
                    break;
                }
                case "Masha": {
                    Game newGame = new Game(new Player(playerName, "987"));
                    newGame.noteHero(new Hero("Спанч Боб", "SB description"));
                    gamesMap.put(newGame.getPlayer().getId(), newGame);
                    game = newGame;
                    break;
                }
                default: {
                    game = getGame(player.getName(), playerId);
                    break;
                }
            }

            while (true) {
                String userInput = cc.getUserInput();
                GameAnswer answer = Handler.getInput(userInput, game);
                if (answer != null) {
                    if (answer.text != null && !answer.text.isEmpty()) {
                        answer.text.forEach(ConsoleCommunication::printText);
                        if (answer.gameFinished)
                            break;
                    }
                    if (answer.buttonText != null)
                        answer.buttonText.forEach(ConsoleCommunication::printText);
                } else ConsoleCommunication.printText(GameAnswer.errorMessage);
            }
        }
        cc.closeScanner();
    }
}
