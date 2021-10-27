package dev.codenmore.tilegame.tile;

import dev.codenmore.tilegame.gfx.Assets;

import java.awt.image.BufferedImage;

public class TileEmpty extends Tile{
    public TileEmpty( int id) {
        super(Assets.tileempty, id);
    }
}
