package transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;

import shape.GShape;

public class Mover extends GTransformer {
	
	private Point previousPoint;

	public Mover(List<GShape> canvasData) {
		super(canvasData);		
		this.previousPoint = new Point(canvasData.get(0).getBounds().x,canvasData.get(0).getBounds().y);
	}

	@Override
	public void setPoint(Point point) {
		previousPoint = point;
		
	}

	@Override
	public void transform(Graphics2D graphics2d, Point currentPoint) {
		canvasData.forEach(shape -> {		  
		  shape.draw(graphics2d);
		  shape.move((double) currentPoint.x - previousPoint.x, (double) currentPoint.y - previousPoint.y);
		  shape.draw(graphics2d);
		});
		previousPoint = currentPoint;
	}

	@Override
	public void finalizeTransform(List<GShape> canvasData) {
		// TODO Auto-generated method stub
		
	}

}
