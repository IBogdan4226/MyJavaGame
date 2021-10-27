package dev.codenmore.tilegame.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

//Clasa incarca fisierele grafice ale jocului
public class Assets {
    public static BufferedImage tilewinter,tilerock,tilebottom,ball,tileside,tilemiddle,tilespike,tileempty,arrow,heart,powerUpSpeed,powerUpArrow,timer;
    public static BufferedImage[] player_right,player_left,player_up,player_jumpright,player_jumpleft,player_downright,player_downleft,buttonNew,buttonExit,buttonResume,buttonSave,buttonLoad;
    public static void init() throws IOException {
        SpriteSheet sheet=new SpriteSheet(ImageLoader.loadImage("res/textures/Spritesheet.png"));

        player_right=new BufferedImage[4];
        player_right[0]=sheet.crop(55,540,89,156);
        player_right[1]=sheet.crop(205,540,89,156);
        player_right[2]=sheet.crop(373,540,89,156);
        player_right[3]=sheet.crop(545,540,89,156);

        player_left=new BufferedImage[4];
        player_left[0]=sheet.crop(55,697,89,156);
        player_left[1]=sheet.crop(201,697,89,156);
        player_left[2]=sheet.crop(365,697,89,156);
        player_left[3]=sheet.crop(546,697,89,156);

        player_up=new BufferedImage[1];
        player_up[0]=sheet.crop(710,540,89,156);

        player_jumpright=new BufferedImage[1];
        player_jumpright[0]=sheet.crop(545,540,89,156);

        player_jumpleft=new BufferedImage[1];
        player_jumpleft[0]=sheet.crop(546,697,89,156);

        player_downleft=new BufferedImage[1];
        player_downleft[0]=sheet.crop(365,697,89,156);

        player_downright=new BufferedImage[1];
        player_downright[0]=sheet.crop(373,540,89,156);

        buttonNew=new BufferedImage[2];
        buttonNew[0]=sheet.crop(1559,1102,300,85);
        buttonNew[1]=sheet.crop(1875,1102,300,85);

        buttonResume=new BufferedImage[2];
        buttonResume[0]=sheet.crop(1559,1187,300,66);
        buttonResume[1]=sheet.crop(1875,1187,300,66);

        buttonExit=new BufferedImage[2];
        buttonExit[0]=sheet.crop(1559,1253,300,85);
        buttonExit[1]=sheet.crop(1875,1254,300,85);

        tilewinter=sheet.crop(1000,591,450,125);
        tilerock=sheet.crop(1170,35,700,265);
        ball=sheet.crop(166,36,265,261);
        tilebottom=sheet.crop(1145,411,62,55);
        tileside=sheet.crop(1265,408,55,59);
        tilemiddle=sheet.crop(2426,604,101,100);
        tilespike=sheet.crop(134,1144,168,155);
        tileempty=sheet.crop(1900,900,100,100);
        arrow=sheet.crop(33,36,60,300);
        heart=sheet.crop(1940,82,32,33);
        powerUpSpeed=sheet.crop(2034,215,63,34);
        powerUpArrow=sheet.crop(1942,210,61,37);
        timer=sheet.crop(2038,64,58,60);

        buttonSave=new BufferedImage[2];
        buttonSave[0]=sheet.crop(2245,1190,119,56);
        buttonSave[1]=sheet.crop(2366,1190,119,56);

        buttonLoad=new BufferedImage[2];
        buttonLoad[0]=sheet.crop(2202,1329,165,53);
        buttonLoad[1]=sheet.crop(2370,1329,165,53);

    }
}
