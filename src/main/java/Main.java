//package appBot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {

    public static java.util.List<Game> gamesList = new java.util.ArrayList<>();
    public static void main(String[] args) {

        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new TelegramBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }


//        Game game = new Game();
//
//        Game game1 = new Game();
//        Player player1 = new Player("Vova", "123");
//        game1.setCurrentTest(game.findTest("Кто ты из губки Боба"));
//        game1.setPlayer(player1);
//        game1.noteHero(new Hero("Гарри? Гермиона? Рон?", "Гарри Поттер", "HP description"));
//
//        Game game2 = new Game();
//        Player player2 = new Player("Masha", "987");
//        game2.setCurrentTest(game.findTest("Гарри? Гермиона? Рон?"));
//        game2.setPlayer(player2);
//        game2.noteHero(new Hero("Кто ты из губки Боба", "Спанч Боб", "SB description"));
//        gamesList.add(game1);
//        gamesList.add(game2);
//
//        ConsoleCommunication cc = new ConsoleCommunication();
//        cc.printHello();
//        String playerName = cc.getPlayerName();
//        String playerId = "001";
//        Player player = new Player(playerName, playerId);
//        game.setPlayer(player);
//
//        if(playerName.equals("Vova"))
//            playerId = "123";
//        else if(playerName.equals("Masha"))
//            playerId = "987";
//        game = Handler.getGame(player.getName(), playerId);
//
//        while(true){
//            boolean response = cc.HandleUserCommand(game);
//            if (response)
//                break;
//            else if(game.getCurrentTest() != null){
//                game.playTest(cc);
//            }
//        }
//        cc.closeScanner();
    }
}
