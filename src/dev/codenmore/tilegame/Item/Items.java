package dev.codenmore.tilegame.Item;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Items {
    public static Items[]items=new Items[10];
    public static Items speedPowerUp=new Items(Assets.powerUpSpeed,0);
    public static Items arrowPowerUp=new Items(Assets.powerUpArrow,1);

    protected Rectangle bounds;

    public static final int ItemW=32,ItemH=32;

    protected Handler handler;
    protected BufferedImage texture;
    protected boolean picked=false;
    protected final int id;

    protected int x,y;
    public Items(BufferedImage texture,int id){
        this.texture=texture;
        this.id=id;
        items[id]=this;
        bounds=new Rectangle(x,y,ItemW,ItemH);

    }

    public void tick(){
        if(y<670)
            setPosition(x,y+3);
        if(y>=670)
            setPosition(x,672);
        if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0,0).intersects(bounds)){
            picked=true;
            if(this.id==0){
                handler.getWorld().getEntityManager().getPlayer().setSpeed(5);
                handler.getWorld().getEntityManager().getPlayer().setTERMINAL_VELOCITY(15);
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                handler.getWorld().getEntityManager().getPlayer().setSpeed(3);
                                handler.getWorld().getEntityManager().getPlayer().setTERMINAL_VELOCITY(10);
                            }
                        },
                        7000
                );
            }
            else if(this.id==1){
                handler.getWorld().getEntityManager().getPlayer().getShooter().setArrow_Timer(500);
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                handler.getWorld().getEntityManager().getPlayer().getShooter().setArrow_Timer(850);
                            }
                        },
                        5000
                );
            }
        }

    }
    public void render(Graphics g){
        if(handler==null)
            return;
        g.drawImage(texture,(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),ItemH,ItemW,null);
    }
    public void setPosition(int x,int y){
        this.x=x;
        this.y=y;

        bounds.y=y;

        bounds.x=x;
    }
    public Items createNew(int x, int y){
        Items i = new Items(texture, id);
        i.setPosition(x, y);
        return i;
    }
    public static int getItemW() {
        return ItemW;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }


    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean getPicked() {
        return picked;
    }

    public void setPicked(boolean picked) {
        this.picked = picked;
    }

}
