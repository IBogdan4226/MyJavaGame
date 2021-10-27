package dev.codenmore.tilegame.tile;

import dev.codenmore.tilegame.gfx.Assets;

public class TileSide extends Tile {
    public TileSide(int id) {
        super(Assets.tileside, id);


    }
    @Override
    public boolean isSolid() {
        return true;
    }
}
