import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.*;
import java.awt.peer.ScrollPanePeer;
import java.io.File;
import java.io.FileFilter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.awt.*;


public class FileChooser extends JPanel implements ActionListener {
	private JButton go;
	private ArrayList<File> files = new ArrayList<>();
	private JFileChooser chooser;
	private String choosertitle;
	private JTextField adr = new JTextField(60);
	private JTextArea errors = new JTextArea(20,50);
	private JScrollPane err = new JScrollPane(errors);
	private static DataObject d;
	private StringBuilder error = new StringBuilder();
	public FileChooser() {
		errors.setEditable(false);
		errors.setEnabled(false);
		errors.setDisabledTextColor(Color.RED);
		JPanel browsingFile = new JPanel();
		JPanel erro = new JPanel();
		adr.setEditable(false);
		go = new JButton("Browse");
		go.addActionListener(this);
		this.setLayout(new BorderLayout());
		browsingFile.add(adr);
		browsingFile.add(go);
		erro.add(err);
		add(browsingFile, BorderLayout.NORTH);
		add(erro, BorderLayout.CENTER);
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
			adr.setText(f.getAbsolutePath());
			for(File file:f.listFiles()){
				files.add(file);
			}
			try {
				d = new DataObject(files);
				HashMap<Integer, ArrayList<String>> er = d.getErrors();
				for(int a: er.keySet()){
					for(String s:er.get(a)){
						error.append(s+"\n");
					}
				}
				errors.setText(error.toString());
				Date date = new Date() ;
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;
				File file = new File("Log File " + dateFormat.format(date) + ".tsv") ;
				PrintWriter out = new PrintWriter(file);
				out.write(error.toString());
				out.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			repaint();
		}
		else {
			System.out.println("No Selection ");
		}
		
		
	}
	public static DataObject getDataObject(){
		return d;
	}
}