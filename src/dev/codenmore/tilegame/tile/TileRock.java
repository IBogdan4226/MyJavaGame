package dev.codenmore.tilegame.tile;

import dev.codenmore.tilegame.gfx.Assets;

import java.awt.image.BufferedImage;

public class TileRock extends Tile{
    public TileRock( int id) {
        super(Assets.tilerock, id);
    }
    @Override
    public boolean isSolid() {
        return true;
    }
}
