package dev.codenmore.tilegame.state;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.SQLCLASS;
import dev.codenmore.tilegame.World.Background;
import dev.codenmore.tilegame.entity.Entity;
import dev.codenmore.tilegame.entity.Player;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.ui.ClickListener;
import dev.codenmore.tilegame.ui.UIImageButton;
import dev.codenmore.tilegame.ui.UIManager;
import java.awt.*;

//Implementeaza meniul de inceput al jocului si locul in care jucatorul se intoarce dupa ce pierde sau castiga.
public class MenuState extends State{
    private static boolean auxGameOver=false;
    private static boolean auxGameWon=false;
    private static UIManager uiManager;
    private final Background background;
    public MenuState(Handler handler,String path){
        super(handler,path);
        background=new Background(handler,"res/textures/wallpaper 2.jpeg");
        uiManager=new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);

        uiManager.addObject(new UIImageButton(362, 60, 300, 200, Assets.buttonNew, new ClickListener() {
            @Override
            public void onClick() {
                handler.getGame().setGameState(new GameState(handler, 1));
                State.setState(handler.getGame().gameState);
            }
        }));
        uiManager.addObject(new UIImageButton(362, 260, 300, 200, Assets.buttonResume, new ClickListener() {
            @Override
            public void onClick() {

                int aux[]=SQLCLASS.getScore();
                if(aux[1]!=0&&aux[1]<=4&&aux[2]!=0){
                    handler.getGame().setGameState(new GameState(handler, aux[1]));
                    Player.scor=aux[0];
                    if(aux[2]==2){
                        Player.takeDamage();
                    }
                    if(aux[2]==1){
                        Player.takeDamage();
                        Player.takeDamage();
                    }
                    State.setState(handler.getGame().gameState);
                }
                else
                {
                    handler.getGame().setGameState(new GameState(handler, 1));
                    State.setState(handler.getGame().gameState);
                }

            }
        }));
        uiManager.addObject(new UIImageButton(362, 460, 300, 200, Assets.buttonExit, new ClickListener() {
            @Override
            public void onClick() {
                System.exit(0);
            }
        }));
        uiManager.addObject(new UIImageButton(100, 100, 150, 100, Assets.buttonSave, new ClickListener() {
            @Override
            public void onClick() {

                SQLCLASS.addSave(Player.scor,handler.getLevel(),Player.getHealth());
            }
        }));
        uiManager.addObject(new UIImageButton(780, 100, 150, 100, Assets.buttonLoad, new ClickListener() {
            @Override
            public void onClick() {
                State.setState(handler.getGame().gameState);
            }
        }));


    }
    @Override
    public void tick() {
        background.tick();
        uiManager.tick();

    }

    @Override
    public void render(Graphics g) {
        background.render(g);
        uiManager.render(g);

        drawEnd(g);
    }
    public static UIManager getUiManager(){
        return uiManager;
    }
    public void drawEnd(Graphics g){
        if(Entity.GAMEOVER) {
            auxGameOver = true;
        }
        if(auxGameOver==true){
            g.setFont(new Font("Serif", Font.PLAIN, 50));
            g.setColor(Color.blue);
            g.drawString("GAME OVER", 360, 50);
            Entity.GAMEOVER=false;
        }
        if(Entity.GAMEWON) {
            auxGameWon = true;
        }
        if(auxGameWon==true){
            g.setFont(new Font("Serif", Font.PLAIN, 50));
            g.setColor(Color.blue);
            g.drawString("GAME WON", 370, 50);
            Entity.GAMEWON=false;
        }
    }



    static void changeaux(){
        auxGameWon=false;
        auxGameOver=false;
    }
}
