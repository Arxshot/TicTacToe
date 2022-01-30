package TicTacToe;

import java.util.HashMap;

public class HumanPlayer extends Player {

    private TicTacToe game;
    private char selfChar;

    private HashMap objects;
    private Main main;

    public HumanPlayer(HashMap objects, TicTacToe game, char selfChar){ // creates humanplayer
        super(objects,game,selfChar);

        this.objects = objects;

        this.game = game;
        this.selfChar = selfChar;

        this.main = (Main)objects.get(Main.class);
    }

    public void turn(){
        objects.remove("humanPlayerTurnPoint");
        while(true) {
            while (!objects.containsKey("humanPlayerTurnPoint")) { // waits for a button to be clicked
                main.fullUpdate(); // refreshs screen
                if(2 != (int) objects.get("GameState")){
                    return;// if the game state changes return
                }
                try {
                    Thread.sleep(16);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }

            try {
                Thread.sleep(2);// just incase the objects.get("humanPlayerTurnPoint") is not fully there
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            Point p = (Point) objects.get("humanPlayerTurnPoint");

            if (game.add(p, selfChar)) { // puts the X or O on the board if not countine
                game.getButtonArr()[(int)p.getX()][(int)p.getY()].setPath((String) objects.get(selfChar));// changes the spirte to the needed one
                objects.remove("humanPlayerTurnPoint"); // removes this object
                return;
            }

            objects.remove("humanPlayerTurnPoint"); // removes this object
        }
    }

}