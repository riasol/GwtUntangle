package eu.riasol.games.untangle.client.model;

import junit.framework.TestCase;

import com.google.gwt.touch.client.Point;

public class LevelTest extends TestCase {

	public void testSimpleRectangle() {
		Level l = new Level();
		l.addLine(new Line(new Point(0, 0), new Point(0, 0)));
		l.addNextLine(new Line(new Point(0, 1), new Point(1, 1)));
		l.addNextLine(new Line(new Point(1, 1), new Point(0, 1)));
		l.addLine(new Line(new Point(0, 1), new Point(0, 0)));
		l.addConnection(3, 0, 2, 1);
		try {
			l.build();
			fail("Expected exception");
		} catch (Exception e) {

		}
		l.addConnection(3, 1, 0, 0);
		try {
			l.build();
		} catch (Exception e) {
			fail("Exception");
		}

	}

}
