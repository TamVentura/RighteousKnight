/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Gr8aX.RighteousKnight.states;

import com.Gr8aX.RighteousKnight.Game;
import static com.Gr8aX.RighteousKnight.Game.HEIGHT;
import static com.Gr8aX.RighteousKnight.Game.WIDTH;
import com.Gr8aX.RighteousKnight.Inventory.ItemList;
import static com.Gr8aX.RighteousKnight.RighteousKnight.player;
import com.Gr8aX.RighteousKnight.input.KeyInput;
import com.Gr8aX.RighteousKnight.input.MouseInput;
import com.Gr8aX.RighteousKnight.rendering.textures.Texture;
import com.Gr8aX.RighteousKnight.rendering.ui.Button;
import com.Gr8aX.RighteousKnight.utils.Fonts;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author Tiago
 */
public class InventoryState implements State {

    private final ArrayList<Button> options =new ArrayList<>();
    private int currentSelection;
    Texture t;

    public InventoryState() {
        Font fontS = new Font("RighteousGame", Font.PLAIN, Game.HEIGHT / 20);
        Font font = new Font("RighteousGame", Font.PLAIN, Game.HEIGHT / 30);
        for (int i = 0; i < ItemList.itemList.size(); i++) {
            if (player.inventory.have(i)) {
                options.add(new Button(ItemList.itemList.get(i).name + " " + ItemList.itemList.get(i).rarity, WIDTH, font, font, Color.yellow, Color.yellow));
            }
        }
    }

    public void render(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

//        t.render(g, 0, 0);
        Fonts.drawString(g, new Font("RighteousGame", Font.PLAIN, Game.HEIGHT / 10), Color.red, Game.TITULO, Game.HEIGHT / 5);

        for (int i = 0; i < options.size(); i++) {
            if (i == currentSelection) {
                options.get(i).setSelected(true);      
            }
            if (i != currentSelection) {
                options.get(i).setSelected(false);
            }
            options.get(i).render(g);
        }
    }

    public boolean canSelectBack() {
        return currentSelection != 0;
    }

    public boolean canSelectNext() {
        return currentSelection != options.size() - 1;
    }

    public void selectBack() {
        if (canSelectBack()) {
            currentSelection--;
        }
    }

    public void selectNext() {
        if (canSelectNext()) {
            currentSelection++;
        }
    }

    public void select(StateManager stateManager) {
        switch (currentSelection) {
            case 0:
                stateManager.setState("game");
                break;
            case 1:
                stateManager.setState("options");
                break;
            case 2:
                System.exit(0);
                break;

        }
    }

    @Override
    public void init(int mod1, int mod2, StateManager stateManager) {
        t = new Texture("Loading", WIDTH, HEIGHT);
    }

    @Override
    public void tick(StateManager stateManager) {
        
        
        boolean clicked = false;
        for (int i = 0; i < options.size(); i++) {
            if(options.get(i).intersects(new Rectangle(MouseInput.getX(),MouseInput.getY(),1,1))){
                if (MouseInput.isMoving()) {
                    currentSelection = i;
                }
                clicked = MouseInput.wasPressed(MouseEvent.BUTTON1);
            }
        }
        
        if (KeyInput.wasPressed(KeyEvent.VK_DOWN)) {
            selectNext();
        }
        if (KeyInput.wasPressed(KeyEvent.VK_UP)) {
            selectBack();
        }
        if (KeyInput.wasPressed(KeyEvent.VK_ENTER)||clicked) {
            select(stateManager);
        }
        Font font = new Font("RighteousGame", Font.PLAIN, Game.HEIGHT / 30);
        options.clear();
        for (int i = 0; i < ItemList.itemList.size(); i++) {
            if (player.inventory.have(ItemList.itemList.get(i).ID)) {
                options.add(new Button(ItemList.itemList.get(i).name + " " + ItemList.itemList.get(i).rarity, Game.HEIGHT/2, font, font, Color.yellow, Color.yellow));
            }
        }
    }

    @Override
    public void exit() {
    }

    @Override
    public String getName() {
        return "inventario";
    }

    @Override
    public void enter() {

    }

}
