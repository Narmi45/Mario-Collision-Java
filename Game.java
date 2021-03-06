import javax.swing.JFrame;
import java.awt.Toolkit;

public class Game extends JFrame
{
	Controller controller;
	Model model;
	View view;
	
	public Game()
	{
		model = new Model();
		controller = new Controller(model);
		view = new View(controller, model);

		view.addMouseListener(controller);
		view.addMouseMotionListener(controller);
		this.addKeyListener(controller);

		this.setTitle("Assignment 5");
		this.setSize(800, 600);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	public void run()
{
	while(true)
	{
		controller.update();
		model.update();
		view.repaint(); // Indirectly calls View.paintComponent
		Toolkit.getDefaultToolkit().sync(); // Updates screen

		// Go to sleep for 40 miliseconds
		try
		{
			Thread.sleep(40);
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}

	public static void main(String[] args)
	{
		Game g = new Game();
		g.run();
	}
}
