package utility;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

import global.Constants;
import global.Constants.EAnchors;

public class AnchorFactory implements Serializable{
	  private static final long serialVersionUID = 1L;
	  
	  private Rectangle anchorRect;
	  private Ellipse2D[] anchorList;

	  public AnchorFactory() {
	    this.anchorList = new Ellipse2D[EAnchors.values().length];
	  }

	  public Ellipse2D[] getAnchorList() {
	    return anchorList;
	  }

	  public void createAnchors(Rectangle bound) {
	    for (int i = 0; i < EAnchors.values().length; i++) {
	      anchorList[i] = getAnchor(bound, EAnchors.values()[i]);
	    }
	  }

	  public void drawAnchors(Graphics2D graphics2D) {
		  graphics2D.draw(anchorRect);
	   for (Ellipse2D anchor : anchorList) {	      
	      graphics2D.setColor(Constants.DEFAULT_FILL_COLOR);
	      graphics2D.fill(anchor);
	      graphics2D.setColor(Constants.DEFAULT_OUTLINE_COLOR);	      
	      graphics2D.draw(anchor);
	      
	      
	    }
	  }

	  private Ellipse2D getAnchor(Rectangle bound, EAnchors anchorType) {
	    Ellipse2D anchor = new Ellipse2D.Double();
	    Point anchorPoint = newAnchorPoint(bound, anchorType);
	    int originX = anchorPoint.x - Constants.ANCHOR_WIDTH / 2;
	    int originY = anchorPoint.y - Constants.ANCHOR_HEIGHT / 2;
	    anchor.setFrame(originX, originY, Constants.ANCHOR_WIDTH, Constants.ANCHOR_HEIGHT);
	    return anchor;
	  }
	  
	  public EAnchors getCurrentAnchor(Point currentPoint) {
		    for (int i = 0; i < anchorList.length; i++) {
		      if (anchorList[i].contains(currentPoint)) {
		        return EAnchors.values()[i];
		      }
		    }
		    return EAnchors.eMM;
	  }
	  
	  private Point newAnchorPoint(Rectangle bound, EAnchors anchorType) {
		  this.anchorRect = bound;
		    switch (anchorType) {
		      case eNW:
		        return new Point(bound.x, bound.y);
		      case eWW:
		        return new Point(bound.x, bound.y + bound.height / 2);
		      case eSW:
		        return new Point(bound.x, bound.y + bound.height);
		      case eSS:
		        return new Point(bound.x + bound.width / 2, bound.y + bound.height);
		      case eSE:
		        return new Point(bound.x + bound.width, bound.y + bound.height);
		      case eEE:
		        return new Point(bound.x + bound.width, bound.y + bound.height / 2);
		      case eNE:
		        return new Point(bound.x + bound.width, bound.y);
		      case eNN:
		        return new Point(bound.x + bound.width / 2, bound.y);
		      case eRR:
		        return new Point(bound.x + bound.width / 2, bound.y - Constants.ROTATE_HANDLE_HEIGHT);
		      case eMM:
		    	return new Point(bound.x + bound.width / 2, bound.y + bound.height /2 );
		      default:
		        throw new IllegalArgumentException("Unknown anchor type: " + anchorType);
		    }		    
		    
		  }
	  
	  

}
