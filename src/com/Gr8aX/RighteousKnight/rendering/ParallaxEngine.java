/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Gr8aX.RighteousKnight.rendering;

import java.awt.Graphics2D;

/**
 *
 * @author tiago
 */
public class ParallaxEngine {

    private ParallaxLayer[] layers;

    public ParallaxEngine(ParallaxLayer... layers) {
        this.layers = layers;
    }

    public void setRight() {
        for (ParallaxLayer e : layers) {
            e.setRight();
        }
    }

    public void setLeft() {
        for (ParallaxLayer e : layers) {
            e.setLeft();
        }
    }

    public void stop() {
        for (ParallaxLayer e : layers) {
            e.stop();
        }
    }

    public void move() {
        for (ParallaxLayer e : layers) {
            e.move();
        }
    }

    public void render(Graphics2D g) {
        for (ParallaxLayer e : layers) {
            e.render(g);
        }
    }
}


