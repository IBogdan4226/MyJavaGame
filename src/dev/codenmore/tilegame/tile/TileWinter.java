package dev.codenmore.tilegame.tile;

import dev.codenmore.tilegame.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TileWinter extends Tile{
    public TileWinter(int id) {
        super(Assets.tilewinter, id);
    }
    public boolean isSolid(){
        return true;
    }

    @Override
    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, TILEWIDTH*4, 54, null);
    }
}
