package com.github.bsfowlie.snake;

import static java.util.Objects.requireNonNull;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.stream.IntStream;
import javax.swing.*;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

    private static final int delay = 100;

    private int[] snakeXLength = new int[750];

    private int[] snakeYLength = new int[750];

    private int lengthOfSnake = 0;

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
        timer.start();

    }

    @Override
    public void paint(final Graphics g) {

        if (!moving) {
            lengthOfSnake = 3;

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
        titleImage.paintIcon(this, g, 25, 11);

        // draw the border for game play
        g.setColor(Color.WHITE);
        g.drawRect(24, 74, 851, 577);

        // draw background for game play
        g.setColor(Color.BLACK);
        g.fillRect(25, 75, 850, 575);

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

        timer.start();
        if (!moving) {
            return;
        }
        // update snake body
        IntStream.iterate(lengthOfSnake - 1, r -> r >= 0, r -> r - 1).forEachOrdered(r -> {
            snakeXLength[r + 1] = snakeXLength[r];
            snakeYLength[r + 1] = snakeYLength[r];
        });
        // update snake head
        if (up) {
            snakeYLength[0] -= 25;
            if (snakeYLength[0] < 75) {
                snakeYLength[0] = 625;
            }
        } else if (left) {
            snakeXLength[0] -= 25;
            if (snakeXLength[0] < 25) {
                snakeXLength[0] = 850;
            }
        } else if (down) {
            snakeYLength[0] += 25;
            if (snakeYLength[0] > 625) {
                snakeYLength[0] = 75;
            }
        } else if (right) {
            snakeXLength[0] += 25;
            if (snakeXLength[0] > 850) {
                snakeXLength[0] = 25;
            }
        }
        repaint();

    }

    @Override
    public void keyTyped(final KeyEvent e) {

    }

    @Override
    public void keyPressed(final KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            moving = true;
            if (!left) {
                right = true;
                left = false;
                down = false;
                up = false;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            moving = true;
            if (!right) {
                left = true;
                right = false;
                down = false;
                up = false;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            moving = true;
            if (!down) {
                up = true;
                down = false;
                left = false;
                right = false;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            moving = true;
            if (!up) {
                down = true;
                up = false;
                left = false;
                right = false;
            }
        }

    }

    @Override
    public void keyReleased(final KeyEvent e) {

    }
}
