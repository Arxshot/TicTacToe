package TicTacToe;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Button extends Rectangle{

    private String path;
    private BufferedImage image;

    private HashMap objects;

    private ArrayList<ActionListener> listeners = new ArrayList<ActionListener>();

    public Button(HashMap objects, Rectangle rect, String path){ // creates the clsss
        super(rect.getX(),rect.getY(),rect.getW(),rect.getH()); // super to extend the Rectangle class
        this.objects = objects;
        this.path = path;
        try{
            this.image = ImageIO.read(new File(path));
        }catch (Exception e){
            this.image = null;
        }
    }

    public void setPath(String path){ // set a new path for the image and Find the new image
        this.path = path;
        try{
            this.image = ImageIO.read(new File(path));
        }catch (Exception e){
            this.image = null;
        }
    }

    ///////////////////////

    public String getPath(){
        return path;
    }

    ///////////////////////

    public void addActionListener(ActionListener c){
        listeners.add(c);
    }

    public ArrayList<ActionListener> getActionListener(){
        return listeners;
    }

    public void event(){ // Performs all action in action listener
        ActionEvent event = new ActionEvent(this,0,"click");

        for(ActionListener l : listeners){
            l.actionPerformed(event);
        }
    }

    public void paint(Graphics g){ // a Paint method to paint to the screen
        Scale scale = (Scale)objects.get(Scale.class);

        int x = scale.scaleToFrameX(getX());
        int y = scale.scaleToFrameY(getY());
        int w = scale.scaleToFrameW(getW());
        int h = scale.scaleToFrameH(getH());
        g.drawImage(image,x,y,w,h,null );
    }

}
