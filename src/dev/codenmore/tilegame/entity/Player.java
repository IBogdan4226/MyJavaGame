package dev.codenmore.tilegame.entity;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.Shooting.Shooter;
import dev.codenmore.tilegame.gfx.Animation;
import dev.codenmore.tilegame.gfx.Assets;
import java.awt.*;
import java.awt.image.BufferedImage;

//Playerul propriu zis. Fata de creature acesta are si gravitatie si animatii.
public class Player extends Creature{
    //Animations
    private final Animation animRight;
    private final Animation animLeft;
    private final Animation animUp;
    private final Animation animJumpRight;
    private final Animation animJumpLeft;
    private final Animation animDownLeft;
    private final Animation animDownRight;

    //Gravity
    public final double GRAVITY=0.08;
    public int TERMINAL_VELOCITY=10;
    double vertical_speed = 0;

    //Shooting
    private final Shooter shooter;


    public static int scor=0;
    public Player(Handler handler, float x, float y) {
        super(handler,x, y,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.x=15;
        bounds.y=15;
        bounds.width=35;
        bounds.height=47;
        shooter=new Shooter(handler);
        //Animations
        animRight=new Animation(140,Assets.player_right);
        animLeft=new Animation(140,Assets.player_left);
        animUp=new Animation(175,Assets.player_up);
        animJumpLeft=new Animation(175,Assets.player_jumpleft);
        animJumpRight=new Animation(175,Assets.player_jumpright);
        animDownLeft=new Animation(175,Assets.player_downleft);
        animDownRight=new Animation(175,Assets.player_downright);
    }

    @Override
    public void tick() {
        //Animations
        animRight.tick();
        animLeft.tick();
        animUp.tick();
        animJumpRight.tick();
        animJumpLeft.tick();
        animDownLeft.tick();
        animDownRight.tick();
        //Movement
        fall();
        move();
        getInput();
        move();
        //Arrow
        shooter.tick();
        //Coliziuni intre inamici
        checkEntityCollisionsforArrows(0,0);
        checkEntityCollisionsforBalls(+2,-2);
        checkEntityCollisionsforBalls(-2,-2);
        checkIfBalls();
        //Camera
        handler.getGameCamera().centerOn(this);

    }
    //Metoda care implementeaza gravitatia
    private void fall(){
        xMove=0;
        yMove=0;

        if(falling&&!checkEntityCollisions(0f,yMove+7)&&!isColissionDownY())
        {

            if(isColissionUpY())
            {
                vertical_speed=TERMINAL_VELOCITY/3*2;
            }
            if (vertical_speed + GRAVITY > TERMINAL_VELOCITY)
            {
                vertical_speed = TERMINAL_VELOCITY;

            } else
                {
                    if(vertical_speed>TERMINAL_VELOCITY/2)
                    {
                        vertical_speed += GRAVITY/1.5;
                    }
                    if(handler.getKeyManager().left||handler.getKeyManager().right)
                    {
                        vertical_speed += GRAVITY/2.5;
                    }
                    if(falling&&!handler.getKeyManager().up)
                    {
                        vertical_speed -= GRAVITY/1.5;
                    }
                vertical_speed += GRAVITY;
            }
            yMove += vertical_speed;
        }
        else
        {
            if(falling) {
                yMove += 3;
            }
            vertical_speed=0;
            falling=false;

        }

    }


    //Verifica daca s-a apasat o tasta din cele stabilite in KeyManager si se deplaseaza in directia stabilita.
    private void getInput()  {
        xMove=0;
        yMove=0;

        if(vertical_speed!=TERMINAL_VELOCITY&&handler.getKeyManager().up) {
            yMove=-speed-(float)0.5;
            falling=true;

        }
        if(handler.getKeyManager().left)
            xMove=-speed;
        if(handler.getKeyManager().right)
            xMove=speed;

        if(handler.getKeyManager().space&&!shooter.actualShooting()){

            shooter.addArrow(new Arrows(handler,x,y));
            shooter.enableShooting();

        }

    }



    @Override
    public void render(Graphics g)  {

        g.drawImage(getCurrentAnimationFrame(),(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),width,height,null);

        /*g.setColor(Color.red);
        g.fillRect((int)(x+bounds.x-handler.getGameCamera().getxOffset()),(int)(y+bounds.y-handler.getGameCamera().getyOffset()),bounds.width, bounds.height);*/
        shooter.render(g);

        for(int i = 0; i< getHealth(); i++){
            g.drawImage(Assets.heart,i*32+16,682,32,32,null);
        }

    }
    //Metoda care returneaza animatia curenta cu ajutorul metodei realizate in gfx.Animation
    private BufferedImage getCurrentAnimationFrame() {
        if(yMove<0||yMove>0)
        {
            if(isColissionDownY()) {
                return Assets.player_right[0];
            }
        }
        if (xMove>0 && yMove<0&&falling)
            return animJumpRight.getCurrentFrame();
        else if (xMove<0 && yMove<0&&falling)
            return animJumpLeft.getCurrentFrame();
        else if (xMove>0 && yMove>0&&falling)
            return animDownRight.getCurrentFrame();
        else if (xMove<0 && yMove>0&&falling)
            return animDownLeft.getCurrentFrame();
        else if(xMove<0&&falling)
            return animDownLeft.getCurrentFrame();
        else if(xMove>0&&falling)
            return animDownRight.getCurrentFrame();
        else if(xMove<0)
            return animLeft.getCurrentFrame();
        else if (xMove>0)
            return animRight.getCurrentFrame();
        else if(yMove<0)
            return animUp.getCurrentFrame();
        else if(yMove>0)
            return animUp.getCurrentFrame();
        else if(falling)
            return animUp.getCurrentFrame();
        else
            return Assets.player_right[0];


    }

    public int getTERMINAL_VELOCITY() {
        return TERMINAL_VELOCITY;
    }

    public void setTERMINAL_VELOCITY(int TERMINAL_VELOCITY) {
        this.TERMINAL_VELOCITY = TERMINAL_VELOCITY;
    }

    public Shooter getShooter() {
        return shooter;
    }
}
