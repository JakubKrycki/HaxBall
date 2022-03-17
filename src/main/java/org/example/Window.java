package org.example;

import org.example.game.Game;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Window extends JFrame {
    private Game game;
    private final int SCREEN_W;
    private final int SCREEN_H;
    private final int BOX_WH = 75;
    private Image background;

    public Window(int screen_w, int screen_h){
        super("HaxBall by Kopecki Piotr & Krycki Jakub");
        SCREEN_W = screen_w;
        SCREEN_H = screen_h;
        game = new Game(SCREEN_W,SCREEN_H,this);
        drawMap();
    }
    public void drawMap(){
        String path;
        try {
            path = new java.io.File(".").getCanonicalPath(); //sciezka do folderu HaxBall
            background = new ImageIcon(path+"\\src\\main\\java\\org\\example\\assets\\pitch_resized.png").getImage();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        setSize(SCREEN_W, SCREEN_H);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.startTheGame();
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g){
        Graphics2D g2D = (Graphics2D) g;
       // g2D.setColor(Color.BLACK); Odkomentowac jak ogarne miganie
        //g2D.fillRect(0,0,SCREEN_W,SCREEN_H);
        g2D.drawImage(background,0,0,null);
        g2D.setFont(new Font("Verdana", Font.BOLD, 60));
        int stringWidth = g2D.getFontMetrics().stringWidth(game.getScore());
        g2D.setColor(Color.white);
        g2D.drawString(game.getScore(),SCREEN_W/2 - stringWidth/2,(int)(720 + (SCREEN_H - 720)/4 + BOX_WH*0.75));

        g2D.setColor(Color.RED);
        g2D.fillRect(SCREEN_W/2 - BOX_WH - stringWidth/2 - 10,720 + (SCREEN_H - 720)/4,75,75);
        g2D.setColor(Color.BLUE);
        g2D.fillRect(SCREEN_W/2 + stringWidth/2 + 10,720 + (SCREEN_H - 720)/4,75,75);


        g2D.setColor(game.getPlayerBlue().getColor());
        int x = (int)(game.getPlayerBlue().getXCoord());
        int y = (int)(game.getPlayerBlue().getYCoord());
        int r = (int)(game.getPlayerBlue().getR());
        g2D.fillOval(x,y,r*2,r*2);

        g2D.setColor(game.getPlayerRed().getColor());
        x = (int)(game.getPlayerRed().getXCoord());
        y = (int)(game.getPlayerRed().getYCoord());
        r = (int)(game.getPlayerRed().getR());
        g.fillOval(x,y,r*2,r*2);

        g2D.setColor(game.getBall().getColor());
        x = (int)(game.getBall().getXCoord());
        y = (int)(game.getBall().getYCoord());
        r = (int)(game.getBall().getR());
        g2D.fillOval(x,y,r*2,r*2);
    }
}
