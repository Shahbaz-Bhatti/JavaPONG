package com.packages;

import java.awt.*;
import java.util.Random;

public class Ball extends Rectangle{
    //Random class
    Random random;
    //How fast the ball moves up or down
    int xVelocity;
    int yVelocity;
    //Because it reminds him of initial D.
    int initialSpeed = 4;

    //Constructing the ball parameters
    Ball(int x, int y, int width, int height){
//The super subclass takes in all the ball diameters
        super(x,y,width,height);
//Now we are going to set a random direction in which the ball is going to head.
        random = new Random();
//This will be a local variable for the ball constructor

        int randomXDirection = random.nextInt(2);
        if(randomXDirection == 0)
//This will be -1.
            randomXDirection--;
        setXDirection(randomXDirection*initialSpeed);

        int randomYDirection = random.nextInt(2);
        if(randomYDirection == 0)

            randomYDirection--;
        setYDirection(randomYDirection*initialSpeed);

    }
    //We need a set x direction and a set y direction.  The random argument is because the ball would want to go in any random direction.  Also, if it is 0, it is going to go right, and if 1, it is going to go left
    public void setXDirection(int randomXDirection) {
        xVelocity = randomXDirection;
    }
    public void setYDirection(int randomYDirection) {
        yVelocity = randomYDirection;
    }
    //Move method
    public void move() {
        x += xVelocity;
        y += yVelocity;

    }
    //This draws the ball
    public void draw(Graphics g) {
        g.setColor(Color.white);
//makes the ball circular; and height/width is the same because they are the same diameter (20).
        g.fillOval(x, y, height, width);

    }
}