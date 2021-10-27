package dev.codenmore.tilegame.World;

import dev.codenmore.tilegame.EntityFactory;
import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.Item.ItemManager;
import dev.codenmore.tilegame.SQLCLASS;
import dev.codenmore.tilegame.Utils.Utils;
import dev.codenmore.tilegame.entity.Ball;
import dev.codenmore.tilegame.entity.EntityManager;
import dev.codenmore.tilegame.entity.Player;
import dev.codenmore.tilegame.entity.Static.TileWinter;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.state.GameState;
import dev.codenmore.tilegame.state.State;
import dev.codenmore.tilegame.tile.Tile;
import java.awt.*;
import java.awt.Color;
import java.awt.Font;


public class World {
    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tiles;
    private final int  level;
    private Player player;
    static public int timp;
    static public int timpJucat=0;

    private EntityManager entityManager;
    private ItemManager itemManager;

    private EntityFactory ef;
    private long lastTime = 0L;
    private final long Timer = 1000L;
    public World(Handler handler,int level){
        this.handler=handler;
        this.level=level;
        ef=new EntityFactory(this.handler);
        if(level==1){
            timpJucat=0;
            Player.scor=0;
            spawnX=500;
            spawnY=640;
            timp=90;
            player = new Player(handler, 70, 70);
            entityManager = new EntityManager(handler, player);
            itemManager=new ItemManager(handler);

            entityManager.addEntity(ef.createEntity(0, 90, 100, 64,1,1,0));
            entityManager.addEntity(ef.createEntity(0, 500, 100, 64,1,0,0));

            try {
                loadWorld(level);
            }
            catch(InvalidMapException e){
                System.err.println(e);
                loadWorldDefault();
            }

            entityManager.getPlayer().setX(spawnX);
            entityManager.getPlayer().setY(spawnY);
        }
        else if(level==2){
            spawnX=500;
            spawnY=640;
            timp=90;
            player = new Player(handler, 70, 70);
            entityManager = new EntityManager(handler, player);
            itemManager=new ItemManager(handler);


            entityManager.addEntity(ef.createEntity(0, 100, 250, 64,1,0,0));
            entityManager.addEntity(ef.createEntity(0, 950, 250, 64,0,0,0));

            entityManager.addEntity(ef.createEntity(0, 500, 100, 100,0,0,0));

            try {
                loadWorld(level);
            }
            catch(InvalidMapException e){
                System.err.println(e);
                loadWorldDefault();
            }

            entityManager.getPlayer().setX(spawnX);
            entityManager.getPlayer().setY(spawnY);
        }

        else if(level==3){
            spawnX=40;
            spawnY=640;
            timp=90;
            player = new Player(handler, 70, 70);
            entityManager = new EntityManager(handler, player);
            itemManager=new ItemManager(handler);

            entityManager.addEntity(ef.createEntity(0, 150, 500, 64,1,1,0));
            entityManager.addEntity(ef.createEntity(0, 500, 600, 64,1,0,0));

            entityManager.addEntity(ef.createEntity(0, 255, 500, 40,0,1,0));
            entityManager.addEntity(ef.createEntity(0, 340, 600, 40,1,0,0));

            entityManager.addEntity(ef.createEntity(0, 900, 600, 84,0,0,0));

            try {
                loadWorld(level);
            }
            catch(InvalidMapException e){
                System.err.println(e);
                loadWorldDefault();
            }

            entityManager.getPlayer().setX(spawnX);
            entityManager.getPlayer().setY(spawnY);
        }
        else if(level==4) {
            spawnX=650;
            spawnY=640;
            timp=90;
            player = new Player(handler, 70, 70);
            entityManager = new EntityManager(handler, player);
            itemManager=new ItemManager(handler);


            //Tiles
            entityManager.addEntity(ef.createEntity(1, 800, 650, 0,0,0,3));
            entityManager.addEntity(ef.createEntity(1, 980, 610, 0,0,0,2));
            entityManager.addEntity(ef.createEntity(1, 1100, 550, 0,0,0,2));
            entityManager.addEntity(ef.createEntity(1, 1160, 500,0,0,0, 1));
            entityManager.addEntity(ef.createEntity(1, 1180, 450,0,0,0, 1));
            entityManager.addEntity(ef.createEntity(1, 1200, 400, 0,0,0,1));

            //Balls
            entityManager.addEntity(ef.createEntity(0, 90, 600, 64,1,0,0));
            entityManager.addEntity(ef.createEntity(0, 90, 100, 64,0,1,0));
            entityManager.addEntity(ef.createEntity(0, 120, 100, 64,0,0,0));
            entityManager.addEntity(ef.createEntity(0, 150, 100, 64,0,1,0));
            entityManager.addEntity(ef.createEntity(0, 250, 100, 84,0,1,0));
            entityManager.addEntity(ef.createEntity(0, 430, 500, 84,1,1,0));
            entityManager.addEntity(ef.createEntity(0, 430, 200, 64,0,1,0));
            entityManager.addEntity(ef.createEntity(0, 800, 100, 64,1,0,0));
            entityManager.addEntity(ef.createEntity(0, 800, 100, 64,0,0,0));

            try {
                loadWorld(level);
            }
            catch(InvalidMapException e){
                System.err.println(e);
                loadWorldDefault();
            }

            entityManager.getPlayer().setX(spawnX);
            entityManager.getPlayer().setY(spawnY);
        }
    }

