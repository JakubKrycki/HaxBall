package org.example.game.objects.players;

import lombok.Data;
import org.example.game.objects.Object;

import java.awt.*;

@Data
public abstract class Player extends Object {

    private int points = 0;
    public Player(float x, float y, Color color){
        super(x, y, 0.1f, color);
    }

    public int checkX(int x){
        if(x < 0)
            return 0;
        else if(x > 1290 - getR())
            return (int)(1290-getR());
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
            setXCoord(1180);
        }
        if(y < 450 && y > 245 && x < 46){
            setXCoord(46);
        }
    }

}
