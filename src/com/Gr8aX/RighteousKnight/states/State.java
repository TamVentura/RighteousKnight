/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Gr8aX.RighteousKnight.states;

import java.awt.Graphics2D;

/**
 *
 * @author Tiago
 */
public interface State {
    
    public void init(int modifier1, int modifier2, StateManager stateManager);
    public void enter();
    public void tick(StateManager stateManager);
    public void render(Graphics2D g);
    public void exit();
    public String getName();
    
}
