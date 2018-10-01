/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Gr8aX.RighteousKnight.Entities;

import com.Gr8aX.RighteousKnight.Effects.Effect;
import com.Gr8aX.RighteousKnight.Game;
import static com.Gr8aX.RighteousKnight.Game.qx;
import static com.Gr8aX.RighteousKnight.Game.qy;
import com.Gr8aX.RighteousKnight.rendering.textures.Animation;
import com.Gr8aX.RighteousKnight.rendering.textures.Texture;
import com.Gr8aX.RighteousKnight.world.Tile;
import com.Gr8aX.RighteousKnight.world.TileMap;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Tiago
 */
public abstract class Mob extends Entity {

    protected static final double VELOCITYX = 10.6;
    protected static final double VELOCITYY = 26.4;
    protected static final double gravity = 1;
    protected double DelayY = 0;
    protected double DelayX = 0;

    protected double vx, vy;
    protected static final double maxvy = 30;

    protected boolean falling;
    protected boolean canJump;
    protected double jumpnumber;
    protected double jumpingnumber = 0;

    protected boolean hasGravity;

    protected boolean jump = false;

    protected int life = 100000;
    protected int lifei = 100000;

    protected ArrayList<Effect> effects;
    protected boolean moving;

    protected double SpeedDirection = 1;

    protected Animation anime;

    public Mob(Texture sprite, double x, double y, TileMap tileMap, Animation anime) {
        super(sprite, x, y, tileMap);
        this.hasGravity = true;
        this.anime = anime;
        setEffects();
    }

    public Mob(Texture sprite, double x, double y, TileMap tileMap, boolean hasGravity) {
        super(sprite, x, y, tileMap);
        this.hasGravity = hasGravity;
        setEffects();
    }

    protected void setEffects() {
        effects = new ArrayList<>();
    }

    public boolean hasVerticalColision() {
        for (Tile t : tileMap.getTiles(getHeight(), x, y, x + vx, y + vy)) {
            if (t != null) {
                if (getNextBoundsVertical().intersects(t.getTop()) && vy > 0) {
                    setY(t.getY() - getHeight() + 1);
                    setVelocityY(0);
                    canJump = true;
                    return true;
                }
                if (getNextBoundsVertical().intersects(t.getBottom()) && vy < 0) {
                    setY(t.getY() + t.getHeight() - 1);
                    setVelocityY(0);
                    return true;
                }
            }
        }
        setCanJump(false);
        return false;
    }

    public boolean hasHorizontalColision() {
        for (Tile t : tileMap.getTiles(getWidth(), x, y, x + vx, y + vy)) {
            if (t != null) {
                if (getNextBoundsHorizontaly().intersects(t.getLeft())) {
                    setX(t.getX() - getWidth() + 1);
                    moving=false;
                    return true;
                }
                if (getNextBoundsHorizontaly().intersects(t.getRight())) {
                    setX(t.getX() + t.getWidth() - 1);
                    moving=false;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void tick() {
        moving = false;
        try {
            for (Effect e : effects) {
                e.tick(this);
            }
        } catch (Exception ex) {
        }

        if (vy > 0) {
            falling = true;
        } else if (vy < 0) {
            falling = false;
        }
        jump(VELOCITYY, jump);
        move();
        if (hasGravity) {
            fall();
        }
        if (life <= 0) {
            tileMap.restart();
        }
        if(vx==0){
            moving = false;
        }
        if (anime != null) {
            if (vx != 0) {
                anime.run();
            }
        }
    }

    public boolean isJump() {
        return jump;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void move() {
        if (!hasHorizontalColision()) {
            x += vx;
            moving = true;
        }
        if (!hasVerticalColision()) {
            y += vy;
        }
    }

    public Rectangle getNextBoundsVertical() {
        return new Rectangle((int) (x), (int) (y + vy), getWidth(), getHeight());
    }

    public Rectangle getNextBoundsHorizontaly() {
        return new Rectangle((int) (x + vx), (int) (y), getWidth(), getHeight());
    }

    public Rectangle getNextBounds() {
        return new Rectangle((int) (x + vx), (int) (y + vy), getWidth(), getHeight());
    }

    protected void fall() {
        vy += (((gravity) * qy) / 120);
        if (vy > (((maxvy) * qy) / 120)) {
            vy = (((maxvy) * qy) / 120);
        }
    }

    protected void jump(double jumpAcc, boolean jump) {
        if (canJump && jump) {
            vy -= ((((jumpAcc - DelayY)) * qy) / 120);
            canJump = false;
            jump = false;
        }
    }

    public void goJump() {
        vy = 0;
        vy -= (((VELOCITYY - DelayY) * qy) / 120);
        canJump = false;
        jump = false;

    }
    
    public void goJump(double Velocity) {
        vy = 0;
        vy -= (((Velocity - DelayY) * qy) / 120);
        canJump = false;
        jump = false;

    }

    public void setVelocityX(double vx) {
        this.vx = vx;
    }

    @Override
    public String getName() {
        return name;
    }

    public void doDamage(int dano) {
        this.life -= dano;
    }

    @Override
    public void render(Graphics2D g, int offsetX, int offsetY) {
        if (vx==0 || anime == null) {
            super.render(g, offsetX, offsetY);
        } else {
            anime.render(g, x + offsetX, y + offsetY);
        }
    }

    public void crouch() {

    }

    public void setVelocityY(double vy) {
        this.vy = vy;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setCanJump(boolean canJump) {
        this.canJump = canJump;
    }

    public void kill() {
        tileMap.restart();
    }

    @Override
    public void restart() {
        super.restart(); //To change body of generated methods, choose Tools | Templates.
        vx = 0;
        vy = 0;
        life = lifei;
        while (effects.size() > 0) {
            for (Effect e : effects) {
                e.exitEffect(this);
                break;
            }
        }
        DelayX = 0;
        DelayY = 0;
    }

    public int getLife() {
        return life;
    }

    public void removeEffect(Effect e) {
        effects.remove(e);
    }

    public void addEffect(Effect e) {
        effects.add(e);
        effects.get(effects.size() - 1).enterEffect(this);
    }

    public void addDelayX(double delay) {
        DelayX += delay;
    }

    public double getVxDelay() {
        return (VELOCITYX - DelayX);
    }

    public void addDelayY(double delay) {
        DelayY += delay;
    }

    public double getVyDelay() {
        return (VELOCITYY - DelayY);
    }

    public void jump() {
        jump = true;
    }

    public void setSpeedDirection(double SpeedDirection) {
        this.SpeedDirection = SpeedDirection;
    }

    public boolean isMoving() {
        return moving;
    }

    public boolean isMovingLeft() {
        return vx < 0;
    }

    public boolean isMovingRight() {
        return vx > 0;
    }

}
