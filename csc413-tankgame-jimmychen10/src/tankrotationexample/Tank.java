package tankrotationexample;


import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Tank{


    private int x;
    private int y;
    private int vx;
    private int vy;
    private int angle;
    private int health;
    private int lives;
    private String player;

    private final int R = 2;
    private final int ROTATIONSPEED = 4;

    private CollisionCheck collision;



    private boolean collide_with_immovable_object = false;


    private BufferedImage img;
    private BufferedImage bullet;
    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean ShootPressed;
    Rectangle tank_box;
    Rectangle health_bar;
    Rectangle lives_bar;
    Rectangle tank_front_side;
    Rectangle tank_back_side;


    Tank(int x,  int y, int vx, int vy, int angle,String player, BufferedImage img, BufferedImage bullet) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.img = img;
        this.bullet = bullet;
        this.angle = angle;
        this.player = player;
        this.health = 100;
        this.lives = 3;
        this.tank_box = new Rectangle(this.x,this.y,this.img.getWidth(),this.img.getHeight());
        this.health_bar = new Rectangle(this.x,this.y+50,this.img.getWidth(),this.img.getHeight());


    }


    void toggleUpPressed() {
        this.UpPressed = true;
    }

    void toggleDownPressed() {
        this.DownPressed = true;
    }

    void toggleRightPressed() {
        this.RightPressed = true;
    }

    void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    void unToggleUpPressed() {
        this.UpPressed = false;
    }

    void unToggleDownPressed() {
        this.DownPressed = false;
    }

    void unToggleRightPressed() {
        this.RightPressed = false;
    }

    void unToggleLeftPressed() {
        this.LeftPressed = false;
    }

    void shootProjectile() {
        this.ShootPressed = true;
        TRE.projectiles.add(new Projectile(x,y,vx,vy,player,angle,bullet));
        

    }
    void unProjectile() {
        this.ShootPressed = false;

    }


    public void update() {
        if (this.UpPressed) {
            this.moveForwards();
        }
        if (this.DownPressed) {
            this.moveBackwards();
        }

        if (this.LeftPressed) {
            this.rotateLeft();
        }
        if (this.RightPressed) {
            this.rotateRight();
        }


    }

    private void rotateLeft() {
        this.angle -= this.ROTATIONSPEED;
    }

    private void rotateRight() {
        this.angle += this.ROTATIONSPEED;
    }

    private void moveBackwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));

        if(collide_with_immovable_object){
            x = x + 4*vx;
            y = y + 4*vy;
            collide_with_immovable_object = false;
        }
        x -= vx;
        y -= vy;
        checkBorder();

    }

    private void moveForwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        if(collide_with_immovable_object){
            x = x - 4*vx;
            y = y - 4*vy;
            collide_with_immovable_object = false;
        }else{
            x += vx;
            y += vy;
        }
        checkBorder();

    }




    private void checkBorder() {
        if (x < 30) {
            x = 30;
        }
        if (x >= TRE.WORLD_WIDTH - 88) {
            x = TRE.WORLD_WIDTH - 88;
        }
        if (y < 40) {
            y = 40;
        }
        if (y >= TRE.WORLD_HEIGHT - 80) {
            y = TRE.WORLD_HEIGHT - 80;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getVx() {
        return vx;
    }

    public int getVy() {
        return vy;
    }

    public int getAngle() {
        return angle;
    }


    public void damage(Projectile b){
        this.health -= b.getDamage();
    }

    public boolean getCollide_with_tank() {
        return collide_with_immovable_object;
    }


    public void setCollide_with_immovable_object(boolean collide_with_immovable_object) {
        this.collide_with_immovable_object = collide_with_immovable_object;
    }


    public BufferedImage getImg() { return this.img; }



    void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.img.getWidth() / 2.0, this.img.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img, rotation, null);

        if(this.health <= 0 ){
            if(this.lives>=0){
                this.lives -=1;
                this.health = 100;
            }

        }

        if(this.health >70){
            g.setColor(Color.green);
        }
        else if(this.health >30){
            g.setColor(Color.yellow);
        }
        else{
            g.setColor(Color.red);
        }

        g.fillRect(x,y+50,this.health/2, this.img.getHeight()/2/2);
        g.setColor(Color.cyan);


        for(int i= 0; i<this.lives; i++){
            g.fillOval(x+(i*(this.img.getHeight()/2/2)),y+65,this.img.getHeight()/2/2, this.img.getHeight()/2/2);
        }




    }



}
