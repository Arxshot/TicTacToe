package TicTacToe;

public class PointNumbered extends Point implements Comparable {
    private int num;
    public PointNumbered (double x, double y, int num ){// a Point class holding a number is seen in MiniMax.turn();
        super(x,y);
        this.num = num;
    }

    public Point getPoint(){
        return new Point(x,y);
    }

    public int getNum() {
        return num;
    }

    @Override
    public int compareTo(Object o) { // used for sorting an Arraylist of this object
        // Ascending order
        return (int)this.getNum()-((PointNumbered)o).getNum();
    }
}
