package eu.riasol.games.untangle.client.model;

public class ConnectionPoint {
	public ConnectionPoint(double x, double y) {
		this.setX(x);
		this.setY(y);
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	private double x;
	private double y;
	public void scale(double factor) {
		x*=factor;
		y*=factor;
	}
	@Override
	public String toString() {
		return "ConnectionPoint, "+x+" "+y;
	}
}
