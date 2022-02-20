package com.balls.models;

import java.awt.*;

public class Ball {
    private double x;
    private double y;
    private double r;
    private double dX;
    private double dY;
    private Color color;
    private Thread thread;

    public Ball(double x, double y, double r, double dX, double dY, Color color) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.dX = dX;
        this.dY = dY;
        this.color = color;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getdX() {
        return dX;
    }

    public void setdX(double dX) {
        this.dX = dX;
    }

    public double getdY() {
        return dY;
    }

    public void setdY(double dY) {
        this.dY = dY;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }
}
