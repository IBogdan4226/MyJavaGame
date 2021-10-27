package dev.codenmore.tilegame.entity;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.tile.Tile;

//Clasa abstracta care defineste notiunea de caracter care se poate misca si are viata.
public abstract class Creature extends Entity{
    public static final int DEFAULT_HEALTH=10;
    public static final float DEFAULT_SPEED=3.0f;
    public static final int DEFAULT_CREATURE_WIDTH=64;
    public static final int DEFAULT_CREATURE_HEIGHT=64;

    protected static int health=3;
    protected float speed;

    protected float xMove,yMove;

    public static void takeDamage() {
        health -= 1;
    }
    public static void restoreHealth(){health=3; }
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public static int getHealth() {
        return health;
    }

    public float getSpeed() {
        return speed;
    }


    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

    public float getxMove() {
        return xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler,x, y,width,height);

        speed=DEFAULT_SPEED;
        xMove=0;
        yMove=0;
    }
    public void move(){
        if(!checkEntityCollisions(xMove,0f))
            moveX();
        if(!checkEntityCollisions(0f,yMove)) {
            moveY();

        }
        else
        {
            falling=false;
        }
        movedown();



    }
    public void moveX(){
        if(xMove>0){//Playerul merge spre dreapta

            int tx=(int)(x+xMove+bounds.x+bounds.width)/ Tile.TILEWIDTH;//Muchia din dreapta a dreptunghiului de coliziune a playerului in pixeli/Tilewidth pentru a obtine pozitia in tiles.

            if(!collisionWithTile(tx,(int)(y+bounds.y)/Tile.TILEWIDTH)&&!collisionWithTile(tx,(int)(y+bounds.y+bounds.height)/Tile.TILEHEIGHT)){
                //Daca nu este coliziune pe muchia dreapta sus si jos
                x+=xMove;



            }else{
                x=tx*Tile.TILEWIDTH-bounds.x-bounds.width-1;//Seteaza pozitia playerlui langa Tile-1pixel pt a nu avea spatiu liber intre player si tile
            }
        }else if(xMove<0){//Playerul merge spre stanga
            int tx=(int)(x+xMove+bounds.x)/ Tile.TILEWIDTH;//Muchia din stanga

            if(!collisionWithTile(tx,(int)(y+bounds.y)/Tile.TILEWIDTH)&&!collisionWithTile(tx,(int)(y+bounds.y+bounds.height)/Tile.TILEHEIGHT)){
                //Daca nu este coliziune pe muchia stanga sus si jos
                x+=xMove;

            }
            else
            {
                x=tx*Tile.TILEWIDTH+Tile.TILEWIDTH-bounds.x;
            }
        }
    }

    public void moveY(){
        if(yMove<0){//Playerul merge in sus

            int ty=(int)(y+yMove+bounds.y)/Tile.TILEHEIGHT;//Muchia de sus a coliziunii playerului
            if(!collisionWithTile((int)(x+bounds.x)/Tile.TILEWIDTH,ty)&&(!collisionWithTile((int)(x+bounds.x+bounds.width)/Tile.TILEWIDTH,ty))){
                //Daca nu este coliziune pe muchia de sus in dreapta si stanga
                y+=yMove;
            }

            else
            {
                y=ty*Tile.TILEHEIGHT+Tile.TILEHEIGHT-bounds.y;

            }
        }else if(yMove>0){//Playerul merge in jos
            int ty=(int)(y+yMove+bounds.y+bounds.height)/Tile.TILEHEIGHT;//Muchia de jos
            if(!collisionWithTile((int)(x+bounds.x)/Tile.TILEWIDTH,ty)&&(!collisionWithTile((int)(x+bounds.x+bounds.width)/Tile.TILEWIDTH,ty))) {
                //Daca nu este coliziune pe muchia de jos in dreapta si stanga
                y += yMove;
            }
            else {
                y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
                falling = false;
            }
        }
    }
    //Daca playerul se da jos de pe un obiect fara a apasa tasta W aceasta metoda va activa gravitatia.
    public void movedown(){
        int ty=(int)(y+5+yMove+bounds.y+bounds.height)/Tile.TILEHEIGHT;//Muchia de jos +5 pixeli
        if(!checkEntityCollisions(0f,yMove+40))
        {
            if(!collisionWithTile((int)(x+bounds.x)/Tile.TILEWIDTH,ty)&&(!collisionWithTile((int)(x+bounds.x+bounds.width)/Tile.TILEWIDTH,ty))) {

                falling=true;
            }
        }
    }
    public boolean isColissionDownY(){
        int ty=(int)(y+5+bounds.y+bounds.height)/Tile.TILEHEIGHT;
        return collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) || (collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty));
    }
    public boolean isColissionUpY(){
        int ty=(int)(y-1+bounds.y)/Tile.TILEHEIGHT;
        return collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) || (collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty));
    }
    public boolean isCollisionRightX(){
        int tx=(int)(x+1+bounds.x+bounds.width)/ Tile.TILEWIDTH;
        return collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEWIDTH) || collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT);
    }
    public boolean isCollisionLeftX(){
        int tx=(int)(x-1+bounds.x)/ Tile.TILEWIDTH;

        return collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEWIDTH) || collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT);
    }
    protected boolean collisionWithTile(int x, int y){
        return handler.getWorld().getTile(x,y).isSolid();
    }
}
