package com.github.bsfowlie.snake;

import static java.util.Objects.requireNonNull;

import java.awt.*;
import java.util.Deque;
import java.util.EnumMap;
import java.util.LinkedList;
import javax.swing.*;

public class GamePlay extends JPanel {

    public static final int WIDTH = 905;

    public static final int HEIGHT = 700;

    private final ImageIcon titleImage;

    private final Deque<Point> snakeBody = new LinkedList<>();

    private final EnumMap<Direction, ImageIcon> headImage = new EnumMap<>(Direction.class);

    private final ImageIcon bodyImage;

    private Point snakeHead;

    private Direction headDir;

    public GamePlay() {

        final ClassLoader classLoader = getClass().getClassLoader();
        titleImage = new ImageIcon(requireNonNull(classLoader.getResource("snaketitle.jpg")));
        headImage.put(Direction.UP, new ImageIcon(requireNonNull(classLoader.getResource("upmouth.png"))));
        headImage.put(Direction.LEFT, new ImageIcon(requireNonNull(classLoader.getResource("leftmouth.png"))));
        headImage.put(Direction.DOWN, new ImageIcon(requireNonNull(classLoader.getResource("downmouth.png"))));
        headImage.put(Direction.RIGHT, new ImageIcon(requireNonNull(classLoader.getResource("rightmouth.png"))));
        bodyImage = new ImageIcon(requireNonNull(classLoader.getResource("snakeimage.png")));

        init();
    }

    private void init() {

        snakeBody.clear();
        snakeBody.addFirst(new Point(50, 100));
        snakeBody.addFirst(new Point(75, 100));
        snakeHead = new Point(100, 100);
        headDir = Direction.RIGHT;
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

    private enum Direction {UP, LEFT, DOWN, RIGHT}
}
