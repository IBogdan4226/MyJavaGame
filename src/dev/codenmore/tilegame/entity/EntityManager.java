package dev.codenmore.tilegame.entity;

import dev.codenmore.tilegame.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class EntityManager {

    private Handler handler;
    private Player player;
    private ArrayList<Entity> entities;
    private final Comparator<Entity> renderSorter=new Comparator<Entity>() {
        @Override
        public int compare(Entity o1, Entity o2) {
            if(o1.getY()<o2.getY())
                return -1;
            return 1;
        }
    };


    public EntityManager(Handler handler, Player player){
        this.handler=handler;
        this.player=player;
        entities=new ArrayList<Entity>();
        addEntity(player);

    }

    public void tick(){
        for(int i=0;i<entities.size();i++)
        {
            Entity e=entities.get(i);
            e.tick();

        }
        entities.sort(renderSorter);

    }

    public void render(Graphics g){
        for(Entity e:entities){
            e.render(g);
        }

    }


    public void addEntity(Entity e){
        entities.add(e);
    }
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }
    public void removeEntities(Entity e){
        entities.remove(e);
    }
}
