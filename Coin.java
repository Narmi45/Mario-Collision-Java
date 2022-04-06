import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.lang.Math;

public class Coin extends Sprite
{

    static BufferedImage imagecoin = null;
    double rand = Math.random();
    double vert_vel = 0;
    double hor_vel = 0;
    int min = -25;
    int max = 25;
    int range;
    Coin(int x_c, int y_c, int w_c, int h_c)
    {
        loadImage();
        x = x_c;
        y = y_c;
        w = w_c;
        h = h_c;
        range = max - min;
        hor_vel = (int)(Math.random() * range) + min;
        vert_vel = -20;

    }


    void loadImage()
    {
        if (imagecoin == null)
        {
            imagecoin = View.loadImage("coin.png");
        }
    }


    @Override
    void update() {
        x += hor_vel;
        vert_vel += 2.1; // this is gravity
        y += vert_vel; //update position
    
    }
  
    void draw(Graphics g) {
        g.drawImage(imagecoin, x - Mario.x_position + Mario.marioScreenLocation, y, w, h, null);
    }

    @Override
    public String toString()
    {
        return "Coin located at (" + x + ", " + y  + ") with a width = " + w + " and a height = " + h;
    }


    @Override
    Json marshal() {
         
        return null;
    }


    @Override
    boolean isBrick() {
         
        return false;
    }


    @Override
    boolean isMario() {
         
        return false;
    }

    @Override
    boolean isCoin()
    {
        return true;
    }

}

