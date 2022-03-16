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

    public Window(int screen_w, int screen_h){
        //TODO game
        super("HaxBall by Kopecki Piotr & Krycki Jakub");
        SCREEN_W = screen_w;
        SCREEN_H = screen_h;
        drawMap();
    }
    public void drawMap(){
        JLabel background;
        String path;
        try {
            path = new java.io.File(".").getCanonicalPath(); //sciezka do folderu HaxBall
            background = new JLabel();
            background.setIcon(new ImageIcon(path+"\\src\\main\\java\\org\\example\\assets\\pitch_resized.png"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        setSize(SCREEN_W, SCREEN_H);
        getContentPane().setBackground(Color.BLACK.darker().darker());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(background, BorderLayout.NORTH);

        setResizable(false);
        setVisible(true);
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.setFont(new Font("Verdana", Font.BOLD, 60));
        int stringWidth = g.getFontMetrics().stringWidth(game.getScore());
        g.setColor(Color.white);
        g.drawString(game.getScore(),SCREEN_W/2 - stringWidth/2,(int)(720 + (SCREEN_H - 720)/4 + BOX_WH*0.75));

        g.setColor(Color.RED);
        g.fillRect(SCREEN_W/2 - BOX_WH - stringWidth/2 - 10,720 + (SCREEN_H - 720)/4,75,75);
        g.setColor(Color.BLUE);
        g.fillRect(SCREEN_W/2 + stringWidth/2 + 10,720 + (SCREEN_H - 720)/4,75,75);


    }
}
