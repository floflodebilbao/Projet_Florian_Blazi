import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import javax.imageio.ImageIO;

public class DynamicSprite extends SolidSprite {
    private Direction direction = Direction.EAST;
    private final double initial_speed = 3;
    private double speed = 3;
    private double timeBetweenFrame = 250;
    private boolean isWalking =true;
    private final int spriteSheetNumberOfColumn = 10;
    private float life = 90;
    private float maxlife = 100;
    private int damage = 0;

    public DynamicSprite(double x, double y, Image image, double width, double height) {
        super(x, y, image, width, height);
    }

    private boolean isMovingPossible(ArrayList<Sprite> environment){
        Rectangle2D.Double moved = new Rectangle2D.Double();
        switch(direction){
            case EAST: moved.setRect(super.getHitBox().getX()+speed,super.getHitBox().getY(),
                                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case WEST:  moved.setRect(super.getHitBox().getX()-speed,super.getHitBox().getY(),
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case NORTH:  moved.setRect(super.getHitBox().getX(),super.getHitBox().getY()-speed,
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case SOUTH:  moved.setRect(super.getHitBox().getX(),super.getHitBox().getY()+speed,
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
        }

        for (Sprite s : environment){
            if ((s instanceof SolidSprite) && (s!=this)){
                if (((SolidSprite) s).intersect(moved)){
                    return false;
                }
            }
        }
        return true;
    }

    public void lose_life(){
        float Life = life - 10;
        this.life = Math.max(0,Life);
    }

    public boolean take_dmg(ArrayList<Sprite> traps){
        Rectangle2D.Double moved = new Rectangle2D.Double();
        switch(direction){
            case EAST: moved.setRect(super.getHitBox().getX()+speed,super.getHitBox().getY(),
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case WEST:  moved.setRect(super.getHitBox().getX()-speed,super.getHitBox().getY(),
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case NORTH:  moved.setRect(super.getHitBox().getX(),super.getHitBox().getY()-speed,
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case SOUTH:  moved.setRect(super.getHitBox().getX(),super.getHitBox().getY()+speed,
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
        }
        for (Sprite s : traps){
            if ((s instanceof SolidSprite) && (s!=this)){
                if (((SolidSprite) s).intersect(moved)){
                    return true;
                }
            }
        }
        return false;
    }

    public void accelerate(){
        this.speed = 10;
    }

    public void initialize_speed(){
        this.speed = initial_speed;
    }

    public void stops(){
        this.speed = 0;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    private void move(){
        switch (direction){
            case NORTH -> {
                this.y-=speed;
            }
            case SOUTH -> {
                this.y+=speed;
            }
            case EAST -> {
                this.x+=speed;
            }
            case WEST -> {
                this.x-=speed;
            }
        }
    }

    public void shoot_fireball_en_bien(Direction BDF_dir) {
        try{
            DynamicSprite BDF = new DynamicSprite(x,y,
                    ImageIO.read(new File("./img/BDF.png")),48,50);
            BDF.direction = BDF_dir;
            BDF.speed = 30;
            BDF.damage = 10;
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void moveIfPossible(ArrayList<Sprite> environment){
        if (isMovingPossible(environment)){
            move();
        }
    }

    public void is_dmg(ArrayList<Sprite> traps){
        if (take_dmg(traps)){
            lose_life();
        }
    }

    public void change_dir(){
        if (this.direction == Direction.NORTH){
            this.setDirection(Direction.EAST);
        } else if (this.direction == Direction.EAST){
            this.setDirection(Direction.SOUTH);
        }else if (this.direction == Direction.SOUTH){
            this.setDirection(Direction.WEST);
        }else if (this.direction == Direction.WEST){
            this.setDirection(Direction.NORTH);
        }
    }

    public void isEnnemy(){
        this.setDirection(Direction.NORTH);
        this.damage = 10;
        Timer ennemy_timer = new Timer(1000,(time)-> this.change_dir());
        ennemy_timer.start();
    }

    @Override
    public void draw(Graphics g) {
        int index= (int) (System.currentTimeMillis()/timeBetweenFrame%spriteSheetNumberOfColumn);
        int HB_width = 58;
        int HB_height = 11;
        int life_width = (int) (HB_width * (life / maxlife));

        if(life>0) {
            g.drawImage(image, (int) x, (int) y, (int) (x + width), (int) (y + height),
                    (int) (index * this.width), (int) (direction.getFrameLineNumber() * height),
                    (int) ((index + 1) * this.width), (int) ((direction.getFrameLineNumber() + 1) * this.height), null);

            g.setColor(Color.RED);
            g.fillRect((int) x - 5, (int) y - 10, HB_width, HB_height);

            g.setColor(Color.GREEN);
            g.fillRect((int) x - 5, (int) y - 10, life_width, HB_height);

            g.setColor(Color.BLACK);
            g.drawString((int) life + "/" + (int) maxlife, (int) x - 1, (int) y);
        }
    }
}
