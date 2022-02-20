package com.balls.threads;

import com.balls.Main;
import com.balls.models.Ball;
import java.util.List;

public class BThread implements Runnable {
    private Ball ball;
    private List<Ball> balls;

    public BThread(Ball ball, List<Ball> balls) {
        this.ball = ball;
        this.balls = balls;
    }

    @Override
    public void run() {
        while (Thread.currentThread().isAlive()) {
            checkBoarders(ball);
            checkCollision();
            moveBall(ball);

            try {
                Thread.sleep(Main.UPDATE_RATE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void moveBall(Ball b) {
        b.setX(b.getX() + b.getdX());
        b.setY(b.getY() + b.getdY());
    }

    private boolean checkBoarders(Ball b) {
        double nextX = b.getX() + b.getdX();
        double nextY = b.getY() + b.getdY();
        double r = b.getR();
        if (nextX <= 0 || (nextX + r >= Main.WIDTH)) {
            b.setdX(b.getdX() * -1d);
            return true;
        } else if (nextY <= 0 || (nextY + r >= Main.HEIGHT)) {
            b.setdY(b.getdY() * -1d);
            return true;
        }
        return false;
    }

    private boolean checkDistance(Ball b1, Ball b2) {
        double r1 = b1.getR() / 2;
        double r2 = b2.getR() / 2;
        double nextX1 = b1.getX() + b1.getdX() + r1;
        double nextY1 = b1.getY() + b1.getdY() + r1;
        double nextX2 = b2.getX() + b2.getdX() + r2;
        double nextY2 = b2.getY() + b2.getdY() + r2;

        if (Math.abs(r2 - r1) < 0.1 && Math.abs(nextX2 - nextX1) < 0.1 && Math.abs(nextY2 - nextY1) < 0.1) {
            return false;
        }

        return Math.sqrt(Math.pow((nextX2 - nextX1), 2) + Math.pow((nextY2 - nextY1), 2)) <= (r1 + r2);
    }

    private boolean checkCollision() {
        if (balls.size() > 1) {
            for (int i = 0; i < balls.size(); i++) {
                if (checkDistance(ball, balls.get(i))) {
                    System.out.println(Thread.currentThread().getName() + " coll " + ball.getX() + " " + balls.get(i).getX());
                    doCollision(ball, balls.get(i));
                    return true;
                }
            }
        }
        return false;
    }

    public void doCollision(Ball b1, Ball b2) {
        double r1 = b1.getR() / 2;
        double r2 = b2.getR() / 2;
        double x1 = b1.getX() + r1;
        double y1 = b1.getY() + r1;
        double x2 = b2.getX() + r2;
        double y2 = b2.getY() + r2;

        System.out.println("AA " + r1 + " " + r2 + " " + x1 + " " + y1 + " " + x2 + " " + y2);

        double m1 = 3.14 * (r1) * (r1);
        double m2 = 3.14 * (r2) * (r2);

        double newDx1 = (2 * m2 * b2.getdX() + (m1 - m2) * b1.getdX()) / (m1 + m2);
        double newDy1 = (2 * m2 * b2.getdY() + (m1 - m2) * b1.getdY()) / (m1 + m2);

        double newDx2 = (2 * m1 * b1.getdX() + (m2 - m1) * b2.getdX()) / (m1 + m2);
        double newDy2 = (2 * m1 * b1.getdY() + (m2 - m1) * b2.getdY()) / (m1 + m2);

        System.out.println("BB " + newDx1 + " " + newDy1 + " " + newDx2 + " " + newDy2 + " ");

        b1.setdX(newDx1);
        b2.setdX(newDx2);
        b1.setdY(newDy1);
        b2.setdY(newDy2);
    }
}
