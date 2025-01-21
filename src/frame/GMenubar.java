package frame;

import javax.swing.JMenuBar;

import global.Constants;
import menus.GEditMenu;
import menus.GFileMenu;
import menus.GScreenMenu;

public class GMenubar extends JMenuBar {
	private static final long serialVersionUID = 1L;
	 //Components
	 private GFileMenu FileMenu;
	 private GEditMenu EditMenu;
	 private GScreenMenu ScreenMenu;
	 private GDrawingPanel DrawingPanel;
	
	
	
	
	 // Instance Holder
	 private static class InstanceHolder { private static final GMenubar INSTANCE = new GMenubar();  }
	 public static GMenubar getInstance() { return InstanceHolder.INSTANCE;} 
	 
	 // Constructor
	 GMenubar() {
		 this.FileMenu = new GFileMenu(Constants.GMenubar.EMenu.eFile.getText());
		 this.add(FileMenu);
		 
		 this.EditMenu = new GEditMenu(Constants.GMenubar.EMenu.eEdit.getText());
		 this.add(EditMenu);
		 
		 this.ScreenMenu = new GScreenMenu(Constants.GMenubar.EMenu.eScreen.getText());
		 this.add(ScreenMenu);		 
	 }	  

	public void intialize() {
		// TODO Auto-generated method stub
		this.DrawingPanel = GDrawingPanel.getInstance();
		
	}

}
