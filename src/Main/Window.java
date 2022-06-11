package Main;

import javax.swing.JFrame;

public class Window {

	public Window(int WIDTH, int HEIGHT, Game game, Mouse mouse) {
		
		JFrame frame = new JFrame("3D Cube");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, 810);
		
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		
		
		game.setSize(WIDTH, 810);
		
		game.addMouseListener(mouse);
		game.addKeyListener(mouse);
		game.addMouseMotionListener(mouse);
		
		
		frame.setVisible(true);
		
		
		
		game.start();
		
	}
	
	

}
