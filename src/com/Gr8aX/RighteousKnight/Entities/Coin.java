/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Gr8aX.RighteousKnight.Entities;

import com.Gr8aX.RighteousKnight.rendering.textures.Texture;
import com.Gr8aX.RighteousKnight.world.TileMap;
import java.awt.Rectangle;

/**
 *
 * @author Tiago
 */
public class Coin extends Entity {

    private int valor;

    public Coin(Texture sprite, double x, double y, int valor, TileMap tileMap) {
        super(sprite, x, y, tileMap);
        this.valor = valor;
    }

    @Override
    public void tick() {
        if(getBounds().intersects(tileMap.getPlayer().getBounds())){
            doAction(tileMap.getPlayer());
        }
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, sprite.getWidth(), sprite.getHeight());
    }

    @Override
    public void doAction(Entity p) {
        if (p instanceof Player) {
            ((Player) p).addMoney(valor);
            delete();
        }

    }

    @Override
    public String getName() {
        return "Coin";
    }
}
