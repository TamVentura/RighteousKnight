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
import com.Gr8aX.RighteousKnight.input.MouseInput;
import com.Gr8aX.RighteousKnight.rendering.textures.Texture;
import com.Gr8aX.RighteousKnight.rendering.ui.Button;
import com.Gr8aX.RighteousKnight.rendering.ui.ScrollBar;
import com.Gr8aX.RighteousKnight.utils.Fonts;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author Tiago
 */
public class Pause {

//    private final String[] options = {"Continuar", "Opções", "Saír"};
//    private final String[] Resolution = {"1920*1080","1280*720", "1600*900", "800*600", "400*300", "Voltar"};
    private final Button[] options;
    private final Button[] resolution;
    private Button[] selected;
    private int selectedLevel = 0;
    private int currentSelection;
    Texture t;
    private GameState state;
    int istart = 2;
    private StateManager stateManager;
    private ScrollBar scroll;

    public Pause(GameState state, StateManager stateManager) {
        this.state = state;
        this.stateManager = stateManager;
        options = new Button[4];
        Font fontS = new Font("RighteousGame", Font.PLAIN, Game.HEIGHT / 20);
        Font font = new Font("RighteousGame", Font.PLAIN, Game.HEIGHT / 30);
        options[0] = new Button("Continuar", Game.HEIGHT / 2, font, fontS, Color.white, Color.yellow);
        options[1] = new Button("Opções", Game.HEIGHT / 2 + Game.HEIGHT / 7, font, fontS, Color.white, Color.yellow);
        options[2] = new Button("Saír", Game.HEIGHT / 2 + 2 * Game.HEIGHT / 7, font, fontS, Color.white, Color.yellow);
        options[3] = new Button("Menu inicial", Game.HEIGHT / 2 + 2 * Game.HEIGHT / 7, font, fontS, Color.white, Color.yellow);
        resolution = new Button[6];
        resolution[0] = new Button("1920*1080", Game.HEIGHT / 2, font, fontS, Color.white, Color.yellow);
        resolution[1] = new Button("1280*720", Game.HEIGHT / 2, font, fontS, Color.white, Color.yellow);
        resolution[2] = new Button("1600*900", Game.HEIGHT / 2, font, fontS, Color.white, Color.yellow);
        resolution[3] = new Button("800*600", Game.HEIGHT / 2, font, fontS, Color.white, Color.yellow);
        resolution[4] = new Button("400*300", Game.HEIGHT / 2, font, fontS, Color.white, Color.yellow);
        resolution[5] = new Button("Voltar", Game.HEIGHT / 2, font, fontS, Color.white, Color.yellow);
        selected = options;
        scroll = new ScrollBar(13 * Game.WIDTH / 20, 8 * Game.HEIGHT / 20, 10, 8 * Game.HEIGHT / 20, 4, 4);
    }

    public void render(Graphics2D g) {
        g.setColor(Color.blue);
        g.fillRect(
                Fonts.getX1(g, new Font("RighteousGame", Font.PLAIN, Game.HEIGHT / 10), "Pausa") - Game.HEIGHT / 20,
                (2 * Game.HEIGHT / 8) - Game.HEIGHT / 7,
                Fonts.getWidth(g, new Font("RighteousGame", Font.PLAIN, Game.HEIGHT / 10), "Pausa") + Game.HEIGHT / 10,
                HEIGHT - 2 * ((2 * Game.HEIGHT / 8) - Game.HEIGHT / 7));

        Fonts.drawString(g, new Font("RighteousGame", Font.PLAIN, Game.HEIGHT / 10), Color.red, "Pausa", 2 * Game.HEIGHT / 8);

        for (Button selected1 : selected) {
            selected1.reset();
        }

        for (int i = 0; i < 4; i++) {
            if (istart - 2 + i == currentSelection) {
                selected[istart - 2 + i].setSelected(true);
                selected[istart - 2 + i].render(g, 9 * Game.HEIGHT / 20 + i * Game.HEIGHT / 8);
            }
            if (istart - 2 + i != currentSelection) {
                selected[istart - 2 + i].setSelected(false);
                selected[istart - 2 + i].render(g, 9 * Game.HEIGHT / 20 + i * Game.HEIGHT / 8);
            }
        }
        scroll.render(g);
    }

