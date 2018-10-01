/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Gr8aX.RighteousKnight.world;

import com.Gr8aX.RighteousKnight.rendering.textures.Texture;

/**
 *
 * @author Aluno
 */
public class Trampoline extends Tile{
    
    public Trampoline(Texture texture, boolean solid, float x, float y , TileMap tileMap) {
        super(texture, solid, x, y,tileMap);
    }
    
    @Override
    public void tick(){
        if(tileMap.getPlayer().getBottomMax().intersects(getTop())){
            tileMap.getPlayer().goJump(50);
        }
    }
        
}
