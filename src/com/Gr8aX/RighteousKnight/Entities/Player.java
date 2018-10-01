/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Gr8aX.RighteousKnight.Entities;

import com.Gr8aX.RighteousKnight.Attacks.AttackType;
import com.Gr8aX.RighteousKnight.Effects.SlowEffect;
import com.Gr8aX.RighteousKnight.Effects.BurnEffect;
import com.Gr8aX.RighteousKnight.Effects.DirectionEffect;
import com.Gr8aX.RighteousKnight.Effects.Effect;
import com.Gr8aX.RighteousKnight.Effects.JumpBoostEffect;
import com.Gr8aX.RighteousKnight.Effects.SpeedEffect;
import com.Gr8aX.RighteousKnight.Game;
import static com.Gr8aX.RighteousKnight.Game.qx;
import static com.Gr8aX.RighteousKnight.Game.qy;
import com.Gr8aX.RighteousKnight.input.KeyInput;
import static com.Gr8aX.RighteousKnight.input.Keys.KEY_CROUCH;
import static com.Gr8aX.RighteousKnight.input.Keys.KEY_JUMP;
import static com.Gr8aX.RighteousKnight.input.Keys.KEY_LEFT;
import static com.Gr8aX.RighteousKnight.input.Keys.KEY_RIGHT;
import com.Gr8aX.RighteousKnight.rendering.textures.Animation;
import com.Gr8aX.RighteousKnight.rendering.textures.Texture;
import com.Gr8aX.RighteousKnight.world.TileMap;
import com.sun.glass.events.KeyEvent;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Tiago
 */
public class Player extends Mob {

    protected int mana = 200;
    protected int manaTick;
    protected AttackType atck1;

    private int money = 0;

    public Player(Texture sprite, double x, double y, TileMap tileMap) {
        super(sprite, x, y, tileMap,
                new Animation(0.1, new Texture(new Texture("animation", 3 * qx, 4 * qy), 1, 1, qx, 2 * qy),
                        new Texture(new Texture("animation", 3 * qx, 4 * qy), 2, 1, qx, 2 * qy),
                        new Texture(new Texture("animation", 3 * qx, 4 * qy), 3, 1, qx, 2 * qy),
                        new Texture(new Texture("animation", 3 * qx, 4 * qy), 1, 2, qx, 2 * qy),
                        new Texture(new Texture("animation", 3 * qx, 4 * qy), 2, 2, qx, 2 * qy),
                        new Texture(new Texture("animation", 3 * qx, 4 * qy), 3, 2, qx, 2 * qy)));
        this.xi = (int) x;
        this.yi = (int) y;
        super.name = "Player";
        this.life = 1000;
        this.lifei = 1000;
        ArrayList<Effect> atckEffects = new ArrayList<>();
        atckEffects.add(new BurnEffect(5, 50, false));
        atckEffects.add(new SlowEffect(5, 4.5, false));
        atckEffects.add(new JumpBoostEffect(5, 5, false));
        atck1 = new AttackType(new Texture("flecha", qx, qy / 3), 1, true, tileMap, atckEffects);
    }

    @Override
    public void tick() {

        manaTick++;
        if (manaTick >= 120 / 2) {
            mana++;
            manaTick = 0;
            if (mana > 200) {
                mana = 200;
            }
        }

        vx = 0;
        jump = false;
        if (KeyInput.isDown(KEY_JUMP)) {
            jump();
        }
        if (KeyInput.isDown(KEY_CROUCH)) {
            crouch();
        }
        if (KeyInput.isDown(KEY_LEFT) && !KeyInput.isDown(KEY_RIGHT)) {
            vx = -SpeedDirection * (((VELOCITYX - DelayX) * qx) / 120);
        }
        if (KeyInput.isDown(KEY_RIGHT) && !KeyInput.isDown(KEY_LEFT)) {
            vx = SpeedDirection * (((VELOCITYX - DelayX) * qx) / 120);
        }
        if (KeyInput.wasPressed(KeyEvent.VK_O)) {
            atck1.launch(this);
        }
        if (KeyInput.wasPressed(KeyEvent.VK_1)) {
            if (mana >= 10) {
                addEffect(new SlowEffect(10, 2, false));
                addMana(-10);
            }
        }
        if (KeyInput.wasPressed(KeyEvent.VK_2)) {
            if (mana >= 50) {
                addEffect(new BurnEffect(10, 50, false));
                addMana(-50);
            }
        }
        if (KeyInput.wasPressed(KeyEvent.VK_3)) {
            if (mana >= 100) {
                addEffect(new SpeedEffect(10, 2, false));
                addMana(-100);
            }
        }
        if (KeyInput.wasPressed(KeyEvent.VK_4)) {
            if (mana >= 100) {
                addEffect(new JumpBoostEffect(10, 0.5, false));
                addMana(-100);
            }
        }
        if (KeyInput.wasPressed(KeyEvent.VK_5)) {
            if (mana >= 20) {
                addEffect(new DirectionEffect(10, 2, false));
                addMana(-20);
            }
        }
//        if(KeyInput.wasRealesed(KEY_JUMP)||KeyInput.wasRealesed(KEY_CROUCH))vy=0;

//        if(hasVerticalCollision()){
//            vy = 0;
//        }
        if (KeyInput.wasPressed(KeyEvent.VK_ENTER)) {
            kill();
        }

        super.tick();
    }

    public String getName() {
        return name;
    }

    public void addMoney(int valor) {
        money += valor;
        System.err.println(money);
    }

    @Override
    public void doAction(Entity p) {
    }

    @Override
    public void kill() {
        super.kill(); //To change body of generated methods, choose Tools | Templates.
    }

    public void render(Graphics2D g, int offsetX, int offsetY) {
        super.render(g, offsetX, offsetY);
    }

    public Rectangle getBottomMax() {
        return new Rectangle((int) x + qx / 15, (int) y + getHeight() - (qy / 5) + (int) vy, getWidth() - qx / 15 - qx / 15, qy / 5);
    }

    public int getMana() {
        return mana;
    }

    public void addMana(int quantity) {
        mana += quantity;
    }
    
    public void reflect(){
        SpeedDirection = -1;
    }
    
    public void direct(){
        SpeedDirection = 1;
    }

}
