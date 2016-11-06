import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.*;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.awt.*;


public class FileChooser extends JPanel implements ActionListener {
	private JButton go;
	private ArrayList<File> files = new ArrayList<>();
	private JFileChooser chooser;
	private String choosertitle;
	private static DataObject d;
	public FileChooser() {
		go = new JButton("Browse");
		go.addActionListener(this);
		
		add(go);
	}

	public void actionPerformed(ActionEvent e) {            
		chooser = new JFileChooser(); 
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle(choosertitle);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);  
		FileNameExtensionFilter ff = new FileNameExtensionFilter("text files", "txt");
		chooser.setFileFilter(ff);
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
			File f = chooser.getSelectedFile();
			for(File file:f.listFiles()){
				files.add(file);
			}
			try {
				d = new DataObject(files);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			repaint();
		}
		else {
			System.out.println("No Selection ");
		}
		
		
	}
	public Dimension getPreferredSize(){
		return new Dimension(200, 200);
	}
	public static DataObject getDataObject(){
		return d;
	}
}