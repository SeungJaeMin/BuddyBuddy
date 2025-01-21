package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import global.Constants;
import utility.FileControl;

public class GFileMenu extends JMenu {
	private JMenuItem newFile;
	private JMenuItem openFile;
	private JMenuItem saveFile;
	private JMenuItem saveAsFile;
	private JMenuItem Exit;

	public GFileMenu(String string) {
		super(string);
		this.newFile = new JMenuItem(Constants.GFileMenu.EFileMenu.eNew.getText());
		this.openFile = new JMenuItem(Constants.GFileMenu.EFileMenu.eOpen.getText());
		this.saveFile = new JMenuItem(Constants.GFileMenu.EFileMenu.eSave.getText());
		this.saveAsFile = new JMenuItem(Constants.GFileMenu.EFileMenu.eSaveAs.getText());
		this.Exit = new JMenuItem(Constants.GFileMenu.EFileMenu.eExit.getText());
		
		this.add(newFile);
		this.add(openFile);
		this.add(saveFile);
		this.add(saveAsFile);
		this.add(Exit);
		
		//this.newFile.addActionListener(new FileMenuActionHandler());
		this.openFile.addActionListener(new FileMenuActionHandler());
		//this.saveFile.addActionListener(new FileMenuActionHandler());
		this.saveAsFile.addActionListener(new FileMenuActionHandler());
		//this.Exit.addActionListener(new FileMenuActionHandler());		ub
	}
	
	public class FileMenuActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String inputAction = e.getActionCommand();
			if(inputAction == Constants.GFileMenu.EFileMenu.eNew.getText()) {
				
			}
			else if(inputAction == Constants.GFileMenu.EFileMenu.eOpen.getText()) {
				FileControl fileControl = FileControl.getInstance();
				fileControl.openFile();
	
			}
			else if(inputAction == Constants.GFileMenu.EFileMenu.eSave.getText()) {			
				
			}
			else if(inputAction == Constants.GFileMenu.EFileMenu.eSaveAs.getText()) {
				FileControl fileControl = FileControl.getInstance();
				fileControl.saveFileAs();
			}
	        else if(inputAction == Constants.GFileMenu.EFileMenu.eExit.getText()) {
				
			}
			
			

		}
	}
	

}
