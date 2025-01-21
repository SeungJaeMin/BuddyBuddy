package shape;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import global.Constants.EShape;

public class GOval extends GShape {
	private final Ellipse2D ellipse2D;

	public GOval() {
		super(new Ellipse2D.Double(), EShape.eOval);
		ellipse2D = (Ellipse2D) shape;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setStartPoint(Point startPoint) {
	    this.startPoint = startPoint;
	  }

	  @Override
	  public void setCurrentPoint(Point currentPoint) {
	    ellipse2D.setFrameFromDiagonal(startPoint.x, startPoint.y, currentPoint.x, currentPoint.y);
	  }

}
