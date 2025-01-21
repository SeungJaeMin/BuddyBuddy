package frame;

import java.awt.BorderLayout;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import global.Constants;

public class GMainFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private static class InstanceHolder { private static final GMainFrame INSTANCE = new GMainFrame(); }
	public static GMainFrame getInstance() {  return InstanceHolder.INSTANCE; }

	
	private GMenubar menuBar;
	private GToolBar shapeToolBar;
	private GDrawingPanel drawingPanel;
	
	

	public GMainFrame() {
		this.setLocation(200,400);     
		this.setSize(Constants.GMainframe.WIDTH,Constants.GMainframe.HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		// Set Layout Manager
		LayoutManager layoutmanager = new BorderLayout();
		this.setLayout(layoutmanager);
		
		// Set Menubar
		this.drawingPanel = new GDrawingPanel();
	    this.menuBar = new GMenubar();		
		this.shapeToolBar = new GToolBar();		
		
		this.setJMenuBar(this.menuBar);
		this.add(shapeToolBar,BorderLayout.NORTH);
		this.add(drawingPanel,BorderLayout.CENTER);		
		this.setVisible(true);
		initialize();
	}
	

	public void initialize() {
		this.shapeToolBar.Associate(drawingPanel);
		this.menuBar.intialize();
		this.shapeToolBar.initialize();
		this.drawingPanel.initialize();		
	}
}
