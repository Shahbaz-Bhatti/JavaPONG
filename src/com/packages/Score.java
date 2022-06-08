package com.packages;

import java.awt.*;

public class Score extends Rectangle{
    //What we need to declare before the constructors.
    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    int player1;
    int player2;
    //Takes an integer variable.
    Score(int GAME_WIDTH, int GAME_HEIGHT){
        Score.GAME_WIDTH = GAME_WIDTH;
        Score.GAME_HEIGHT = GAME_HEIGHT;
    }
    public void draw(Graphics g) {
//Helps to draw the score on screen with a unique font
        g.setColor(Color.white);
        g.setFont(new Font("Consolas",Font.PLAIN,60));
//Draws line down the middle of the game
        g.drawLine(GAME_WIDTH/2, 0,GAME_WIDTH/2, GAME_HEIGHT);
//Passes in a string for x and y position.  Modulus 10 gives us the remainder of player 1 modulus 10
        g.drawString(String.valueOf(player1/10)+String.valueOf(player1%10), (GAME_WIDTH/2)-85, 50);
        g.drawString(String.valueOf(player2/10)+String.valueOf(player2%10), (GAME_WIDTH/2)+20, 50);
    }
}
