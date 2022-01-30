package TicTacToe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class GameSetupScreen {

    private HashMap objects;
    private ArrayList<Button> buttons;
    private ArrayList<Button> buttonsSettings;
    private ArrayList<Button> buttonsXPlayer;
    private ArrayList<Button> buttonsOPlayer;
    private ArrayList<Button> numbers;

    private Main main;
    private ScreenMouseListener mouseListener;

    private ArrayList<ActionListener> listeners;

    private Button xPlayerHighligther, oPlayerHighligther, settingsHighlighter;

    private int w,h, lenghtToWin;

    private int settingsState, xPlayerState, oPlayerState;

    private double sizeBorder;

    public GameSetupScreen(HashMap objects){
        this.objects = objects;
        this.buttons = new ArrayList<Button>(); // holds the buttons to be painted

        this.main = (Main)objects.get(Main.class);
        this.listeners = new ArrayList<ActionListener>();
        this.mouseListener = (ScreenMouseListener)objects.get(ScreenMouseListener.class);

        this.w = 3; // the width of the board
        this.h = 3; // the heigh of the board
        this.lenghtToWin = 3; // the lenght of the x's or o's to win the game

        TicTacToe borad = new TicTacToe(objects,w,h, lenghtToWin);
        objects.put(TicTacToe.class,borad);

        this.sizeBorder = 1; // for the highlights and for spacing of the numbers

        this.settingsState = 0;// to know what Settings numbers(in w, int h, int lenghtToWin) should be changed and where the highligher goes
        this.xPlayerState = 0; // to know who the x player is(human or ai)
        this.oPlayerState = 0; // to know who the o player is(human or ai)

        this.buttonsSettings = new ArrayList<Button>(); // holds all the given buttons(Settings,XPlayer...)
        this.buttonsXPlayer = new ArrayList<Button>();
        this.buttonsOPlayer = new ArrayList<Button>();
        this.numbers = new ArrayList<Button>();

        Button returnButton = new Button(objects,new Rectangle(2,88,10,10),"Sprites" + File.separatorChar + "return.png");
        listeners.add(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(returnButton.collision(mouseListener.getPoint())){
                    objects.replace("GameState",0); // back back to the opening screen
                }
            }
        });
        buttons.add(returnButton);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Button startButton = new Button(objects,new Rectangle(35,12.5,30,20),"Sprites" + File.separatorChar + "Play.png");
        listeners.add(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(startButton.collision(mouseListener.getPoint())){
                    objects.replace("GameState",2); // start the game
                }
            }
        });
        buttons.add(startButton);

        Button heightButton = new Button(objects,new Rectangle(35,40,16,8),"Sprites" + File.separatorChar + "H.png");
        listeners.add(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(heightButton.collision(mouseListener.getPoint())){
                    settingsState = 0; // changes which setting is to be changed to the high
                }
            }
        });
        buttons.add(heightButton);
        buttonsSettings.add(heightButton);

        Button widthButton = new Button(objects,new Rectangle(35,50,16,8),"Sprites" + File.separatorChar + "W.png");
        listeners.add(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(widthButton.collision(mouseListener.getPoint())){
                    settingsState = 1;// changes which setting is to be changed to the width
                }
            }
        });
        buttons.add(widthButton);
        buttonsSettings.add(widthButton);

        Button lenghtToWinButton = new Button(objects,new Rectangle(35,60,16,8),"Sprites" + File.separatorChar + "L.png");
        listeners.add(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(lenghtToWinButton.collision(mouseListener.getPoint())){
                    settingsState = 2;// changes which setting is to be changed to length to  win
                }
            }
        });
        buttons.add(lenghtToWinButton);
        buttonsSettings.add(lenghtToWinButton);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Button xButton = new Button(objects,new Rectangle(5,10,25,25),(String)objects.get('x'));
        buttons.add(xButton);

        Button xHumanPlayerButton = new Button(objects,new Rectangle(5,40,25,10),"Sprites" + File.separatorChar + "Player.png");
        listeners.add(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(xHumanPlayerButton.collision(mouseListener.getPoint())){
                    xPlayerState = 0; // for the human player
                }
            }
        });
        buttons.add(xHumanPlayerButton);
        buttonsXPlayer.add(xHumanPlayerButton);

        Button xAIPlayerButton = new Button(objects,new Rectangle(5,55,25,10),"Sprites" + File.separatorChar + "AI.png");
        listeners.add(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(xAIPlayerButton.collision(mouseListener.getPoint())){
                    xPlayerState = 1; // for the AI player
                }
            }
        });
        buttons.add(xAIPlayerButton);
        buttonsXPlayer.add(xAIPlayerButton);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Button oButton = new Button(objects,new Rectangle(70,10,25,25),(String)objects.get('o'));
        buttons.add(oButton);

        Button oHumanPlayerButton = new Button(objects,new Rectangle(70,40,25,10),"Sprites" + File.separatorChar + "Player.png");
        listeners.add(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(oHumanPlayerButton.collision(mouseListener.getPoint())){
                    oPlayerState = 0; // for the human player
                }
            }
        });
        buttons.add(oHumanPlayerButton);
        buttonsOPlayer.add(oHumanPlayerButton);

        Button oAIPlayerButton = new Button(objects,new Rectangle(70,55,25,10),"Sprites" + File.separatorChar + "AI.png");
        listeners.add(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(oAIPlayerButton.collision(mouseListener.getPoint())){
                    oPlayerState = 1; // for the AI player
                }
            }
        });
        buttons.add(oAIPlayerButton);
        buttonsOPlayer.add(oAIPlayerButton);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        objects.put(0,"Sprites" + File.separatorChar + "0.png"); // sets up are the sprites up with the main hashmap
        objects.put(1,"Sprites" + File.separatorChar + "1.png");
        objects.put(2,"Sprites" + File.separatorChar + "2.png");
        objects.put(3,"Sprites" + File.separatorChar + "3.png");
        objects.put(4,"Sprites" + File.separatorChar + "4.png");
        objects.put(5,"Sprites" + File.separatorChar + "5.png");
        objects.put(6,"Sprites" + File.separatorChar + "6.png");
        objects.put(7,"Sprites" + File.separatorChar + "7.png");
        objects.put(8,"Sprites" + File.separatorChar + "8.png");
        objects.put(9,"Sprites" + File.separatorChar + "9.png");
        objects.put("BackSpace","Sprites" + File.separatorChar + "BackSpace.png");
        double innerBorder = 0.5; // to change the spacing of the number buttons
        int maxBoradX = 20; // sets the max width
        int maxBoradY = 20; // sets the max high
        final int[] maxBoradLenghtToWin = {Integer.max(maxBoradX, maxBoradY)};
        ArrayList<Button> numberButton = new ArrayList<Button>();
        for(int i = 0; i < 11; i++){
            if(i == 10){ // creates back space button
                int thisI = i;
                numberButton.add( new Button(objects,new Rectangle(i * (main.getMaxX()/11) + innerBorder,75.0,
                        (main.getMaxX()/11)-(innerBorder * 2),(main.getMaxY()/11)-(innerBorder * 2) ), (String) objects.get("BackSpace")  ));
                listeners.add(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(numberButton.get(thisI).collision(mouseListener.getPoint())){
                            switch (settingsState){
                                case 0:
                                    h /= 10; // removes the 1's place number (backSpace)
                                    maxBoradLenghtToWin[0] = Integer.max(h,w);
                                    if(maxBoradLenghtToWin[0] < lenghtToWin){ // check to see if the lenght to win is to high
                                        lenghtToWin = maxBoradLenghtToWin[0]; // lowers the lenght to win to the game is winnable
                                    }
                                    break;
                                case 1:
                                    w /= 10;// removes the 1's place number (backSpace)
                                    maxBoradLenghtToWin[0] = Integer.max(h,w);
                                    if(maxBoradLenghtToWin[0] < lenghtToWin){// check to see if the lenght to win is to high
                                        lenghtToWin = maxBoradLenghtToWin[0];// lowers the lenght to win to the game is winnable
                                    }
                                    break;
                                case 2:
                                    lenghtToWin /= 10;// removes the 1's place number (backSpace)
                                    if(maxBoradLenghtToWin[0] < lenghtToWin){ // check to see if the lenght to win is to high
                                        lenghtToWin = maxBoradLenghtToWin[0]; // lowers the lenght to win to the game is winnable
                                    }
                                    break;
                                default:
                                    System.err.println(settingsState);
                            }
                        }
                    }
                });
                buttons.add(numberButton.get(thisI));

            } else { // creates all the numbered buttons
                int thisI = i;
                numberButton.add( new Button(objects,new Rectangle(i * (main.getMaxX()/11) + innerBorder,75,(main.getMaxX()/11)-(innerBorder * 2),(main.getMaxY()/11)-(innerBorder * 2)), (String) objects.get(i) ));
                listeners.add(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(numberButton.get(thisI).collision(mouseListener.getPoint())){
                            switch (settingsState){
                                case 0:
                                    h = (h * 10) + thisI; // adds a number on the the end
                                    if(maxBoradY < h){ // makes sure the number isn't to high
                                        h = maxBoradY;
                                    }
                                    maxBoradLenghtToWin[0] = Integer.max(h,w);
                                    break;
                                case 1:
                                    w = (w * 10) + thisI;// adds a number on the the end
                                    if(maxBoradX < w){// makes sure the number isn't to high
                                        w = maxBoradX;
                                    }
                                    maxBoradLenghtToWin[0] = Integer.max(h,w);
                                    break;
                                case 2:
                                    lenghtToWin = (lenghtToWin * 10) + thisI;// adds a number on the the end
                                    maxBoradLenghtToWin[0] = Integer.max(h,w);
                                    if(maxBoradLenghtToWin[0] < lenghtToWin){// makes sure the number isn't to high
                                        lenghtToWin = maxBoradLenghtToWin[0];
                                    }
                                    break;
                                default:
                                    System.err.println(settingsState);
                            }
                            if(maxBoradLenghtToWin[0] < lenghtToWin){// makes sure the number isn't to high
                                lenghtToWin = maxBoradLenghtToWin[0];
                            }
                        }
                    }
                });
                buttons.add(numberButton.get(thisI));

            }
        }
        numbersRest();
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        buttons.add(xPlayerHighligther = new Button(objects,new Rectangle(0,0,0,0),"Sprites" + File.separatorChar + "HighLighter.png")); // creates the highlighters
        buttons.add(oPlayerHighligther = new Button(objects,new Rectangle(0,0,0,0),"Sprites" + File.separatorChar + "HighLighter.png"));
        buttons.add(settingsHighlighter = new Button(objects,new Rectangle(0,0,0,0),"Sprites" + File.separatorChar + "HighLighter.png"));
        highligtherRest();
    }

    public void numbersRest(){ // put the highligther where they need to go
        buttons.removeAll(numbers);// removes all the numbers
        numbers.removeAll(numbers);// removes all the numbers
        for(int i = 0; i < buttonsSettings.size();i++){
            Button b = buttonsSettings.get(i);
            String str = "";
            switch (i){ // gets the needed number for the given buttton
                case 0:
                    str = Integer.toString(h);
                    break;
                case 1:
                    str = Integer.toString(w);
                    break;
                case 2:
                    str = Integer.toString(lenghtToWin);
                    break;
            }
            for(int s = 0; s < str.length(); s++) { // gets the numbers from left to right to be created and painted on to the screen
                String ss = "";
                ss += str.charAt(s);
                int num = Integer.parseInt(ss);
                double x = (b.getX()+b.getW()) + (b.getH()*(s));
                double y = b.getY();
                double h = b.getH();
                double w = b.getH();
                numbers.add(new Button(objects,new Rectangle(x,y,h,w),(String)objects.get(num))); // creats a new number where one needs to go
            }
        }
        buttons.addAll(numbers);// adds all the numbers button's to Buttons
    }

    public void highligtherRest(){ // put the highligther where they need to go
        Button b = buttonsXPlayer.get(xPlayerState);
        xPlayerHighligther.setX(b.getX() - sizeBorder);
        xPlayerHighligther.setY(b.getY() - sizeBorder);
        xPlayerHighligther.setW(b.getW() + (sizeBorder*2));
        xPlayerHighligther.setH(b.getH() + (sizeBorder*2));
        b = buttonsOPlayer.get(oPlayerState);
        oPlayerHighligther.setX(b.getX() - sizeBorder);
        oPlayerHighligther.setY(b.getY() - sizeBorder);
        oPlayerHighligther.setW(b.getW() + (sizeBorder*2));
        oPlayerHighligther.setH(b.getH() + (sizeBorder*2));
        b = buttonsSettings.get(settingsState);
        settingsHighlighter.setX(b.getX() - sizeBorder);
        settingsHighlighter.setY(b.getY() - sizeBorder);
        settingsHighlighter.setW(b.getW() + (sizeBorder*2));
        settingsHighlighter.setH(b.getH() + (sizeBorder*2));
    }

    public void run(){
        mouseListener.resetAllListeners();
        mouseListener.addActionListenerToMousePressed(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event();
            }
        });

        TicTacToe borad = (TicTacToe) objects.get(TicTacToe.class); // get the board from the hashmap

        while(1 == (int) objects.get("GameState")){ // refreash the screen at 60FPS untill the next screen is run()


            highligtherRest();
            numbersRest();
            main.fullUpdate();
            try
            {
                Thread.sleep(16);
            }
            catch (InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
        }

        borad.getPlayers().removeAll(borad.getPlayers()); // romves all players from past games


        switch (xPlayerState){ // adds to x player first
            case 0:
                borad.getPlayers().add(new HumanPlayer(objects,borad,'x'));// human
                break;
            case 1:
                borad.getPlayers().add(new MiniMaxAI(objects,borad,'x')); // AI
                break;

        }

        switch (oPlayerState){// adds to x player first
            case 0:
                borad.getPlayers().add(new HumanPlayer(objects,borad,'o')); // human
                break;
            case 1:
                borad.getPlayers().add(new MiniMaxAI(objects,borad,'o')); //AI
                break;

        }
        borad.setH(h);//set the borad hight to h
        borad.setW(w);//set the borad width to w
        borad.setLenghtToWin(lenghtToWin);//set the borad LenghtToWin to lenghtToWin
    }

    public void addActionListener(ActionListener c){
        listeners.add(c);
    }

    public ArrayList<ActionListener> getActionListener(){
        return listeners;
    }

    public void event(){ // method to perfrom all the action listener
        ActionEvent event = new ActionEvent(this,0,"click");

        for(ActionListener l : listeners){
            l.actionPerformed(event);
        }
    }

    public void paint(Graphics2D g) { // paint this screen
        ArrayList<Button> buttonsClone = new ArrayList<Button>();
        buttonsClone.addAll(buttons);
        for(Button b : buttonsClone){
            b.paint(g);
        }
    }


}
