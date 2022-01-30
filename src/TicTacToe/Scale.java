package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Scale {

    private JFrame frame;

    Rectangle ratioFrame;

    private int maxX;
    private int maxY;

    public Scale(int maxX, int maxY, JFrame frame){ // scales the paint methods to the size
        this.frame = frame;
        this.maxX  = maxX;
        this.maxY  = maxY;
        this.ratioFrame = new Rectangle(0,0,maxX,maxY);
    }

    public void newRatioFrame(){ // finds the new Frame to scale up to
        //Resizing happens here
        double ratio = (double) maxX / maxY ;

        ratioFrame.setX(0);
        ratioFrame.setY(0);

        if(frame.getSize().width > frame.getSize().height * ratio) {
            // ratio = (double) maxY / maxX

            ratioFrame.setH(frame.getSize().height);
            ratioFrame.setW((int)( frame.getSize().height * ratio));

            //Recentering happens here
            ratioFrame.setX( (int) ( ( frame.getSize().width - (int)( frame.getSize().height * ratio ) ) / 2 ) );
        } else {
            ratio = (double) maxY / maxX;

            ratioFrame.setW(frame.getSize().width);
            ratioFrame.setH((int)(frame.getSize().width * ratio));

            //Recentering happens here
            ratioFrame.setY( (int) ( ( (frame.getSize().height) - (int)( frame.getSize().width * ratio ) ) / 2 ) );
        }
    }
    // this scale the given (x,h,w,y) values onto the scaled frame
    public int scaleToFrameX(int x){
        return (int) ( ( (double) ratioFrame.getW() / maxX * x ) + ratioFrame.getX() );
    }

    public int scaleToFrameW(int w){
        return (int) ( (double) ratioFrame.getW() / maxX * w );
    }

    public int scaleToFrameY(int y){
        return (int) ( ( (double) ratioFrame.getH() / maxY * y ) + ratioFrame.getY() );
    }

    public int scaleToFrameH(int h){
        return (int) ( (double) ratioFrame.getH() / maxY * h );
    }

    /////////////////////////////////

    public double scaleDownX(double x){// scales down and x point
        return ( ( (double) ( x - ratioFrame.getX() ) * maxX ) / ratioFrame.getW());
    }

    public double scaleDownY(double y){// scales down and x point
        return ( ( (double) ( y - ratioFrame.getY() ) * maxY ) / ratioFrame.getH());
    }

    /////////////////////////////////
    // this scale the given (x,h,w,y) values onto the scaled frame
    // but with doubles
    public int scaleToFrameX(double x){
        return (int) ( ( (double) ratioFrame.getW() / maxX * x ) + ratioFrame.getX() );
    }

    public int scaleToFrameW(double w){
        return (int) ( (double) ratioFrame.getW() / maxX * w );
    }

    public int scaleToFrameY(double y){
        return (int) ( ( (double) ratioFrame.getH() / maxY * y ) + ratioFrame.getY() );
    }

    public int scaleToFrameH(double h){
        return (int) ( (double) ratioFrame.getH() / maxY * h );
    }

    /////////////////////////////////

    public double getScale(){
        return (double) ratioFrame.getH() / maxY;
    }// not used

    public Rectangle getRatioFrame(){
        return ratioFrame;
    }// not used

    public void ScaleImage(BufferedImage image){// not used

    }

}