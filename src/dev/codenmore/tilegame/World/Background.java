package dev.codenmore.tilegame.World;

import dev.codenmore.tilegame.Game;
import dev.codenmore.tilegame.Handler;

import javax.swing.*;
import java.awt.*;

public class Background  extends JPanel {
    private final Handler handler;
    private final Image pic;
    public Background(Handler handler, String path){
        this.handler=handler;
        ImageIcon obj=new ImageIcon( path);
        pic=obj.getImage();


    }
    public void render(Graphics g){
        super.paintComponent(g);
        g.drawImage(pic,0,0,1024,720,null);
    }
    public void tick(){

    }
}
