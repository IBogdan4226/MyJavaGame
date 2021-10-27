package dev.codenmore.tilegame.entity;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.Item.Items;
import dev.codenmore.tilegame.SQLCLASS;
import dev.codenmore.tilegame.Shooting.Shooter;
import dev.codenmore.tilegame.World.World;
import dev.codenmore.tilegame.state.GameState;
import dev.codenmore.tilegame.state.State;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Scanner;

//Implementeaza notiunea abstracta de entitate activa din joc dar care nu se poate misca inca

public abstract class Entity {
    protected float x, y;
    public static boolean GAMEOVER=false;
    public static boolean GAMEWON=false;
    protected Handler handler;
    protected int width, height;
    public boolean falling = true;
    public boolean jumping = false;
    public boolean collisionWithTileY = false;

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final LocalDateTime now = LocalDateTime.now();

    protected Rectangle bounds;

    public Entity(Handler handler, float x, float y, int width, int height) {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bounds = new Rectangle(0, 0, width, height);



    }
    //Metoda care returneaza true atunci cand dreptunghiul pt bounds a 2 entitati se intersecteaza
    public boolean checkEntityCollisions(float xOffset, float yOffset) {
        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this))
                continue;
            if (e instanceof Ball && this instanceof Ball)
                continue;
            if (e.getCollisionBounds(0, 0).intersects(getCollisionBounds(xOffset, yOffset))) {
                return true;
            }
        }
        return false;
    }

    //Metoda care verifica atunci cand playerul este lovid de o bila. O viata se pierde atunci cand se intampla asta
    public void checkEntityCollisionsforBalls(float xOffset,float yOffset){
        int aux=0;
        for(Entity e:handler.getWorld().getEntityManager().getEntities()){
            if(e.getCollisionBounds(0,0).intersects(getCollisionBounds(xOffset,yOffset))) {
                if ((e instanceof Ball && this instanceof Player) || (this instanceof Ball && e instanceof Player)) {
                    aux=1;
                }

            }
        }
        if(aux==1){
            Player.takeDamage();
            System.out.println(dtf.format(now)+" Ai luat 1 dmg");
            handler.getGame().setGameState(new GameState(handler, handler.getLevel()));
            State.setState(handler.getGame().gameState);


        }
    }

    //Metoda care verifica atunci cand o bila este atinsa de o sageata, urmand ca bila sa se distruga.
    public void checkEntityCollisionsforArrows(float xOffset,float yOffset){
        int auxRandom=(int)(Math.random()*100);
        LinkedList<Arrows> a= Shooter.getArrows();
        int ok=0;
        Entity aux = null;
        for(Entity e:handler.getWorld().getEntityManager().getEntities()){
            for(int i=0;i<a.size();i++)
            {
                if(e instanceof Ball&&e.getCollisionBounds(0,0).intersects(a.get(i).getCollisionBounds(xOffset,yOffset))){
                    ok=1;
                    aux=e;
                    Shooter.getArrows().remove(a.get(i));
                    continue;
                }
            }
        }
    if(ok==1&&aux.getWidth()>59){
        handler.getWorld().getEntityManager().addEntity(new Ball(handler, aux.getX(), aux.getY(), aux.getWidth() -20,0,1));
        handler.getWorld().getEntityManager().addEntity(new Ball(handler, aux.getX(), aux.getY(), aux.getWidth() -20,0,0));
        handler.getWorld().getEntityManager().removeEntities(aux);
        System.out.println(dtf.format(now)+" Ai distrus o bila");
        Player.scor++;
        Player.scor++;
        if(auxRandom>93) {
            handler.getWorld().getItemManager().addItem(Items.speedPowerUp.createNew((int) aux.getX(), (int) aux.getY()));
            System.out.println(dtf.format(now)+" Ai droppat un speedBoost");
        }
        if(auxRandom<7){
            handler.getWorld().getItemManager().addItem(Items.arrowPowerUp.createNew((int) aux.getX(), (int) aux.getY()));
            System.out.println(dtf.format(now)+" Ai droppat un arrowBoost");
        }
        ok=0;
    }
    else if(ok==1){
        System.out.println(dtf.format(now)+" Ai distrus o bila");
        Player.scor++;

        handler.getWorld().getEntityManager().removeEntities(aux);
        if(auxRandom>93) {
            handler.getWorld().getItemManager().addItem(Items.speedPowerUp.createNew((int) aux.getX(), (int) aux.getY()));
            System.out.println(dtf.format(now)+" Ai droppat un speedBoost");
        }
        if(auxRandom<7){
            handler.getWorld().getItemManager().addItem(Items.arrowPowerUp.createNew((int) aux.getX(), (int) aux.getY()));
            System.out.println(dtf.format(now)+" Ai droppat un arrowBoost");
        }
        ok=0;
    }
    }
    public Rectangle getCollisionBounds(float xOffset,float yOffset){
        return new Rectangle((int)(x+bounds.x+xOffset),(int)(y+bounds.y+yOffset),bounds.width,bounds.height);
    }
    //Metoda care verifica daca mai sunt bile in joc. Daca nu sunt nivelul a fost castigat.
    public void checkIfBalls() {
        int ok = 0;
        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e instanceof Ball || this instanceof Ball)
                return;
        }
        if(handler.getLevel()<=3){
            World.timpJucat+=90-World.timp;
            System.out.println(dtf.format(now)+" Ai trecut la nivelul urmator");
            handler.getGame().setGameState(new GameState(handler, handler.getLevel()+1));
            State.setState(handler.getGame().gameState);}
        else
        {
            World.timpJucat+=90-World.timp;
            Scanner sc= new Scanner(System.in);
            System.out.print("Introdu numele pentru a salva scorul ");
            String name= sc.nextLine();
            SQLCLASS.addScore(name,Player.scor,handler.getLevel(), World.timpJucat);
            GAMEWON=true;
        }

    }

    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public void setX(float x){
        this.x=x;
    }
    public void setY(float y){
        this.y=y;
    }
    public void setHeight(int x){
        this.height=x;
    }
    public void setWidth(int x){
        this.width=x;
    }
    public abstract void tick();
    public abstract void render(Graphics g);

}

