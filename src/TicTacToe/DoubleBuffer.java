package TicTacToe;

import java.awt.*;
import java.util.HashMap;

public class DoubleBuffer { // just to help me learn what and how to use a Double buffer

    private int bufferWidth;
    private int bufferHeight;
    private Image bufferImage;
    private Graphics bufferGraphics;
    private HashMap objects;
    private Main main;


    public DoubleBuffer(HashMap objects){
        this.objects = objects;
        this.main = (Main) objects.get(Main.class);
    }

    public void paint(Graphics g){
        //    checks the buffersize with the current panelsize
        //    or initialises the image with the first paint
        if(bufferWidth != main.getSize().width ||
                bufferHeight != main.getSize().height ||
                bufferImage==null || bufferGraphics==null)
            resetBuffer();

        if(bufferGraphics!=null){
            //this clears the offscreen image, not the onscreen one
            bufferGraphics.clearRect(0,0,bufferWidth,bufferHeight);

            //calls the paintbuffer method with
            //the offscreen graphics as a param
            paintBuffer(bufferGraphics);

            //we finaly paint the offscreen image onto the onscreen image
            g.drawImage(bufferImage,0,0,null);
        }

    }

    private void resetBuffer(){
        // always keep track of the image size
        bufferWidth=main.getSize().width;
        bufferHeight=main.getSize().height;

        //    clean up the previous image
        if(bufferGraphics!=null){
            bufferGraphics.dispose();
            bufferGraphics=null;
        }
        if(bufferImage!=null){
            bufferImage.flush();
            bufferImage=null;
        }
        System.gc();

        //    create the new image with the size of the panel
        bufferImage=main.createImage(bufferWidth,bufferHeight);
        bufferGraphics=bufferImage.getGraphics();
    }

    public void paintBuffer(Graphics g){
        //in classes extended from this one, add something to paint here!
        //always remember, g is the offscreen graphics


    }


}
