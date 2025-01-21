package global;

import java.awt.Color;
import java.awt.Cursor;
import shape.GLine;
import shape.GOval;
import shape.GPolygon;
import shape.GRectangle;
import shape.GShape;
import utility.BoundCalculator;
import utility.ScaleCalculator;

public class Constants {
	
	public final static Color BACKGROUND_COLOR = Color.white;
	public final static Color DEFAULT_FILL_COLOR = Color.white;
	public final static int DEFAULT_OUTLINE_SIZE = 1;
	public final static int DEFAULT_DASH_SIZE = 0;
	public final static Color DEFAULT_OUTLINE_COLOR = Color.blue;
	public final static int ANCHOR_WIDTH = 10;
	public final static int ANCHOR_HEIGHT = 10;
	public final static int ROTATE_HANDLE_HEIGHT = 40;
	
	public static final Cursor DEFAULT_STYLE_CURSOR = new Cursor(Cursor.DEFAULT_CURSOR);
	public static final Cursor HAND_STYLE_CURSOR = new Cursor(Cursor.HAND_CURSOR);
	public static final Cursor CROSSHAIR_STYLE_CURSOR = new Cursor(Cursor.CROSSHAIR_CURSOR);
	public static final String FILE_EXTENSION = null;
	public static final String FILE_EXTENSION_DESCRIPTION = null;
	
	public static final String OPEN_DIALOG_TITLE = "Open";
	public static final String OPEN_DIALOG_ERROR_MESSAGE = "Unable to Open";
	public static final String DEFAULT_FILE_NAME = "untitle";
	

	public static class GMainframe{ // for Mainframe
		public final static int WIDTH = 800;
		public final static int HEIGHT = 800;
	}
	
	public static class GMenubar{ // for Menubar
		public enum EMenu{
			eFile("File"), eEdit("Edit"), eScreen("Window"), eHelp("Help");
			
			private String text;
			EMenu(String string) {
			   this.text = string;
			}
			public String getText() {return this.text;}
		}
	}
	
	public static class GFileMenu{
		public enum EFileMenu{
			eOpen("Open File"), eNew("New File"), eSave("Save File"), eSaveAs("Save As"), eExit("Exit");					
			private String text;
			EFileMenu(String string){
				this.text = string;
			}
			public String getText() {return this.text;}
		}
	}
	
	
	public enum EShape {
		eSelect("Select", null),
		eRectangle("Rectangle", new GRectangle()),
		eOval("Oval", new GOval()),
		eLine("Line", new GLine()),
		ePolygon("Polygon", new GPolygon()); // object

		private String name;
		private GShape gShape;
		

		private EShape(String name, GShape gShape) {
			this.name = name;
			this.gShape = gShape;			
		}

		public String getName() {
			return this.name;
		}

		public GShape getShapeTool() {
			return this.gShape;
		}
	}
	
	public enum DrawingToolItem {
		  delete("delete"),
		  clear("clearPanel"),
		  outline("chooseOutlineColor"),
		  fill("chooseFillColor");

		  private final String methodName;

		  DrawingToolItem(String methodName) {
		    this.methodName = methodName;
		  }

		  public String getMethodName() {
		    return methodName;
		  }
		}
	
	
	public enum EAnchors{
		eRR(new Cursor(Cursor.HAND_CURSOR)),
		eNN(new Cursor(Cursor.N_RESIZE_CURSOR)),
		eSS(new Cursor(Cursor.S_RESIZE_CURSOR)),
		eEE(new Cursor(Cursor.E_RESIZE_CURSOR)),
		eWW(new Cursor(Cursor.W_RESIZE_CURSOR)),
		eNE(new Cursor(Cursor.NE_RESIZE_CURSOR)),
		eNW(new Cursor(Cursor.NW_RESIZE_CURSOR)),
		eSE(new Cursor(Cursor.SE_RESIZE_CURSOR)),
		eSW(new Cursor(Cursor.SW_RESIZE_CURSOR)),
		eMM(new Cursor(Cursor.CROSSHAIR_CURSOR));
		
		private Cursor cursor;
		private EAnchors(Cursor cursor) {
			this.cursor = cursor;
		}

		public Cursor getCursorStyle() {
			// TODO Auto-generated method stub
			return this.cursor;
		}
		
		public ScaleCalculator computeScale(BoundCalculator calculator) {
	        switch (this) {
	            case eNW:
	                return ScaleCalculator.builder()
	                    .translateX(calculator.getBoundX() + calculator.getBoundWidth())
	                    .translateY(calculator.getBoundY() + calculator.getBoundHeight())
	                    .scaleX(1 - calculator.getXFactor())
	                    .scaleY(1 - calculator.getYFactor())
	                    .build();
	            case eWW:
	                return ScaleCalculator.builder()
	                    .translateX(calculator.getBoundX() + calculator.getBoundWidth())
	                    .translateY(0)
	                    .scaleX(1 - calculator.getXFactor())
	                    .scaleY(1)
	                    .build();
	            case eSW:
	                return ScaleCalculator.builder()
	                    .translateX(calculator.getBoundX() + calculator.getBoundWidth())
	                    .translateY(calculator.getBoundY())
	                    .scaleX(1 - calculator.getXFactor())
	                    .scaleY(1 + calculator.getYFactor())
	                    .build();
	            case eSS:
	                return ScaleCalculator.builder()
	                    .translateX(0)
	                    .translateY(calculator.getBoundY())
	                    .scaleX(1)
	                    .scaleY(1 + calculator.getYFactor())
	                    .build();
	            case eSE:
	                return ScaleCalculator.builder()
	                    .translateX(calculator.getBoundX())
	                    .translateY(calculator.getBoundY())
	                    .scaleX(1 + calculator.getXFactor())
	                    .scaleY(1 + calculator.getYFactor())
	                    .build();
	            case eEE:
	                return ScaleCalculator.builder()
	                    .translateX(calculator.getBoundX())
	                    .translateY(0)
	                    .scaleX(1 + calculator.getXFactor())
	                    .scaleY(1)
	                    .build();
	            case eNE:
	                return ScaleCalculator.builder()
	                    .translateX(calculator.getBoundX())
	                    .translateY(calculator.getBoundY() + calculator.getBoundHeight())
	                    .scaleX(1 + calculator.getXFactor())
	                    .scaleY(1 - calculator.getYFactor())
	                    .build();
	            case eNN:
	                return ScaleCalculator.builder()
	                    .translateX(0)
	                    .translateY(calculator.getBoundY() + calculator.getBoundHeight())
	                    .scaleX(1)
	                    .scaleY(1 - calculator.getYFactor())
	                    .build();
	            default:
	                return null; // or throw an exception if preferred
	        }
	    }
	}


	
	
	
}
