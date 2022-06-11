package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener, KeyListener {

	Game game;
	double speed;
	double rotationSpeed;

	public static boolean isButton1Pressed = false;
	public static int button = 0;

	public static int mouseX = 0, mouseY = 0;

	public Mouse(Game game) {
		this.game = game;
		speed = Game.speed;
		rotationSpeed = game.rotationSpeed;
	}

	public void mouseClicked(MouseEvent m) {

	}

	public void mouseEntered(MouseEvent arg0) {

	}

	public void mouseExited(MouseEvent arg0) {

	}

	public void mousePressed(MouseEvent m) {

		button = m.getButton();

		/*
		 * if (m.getButton() == 2) {
		 * 
		 * game.weight.position.x = Math.random() * Game.WIDTH;
		 * game.weight.position.y = Math.random() * Game.HEIGHT;
		 * 
		 * game.weight.velocity.setLength((Math.random() * 50) + 50);
		 * game.weight.velocity.setAngle(Math.random() * (Math.PI * 2));
		 * 
		 * }
		 */

	}

	public void mouseReleased(MouseEvent m) {

		button = 0;

	}

	public void keyPressed(KeyEvent ke) {

		if (ke.getKeyCode() == KeyEvent.VK_W) {
			game.translateModel(0, -speed, 0);
		} else if (ke.getKeyCode() == KeyEvent.VK_S) {
			game.translateModel(0, speed, 0);
		} else if (ke.getKeyCode() == KeyEvent.VK_A) {
			game.translateModel(-speed, 0, 0);
		} else if (ke.getKeyCode() == KeyEvent.VK_D) {
			game.translateModel(speed, 0, 0);
		} else if (ke.getKeyCode() == KeyEvent.VK_A) {
			game.translateModel(-speed, 0, 0);
		} else if (ke.getKeyCode() == KeyEvent.VK_R) {
			game.translateModel(0, 0, speed);
		} else if (ke.getKeyCode() == KeyEvent.VK_F) {
			game.translateModel(0, 0, -speed);
		} else if (ke.getKeyCode() == KeyEvent.VK_UP) {
			game.rotateX(-rotationSpeed);
		} else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
			game.rotateX(rotationSpeed);
		} else if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
			game.rotateY(rotationSpeed);
		} else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
			game.rotateY(-rotationSpeed);
		} else if (ke.getKeyCode() == KeyEvent.VK_Q) {
			game.rotateZ(-rotationSpeed);
		} else if (ke.getKeyCode() == KeyEvent.VK_E) {
			game.rotateZ(rotationSpeed);
		}

	}

	public void keyReleased(KeyEvent ke) {

	}

	public void keyTyped(KeyEvent ke) {

	}

	public void mouseDragged(MouseEvent m) {

		mouseX = m.getX();
		mouseY = m.getY();

	}

	public void mouseMoved(MouseEvent m) {

		mouseX = m.getX();
		mouseY = m.getY();

	}

}
