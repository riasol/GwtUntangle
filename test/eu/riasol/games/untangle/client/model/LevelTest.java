package eu.riasol.games.untangle.client.model;

import com.google.gwt.touch.client.Point;

import junit.framework.TestCase;

public class LevelTest extends TestCase {

	public void testSimpleRectangle() {
		Level l=new Level();
		l.addLine(new Line(new Point(0, 0), new Point(0, 0)));
		l.addNextLine(new Line(new Point(0, 1), new Point(1, 1)));
		l.addNextLine(new Line(new Point(1, 1), new Point(0, 1)));
		l.addLine(new Line(new Point(0, 1), new Point(0, 0)));
		l.addConnection(3, 0, 2, 1);
		l.addConnection(3, 1, 0, 0);
	}

	public void testAddConnection() {
		fail("Not yet implemented");
	}

}
