import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;

public class TelegramBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "testik_boy_bot";
    }

    @Override
    public String getBotToken() {
        return FileWorker.getBotToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message rMessage = update.getMessage();
        String playerId = TelegramCommunication.getPlayerId(rMessage);
        String playerName = TelegramCommunication.getPlayerName(rMessage);

        String answer = "";
        Game game = new Game();
        synchronized (game) {
            game = Handler.getGame(playerName, playerId);
            answer = Handler.getInput(rMessage.getText(), game);
        }

        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(answer);
        sendMessage.setChatId(playerId);

//        for(Game curGame : Main.gamesList){
//            if(curGame.getPlayer().getId().equals(playerId)) {
//                Main.gamesList.remove(curGame);
//                Main.gamesList.add(game);
//                break;
//            }
//        }
        try {
            if(!sendMessage.getText().isEmpty())
                execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
