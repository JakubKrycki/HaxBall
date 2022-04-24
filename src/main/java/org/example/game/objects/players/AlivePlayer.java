package org.example.game.objects.players;

import org.example.game.objects.ball.Ball;

import java.awt.*;

public class AlivePlayer extends Player{
    public AlivePlayer(float x, float y, char side, Color color) {
        super(x, y, side, color);
    }

    @Override
    public void move(Player active, Player enemy, Ball ball, Rectangle boundaries, Rectangle goalBoundaries) {
        if(enemy.distanceFromPoint(getXCoord()+getXVector(), getYCoord()) >= getR() + enemy.getR())
            setXCoord(checkX((int) (getXCoord() + getXVector())));
        if(enemy.distanceFromPoint(getXCoord(), getYCoord()+getYVector()) >= getR() + enemy.getR())
            setYCoord(checkY((int) (getYCoord() + getYVector())));
        checkNotHittingSoccerGoal((int)getXCoord(),(int)getYCoord());
    }
}
