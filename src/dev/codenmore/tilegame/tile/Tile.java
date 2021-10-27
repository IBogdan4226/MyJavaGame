package dev.codenmore.tilegame.tile;

import java.awt.*;
import java.awt.image.BufferedImage;

//Retine toate tile urile din joc si le ordoneaza dupa un id pentru a le utiliza mai usor.
public class Tile {
    //STATIC TILE
    public static Tile[] tiles = new Tile[128];
    public static Tile tilebottom=new TileBottom(0);
    public static Tile tileside=new TileSide(1);
    public static Tile tilemiddle=new TileMiddle(2);
    public static Tile tilespike=new TileSpike(3);
    public static Tile tileempty=new TileEmpty(4);
    public static Tile tilewinter=new TileWinter(5);

    //CLASS
    public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id){
        this.texture = texture;
        this.id = id;

        tiles[id] = this;
    }

    public void tick(){

    }

    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
    }

    public boolean isSolid(){
        return false;
    }

    public int getId(){
        return id;
    }

}