/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Gr8aX.RighteousKnight.utils;

import com.Gr8aX.RighteousKnight.Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

/**
 *
 * @author Tiago
 */
public class Fonts {

    public static void drawString(Graphics g, Font f, Color c, String text, int x, int y) {
        g.setColor(c);
        g.setFont(f);
        g.drawString(text, x, y);
    }

    public static void drawString(Graphics g, Font f, Color c, String text) {
        FontMetrics fm = g.getFontMetrics(f);
        int x = (Game.WIDTH - fm.stringWidth(text)) / 2;
        int y = (Game.HEIGHT - fm.getHeight()) / 2 + fm.getAscent();
        drawString(g, f, c, text, x, y);
    }
    public static void drawString(Graphics g, Font f, Color c, String text,boolean hor) {
        FontMetrics fm = g.getFontMetrics(f);
        int x = 2;
        int y = fm.getHeight();
        drawString(g, f, c, text, x, y);
    }
    
    public static void drawString(Graphics g, Font f, Color c, String text, double x) {
        FontMetrics fm = g.getFontMetrics(f);        
        int y = (Game.HEIGHT - fm.getHeight()) / 2 + fm.getAscent();
        drawString(g, f, c, text, (int)x, y);
    }
    
    public static void drawString(Graphics g, Font f, Color c, String text, int y) {
        FontMetrics fm = g.getFontMetrics(f);
        int x = (Game.WIDTH - fm.stringWidth(text)) / 2;       
        drawString(g, f, c, text, x, y);
    }
    
    public static int getX1(Graphics g,Font f, String text){
         FontMetrics fm = g.getFontMetrics(f);
        return (Game.WIDTH - fm.stringWidth(text)) / 2;
    }
    public static int getWidth(Graphics g,Font f, String text){
         FontMetrics fm = g.getFontMetrics(f);
        return fm.stringWidth(text);
    }
}