    public void tick() {
        entityManager.tick();
        itemManager.tick();
        if(!Decrement())
        {
            timp--;
            enableTime();
            if(timp<=0){
                Player.takeDamage();
                System.out.println("Timpul a expirat");
                handler.getGame().setGameState(new GameState(handler, handler.getLevel()));
                State.setState(handler.getGame().gameState);
            }
        }


    }


    public void render(Graphics g)  {
        for(int y = 0;y < height;y++){
            for(int x = 0;x < width;x++){
                /////CAMERA
                getTile(x, y).render(g,(int)(x * Tile.TILEWIDTH-handler.getGameCamera().getxOffset()), (int)(y * Tile.TILEHEIGHT-handler.getGameCamera().getyOffset()));
            }
        }
        itemManager.render(g);
        entityManager.render(g);
        g.setFont(new Font("Serif", Font.PLAIN, 25));
        g.setColor(Color.red);
        g.drawString("Points:"+Player.scor, 918, 703);
        g.drawImage(Assets.timer,450,678,38,38,null);
        g.setFont(new Font("Serif", Font.PLAIN, 30));
        g.setColor(Color.black);
        g.drawString(""+timp, 500, 707);
    }

    public Tile getTile(int x, int y){
        if(x<0||y<0||x>=width||y>=height)
            return Tile.tilebottom;
        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null)
            return Tile.tileempty;
        return t;
    }

    private void loadWorld(int nivel) throws InvalidMapException {

        try {
            int [][]defaultMap= SQLCLASS.returnMapMatrix(nivel);
            width = defaultMap[0].length;
            height = defaultMap.length;


            tiles = new int[width][height];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    tiles[x][y] = defaultMap[y][x];
                }
            }
            System.out.println("Am incarcat harta din SQLITE");
        }
        catch (Exception a){
            throw new InvalidMapException("Harta curenta are probleme. Harta default se va incarca.");
        }
    }
    private void loadWorldDefault()  {


        String file = Utils.loadFileAsString("res/World/WorldDefault");
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        tiles = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);

            }

        }
    }




    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public int getLevel(){
        return this.level;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }
    public boolean Decrement(){
        return System.currentTimeMillis()-lastTime<Timer;
    }
    public void enableTime(){
        lastTime=System.currentTimeMillis();
    }
    class InvalidMapException extends Exception{
        public InvalidMapException(String error){
            super(error);
        }
    }
}
