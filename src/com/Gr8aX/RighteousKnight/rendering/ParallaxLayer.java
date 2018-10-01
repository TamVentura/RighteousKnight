/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Gr8aX.RighteousKnight.rendering;

import com.Gr8aX.RighteousKnight.Game;
import com.Gr8aX.RighteousKnight.rendering.textures.Texture;
import java.awt.Graphics2D;

/**
 *
 * @author tiago
 */
public class ParallaxLayer {

    private Texture texture;
    private int x, y;
    private int width, height;
    private int vx;
    private int gap;
    private boolean left, right;

    public ParallaxLayer(Texture texture, int vx, int gap) {
        this.texture = texture;
        this.vx = vx;
        this.gap = gap;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.x = this.y = 0;
    }

    public ParallaxLayer(Texture texture, int vx) {
        this(texture, vx, 0);
    }

    public void setRight() {
        right = true;
        left = false;
    }

    public void setLeft() {
        left = true;
        right = false;
    }

    public void stop() {
        right = left = false;
    }

    public void move() {
        if (right) {
            x = (x + vx) % (width + gap);
        }
        if (left) {
            x = (x - vx) % (width);
        }
    }

    public void render(Graphics2D g) {
        if (x == 0) {
            texture.render(g, 0, Game.WIDTH, 0, Game.WIDTH, y);
        } else if (x > 0 && x < Game.WIDTH) {
            texture.render(g, x, Game.WIDTH, 0, Game.WIDTH - x, y);
            texture.render(g,0, x, width-x, width, y);
        } else if (x >= Game.WIDTH) {
            texture.render(g, 0, Game.WIDTH, width-x, width-x + Game.WIDTH, y);
        } else if (x < 0 && x >= Game.WIDTH - width) {
            texture.render(g, 0, Game.WIDTH,-x,Game.WIDTH-x,y);
        } else if (x < Game.WIDTH - width) {
            texture.render(g, 0, width+x, -x, width, y);
            texture.render(g, gap+width+x, gap + Game.WIDTH, 0, Game.WIDTH-width-x, y);
        }
    }

}