    public boolean canSelectBack() {
        return currentSelection != 0;
    }

    public boolean canSelectNext() {
        return currentSelection != selected.length - 1;
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

    public void exitGame() {
        System.exit(0);
    }

    public void continuar() {
        state.play();
        reset();
    }

    public void options() {
        //TODO
    }

    public void resolution(int level) {
        selected = resolution;
        selectedLevel = level;
        scroll.setnItems(6);
    }

    public void back() {
        if (selectedLevel <= 0) {
            continuar();
        } else {
            selectedLevel--;
        }
        scroll.setnItems(4);
        scroll.setSelectedItem(0);
        currentSelection = 0;
        istart = 2;
        selected = options;

    }

    public void reset() {
        currentSelection = 0;
        istart = 2;
        selected = options;
        selectedLevel = 0;
    }

    public void selectResolution() {
        switch (currentSelection) {
            case 0:
                RighteousKnight.game.setSizeGame(1920, 1080);
                state.restartSize();
                break;
            case 1:
                RighteousKnight.game.setSizeGame(1280, 720);
                state.restartSize();
                break;
            case 2:
                RighteousKnight.game.setSizeGame(1600, 900);
                state.restartSize();
                break;
            case 3:
                RighteousKnight.game.setSizeGame(800, 600);
                state.restartSize();
                break;
            case 4:
                RighteousKnight.game.setSizeGame(400, 300);
                state.restartSize();
                break;
            case 5:
                back();
                break;
        }
    }

    public void select() {
        switch (currentSelection) {
            case 0:
                continuar();
                break;
            case 1:
                resolution(1);
                break;
            case 2:
                exitGame();
                break;
            case 3:
                state.restart();
                stateManager.setState("Menu");
//                state.loadLevel(1, 2);
//                continuar();
                break;
        }
    }

    public void init(int mod1, int mod2) {
        t = new Texture("Loading", WIDTH, HEIGHT);
    }

    public void tick() {

        boolean clicked = false;
        for (int i = 0; i < selected.length; i++) {
            if (selected[i].intersects(new Rectangle(MouseInput.getX(), MouseInput.getY(), 1, 1))) {
                if (MouseInput.isMoving()) {
                    currentSelection = i;
                }
                clicked = MouseInput.wasPressed(MouseEvent.BUTTON1);
            }

        }

        if (KeyInput.wasRealesed(KeyEvent.VK_DOWN)) {
            selectNext();

            istart = Math.min(currentSelection, selected.length - 2);
            if (istart <= 1) {
                istart = 2;
            }

        }
        if (KeyInput.wasRealesed(KeyEvent.VK_UP)) {
            selectBack();

            istart = Math.min(currentSelection, selected.length - 2);
            if (istart <= 1) {
                istart = 2;
            }

        }
        if (KeyInput.wasRealesed(KeyEvent.VK_ENTER) || clicked) {
            if (selectedLevel == 0) {
                select();
            } else if (selectedLevel == 1) {
                selectResolution();
            }
            currentSelection = 0;
        }
        if (KeyInput.wasRealesed(KeyEvent.VK_ESCAPE)) {
            back();
        }

        if (MouseInput.getNotches() >= 1) {
            istart++;
            istart = Math.min(istart, selected.length - 2);
            if (istart <= 1) {
                istart = 2;
            }
        }
        if (MouseInput.getNotches() <= -1) {
            istart--;
            istart = Math.min(istart, selected.length - 2);
            if (istart <= 1) {
                istart = 2;
            }
        }
        scroll.setSelectedItem(istart - 2);
        MouseInput.updateNotches();
    }

}
