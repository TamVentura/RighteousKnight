/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Gr8aX.RighteousKnight.Entities;

import static com.Gr8aX.RighteousKnight.Game.DEBUGGING;
import static com.Gr8aX.RighteousKnight.Game.qx;
import static com.Gr8aX.RighteousKnight.Game.qy;
import com.Gr8aX.RighteousKnight.rendering.textures.Texture;
import com.Gr8aX.RighteousKnight.world.TileMap;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Tiago
 */
public abstract class Entity {

    protected double x, y, xi, yi;
    protected Texture sprite;
    protected TileMap tileMap;
    protected String name;
    protected int offsetX;
    protected int offsetY;
    protected int width,height;

    public Entity(Texture sprite, double x, double y, TileMap tileMap) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
        this.xi = x;
        this.yi = y;
        this.tileMap = tileMap;
        this.tileMap.addEntity(this);
        this.width = sprite.getWidth()/qx;
        this.height = sprite.getHeight()/qy;
    }

    public abstract void tick();

    public int getWidth() {
        return sprite.getWidth(); //To change body of generated methods, choose Tools | Templates.
    }

    public int getHeight() {
        return sprite.getHeight(); //To change body of generated methods, choose Tools | Templates.
    }

    public void render(Graphics2D g, int offsetX, int offsetY) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        sprite.render(g, x + offsetX, y + offsetY);
        if (DEBUGGING) {
            g.setColor(Color.yellow);
            g.draw(getTopOffset());
            g.setColor(Color.yellow);
            g.draw(getBottomOffset());
            g.setColor(Color.yellow);
            g.draw(getLeftOffset());
            g.setColor(Color.yellow);
            g.draw(getRightOffset());
        }
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, getWidth(), getHeight());
    }

    public Rectangle getTop() {
        return new Rectangle((int) x + qx / 15, (int) y, getWidth() - qx / 15 - qx / 15, qy / 15);
    }

    public Rectangle getBottom() {
        return new Rectangle((int) x + qx / 15, (int) y + getHeight() - (qy / 15), getWidth() - qx / 15 - qx / 15, qy / 15);
    }

    public Rectangle getLeft() {
        return new Rectangle((int) x + getWidth() - qx / 15, (int) y + qy / 15, qx / 15, getHeight() - qy / 15 - qy / 15);
    }

    public Rectangle getRight() {
        return new Rectangle((int) x, (int) y + qy / 15, qx / 15, getHeight() - qy / 15 - qy / 15);
    }

    public Rectangle getTopOffset() {
        return new Rectangle((int) x + offsetX + qx / 15, (int) y + offsetY, getWidth() - qx / 15 - qx / 15, qy / 15);
    }

    public Rectangle getBottomOffset() {
        return new Rectangle((int) x + offsetX + qx / 15, (int) y + offsetY + getHeight() - (qy / 15), getWidth() - qx / 15 - qx / 15, qy / 15);
    }

    public Rectangle getLeftOffset() {
        return new Rectangle((int) x + offsetX + getWidth() - qx / 15, (int) y + offsetY + qy / 15, qx / 15, getHeight() - qy / 15 - qy / 15);
    }

    public Rectangle getRightOffset() {
        return new Rectangle((int) x + offsetX, (int) y + offsetY + qy / 15, qx / 15, getHeight() - qy / 15 - qy / 15);
    }

    public abstract String getName();

    public abstract void doAction(Entity p);

    public void delete() {
        x = -1000;
        y = -1000;
    }

    public void restart() {
        this.x = xi;
        this.y = yi;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    public int getPWidth(){
        return width;
    }
    public int getPHeight(){
        return height;
    }
    
    public void setSize(int width, int height){
        sprite.setSize(width, height);
    }
    

}
