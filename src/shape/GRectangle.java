package shape;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;

import global.Constants.EShape;

public class GRectangle extends GShape {
	  private final Rectangle rectangle;

	
	public GRectangle() {
		super(new Rectangle(), EShape.eRectangle);
		rectangle = (Rectangle) shape;
	}

	@Override
	public void setStartPoint(Point startPoint) {
	  this.startPoint = startPoint;
	}

	@Override
	public void setCurrentPoint(Point currentPoint) {
	  rectangle.setFrameFromDiagonal(startPoint.x, startPoint.y, currentPoint.x, currentPoint.y);
	}

}
