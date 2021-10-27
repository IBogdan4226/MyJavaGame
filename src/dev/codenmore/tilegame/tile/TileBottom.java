package dev.codenmore.tilegame.tile;

import dev.codenmore.tilegame.gfx.Assets;

import java.awt.image.BufferedImage;

public class TileBottom extends Tile{
    public TileBottom( int id) {
        super(Assets.tilebottom, id);
    }
    @Override
    public boolean isSolid() {
        return true;
    }
}
