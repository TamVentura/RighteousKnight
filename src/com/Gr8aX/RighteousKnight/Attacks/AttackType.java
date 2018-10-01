/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Gr8aX.RighteousKnight.Attacks;

import com.Gr8aX.RighteousKnight.Effects.Effect;
import com.Gr8aX.RighteousKnight.Entities.Attack;
import com.Gr8aX.RighteousKnight.Entities.Entity;
import static com.Gr8aX.RighteousKnight.Game.qx;
import com.Gr8aX.RighteousKnight.rendering.textures.Texture;
import com.Gr8aX.RighteousKnight.world.TileMap;
import java.util.ArrayList;

/**
 *
 * @author tiago
 */
public class AttackType {
    
    private Texture sprite;
    private int damage;
    private boolean gravityEffect;
    private TileMap tileMap;
    ArrayList<Effect> effects;

    public AttackType(Texture sprite, int damage, boolean gravityEffect, TileMap tileMap, ArrayList<Effect> effects) {
        this.sprite = sprite;
        this.damage = damage;
        this.gravityEffect = gravityEffect;
        this.tileMap = tileMap;
        this.effects = effects;
    }  
    public void launch(Entity e){
        tileMap.addMob(new Attack(sprite,e.getX(),e.getY()-3*qx,tileMap,"nome",1,e,gravityEffect,effects));
    }
}
