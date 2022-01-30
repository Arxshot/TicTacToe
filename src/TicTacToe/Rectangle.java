package TicTacToe;

import java.awt.*;
import java.util.ArrayList;

public class Rectangle extends Point{// a home made rect class

    //Point p1;n
    double w, h;

    public Rectangle(){
        super(0,0);
        this.w  = 0;
        this.h  = 0;
    }

    public Rectangle(double x, double y, double w, double h){ // creates the class
        super(x,y);
        assert(x >= 0 && y >= 0 && w >= 0 && h >= 0);
        this.w  = w;
        this.h  = h;
    }

    public Rectangle(Point p1, Point p2){// creates the class
        super(p1.getX(),p1.getY());
        this.w  = p2.getX() - p1.getX();
        this.h  = p2.getY() - p1.getY();
    }

    public Rectangle(Point p1, double w, double h){// creates the class
        super(p1.getX(),p1.getY());
        this.w  = w;
        this.h  = h;
    }

    ///////////////

    public boolean collision(Point point){// check for a ccollision with a rectangle and a point
        if(getX() < point.getX() && getY() < point.getY() && (getX() + getW()) > point.getX() && (getY() + getH()) > point.getY()){
            return true;
        }
        return false;
    }

    public boolean collision(Rectangle rect1, Rectangle rect2){// check for a ccollision with a rectangle and another rectangle
        if(lineInTopBottom(rect1,rect2) && lineInLeftRight(rect1,rect2)){
            return true;
        }
        return false;
    }

    public boolean collision(Rectangle rect){// not used
        if(lineInTopBottom(this,rect) && lineInLeftRight(this,rect)){
            return true;
        }
        return false;
    }

    public boolean collision(ArrayList<Rectangle> rectangleArrayList){// not used
        for(int i = 0;i < rectangleArrayList.size(); i++){
            if(collision(this,rectangleArrayList.get(i))) {
                return true;
            }
        }
        return false;
    }

    public Rectangle collisionWith(Rectangle rect){// not used
        if(lineInTopBottom(this,rect) && lineInLeftRight(this,rect)){
            return rect;
        }
        return null;
    }

    public ArrayList<Rectangle> collisionWith(ArrayList<Rectangle> rectangleArrayList){// not used
        ArrayList<Rectangle> collisionsOfRectangle = new ArrayList<Rectangle>();
        for(int i = 0;i < rectangleArrayList.size(); i++){
            if(collision(this,rectangleArrayList.get(i))) {
                collisionsOfRectangle.add(rectangleArrayList.get(i));
            }
        }
        return collisionsOfRectangle;
    }

    // https://www.baeldung.com/java-check-if-two-rectangles-overlap
    // ^^ to be used to make the collision motheds better ^^
    public static boolean lineInTopBottom(Rectangle rect1, Rectangle rect2){// not used
        if          (rect1.getBottom() >= rect2.getBottom()   && rect2.getBottom() >= rect1.getTop()){
            // if( rect.bottom >= self.bottom >= rect.bottom)
            return true;
        } else if   (rect1.getBottom() >= rect2.getTop()      && rect2.getTop()    >= rect1.getTop()){
            // if( rect.bottom >= self.top    >= rect.bottom)
            return true;
        } else if   (rect2.getBottom() >= rect1.getBottom()   && rect1.getBottom() >= rect2.getTop()){
            // if( self.bottom >= rect.bottom >= self.bottom)
            return true;
        } else if   (rect2.getBottom() >= rect1.getTop()      && rect1.getTop()    >= rect2.getTop()){
            // if( self.bottom >= rect.top    >= self.bottom)
            return true;
        }


        return false;
    }

    public boolean lineInLeftRight(Rectangle rect1, Rectangle rect2){// not used
        double rect1Left = rect1.getLeft();
        double rect2Left = rect2.getLeft();
        double rect1Right = rect1.getRight();
        double rect2Right = rect2.getRight();
        if          (rect1Left <= rect2Left     && rect2Left    <= rect1Right){
            // if( rect.left >= self.left  >= rect.right)
            return true;
        } else if   (rect1Left <= rect2Right    && rect2Right   <= rect1Right){
            // if( rect.left >= self.right >= rect.right)
            return true;
        } else if   (rect2Left <= rect1Left     && rect1Left    <= rect2Right){
            // if( self.left >= rect.left  >= self.right)
            return true;
        } else if   (rect2Left <= rect1Right    && rect1Right   <= rect2Right){
            // if( self.left >= rect.right >= self.right)
            return true;
        }
        return false;
    }

    ///////////////

    public Point getP1(){
        return new Point(getX(),getY());
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public double getW(){
        return this.w;
    }

    public double getH(){
        return this.h;
    }

    public Point getP2(){
        return new Point(getX() + this.w,getY() + this.h);
    }

    ////////////////

    public double getTop(){
        return this.getP1().getY();
    }

    public double getRight(){
        return this.getP2().getX();
    }

    public double getLeft(){
        return this.getP1().getX();
    }

    public double getBottom(){
        return this.getP2().getY();
    }

    ////////////////

    public void setP1(Point p1){
        this.setX(p1.getX());
        this.setY(p1.getY());
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setW(double w){
        this.w = w;
    }

    public void setH(double h){
        this.h = h;
    }

    public void setP2(Point p2){
        this.w = p2.getX() - getX();
        this.h = p2.getY() - getY();
    }

    public void setrect(Rectangle rect){
        setX(rect.getX());
        setY(rect.getY());
        setW(rect.getW());
        setH(rect.getH());
    }

    ////////////////
    public void moveX(double x){
        x += getX();
        setX(x);
    }

    public void moveY(double y){
        y += getY();
        setY(y);
    }

    public void move(double x,double y){
        moveX(x);
        moveY(y);
    }
    ////////////////

    public void paint(Graphics g){// not used
        g.setColor(Color.BLUE);
        g.fillRect((int)this.getX(), (int)this.getY(), (int)this.getW(), (int)this.getH());
    }

}