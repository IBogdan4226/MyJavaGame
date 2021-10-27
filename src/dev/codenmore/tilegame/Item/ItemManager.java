package dev.codenmore.tilegame.Item;

import dev.codenmore.tilegame.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ItemManager {
    private Handler handler;
    private final ArrayList<Items> items;

    public ItemManager(Handler handler){
        this.handler=handler;
        items=new ArrayList<Items>();
    }
    public void tick(){
       Iterator<Items> iter=items.iterator();
       while(iter.hasNext()){
           Items i=iter.next();
           i.tick();
           if(i.getPicked()==true){
               iter.remove();
           }
       }
    }
    public void render(Graphics g){
        for(Items a:items){
            a.render(g);


        }
    }
    public void addItem(Items i){
        i.setHandler(handler);
        items.add(i);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
