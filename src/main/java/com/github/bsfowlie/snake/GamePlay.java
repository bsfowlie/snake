package com.github.bsfowlie.snake;

import static java.util.Objects.requireNonNull;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Deque;
import java.util.EnumMap;
import java.util.LinkedList;
import javax.swing.*;

public class GamePlay extends JPanel implements KeyListener, ActionListener {

    public static final int WIDTH = 905;

    public static final int HEIGHT = 700;

    private static final int DELAY = 100;

    private final ImageIcon titleImage;

    private final Deque<Point> snakeBody = new LinkedList<>();

    private final EnumMap<Direction, ImageIcon> headImage = new EnumMap<>(Direction.class);

    private final ImageIcon bodyImage;

    private Point snakeHead;

    private Direction headDir;

    private Direction lastHeadDir;

    private Timer timer;

    private boolean moving;

    public GamePlay() {

        final ClassLoader classLoader = getClass().getClassLoader();
        titleImage = new ImageIcon(requireNonNull(classLoader.getResource("snaketitle.jpg")));
        headImage.put(Direction.UP, new ImageIcon(requireNonNull(classLoader.getResource("upmouth.png"))));
        headImage.put(Direction.LEFT, new ImageIcon(requireNonNull(classLoader.getResource("leftmouth.png"))));
        headImage.put(Direction.DOWN, new ImageIcon(requireNonNull(classLoader.getResource("downmouth.png"))));
        headImage.put(Direction.RIGHT, new ImageIcon(requireNonNull(classLoader.getResource("rightmouth.png"))));
        bodyImage = new ImageIcon(requireNonNull(classLoader.getResource("snakeimage.png")));

        init();
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        addKeyListener(this);
        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void init() {

        snakeBody.clear();
        snakeBody.addFirst(new Point(50, 100));
        snakeBody.addFirst(new Point(75, 100));
        snakeHead = new Point(100, 100);
        headDir = Direction.RIGHT;
        lastHeadDir = Direction.RIGHT;
        moving = false;
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

        // draw the snake head
        headImage.get(headDir).paintIcon(this, g, snakeHead.x, snakeHead.y);

        // draw the snake body
        snakeBody.forEach(body -> bodyImage.paintIcon(this, g, body.x, body.y));

    }

    @Override
    public void actionPerformed(final ActionEvent e) {

        timer.start();
        if (!moving) return;
        switch (headDir) {
            case UP:
                snakeBody.removeLast();
                snakeBody.addFirst(snakeHead.getLocation());
                snakeHead.y -= 25;
                if (snakeHead.y < 75) snakeHead.y = 625;
                break;
            case LEFT:
                snakeBody.removeLast();
                snakeBody.addFirst(snakeHead.getLocation());
                snakeHead.x -= 25;
                if (snakeHead.x < 25) snakeHead.x = 850;
                break;
            case DOWN:
                snakeBody.removeLast();
                snakeBody.addFirst(snakeHead.getLocation());
                snakeHead.y += 25;
                if (snakeHead.y > 625) snakeHead.y = 75;
                break;
            case RIGHT:
                snakeBody.removeLast();
                snakeBody.addFirst(snakeHead.getLocation());
                snakeHead.x += 25;
                if (snakeHead.x > 850) snakeHead.x = 25;
                break;
        }
        lastHeadDir = headDir;
        repaint();

    }

    @Override
    public void keyTyped(final KeyEvent e) {

    }

    @Override
    public void keyPressed(final KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_Q) {
            System.exit(0);
        }

        if (e.getKeyCode() == KeyEvent.VK_UP && lastHeadDir != Direction.DOWN) {
            moving = true;
            headDir = Direction.UP;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && lastHeadDir != Direction.RIGHT) {
            moving = true;
            headDir = Direction.LEFT;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && lastHeadDir != Direction.UP) {
            moving = true;
            headDir = Direction.DOWN;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && lastHeadDir != Direction.LEFT) {
            moving = true;
            headDir = Direction.RIGHT;
        }

    }

    @Override
    public void keyReleased(final KeyEvent e) {

    }

    private enum Direction {UP, LEFT, DOWN, RIGHT}
}
