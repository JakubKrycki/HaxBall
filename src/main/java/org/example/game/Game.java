package org.example.game;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.game.objects.ball.Ball;
import org.example.game.objects.players.AlivePlayer;
import org.example.game.objects.players.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

@Data
@RequiredArgsConstructor
public class Game implements ActionListener,KeyListener {
    private Player playerRed;
    private Player playerBlue;
    private Ball ball;
    private String score = "0 : 0";
    private int SCREEN_W;
    private int SCREEN_H;
    private Window window;
    private Timer timer;
    public Game(int w, int h, Window win){
        SCREEN_W = w;
        SCREEN_H = h;
        window = win;
    }
    public void startTheGame(){
        playerRed = new AlivePlayer((float)SCREEN_W/4 - 215,(float)SCREEN_H/2 - 120, Color.RED);
        playerBlue = new AlivePlayer((float)SCREEN_W/2 + 480,(float)SCREEN_H/2 - 120, Color.BLUE);
        ball = new Ball((float)SCREEN_W/2 - 30 ,(float)SCREEN_H/2 - 120,Color.WHITE);
        timer = new Timer(10,this);
        timer.start();
        window.addKeyListener(this);
        window.setFocusable(true);
        window.setFocusTraversalKeysEnabled(false);

    }

    public void changeScore(){
        String s = Integer.toString(playerBlue.getPoints());
        score = s;
        score += " : ";
        s = Integer.toString(playerRed.getPoints());
        score += s;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int button = e.getKeyCode();
        if(button == KeyEvent.VK_DOWN){
            playerBlue.setYVector(5);
        }
        if(button == KeyEvent.VK_UP){
            playerBlue.setYVector(-5 );
        }
        if(button == KeyEvent.VK_RIGHT){
            playerBlue.setXVector(5);
        }
        if(button == KeyEvent.VK_LEFT){
            playerBlue.setXVector(-5);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int button = e.getKeyCode();
        if(button == KeyEvent.VK_DOWN){
            playerBlue.setYVector(0);
        }
        if(button == KeyEvent.VK_UP){
            playerBlue.setYVector(0);
        }
        if(button == KeyEvent.VK_RIGHT){
            playerBlue.setXVector(0);
        }
        if(button == KeyEvent.VK_LEFT){
            playerBlue.setXVector(0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        playerBlue.move();
        changeScore();
        window.repaint();
    }
}
