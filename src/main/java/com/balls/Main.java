package com.balls;

import com.balls.models.Ball;
import com.balls.services.Service;
import com.balls.view.Display;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int MAX_R = WIDTH / 8;
    public static final int MIN_R = 50;
    public static final long UPDATE_RATE = 1000 / 120;
    public static final String TITLE = "Balls";

    public static void main(String[] args) {

        JFrame frame = new JFrame(TITLE);
        Canvas content = new Canvas();
        Dimension size = new Dimension(WIDTH, HEIGHT);
        BufferedImage buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        List<Ball> balls = new ArrayList<>();
        Random random = new Random();
        Service service = new Service(balls, random);
        MouseAdapterImpl m = new MouseAdapterImpl(service);
        Display display = new Display(frame, content, size, buffer, 0xFFFFFFFF, balls, m);

        display.start();
        display.clear();

        Timer t = new Timer((int) UPDATE_RATE, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.clear();
                display.render();
                display.swapBuffers();
            }
        });
        t.setRepeats(true);
        t.start();
    }
}
