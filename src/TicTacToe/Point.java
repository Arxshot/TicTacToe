package TicTacToe;


public class Point { // it is just a home made Point class
    double x , y;

    public Point(){
        this.x = 0;
        this.y = 0;
    }

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public void moveX(double x1){
        setX(x += x1);
    }

    public void moveY(double y1){
        setY(y += y1);
    }

    public void move(double x,double y){
        moveX(x);
        moveY(y);
    }
}
