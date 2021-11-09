import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

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

        Game game = Main.getGame(playerName, playerId);
        GameAnswer answer = Handler.getInput(rMessage.getText(), game);

        SendMessage sendMessage = new SendMessage();
        if (answer != null) {
            sendMessage.setText(String.join("\n", answer.text));
            if (!answer.hasKeyBoard)
                sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true, false));
            else {
                ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                replyKeyboardMarkup.setSelective(true);
                replyKeyboardMarkup.setResizeKeyboard(true);
                replyKeyboardMarkup.setOneTimeKeyboard(true);
                sendMessage.enableMarkdown(true);
                sendMessage.setReplyMarkup(replyKeyboardMarkup);
                List<KeyboardRow> keyboard = new ArrayList<>();
                for (int i = 0; i < answer.buttonText.size(); i++) {
                    KeyboardRow kRow = new KeyboardRow();
                    kRow.add(answer.buttonText.get(i));
                    keyboard.add(kRow);
                }
                replyKeyboardMarkup.setKeyboard(keyboard);
            }
        } else sendMessage.setText(GameAnswer.errorMessage);
        sendMessage.setChatId(playerId);

        try {
            if (!sendMessage.getText().isEmpty())
                execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
