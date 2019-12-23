package com.github.bsfowlie.snake;

import static java.util.Objects.requireNonNull;

import java.awt.*;
import javax.swing.*;

public class Gameplay extends JPanel {

    private ImageIcon titleImage;

    public Gameplay() {

        final ClassLoader classLoader = getClass().getClassLoader();
        titleImage = new ImageIcon(requireNonNull(classLoader.getResource("snaketitle.jpg")));

    }

    @Override
    public void paint(final Graphics g) {

        // draw title image borders
        g.setColor(Color.WHITE);
        g.drawRect(24, 10, 851, 55);

        // draw the title image
        titleImage.paintIcon(this, g, 25, 11 );

        // draw the border for game play
        g.setColor(Color.WHITE);
        g.drawRect(24, 74, 851, 577 );

        // draw background for game play
        g.setColor(Color.BLACK);
        g.fillRect(25, 75, 850, 575 );

    }
}
