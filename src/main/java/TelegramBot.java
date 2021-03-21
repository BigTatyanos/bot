import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

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

    }
}
