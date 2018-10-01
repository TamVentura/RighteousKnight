/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Gr8aX.RighteousKnight.Entities;

import com.Gr8aX.RighteousKnight.Effects.Effect;
import com.Gr8aX.RighteousKnight.rendering.textures.Animation;
import com.Gr8aX.RighteousKnight.rendering.textures.Texture;
import com.Gr8aX.RighteousKnight.world.TileMap;
import java.util.ArrayList;

/**
 *
 * @author tiago
 */
public class Attack extends Mob {

    int damage;
    Entity shooter;
    boolean Destroyed;
    ArrayList<Effect> effects;

    public Attack(Texture sprite, double x, double y, TileMap tileMap, String name, int damage, Entity shooter, boolean gravity, ArrayList<Effect> effects) {
        super(sprite, x, y, tileMap, null);
        this.damage = damage;
        vx = 20;
        vy = -20;
        this.shooter = shooter;
        this.effects = effects;
    }

    public boolean isDestroyed() {
        return Destroyed;
    }

    @Override
    public void tick() {
        super.tick(); //To change body of generated methods, choose Tools | Templates.
        if (hasVerticalColision()) {
            vx = 0;
        }
        if (hasHorizontalColision()) {
            vy = 0;
        }
        for (Entity e : tileMap.getEntities()) {
            if (getBounds().intersects(e.getBounds())) {
                if (e instanceof Mob) {
                    doAction(e);
                }
            }
        }
        if (getBounds().intersects(tileMap.getPlayer().getBounds())) {
            doAction(tileMap.getPlayer());
        }
    }

    public void doAction(Entity p) {
        if (shooter != p) {
            this.delete();
            for (Effect e : effects) {
                ((Mob) p).addEffect(e);
            }
        }
    }

}
