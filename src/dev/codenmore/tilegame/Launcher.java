package dev.codenmore.tilegame;

import dev.codenmore.tilegame.display.Display;
import dev.codenmore.tilegame.SQLCLASS;

public class Launcher {
    public static void main(String[] args){
        Game game=Game.getInstance("Bubble Trouble",1024,720);
        game.start();

    }
}


