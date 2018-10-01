/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Gr8aX.RighteousKnight.world;

import com.Gr8aX.RighteousKnight.Entities.Coin;
import com.Gr8aX.RighteousKnight.Entities.Entity;
import com.Gr8aX.RighteousKnight.Entities.Mob;
import com.Gr8aX.RighteousKnight.Entities.Player;
import com.Gr8aX.RighteousKnight.Entities.Spike;
import com.Gr8aX.RighteousKnight.Game;
import static com.Gr8aX.RighteousKnight.Game.qx;
import static com.Gr8aX.RighteousKnight.Game.qy;
import com.Gr8aX.RighteousKnight.rendering.ParallaxEngine;
import com.Gr8aX.RighteousKnight.rendering.ParallaxLayer;
import com.Gr8aX.RighteousKnight.rendering.textures.Texture;
import com.Gr8aX.RighteousKnight.rendering.ui.ProgressBar;
import com.Gr8aX.RighteousKnight.states.GameState;
import com.Gr8aX.RighteousKnight.utils.Fonts;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author Tiago
 */
public class TileMap {

    private static final int TILE_SIZE = qx;
    private static final int TILE_SIZE_BITS = -1;

    private static final int spriteXSize = 4;
    private static final int spriteYSize = 6;

    private int mundo, nivel;

    private int width, height;

    private Texture tileSheet;
    private Tile[] tiles;

    private Player player;

    private Tile[][][] PreTileSet;

    private ArrayList<Entity> entities;
    private ArrayList<Mob> mobs;

    private GameState state;

    private ParallaxEngine parallaxEngine;

    private int oldOffsetX, oldOffsetY;

    private ProgressBar progressBar;

    public TileMap(int mundo, int nivel, GameState state) {
        this.mundo = mundo;
        this.nivel = nivel;
        this.state = state;
        tileSheet = new Texture("SpriteSheet", spriteXSize * qx, spriteYSize * qy);
        PreTileSet = new Tile[spriteXSize][spriteYSize][2];
        entities = new ArrayList<Entity>();
        mobs = new ArrayList<Mob>();

        progressBar = new ProgressBar((Game.WIDTH / 2) - (Game.WIDTH / 6), 50, 2 * Game.WIDTH / 6, 20, 200);

        ParallaxLayer back = new ParallaxLayer(new Texture("ParallaxBack", (int) Game.WIDTH, Game.HEIGHT), (int) ((16 * 0.25) * -0.35));
        ParallaxLayer mid = new ParallaxLayer(new Texture("ParallaxMiddle", Game.WIDTH, Game.HEIGHT), (int) ((16 * 0.25) * -0.65));
        ParallaxLayer front = new ParallaxLayer(new Texture("ParallaxFront", Game.WIDTH, Game.HEIGHT), (int) ((16 * 0.25) * -0.85));
        this.parallaxEngine = new ParallaxEngine(back, mid, front);

        load(mundo, nivel);
    }

    public static int pixelsToTiles(int pixels) {
        return (int) Math.floor(pixels / TILE_SIZE);
    }

    public static int tilesToPixels(int tiles) {
        return tiles * TILE_SIZE;
    }

    public void setTile(int x, int y, Tile k) {
        Tile tile = k;
        tile.setX(tilesToPixels(x));
        tile.setY(tilesToPixels(y));
        tiles[x + y * width] = tile;
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return null;
        }
        return tiles[x + y * width];
    }

    public void addEntity(Entity e) {
        if (!(e instanceof Mob)) {
            entities.add(e);
        }
    }

    public void addMob(Mob e) {
        if (!(e instanceof Player)) {
            mobs.add(e);
        }
    }

    public void removeEntity(Entity e) {
        if (!(e instanceof Player)) {
            entities.remove(e);
        }
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public Player getPlayer() {
        return player;
    }

    private void load(int mundo, int nivel) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("./resources/levels/" + mundo + "-" + nivel + ".png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.width = image.getWidth();
        this.height = image.getHeight();
        tiles = new Tile[width * height];
        int[] pixels = image.getRGB(0, 0, width, height, null, 0, width);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = pixels[x + y * width];
                int alpha = (pixel >> 24) & 0xff;
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel >> 0) & 0xff;
                if (alpha == 240) {
                    try {
                        Tile t = new Tile(new Texture(tileSheet, red, green, qx), false, tilesToPixels(x), tilesToPixels(y), this);
                        setTile(x, y, t);
                    } catch (Exception ex) {
                        System.out.println("X:" + x + "    Y:" + y + "   RGB:" + (red - 1) + ";" + (green - 1) + ";" + blue);
                    }

                }
                if (alpha == 230) {
                    player = new Player(new Texture("monstro", qx, 2 * qy), tilesToPixels(x), tilesToPixels(y), this);
                }
                if (alpha == 220) {
                    addEntity(new Coin(new Texture("coin", qx, qy), tilesToPixels(x), tilesToPixels(y), blue, this));
                }
                if (alpha == 210) {
                    addEntity(new Spike(new Texture("spike", qx, qy), tilesToPixels(x), tilesToPixels(y), this));
                }
            }
        }
