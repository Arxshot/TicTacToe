package TicTacToe;

import java.util.HashMap;

public class Player {   // an object to be extended into other class
                        // only is a thing so all the players can be put into a
                        // Arraylist and have turn called on them with out an error

    private TicTacToe game;
    private char selfChar;
    private HashMap objects;

    public Player(HashMap objects,TicTacToe game, char selfChar){
        this.game = game;
        this.selfChar = selfChar;
        this.objects = objects;



    }

    public boolean add(Point point){
        if(game.add(point,selfChar)){
            return true;
        }
        return false;
    }

    public char getTurn(){
        return selfChar;
    }

    public void turn(){ // is used to extend the method



        System.err.print("Player doesn't work");

    }

}
