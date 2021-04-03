import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Set;

public class TelegramCommunication {
    private String id;
    public TelegramCommunication(String id) {
        this.id = id;
    }
    public static String getPlayerName(Message rMessage) {
        return rMessage.getFrom().getFirstName();
    }

    public String getPlayerAnswer(Message rMessage) {
        return rMessage.getText();
    }

    public static String getPlayerId(Message rMessage) {
        return rMessage.getFrom().getId().toString();
    }

    public static SendMessage addText(SendMessage sMessage, String text) {
        String oldtext = sMessage.getText();
        if(oldtext != null)
            sMessage.setText(oldtext + "\n" + text);
        else sMessage.setText(text);
        return sMessage;
    }

    private SendMessage getHelp(SendMessage sMessage) {
        sMessage = addText(sMessage, "Введи /start, чтобы начать игру");
        sMessage = addText(sMessage,"Введи /help, чтобы получить справку об игре");
        sMessage = addText(sMessage,"Введи /exit, чтобы закончить игру");
        sMessage = addText(sMessage,"Введи /heroes, чтобы вывести список своих полученных персонажей");
        sMessage = addText(sMessage,"Это тест бот, в котором нужно выбирать тест и отвечать на вопросы, а в конце ты узнаешь, кто ты из персонажей");
        return sMessage;
    }

}
