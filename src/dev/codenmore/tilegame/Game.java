package dev.codenmore.tilegame;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.Scanner;

import dev.codenmore.tilegame.World.World;
import dev.codenmore.tilegame.display.Display;
import dev.codenmore.tilegame.entity.Entity;
import dev.codenmore.tilegame.entity.Player;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.gfx.GameCamera;
import dev.codenmore.tilegame.state.GameState;
import dev.codenmore.tilegame.state.MenuState;
import dev.codenmore.tilegame.state.State;
import dev.codenmore.tilegame.input.MouseManager;
import dev.codenmore.tilegame.input.KeyManager;

//Clasa principala a intregului proiect. Implementeaza Game - Loop (Update -> Draw) de 60 de ori pe secunda
public class Game implements Runnable {
    //Singleton
    private static Game instance = null;


    private Display display;
    private final int width;
    private final int height;
    public String title;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    //States
    public State gameState;
    public State menuState;

    //Input
    private final KeyManager keyManager;
    private final MouseManager mouseManager;

    //Camera
    private GameCamera gameCamera;

    //Handler
    private Handler handler;

    private Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }

    public static Game getInstance(String title, int width, int height) {
        if (instance == null) {
            instance = new Game(title, width, height);
        }
        return instance;
    }

    //Construieste fereastra grafica si se ataseaza managerul pt tastatura,mouse si tile urile.
    private void init() throws IOException {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();

        handler = new Handler(this);
        gameCamera = new GameCamera(handler, 0, 0);

        gameState = new GameState(handler,  1);
        menuState = new MenuState(handler, "res/textures/wallpaper 2.jpeg");
        State.setState(menuState);
    }

    //Actualizeaza starea elementelor din joc
    //Este privata deoarece trebuie apelata doar in metoda run()
    private void tick() {
        keyManager.tick();

        if (State.getState() != null)
            State.getState().tick();
        if (Player.getHealth() == 0) {

            State.setState(menuState);
            Player.restoreHealth();
            Entity.GAMEOVER = true;
            Scanner sc= new Scanner(System.in);
            int aux;
            System.out.println("Doriti sa salvati scorul?1-Da//0-Nu");
            aux=sc.nextInt();
            if (aux == 1) {
                System.out.print("Introdu numele pentru a salva scorul ");
                String name = sc.next();
                SQLCLASS.addScore(name, Player.scor, handler.getLevel(), 0);
            }



        }
        if (handler.getKeyManager().esc) {
            State.setState(menuState);
        }
        if (Entity.GAMEOVER == true || Entity.GAMEWON == true) {

            State.setState(menuState);

        }
    }

    //Deseneaza elementele grafice in fereastra coresponzator starilor actualizate ale elementelor.
    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, width, height);
        if (State.getState() != null)
            State.getState().render(g);

        bs.show();
        g.dispose();
    }

    //Metoda redeseneaza tabla de joc de 60 de ori pe secunda
    public void run() {

        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }

            if (timer >= 1000000000) {
                ticks = 0;
                timer = 0;
            }
        }

        stop();

    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setGameState(GameState a) {
        this.gameState = a;
    }

    //Creaza si starteaza firul separat de executie (thread).
    //Metoda trebuie sa fie declarata synchronized pentru ca apelul acesteia sa fie semaforizat.
    public synchronized void start() {
        if (running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    //Opreste executie thread-ului.
    public synchronized void stop() {
        if (!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

