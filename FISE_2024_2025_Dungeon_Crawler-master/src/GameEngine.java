import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameEngine implements Engine, KeyListener {
    DynamicSprite hero;

    public GameEngine(DynamicSprite hero) {
        this.hero = hero;
    }

    @Override
    public void update() {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP :
                //hero.initialize_speed();
                hero.setDirection(Direction.NORTH);
                break;
            case KeyEvent.VK_DOWN:
                //hero.initialize_speed();
                hero.setDirection(Direction.SOUTH);
                break;
            case KeyEvent.VK_LEFT:
                //hero.initialize_speed();
                hero.setDirection(Direction.WEST);
                break;
            case KeyEvent.VK_RIGHT:
                //hero.initialize_speed();
                hero.setDirection(Direction.EAST);
                break;
            case KeyEvent.VK_Z:
                hero.accelerate();
                break;
            case KeyEvent.VK_A:
                hero.shoot_fireball_en_bien(Direction.NORTH);
                System.out.println("Pour la France !!!!!!!!");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_Z:
                hero.initialize_speed();
                break;
            /*case KeyEvent.VK_UP :
                hero.stops();
                break;
            case KeyEvent.VK_DOWN:
                hero.stops();
                break;
            case KeyEvent.VK_LEFT:
                hero.stops();
                break;
            case KeyEvent.VK_RIGHT:
                hero.stops();
                break;*/
        }
    }
}
