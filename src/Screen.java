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
		setTitle("A Bar Chart");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel1();
		setVisible(true);
	}
	public void panel1(){
		panel1 = new JPanel(new BorderLayout());
		JPanel buttonPanel = new JPanel();
		fi = new FileChooser();
		JButton next1 = new JButton("next");
		JLabel ins1 = new JLabel("Press browse to choose a file, The text area will show any errors if"
				+ " they exist in the folder you picked, Then proceed to press next");
		buttonPanel.add(ins1);
		buttonPanel.add(next1);
		next1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(fi.getDataObject() != null){
					panel2();
					setContentPane(panel2);
					invalidate();
					validate();
				}
			}
		});
		panel1.add(fi, BorderLayout.NORTH);
		panel1.add(buttonPanel, BorderLayout.CENTER);
		setContentPane(panel1);
	}
	public void panel2(){
		panel2 = new JPanel(new BorderLayout());
		JPanel buttonPanel2 = new JPanel();
		JPanel text = new JPanel();
		JButton change = new JButton("Pick");
		String[] a = new String[fi.getDataObject().getOthers().size()];
		for(int i=0;i<a.length;i++)
			a[i] = fi.getDataObject().getStates().get(i);
		pp = new PickingPane(a);
		JLabel ins2 = new JLabel("Choose Change to pick the county, sub-couties, or all counties you want,"
				+ " then proceed to press next to see the graphs.");
		change.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pp.doClick();
				getPicked(pp.getSelected(), pickedCounties);
				repaint();
			}
		});
		JButton next2 = new JButton("next");
		next2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(pp.getSelected() != null && pp.getSelected().length > 0){
					new Window(td, bg, pg, scroller, fi, pp, pickedCounties);
				}
			}
		});
		buttonPanel2.add(change);
		buttonPanel2.add(next2);
		text.add(ins2);
		text.add(scroller);
		panel2.add(buttonPanel2, BorderLayout.NORTH);
		panel2.add(text);
	}
	public static void main(String[] args) throws Exception {
		new Screen();
	}
	public void getGraphs(int[] selected){
		bg = new BarGraph(fi.getDataObject().getRepulicans(), fi.getDataObject().getDemocratics()
				, fi.getDataObject().getOthers(), selected);
		pg = new PieGraph(fi.getDataObject().getRepulicans(), fi.getDataObject().getDemocratics()
				, fi.getDataObject().getOthers(), selected);
		td = new TextDisplay(fi.getDataObject().getRepulicans(), fi.getDataObject().getDemocratics()
				, fi.getDataObject().getOthers(), selected);
	}
	public void getPicked(int[] selected, JTextArea show){
		StringBuilder sb = new StringBuilder();
		for(int i:selected){
			sb.append(fi.getDataObject().getStates().get(i)+"\n");
		}
		show.setText(sb.toString());
	}
}
