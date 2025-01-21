package transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;

import shape.GShape;

public abstract class GTransformer {
	protected List<GShape> canvasData;

	  public GTransformer(List<GShape> canvasData) {
	    this.canvasData = canvasData;
	  }

	  public abstract void setPoint(Point point);
	  public abstract void transform(Graphics2D graphics2D, Point currentPoint);
	  public abstract void finalizeTransform(List<GShape> canvasData);

}
