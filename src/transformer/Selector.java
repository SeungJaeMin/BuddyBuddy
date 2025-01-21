package transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

import global.Constants.EShape;
import shape.GShape;

public class Selector extends GTransformer {
	public Selector(List<GShape> canvasData) {
		super(canvasData);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setPoint(Point point) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transform(Graphics2D graphics2d, Point currentPoint) {
		
	}

	@Override
	public void finalizeTransform(List<GShape> canvasData) {		
		for(GShape gshape : canvasData) {
			if(gshape.getBounds().contains(gshape.getBounds())) {
				gshape.setSelected(true);
				System.out.println(gshape.getClass());
				System.out.println("Selected");
			}
		}
	}

	

}
