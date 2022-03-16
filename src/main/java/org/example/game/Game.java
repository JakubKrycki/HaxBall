package org.example.game;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.game.objects.ball.Ball;
import org.example.game.objects.players.Player;

@Data
@RequiredArgsConstructor
public class Game {

    private Player player1;
    private Player player2;
    private Ball ball;
    private String score = "0 : 0";


}
