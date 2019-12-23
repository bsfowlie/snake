/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.github.bsfowlie.snake;

import java.awt.*;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            Gameplay game = new Gameplay();

            frame.setBounds(10, 10, 905, 700);
            frame.setResizable(false);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(game);
        });

    }

}
