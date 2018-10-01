/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Gr8aX.RighteousKnight.rendering.textures;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Tiago
 */
public class Texture {
    
    private final static Map<String, BufferedImage> texMap = new HashMap<String, BufferedImage>();
    private BufferedImage image;
    private String filename;
    private int width, height; 
    
    public static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
    
    public Texture(String file, int width, int height){
        filename = file;
        BufferedImage oldTexture = texMap.get(file);
        if (oldTexture != null) {
            this.image = toBufferedImage(oldTexture.getScaledInstance(width, height, 1));
        }else{
            try {
                this.image = toBufferedImage(ImageIO.read(new File("./resources/textures/Images/" + file + ".png")).getScaledInstance(width, height, 1));
                texMap.put(file, this.image);
            } catch (IOException ex) {
                Logger.getLogger(Texture.class.getName()).log(Level.SEVERE, null, ex);
            }
        }      
        this.width=image.getWidth();
        this.height = image.getHeight();
    }
    
    public Texture(Texture spriteSheet, int x, int y, int size){
        this(spriteSheet,x,y,size,size);
    }
    
    public Texture(Texture spriteSheet,int x, int y, int width, int height){
        this.width=width;
        this.height = height;
        String key = spriteSheet.filename +"_"+x+"_"+y;
        BufferedImage old = texMap.get(key);
        if(old != null){
            this.image = toBufferedImage(old.getScaledInstance(width, height, 1));
        }
        this.image = spriteSheet.image.getSubimage((x*width)-width, (y*height)-height, width, height);
    }
    
    public void render(Graphics2D g, double x, double y){
        g.drawImage(image, (int) x, (int) y, null);
    }
    
    public BufferedImage getImage(){
        return image;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    public void setSize(int width, int height){
        this.image = toBufferedImage(this.image.getScaledInstance(width, height, 1));
    }
    
    public void render(Graphics2D g, int destX1,int destX2, int srcX1,int srcX2, int y){
        g.drawImage(image, destX1, y, destX2, y+height,srcX1,0,srcX2,height,null);
    }    
}
