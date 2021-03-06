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
        System.out.print(text);
    }

    public static void printHello(){
        String message = "Привет, это ТестБот.\n";
        System.out.print(message);
    }

    public void getTestsNames(Game game){
        for(Test test : game.getTests())
        {
            System.out.println(test.getName());
        }
    }

    public void HandleUserCommand(Game game){
        String userInput = getUserInput();
        if(userInput.equals("/help")){
            printHello();
        }
        else if(userInput.equals("/start"))
        {
            getTestsNames(game);
        }
    }

    public String HandleUserInput(){
        String userInput = getUserInput();
        return userInput + userInput;
    }
}
