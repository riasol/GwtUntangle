package eu.riasol.games.untangle.client.model;

import java.util.ArrayList;

import pythagoras.d.GeometryUtil;

public class Level {
	public Level(String label) {
		this.label = label;
	}
	private final String label;
	private ArrayList<ConnectionPoint> points = new ArrayList<ConnectionPoint>();
	private ArrayList<Relation> relations = new ArrayList<Relation>();
	public void addPoint(ConnectionPoint point) {
		points.add(point);
	}
	public void addRelation(Relation relation) {
		getRelations().add(relation);
	}
	public ArrayList<Relation> getRelations() {
		return relations;
	}
	public ArrayList<ConnectionPoint> getPoints() {
		return points;
	}
	public void scale(double scaling) {
		for (ConnectionPoint point : points) {
			point.scale(scaling);
		}
	}
	public void checkIntersections() {
		for(Relation rel0:getRelations()) {
			rel0.resetIntersected();
		}
		for (Relation rel1 : getRelations()) {
			for (Relation rel2 : getRelations()) {
				if (!rel1.getFrom().equals(rel2.getFrom())) {
					for (ConnectionPoint to1 : rel1.getTo()) {
						for (ConnectionPoint to2 : rel2.getTo()) {
							double[] intersection = new double[2];
							int intersected = GeometryUtil.intersectLines(
									rel1.getFrom().getX(), rel1.getFrom().getY(),
									to1.getX(), to1.getY(),
									rel2.getFrom().getX(), rel2.getFrom().getY(),
									to2.getX(), to2.getY(),
									intersection);
							boolean inside = intersected == 1;
							if (inside) {
								inside = isBetween(rel1.getFrom().getX(), to1.getX(), intersection[0]);
								inside = inside && isBetween(rel2.getFrom().getX(), to2.getX(), intersection[0]);
							}
							rel1.setIntersected(to1, rel1.getIntersected(to1) || inside);
							rel2.setIntersected(to2, rel2.getIntersected(to2) || inside);
							//GWT.log(intersected+" "+rel1.getFrom().toString()+" "+to1.toString()+" - " + rel2.getFrom().toString()+" "+to2.toString());
						}
					}
				}
			}
		}
	}
	public static boolean isBetween(double p1, double p2, double point) {
		double reso = 0.00001;
		if (Math.abs(p1 - point) < reso || Math.abs(p2 - point) < reso) {
			return false;
		}
		return (p1 < point && point < p2) || (p1 > point && point > p2);
	}
}
