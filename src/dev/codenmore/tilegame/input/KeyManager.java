package dev.codenmore.tilegame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//Clasa citeste daca s-a apasat o tasta si seteaza variabila tastei corespunzatoare ca true
public class KeyManager implements KeyListener {
    private final boolean []keys;
    public boolean up,down,left,right,space,esc;
    public KeyManager(){
        keys=new boolean[100];
    }
    public void tick(){
        up=keys[KeyEvent.VK_W];
        down=keys[KeyEvent.VK_S];
        left=keys[KeyEvent.VK_A];
        right=keys[KeyEvent.VK_D];
        space=keys[KeyEvent.VK_SPACE];
        esc=keys[KeyEvent.VK_ESCAPE];
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Pt a nu primi erori cand apasam o tasta gresita
        if(e.getKeyCode()<0||e.getKeyCode()>= keys.length)
            return;

        //Se retine ca tasta corespunzatoare a fost apasata
        keys[e.getKeyCode()]=true;

    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()]=false;
    }
}
