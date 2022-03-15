package org.example;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    static int SCREEN_W = gd.getDisplayMode().getWidth();
    static int SCREEN_H = gd.getDisplayMode().getHeight();

    public static void main(String[] args){

        JFrame frame;
        frame = new JFrame();
        frame.setTitle("HaxBall by Kopecki Piotr & Krycki Jakub");
        frame.setSize(SCREEN_W, SCREEN_H);
        frame.getContentPane().setBackground(Color.GREEN.darker().darker());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

    }

}
