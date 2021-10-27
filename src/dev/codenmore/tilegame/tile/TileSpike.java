package dev.codenmore.tilegame.tile;

import dev.codenmore.tilegame.gfx.Assets;

import java.awt.image.BufferedImage;

public class TileSpike extends Tile{
    public TileSpike( int id) {
        super(Assets.tilespike, id);
    }
    @Override
    public boolean isSolid() {
        return true;
    }
}
