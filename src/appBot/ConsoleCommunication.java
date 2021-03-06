package appBot;

import java.util.*;

public class ConsoleCommunication {
    private Scanner in = new Scanner(System.in);
    public String getUserInput(){
        String input = in.nextLine();
        return input;
    }

    public void closeScanner(){
        in.close();
    }

    public static void printText(String text) {
        System.out.println(text);
    }

    public void printHello(){
        String message = "Привет, это ТестБот.";
        printText(message);
    }
    public String getPlayerName(){
        printText("Как Вас зовут?");
        String playerName = getUserInput();
        return playerName;
    }

    public void getTestsNames(Game game){
        for(Test test : game.getTests())
        {
            printText(test.getName());
        }
    }

    public boolean HandleUserCommand(Game game){
        String userInput = getUserInput();
        if(userInput.equals("/help")){
            printHello();
            return false;
        }
        else if(userInput.equals("/start")){
            printText("Какой тест хочешь пройти? Вот список тестов:");
            getTestsNames(game);
            return false;
        }
        else if (userInput.equals("/exit")){
            printText("Пока, до скорой встречи!");
            return true;
        }
        else {
            printText(HandleUserInput(userInput));
            return false;
        }
    }

    public String HandleUserInput(String userInput){
        return userInput + userInput + "\n";
    }
}
