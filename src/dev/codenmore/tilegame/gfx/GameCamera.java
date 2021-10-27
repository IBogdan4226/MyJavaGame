package dev.codenmore.tilegame.gfx;

import dev.codenmore.tilegame.Game;
import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.entity.Entity;
import dev.codenmore.tilegame.tile.Tile;

public class GameCamera {
    private final Handler handler;
    private float xOffset,yOffset;
    public GameCamera(Handler handler,float x,float y){
        this.handler=handler;
        this.xOffset=x;
        this.yOffset=y;
    }
    public void centerOn(Entity e){
        //Metoda care realizeaza legatura dintre camera si player
        xOffset=e.getX()-handler.getWidth()/2+e.getWidth()/2;//Impartim la 2 ca playerul sa fie in mijlocul ecranului, altfel ar fi intr-un colt
        yOffset=e.getY()-handler.getHeight()/2+e.getHeight()/2;
        removeBlackSpace();
    }

    public float getxOffset() {
        return xOffset;
    }
    public void removeBlackSpace(){
        if(xOffset<45)//Camera nu va arata primii 45 pixeli din stanga
            xOffset=45;
        else if(xOffset>handler.getWorld().getWidth()* Tile.TILEWIDTH-handler.getWidth()-45)//Verificam spatiile din dreapta
        {
            xOffset=handler.getWorld().getWidth()* Tile.TILEWIDTH-handler.getWidth()-45;
        }

        if(yOffset<720)
            yOffset=32;
        else if(yOffset>handler.getWorld().getHeight()*Tile.TILEHEIGHT-handler.getHeight())
            yOffset=handler.getWorld().getHeight()*Tile.TILEHEIGHT-handler.getHeight();
    }
    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }
    public void move(float x, float y){
        //Metoda care misca camera la stanga/dreapta/sus/jos
        xOffset+=x;
        yOffset+=y;
        removeBlackSpace();
    }
}
