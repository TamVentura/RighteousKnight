/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Gr8aX.RighteousKnight.rendering.ui;

import static com.Gr8aX.RighteousKnight.Game.WIDTH;
import com.Gr8aX.RighteousKnight.utils.Fonts;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Tiago
 */
public class Button extends Rectangle {

    private Font font, selectedFont;
    private Color color, selectedColor;
    private boolean selected, centeredX;
    private String text;
    private int textY, textX;

    public Button(String text, int textX, int textY, Font font, Font selectedFont, Color color, Color selectedColor) {
        this.font = font;
        this.selectedFont = selectedFont;
        this.color = color;
        this.selectedColor = selectedColor;
        this.text = text;
        this.textY = textY;
        this.textX = textX;
        centeredX = false;
    }

    public Button(String text, int textY, Font font, Font selectedFont, Color color, Color selectedColor) {
        this.font = font;
        this.selectedFont = selectedFont;
        this.color = color;
        this.selectedColor = selectedColor;
        this.text = text;
        this.textY = textY;
        centeredX = true;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void render(Graphics2D g) {
        if (!centeredX) {
            if (selected) {
                Fonts.drawString(g, selectedFont, selectedColor, text, textX, textY);
            } else {
                Fonts.drawString(g, font, color, text, textX, textY);
            }
        } else {
            if (selected) {
                Fonts.drawString(g, selectedFont, selectedColor, text,textY);
            } else {
                Fonts.drawString(g, font, color, text, textY);
            }
        }
        FontMetrics fm = g.getFontMetrics();
        this.x= (WIDTH - fm.stringWidth(text))/2;
        this.y = textY - 2*fm.getHeight()/3;
        this.width = fm.stringWidth(text);
        this.height=fm.getHeight();
//        g.drawRect(x, y, width, height);
    }
    
    public void render(Graphics2D g,int fakeX, int fakeY) {
        
            if (selected) {
                Fonts.drawString(g, selectedFont, selectedColor, text,fakeX, fakeY);
            } else {
                Fonts.drawString(g, font, color, text, fakeX, fakeY);
            }
        
        textY=fakeY;
        textX=fakeX;
        FontMetrics fm = g.getFontMetrics();
        this.x= (WIDTH - fm.stringWidth(text))/2;
        this.y = textY - 2*fm.getHeight()/3;
        this.width = fm.stringWidth(text);
        this.height=fm.getHeight();
//        g.drawRect(x, y, width, height);
    }
    
    public void render(Graphics2D g,int fakeY) {
        
            if (selected) {
                Fonts.drawString(g, selectedFont, selectedColor, text, fakeY);
            } else {
                Fonts.drawString(g, font, color, text, fakeY);
            }
        
        textY=fakeY;
        FontMetrics fm = g.getFontMetrics();
        this.x = (WIDTH - fm.stringWidth(text))/2;
        this.y = textY - 2*fm.getHeight()/3;
        this.width = fm.stringWidth(text);
        this.height=fm.getHeight();
//        g.drawRect(x, y, width, height);
    }
        
        public void reset(){
            textY=-100;
            this.y=-100;
        }

}
