/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Gr8aX.RighteousKnight.Entities;

import com.Gr8aX.RighteousKnight.rendering.textures.Animation;
import com.Gr8aX.RighteousKnight.rendering.textures.Texture;
import com.Gr8aX.RighteousKnight.world.TileMap;

/**
 *
 * @author tiago
 */
public class Monsters extends Mob{

    public Monsters(Texture sprite, double x, double y, TileMap tileMap, Animation anime) {
        super(sprite, x, y, tileMap, anime);
    }

    @Override
    public void doAction(Entity p) {
    }
    
}
