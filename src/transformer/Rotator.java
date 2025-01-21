package transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;

import shape.GShape;

public class Rotator extends GTransformer {

	private Point previousPoint;

	public Rotator(List<GShape> canvasData) {
		super(canvasData);		
	}

	@Override
	public void setPoint(Point point) {
		previousPoint = point;
	}

	@Override
	public void transform(Graphics2D graphics2d, Point currentPoint) {
		canvasData.stream()
        .filter(shape -> shape.getCurrentAnchor(currentPoint) != null)
        .findFirst().ifPresent(rotateShape -> {
          double computedAngle =
              computeAngle(rotateShape.getCenterPoint(), previousPoint, currentPoint);

          canvasData.forEach(shape -> {
            shape.draw(graphics2d);
            shape.rotate(computedAngle, shape.getCenterPoint());
            shape.draw(graphics2d);
          });
          previousPoint = currentPoint;
        });
	}

	private double computeAngle(Point rotatePoint, Point previousPoint, Point currentPoint) {
	    double startAngle = Math.toDegrees(
	        Math.atan2(rotatePoint.x - previousPoint.x, rotatePoint.y - previousPoint.y));
	    double endAngle = Math.toDegrees(
	        Math.atan2(rotatePoint.x - currentPoint.x, rotatePoint.y - currentPoint.y));
	    return Math.toRadians(
	        (startAngle - endAngle) < 0 ? startAngle - endAngle + 360 : startAngle - endAngle);
	  }

	@Override
	public void finalizeTransform(List<GShape> canvasData) {
		// TODO Auto-generated method stub
		
	}

}
