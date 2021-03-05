package appBot;

import java.util.*;

public class ConsoleCommunication {
    public static String getUserInput(){
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        in.close();
        return input;
    }

    public static void printText(String text) {
        System.out.print(text);
    }

    public static void printHello(){
        String message = "Привет, это ТестБот.\n";
        System.out.print(message);
    }

    public static void getTestsNames(){
        for(Test test : Game.tests)
        {
            System.out.println(test.getName());
        }
    }

    public static void HandleUserCommand(){
        String userInput = getUserInput();
        if(userInput.equals("/help")){
            printHello();
        }
        else if(userInput.equals("/start"))
        {
            getTestsNames();
        }
    }

    public static String HandleUserInput(){
        String userInput = getUserInput();
        return userInput + userInput;
    }
}
