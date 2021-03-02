package tankrotationexample;

import java.awt.Rectangle;

public class CollisionCheck {

    Rectangle Boundries;
    Rectangle tank;
    Rectangle tank_front_side;
    Rectangle tank_back_side;
    Rectangle tank_left_side;
    Rectangle tank_right_side;


    Rectangle bullet;
    Rectangle breakableWall;
    Rectangle nonBreakableWall;

    Rectangle t1;
    Rectangle t2;

    public CollisionCheck(){

    }
    public boolean collision_bullet_with_nonwall(Projectile b, NonBreakableWall w) {
        this.nonBreakableWall = new Rectangle(w.getX(), w.getY(), w.getImg().getWidth(), w.getImg().getHeight());
        this.bullet = new Rectangle(b.getX(), b.getY(), b.getImg().getWidth(), b.getImg().getHeight());

        if (this.bullet.intersects(this.nonBreakableWall)) {
            return true;
        }
        else {
            return false;
        }
    }
        public boolean collision_bullet_with_tank(Projectile b, Tank t1){


            this.bullet = new Rectangle(b.getX(),b.getY(),b.getImg().getWidth(),b.getImg().getHeight());
            this.t1= new Rectangle(t1.getX(),t1.getY(),t1.getImg().getWidth(),t1.getImg().getHeight());

            if (this.bullet.intersects(this.t1)){
                return true;
            }
            else{
                return false;
            }

    }
    public boolean collision_bullet_with_wall(Projectile b, BreakableWall w){
        this.breakableWall= new Rectangle(w.getX(),w.getY(),w.getImg().getWidth(),w.getImg().getHeight());

        this.bullet = new Rectangle(b.getX(),b.getY(),b.getImg().getWidth(),b.getImg().getHeight());

        if (this.bullet.intersects(this.breakableWall)){
            return true;
        }
        else{
            return false;
        }

    }


    public boolean collision_tank_with_tank(Tank t1, Tank t2){

        this.t1= new Rectangle(t1.getX(),t1.getY(),t1.getImg().getWidth(),t1.getImg().getHeight());

        this.t2 = new Rectangle(t2.getX(),t2.getY(),t2.getImg().getWidth(),t2.getImg().getHeight());

        if(this.t1.intersects(this.t2)){
            return true ;
        }
        else{
            return false;
        }
    }
    public boolean collision_tank_with_bwall(Tank t1, BreakableWall w ){
        this.t1= new Rectangle(t1.getX(),t1.getY(),t1.getImg().getWidth(),t1.getImg().getHeight());
        this.breakableWall= new Rectangle(w.getX(),w.getY(),w.getImg().getWidth(),w.getImg().getHeight());

        if(this.t1.intersects(this.breakableWall)){

            return true ;
        }
        else{
            return false;
        }

    }
    public boolean collision_tank_with_nbwall(Tank t1, NonBreakableWall w ){
        this.t1= new Rectangle(t1.getX(),t1.getY(),t1.getImg().getWidth(),t1.getImg().getHeight());
        this.nonBreakableWall= new Rectangle(w.getX(),w.getY(),w.getImg().getWidth(),w.getImg().getHeight());

        if(this.t1.intersects(this.nonBreakableWall)){

            return true ;
        }
        else{
            return false;
        }

    }


}
