/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Gr8aX.RighteousKnight.utils;

/**
 *
 * @author tiago
 */
public class IDAssigner {
    
    private int baseID;

    public IDAssigner(int baseID) {
        this.baseID = baseID;
    }
    
    public int next(){
        return baseID++;
    }
    
    public int getCurrentID(){
        return baseID;
    }
        
}
