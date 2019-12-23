package com.github.bsfowlie.snake;

import static java.util.Objects.requireNonNull;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.stream.IntStream;
import javax.swing.*;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

    private static final int delay = 100;

    private int[] snakeXPos = new int[750];

    private int[] snakeYPos = new int[750];

    private int lengthOfSnake = 0;

    private boolean up = false;

    private boolean left = false;

    private boolean down = false;

    private boolean right = true;

    private boolean moving = false;

    private ImageIcon titleImage;

    private ImageIcon mouthUp;

    private ImageIcon mouthLeft;

    private ImageIcon mouthDown;

    private ImageIcon mouthRight;

    private ImageIcon snakeBody;

    private Timer timer;

    private final int[] foodXPos = IntStream.iterate(25, i -> i <= 850, i -> i + 25).toArray();

    private final int[] foodYPos = IntStream.iterate(75, i -> i <= 625, i -> i + 25).toArray();

    private ImageIcon foodImage;

    private final Random random = new Random();

    private int xIndex = random.nextInt(foodXPos.length);

    private int yIndex = random.nextInt(foodYPos.length);

    public Gameplay() {

        final ClassLoader classLoader = getClass().getClassLoader();
        titleImage = new ImageIcon(requireNonNull(classLoader.getResource("snaketitle.jpg")));
        mouthUp = new ImageIcon(requireNonNull(classLoader.getResource("upmouth.png")));
        mouthLeft = new ImageIcon(requireNonNull(classLoader.getResource("leftmouth.png")));
        mouthDown = new ImageIcon(requireNonNull(classLoader.getResource("downmouth.png")));
        mouthRight = new ImageIcon(requireNonNull(classLoader.getResource("rightmouth.png")));
        snakeBody = new ImageIcon(requireNonNull(classLoader.getResource("snakeimage.png")));

        foodImage = new ImageIcon(requireNonNull(classLoader.getResource("enemy.png")));

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

            snakeXPos[2] = 50;
            snakeXPos[1] = 75;
            snakeXPos[0] = 100;

            snakeYPos[2] = 100;
            snakeYPos[1] = 100;
            snakeYPos[0] = 100;
        }

        // fill in the background
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, 905, 700);

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
                if (up) mouthUp.paintIcon(this, g, snakeXPos[a], snakeYPos[a]);
                else if (left) mouthLeft.paintIcon(this, g, snakeXPos[a], snakeYPos[a]);
                else if (down) mouthDown.paintIcon(this, g, snakeXPos[a], snakeYPos[a]);
                else if (right) mouthRight.paintIcon(this, g, snakeXPos[a], snakeYPos[a]);
            } else {
                snakeBody.paintIcon(this, g, snakeXPos[a], snakeYPos[a]);
            }
        }

        if (foodXPos[xIndex] == snakeXPos[0] && foodYPos[yIndex] == snakeYPos[0]) {
            lengthOfSnake++;
            xIndex = random.nextInt(foodXPos.length);
            yIndex = random.nextInt(foodYPos.length);
        }
        foodImage.paintIcon(this,g, foodXPos[xIndex], foodYPos[yIndex]);

        g.dispose();
    }

    @Override
    public void actionPerformed(final ActionEvent e) {

        timer.start();
        if (!moving) {
            return;
        }
        // update snake body
        IntStream.iterate(lengthOfSnake - 1, r -> r >= 0, r -> r - 1).forEachOrdered(r -> {
            snakeXPos[r + 1] = snakeXPos[r];
            snakeYPos[r + 1] = snakeYPos[r];
        });
        // update snake head
        if (up) {
            snakeYPos[0] -= 25;
            if (snakeYPos[0] < 75) {
                snakeYPos[0] = 625;
            }
        } else if (left) {
            snakeXPos[0] -= 25;
            if (snakeXPos[0] < 25) {
                snakeXPos[0] = 850;
            }
        } else if (down) {
            snakeYPos[0] += 25;
            if (snakeYPos[0] > 625) {
                snakeYPos[0] = 75;
            }
        } else if (right) {
            snakeXPos[0] += 25;
            if (snakeXPos[0] > 850) {
                snakeXPos[0] = 25;
            }
        }
        repaint();

    }

    @Override
    public void keyTyped(final KeyEvent e) {

    }

    @Override
    public void keyPressed(final KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_RIGHT && !left) {
            moving = true;
            right = true;
            left = false;
            down = false;
            up = false;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && !right) {
            moving = true;
            left = true;
            right = false;
            down = false;
            up = false;
        } else if (e.getKeyCode() == KeyEvent.VK_UP && !down) {
            moving = true;
            up = true;
            down = false;
            left = false;
            right = false;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && !up) {
            moving = true;
            down = true;
            up = false;
            left = false;
            right = false;
        } else if (e.getKeyCode() == KeyEvent.VK_Q) {
            System.exit(0);
        }

    }

    @Override
    public void keyReleased(final KeyEvent e) {

    }
}
