import java.util.List;

public class TelegramAnswer {
    public List<String> text;
    public boolean hasKeyBoard = true;
    public List<String> buttonText;

    public boolean gameFinished() {
        if (text != null && !text.isEmpty()) {
            return text.get(0).equals("Пока, до скорой встречи!");
        }
        return false;
    }
}
