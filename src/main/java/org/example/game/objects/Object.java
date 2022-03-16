package org.example.game.objects;

import lombok.Data;

import java.awt.*;

@Data
public abstract class Object {

    private float xCoord = 0;
    private float yCoord = 0;
    private float xVector = 0;
    private float yVector = 0;
    private float r = 15;
    private float deltaT = 0.1f;
    private Color color = Color.BLACK;

    public Object(){}

    public Object(float x, float y, float deltaT, Color color) {
        xCoord = x;
        yCoord = y;
        xVector = 0;
        yVector = 0;
        this.deltaT = deltaT;
        this.color = color;
    }

    public void move(){
        xCoord += xVector;
        yCoord += yVector;
    }

    public boolean checkHit(Object object){
        return distance(object) <= r+object.getR();
    }

    public double distance(Object object){
        return Math.sqrt(Math.pow(xCoord- object.xCoord, 2)+Math.pow(yCoord- object.yCoord, 2));
    }


}
