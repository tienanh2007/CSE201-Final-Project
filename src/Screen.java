import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class Screen extends JFrame{
	private BarGraph bg;
	private PieGraph pg;
	private TextDisplay td;
	private JPanel panel1, panel2;
	private FileChooser fi;
	private PickingPane pp;
	private JTextArea pickedCounties = new JTextArea(30,40);
	private JScrollPane scroller = new JScrollPane(pickedCounties);
	public Screen(){
		pickedCounties.setEditable(false);
		setSize(1300, 700);
		setTitle("CSE201 Project");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel1();
		setVisible(true);
	}
	public void panel1(){
		panel1 = new JPanel(new BorderLayout());
		JPanel buttonPanel = new JPanel();
		fi = new FileChooser();
		JButton next1 = new JButton("open");
		JButton exit = new JButton("exit");
		JLabel ins1 = new JLabel("Press browse to choose a file, The text area will show any errors if"
				+ " they exist in the folder you picked, Then proceed to press next");
		buttonPanel.add(ins1);
		buttonPanel.add(next1);
		buttonPanel.add(exit);
		next1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(fi.getDataObject() != null){
					String[] a = new String[fi.getDataObject().getOthers().size()];
					for(int i=0;i<a.length;i++)
						a[i] = fi.getDataObject().getStates().get(i);
					pp = new PickingPane(a);
					new Window(td, bg, pg, scroller, fi, pp, pickedCounties);
				}
			}
		});
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panel1.add(fi, BorderLayout.NORTH);
		panel1.add(buttonPanel, BorderLayout.CENTER);
		setContentPane(panel1);
	}
	public static void main(String[] args) throws Exception {
		new Screen();
	}
}
