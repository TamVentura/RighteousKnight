/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Gr8aX.RighteousKnight.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 *
 * @author Tiago
 */
public class MouseInput extends MouseAdapter{

    private static final int numButtons = 10;

    private static final boolean[] buttons = new boolean[numButtons];
    private static final boolean[] lastButtons = new boolean[numButtons];

    private static int x = -1, y = -1;
    private static int lastX = x, lastY = y;

    private static boolean moving;
    
    private static int notches;
    

    @Override
    public void mousePressed(MouseEvent e) {
        buttons[e.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        buttons[e.getButton()] = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        y = e.getY();
        x = e.getX();
        if (x != lastX || y != lastY) {
            moving = true;
        } else {
            moving = false;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        y = e.getY();
        x = e.getX();
        moving = true;
    }
    
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        notches=e.getWheelRotation();
    }

    public static void update() {
        for (int i = 0; i < numButtons; i++) {
            lastButtons[i] = buttons[i];
        }
        if (x == lastX && y == lastY) {
            moving = false;
        }
        lastX = x;
        lastY = y;
    }

    public static boolean isDown(int button) {
        return buttons[button];
    }

    public static boolean wasPressed(int button) {
        return isDown(button) && !lastButtons[button];
    }

    public static boolean wasRealesed(int button) {
        return !isDown(button) && lastButtons[button];
    }

    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }

    public static boolean isMoving() {
        return moving;
    }
    
    public static int getNotches(){
        int oldn = notches;
        
        return oldn;
    }
    
    public static void updateNotches(){
        notches =0;
    }
}
