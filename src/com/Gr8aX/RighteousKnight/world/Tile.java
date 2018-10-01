/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Gr8aX.RighteousKnight.world;

import static com.Gr8aX.RighteousKnight.Game.DEBUGGING;
import static com.Gr8aX.RighteousKnight.Game.qx;
import static com.Gr8aX.RighteousKnight.Game.qy;
import com.Gr8aX.RighteousKnight.rendering.textures.Texture;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Tiago
 */
public class Tile {
    
    protected float x,y;
    protected int offsetX,offsetY;
    protected Texture texture;
    protected boolean solid;
    protected TileMap tileMap;

    public Tile(Texture texture, boolean solid,float x, float y,TileMap tileMap) {
        this.texture = texture;
        this.tileMap = tileMap;
        this.solid = solid;
        this.x = x;
        this.y=y;
    }
    
    public void render(Graphics2D g, int x, int y, int offsetX, int offsetY){
        texture.render(g, x+offsetX, y+offsetY);
        this.offsetX=offsetX;
        this.offsetY=offsetY;
        if (DEBUGGING) {
            g.setColor(Color.yellow);
            g.draw(getTopOffset());
            g.setColor(Color.yellow);
            g.draw(getBottomOffset());
            g.setColor(Color.red);
            g.draw(getLeftOffset());
            g.setColor(Color.yellow);
            g.draw(getRightOffset());
        }
    }

    public boolean isSolid() {
        return solid;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
    
    
    
    public int getWidth() {
        return texture.getWidth();
    }
    public int getHeight() {
        return texture.getHeight();
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

    public Rectangle getRight() {
        return new Rectangle((int) x + getWidth() - qx / 15, (int) y + qy / 15, qx / 15, getHeight() - qy / 15 - qy / 15);
    }

    public Rectangle getLeft() {
        return new Rectangle((int) x, (int) y + qy / 15, qx / 15, getHeight() - qy / 15 - qy / 15);
    }
        public Rectangle getTopOffset() {
        return new Rectangle((int) x + offsetX + qx / 15, (int) y + offsetY, (int) (getWidth() - qx / 15 - qx / 15), qy / 15);
    }

    public Rectangle getBottomOffset() {
        return new Rectangle((int) x + offsetX + qx / 15, (int) y + offsetY + (int)getHeight() - (qy / 15), getWidth() - qx / 15 - qx / 15, qy / 15);
    }

    public Rectangle getLeftOffset() {
        return new Rectangle((int) x + offsetX + getWidth() - qx / 15, (int) y + offsetY + qy / 15, qx / 15, getHeight() - qy / 15 - qy / 15);
    }

    public Rectangle getRightOffset() {
        return new Rectangle((int) x + offsetX, (int) y + offsetY + qy / 15, qx / 15, getHeight() - qy / 15 - qy / 15);
    }
    
    public void setSize(int width, int height){
        texture.setSize(width, height);
    }
    
    public void tick(){
        
    }
    
}
