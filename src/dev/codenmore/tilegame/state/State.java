package dev.codenmore.tilegame.state;

import dev.codenmore.tilegame.Handler;
import java.awt.*;

//Implementeaza notiunea abstracta de stare a jocului/programului.
public abstract class State {
    //State manager
    private static State currentState=null;
    private final String path;
    public static void setState(State state){
        currentState=state;
    }
    public static State getState(){
        return currentState;
    }

    //Class
    protected Handler handler;
    public State(Handler handler,String path){
        this.handler=handler;
        this.path=path;
    }
    public abstract void tick();
    public abstract void render(Graphics g) ;
    public String getPath(){
        return this.path;
    }


}
