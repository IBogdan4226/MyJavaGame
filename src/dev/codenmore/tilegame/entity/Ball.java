package dev.codenmore.tilegame.entity;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.gfx.Assets;

import java.awt.*;

public class Ball extends Creature{

    private long lastCollision=0;
    private final long Timer=200L;
    int speed=3;
    int angleX,angleY;
    public Ball(Handler handler, float x, float y,int size,int vertical,int horizontal) {
        super(handler, x, y, size, size);


        double xx=Math.random();
        if(horizontal==0){
            angleX=speed;
        }
        else
        {
            angleX=-speed;
        }
        if(vertical==0){
            angleY=speed-1;
        }
        else
        {
            angleY=-speed+1;
        }
    }

    private void getInput(){

        xMove=0;
        yMove=0;
        if(isColissionDownY()&&isColissionUpY()&&!this.actualc())
        {

            if(angleX<0){
                angleX=speed;
            }
            else
            {
                angleX=-speed;
            }
        }
        else if(isCollisionLeftX()&&isCollisionRightX()&&!this.actualc()){

            angleY=speed-1;
        }

        else if(checkEntityCollisions(1,1)&&!this.actualc())
        {


            angleY=-speed+1;
            if(angleX<0){
                angleX=speed;
            }
            else
            {
                angleX=-speed;
            }
            this.enablec();

        }


        else if(isColissionDownY()||isColissionUpY()&&!this.actualc())
        {

            double xx=Math.random();
            if(xx<0.4){
                if(angleX<0){
                    angleX=speed;
                }
                else
                {
                    angleX=-speed;
                }
            }

            if(angleY<0){
                angleY=speed-1;
            }
            else
            {
                angleY=-speed+1;
            }

        }
        else if(isCollisionLeftX()||isCollisionRightX()&&!this.actualc())
        {


            if(angleX<0){
                angleX=speed;
            }
            else
            {
                angleX=-speed;
            }
        }



        xMove=angleX;
        yMove=angleY;


    }
    @Override
    public void tick() {
        getInput();
        move();

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.ball,(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),width,height,null);
        //g.setColor(Color.red);
        //g.fillRect((int)(x+bounds.x-handler.getGameCamera().getxOffset()),(int)(y+bounds.y-handler.getGameCamera().getyOffset()),bounds.width, bounds.height);

    }
    public void enablec(){
        lastCollision=System.currentTimeMillis();
    }
    public boolean actualc(){
        return System.currentTimeMillis()-lastCollision<Timer;
    }

}
