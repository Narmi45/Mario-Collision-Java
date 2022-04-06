import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.security.Key;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements ActionListener, MouseListener, KeyListener, MouseMotionListener
{
	View view;
	Model model;
	boolean keyLeft;
	boolean keyRight;
	boolean keySpace;
	boolean edit = false;
	boolean dragging = false;
	int initialX, initialY;
	int finalX, finalY;
	int Pos_X, Pos_Y;
	int width;
	int height;
	int x, y, w, h;
	int t;

	Controller(Model m)
	{
		model = m;
	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}

	void setView(View v)
	{
		view = v;
	}

	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = true; break;
			case KeyEvent.VK_LEFT: keyLeft = true; break;
			case KeyEvent.VK_SPACE: if(model.mario.CounterJ <= 5) {keySpace = true; }break;
		}
	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = false; break;
			case KeyEvent.VK_LEFT: keyLeft = false; break;
			case KeyEvent.VK_SPACE: keySpace  = false; break;
			case KeyEvent.VK_T: t++; if(t == 2){t = 0; System.out.println(t + " mode is on"); } break;
		}

		char c = e.getKeyChar();
		System.out.println(c);
		if (c == 's' || c == 'S')
		{
			model.marshal().save("map.json");
			System.out.println("map saved. . .");
		}

		if(c == 'l' || c =='L')
		{
			Json j = Json.load("map.json");
			model.unmarshal(j);
			System.out.println("You have loaded map.json");
		}

		if(c == 'e' || c == 'E')
		{
			edit = !edit;
			System.out.println("Edit mode: " + edit);
		}

	}

	public void keyTyped(KeyEvent e)
	{
	}

	void update()
	{
		if(keyRight)
		{
			model.mario.x += 10;
			model.mario.updateImageNum();
		}
		if(keyLeft)
		{
			model.mario.x -= 10;
			model.mario.updateImageNum();
		}
		if(keySpace)
		{
			model.mario.y -= 30;
			model.mario.updateImageNum(); 
		}
	}


	public void mousePressed(MouseEvent e)
	{
	if(edit == true)
		{
		initialX = e.getX() + model.mario.x - model.mario.marioScreenLocation;
		initialY = e.getY();
		model.sprites.add(new Brick(model, t));
		}
	}

	public void mouseDragged(MouseEvent e)
	{
	if(edit == true)
		{
		Sprite current = model.sprites.get(model.sprites.size()-1);

		finalX = e.getX() + model.mario.x - model.mario.marioScreenLocation;
		finalY = e.getY();
		Pos_X = initialX;
		Pos_Y = initialY;
		width = finalX - Pos_X;
		height = finalY - Pos_Y;

		if (width < 0)
		{
			Pos_X = finalX;
			width = initialX - finalX;
		}
		if (height < 0)
		{
			Pos_Y = finalY;
			height = initialY - finalY;
		}
	current.setAll(Pos_X, Pos_Y, width, height);
		}
	}
	
	public void mouseReleased(MouseEvent e) 
	{  
	}

	public void actionPerformed(ActionEvent e)
	{
	}
}
