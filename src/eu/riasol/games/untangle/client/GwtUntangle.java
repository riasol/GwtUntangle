package eu.riasol.games.untangle.client;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.ui.RootPanel;

import eu.riasol.games.untangle.client.model.ConnectionPoint;
import eu.riasol.games.untangle.client.model.Level;
import eu.riasol.games.untangle.client.model.Relation;

public class GwtUntangle {
	private int width;
	private int height;
	public GwtUntangle(String targetId, int width, int height) {
		this.width = width;
		this.height = height;
		cnv = Canvas.createIfSupported();
		cnv.setPixelSize(width, height);
		cnv.setCoordinateSpaceWidth(width);
		cnv.setCoordinateSpaceHeight(height);
		RootPanel.get(targetId).add(cnv);
		ctx = cnv.getContext2d();
		initializeListeners();
	}
	/**
	 * Offset to 0,0 in LT point
	 */
	private int[] offset;
	private double getScaling() {
		double sc = Math.min(width, height);
		double factor = 0.7;
		offset = new int[] { (int) (width - width * 0.7) / 2, (int) (height - height * 0.7) / 2 };
		return sc * factor;
	}
	private void initializeListeners() {
		cnv.addMouseDownHandler(new MouseDownHandler() {
			@Override
			public void onMouseDown(MouseDownEvent event) {
				onPointerDown(event.getX(), event.getY());
			}
		});
		cnv.addMouseUpHandler(new MouseUpHandler() {
			@Override
			public void onMouseUp(MouseUpEvent event) {
				onPointerUp();
			}
		});
		cnv.addMouseMoveHandler(new MouseMoveHandler() {
			@Override
			public void onMouseMove(MouseMoveEvent event) {
				onPointerMove(event.getX(), event.getY());
			}
		});
	}
	private Context2d ctx;
	private Level level;
	private Canvas cnv;
	private ConnectionPoint currentCircle;
	public void setLevel(Level level) {
		this.level = level;
		this.level.scale(getScaling());// TODO Bad practise
		draw();
	}
	private void onPointerDown(int x, int y) {
		currentCircle = null;
		for (ConnectionPoint p : level.getPoints()) {
			double diff=Math.sqrt(Math.pow(p.getX()+offset[0] - (x), 2) + Math.pow(p.getY()+offset[1] - (y), 2));
			if (diff < radius) {
				currentCircle = p;
				break;
			}
		}
	}
	private void onPointerUp() {
		currentCircle = null;
	}
	private void onPointerMove(int x, int y) {
		if (currentCircle != null) {
			currentCircle.setX(x-offset[0]);
			currentCircle.setY(y-offset[1]);
			draw();
		}
	}
	private void draw() {
		level.checkIntersections();
		ctx.clearRect(0, 0, width, height);
		ctx.setFillStyle(CssColor.make("green"));
		ctx.rect(0, 0, width, height);
		ctx.fill();
		for (Relation relation : level.getRelations()) {
			for (ConnectionPoint to : relation.getTo()) {
				ctx.beginPath();
				drawLine(relation.getFrom(), to);
				ctx.setLineWidth(relation.getIntersected(to)?3:1);
				//ctx.setLineWidth(1);
				ctx.setStrokeStyle(CssColor.make("#cccccc"));
				ctx.stroke();
			}
		}
		for (ConnectionPoint point : level.getPoints()) {
			drawCircle(point);
		}
	}
	private void drawLine(ConnectionPoint p1, ConnectionPoint p2) {
		ctx.moveTo(offset[0] + p1.getX(), offset[1] + p1.getY());
		ctx.lineTo(offset[0] + p2.getX(), offset[1] + p2.getY());
	}
	private int radius = 20;
	private void drawCircle(ConnectionPoint p) {
		ctx.setFillStyle(CssColor.make("#ffffff"));
		ctx.beginPath();
		ctx.arc(offset[0] + p.getX(), offset[1] + p.getY(), radius, 0, Math.PI * 2);
		ctx.closePath();
		ctx.fill();
	}
}
