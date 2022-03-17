package org.example.game.objects;

import lombok.Data;

import java.awt.*;

@Data
public abstract class Object {

    private float xCoord;
    private float yCoord;
    private float xVector;
    private float yVector;
    private float r = 30;
    private float deltaT;
    private Color color;

    public Object(){}
    public Object(float x, float y, float deltaT, Color color) {
        xCoord = x;
        yCoord = y;
        xVector = 0;
        yVector = 0;
        this.deltaT = deltaT;
        this.color = color;
    }
    public int checkX(int x){
        if(x < 0){
            return 0;
        }
        else if(x > 1280 - 2*r){
            return (int)(1280-2*r);
        }
        return x;
    }
    public int checkY(int y){
        if(y < 0 + r){
            return (int)(r);
        }
        else if(y > 720 - 2*r){
            return (int)(720-2*r);
        }
        return y;
    }
    public void move(){
        xCoord += xVector;
        xCoord = checkX((int)xCoord);
        yCoord += yVector;
        yCoord = checkY((int)yCoord);

    }

    public boolean checkHit(Object object){
        return distance(object) <= r+object.getR();
    }

    public double distance(Object object){
        return Math.sqrt(Math.pow(xCoord- object.xCoord, 2)+Math.pow(yCoord- object.yCoord, 2));
    }


}
