package shape;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;

import global.Constants.EShape;

public class GPolygon extends GShape {
	private static final long serialVersionUID = 1L;
	private final Polygon polygon;

	public GPolygon() {
		super(new Polygon(),EShape.ePolygon);
		polygon = (Polygon) shape;
	}

	@Override
	public void setStartPoint(Point startPoint) {
		polygon.addPoint(startPoint.x, startPoint.y);
		
	}

	@Override
	public void setCurrentPoint(Point currentPoint) {
		polygon.xpoints[polygon.npoints - 1] = currentPoint.x;
	    polygon.ypoints[polygon.npoints - 1] = currentPoint.y;
		
	}

	public void keepDraw(Point currentPoint) {
		polygon.addPoint(currentPoint.x, currentPoint.y);
		
	}

}
