package com.packages;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddles extends Rectangle {
    //id for player 1; id 2 for player 2
    int id;
    //yVelocity controls how fast the ball moves up or down.
    int yVelocity;
    //An integer to control the speed.
    int speed =10;
    Paddles(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){
//Allows to assign some of these arguments for us.
        super(x,y,PADDLE_WIDTH, PADDLE_HEIGHT);
        this.id=id;

    }
    //method for keypressed and takes 1 argument.  Also for keypressed, make sure to pass in a value such as a number or a variable that contains a number.
    public void keyPressed(KeyEvent e) {
        switch(id) {
            case 1:
                // The if statement is going to execute the block of code
                if(e.getKeyCode()==KeyEvent.VK_W) {
                    // This will move up on the y-axis
                    setYDirection(-speed);
                    move();
                }
                // The if statement is going to execute the block of code
                if(e.getKeyCode()==KeyEvent.VK_S) {
                    // This will move up on the x-axis
                    setYDirection(speed);
                    move();
                }
                break;
            case 2:
                // The if statement is going to execute the block of code
                if(e.getKeyCode()==KeyEvent.VK_UP) {
                    // This will move up on the y-axis
                    setYDirection(-speed);
                    move();
                }
                // The if statement is going to execute the block of code
                if(e.getKeyCode()==KeyEvent.VK_DOWN) {
                    // This will move up on the x-axis
                    setYDirection(speed);
                    move();
                }
                break;
        }
    }
    public void keyReleased(KeyEvent e) {
//This method examines the contents of our ID variable.  Case is either 1 or 2
        switch(id) {
            case 1:
// The if statement is going to execute the block of code
                if(e.getKeyCode()==KeyEvent.VK_W) {
// This will move up on the y-axis, and must be 0 because otherwise it will just keep moving.
                    setYDirection(0);
                    move();
                }
// The if statement is going to execute the block of code
                if(e.getKeyCode()==KeyEvent.VK_S) {
// This will move up on the x-axis
                    setYDirection(0);
                    move();
                }
                break;
            case 2:
// The if statement is going to execute the block of code
                if(e.getKeyCode()==KeyEvent.VK_UP) {
// This will move up on the y-axis
                    setYDirection(0);
                    move();
                }
// The if statement is going to execute the block of code
                if(e.getKeyCode()==KeyEvent.VK_DOWN) {
// This will move up on the x-axis
                    setYDirection(0);
                    move();
                }
                break;
        }
    }
    //This will take one argument, which is the int y direction.  Plus, since the paddles are only moving up or down, we don't need to set the x direction.
    public void setYDirection(int yDirection) {
        yVelocity = yDirection;

    }
    public void move() {
//We are taking the y positioning and equal it to y plus yVelocity.
        y= y + yVelocity;
    }
    public void draw(Graphics g) {
//Draws rectangles
//Assigns the color blue to player/id 1.
        if (id==1)
            g.setColor(Color.red);
        else
            g.setColor(Color.blue);
//Fills the rectangles with the assigned color.
        g.fillRect(x, y, width, height);


    }
}