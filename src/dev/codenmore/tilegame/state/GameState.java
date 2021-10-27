package dev.codenmore.tilegame.state;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.World.Background;
import dev.codenmore.tilegame.World.World;
import java.awt.*;

//Contine toate elementele jocului
public class GameState extends State{

    private final World world;
    private final Background background;
    private String path;
    public GameState(Handler handler,int level){
        super(handler,"");
        world=new World(handler,level);
        handler.setWorld(world);
        background=new Background(handler,"res/textures/wallpaper 2.jpeg");
        MenuState.changeaux();

    }
    @Override
    public void tick() {
       background.tick();
        world.tick();


    }

    @Override
    public void render(Graphics g)  {
        background.render(g);
        world.render(g);

    }


}
