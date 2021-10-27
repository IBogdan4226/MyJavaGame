package dev.codenmore.tilegame.display;

import javax.swing.*;
import java.awt.*;
//Implementeaza fereastra jocului
public class Display {
    private Canvas canvas;
    private JFrame frame;
    private final String title;
    private final int width;
    private final int height;
    public Display(String title, int width, int height){
        this.title=title;
        this.width=width;
        this.height=height;
        createDisplay();
    }
    private void createDisplay(){
        //Fereastra
        frame=new JFrame(title);
        frame.setSize(width,height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Asa ne asiguram ca tot programul va fi inchis atunci cand apasam pe x-ul ferestrei
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        //Fereastra apare in mijlocul ecranului
        frame.setVisible(true);

        //"Panza" pe care urmeaza sa fie desenat jocul
        canvas=new Canvas();
        canvas.setPreferredSize(new Dimension(width,height));
        canvas.setMaximumSize((new Dimension(width,height)));
        canvas.setMinimumSize(new Dimension(width,height));
        canvas.setFocusable(false);

        frame.add(canvas);
        /// Urmatorul apel de functie are ca scop eventuala redimensionare a ferestrei
        /// ca tot ce contine sa poate fi afisat complet
        frame.pack();


    }
    public Canvas getCanvas(){
        return canvas;
    }
    public JFrame getFrame(){
        return frame;
    }
}
