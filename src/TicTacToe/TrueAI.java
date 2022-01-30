package TicTacToe;

import java.io.*;
import java.util.*;

public class TrueAI extends Player{ // wasn't used don't work so I made a MinMax AI instead

    // o is self
    // x is oppent

    private TicTacToe game;
    private char selfChar;

    private boolean square;

    private ArrayList<String> textFile;

    private String path;
    private OutputStreamWriter writer;
    private BufferedReader reader;

    private HashMap nextMove;

    private HashMap objects;

    public TrueAI(HashMap objects , TicTacToe game, char selfChar) {
        super(objects,game,selfChar);
        this.objects = objects;
        this.textFile = new ArrayList<String>();

        this.game = game;
        this.selfChar = selfChar;
        this.path = "AI" + File.separator + game.getLenghtToWin() + "_" + game.getW() + "_" + game.getH() + ".txt";
        this.nextMove = new HashMap<String,int[][]>();

        if(game.getH() == game.getW()){
            this.square = true;
        } else {
            this.square = false;
        }

        try{

            readDataFromFile();

        } catch (Exception e) {
            try {
                this.writer = new OutputStreamWriter(new FileOutputStream(path, Boolean.parseBoolean("UTF-8")));
                this.reader = new BufferedReader(new FileReader(new File(path)));
            } catch (FileNotFoundException ex){
                System.out.print(ex);
            }
        }
    }

    public void turn(){
        Point p = getNextMove();
        if(p != null) {
            game.add(p, selfChar);
            game.getButtonArr()[(int) p.getX()][(int) p.getY()].setPath((String) objects.get(selfChar));
        }
    }

    public Point getNextMove(){
        String str = "";
        for(int x = 0; x < game.getW();x++){
            for(int y = 0; y < game.getH();y++){
                if(game.getGrid()[x][y] == selfChar){
                    str += 'o';
                } else if(game.getGrid()[x][y] == ' '){
                    str += ' ';
                } else {
                    str += 'x';
                }
            }
        }
        int i = 0;
        int[][] arr = new int[game.getW()][game.getH()];
        end:
        for(; i < 2;i++){
            for(int r = 0; r < 4;r++){
                if(nextMove.containsKey(str)){
                    arr = (int[][]) nextMove.get(str);
                    if(i == 1){
                        arr = game.flipHorizontal(arr);
                    }
                    for(int r2 = 0; r2 < r;r2++){
                        arr = game.rotateCCW(arr);
                    }
                    i = 2;
                    break end;
                }
                str = game.rotateCW(str);
            }
            str = game.flipHorizontal(str);
        }

        ArrayList<PointNumbered> nextMove = new ArrayList<PointNumbered>();

        for(int x = 0; x < game.getW();x++){
            for(int y = 0; y < game.getH();y++){
                if(game.getGrid()[x][y] == ' '){
                    nextMove.add(new PointNumbered(x,y,arr[x][y]));
                }
            }
        }

        Collections.sort(nextMove);

        for(i = 0; i < nextMove.size(); i++) {
            if (nextMove.get(i).getNum() != nextMove.get(0).getNum()) {
                break;
            }
        }
        int random = (int)(Math.random() * i);
        System.out.println(random);
        return (Point) nextMove.get(0).getPoint();
    }

    public void writeDataToFile() {//throws Exception{
        // saveRoundData() method should be used before game is done to save it to a file
        try {
            this.writer = new OutputStreamWriter(new FileOutputStream(path, Boolean.parseBoolean("UTF-8")));
            try{
            Set<String> keySet = (Set<String>) nextMove.keySet();
            String[] stringArr = keySet.toArray(new String[keySet.size()]);

            for (int i = 0; i < stringArr.length; i++) {

                writer.write(stringArr[i] + "\n");
                int[][] arr = (int[][]) nextMove.get(stringArr[i]);
                for (int x = 0; x < game.getW(); x++) {
                    for (int y = 0; y < game.getH(); y++) {
                        writer.write(arr[x][y] + ",");
                    }
                }
                writer.write("\n");
            }
            writer.close();
            } catch (IOException e){
                System.err.println(e);
            }
        }
        catch (FileNotFoundException e){
            System.err.println("writeDataToFile() - Failed" + e);
        }
    }

