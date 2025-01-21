package transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;

import shape.GPolygon;
import shape.GShape;

public class Drawer extends GTransformer {
	protected final GShape shape;

	public Drawer(List<GShape> canvasData) {		
		super(canvasData);
		this.shape = canvasData.get(0);		
	}

	@Override
	public void setPoint(Point point) {
		shape.setStartPoint(point);
	}

	@Override
	public void transform(Graphics2D graphics2D, Point currentPoint) {
		//graphics2D.setXORMode(graphics2D.getBackground());
	    shape.draw(graphics2D);
	    shape.setCurrentPoint(currentPoint);
	    shape.draw(graphics2D);		
	}
	
	public void keepTransform(Point currentPoint) {
	    ((GPolygon) shape).keepDraw(currentPoint);
	}

	@Override
	public void finalizeTransform(List<GShape> canvasData) {
		canvasData.add(shape);		
	}

}
