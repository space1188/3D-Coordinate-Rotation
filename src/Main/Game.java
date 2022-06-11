package Main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 1080, HEIGHT = 810;

	boolean running = false;

	Window window;
	Thread thread;
	Mouse mouse;

	Vector point;
	Color color = Color.GREEN;

	double fl = 300;
	double centerZ = 1500;
	Point[] points = new Point[8];
	boolean needsUpdate = true;
	public static double speed = 50;
	public static double rotationSpeed = 0.1;

	public Game() {

		mouse = new Mouse(this);
		window = new Window(WIDTH, HEIGHT, this, mouse);

		points[0] = new Point(-500, -500, 500);
		points[1] = new Point(500, -500, 500);
		points[2] = new Point(500, -500, -500);
		points[3] = new Point(-500, -500, -500);
		points[4] = new Point(-500, 500, 500);
		points[5] = new Point(500, 500, 500);
		points[6] = new Point(500, 500, -500);
		points[7] = new Point(-500, 500, -500);

		project();

	}

	public void update() {
		
		project();

	}

	public void render() {

		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics2D g2d = (Graphics2D) bs.getDrawGraphics();

		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, WIDTH, HEIGHT);

		////////////////////////////////////////

		g2d.setColor(Color.BLACK);
		g2d.translate(WIDTH / 2, HEIGHT / 2);

		// DIMENSIONS FOR A CUBE

		// 0, 1, 2, 3, 0
		// 4, 5, 6, 7, 4
		// 0, 4
		// 1, 5
		// 2, 6
		// 3, 7

		g2d.setColor(Color.BLACK);

		g2d.drawLine((int) points[0].sx, (int) points[0].sy, (int) points[1].sx, (int) points[1].sy);
		g2d.drawLine((int) points[1].sx, (int) points[1].sy, (int) points[2].sx, (int) points[2].sy);
		g2d.drawLine((int) points[2].sx, (int) points[2].sy, (int) points[3].sx, (int) points[3].sy);
		g2d.drawLine((int) points[3].sx, (int) points[3].sy, (int) points[0].sx, (int) points[0].sy);

		g2d.drawLine((int) points[4].sx, (int) points[4].sy, (int) points[5].sx, (int) points[5].sy);
		g2d.drawLine((int) points[5].sx, (int) points[5].sy, (int) points[6].sx, (int) points[6].sy);
		g2d.drawLine((int) points[6].sx, (int) points[6].sy, (int) points[7].sx, (int) points[7].sy);
		g2d.drawLine((int) points[7].sx, (int) points[7].sy, (int) points[4].sx, (int) points[4].sy);

		g2d.drawLine((int) points[0].sx, (int) points[0].sy, (int) points[4].sx, (int) points[4].sy);
		g2d.drawLine((int) points[1].sx, (int) points[1].sy, (int) points[5].sx, (int) points[5].sy);
		g2d.drawLine((int) points[2].sx, (int) points[2].sy, (int) points[6].sx, (int) points[6].sy);
		g2d.drawLine((int) points[3].sx, (int) points[3].sy, (int) points[7].sx, (int) points[7].sy);

		////////////////////////////////////////

		g2d.dispose();
		bs.show();

	}

	public void project() {
		for (int i = 0; i < points.length; i++) {
			Point p = points[i];
			double scale = fl / (fl + p.z + centerZ);
			p.sx = p.x * scale;
			p.sy = p.y * scale;
		}
	}

	public void translateModel(double x, double y, double z) {

		for (int i = 0; i < points.length; i++) {
			Point p = points[i];
			p.x += x;
			p.y += y;
			p.z += z;
		}

	}

	public void rotateX(double angle) {
		double cos = Math.cos(angle);
		double sin = Math.sin(angle);
		for (int i = 0; i < points.length; i++) {
			Point p = points[i];
			double y = p.y * cos - p.z * sin;
			double z = p.z * cos + p.y * sin;
			p.y = y;
			p.z = z;
		}
	}

	public void rotateY(double angle) {
		double cos = Math.cos(angle);
		double sin = Math.sin(angle);
		for (int i = 0; i < points.length; i++) {
			Point p = points[i];
			double x = p.x * cos - p.z * sin;
			double z = p.z * cos + p.x * sin;
			p.x = x;
			p.z = z;
		}
	}

	public void rotateZ(double angle) {
		double cos = Math.cos(angle);
		double sin = Math.sin(angle);
		for (int i = 0; i < points.length; i++) {
			Point p = points[i];
			double x = p.x * cos - p.y * sin;
			double y = p.y * cos + p.x * sin;
			p.x = x;
			p.y = y;
		}
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {

		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		while (running) {

			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				delta--;
			}
			if (running) {
				render();
			}
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
			}

		}

	}

	public static void main(String[] args) {
		new Game();
	}

}
