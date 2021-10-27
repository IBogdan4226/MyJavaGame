package dev.codenmore.tilegame.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//Clasa ce contine o metoda statica pentru incarcarea unei imagini in memorie.
public class ImageLoader {
    public static BufferedImage loadImage(String path) throws IOException {
        try{
            return ImageIO.read(new File(path));
        }catch(IOException e){
            e.printStackTrace();

        }
        return null;
    }
}
