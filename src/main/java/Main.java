//package appBot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {

    public static void main(String[] args) {

        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new TelegramBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        Game game = new Game();

        ConsoleCommunication cc = new ConsoleCommunication();
        cc.printHello();
        String playerName = cc.getPlayerName();
        Player player = new Player(playerName);
        game.setPlayer(player);

        while(true){
            boolean response = cc.HandleUserCommand(game);
            if (response)
                break;
            else if(game.getCurrentTest() != null){
                game.playTest(cc);
            }
        }
        cc.closeScanner();
    }
}
