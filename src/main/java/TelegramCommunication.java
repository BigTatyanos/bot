import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class TelegramCommunication {

    public static String getPlayerName(Message rMessage) {
        return rMessage.getFrom().getFirstName();
    }

    public static String getPlayerId(Message rMessage) {
        return rMessage.getFrom().getId().toString();
    }

    public static SendMessage addKeyboard(SendMessage message) {
        String[] parsedData = message.getText().split("\n");

        if(parsedData[0].equals("0")) {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < parsedData.length; i++) {
                sb.append(parsedData[i]).append("\n");
            }
            message.setText(sb.toString());
            message.setReplyMarkup(new ReplyKeyboardRemove(true, false));
            return message;
        }
        else {
            SendMessage newSendMessage = new SendMessage();
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            replyKeyboardMarkup.setSelective(true);
            replyKeyboardMarkup.setResizeKeyboard(true);
            replyKeyboardMarkup.setOneTimeKeyboard(true);
            newSendMessage.enableMarkdown(true);


            newSendMessage.setReplyMarkup(replyKeyboardMarkup);
            List<KeyboardRow> keyboard = new ArrayList<>();
            newSendMessage.setText(parsedData[0]);
            for (int i = 1; i < parsedData.length; i++) {
                KeyboardRow kRow = new KeyboardRow();
                kRow.add(parsedData[i]);
                keyboard.add(kRow);
            }

            replyKeyboardMarkup.setKeyboard(keyboard);
            newSendMessage.setChatId(message.getChatId());
            return newSendMessage;
        }
    }
}
