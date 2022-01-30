package TicTacToe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class OpeningScreen {

    private HashMap objects;

    private ArrayList<Button> buttons;
    private ArrayList<ActionListener> listeners;


    public OpeningScreen(HashMap objects){
        this.objects = objects;
        this.buttons = new ArrayList<Button>();
        this.listeners = new ArrayList<ActionListener>();
        Button goButton = new Button(objects,new Rectangle(25,50,50,25),"Sprites" + File.separatorChar + "Play.png");
        listeners.add(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ScreenMouseListener mouseListener = (ScreenMouseListener)objects.get(ScreenMouseListener.class);
                        if(goButton.collision(mouseListener.getPoint())) {
                            objects.replace("GameState", (int) 1);
                        }
                    }
                }
        );
        buttons.add(goButton);
    }

    public void run(){
        Main main = (Main) objects.get(Main.class);
        ScreenMouseListener screenMouseListener = (ScreenMouseListener) objects.get(ScreenMouseListener.class);

        screenMouseListener.resetAllListeners();

        screenMouseListener.addActionListenerToMousePressed(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event(e);
            }// the actionlisteners into the screenMouseListener
        });


        while(0 == (int) objects.get("GameState")){

            main.fullUpdate(); // refreshs screen

            try
            {
                Thread.sleep(16);
            }
            catch (InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }

        }

    }

    public void event(ActionEvent e){ //performs all actions
            for(ActionListener l : listeners){
                l.actionPerformed(e);
            }
    }

    public void paint(Graphics2D g){// paints to screen
        for (Object obj : buttons) {
            Button button = (Button) obj;
            button.paint(g);
        }
    }

}
