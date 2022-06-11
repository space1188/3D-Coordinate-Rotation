package Main;

public class Utils {

	public Utils() {

	}

	public static double norm(double value, double min, double max) {
		return (value - min) / (max - min);
	}

	public static double lerp(double norm, double min, double max) {
		return (max - min) * (norm + min);
	}

	public static double map(double value, double sourceMin, double sourceMax, double destMin, double destMax) {
		return (lerp(norm(value, sourceMin, sourceMax), destMin, destMax));
	}

	public static double clamp(double value, double min, double max) {
		return Math.min(Math.max(value, min), max);
	}

	public static double distance(Vector p0, Vector p1) {
		double dx = p1.x - p0.x;
		double dy = p1.y - p0.y;
		return Math.sqrt(dx * dx + dy * dy);
	}

	public static double distanceXY(double x0, double y0, double x1, double y1) {
		double dx = x1 - x0;
		double dy = y1 - y0;
		return Math.sqrt(dx * dx + dy * dy);
	}

	public static boolean isWithin(double x, double y, double w, double h, double x2, double y2) {

		if (x2 >= x && x2 <= (x + w) && y2 > y && y2 < (y + h))
			return true;
		else
			return false;

	}

	public static boolean circleCollision(Particle c0, Particle c1) {
		return distance(new Vector(c0.x + c0.radius, c0.y + c0.radius),
				new Vector(c1.x + c1.radius, c1.y + c1.radius)) <= c0.radius + c1.radius;
	}

	public static boolean circlePointCollision(double x, double y, Particle circle) {
		return distanceXY(x, y, circle.y + circle.radius, circle.y + circle.radius) < circle.radius;
	}

	public static boolean inRange(double value, double min, double max) {

		return value >= min && value <= max;

	}

}
