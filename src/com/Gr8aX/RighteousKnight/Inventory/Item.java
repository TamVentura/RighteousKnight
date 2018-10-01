/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Gr8aX.RighteousKnight.Inventory;

/**
 *
 * @author tiago
 */

public class Item {
    
    public String name;
    public int tamanhoX, tamanhoY;
    public Rarity rarity = Rarity.ultracommon;
    public int strength;
    Type type;
    public int ID;

    public Item(int ID,String name,int strength, int tamanhoX, int tamanhoY, Type type, Rarity rarity) {
        this.name = name;
        this.tamanhoX = tamanhoX;
        this.strength = strength;
        this.tamanhoY = tamanhoY;
        this.type = type;
        this.ID=ID;
        this.rarity = rarity;
    }
    
    public Item(int ID,String name,int strength, Type type, Rarity rarity) {
        this.name = name;
        this.tamanhoX = 1;
        this.strength = strength;
        this.tamanhoY = 1;
        this.type = type;
        this.rarity = rarity;
        this.ID=ID;
    }
    
    public Item(int ID,String name) {
        this.name = name;
        this.tamanhoX = 1;
        this.tamanhoY = 1;
        this.strength = 1;
        this.type = Type.resources;
        this.ID=ID;
    }
    
    public Item(int ID,String name, int strength) {
        this.name = name;
        this.tamanhoX = 1;
        this.tamanhoY = 1;
        this.strength = strength;
        this.type = Type.resources;
        this.ID=ID;
    }
    
    public Item(int ID,String name, int strength, Type type) {
        this.name = name;
        this.tamanhoX = 1;
        this.tamanhoY = 1;
        this.strength = strength;
        this.type = type;
        this.ID=ID;
    }
    
    public Item(int ID,String name, int strength, Rarity rarity) {
        this.name = name;
        this.tamanhoX = 1;
        this.tamanhoY = 1;
        this.strength = strength;
        this.rarity = rarity;
        this.type = Type.resources;
        this.ID=ID;
    }
}
