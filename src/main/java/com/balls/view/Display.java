package com.balls.view;

import com.balls.models.Ball;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;
import java.util.List;

public class Display {
    private JFrame window;
    private Canvas content;
    private Dimension size;
    private BufferedImage buffer;
    private int[] bufferData;
    private Graphics bufferGraphics;
    private int clearColor;
    private List<Ball> balls;
    private MouseAdapter mouseAdapter;

    public Display(JFrame window, Canvas content, Dimension size, BufferedImage buffer, int clearColor, List<Ball> balls,
                   MouseAdapter mouseAdapter) {
        this.window = window;
        this.content = content;
        this.size = size;
        this.buffer = buffer;
        this.clearColor = clearColor;
        this.balls = balls;
        this.mouseAdapter = mouseAdapter;
    }

    public void start() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        content.setPreferredSize(size);
        window.setResizable(false);
        window.getContentPane().add(content);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        bufferData = ((DataBufferInt) buffer.getRaster().getDataBuffer()).getData();
        bufferGraphics = buffer.getGraphics();
        content.addMouseListener(mouseAdapter);
    }

    public void clear() {
        Arrays.fill(bufferData, clearColor);
    }

    public void render() {
        for (Ball b: balls) {
            bufferGraphics.setColor(b.getColor());
            bufferGraphics.fillOval((int) b.getX(), (int) b.getY(), (int) b.getR(), (int) b.getR());
        }
    }

    public void swapBuffers() {
        Graphics g = content.getGraphics();
        g.drawImage(buffer, 0, 0, null);
    }
}
