/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Gr8aX.RighteousKnight.Inventory;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author tiago
 */
public class Inventory {

    //Lista ID-->Quantity
    public static Map<Integer, Integer> inventoryList = new HashMap<>();

    public void Init() {
        for (int i = 0; i < ItemList.itemList.size(); i++) {
            addItem(ItemList.itemList.get(i).ID, 0);
        }
        set(ItemList.getIDFromName("wooden_sword"), 20);
    }

    public static void addItem(int ID, int quantity) {
        try {
            inventoryList.putIfAbsent(ID, quantity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean add(int ID, int quantity) {
        if (ItemList.getItemFromID(ID).type == Type.resources) {
            inventoryList.replace(ID, getQuantity(ID) + quantity);
            return true;
        }
        return false;
    }

    public boolean set(int ID, int quantity) {

        inventoryList.replace(ID, quantity);
        return true;

    }

    public boolean use(int ID, int quantity) {
        if (ItemList.getItemFromID(ID).type == Type.resources) {
            inventoryList.replace(ID, getQuantity(ID) - quantity);
            return true;
        }
        return false;
    }

    public boolean have(int ID) {
        return getQuantity(ID) != 0;
    }

    public int getQuantity(int ID) {
        return inventoryList.getOrDefault(ID, 0);
    }

}
