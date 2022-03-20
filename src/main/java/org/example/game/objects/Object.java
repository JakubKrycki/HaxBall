package org.example.game.objects;

import lombok.Data;
import org.example.game.objects.players.Player;

import java.awt.*;

@Data
public abstract class Object {

    private float xCoord = 0;
    private float yCoord = 0;
    private float xVector = 0;
    private float yVector = 0;
    private float r = 30;
    private float deltaT = 0.1f;
    private Color color = Color.BLACK;

    public Object(){}

    public abstract void move(Player player1, Player player2, Rectangle boundaries, Rectangle goalBoundaries);

    public Object(float x, float y, float deltaT, Color color) {
        xCoord = x;
        yCoord = y;
        xVector = 0;
        yVector = 0;
        this.deltaT = deltaT;
        this.color = color;
    }
    public int checkX(int x){
        if(x < 0)
            return 0;
        else if(x > 1290 - r)
            return (int)(1290-r);
        return x;
    }
    public int checkY(int y){
        if(y < 0)
            return 0;
        else if(y > 720)
            return 720;
        return y;
    }
    public void checkNotHittingSoccerGoal(int x, int y){
        if(y < 450 && y > 245 && x > 1180){
            xCoord = 1180;
        }
        if(y < 450 && y > 245 && x < 46){
            xCoord = 46;
        }
    }

    public boolean checkHit(Object object){
        return distance(object) <= r+object.getR();
    }

    public double distance(Object object){
        return Math.sqrt(Math.pow(xCoord- object.xCoord, 2)+Math.pow(yCoord- object.yCoord, 2));
    }

    public float getSignOfNumber(float number){
        return (number != 0)? number/Math.abs(number) : 0;
    }

}
