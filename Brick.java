import java.awt.Graphics;
import java.awt.image.BufferedImage;
public class Brick extends Sprite
{
    boolean illuminate;
    static BufferedImage image = null;
    static BufferedImage imagec = null;
    static BufferedImage imagec2 = null;

    Model model;
    
    public Brick(Model m, int t) 
    {
        loadImage();
        model = m;
        type = t;

    }
    
    
    public Brick(Json ob, Model m)
    {
        h = (int)ob.getLong("h");
        w = (int)ob.getLong("w");
        y = (int)ob.getLong("y");
        x = (int)ob.getLong("x");
        type = (int)ob.getLong("type");
        loadImage();
        model = m;
    
    }

    void loadImage()
    {
        if (image == null)
        {
            image = View.loadImage("brick.png");
        }

        if (imagec == null)
        {
            imagec = View.loadImage("block1.png");
        }

        if (imagec2 == null)
        {
            imagec2 = View.loadImage("block2.png");
        }
        
    }

    Json marshal()
    {
        Json ob = Json.newObject();
        ob.add("x", x);
        ob.add("y", y);
        ob.add("w", w);
        ob.add("h", h);
        ob.add("type", type);
        return ob;
    }

    void draw(Graphics g)
    {
        if(type == 0)
        {
            g.drawImage(image, x - model.mario.x + model.mario.marioScreenLocation, y, w, h, null);
        }
        else if(type == 1)
        {
            g.drawImage(imagec, x - model.mario.x + model.mario.marioScreenLocation, y, w, h, null);
        }
        else if(type == 2)
        {
            g.drawImage(imagec2, x - model.mario.x + model.mario.marioScreenLocation, y, w, h, null);
        }
        
        
    }

    void update()
    {
        if(type == 1 && totalc <= 0)
        {
            type = 2;
        }
    }

    @Override
    public String toString()
    {
        return "Brick located at (" + x + ", " + y  + ") with a width = " + w + " and a height = " + h;
    }

    @Override
    boolean isBrick()
    {
        return true;
    }

    @Override
    boolean isMario()
    {
        return false;
    }

    @Override
    boolean isCoin()
    {
        return false;
    }


}