/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Gr8aX.RighteousKnight.states;

import com.Gr8aX.RighteousKnight.Game;
import static com.Gr8aX.RighteousKnight.Game.HEIGHT;
import static com.Gr8aX.RighteousKnight.Game.WIDTH;
import com.Gr8aX.RighteousKnight.RighteousKnight;
import com.Gr8aX.RighteousKnight.input.KeyInput;
import com.Gr8aX.RighteousKnight.rendering.textures.Texture;
import com.Gr8aX.RighteousKnight.utils.Fonts;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 *
 * @author Tiago
 */
public class OptionState implements State {

    private final String[] options = {"Resolução", "Fullscreen", "Voltar"};
    private int currentSelection;
    Texture t;

    public void render(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        
        
//        t.render(g, 0, 0);
        
        Fonts.drawString(g, new Font("RighteousGame", Font.PLAIN, Game.HEIGHT / 10), Color.red, Game.TITULO, Game.HEIGHT / 5);

        for (int i = 0; i < options.length; i++) {
            if (i == currentSelection) {
                Fonts.drawString(g, new Font("RighteousGame", Font.PLAIN, Game.HEIGHT / 20), Color.YELLOW, options[i], Game.HEIGHT / 2+ i*Game.HEIGHT / 5);
            }
            if (i != currentSelection) {
                Fonts.drawString(g, new Font("RighteousGame", Font.PLAIN, Game.HEIGHT / 30), Color.WHITE, options[i], Game.HEIGHT / 2+ i*Game.HEIGHT / 5);
            }
        }
    }
    
    public boolean canSelectBack(){
        return currentSelection!=0;
    } 
    public boolean canSelectNext(){
        return currentSelection!=options.length-1;
    } 
    
    public void selectBack(){
        if(canSelectBack()){
            currentSelection--;
        }
    }
    
    public void selectNext(){
        if(canSelectNext()){
            currentSelection++;
        }
    }
    
    public void select(StateManager stateManager){
        switch(currentSelection)
        {
            case 0:
                RighteousKnight.game.setSizeGame();
                break;
            case 1:
                RighteousKnight.setFrame(!RighteousKnight.fullscreen);
                break;
            case 2:
                stateManager.setState("menu");
                break;
                
        }
    }

    @Override
    public void init(int mod1,int mod2, StateManager stateManager) {
            t = new Texture("Loading", WIDTH, HEIGHT);
    }

    @Override
    public void tick(StateManager stateManager) {
        if(KeyInput.wasPressed(KeyEvent.VK_DOWN)){
            selectNext();
        }
        if(KeyInput.wasPressed(KeyEvent.VK_UP)){
            selectBack();
        }
        if(KeyInput.wasPressed(KeyEvent.VK_ENTER)){
            select(stateManager);
        }
    }

    @Override
    public void exit() {
    }

    @Override
    public String getName() {
        return "options";
    }

    @Override
    public void enter() {

    }
    
    

}
