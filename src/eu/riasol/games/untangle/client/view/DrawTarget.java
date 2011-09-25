package eu.riasol.games.untangle.client.view;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.touch.client.Point;
import com.google.gwt.user.client.ui.RootPanel;

import eu.riasol.games.untangle.client.model.Line;

public class DrawTarget {
	private Canvas canvas = Canvas.createIfSupported();
	private Context2d ctx = canvas.getContext2d();

	public DrawTarget(String container, Point size) {
		RootPanel.get(container).add(canvas);
	}

	public void drawLine(Line l) {
		ctx.beginPath();
		ctx.moveTo(l.getPoints()[0].getX(), l.getPoints()[0].getY());
		ctx.lineTo(l.getPoints()[1].getX(), l.getPoints()[1].getY());
		ctx.setLineWidth(l.getLineWidth());
		ctx.setStrokeStyle("#cfc");
		ctx.fill();
	}

	public void drawCircle(Point p) {
		ctx.beginPath();
		ctx.arc(p.getX(), p.getY(), 5, 0, Math.PI * 2);
		ctx.setStrokeStyle("#dd");
		ctx.closePath();
		ctx.fill();
	}

	public void clear() {
		ctx.clearRect(0, 0, canvas.getOffsetWidth(), canvas.getOffsetHeight());
	}

}
