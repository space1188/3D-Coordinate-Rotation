package Main;

import java.util.ArrayList;

public class Particle {

	public double gravity;

	public double x = 0, y = 0, vx = 0, vy = 0;

	public double mass = 1;
	public double radius = 0;
	public double bounce = -1;
	public double friction = 0.955;
	public ArrayList<Spring> springs;
	public ArrayList<Particle> gravitations;

	double grav;

	public Particle(double x, double y, double speed, double direction, double grav) {

		this.x = x;
		this.y = y;
		gravity = grav;
		springs = new ArrayList<Spring>();
		gravitations = new ArrayList<Particle>();

		vx = Math.cos(direction) * speed;
		vy = Math.sin(direction) * speed;

	}

	public void update() {

		this.handleSprings();
		this.handleGravitations();

		vx *= friction;
		vy *= friction;
		vy += gravity;

		x += vx;
		y += vy;

		if (x <= 0)
			x = 0;
		if (x + radius >= 1074)
			x = 1074 - radius;
		if (y <= 0)
			y = 0;
		if (y + radius >= 775)
			y = 775 - radius;

	}

	//////////////// SPRINGS DOWN

	public void handleSprings() {
		for (int i = 0; i < springs.size(); i++) {
			springTo(springs.get(i));
		}
	}

	public void addSpring(Spring spring) {
		this.removeSpring(spring);
		springs.add(spring);
	}

	public void removeSpring(Spring spring) {
		for (int i = 0; i < springs.size(); i++) {
			springs.remove(i);
		}
	}

	public void springTo(Particle point, double k, double length) {

		double dx = point.x - x;
		double dy = point.y - y;
		double distance = Math.sqrt(dx * dx + dy * dy);
		double springForce = (distance - length) * k;

		this.vx += dx / distance * springForce;
		this.vy += dy / distance * springForce;

	}

	//////////////// SPRINGS UP

	//////////////// GRAVIATIONS DOWN

	public void gravitateTo(Particle p2) {

		double dx = p2.x - x, dy = p2.y - y;

		double distSQ = ((dx * dx) + (dy * dy));

		double dist = Math.sqrt(distSQ);

		double force = p2.mass / distSQ;
		double angle = this.angleTo(p2);

		double ax = Math.cos(angle) * force;
		double ay = Math.sin(angle) * force;

		vx += ax;
		vy += ay;

	}

	public void addGravitation(Particle p) {
		removeGravitation(p);
		gravitations.add(p);
	}

	public void removeGravitation(Particle p) {
		for (int i = 0; i < gravitations.size(); i++) {
			if (gravitations.get(i) == null)
				return;
			gravitations.remove(i);

		}
		gravitations.remove(p);
	}

	public void handleGravitations() {
		for (int i = 0; i < gravitations.size(); i++) {
			this.gravitateTo(gravitations.get(i));
		}
	}

	//////////////// GRAVITATIONS UP

	public void accelerate(double ax, double ay) {
		this.vx += ax;
		this.vy += ay;
	}

	public double angleTo(Particle p2) {
		return Math.atan2(p2.y - y, p2.x - x);
	}

	public double distanceTo(Particle p2) {
		double dx = p2.x - x, dy = p2.y - y;
		return Math.sqrt(dx * dx + dy * dy);
	}

	public double getSpeed() {
		return Math.sqrt(vx * vx + vy * vy);
	}

	public void stSpeed(double speed) {

		double heading = this.getHeading();
		vx = Math.cos(heading) * speed;
		vy = Math.sin(heading) * speed;

	}

	public double getHeading() {
		return Math.atan2(vy, vx);
	}

	public void setHeading(double heading) {
		double speed = this.getSpeed();
		vx = Math.cos(heading) * speed;
		vy = Math.sin(heading) * speed;
	}

	public void springTo(Spring spring) {

		double dx = spring.point.x - x;
		double dy = spring.point.y - y;
		double distance = Math.sqrt(dx * dx + dy * dy);
		double springForce = (distance - spring.length) * spring.k;

		this.vx += dx / distance * springForce;
		this.vy += dy / distance * springForce;

	}

}