//        addMob(new WalkingDead(new Texture("boneco", qx, 2 * qy), tilesToPixels(27), tilesToPixels(0), this,null));
        Tile t = new Trampoline(new Texture("trampolino", qx, qy), false, tilesToPixels(27), tilesToPixels(11), this);
        setTile(27, 11, t);

    }

    public void pause() {

    }

    public void play() {

    }

    public void tick() {
        if (isMovingLeft()) {
            parallaxEngine.setLeft();
        }
        if (isMovingRight()) {
            parallaxEngine.setRight();
        }
        if (isMoving()) {
            parallaxEngine.move();
        }
        oldOffsetX = getOffsetX();
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).tick();
        }
        for (int i = 0; i < mobs.size(); i++) {
            mobs.get(i).tick();
        }
        if (player.getY() + 2 * qy >= tilesToPixels(height)) {
            player.kill();
        }
        player.tick();
        for (Tile t : tiles) {
            if (t != null) {
                t.tick();
            }
        }
        progressBar.setAtual(player.getMana());
    }

    public void render(Graphics2D g, int screenWidth, int screenHeight) {
        int offsetX = getOffsetX();
        int offsetY = getOffsetY();

        int firstX = pixelsToTiles(-offsetX);
        int lastX = firstX + pixelsToTiles(screenWidth) + 1;

        int firstY = pixelsToTiles(-offsetY);
        int lastY = firstY + pixelsToTiles(screenHeight) + 1;

        parallaxEngine.render(g);

        for (int y = firstY; y < lastY + 1; y++) {
            for (int x = firstX; x < lastX; x++) {
                Tile t = getTile(x, y);
                if (t != null) {
                    t.render(g, tilesToPixels(x), tilesToPixels(y), offsetX, offsetY);
                }
            }
        }
        for (int i = 0; i < mobs.size(); i++) {
            mobs.get(i).render(g, offsetX, offsetY);
        }
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).render(g, offsetX, offsetY);
        }
        player.render(g, offsetX, offsetY);
        progressBar.render(g);
        Fonts.drawString(g, new Font("RighteousGame", Font.PLAIN, Game.HEIGHT / 100), Color.BLACK, "FPS: " + Game.fpsT + " | TPS: " + Game.tpsT + " | Vida: " + player.getLife() + " | VelocidadeX: " + player.getVxDelay() + " | VelocidadeY: " + player.getVyDelay() + " | Mana: " + player.getMana(), true);
        Fonts.drawString(g, new Font("RighteousGame", Font.PLAIN, 13), Color.BLACK, player.getMana() + "/200", 69);

    }

   

    public Tile[] getTiles(int size, double oldX, double oldY, double newX, double newY) {
        double fromX = Math.min(oldX, newX);
        double fromY = Math.min(oldY, newY);
        double toX = Math.max(oldX, newX);
        double toY = Math.max(oldY, newY);

        int fromTileX = pixelsToTiles((int) fromX);
        int fromTileY = pixelsToTiles((int) fromY);
        
        int toTileX = pixelsToTiles((int) toX + 1);
        int toTileY = pixelsToTiles((int) toY + 1);
        
        ArrayList<Tile> ttt = new ArrayList<>();
        
        for (int y = fromTileY-1; y <= toTileY+2; y++) {
            for (int x = fromTileX-1; x <= toTileX+2; x++) {
                ttt.add(getTile(x, y));
            }
        }
        
        Tile[] tttt = new Tile[ttt.size()];
        for (int i = 0; i < ttt.size(); i++) {
            tttt[i]=ttt.get(i);
        }
        
        return tttt;
    }

    public Tile[] getTiles() {
        return tiles;
    }

    public void restart() {
        state.restart();
    }

    public boolean isMovingRight() {
        boolean itIs = (oldOffsetX > getOffsetX());

        return itIs;
    }

    public boolean isMovingLeft() {
        boolean itIs = (oldOffsetX < getOffsetX());

        return itIs;
    }

    public boolean isMoving() {
        boolean itIs = (oldOffsetX != getOffsetX());
        return itIs;
    }

    public int getOffsetX() {
        int mapWidth = tilesToPixels(width);

        int offsetX = (int) (Game.WIDTH / 2 - player.getX() - TILE_SIZE);

        offsetX = Math.min(offsetX, 0);
        offsetX = Math.max(offsetX, Game.WIDTH - mapWidth);

        return offsetX;

    }

    public int getOffsetY() {
        int mapHeigth = tilesToPixels(height);

        int offsetY = (int) (Game.HEIGHT / 2 - player.getY() - TILE_SIZE);

        offsetY = Math.min(offsetY, 0);
        offsetY = Math.max(offsetY, Game.HEIGHT - mapHeigth);
        return offsetY;
    }

    public ArrayList<Mob> getMobs() {
        return mobs;
    }

    public void restartSize() {
//        for (Tile t : tiles) {
//            if(t!=null)
//                t.setSize(qx, qy);
//        }
//        for (Entity t : entities) {
//             t.setSize(t.getPWidth()*qx, t.getPHeight()*qy);
//        }
//        for (Mob t : mobs) {
//             t.setSize(t.getPWidth()*qx, t.getPHeight()*qy);
//        }
//        player.setSize(player.getPWidth()*qx, player.getPHeight()*qy);
    }

}
