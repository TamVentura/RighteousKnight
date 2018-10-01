/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Gr8aX.RighteousKnight.states;

import com.Gr8aX.RighteousKnight.Entities.Entity;
import com.Gr8aX.RighteousKnight.Entities.Mob;
import static com.Gr8aX.RighteousKnight.Game.HEIGHT;
import static com.Gr8aX.RighteousKnight.Game.WIDTH;
import com.Gr8aX.RighteousKnight.input.KeyInput;
import com.Gr8aX.RighteousKnight.world.TileMap;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 *
 * @author Tiago
 */
public class GameState implements State {

    private int world, level;
    private TileMap tileMap;
    protected boolean pause = false, waspaused = false;

    private Pause pauseState;
    
    @Override
    public void init(int world, int level, StateManager stateManager) {
        this.world = world;
        this.level = level;
        this.pauseState = new Pause(this, stateManager);
        tileMap = new TileMap(world, level, this);
    }

    @Override
    public void enter() {
    }

    public void loadLevel(int world, int level){
        this.world = world;
        this.level = level;
        tileMap = new TileMap(world, level, this);
    }
    
    @Override
    public void tick(StateManager stateManager) {
        
        if (KeyInput.wasRealesed(KeyEvent.VK_ESCAPE) && !pause) {
            pause();
            return;
        }
        if (!pause) {
            tileMap.tick();
        } else {
            pauseState.tick();
        }

    }

    @Override
    public void render(Graphics2D g) {

        tileMap.render(g, WIDTH, HEIGHT);
        if (pause) {
            pauseState.render(g);
        }

    }

    @Override
    public void exit() {
    }

    @Override
    public String getName() {
        return "game";
    }
    
    public void reload(){
       
    }

    public void restart() {
        for (Entity e : tileMap.getEntities()) {
            e.restart();
        }
        for (Mob e : tileMap.getMobs()) {
            e.restart();
        }
        tileMap.getPlayer().restart();
        pause = false;
        waspaused = false;
    }

    public void play() {
        pause = false;
        waspaused = false;
    }

    public void pause() {
        pause = true;
    }

    public void restartSize() {
         tileMap = new TileMap(world, level, this);
    }

}
