package TicTacToe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class WinScreen {

    private HashMap objects;
    private Main main;
    private ScreenMouseListener mouseListener;
    private TicTacToe borad;
    private ArrayList<Button> buttons;

    private ArrayList<ActionListener> listeners;

    public WinScreen(HashMap objects) {
        this.objects = objects;
        this.buttons = new ArrayList<Button>();

        this.main = (Main) objects.get(Main.class);
        this.listeners = new ArrayList<ActionListener>();
        this.mouseListener = (ScreenMouseListener) objects.get(ScreenMouseListener.class);
        this.borad = (TicTacToe)objects.get(TicTacToe.class);

        Button returnButton = new Button(objects, new Rectangle(2, 88, 10, 10), "Sprites" + File.separatorChar + "return.png"); // if click will go back to the game setup screen
        listeners.add(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (returnButton.collision(mouseListener.getPoint())) {
                    objects.replace("GameState",1);
                }
            }
        });
        buttons.add(returnButton);

        Button winnerButton = new Button(objects, new Rectangle(10, 10, 80, 80), null);// if click will go back to the game screen with new board
        listeners.add(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (winnerButton.collision(mouseListener.getPoint())) {
                    objects.replace("GameState",2);
                }
            }
        });
        buttons.add(winnerButton);
    }

    public void run() {
        this.borad = (TicTacToe) objects.get(TicTacToe.class);
        mouseListener.resetAllListeners();// resets the mouseListener ActionListener
        mouseListener.addActionListenerToMousePressed(new ActionListener() {// adds new ActionListener to the mouseListener
            @Override
            public void actionPerformed(ActionEvent e) {
                event();
            }
        });
        buttons.get(1).setPath((String) objects.get(borad.getWinState()));

        while (3 == (int) objects.get("GameState")) {

            main.fullUpdate();
            try {
                Thread.sleep(16);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

    }

    public void addActionListener(ActionListener c) {
        listeners.add(c);
    }

    public ArrayList<ActionListener> getActionListener() {
        return listeners;
    }

    public void event() { // performs all events
        ActionEvent event = new ActionEvent(this, 0, "click");

        for (ActionListener l : listeners) {
            l.actionPerformed(event);
        }
    }

    public void paint(Graphics2D g) { // paint to screen
        TicTacToe borad = (TicTacToe) objects.get(TicTacToe.class);
        borad.paint(g);
        for (Button b : buttons) {
            b.paint(g);
        }
    }

}
