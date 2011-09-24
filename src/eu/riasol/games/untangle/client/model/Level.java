package eu.riasol.games.untangle.client.model;

import java.util.ArrayList;

import com.google.gwt.touch.client.Point;
public class Level {
	private ArrayList<Point> points;
	private ArrayList<Line> lines=new ArrayList<Line>();
	 private ArrayList<Connection> connections=new ArrayList<Connection>();
	 public void addLine(Line l){
		 lines.add(l);
	 }
	 public void addNextLine(Line l){
		 addLine(l);
		 addConnection(lines.size()-1, 1, lines.size(), 0);
	 }
	 /**
	  *
	  * @param l
	  * @param idx1 index on l
	  * @param l2
	  * @param idx2
	  */
	 public void addConnection(int l1, int idx1,int l2, int idx2){
connections.add(new Connection(l1, idx1, l2, idx1));
	 }
	 public void build() throws Exception{

	 }
}
