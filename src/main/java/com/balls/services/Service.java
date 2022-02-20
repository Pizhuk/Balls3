package com.balls.services;

import com.balls.Main;
import com.balls.models.Ball;
import com.balls.threads.BThread;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class Service {
    private List<Ball> balls;
    private Random random;

    public Service(List<Ball> balls, Random random) {
        this.balls = balls;
        this.random = random;
    }

    public void createRandomBall(int x, int y) {
        Ball b = new Ball(x, y, random.nextInt(Main.MAX_R - Main.MIN_R) + Main.MIN_R, random.nextDouble() * 4 - 2,
                random.nextDouble() * 4 - 2, new Color(random.nextInt(Integer.MAX_VALUE)));

        balls.add(b);
        createThread(b);
    }

    public void createThread(Ball b) {
        Thread thread = new Thread(new BThread(b, balls));
        b.setThread(thread);
        thread.start();
    }
}
