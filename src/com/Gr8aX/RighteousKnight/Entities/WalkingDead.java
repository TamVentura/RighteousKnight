/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Gr8aX.RighteousKnight.Entities;

import static com.Gr8aX.RighteousKnight.Game.qx;
import com.Gr8aX.RighteousKnight.rendering.textures.Animation;
import com.Gr8aX.RighteousKnight.rendering.textures.Texture;
import com.Gr8aX.RighteousKnight.world.TileMap;

/**
 *
 * @author tiago
 */
public class WalkingDead extends Monsters {

    public WalkingDead(Texture sprite, double x, double y, TileMap tileMap, Animation anime) {
        super(sprite, x, y, tileMap, anime);
        tileMap.addMob(this);
        hasGravity = true;
    }

    @Override
    public void tick() {
        vx = 0;
        if (tileMap.getPlayer().getBottomMax().intersects(this.getTop())&&tileMap.getPlayer().isFalling()) {
            tileMap.getPlayer().setY(this.y-tileMap.getPlayer().getHeight());
            tileMap.getPlayer().goJump();
        }else if(tileMap.getPlayer().getBounds().intersects(this.getBounds())){
            tileMap.getPlayer().doDamage(10);
            tileMap.getPlayer().setX(this.x-2*qx);
        }

        super.tick(); //To change body of generated methods, choose Tools | Templates.
    }

}
