package org.example.game.objects.players;

import org.example.game.objects.Object;

import java.awt.*;

public abstract class Player extends Object {

    public Player(float x, float y, Color color){
        super(x, y, 0.1f, color);
    }



}
