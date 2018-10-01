/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Gr8aX.RighteousKnight.rendering.textures;

import com.Gr8aX.RighteousKnight.Game;
import java.awt.Graphics2D;

/**
 *
 * @author tiago
 */
public class Animation {

    private int count;
    private int index;
    private double speed;
    private int numFrames;
    private Texture currentFrame;
    private Texture[] frames;

    public Animation(double speed, Texture... frames) {
        this.speed = speed;
        this.frames = frames;
        this.numFrames = frames.length;
        for (int i = 0; i < frames.length + 1; i++) {
            if (++index > (speed * Game.tpsT)) {
                index = 0;
                nextFrame();
            }
        }
    }

    private void nextFrame() {
        currentFrame = frames[count++];
        if (count >= numFrames) {
            count = 0;
        }
    }

    public void run() {
        if (++index > (speed * Game.tpsT)) {
            index = 0;
            nextFrame();
        }
    }

    public void render(Graphics2D g, double x, double y) {
        if (currentFrame != null) {
            currentFrame.render(g, x, y);
        }
    }
}
