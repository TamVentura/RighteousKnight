/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Gr8aX.RighteousKnight;

import static com.Gr8aX.RighteousKnight.Game.HEIGHT;
import static com.Gr8aX.RighteousKnight.Game.TITULO;
import static com.Gr8aX.RighteousKnight.Game.WIDTH;
import com.Gr8aX.RighteousKnight.Inventory.Inventory;
import com.Gr8aX.RighteousKnight.Inventory.ItemList;
import com.Gr8aX.RighteousKnight.audio.MusicPlayer;
import com.Gr8aX.RighteousKnight.utils.ThreadPool;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author Tiago
 */
public class RighteousKnight {

    public static boolean fullscreen;
    public static JFrame frame;
    public static Game game;
    public static Player player = new Player();

    public static void main(String[] args) {
        ItemList.Init();
        player.Init();
        game = new Game();
        ThreadPool pool = new ThreadPool(2);
        setFrame(true);

        MusicPlayer musicPlayer = new MusicPlayer("jogo");
        pool.runTask(musicPlayer);
        pool.runTask(game);
        pool.join();
    }

    public static void setFrame(boolean full) {
        try {
            frame.dispose();
        } catch (Exception ex) {
        }
        frame = new JFrame(TITULO);

        fullscreen = full;

        frame.add(game);

//        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        frame.setSize(WIDTH, HEIGHT);

        frame.setUndecorated(fullscreen);
        frame.setFocusable(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.err.println("A sair do jogo");
                game.stop();
            }
        });
        frame.setVisible(true);
        Insets insets = frame.getInsets();
        frame.setSize(WIDTH + insets.left + insets.right, HEIGHT + insets.top + insets.bottom);
        game.requestFocus();
    }

}
