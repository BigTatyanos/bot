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

    public void printHello() {
        String message = "Привет, это ТестБот.";
        printText(message);
    }
    public String getPlayerName() {
        printText("Как Вас зовут?");
        String playerName = getUserInput();
        getHelp();
        return playerName;
    }

    private void getHelp() {
        printText("Введи /start, чтобы начать игру");
        printText("Введи /help, чтобы получить справку об игре");
        printText("Введи /exit, чтобы закончить игру");
        printText("Введи /heroes, чтобы вывести список своих полученных персонажей");
        printText("Это тест бот, в котором нужно выбирать тест и отвечать на вопросы, а в конце ты узнаешь, кто ты из персонажей");
    }

    public boolean HandleUserCommand(Game game){
        String userInput = getUserInput();
        if(userInput.equals("/help")){
            getHelp();
            return false;
        }
        else if (userInput.equals("/start")) {
            printText("Какой тест хочешь пройти? Вот список тестов:");
            for(String testName: game.getTestsNames())
                printText(testName);
            return false;
        }
        else if (userInput.equals("/exit")) {
            printText("Пока, до скорой встречи!");
            return true;
        }
        else if (userInput.equals("/heroes")) {
            Set<Hero> heroes = game.getPlayer().getHeroes();
            if(heroes.isEmpty()){
                printText("Ты ещё не прошёл ни одного теста");
            }
            else {
                for(Hero hero: heroes){
                    printText(hero.getName());
                }
            }
            return false;
        }
        else if(game.getTestsNames().contains(userInput)) {
            game.setCurrentTest(game.findTest(userInput));
            return false;
        }
        else{
            printText("Некорректная строка");
            return false;
        }
    }
}
