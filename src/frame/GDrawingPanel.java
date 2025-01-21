package frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.swing.JPanel;

import global.Constants;
import global.Constants.EAnchors;
import global.Constants.EShape;
import shape.GShape;
import transformer.Drawer;
import transformer.GTransformer;
import transformer.Mover;
import transformer.Resizer;
import transformer.Rotator;

public class GDrawingPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static class InstanceHolder { private static final GDrawingPanel INSTANCE = new GDrawingPanel(); }
	public static GDrawingPanel getInstance() {  return InstanceHolder.INSTANCE; }
	
	// Drawing Panel State
	private enum EDrawingPanelState{ 
		eIdle,
		eResizing,
		eMoving,
		eRotating,
		eSelection,
		eDrawing
	} 
	
	private EDrawingPanelState eDrawingPanelState;
	private EShape eShapeClass;
	private List<GShape> CanvasData;
	private GShape currentShape;
	private GShape selectedShape;
	private BufferedImage bufferedImage;
	
	private GTransformer currentTransformer;
	
	private Color fillColor;
	private Color outlineColor;
	private int outlineSize;
	private int strokeSize;
	
	
	// Constructor
	public GDrawingPanel() {
		this.eDrawingPanelState = EDrawingPanelState.eIdle;
		this.eShapeClass = null;
		this.fillColor = Constants.DEFAULT_FILL_COLOR;
		this.outlineSize = Constants.DEFAULT_OUTLINE_SIZE;
		this.outlineColor = Constants.DEFAULT_OUTLINE_COLOR;
				
		MouseEventHandler mouseEventHandler = new MouseEventHandler();
		this.addMouseListener(mouseEventHandler);
		this.addMouseMotionListener(mouseEventHandler);
		
		this.setBackground(Constants.BACKGROUND_COLOR);
		
		// dynamic components
		this.CanvasData = new ArrayList<>();
	}
	
	protected void paintComponent(Graphics graphics) {
	    super.paintComponent(graphics);

	    if (bufferedImage == null) {
	        createBufferedImage();
	    }
	    
	    Graphics2D bufferedImageGraphics2D = createBufferedImageGraphics2D();
	    bufferedImageGraphics2D.clearRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());	    
	    CanvasData.forEach(shape -> shape.draw(bufferedImageGraphics2D));	    
	    graphics.drawImage(bufferedImage, 0, 0, this);	    	    
	}
	
	  public void createBufferedImage() {
		    this.bufferedImage = (BufferedImage) createImage(this.getWidth(), this.getHeight());
	  }

	  public Graphics2D createBufferedImageGraphics2D() {
		 Graphics2D bufferedImageGraphics2D = (Graphics2D) bufferedImage.getGraphics();
		 bufferedImageGraphics2D.setBackground(Constants.BACKGROUND_COLOR);
		 return bufferedImageGraphics2D;
	  }
	  
	  private List<GShape> createShapeNode(GShape shape) {
		    List<GShape> ShapeNode = new ArrayList<>();
		    ShapeNode.add(shape);
		    return ShapeNode;
	  }
	  

	// Initializer
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	// Setter	
	public void setShapeTool(GShape shapeTool) {
		this.currentShape = shapeTool;		
	}
	
	public void setShapeEnumclass(EShape state) {
		this.eShapeClass = state;
	}
	
	public void setTransformer(GTransformer transformer) {
		this.currentTransformer = transformer;
	}
	
	public void setStateToIdle(){
		this.eDrawingPanelState = EDrawingPanelState.eIdle;
		setTransformer(null);
	    repaint();
	}
	
	public void setShapeAttributes(GShape shape) {
		shape.setOutlineColor(this.outlineColor);
	    shape.setFillColor(this.fillColor);
	}
	
	// Getter
	
	
	private GShape getSelectedShape(Point currentPoint) {
	    List<GShape> temp = new ArrayList<>(CanvasData);
	    Collections.reverse(temp);
	    return temp.stream().filter(shape -> shape.contains(currentPoint)).findFirst().orElse(null);
	}
	
	private List<GShape> getSelectedShapes() {
	    return CanvasData.stream().filter(GShape::bSelected).collect(Collectors.toList());
	}
	
	private boolean OnShape(Point currentPoint) {
	    return CanvasData.stream().anyMatch(shape -> shape.contains(currentPoint));
	}
	
	public void clearSelected() {
	    List<GShape> selectedShapes = getSelectedShapes();
	    selectedShapes.forEach(selectedShape -> selectedShape.setSelected(false));
	    repaint();
	}
	
	public void changeCursor(Point point) {
		GShape shape = this.getSelectedShape(point);
		if(shape == null) {
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}else {
			this.setCursor(shape.getCursor());
		}
		
	}
	
	public void startTransform(Point currentpoint) {
		System.out.println("startTransform");
		if(this.eShapeClass==EShape.eSelect) {
		    // State.Select_Shape
			List<GShape> selectedShapes = new ArrayList<>();
			GShape selectedShape = getSelectedShape(currentpoint);
			if(OnShape(currentpoint)) {
				selectedShape.setSelected(true);
				repaint();
				selectedShapes.add(selectedShape);
				this.selectedShape = selectedShape;
				this.eDrawingPanelState = EDrawingPanelState.eSelection;				
			}else {
				clearSelected();
				this.eDrawingPanelState = EDrawingPanelState.eIdle;
			}
			
			if( this.eDrawingPanelState == EDrawingPanelState.eSelection && selectedShapes!=null && selectedShape.bSelected()) {
				
				if(selectedShape.getCurrentAnchor(currentpoint)==null || selectedShape.getCurrentAnchor(currentpoint)==EAnchors.eMM) {
					// Move
					System.out.println("Move");					
					setTransformer(new Mover(selectedShapes));
					((Mover)currentTransformer).setPoint(currentpoint);
					this.eDrawingPanelState = EDrawingPanelState.eMoving;
				}
				else if(selectedShape.getCurrentAnchor(currentpoint)==EAnchors.eRR) {
					// Rotate
					System.out.println("Rotate");
					setTransformer(new Rotator(selectedShapes));
					((Rotator)currentTransformer).setPoint(currentpoint);
					this.eDrawingPanelState = EDrawingPanelState.eRotating;
				}
				else {
					// Resize
					System.out.println("Resize");
					setTransformer(new Resizer(selectedShapes));
					((Resizer)currentTransformer).setPoint(currentpoint);
					this.eDrawingPanelState = EDrawingPanelState.eResizing;
				}
			}
		}
		else if(this.eShapeClass==EShape.ePolygon) {
			// State.Drawing_NPStyle
			this.eDrawingPanelState = EDrawingPanelState.eDrawing;
		}else {
			// State.Drawing_2PStyle
			setShapeAttributes(currentShape);
			setTransformer(new Drawer(createShapeNode(currentShape)));
			currentTransformer.setPoint(currentpoint);
			this.eDrawingPanelState = EDrawingPanelState.eDrawing;
		}	
	}
	
	public void keepTransform(Point currentpoint) {
		System.out.println("keepDrawingTransform");			
		if(this.eDrawingPanelState != EDrawingPanelState.eIdle && currentTransformer != null) {
		    currentTransformer.transform(createBufferedImageGraphics2D(), currentpoint);	
		    getGraphics().drawImage(bufferedImage, 0, 0, this);
//		    getGraphics().clearRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
//		    repaint();
		    if(eDrawingPanelState==EDrawingPanelState.eMoving || eDrawingPanelState==EDrawingPanelState.eResizing|| eDrawingPanelState==EDrawingPanelState.eRotating)
		    repaint();		        
		}		
		
	}

	

	public void finalizeTransform(Point currentpoint) {
		System.out.println("finalizeTransform");
		if(eDrawingPanelState == EDrawingPanelState.eDrawing) {
			((Drawer) currentTransformer).finalizeTransform(CanvasData);
			setStateToIdle();
		}else {
			setStateToIdle();
		}		
	}
	
	

	
	private class MouseEventHandler implements MouseListener, MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent e) {
			if(eDrawingPanelState != EDrawingPanelState.eIdle) {
				keepTransform(e.getPoint());				
			}
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			if(eDrawingPanelState == EDrawingPanelState.eSelection) {
				changeCursor(e.getPoint());
			}
		}
		@Override
		public void mouseClicked(MouseEvent e) {}
		@Override
		public void mousePressed(MouseEvent e) {
			if(eDrawingPanelState == EDrawingPanelState.eIdle) {
				startTransform(e.getPoint());				
			}
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			if(eDrawingPanelState != EDrawingPanelState.eIdle) {
				finalizeTransform(e.getPoint());
				setStateToIdle();
			}
		}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
		
	}
	public void setShapedata(Object readObject) {
		this.CanvasData = (List<GShape>) readObject;		
	}

	public Object getShapesData() {
		// TODO Auto-generated method stub
		return this.CanvasData;
	}
	
	
}
