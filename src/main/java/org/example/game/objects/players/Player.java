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
}
