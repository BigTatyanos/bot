//package appBot;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();

        ConsoleCommunication cc = new ConsoleCommunication();
        cc.printHello();
        String playerName = cc.getPlayerName();
        Player player = new Player(playerName);
        game.setPlayer(player);

        while(true){
            boolean response = cc.HandleUserCommand(game);
            if (response)
                break;
            else if(game.getCurrentTest() != null){
                game.playTest(cc);
            }
        }
        cc.closeScanner();
    }
}
