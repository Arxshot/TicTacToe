package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

public class ScreenMouseListener implements MouseListener {
    //where initialization occurs:
    //Register for mouse events on blankArea and the panel.
    private Point mousePoint;

    private ArrayList<ActionListener> mousePressedListeners = new ArrayList<ActionListener>(); // Arraylist of actionListeners for mousePressed
    private ArrayList<ActionListener> mouseReleasedListeners = new ArrayList<ActionListener>();// Arraylist of actionListeners for mouseReleased
    private ArrayList<ActionListener> mouseClickedListeners = new ArrayList<ActionListener>(); // Arraylist of actionListeners for mouseClicked

    private Main main;
    private Scale scale;
    private HashMap objects;

    public ScreenMouseListener(HashMap objects) { // creates the object
        this.objects = objects;
        this.main = (Main)objects.get(Main.class);
        this.scale = (Scale) objects.get(Scale.class);
        mousePoint = new Point(-1,-1);
    }

    public void mousePressed(MouseEvent e) {
        mousePoint.setX(scale.scaleDownX(e.getX())); // gets the x ep then scales it down and puts it into a Point object
        mousePoint.setY(scale.scaleDownY(e.getY())); // gets the y ep then scales it down and puts it into a Point object

        mousePressedEvent();// performs all actions within
    }

    public void mouseReleased(MouseEvent e) {
        mousePoint.setX(scale.scaleDownX(e.getX()));// gets the x ep then scales it down and puts it into a Point object
        mousePoint.setY(scale.scaleDownY(e.getY()));// gets the y ep then scales it down and puts it into a Point object

        mouseReleasedEvent();// performs all actions within
    }

    public void mouseEntered(MouseEvent e) {
        mousePoint.setX(scale.scaleDownX(e.getX()));// gets the x ep then scales it down and puts it into a Point object
        mousePoint.setY(scale.scaleDownY(e.getY()));// gets the y ep then scales it down and puts it into a Point object

    }

    public void mouseExited(MouseEvent e) {
        mousePoint.setX(scale.scaleDownX(e.getX()));// gets the x ep then scales it down and puts it into a Point object
        mousePoint.setY(scale.scaleDownY(e.getY()));// gets the y ep then scales it down and puts it into a Point object

    }

    public void mouseClicked(MouseEvent e) {
        mousePoint.setX(scale.scaleDownX(e.getX()));// gets the x ep then scales it down and puts it into a Point object
        mousePoint.setY(scale.scaleDownY(e.getY()));// gets the y ep then scales it down and puts it into a Point object

        mouseClickedEvent();// performs all actions within
    }

    ////////////////////////////////////////////////////

    public Point getPoint(){
        return mousePoint;
    }

    ////////////////////////////////////////////////////

    public void addActionListenerToMousePressed(ActionListener c) {
        mousePressedListeners.add(c);
    }

    public void mousePressedEvent() { // performs all actions
        ActionEvent event = new ActionEvent(this, 0, "click");

        for (ActionListener l : mousePressedListeners) {
            l.actionPerformed(event);
        }
    }

    public void resetMousePressedListeners(){
        mousePressedListeners.clear();
    }

    public void addActionListenerToMouseReleased(ActionListener c) {
        mousePressedListeners.add(c);
    }

    public void mouseReleasedEvent() {// performs all actions
        ActionEvent event = new ActionEvent(this, 0, "click");

        for (ActionListener l : mouseReleasedListeners) {
            l.actionPerformed(event);
        }
    }

    public void resetMouseReleasedListeners(){
        mouseReleasedListeners.clear();
    }

    public void addActionListenerToMouseClicked(ActionListener c) {
        mouseClickedListeners.add(c);
    }

    public void mouseClickedEvent() {// performs all actions
        ActionEvent event = new ActionEvent(this, 0, "click");

        for (ActionListener l : mouseClickedListeners) {
            l.actionPerformed(event);
        }
    }

    public void resetMouseClickedListeners(){
        mousePressedListeners.clear();
    }

    //////////////////////////////////////////////////////////////////////////////

    public void resetAllListeners(){ // clears all Action listener Arraylist to be filled laster
        resetMousePressedListeners();
        resetMouseReleasedListeners();
        resetMouseClickedListeners();
    }

}