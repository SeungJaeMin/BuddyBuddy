package transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

import shape.GShape;
import utility.BoundCalculator;
import utility.ScaleCalculator;

public class Resizer extends GTransformer {

	private Point previousPoint;

	public Resizer(List<GShape> canvasData) {
		super(canvasData);
		
	}

	@Override
	public void setPoint(Point point) {
		this.previousPoint = point;
	}

	@Override
	public void transform(Graphics2D graphics2d, Point currentPoint) {
		canvasData.stream()
        .filter(shape -> shape.getCurrentAnchor(currentPoint) != null)
        .findFirst().ifPresent(resizeShape -> {
          Rectangle resizeShapeBound = resizeShape.getBounds();
          double resizeShapeWidth = resizeShapeBound.getWidth();
          double resizeShapeHeight = resizeShapeBound.getHeight();

          if (resizeShapeWidth * resizeShapeHeight > 0) {
        	  canvasData.forEach(shape -> {
              Rectangle bound = shape.getBounds();
              ScaleCalculator calculator = resizeShape.getCurrentAnchor().computeScale(
            		  BoundCalculator.builder()
                      .boundX(bound.getMinX())
                      .boundY(bound.getMinY())
                      .boundWidth(bound.getWidth())
                      .boundHeight(bound.getHeight())
                      .xFactor((currentPoint.x - previousPoint.x) / resizeShapeWidth)
                      .yFactor((currentPoint.y - previousPoint.y) / resizeShapeHeight)
                      .build());

              shape.draw(graphics2d);
              shape.resize(calculator);
              shape.draw(graphics2d);
            });
            previousPoint = currentPoint;
          }
        });		
	}

	@Override
	public void finalizeTransform(List<GShape> canvasData) {
				
	}

}
