package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

public class Main extends JFrame {

    private JPanel panel;
    private int bufferWidth;
    private int bufferHeight;
    private Image bufferImage;
    private Graphics bufferGraphics;

    private HashMap objects;

    private int maxX;
    private int maxY;

    private Scale scale;
    private ScreenMouseListener screenMouseListener;

    private int gameStatue;

    public Main(){
        this.maxX = 100; // creates the size of the game
        this.maxY = 100;
        this.gameStatue = 0;

        this.scale = new Scale(maxX,maxY, this); // scale class the scale paint the size of the screen



        this.objects = new HashMap<String,Object>(); // holds everthing important(objects/class/spirte/etc.)

        this.objects.put("GameState", gameStatue);// puts the objects into the hashmap
        this.objects.put(Scale.class,scale);
        this.objects.put(Main.class,this);
        this.screenMouseListener = new ScreenMouseListener(objects); // creates this class
        this.objects.put(ScreenMouseListener.class, screenMouseListener);


        setTitle("TicTacToe"); // screen values are set
        setSize(maxX * 5, maxY * 5);
        setLocation(50,50);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(true);
        add(panel);
        addMouseListener(screenMouseListener);






    }

    public static void main(String[] args) throws IOException {
	// write your code here
        Main main = new Main(); // Main class is created

        OpeningScreen openingScreen = new OpeningScreen(main.objects);// Opening screen is created and put into the hashmap
        main.addObject(OpeningScreen.class,openingScreen);

        GameSetupScreen gameSetupScreen = new GameSetupScreen(main.objects);// game set up screen is created and put into the hashmap
        main.addObject(GameSetupScreen.class,gameSetupScreen);

        GameScreen gameScreen = new GameScreen(main.objects);// game screen is created and put into the hashmap
        main.addObject(GameScreen.class,gameScreen);

        WinScreen winScreen = new WinScreen(main.objects);// win screen is created and put into the hashmap
        main.addObject(WinScreen.class,winScreen);

        main.fullUpdate(); // refreshs the screen

        while(true){

            switch ((int) main.getObjects().get("GameState")){ // state machine to move between states
                case 0:
                    openingScreen.run();
                    break;

                case 1:
                    gameSetupScreen.run();
                    break;

                case 2:
                    gameScreen.run();
                    break;

                case 3:
                    winScreen.run();
                    break;


            }
        }
    }
    /*
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        super.paint(g);

        g2.setBackground(Color.BLACK);
        g2.clearRect(0,0,getWidth(),getHeight());

        g2.setColor(Color.cyan);
        g2.fillRect((int)scale.getRatioFrame().getX(),(int)scale.getRatioFrame().getY(),
                (int)scale.getRatioFrame().getW(),(int)scale.getRatioFrame().getH());


        switch ((int)objects.get("GameState")){
            case 0:
                OpeningScreen openingScreen = (OpeningScreen) objects.get(OpeningScreen.class);
                openingScreen.paint(g2);
                break;

            case 1:
                GameSetupScreen gameSetupScreen = (GameSetupScreen) objects.get(GameSetupScreen.class);
                gameSetupScreen.paint(g2);
                break;

            case 2:
                GameScreen gameScreen = (GameScreen) objects.get(GameScreen.class);
                gameScreen.paint(g2);
                break;

        }
    }*/

    public void paint(Graphics g){ // paint an image onto the screen with the double buffer
        //    checks the buffersize with the current panelsize
        //    or initialises the image with the first paint
        if(bufferWidth != getSize().width ||
                bufferHeight != getSize().height ||
                bufferImage==null || bufferGraphics==null)
            resetBuffer();

        if(bufferGraphics!=null){
            //this clears the offscreen image, not the onscreen one
            bufferGraphics.clearRect(0,0,bufferWidth,bufferHeight);

            //calls the paintbuffer method with
            //the offscreen graphics as a param
            paintBuffer(bufferGraphics);

            //we finaly paint the offscreen image onto the onscreen image
            g.drawImage(bufferImage,0,0,this);
        }

    }

    private void resetBuffer(){ // resets the double buff to be repainted on
        // always keep track of the image size
        bufferWidth=getSize().width;
        bufferHeight=getSize().height;

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
        bufferImage=createImage(bufferWidth,bufferHeight);
        bufferGraphics=bufferImage.getGraphics();
    }

    public void paintBuffer(Graphics g){ // creates and paints on to an image to be painted on to screen after
        //in classes extended from this one, add something to paint here!
        //always remember, g is the offscreen graphics
        Graphics2D g2 = (Graphics2D) g;
        super.paint(g);

        g2.setBackground(Color.BLACK);// makes to background black
        g2.clearRect(0,0,getWidth(),getHeight());




        switch ((int)objects.get("GameState")){ // paints the given state's screen
            case 0:
                OpeningScreen openingScreen = (OpeningScreen) objects.get(OpeningScreen.class);
                if(openingScreen != null){
                    openingScreen.paint(g2);// protect against start up
                }
                break;

            case 1:
                GameSetupScreen gameSetupScreen = (GameSetupScreen) objects.get(GameSetupScreen.class);
                gameSetupScreen.paint(g2);
                break;

            case 2:
                GameScreen gameScreen = (GameScreen) objects.get(GameScreen.class);
                gameScreen.paint(g2);
                break;

            case 3:
                WinScreen winScreen = (WinScreen) objects.get(WinScreen.class);
                winScreen.paint(g2);
                break;

        }

    }

    public Scale getScale(){
        return this.scale;
    }

    public int getGameStatue(){
        return gameStatue;
    }

    public HashMap getObjects(){
        return objects;
    }

    public void addObject(Object str,Object object){
        objects.put(str,object);
    }

    public double getMaxX(){
        return maxX;
    }

    public double getMaxY(){
        return maxY;
    }
    /////////////////////////////////////////

    public void fullUpdate(){
        Scale scale = (Scale) objects.get(Scale.class);// get the scale class
        scale.newRatioFrame(); // find the new scale rectangel for scaling
        repaint();// the screen is repainted
    }
}
