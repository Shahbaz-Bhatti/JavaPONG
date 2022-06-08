package com.packages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
//Treats the game as a JPanel
public class GamePanel extends JPanel implements Runnable {
    //Just in case we have multiple instances of the game panel, they would all share just one variable and not their own individual game.  The final keyword prohibits us from accidentally modifying our game and run just a tad bit faster.
    static final int GAME_WIDTH = 1000;
    // Creates th;e dimensions to fit the size of a real ping bong table, but can also adjust to a different height if inputed accordingly.
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
    // Creates a dimension for screen size that contains the game width and height.
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
    //Shapes ball diameter; the higher the number, the bigger the diameter.	There is also the dimensions for the paddle height and width.
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    //These are a few instances that are not initialized using a thread method.
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddles paddles1;
    Paddles paddles2;
    Ball ball;
    Score score;

    GamePanel(){
//New panels need new paddles to appear on the game board.  Same with new ball.  This connects to the integer method in score.java.
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH,GAME_HEIGHT);
//Helps to focus the game in order to read key presses and key strokes.
        this.setFocusable(true);
//Action listener for the key presses to respond to.
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);

        //This is a thread which also implements the runnable interface
        gameThread = new Thread(this);
        gameThread.start();

    }
    //Resets level/game
    public void newBall() {
//finishes instantiating random random
        random = new Random();
//We are going to pass our ball coordinates to our ball constructor, and the method we are using will make the ball appear in the middle of the x-axis and y-axis
        ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);
    }
    public void newPaddles() {
//finish instantiating instances of paddle 1 and paddle 2
//First, equal paddle 1 to a new paddle and set the position to where we want it on the x axis

        paddles1 = new Paddles(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
        paddles2 = new Paddles(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);

    }
    //creates method to paint
    public void paint(Graphics g) {
//Creates image that has the width and height of our game panel.
        image = createImage(getWidth(), getHeight());
//Graphics would be done by the image.getGraphics() method.
        graphics = image.getGraphics();
//Draws components and image for the game graphics.
        draw(graphics);
        g.drawImage(image, 0,0,this);

    }
    //creates method to draw
    public void draw(Graphics g) {
//Draws the rectangles for paddle 1 and 2.
        paddles1.draw(g);
        paddles2.draw(g);
        ball.draw(g);
//Draws score
        score.draw(g);
    }
    //creates method to move within iterations of our game
    public void move(){
//allows the paddles and ball to move much more smoothly
        paddles1.move();
        paddles2.move();
        ball.move();
    }
    //Method for collision of objects in game
    public void checkCollision() {

        //bounce ball of top & bottom window edges
        if(ball.y <=0) {
            ball.setYDirection(-ball.yVelocity);
        }
        if (ball.y >= GAME_HEIGHT-BALL_DIAMETER) {
            ball.setYDirection(-ball.yVelocity);
        }
        //bounces ball off paddles; since this ball class is a subclass of the rectangle superclass it inherits all the methods and properties of this rectangle including the intersects method.
        if(ball.intersects(paddles1)) {
//Turns negative into positive values.
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; //optional for more difficulty
//If yVelocity is negative, it is going upwards, so we're just going to increase the upwards velocity.
            if(ball.yVelocity>0)
                ball.yVelocity++;//optional for more difficulty but also increases speed
            else
                ball.yVelocity--;
//Set x and y direction with these new values
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        if(ball.intersects(paddles2)) {
            //Turns negative into positive values.
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; //optional for more difficulty
            //If yVelocity is negative, it is going upwards, so we're just going to increase the upwards velocity.
            if(ball.yVelocity>0)
                ball.yVelocity++;//optional for more difficulty but also increases speed
            else
                ball.yVelocity--;
            //Set x and y direction with these new values
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        //stops paddles at window edges
        if(paddles1.y<=0)
            paddles1.y=0;
        //if you are moving down
        if(paddles1.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
            paddles1.y = GAME_HEIGHT-PADDLE_HEIGHT;

        if(paddles2.y<=0)
            paddles2.y=0;
        //if you are moving down
        if(paddles2.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
            paddles2.y = GAME_HEIGHT-PADDLE_HEIGHT;

        //give a player 1 point and creates new paddles & ball
        //This means the ball touched
        if(ball.x <=0) {
            //Gives player 2 a point
            score.player2++;
            newPaddles();
            newBall();
            System.out.println("Player 2:" +score.player2);
        }
        if(ball.x >= GAME_WIDTH-BALL_DIAMETER) {
            //Gives player 1 a point
            score.player1++;
            newPaddles();
            newBall();
            System.out.println("Player 1 :" +score.player1);
        }
    }
    //Method for run method in the GamePanel class.
    public void run() {
//Basic game loop with a long value called LastTime
        long lastTime = System.nanoTime();
//Double variable to run the game at 60 frames per second and 1 billion nanoseconds.
        double amountofTicks =60.0;
        double ns = 1000000000 / amountofTicks;
//Double variable delta
        double delta = 0;
//You can also have a boolean value called running.
        while(true) {
            long now = System.nanoTime();
            delta += (now -lastTime)/ns;
            lastTime = now;
//if this happens, we'll move all the components and then we are going to check for any collision and then repaint everything and then subtract 1 from Delta
            if(delta >=1) {
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }

    }
    //This is an inner class (action listener) and two more methods(key pressed)
    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e) {
//Action listener and keyPressed should create the certain keystrokes to move the paddles.
            paddles1.keyPressed(e);
            paddles2.keyPressed(e);
        }
        //This method is used when the key is released after pressing to input the command.
        public void keyReleased(KeyEvent e){
            paddles1.keyReleased(e);
            paddles2.keyReleased(e);
        }

    }
}
