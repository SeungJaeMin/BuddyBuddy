package shape;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D.Double;
import java.io.Serializable;
import global.Constants.EAnchors;
import global.Constants.EShape;
import utility.AnchorFactory;
import utility.ScaleCalculator;

abstract public class GShape implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;
	protected Shape shape;
	protected GShape currentShape;
	protected Point startPoint;
	protected boolean bselected;
	private AffineTransform affineTransform;
	
	// attributes_Color
	private Color outlineColor;
	private Color fillColor;
	
	// attributes_Anchors	

	private AnchorFactory anchorFactory;
	private EAnchors currentAnchor;
	private EShape eDrawingStyle;
	
	
	
	public GShape(Shape shape,EShape eDrawingstyle) {
		this.shape = shape;
		this.eDrawingStyle = eDrawingstyle;
		this.anchorFactory = new AnchorFactory();
		this.affineTransform = new AffineTransform();
		currentAnchor = null;
		
	}
	// Getter & Setter
	public EShape getEDrawingStyle(){
		return this.eDrawingStyle;
	}
	
	public abstract void setStartPoint(Point startPoint);
	public abstract void setCurrentPoint(Point currentPoint);
	
	public void setOutlineColor(Color outlineColor2) {
		this.outlineColor = outlineColor2;		
	}
	public void setFillColor(Color fillColor2) {
		this.fillColor = fillColor2;	
	}
	
	public EAnchors getCurrentAnchor() {
	    return this.currentAnchor;
	  }
	
	public EAnchors getCurrentAnchor(Point currentPoint) {
		this.currentAnchor = anchorFactory.getCurrentAnchor(currentPoint);
	    return anchorFactory.getCurrentAnchor(currentPoint);
	}
	
	
	
	public void drawShape(Graphics2D graphics2D, Point currentPoint) {
		 graphics2D.setXORMode(graphics2D.getBackground());
		 draw(graphics2D);
		 setCurrentPoint(currentPoint);
		 draw(graphics2D);
	}	


	public void draw(Graphics2D graphics2D) {
	    if (!bDefaultFillColor() && !bUnfilledShape()) {
	        graphics2D.setColor(fillColor);
	        graphics2D.fill(shape);
	      }
	      graphics2D.setColor(outlineColor);
	      graphics2D.draw(shape);
	      anchorFactory.createAnchors(shape.getBounds());

	      if (bselected) {
	    	anchorFactory.drawAnchors(graphics2D);
	      }
	}
	private Shape createTransformedShape(Shape TargetShape, AffineTransform affineTransform) {
		return TargetShape instanceof Path2D.Float ? new Path2D.Float(TargetShape, affineTransform)
		: new Path2D.Double(TargetShape, affineTransform);
	}
	
	public void move(double translateX, double translateY) {
	    affineTransform.setToTranslation(translateX, translateY);
	    shape = createTransformedShape(shape, affineTransform);
	}
	
	public void resize(ScaleCalculator calculator) {
	    affineTransform.setToTranslation(calculator.getTranslateX(), calculator.getTranslateY());
	    affineTransform.scale(calculator.getScaleX(), calculator.getScaleY());
	    affineTransform.translate(-calculator.getTranslateX(), -calculator.getTranslateY());
	    shape = createTransformedShape(shape, affineTransform);
	}

	public void rotate(double rotateAngle, Point rotatePoint) {
	    affineTransform.setToRotation(rotateAngle, rotatePoint.getX(), rotatePoint.getY());
	    shape = createTransformedShape(shape, affineTransform);
	}

	
	private boolean bUnfilledShape() {
		return false;
	}

	private boolean bDefaultFillColor() {
		if(this.fillColor!=null) {
			return true;
		}
		return false;
	}
	
	public GShape clone() {
	    try {
	      return (GShape) super.clone();
	    } catch (CloneNotSupportedException e) {
	      throw new AssertionError();
	    }
	  }
	public boolean contains(Point currentPoint) {
    if (currentAnchor != null) {
      return true;
    }
    return shape.intersects(new Double(currentPoint.x, currentPoint.y, 2, 2));
    }
	
	public boolean setSelected(boolean b) {
		this.bselected = b;
		return this.bselected;
	}
	
	public boolean bSelected() {
		return this.bselected;
	}
	
	public void clearSelected() {
		this.bselected = false;
	}
	
	public Rectangle getBounds() {
	    return shape.getBounds();
	}
	public Cursor getCursor() {		
		return this.currentAnchor.getCursorStyle();
	}
	public Point getCenterPoint() {
		return new Point((int) shape.getBounds().getCenterX(),
		(int) shape.getBounds().getCenterY());
	}
}
