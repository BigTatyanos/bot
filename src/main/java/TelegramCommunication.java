import org.telegram.telegrambots.meta.api.objects.Message;

public class TelegramCommunication {

    public static String getPlayerName(Message rMessage) {
        return rMessage.getFrom().getFirstName();
    }

    public static String getPlayerId(Message rMessage) {
        return rMessage.getFrom().getId().toString();
    }

}
