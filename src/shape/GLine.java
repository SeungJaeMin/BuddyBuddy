package shape;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Line2D;

import global.Constants.EShape;

public class GLine extends GShape {

	private Line2D line2D;

	public GLine() {
		super(new Line2D.Double(),EShape.eLine);
	    line2D = (Line2D) shape;
	}

	@Override
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;		
	}

	@Override
	public void setCurrentPoint(Point currentPoint) {
	    line2D.setLine(startPoint.x, startPoint.y, currentPoint.x, currentPoint.y);
	}

}
