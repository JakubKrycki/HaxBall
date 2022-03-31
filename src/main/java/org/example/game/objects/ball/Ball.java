package org.example.game.objects.ball;

import org.example.game.objects.Object;
import org.example.game.objects.players.Player;

import java.awt.*;

public class Ball extends Object {

    public Ball(float x, float y, Color color){
        super(x, y, 0.1f, color);
        this.setR(20);
    }

    public void playerCollision(Player player){
        float deltaY = player.getYCoord() - getYCoord();
        float deltaX = getXCoord() - player.getXCoord();
        float xSign = getSignOfNumber(deltaX);// 1 or -1 or 0
        float ySign = getSignOfNumber(-deltaY);// 1 or -1 or 0
        if(deltaX != 0) {
            float a = (deltaY) / (deltaX);//a = (y2-y1)/(x2-x1)
            setXVector((float)(xSign*Math.sqrt(30/(Math.pow(a, 2)+1))));
            setYVector(ySign*Math.abs(getXVector()*a));
        }else{
            setXVector(0);
            setYVector(-(float)Math.sqrt(30)*ySign);
        }
    }

    public void checkCollisions(Player player1, Player player2, Rectangle boundaries, Rectangle goalBoundaries){
        if(isPlayerHit(player1))
            playerCollision(player1);
        if(isPlayerHit(player2))
            playerCollision(player2);

        checkWallHit(boundaries, goalBoundaries);
    }

    public boolean isPlayerHit(Object object){
        return distance(object) <= getR()+object.getR();
    }

    public void checkWallHit(Rectangle boundaries, Rectangle goalBoundaries){
        //up or down
        if(getYCoord() - getR() <= boundaries.y || getYCoord() + getR() >= boundaries.y + boundaries.height){
            setYVector(-1*getYVector());
        }

        if(getXCoord() - getR() <= boundaries.x || getXCoord() + getR() >= boundaries.x + boundaries.width){
            if(getYCoord() <= goalBoundaries.y || getYCoord() >= goalBoundaries.y + goalBoundaries.height)
                setXVector(-1*getXVector());
            else if((getYCoord() > goalBoundaries.y && getYCoord() - getR() < goalBoundaries.y) || (getYCoord() < goalBoundaries.y + goalBoundaries.height && getYCoord() + getR() > goalBoundaries.y + goalBoundaries.height)){
                float temp = getXVector();
                setXVector(getYVector());
                setYVector(-1*temp);
            }
        }
    }
    public String checkIfGoal(){
        if(getYCoord() > 280 && getYCoord() < 480 && getXCoord() <= 70){
            return "blue";
        }
        else if(getYCoord() > 280 && getYCoord() < 480 && getXCoord() >= 1210){
            return "red";
        }
        return null;
    }
    @Override
    public void move(Player player1, Player player2, Rectangle boundaries, Rectangle goalBoundaries) {
        checkCollisions(player1, player2, boundaries, goalBoundaries);

        setXCoord(getXCoord() + getXVector());
        setYCoord(getYCoord() + getYVector());
        setXVector((float)0.95*getXVector());
        setYVector((float)0.95*getYVector());
    }
}
