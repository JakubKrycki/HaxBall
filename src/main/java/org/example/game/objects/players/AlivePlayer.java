package org.example.game.objects.players;

import java.awt.*;

public class AlivePlayer extends Player{
    public AlivePlayer(float x, float y, Color color) {
        super(x, y, color);
    }

    @Override
    public void move(Player player1, Player player2, Rectangle boundaries, Rectangle goalBoundaries) {
        setXCoord(checkX((int) (getXCoord() + getXVector())));
        setYCoord(checkY((int) (getYCoord() + getYVector())));
        checkNotHittingSoccerGoal((int)getXCoord(),(int)getYCoord());
    }
}
