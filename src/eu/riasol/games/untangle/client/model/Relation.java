package eu.riasol.games.untangle.client.model;

public class Relation {
	public Relation(ConnectionPoint from, ConnectionPoint[] to) {
		this.from = from;
		this.to = to;
		intersections = new boolean[to.length];
	}
	public ConnectionPoint getFrom() {
		return from;
	}
	public ConnectionPoint[] getTo() {
		return to;
	}
	private final ConnectionPoint from;
	private final ConnectionPoint[] to;
	private final boolean[] intersections;
	public boolean getIntersected(ConnectionPoint point) {
		for (int i = 0; i < to.length; i++) {
			if (to[i].equals(point)) {
				return intersections[i];
			}
		}
		return false;
	}
	public void setIntersected(ConnectionPoint point, boolean intersected) {
		for (int i = 0; i < to.length; i++) {
			if (to[i].equals(point)) {
				intersections[i]=intersected;
				break;
			}
		}
	}
	public void resetIntersected() {
		for (int i = 0; i < to.length; i++) {
			intersections[i]=false;
		}
	}
	@Override
	public String toString() {
		return getClass().getName()+", from: "+from.getX()+" "+from.getY();
	}
}
