package dev.codenmore.tilegame.entity.Static;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.tile.Tile;

import java.awt.*;

public class TileWinter extends StaticEntity{
    public TileWinter(Handler handler, float x, float y,int multiplier) {
        super(handler, x, y, Tile.TILEWIDTH*multiplier, (int)(Tile.TILEHEIGHT*0.8));
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.tilewinter,(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),width,height,null);

    }
}