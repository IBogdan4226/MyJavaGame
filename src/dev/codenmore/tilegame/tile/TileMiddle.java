package dev.codenmore.tilegame.tile;

import dev.codenmore.tilegame.gfx.Assets;

import java.awt.image.BufferedImage;

public class TileMiddle extends Tile{
    public TileMiddle(int id) {
        super(Assets.tilemiddle, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
