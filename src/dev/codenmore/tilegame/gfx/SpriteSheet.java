package dev.codenmore.tilegame.gfx;

import java.awt.image.BufferedImage;

//Clasa va salva imaginea  in care se va afla SpriteSheet-ul si ne va ajuta sa decupam portiuni exacte pentru a alege assets urile
public class SpriteSheet {
    private final BufferedImage sheet;
    public SpriteSheet(BufferedImage sheet)
    {
        this.sheet=sheet;

    }
    public BufferedImage crop(int x,int y, int width,int height)
    {
        return sheet.getSubimage(x,y,width,height);
    }
}
