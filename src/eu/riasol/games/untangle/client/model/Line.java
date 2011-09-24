package eu.riasol.games.untangle.client.model;

import com.google.gwt.touch.client.Point;

public class Line {
private Point[] points;
	public Line(Point p1, Point p2) {
		super();
		points=new Point[]{p1,p2};
	}
}
