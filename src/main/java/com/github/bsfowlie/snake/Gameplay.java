package com.github.bsfowlie.snake;

import static java.util.Objects.requireNonNull;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

    private int[] snakeXLength = new int[750];
    private int[] snakeYLength = new int[750];
    private int lengthOfSnake = 3;

    private boolean up = false;
    private boolean left = false;
    private boolean down = false;
    private boolean right = true;
    private boolean moving = false;

    private ImageIcon titleImage;

    private ImageIcon upmouth;
    private ImageIcon leftmouth;
    private ImageIcon downmouth;
    private ImageIcon rightmouth;

    private ImageIcon snakeimage;

    private Timer timer;
    private static final int delay = 100;

    public Gameplay() {

        final ClassLoader classLoader = getClass().getClassLoader();
        titleImage = new ImageIcon(requireNonNull(classLoader.getResource("snaketitle.jpg")));
        upmouth = new ImageIcon(requireNonNull(classLoader.getResource("upmouth.png")));
        leftmouth = new ImageIcon(requireNonNull(classLoader.getResource("leftmouth.png")));
        downmouth = new ImageIcon(requireNonNull(classLoader.getResource("downmouth.png")));
        rightmouth = new ImageIcon(requireNonNull(classLoader.getResource("rightmouth.png")));
        snakeimage = new ImageIcon(requireNonNull(classLoader.getResource("snakeimage.png")));

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);

    }

    @Override
    public void paint(final Graphics g) {

        if (!moving) {
            snakeXLength[2] = 50;
            snakeXLength[1] = 75;
            snakeXLength[0] = 100;

            snakeYLength[2] = 100;
            snakeYLength[1] = 100;
            snakeYLength[0] = 100;
        }

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

        // draw snake
        for (int a = 0; a < lengthOfSnake; a++) {
            if (a == 0) {
                if (up) upmouth.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
                else if (left) leftmouth.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
                else if (down) downmouth.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
                else if (right) rightmouth.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
            } else {
                snakeimage.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
            }
        }
    }

    @Override
    public void actionPerformed(final ActionEvent e) {

    }

    @Override
    public void keyTyped(final KeyEvent e) {

    }

    @Override
    public void keyPressed(final KeyEvent e) {

    }

    @Override
    public void keyReleased(final KeyEvent e) {

    }
}
