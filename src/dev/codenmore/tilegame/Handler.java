package dev.codenmore.tilegame;

import dev.codenmore.tilegame.World.Background;
import dev.codenmore.tilegame.World.World;
import dev.codenmore.tilegame.gfx.GameCamera;
import dev.codenmore.tilegame.input.KeyManager;
import dev.codenmore.tilegame.input.MouseManager;

//Clasa ce retine o serie de referinte ale unor elemente pentru a fi usor accesibile in celelalte clase si pachete
public class Handler {
    private Game game;
    private World world;
    private Background background;
    public Handler(Game game){
        this.game=game;
    }

    public int getWidth(){
        return game.getWidth();
    }
    public int getHeight(){
        return game.getHeight();
    }
    public KeyManager getKeyManager(){

        return game.getKeyManager();
    }
    public MouseManager getMouseManager(){
        return game.getMouseManager();
    }
    public GameCamera getGameCamera(){

        return game.getGameCamera();
    }

    public Game getGame() {

        return game;
    }

    public void setGame(Game game) {

        this.game = game;
    }

    public World getWorld() {

        return world;
    }

    public void setWorld(World world) {

        this.world = world;
    }

    public Background getBackground() {

        return background;
    }

    public void setBackground(Background background) {

        this.background = background;
    }
    public int getLevel(){
        return this.world.getLevel();
    }
}
