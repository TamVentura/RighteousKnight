/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Gr8aX.RighteousKnight.Inventory;

import static java.awt.PageAttributes.MediaType.A;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author tiago
 */
public class ItemList {
    
    public static ArrayList<Item> itemList = new ArrayList<>();
    
    public static void Init(){
        itemList.add(new Item(0,"stone"));
        itemList.add(new Item(1,"wood"));
        itemList.add(new Item(50,"Wooden_Sword",0, Type.weapon, Rarity.common));
    }
    
    public static int getIDFromName(String name){
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).name.equalsIgnoreCase(name)) {
                return itemList.get(i).ID;
            }
        }
        return -1;
    }
    
    public static Item getItemFromID(int ID){
        for (int i = 0; i < itemList.size(); i++) {
            if(itemList.get(i).ID==ID){
                return itemList.get(i);
            }
        }
        return null;
    }
    
}
