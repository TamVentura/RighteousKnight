/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Gr8aX.RighteousKnight.Effects;

import com.Gr8aX.RighteousKnight.Entities.Mob;
import java.awt.Graphics2D;

/**
 *
 * @author tiago
 */
public interface Effect {

    public void doEffect(Mob p);
    public void tick(Mob p);
    public void exitEffect(Mob p);
    public void enterEffect(Mob p);
    public String getName();
    
}
