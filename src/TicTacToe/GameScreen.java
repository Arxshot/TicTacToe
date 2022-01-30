package TicTacToe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class GameScreen {

    private HashMap objects;
    private ArrayList<Button> buttons;
    private Main main;
    private ScreenMouseListener mouseListener;
    private TicTacToe borad;

    private ArrayList<ActionListener> listeners;

    public GameScreen(HashMap objects){ // creates the class
        this.objects = objects;
        this.buttons = new ArrayList<Button>(); // holds all the buttons to be painted

        this.main = (Main)objects.get(Main.class);
        this.listeners = new ArrayList<ActionListener>();
        this.mouseListener = (ScreenMouseListener)objects.get(ScreenMouseListener.class); // a mouselistener to listen for mouse press
        // adds
    }

    public void createEvents(){ // creates all the events for the buttons and adds them to the actionListener arraylist
        Button returnButton = new Button(objects,new Rectangle(2,88,10,10),"Sprites" + File.separatorChar + "return.png");
        listeners.add(new ActionListener() { // creating a ActionListener
            @Override
            public void actionPerformed(ActionEvent e) {
                if(returnButton.collision(mouseListener.getPoint())){
                    objects.replace("GameState",1); // to ruturn to the setup screen
                }
            }
        });
        buttons.add(returnButton); // adding the button to buttons to be painted later

        Button turnButton = new Button(objects,new Rectangle(88,88,10,10),null);
        buttons.add(turnButton);

        listeners.add(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borad.event();
            }
        });

        listeners.add(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borad.event();
            }
        });
    }

    public void run(){
        this.borad = (TicTacToe)objects.get(TicTacToe.class); // gets the board and put it into an object in this class
        borad.createBorad();
        listeners.clear();
        createEvents();
        mouseListener.resetAllListeners();
        mouseListener.addActionListenerToMousePressed(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event();
            }
        });

        int i = 0;
        while(2 == (int) objects.get("GameState")){

            if( i >= borad.getPlayers().size()){ // switch betwenn all the players (only allows 2 but can be more is code is changed)
                i = 0;
            }

            Player player = borad.getPlayers().get(i);

            Button button = buttons.get(1);
            button.setPath((String) objects.get(player.getTurn()));

            player.turn();


            if(borad.checkWin('o')){
                objects.replace("GameState",3); // this moves the code to the right state
                gameEnd();
            } else if(borad.checkWin('x')){
                objects.replace("GameState",3);// this moves the code to the right state
                gameEnd();
            } else if(borad.checkDraw()){
                objects.replace("GameState",3);// this moves the code to the right state
                gameEnd();
            }

            main.fullUpdate();
            i++;
            try
            {
                Thread.sleep(16); // gives a FPS of 60
            }
            catch (InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
        }

    }
    public void gameEnd() { // is a TrueAi is one of the player it will save the Data for furture games
        for (int i = 1; i < borad.getPlayers().size(); i++) {
            Object player = borad.getPlayers().get(i);
            if (player instanceof TrueAI) {
                TrueAI ai = (TrueAI) player;
                ai.saveRoundData();
                ai.writeDataToFile();
                break;
            }
        }
    }
    public void addActionListener(ActionListener c){
        listeners.add(c);
    }

    public ArrayList<ActionListener> getActionListener(){
        return listeners;
    }

    public void event(){
        ActionEvent event = new ActionEvent(this,0,"click");

        for(ActionListener l : listeners){
            l.actionPerformed(event);
        }
    }

    public void paint(Graphics2D g) { // paints the game to the screen with the other buttons
        TicTacToe borad = (TicTacToe) objects.get(TicTacToe.class);
        borad.paint(g);
        for(Button b : buttons){
            b.paint(g);
        }
    }

}
