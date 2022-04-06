import java.awt.Graphics;
import java.awt.image.BufferedImage;
abstract class Sprite
{
    int x, y, w, h;
    int totalc = 5;
    int type;

    abstract void update();
    abstract void draw(Graphics g);
    abstract Json marshal();
    abstract boolean isBrick();
    abstract boolean isMario();
    abstract boolean isCoin();

    public void setAll(int Pos_X, int Pos_Y, int width, int height) //Brick SetAll 
    {
        x = Pos_X;
        y = Pos_Y;
        w = width;
        h = height;
    }
}
