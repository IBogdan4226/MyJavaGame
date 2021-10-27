package dev.codenmore.tilegame.gfx;

import java.awt.image.BufferedImage;

//Clasa va alterna printre un vector de imagini la un interval de milisecunde precizat.
public class Animation {
    private final int speed;
    private int index;
    private long lastTime,timer;
    private final BufferedImage[] frames;

    public Animation(int speed, BufferedImage[] frames){
        this.speed=speed;
        this.frames=frames;
        index=0;
        timer=0;
        lastTime=System.currentTimeMillis();
    }
    public void tick(){
        timer+=System.currentTimeMillis()-lastTime;
        lastTime=System.currentTimeMillis();

        if(timer>speed){
            index++;
            timer=0;
            if(index>=frames.length)
                index=0;
        }
    }

    public BufferedImage getCurrentFrame(){
        return frames[index];
    }

}
