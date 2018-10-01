/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Gr8aX.RighteousKnight.states;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Tiago
 */
public class StateManager {
    
    private Map<String, State> map;
    private State currentState;
    
    public StateManager(){
        map = new HashMap<String, State>();
    }
    
    public void addState(State state, int modifier1, int modifier2){
        map.put(state.getName().toUpperCase(), state);
        state.init(modifier1,modifier2,this);
        if(currentState == null){
            state.enter();
            currentState = state;
        }
    }
    public void addState(State state){
        map.put(state.getName().toUpperCase(), state);
        state.init(1,1,this);
        if(currentState == null){
            state.enter();
            currentState = state;
        }
    }
    
    public void setState(String name){
        State state = map.get(name.toUpperCase());
        if(state == null){
            System.err.println("No state named <"+name.toUpperCase()+"> found");
            return;
        }
        currentState.exit();
        state.enter();
        currentState = state;
    }
    
    public void removeState(String name){
        State state = map.get(name.toUpperCase());
        if(state == null){
            map.remove(name);
            return;
        }
    }
    
    public void tick(){
        currentState.tick(this);
    }
    public void render(Graphics2D g){
        currentState.render(g);
    }
    
}
