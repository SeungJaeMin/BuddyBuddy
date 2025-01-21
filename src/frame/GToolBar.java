package frame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import global.Constants;
import global.Constants.EShape;
import shape.GShape;

public class GToolBar extends JToolBar {	
	private static final long serialVersionUID = 1L;
	private GDrawingPanel DrawingPanel;

	
	public GToolBar() {
		 ShapeButtonActionHandler shapeActionHandler = new ShapeButtonActionHandler();

		// set Toolbar Buttons
		
		ButtonGroup buttonGroup = new ButtonGroup();
		for(Constants.EShape eShapeButtons : Constants.EShape.values()) {
			JRadioButton button =  new JRadioButton();
			button.setIcon(createIcon("./uiDesign/btn_Shape_off_" + eShapeButtons.getName() + ".png"));
			button.setSelectedIcon(createIcon("./uiDesign/btn_Shape_Selected_" + eShapeButtons.getName() + ".png"));			
		
			button.setBorderPainted(false);
			button.setContentAreaFilled(false);
			button.setFocusPainted(false);
			button.setActionCommand(eShapeButtons.toString());
			button.addActionListener(shapeActionHandler);
			
			this.add(button);
			buttonGroup.add(button);
		}
	}
	
	public void initialize() {
		JRadioButton defaultButton = (JRadioButton) (this.getComponent(global.Constants.EShape.eSelect.ordinal()));
		defaultButton.doClick();
	}

	private Icon createIcon(String path) {
		 ImageIcon icon = new ImageIcon(path);
	     Image img = icon.getImage();
	     BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
	     Graphics g = bufferedImage.createGraphics();
	     g.drawImage(img, 0, 0, null);
	     g.dispose();
	     return new ImageIcon(bufferedImage);
	}
	


	

	public void Associate(GDrawingPanel DrawingPanel) {
	    this.DrawingPanel = DrawingPanel;
	}
	 
	

	private void setShapeTool(GShape shapeTool) {
		DrawingPanel.setShapeTool(shapeTool);
    	System.out.println(" [GToolBar] : Called setShapeTool ");		
	}
	
	public void setShapeState(EShape eShapeButton) {
		DrawingPanel.setShapeEnumclass(eShapeButton);
		
	}
	
	public class ShapeButtonActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			global.Constants.EShape eShapeButton = global.Constants.EShape.valueOf(e.getActionCommand());
			setShapeState(eShapeButton);
			setShapeTool(eShapeButton.getShapeTool());		
			System.out.println(e.getActionCommand());
		}
	}

	

}
