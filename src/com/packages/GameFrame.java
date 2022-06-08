package com.packages;

import javax.swing.*;
import java.awt.*;
//Treats the game as a JFrame
public class GameFrame extends JFrame{
    //Instance and create a panel for the game
    GameFrame(){
//Instantiates within constructor
        GamePanel panel = new GamePanel();
//Game panel to frame
        this.add(panel);
//Title for the game
        this.setTitle("Pong Game");
//Does not allow the game to be resized
        this.setResizable(false);
//Adds background color
        this.setBackground(Color.black);
//When we exit the game, the game closes.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//Causes this Window to be sized to fit the preferred size and layouts of its subcomponents.
        this.pack();
        this.setVisible(true);
//Moves the window to appear in the middle of the screen
        this.setLocationRelativeTo(null);


    }

}
