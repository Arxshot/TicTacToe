package TicTacToe;

public class Board {

    private char[][] borad;
    private char turn;
    private Point newPoint;

    public Board(char[][] borad, char turn, Point newPoint){ // a class to store the history of the game for a TrueAI
        this.borad = borad;
        this.turn = turn;
        this.newPoint = newPoint;
    }

    public char[][] getBorad() {
        return borad;
    }

    public char getTurn() {
        return turn;
    }

    public Point getNewPoint() {
        return newPoint;
    }

}
