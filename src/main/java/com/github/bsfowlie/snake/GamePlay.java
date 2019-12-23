package com.github.bsfowlie.snake;

import static java.util.Objects.requireNonNull;

import java.awt.*;
import javax.swing.*;

public class GamePlay extends JPanel {

    public static final int WIDTH = 905;

    public static final int HEIGHT = 700;

    private ImageIcon titleImage;

    public GamePlay() {

        final ClassLoader classLoader = getClass().getClassLoader();
        titleImage = new ImageIcon(requireNonNull(classLoader.getResource("snaketitle.jpg")));

    }

    @Override
    public void paint(final Graphics g) {

        // fill background
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // draw title bar
        g.setColor(Color.WHITE);
        g.drawRect(24, 10, 851, 55);
        titleImage.paintIcon(this, g, 25, 11);

        // draw game area
        g.drawRect(24, 74, 851, 577);
        g.setColor(Color.BLACK);
        g.fillRect(25, 75, 850, 575);
    }
}
