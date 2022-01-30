package TicTacToe;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class MiniMaxAI extends Player {

    private TicTacToe game;
    private char selfChar;
    private char otherChar;

    private HashMap objects;
    private HashMap tables;
    private Main main;

    public MiniMaxAI(HashMap objects, TicTacToe game, char selfChar) {
        super(objects, game, selfChar);

        this.objects = objects;
        this.tables = new HashMap<Table,Integer>();

        this.game = game;
        this.selfChar = selfChar;
        if (selfChar == 'x') {
            this.otherChar = 'o';
        } else {
            this.otherChar = 'x';
        }

        this.main = (Main) objects.get(Main.class);
    }

    @Override
    public void turn() { // a method to place the players thing turn
        ArrayList<Point> bestPoints = new ArrayList<Point>();
        main.fullUpdate();

        int maxX = game.getW();
        int maxY = game.getH();


        int bestPoint = Integer.MIN_VALUE ;


        if(game.getBoardHistory().size() < 1) { // only does if the borad on the first tunr
            maxX = (int) (game.getW() / 2 + 1); // changes this values to only MinMax the top left corner
            maxY = (int) (game.getH() / 2 + 1);
        }

        for (int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                if (game.getGrid()[x][y] == ' ') { // check to see if something is all realy there
                    game.getGrid()[x][y] = selfChar; // puts a posable move there
                    int num = bestMove(game.getGrid(),(int)(game.getLenghtToWin()*2.5),Integer.MIN_VALUE,Integer.MAX_VALUE, otherChar); // goes into recorshion
                    PointNumbered point = new PointNumbered(x, y, num); // puts thati  into a class randomise the it's turn
                    game.getGrid()[x][y] = ' ';// roves the posable move there

                    if (bestPoint < point.getNum()) { // check to see if it have the hightest score or if there is a new one
                        bestPoints.removeAll(bestPoints);// reomves the point and adds new ones
                        bestPoints.add(new Point(point.getX(),point.getY()));
                        bestPoint = point.getNum();
                    } else if (bestPoint == point.getNum()) {
                        bestPoints.add(new Point(point.getX(),point.getY()));// adds to the possable points
                    }
                }
            }
        }

        int i = (int) (Math.random() * bestPoints.size()); // get a random point based on the given points
        Point p = bestPoints.get(i);
        game.add(p,selfChar);
        game.getButtonArr()[(int)p.getX()][(int)p.getY()].setPath((String) objects.get(selfChar));
    }



    public int bestMove(char[][] grid,int depth, int alpha, int beta, char turn){
        if(depth == 0){// depth checker
            return 0;
        }
            if (turn == 'x') {// win checker to give a a score
                if (game.checkWin('o', grid)) {
                    if (turn == selfChar) {
                        return -1; // lossing
                    } else {
                        return 1;// winning
                    }
                }
            } else {
                if (game.checkWin('x', grid)) {
                    if (turn == selfChar) {
                        return -1; // lossing
                    } else {
                        return 1;// winning
                    }
                }
            }
        if(game.checkDraw(grid)){
            return 0;  //draw
        }
        if(turn == selfChar){// maximiseing player
            int bestScore = -2;//Integer.MIN_VALUE;
            for(int x = 0; x < game.getW(); x++){
                for(int y = 0; y < game.getH(); y++){// looks for the best score
                    if(grid[x][y] == ' '){
                        grid[x][y] = selfChar;
                        int score = bestMove(grid,depth-1,alpha,beta,otherChar);
                        grid[x][y] = ' ';
                        bestScore = Integer.max(score,bestScore);

                        alpha = Integer.max(score,alpha);
                        if(beta <= alpha){ // to end useless recorshion
                            return bestScore;
                        }
                    }
                }
            }
            return bestScore;
        } else if(turn == otherChar){// minimiseing player
            int bestScore = 2;//Integer.MAX_VALUE;
            for(int x = 0; x < game.getW(); x++){
                for(int y = 0; y < game.getH(); y++){// looks for the lowest score
                    if(grid[x][y] == ' '){
                        grid[x][y] = otherChar;
                        int score = bestMove(grid,depth-1,alpha,beta,selfChar);
                        grid[x][y] = ' ';
                        bestScore = Integer.min(score,bestScore);

                        beta = Integer.min(score,beta);
                        if(beta <= alpha){ // to end useless recorshion
                            return bestScore;
                        }
                    }
                }
            }
            return bestScore;
        }
        return 0;
    }

}
class Table{// makes a table for save in to a hashmap for meavosashion
    private  char[][] grid;
    private char selfChar;
    private char turn;
    public Table(char[][] grid,char selfChar, char turn){
        this.grid = grid;
        this.selfChar = selfChar;
        this.turn = turn;
    }

}