package dev.codenmore.tilegame;

import dev.codenmore.tilegame.entity.Ball;
import dev.codenmore.tilegame.entity.Entity;
import dev.codenmore.tilegame.entity.Static.TileWinter;

public class EntityFactory {

    private Handler handler;
    public EntityFactory(Handler handler){
        this.handler=handler;
    }
    public Entity createEntity(int option,int x,int y,int size,int ver,int hor,int mult){
        switch (option){
            case 0:
                return new Ball(this.handler,x,y,size,ver,hor);
            case 1:
                return new TileWinter(this.handler,x,y,mult);
            default:
                return null;

        }

    }
}
