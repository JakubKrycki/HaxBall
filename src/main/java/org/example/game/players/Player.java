package org.example.game.players;


import lombok.Data;

import java.awt.*;

@Data
public abstract class Player {

    private float xCoord;
    private float yCoord;
    private float xVector;
    private float yVector;
    private float deltaT = 0.1f;
    private Color color;

    public Player(float x, float y, Color color){
        xCoord = x;
        yCoord = y;
        xVector = 0;
        yVector = 0;
        this.color = color;
    }

    public void move(){
        xCoord += xVector;
        yCoord += yVector;
    }


}
