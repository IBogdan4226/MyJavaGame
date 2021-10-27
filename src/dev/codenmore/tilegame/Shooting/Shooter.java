package dev.codenmore.tilegame.Shooting;
import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.entity.Arrows;

import java.awt.*;
import java.util.LinkedList;

public class Shooter {
    private static final LinkedList<Arrows> a=new LinkedList<Arrows>();
    Arrows tempArrow;
    Handler handler;
    private long lastArrow = 0L;
    private long Arrow_Timer = 850L;
    public Shooter(Handler handler){
        this.handler=handler;

    }
    public void tick(){
        for(int i=0;i<a.size();i++)
        {
            tempArrow=a.get(i);
            if(tempArrow.getY()<0)
                removeArrow(tempArrow);
            if(tempArrow.isColissionUpY())
                removeArrow(tempArrow);
            tempArrow.tick();
        }
    }
     public void render(Graphics g){
         for(int i=0;i<a.size();i++)
         {
             tempArrow=a.get(i);
             tempArrow.render(g);
         }
     }
     public void addArrow(Arrows t){
        a.add(t);
     }
     public void removeArrow(Arrows t){
        a.remove(t);
     }
    public void enableShooting(){
        lastArrow=System.currentTimeMillis();
    }
    public boolean actualShooting(){
        return System.currentTimeMillis()-lastArrow<Arrow_Timer;
    }
    public static LinkedList getArrows(){
        return Shooter.a;
    }

    public long getArrow_Timer() {
        return Arrow_Timer;
    }

    public void setArrow_Timer(long arrow_Timer) {
        Arrow_Timer = arrow_Timer;
    }
}
