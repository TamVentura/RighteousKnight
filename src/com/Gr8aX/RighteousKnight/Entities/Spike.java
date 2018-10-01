/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Gr8aX.RighteousKnight.Entities;

import com.Gr8aX.RighteousKnight.rendering.textures.Texture;
import com.Gr8aX.RighteousKnight.world.TileMap;
import java.awt.Point;

/**
 *
 * @author Tiago
 */
public class Spike extends Entity {

    public Spike(Texture sprite, double x, double y, TileMap tileMap) {
        super(sprite, x, y, tileMap);
    }

    @Override
    public void tick() {
        if (isInTouch(tileMap.getPlayer())) {
            doAction(tileMap.getPlayer());
        }
    }

    public boolean isInTouch(Mob p) {
        Point menor = null;
        Point maior = null;

        menor = new Point((int)getX()+5, (int)getY());
        maior = new Point((int)getX() + getWidth()/2, (int)getY() + getHeight());
        if(p.getBounds().intersectsLine(menor.x, menor.y, maior.x, maior.y)){
            return true;
        }
        menor = new Point((int)getX() + getWidth()/2, (int)getY() + getHeight());
        maior = new Point((int)getX()+ getWidth()-5, (int)getY());
        if(p.getBounds().intersectsLine(menor.x, menor.y, maior.x, maior.y)){
            return true;
        }

        return false;
    }

    @Override
    public void doAction(Entity p) {
        if (p instanceof Player) {
            ((Mob) p).kill();
        }
    }

    public void render(){
        
    }
    
    @Override
    public String getName() {
        return "Spike";
    }
}
