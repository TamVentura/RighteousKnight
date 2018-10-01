/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Gr8aX.RighteousKnight.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Tiago
 */
public class KeyInput extends KeyAdapter {

    private static final int numKeys = 525;

    private static final boolean[] keys = new boolean[numKeys];
    private static final boolean[] lastKeys = new boolean[numKeys];

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    public static boolean isDown(int key) {
        return keys[key];
    }

    public static void update() {
        for (int i = 0; i < numKeys; i++) {
            lastKeys[i] = keys[i];
        }
    }

    public static boolean wasPressed(int key) {
        return isDown(key) && !lastKeys[key];
    }

    public static boolean wasRealesed(int key) {
        return !isDown(key) && lastKeys[key];
    }

}
