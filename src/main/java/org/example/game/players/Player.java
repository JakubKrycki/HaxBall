package org.example.game.players;

import org.example.game.Object;

import java.awt.*;

public abstract class Player extends Object {

    public Player(float x, float y, Color color){
        super(x, y, 0.1f, color);
    }



}
