package eu.riasol.games.untangle.client;

import java.util.ArrayList;

import eu.riasol.games.untangle.client.model.Level;
import eu.riasol.games.untangle.client.model.ConnectionPoint;
import eu.riasol.games.untangle.client.model.Relation;

public class LevelCreator {
	public LevelCreator() {
		Level l4 = new Level("simple");
		ConnectionPoint p0 = new ConnectionPoint(0, 0);
		l4.addPoint(p0);
		ConnectionPoint p1 = new ConnectionPoint(1, 0);
		l4.addPoint(p1);
		ConnectionPoint p2 = new ConnectionPoint(1, 1);
		l4.addPoint(p2);
		ConnectionPoint p3 = new ConnectionPoint(0, 1);
		l4.addPoint(p3);
		l4.addRelation(new Relation(p0, new ConnectionPoint[] { p1, p2, p3 }));
		l4.addRelation(new Relation(p1, new ConnectionPoint[] { p2, p3 }));
		l4.addRelation(new Relation(p2, new ConnectionPoint[] { p3 }));
		levels.add(l4);
	}
	private ArrayList<Level> levels=new ArrayList<Level>();
	public ArrayList<Level> getLevels() {
		return levels;
	}
}