    public void readDataFromFile() throws Exception{
        this.reader = new BufferedReader(new FileReader(new File(path)));
        int i = -1;
        do{
            i++;
            textFile.add(i,reader.readLine());
        }while (textFile.get(i) != null);

        for(i = 0; i < (textFile.size() / 2); i++){
            int prenum = 0;
            int num = 0;

            String str = textFile.get(( i * 2 )+1);

            int[][] arr = new int[game.getH()][game.getW()];
            for(int y = 0; y < game.getH(); y++){
                for(int x = 0; x < game.getW(); x++){
                    num = str.indexOf(',',prenum);
                    arr[x][y] = Integer.parseInt(str.substring(prenum,num));
                    prenum = num + 1;
                }
            }
            // str = textFile.get( i * 2 );
            // str.substring(str.length());
            // nextMove.put(textFile.get(i * 2),arr);
            nextMove.put(textFile.get(i * 2),arr);
        }
    }

    public void saveRoundData(){
        ArrayList boardHistory = game.getBoardHistory();
        int win  = 2;
        int draw = 6;
        int loss = (game.getH() * game.getW()) * -1;

        win  = boardHistory.size();
        draw *= boardHistory.size();
        loss /= boardHistory.size();

        // ai stuff first
        for(int i = 0; i < boardHistory.size(); i++) { // goes throught the history of the board

            Board board = (Board) boardHistory.get(i);

            if (board.getTurn() == game.getWinState()) { // for the win ////////////////////////////////////////////////////////////

                // turns a char[][] to a String ////////////////////////////
                String str = "";
                for(int x = 0; x < game.getW();x++){

                    for(int y = 0; y < game.getH();y++){

                        if(board.getBorad()[x][y] == board.getTurn()){
                            str += 'o'; // 'o' is for the wining side
                        } else if(board.getBorad()[x][y] == ' '){
                            str += ' '; // ' ' for empty
                        } else {
                            str += 'x'; // 'x' is for losing side
                        }

                    }

                }
                ////////////////////////////////////////////////////////////

                boolean found = false;

                for(int f = 0; f < 2;f++){ // flips the String
                    for(int r = 0; r < 4;r++){ // Rotates the String
                        if(nextMove.containsKey(str)){ // checks to see it has happened before
                            int[][] arr;
                            arr = (int[][]) nextMove.get(str); // saves the number of the previouse games

                            int[][] num = new int[game.getW()][game.getH()];
                            num[(int)board.getNewPoint().getX()][(int)board.getNewPoint().getY()] += win;

                            if(f == 1){
                                game.flipHorizontal(num);
                            }
                            for(int r2 = 0; r2 < r;r2++) {
                                game.rotateCW(num);
                            }

                            for(int y = 0; y < game.getH(); y++) {
                                for (int x = 0; x < game.getW(); x++) {
                                    arr[x][y] += num[x][y];
                                }
                            }
                            nextMove.replace(str,arr);
                            found = true;
                        }
                        str = game.rotateCW(str);
                    }
                    str = game.flipHorizontal(str);
                }
                if( ! found ){
                    int[][] num = new int[game.getW()][game.getH()];
                    num[(int)board.getNewPoint().getX()][(int)board.getNewPoint().getY()] += win;
                    nextMove.put(str, num);
                }

            } else if(game.getWinState() == ' '){ // the draw ////////////////////////////////////////////////////////////

                // turns a char[][] to a String ////////////////////////////
                String str = "";
                for(int x = 0; x < game.getW();x++){

                    for(int y = 0; y < game.getH();y++){

                        if(board.getBorad()[x][y] == 'o'){
                            str += 'o'; // 'o' is for the wining side
                        } else if(board.getBorad()[x][y] == ' '){
                            str += ' '; // ' ' for empty
                        } else {
                            str += 'x'; // 'x' is for losing side
                        }

                    }

                }
                ////////////////////////////////////////////////////////////

                boolean found = false;

                for(int f = 0; f < 2;f++){ // flips the String
                    for(int r = 0; r < 4;r++){ // Rotates the String
                        if(nextMove.containsKey(str)){ // checks to see it has happened before
                            int[][] arr;
                            arr = (int[][]) nextMove.get(str); // saves the number of the previouse games

                            int[][] num = new int[game.getW()][game.getH()];
                            num[(int)board.getNewPoint().getX()][(int)board.getNewPoint().getY()] += draw;

                            if(f == 1){
                                game.flipHorizontal(num);
                            }

                            for(int r2 = 0; r2 < r;r2++) {
                                game.rotateCW(num);
                            }

                            for(int y = 0; y < game.getH(); y++) {
                                for (int x = 0; x < game.getW(); x++) {
                                    arr[x][y] += num[x][y];
                                }
                            }
                            nextMove.replace(str,arr);
                            found = true;
                        }
                        str = game.rotateCW(str);
                    }
                    str = game.flipHorizontal(str);
                }
                if( ! found ){
                    int[][] num = new int[game.getW()][game.getH()];
                    num[(int)board.getNewPoint().getX()][(int)board.getNewPoint().getY()] += draw;
                        nextMove.put(str, num);
                }

                ////////////////////////////////////////////////////////////
                ////////////////////////////////////////////////////////////
                ////////////////////////////////////////////////////////////

                // turns a char[][] to a String ////////////////////////////
                str = "";
                for(int x = 0; x < game.getW();x++){

                    for(int y = 0; y < game.getH();y++){

                        if(board.getBorad()[x][y] == 'x'){
                            str += 'o'; // 'o' is for the wining side
                        } else if(board.getBorad()[x][y] == ' '){
                            str += ' '; // ' ' for empty
                        } else {
                            str += 'x'; // 'x' is for losing side
                        }

                    }

                }
                ////////////////////////////////////////////////////////////

                for(int f = 0; f < 2;f++){ // flips the String
                    for(int r = 0; r < 4;r++){ // Rotates the String
                        if(nextMove.containsKey(str)){ // checks to see it has happened before
                            int[][] arr;
                            arr = (int[][]) nextMove.get(str); // saves the number of the previouse games

                            int[][] num = new int[game.getW()][game.getH()];
                            num[(int)board.getNewPoint().getX()][(int)board.getNewPoint().getY()] += draw;

                            if(f == 1){
                                game.flipHorizontal(num);
                            }
                            for(int r2 = 0; r2 < r;r2++) {
                                game.rotateCW(num);
                            }

                            for(int y = 0; y < game.getH(); y++) {
                                for (int x = 0; x < game.getW(); x++) {
                                    arr[x][y] += num[x][y];
                                }
                            }
                            nextMove.replace(str,arr);
                            found = true;
                        }
                        str = game.rotateCW(str);
                    }
                    str = game.flipHorizontal(str);
                }
                if( ! found ){
                    int[][] num = new int[game.getW()][game.getH()];
                    num[(int)board.getNewPoint().getX()][(int)board.getNewPoint().getY()] += draw;
                    nextMove.put(str,num);
                }

            } else {  // for the loss ////////////////////////////////////////////////////////////

                // turns a char[][] to a String ////////////////////////////
                String str = "";
                for(int x = 0; x < game.getW();x++){

                    for(int y = 0; y < game.getH();y++){

                        if(board.getBorad()[x][y] == board.getTurn()){
                            str += 'o'; // 'o' is for the wining side
                        } else if(board.getBorad()[x][y] == ' '){
                            str += ' '; // ' ' for empty
                        } else {
                            str += 'x'; // 'x' is for losing side
                        }

                    }

                }
                ////////////////////////////////////////////////////////////

                boolean found = false;

                for(int f = 0; f < 2;f++){ // flips the String
                    for(int r = 0; r < 4;r++){ // Rotates the String
                        if(nextMove.containsKey(str)){ // checks to see it has happened before
                            int[][] arr;
                            arr = (int[][]) nextMove.get(str); // saves the number of the previouse games

                            int[][] num = new int[game.getW()][game.getH()];
                            num[(int)board.getNewPoint().getX()][(int)board.getNewPoint().getY()] += loss;

                            if(f == 1){
                                game.flipHorizontal(num);
                            }
                            for(int r2 = 0; r2 < r;r2++) {
                                game.rotateCW(num);
                            }

                            for(int y = 0; y < game.getH(); y++) {
                                for (int x = 0; x < game.getW(); x++) {
                                    arr[x][y] += num[x][y];
                                }
                            }
                            nextMove.replace(str,arr);
                            found = true;
                        }
                        str = game.rotateCW(str);
                    }
                    str = game.flipHorizontal(str);
                }
                if( ! found ){
                    int[][] num = new int[game.getW()][game.getH()];
                    num[(int)board.getNewPoint().getX()][(int)board.getNewPoint().getY()] += loss;
                    nextMove.put(str,num);
                }
            }
        }
    }
}
