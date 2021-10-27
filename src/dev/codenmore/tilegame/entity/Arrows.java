package dev.codenmore.tilegame.entity;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.entity.Creature;
import dev.codenmore.tilegame.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Arrows extends Creature {

    public Arrows(Handler handler, float x, float y){
        super(handler,x,y,30,64);
        bounds.x=bounds.x+17;
        bounds.width=bounds.width-4;
        bounds.y=bounds.y-68;

    }
    public void tick(){
        y-=10;
    }
    public void render(Graphics g){
        g.drawImage(Assets.arrow,(int)(x-handler.getGameCamera().getxOffset()+14),(int)(y-handler.getGameCamera().getyOffset()-70),width,height,null);
        //g.setColor(Color.red);
        //g.fillRect((int)(x+bounds.x-handler.getGameCamera().getxOffset()),(int)(y+bounds.y-handler.getGameCamera().getyOffset()),bounds.width, bounds.height);
    }


}
