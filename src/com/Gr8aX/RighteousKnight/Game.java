/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Gr8aX.RighteousKnight;

import com.Gr8aX.RighteousKnight.Inventory.Inventory;
import static com.Gr8aX.RighteousKnight.Inventory.ItemList.getIDFromName;
import static com.Gr8aX.RighteousKnight.RighteousKnight.frame;
import com.Gr8aX.RighteousKnight.states.MenuState;
import com.Gr8aX.RighteousKnight.input.KeyInput;
import com.Gr8aX.RighteousKnight.input.MouseInput;
import com.Gr8aX.RighteousKnight.states.GameState;
import com.Gr8aX.RighteousKnight.states.InventoryState;
import com.Gr8aX.RighteousKnight.states.OptionState;
import com.Gr8aX.RighteousKnight.states.StateManager;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tiago
 */
public class Game extends Canvas implements Runnable {

    public static final String TITULO = "Righteous Knight";
    public static int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
//        public static int WIDTH = 640;
//    public static int HEIGHT = 360;
    public static final int WIDTHNot = 1280;
    public static final int HEIGHTNot = 720;

    public boolean state = true;

    public static int fpsT = 120;
    public static int tpsT = 120;

    public static final boolean DEBUGGING = false;
    public static int qy = (int) (HEIGHT / 13.5);
    public static int qx = qy;

//    public static final int qx = 80;
//    public static final int qy = 80;
    private boolean running;

    private StateManager stateManager;

    public Game() {
        System.out.println(RighteousKnight.player.inventory.have(getIDFromName("wooden_sword")));
        addKeyListener(new KeyInput());
        MouseInput mi = new MouseInput();
        addMouseListener(mi);
        addMouseMotionListener(mi);
        requestFocus();
        stateManager = new StateManager();
        stateManager.addState(new MenuState());
        stateManager.addState(new GameState(), 1, 5);
        stateManager.addState(new OptionState());
        stateManager.addState(new InventoryState());
        stateManager.setState("menu");
        addMouseWheelListener(mi);
    }

    public void setSizeGame() {
        if (state) {
            WIDTH = WIDTHNot;
            HEIGHT = HEIGHTNot;
        } else {
            WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
            HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
        }
        state = !state;
        Insets insets = frame.getInsets();
        frame.setSize(WIDTH + insets.left + insets.right, HEIGHT + insets.top + insets.bottom);
        qy = (int) (HEIGHT / 13.5);
        qx = qy;
    }

    public void setSizeGame(int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;
        Insets insets = frame.getInsets();
        frame.setSize(WIDTH + insets.left + insets.right, HEIGHT + insets.top + insets.bottom);
        qy = (int) (HEIGHT / 13.5);
        qx = qy;
        System.out.println(qx);
    }

    public void tick() {
        stateManager.tick();
    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        try {
            Graphics g = bs.getDrawGraphics();
            Graphics2D g2 = (Graphics2D) g;

            ///////////////////////////////////////////////////////////////////////////////////////////////
            g2.setColor(Color.cyan);
            g2.fillRect(0, 0, WIDTH, HEIGHT);

            stateManager.render(g2);

            ///////////////////////////////////////////////////////////////////////////////////////////////
            g.dispose();
            bs.show();
        } catch (Exception ex) {
            createBufferStrategy(3);
        }
    }

    protected void stop() {
        if (!running) {
            return;
        }
        running = false;
    }

    @Override
    public void run() {
        running = true;
        double target = 120;
        double nsPerTick = 1000000000.0 / target;
        double targetFrame = 120;
        double nsPerFrame = 1000000000.0 / targetFrame;
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double unprocessed = 0.0;
        double unprocessedFrame = 0.0;
        boolean canrender = false;
        int fps = 0;
        int tps = 0;
        while (running) {
            long now = System.nanoTime();
            unprocessed += (now - lastTime) / nsPerTick;
            unprocessedFrame += (now - lastTime) / nsPerFrame;
            lastTime = now;

            if (unprocessedFrame >= 1.0) {
                unprocessedFrame--;
                canrender = true;
            } else {
                canrender = false;
            }
            if (unprocessed >= 1.0) {
                tick();
                KeyInput.update();
                MouseInput.update();
                unprocessed--;
                tps++;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (canrender) {
                render();
                fps++;
            }

            if (System.currentTimeMillis() - 1000 > timer) {
                timer += 1000;
                fpsT = fps;
                tpsT = tps;
//                System.out.printf("FPS: %d | TPS: %d\n", fps, tps);
                fps = 0;
                tps = 0;
            }

        }

        System.exit(0);
    }

}
