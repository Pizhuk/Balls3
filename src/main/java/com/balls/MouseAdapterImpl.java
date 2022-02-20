package com.balls;

import com.balls.services.Service;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseAdapterImpl extends MouseAdapter {

    private Service service;

    public MouseAdapterImpl(Service service) {
        this.service = service;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        service.createRandomBall(e.getX(), e.getY());
    }
}
