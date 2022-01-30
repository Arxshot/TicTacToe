package TicTacToe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class TicTacToe {

    private ArrayList<Player> players;

    private int w;
    private int h;

    private char[][] grid;

    private int lenghtToWin;
    private char turn;
    private char winState;

    private ArrayList<Board> boardHistory;

    private ArrayList<Button> buttons;
    private Button[][] buttonArr;

    private HashMap objects;

    private ArrayList<ActionListener> listener;

    public TicTacToe(HashMap objects,int w, int h, int lenghtToWin){
        this.w = w;
        this.h = h;

        this.boardHistory = new ArrayList<Board>();

        this.winState = ' ';

        this.turn = 'x';

        objects.put('x',"Sprites" + File.separatorChar + "X.png");
        objects.put('o',"Sprites" + File.separatorChar + "O.png");
        objects.put('*',"Sprites" + File.separatorChar + "Thing.png");


        this.lenghtToWin = lenghtToWin; // must be shorter then weight and height

        this.objects = objects;

        this.listener = new ArrayList<ActionListener>();

        this.players = new ArrayList<Player>();

        this.buttons = new ArrayList<Button>();
    }

    public void clearBorad(){ // clears everything form the past setings
        boardHistory.clear();
        buttons.clear();
        listener.clear();
        this.buttonArr = new Button[getW()][getH()];
        this.grid = new char[w][h];
        for(int x = 0; x < w;x++){
            for(int y = 0; y < h;y++){
                grid[x][y] = ' ';
            }
        }
    }

    public void createBorad(){ //creates a new board to play on
        clearBorad();

        Main main = (Main) objects.get(Main.class);
        int outterBorder = 10; // for the spaceing between buttons
        double innerBorder = 0.05;// for the spaceing between buttons

        int width = (int) (main.getMaxX() - (outterBorder * 2)); // gets the width of each Button
        int height = (int) (main.getMaxY() - (outterBorder * 2));// gets the heiht of each Button

        int wigthPerButton = width / getW();// gets the width of each Button
        int heightPerButton = height /getH();// gets the heiht of each Button
        double wigthInnerBorder = wigthPerButton * (innerBorder);
        double heightInnerBorder = heightPerButton * (innerBorder);

        ScreenMouseListener mouseListener = (ScreenMouseListener) objects.get(ScreenMouseListener.class);

        for(int x = 0; x < getW(); x++){
            for(int y = 0; y < getH(); y++){
                int thisX = x;
                int thisY = y;

                double rectX = (x * wigthPerButton) + outterBorder + wigthInnerBorder;
                double rectY = (y * heightPerButton) + outterBorder + heightInnerBorder;
                double rectW = wigthPerButton - (wigthInnerBorder * 2);
                double rectH = heightPerButton - (heightInnerBorder * 2);

                Button button = new Button(objects,new Rectangle(rectX,rectY,rectW,rectH),"Sprites" + File.separatorChar + "Thing.png" );//creates button

                addActionListener(new ActionListener() {//creates and adds ActionListener
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Scale scale = (Scale) objects.get(Scale.class);

                        mouseListener.getPoint().getY();
                        if (button.collision(mouseListener.getPoint())) {//if pressed
                            if (objects.containsKey("humanPlayerTurnPoint")) {//add "humanPlayerTurnPoint" , Point(thisX, thisY) to the HashMap
                                objects.replace("humanPlayerTurnPoint", new Point(thisX, thisY));
                            } else {
                                objects.put("humanPlayerTurnPoint", new Point(thisX, thisY));
                            }
                        }
                    }
                });

                buttons.add(button);
                buttonArr[x][y] = button;
            }
        }
    }

    public void turn(Point point){ //not used
        add(point, getTurn());
        switchTurn();
    }

    public void switchTurn(){
        if(turn == 'x'){
            setTurn('o');
        } else if(turn == 'o'){
            setTurn('x');
        }
    }

    public void setTurnX(){
        setTurn('x');
    }//doesn't work

    public void setTurnO(){
        setTurn('o');
    }//doesn't work

    public void setTurn(char c){
        turn = c;
    }//doesn't work

    public boolean add(int x, int y , char c){// adds an x or o to a point of the borad
        if(grid[x][y] == ' ') {
            char[][] arr = new char[getW()][getH()];
            for (int ix = 0; ix < getW(); ix++) {
                for (int iy = 0; iy < getH(); iy++) {
                    arr[ix][iy] = grid[ix][iy];
                }
            }
            boardHistory.add(new Board(arr, c, new Point(x, y)));
            grid[x][y] = c;
            return true;
        }
        return false;
    }

    public boolean add(Point p, char c){
        return add((int)p.getX(),(int)p.getY(),c);
    }

    public boolean addX(int x, int y){
        return add(x,y,'x');
    }

    public boolean addX(Point p){
        return add((int)p.getX(),(int)p.getY(),'x');
    }

    public boolean addO(int x, int y){
        return add(x,y,'o');
    }

    public boolean addO(Point p){
        return add((int)p.getX(),(int)p.getY(),'o');
    }

    public boolean checkDraw(){ //checks for a draw
        for(int x = 0; x < getW(); x++){
            for(int y = 0; y < getH(); y++){
                if(grid[x][y] == ' '){
                    return false;
                }
            }
        }
        winState = '*';
        return true;
    }

    public boolean checkDraw(char[][] grid){// checks for a draw with a given grid
        for(int x = 0; x < getW(); x++){
            for(int y = 0; y < getH(); y++){
                if(grid[x][y] == ' '){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkWin(){ // checks for a win
        if(checkWin('o') || checkWin('x')){
            return true;
        } else if(checkDraw()){
            return true;
        }

        return false;
    }

    public boolean checkWin(char c){// checks for a win with a given char
        if(checkWinHorizontal(c,grid) || checkWinVertical(c,grid) || checkWinTL_BR(c,grid) || checkWinTR_BL(c,grid)){
            winState = c;
            return true;
        }
        winState = ' ';
        return false;
    }

    public boolean checkWin(char c, char[][] grid) { // check for a win with a given char and grid
//        if(boardHistory.size() < (lenghtToWin*2)-2) {
//            int numX = 0;
//            int numY = 0;
//            for (int x = 0; x < getW(); x++) {
//                for (int y = 0; y < getH(); y++) {
//                    if (grid[x][y] == 'x') {
//                        numX += 1;
//                        if (numX == lenghtToWin) {
//                            return false;
//                        }
//                    } else if (grid[x][y] == 'o') {
//                        numY += 1;
//                        if (numY == lenghtToWin) {
//                            return false;
//                        }
//                    }
//                }
//            }
//        }
        if (checkWinHorizontal(c, grid) || checkWinVertical(c, grid) || checkWinTL_BR(c, grid) || checkWinTR_BL(c, grid)) {
            return true;
        }
        return false;
    }


    public boolean checkWinVertical(char c, char[][] grid){ // checks for a Vertical for a given char and grid
        for(int x = 0; x < getW() - (lenghtToWin-1); x++){
            for(int y = 0; y < getH(); y++){
                for(int i = 0; i < lenghtToWin; i++) {
                    if(grid[x + i][y] == c){
                        if(i + 1 == lenghtToWin){ // lenght to win is change able
                            return true;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkWinHorizontal(char c, char[][] grid){// checks for a Horizontal for a given char and grid
        for(int x = 0; x < getW(); x++){
            for(int y = 0; y < getH() - (lenghtToWin-1); y++){
                for(int i = 0; i < lenghtToWin; i++) {
                    if(grid[x][y + i] == c){
                        if(i + 1 == lenghtToWin){
                            return true;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkWinTL_BR(char c, char[][] grid){// checks for a top left to bottom Right for a given char and grid
        for(int x = 0; x < getW() - (lenghtToWin-1); x++){
            for(int y = 0; y < getH() - (lenghtToWin-1); y++){
                for(int i = 0; i < lenghtToWin; i++) {
                    if(grid[x + i][y + i] == c){
                        if(i + 1 == lenghtToWin){
                            return true;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkWinTR_BL(char c, char[][] grid){// checks for a top Right to bottom left for a given char and grid
        for(int x = 0; x < getW() - (lenghtToWin-1); x++){
            for(int y = 0; y < getH() - (lenghtToWin-1); y++){
                for(int i = 0; i < lenghtToWin; i++) {
                    if(grid[x + i][y + (lenghtToWin-1) - i] == c){
                        if(i + 1 == lenghtToWin){
                            return true;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        return false;
    }

    ///////////////////////////////////////////// this block of code is for the TrueAi but it didn't work /////////////////////////////////////////////

    public String flipHorizontal(String string){
        String flip = "";

        for(int y = h - 1; y >= 0; y--){
            for(int x = 0; x < w; x++){
                flip += string.charAt(x + (y * h));
            }
        }
        return flip;
    }

    public int[][] flipHorizontal(int[][] arr){
        int [][] newarr = arr;
        int y2 = 0;
        for(int y = h-1; y >= 0; y--){
            int x2 = 0;
            for(int x = 0; x < w; x++){
                newarr[x2][y2] = arr[x][y];
                x2++;
            }
            y2++;
        }
        return newarr;
    }

    public String flipVertical(String string){
        String flip = "";

        for(int y = 0; y < h; y++){
            for(int x = w; x > 0; x--){
                flip += string.charAt(x + (y * h));
            }
        }
        return flip;
    }

    public int[][] flipVertical(int[][] arr){
        int [][] newarr = arr;
        int y2 = 0;
        for(int y = 0; y < h; y++){
            int x2 = 0;
            for(int x = w; x > 0; x--){
                newarr[x2][y2] = arr[x][y];
                x2++;
            }
            y2++;
        }
        return newarr;
    }


    public String rotateCW(String string){
        String rotate = "";

        for(int x = 0; x < w; x++){
            for(int y = h -1; y >= 0; y--){
                rotate += string.charAt(x + (y * h));
            }
        }
        return rotate;
    }

    public int[][] rotateCW(int[][] arr){
        int [][] newarr = arr;
        int y2 = 0;
        for(int x = 0; x < w; x++){
            int x2 = 0;
            for(int y = h; y < 0; y--){
                newarr[x2][y2] = arr[x][y];
                x2++;
            }
            y2++;
        }
        return newarr;
    }

    public String rotateCCW(String string){
        String rotate = "";

        for(int x = w; x > 0; x--){
            for(int y = 0; y < h; y++){
                rotate += string.charAt(x * w + y);
            }
        }
        return rotate;
    }

    public int[][] rotateCCW(int[][] arr){
        int [][] newarr = arr;
        int y2 = 0;
        for(int x = w-1; x >= 0; x--){
            int x2 = 0;
            for(int y = 0; y < h; y++){
                newarr[x2][y2] = arr[x][y];
                x2++;
            }
            y2++;
        }
        return newarr;
    }

    //////////////////////////////////////////////////////////////
    
    public int getW(){
        return w;
    }

    public int getH(){
        return h;
    }

    public char getTurn(){
        return turn;
    }

    public char[][] getGrid(){
        return grid;
    }

    public void setW(int w){
        this.w = w;
    }

    public void setH(int h){
        this.h = h;
    }

    public void setLenghtToWin(int lenghtToWin){
        this.lenghtToWin = lenghtToWin;
    }

    public int getLenghtToWin(){
        return lenghtToWin;
    }

    public char getWinState(){
        return winState;
    }

    public ArrayList<Player> getPlayers(){
        return players;
    }

    public void addPlayers(Player player){
        players.add(player);
    }

    public Button[][] getButtonArr() {
        return buttonArr;
    }

    public ArrayList getBoardHistory() {
        return boardHistory;
    }

    public void addActionListener(ActionListener c) {
        listener.add(c);
    }

    public void addActionListener(ArrayList<ActionListener> c) {
        listener.addAll(c);
    }

    public void event(){
        ActionEvent event = new ActionEvent(this,0,"click");

        for(ActionListener l : listener){
            l.actionPerformed(event);
        }
    }

    public void paint(Graphics g){
        for(Button button : buttons) {
            button.paint(g);
        }

    }



}