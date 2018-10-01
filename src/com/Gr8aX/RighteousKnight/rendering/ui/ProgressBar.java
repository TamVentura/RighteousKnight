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
public class ProgressBar extends Rectangle{
    
    public int boxX,boxY;
    public int boxWidth,boxHeight;
    public int max, atual;
    public int states;

    public ProgressBar(int x, int y, int width, int height, int max) {
        this.boxX=x;
        this.boxY=y;
        this.boxWidth=width;
        this.boxHeight=height;
        this.max=max;
        this.atual=0;
    }

    public void setAtual(int atual) {
        this.atual = atual;
    }  
    
    public void render(Graphics2D g){
        g.setColor(Color.BLACK);
        g.drawRect(boxX, boxY, boxWidth, boxHeight);
        
        double itemSize = (double)boxWidth/(double)max;
        
        this.x = boxX+1;
        this.y = boxY+1;
        this.width=(int)(atual*itemSize)-1;
        this.height = boxHeight-1;
        g.setColor(Color.CYAN);
        g.fill(this);
    }
    
    
    
}
