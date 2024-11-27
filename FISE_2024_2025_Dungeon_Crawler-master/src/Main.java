import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {

    JFrame displayZoneFrame;
    JFrame loading_screen;
    RenderEngine renderEngine;
    GameEngine gameEngine;
    PhysicEngine physicEngine;

    public Main() throws Exception{

        displayZoneFrame = new JFrame("Java Labs");
        displayZoneFrame.setSize(400,600);
        displayZoneFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);


        DynamicSprite hero = new DynamicSprite(200,300,
                ImageIO.read(new File("./img/heroTileSheetLowRes.png")),48,50);

        DynamicSprite ennemy_1 = new DynamicSprite(200,300,
                ImageIO.read(new File("./img/heroTileSheetLowRes.png")),48,50);

        ennemy_1.isEnnemy();
        renderEngine = new RenderEngine(displayZoneFrame);
        physicEngine = new PhysicEngine();
        gameEngine = new GameEngine(hero);

        Timer renderTimer = new Timer(50,(time)-> renderEngine.update());
        Timer gameTimer = new Timer(50,(time)-> gameEngine.update());
        Timer physicTimer = new Timer(50,(time)-> physicEngine.update());

        renderTimer.start();
        gameTimer.start();
        physicTimer.start();

        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);

        Playground level = new Playground("./data/level1.txt");
        //SolidSprite testSprite = new DynamicSprite(100,100,test,0,0);
        renderEngine.addToRenderList(level.getSpriteList());
        renderEngine.addToRenderList(hero);
        physicEngine.addToMovingSpriteList(hero);
        renderEngine.addToRenderList(ennemy_1);
        physicEngine.addToMovingSpriteList(ennemy_1);
        physicEngine.setEnvironment(level.getSolidSpriteList_1());
        physicEngine.setTraps(level.getSolidSpriteList_2());


        displayZoneFrame.addKeyListener(gameEngine);
    }

    public static void main (String[] args) throws Exception {
	// write your code here
        Main main = new Main();
    }
}