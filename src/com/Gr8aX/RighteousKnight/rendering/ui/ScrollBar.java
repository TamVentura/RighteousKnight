/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Gr8aX.RighteousKnight.rendering.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author tiago
 */
public class ScrollBar extends Rectangle{
    
    public int boxX,boxY;
    public int boxWidth,boxHeight;
    public int nItems, selectedItem, itemsPP;
    public int states;

    public ScrollBar(int x, int y, int width, int height, int nItems, int itemsPP) {
        this.boxX=x;
        this.boxY=y;
        this.boxWidth=width;
        this.boxHeight=height;
        this.nItems=nItems;
        this.selectedItem=0;
        this.itemsPP=itemsPP;
    }

    public void setSelectedItem(int selectedItem) {
        this.selectedItem = selectedItem;
    }

    public void setnItems(int nItems) {
        this.nItems = nItems;
    }

    public void setItemsPP(int itemsPP) {
        this.itemsPP = itemsPP;
    }
    
    
     
    public void render(Graphics2D g){
        g.setColor(Color.WHITE);
        g.drawRect(boxX, boxY, boxWidth, boxHeight);
        
        int itemSize = boxHeight/nItems;
        
        this.x = boxX;
        this.y = boxY+itemSize*selectedItem;
        this.width=boxWidth;
        this.height = itemsPP*itemSize;
        
        g.fill(this);
    }
    
    
    
}
