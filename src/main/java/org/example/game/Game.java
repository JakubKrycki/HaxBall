package org.example.game;

import lombok.Data;
import org.example.Window;
import org.example.game.objects.ball.Ball;
import org.example.game.objects.players.AlivePlayer;
import org.example.game.objects.players.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

import static org.example.Main.*;

@Data
public class Game extends JPanel implements ActionListener,KeyListener {
    private Player playerRed;
    private Player playerBlue;
    private Ball ball;
    private String score = "0 : 0";
    private Timer timer;
    private final int BOX_WH = 75;
    private Image backgroundImage;

    public Game(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        String path = "assets/pitch_resized.png";
        backgroundImage = toolkit.getImage(path);
        playerRed = new AlivePlayer((float)SCREEN_W/4 - 215,(float)SCREEN_H/2 - 120, Color.RED);
        playerBlue = new AlivePlayer((float)SCREEN_W/2 + 480,(float)SCREEN_H/2 - 120, Color.BLUE);
        ball = new Ball((float)SCREEN_W/2 - 30 ,(float)SCREEN_H/2 - 120,Color.WHITE);
        addKeyListener(this);
        setFocusable(true);
        requestFocus();
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(0, this);
        timer.start();
    }

    @Override
    public void paint(Graphics g){
        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setColor(Color.BLACK); //Odkomentowac jak ogarne miganie
        g.fillRect(0,0,SCREEN_W,SCREEN_H);//*/
        g.drawImage(backgroundImage,0,0,null);

        printScore(g);
        printObjects(g);
    }

    void printScore(Graphics g){
        g.setFont(new Font("Verdana", Font.BOLD, 60));
        int stringWidth = g.getFontMetrics().stringWidth(getScore());
        g.setColor(Color.white);
        g.drawString(getScore(),SCREEN_W/2 - stringWidth/2,(int)(720 + (SCREEN_H - 720)/4 + BOX_WH*0.75));
        g.setColor(Color.RED);
        g.fillRect(SCREEN_W/2 - BOX_WH - stringWidth/2 - 10,720 + (SCREEN_H - 720)/4,75,75);
        g.setColor(Color.BLUE);
        g.fillRect(SCREEN_W/2 + stringWidth/2 + 10,720 + (SCREEN_H - 720)/4,75,75);
    }

    void printObjects(Graphics g){
        g.setColor(getPlayerBlue().getColor());
        int x = (int)(getPlayerBlue().getXCoord());
        int y = (int)(getPlayerBlue().getYCoord());
        int r = (int)(getPlayerBlue().getR());
        g.fillOval(x,y,r*2,r*2);

        g.setColor(getPlayerRed().getColor());
        x = (int)(getPlayerRed().getXCoord());
        y = (int)(getPlayerRed().getYCoord());
        r = (int)(getPlayerRed().getR());
        g.fillOval(x,y,r*2,r*2);

        g.setColor(getBall().getColor());
        x = (int)(getBall().getXCoord());
        y = (int)(getBall().getYCoord());
        r = (int)(getBall().getR());
        g.fillOval(x,y,r*2,r*2);
    }

    public void changeScore(){
        String s = Integer.toString(playerBlue.getPoints());
        score = s;
        score += " : ";
        s = Integer.toString(playerRed.getPoints());
        score += s;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

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
        repaint();
    }
}
