package eu.riasol.games.untangle.client.model;

import junit.framework.TestCase;
import pythagoras.d.GeometryUtil;

public class LevelTest extends TestCase {
	public void testIntersectionMathTheory() {
		double p[][] = { { 0, 0 }, { 10, 0 }, { 10, 10 }, { 0, 10 } };
		double[] ip = new double[2];
		int actual = GeometryUtil.intersectLines(p[0][0], p[0][1],
				p[2][0], p[2][1],
				p[1][0], p[1][1],
				p[3][0], p[3][1],
				ip);
		assertEquals(1, actual);
		double delta = 0.01;
		assertEquals(ip[0], 5, delta);
		assertEquals(ip[1], 5, delta);
	}
	public void testCheckIntersections() {
	}
	public void testIsBetween() {
		double p[][] = { { 0, 1, 0.5 }
				, { 0, 1, 1 }
		};
		assertEquals(true, Level.isBetween(p[0][0], p[0][1], p[0][2]));
		assertEquals(false, Level.isBetween(p[1][0], p[1][1], p[1][2]));
	}
}
