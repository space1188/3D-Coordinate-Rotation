package Main;

import Main.Vector;

public class Vector {

	public double x, y;

	public Vector(double x, double y) {

		this.x = x;
		this.y = y;

	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public Vector get() {
		return new Vector(x, y);
	}

	public Vector add(Vector v) {
		return new Vector(this.x += v.x, this.y += v.y);
	}

	public void addTo(Vector v2) {
		x += v2.x;
		y += v2.y;
	}

	public void addTo(double v2) {
		x += v2;
		y += v2;
	}

	public void sub(Vector v) {
		this.x -= v.x;
		this.y -= v.y;
	}
	public Vector subR(Vector v) {
		return new Vector(this.x - v.x, this.y - v.y);
	}
	public void sub(double v) {
		this.x -= v;
		this.y -= v;
	}

	public void subTo(Vector v2) {
		x -= v2.x;
		y -= v2.y;
	}

	public void multBy(Vector v) {
		this.x *= v.x;
		this.y *= v.y;
	}

	public void multBy(double v) {
		this.x *= v;
		this.y *= v;
	}

	public Vector mult(Vector v2) {
		double x = this.x *= v2.x;
		double y = this.y *= v2.y;
		return (new Vector(x, y));
	}

	public void divBy(Vector v) {
		this.x /= v.x;
		this.y /= v.y;
	}

	public Vector div(Vector v2) {
		double x = this.x /= v2.x;
		double y = this.y /= v2.y;
		return (new Vector(x, y));
	}

	public void setLength(double length) {

		double angle = this.getAngle();

		this.x = Math.cos(angle) * length;
		this.y = Math.sin(angle) * length;

	}

	public double getLength() {

		return Math.sqrt(this.x * this.x + this.y * this.y);

	}

	public void setAngle(double d) {

		double length = this.getLength();

		this.x = Math.cos(d) * length;
		this.y = Math.sin(d) * length;

	}

	public double getAngle() {

		return Math.atan2(this.y, this.x);

	}

}
