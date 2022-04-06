import java.awt.Graphics;
import java.awt.image.BufferedImage;
import org.w3c.dom.css.Counter;

public class Mario extends Sprite
{
    double vert_velocity;
    static BufferedImage[] images = null;
    int imageNum;
    static int marioScreenLocation;
    int CounterJ;
    static int x_position;

    public Mario()
    {
        marioScreenLocation = 300;
        // x = 0;
        // y = 0;
        w = 60;
        h = 95;
        imageNum = 3;
        vert_velocity = 0;
        int CounterJ = 0;
        if (images == null)
        {
            images = new BufferedImage[5];
            images[0] = View.loadImage("mario1.png");
            images[1] = View.loadImage("mario2.png");
            images[2] = View.loadImage("mario3.png");
            images[3] = View.loadImage("mario4.png");
            images[4] = View.loadImage("mario5.png");
            
        }
    }

    void updateImageNum()
    {
        imageNum++;
        if(imageNum > 4)
            imageNum = 0;
    }

    public void update()
    {
        vert_velocity += 2.1; // this is gravity
        y += vert_velocity; //update position
        if(y > 400)
        {
            vert_velocity = 0;
            y = 400;
            CounterJ = 0;
        }
        else {
            CounterJ++;
        }

        x_position = x;


    }
    @Override
    public String toString()
    {
        return "Mario located at (" + x + ", " + y  + ") with a width = " + w + " and a height = " + h;
    }

    @Override
    void draw(Graphics g)
    {
        g.drawImage(images[imageNum], marioScreenLocation, y, null);
    }

    @Override
    boolean isBrick()
    {
        return false;
    }

    @Override
    boolean isMario()
    {
        return true; 
    }

    @Override
    boolean isCoin()
    {
        return false;
    }


    Json marshal()
    {
        Json ob = Json.newObject();
        ob.add("x", x);
        ob.add("y", y);
        ob.add("w", w);
        ob.add("h", h);
        return ob;
    }


}
