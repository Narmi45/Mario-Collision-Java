import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Iterator;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;

class View extends JPanel
{
	Model model;
	BufferedImage background_image;
	BufferedImage ground_image;

	View(Controller c, Model m)
	{
		model = m;
		this.background_image = loadImage("background.png");
		this.ground_image = loadImage("ground.png");
	}

	static BufferedImage loadImage(String filename)
	{
		BufferedImage im = null;
		try
		{
			im = ImageIO.read(new File(filename));
			System.out.println(filename + " loaded");
		} catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
		return im;
	}

	public void paintComponent(Graphics g)
	{
		g.drawImage(background_image, 0 - model.mario.x / 2 - 1000, 0, 5000, this.getHeight(), null);
		g.drawImage(ground_image, 0 - model.mario.x, 495, 5000, 95, null);

		for (int i = 0; i < model.sprites.size(); i++)
		{
			Sprite s = model.sprites.get(i);
			s.draw(g);
			//System.out.println(model.sprites.get(i));		
		}
		//System.out.println(model.mario + "\n");
	}
}