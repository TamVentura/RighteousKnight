/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Gr8aX.RighteousKnight;

import com.Gr8aX.RighteousKnight.Inventory.Inventory;

/**
 *
 * @author tiago
 */
public class Player {
    
    public Inventory inventory = new Inventory();
    private int exp = 0;
    private int money = 1000;
    
    public void Init(){
        inventory.Init();
    }
    
}
