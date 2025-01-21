package utility;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import frame.GDrawingPanel;
import global.Constants;

public class FileControl {	
	  private static class InstanceHolder { private static final FileControl INSTANCE = new FileControl(); }
	  public static FileControl getInstance() {  return InstanceHolder.INSTANCE;  }
	  
	  //Attributes
	  private String filePath;
	  private final JFileChooser fileChooser;
	  private final FileFactory fileFactory;
	  
	  // Constructor
	  private FileControl() {
		    filePath = null;
		    fileChooser = new JFileChooser();
		    fileChooser.setFileFilter(new FileNameExtensionFilter(Constants.FILE_EXTENSION_DESCRIPTION,
		        Constants.FILE_EXTENSION));
		    fileChooser.setMultiSelectionEnabled(false);
		    fileFactory = FileFactory.getInstance();
	  }
	  // Getter,Setter	  
	  private boolean isNewFile() {
		    return filePath == null;
	  }	  
	  
	  public void saveFile() {
		    if (isNewFile()) {
		      saveFileAs();
		    } else {
		      writeShapeObject(fileFactory.getFile(filePath));
		    }
		}
	  

	
	  
	  public void openFile() {
		    GDrawingPanel drawingPanel = GDrawingPanel.getInstance();
		      int dialogOption = fileChooser.showOpenDialog(drawingPanel);

		      if (dialogOption == JFileChooser.APPROVE_OPTION) {
		        File currentFile = fileFactory.getFile(
		            fileChooser.getSelectedFile().getAbsolutePath());		      
		          readShapeObject(currentFile);
		          drawingPanel.repaint();		       
		      }
	  }
		  

	  public void saveFileAs() {
		    GDrawingPanel drawingPanel = GDrawingPanel.getInstance();

		    fileChooser.setSelectedFile(fileFactory.getFile(
		        fileChooser.getCurrentDirectory() + Constants.DEFAULT_FILE_NAME));
		    int dialogOption = fileChooser.showSaveDialog(drawingPanel);
		    
		    if (dialogOption == JFileChooser.APPROVE_OPTION) {
		      System.out.println("File Save As Complete.");
		      File currentFile = fileFactory.getFile(fileChooser.getSelectedFile().getAbsolutePath());
		      writeShapeObject(currentFile);
		   }
	   }
	  

	  private void setFilePath(String filePath) {
	    this.filePath = filePath;
	  }
	  
	  private void readShapeObject(File currentFile) {
		    GDrawingPanel drawingPanel = GDrawingPanel.getInstance();

		    try {
		      ObjectInputStream objectInputStream = new ObjectInputStream(
		          new BufferedInputStream(new FileInputStream(currentFile)));
		      drawingPanel.setShapedata(objectInputStream.readObject());
		      setFilePath(currentFile.getAbsolutePath());
		      objectInputStream.close();
		    } catch (IOException | ClassNotFoundException exception) {
		      JOptionPane.showMessageDialog(drawingPanel, Constants.OPEN_DIALOG_ERROR_MESSAGE,
		          Constants.OPEN_DIALOG_TITLE, JOptionPane.ERROR_MESSAGE);
		    }
		  }
	  
	  private void writeShapeObject(File currentFile) {		   
		    GDrawingPanel drawingPanel = GDrawingPanel.getInstance();

		    try { 
		      ObjectOutputStream objectOutputStream = new ObjectOutputStream(
		          new BufferedOutputStream(new FileOutputStream(currentFile)));		      
		      objectOutputStream.writeObject(drawingPanel.getShapesData());		      
		      setFilePath(currentFile.getAbsolutePath());
		      objectOutputStream.close();
		    } catch (IOException exception) {
		    	System.out.println("cant save img.");
		    }
		  }
	  


}
