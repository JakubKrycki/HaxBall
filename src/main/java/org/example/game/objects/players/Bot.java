package org.example.game.objects.players;

import org.example.game.objects.ball.Ball;

import java.awt.*;

public class Bot extends Player{

    public Bot(float x, float y, char side, Color color) {
        super(x, y, side, color);
    }

    @Override
    public void move(Player active, Player enemy, Ball ball, Rectangle boundaries, Rectangle goalBoundaries){
        //toBall(ball); // bot1
        checkDistance(ball, enemy, goalBoundaries); // bot2
        if(enemy.distanceFromPoint(getXCoord()+getXVector(), getYCoord()) >= getR() + enemy.getR())
            setXCoord(checkX((int) (getXCoord() + getXVector())));
        if(enemy.distanceFromPoint(getXCoord(), getYCoord()+getYVector()) >= getR() + enemy.getR())
            setYCoord(checkY((int) (getYCoord() + getYVector())));
        checkNotHittingSoccerGoal((int)getXCoord(),(int)getYCoord());
    }

    public void toBall(Ball ball){
        float x = getSignOfNumber(ball.getXCoord() - getXCoord());
        float y = getSignOfNumber(ball.getYCoord() - getYCoord());
        setXVector(x*5);
        setYVector(y*5);
    }

    public void checkDistance(Ball ball, Player enemy, Rectangle goalBoundaries){
        if(distance(ball) > enemy.distance(ball) && (distance(ball) > 2*getR() || Math.abs(distance(ball)-enemy.distance(ball)) < getR())){
            //to goal line
            float xS = (getSide() == 'L')? goalBoundaries.x : goalBoundaries.x+goalBoundaries.width;
            float yS = (float)(goalBoundaries.y+0.5*goalBoundaries.height);
            double dist = distanceFromPoint(xS, yS);
            if(Math.abs(getYCoord()-(yS+ball.getYCoord())/2) >= 5){
                setXVector(0);
                if(getYCoord() > (yS+ball.getYCoord())/2)
                    setYVector(-5);
                else
                    setYVector(5);
            }else{
                if(getSide() == 'L' && xS + getR() >= getXCoord())
                    setXVector(0);
                else if(getSide() == 'R' && xS - getR() <= getXCoord())
                    setXVector(0);
                else{
                    float xV = xS - getXCoord();
                    setXVector(xV * 5 / (float)dist);
                }
                float yV = yS - getYCoord();
                setYVector(yV * 5 / (float)dist);
            }
        }else
            toBall(ball);
    }

}
