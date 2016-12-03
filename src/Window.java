import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class Window extends JFrame{
	private JPanel panel3;
	private JTextArea pickedCounties = new JTextArea(30,40);
	private PickingPane pp;
	private FileChooser fi;
	private JScrollPane scroller = new JScrollPane(pickedCounties);
	private BarGraph bg;
	private PieGraph pg;
	private TextDisplay td;
	public Window(TextDisplay td, BarGraph bg, PieGraph pg, JScrollPane scroller, FileChooser fi,
			PickingPane pp, JTextArea pickedCounties){
		this.pickedCounties = pickedCounties;
		this.pp = pp;
		this.td = td;
		this.bg = bg;
		this.scroller = scroller;
		this.fi = fi;
		setSize(1300, 700);
		setTitle("A Bar Chart");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panel3();
		setContentPane(panel3);
		setVisible(true);
		
	}
	public void panel3(){
		panel3 = new JPanel(new BorderLayout());
		int[] a = new int[fi.getDataObject().getStates().size()];
		for(int i=0;i<a.length;i++) a[i] = i;
		getGraphs(a);
		getPicked(a, pickedCounties);
		JPanel controlPanel = new JPanel(new GridLayout(2, 1));
		JPanel button = new JPanel(new GridLayout(5, 1, 1, 10));
		JPanel show = new JPanel();
		JTabbedPane tabs = new JTabbedPane();
		JButton pie = new JButton("Change counties");
		JButton all = new JButton("Get All");
		JButton close = new JButton("Close");
		JLabel total = new JLabel("Total Voters: "+ getTotal(a), SwingConstants.CENTER);
		JLabel counties = new JLabel("Total Counties: "+ a.length, SwingConstants.CENTER);
		button.add(pie);
		button.add(all);
		button.add(close);
		button.add(total);
		button.add(counties);
		pickedCounties.setColumns(20);
		pickedCounties.setRows(10);
		show.add(scroller);
		controlPanel.add(button);
		controlPanel.add(show);
		all.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] a = new int[fi.getDataObject().getDemocratics().size()];
				for(int i=0;i<a.length;i++) a[i] = i;
				getGraphs(a);
				tabs.removeAll();
				tabs.add("Pie", pg.getPieChart());
				tabs.add("Bar", bg);
				tabs.add("Text", td);
				getPicked(a, pickedCounties);
				total.setText("Total Voters: " + getTotal(a));
				counties.setText("Total Counties: " + a.length);
				repaint();
			}
		});
		pie.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pp.doClick();
				getGraphs(pp.getSelected());
				tabs.removeAll();
				tabs.add("Pie", pg.getPieChart());
				tabs.add("Bar", bg);
				tabs.add("Text", td);
				getPicked(pp.getSelected(), pickedCounties);
				total.setText("Total Voters: "+getTotal(pp.getSelected()));
				counties.setText("Total Counties: " + pp.getSelected().length);
				repaint();
			}
		});
		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		tabs.add("Pie", pg.getPieChart());
		tabs.add("Bar", bg);
		tabs.add("Text", td);
		panel3.add(tabs, BorderLayout.CENTER);
		panel3.add(controlPanel, BorderLayout.WEST);
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
	public int getTotal(int[] selected){
		int total = 0;
		for(int i:selected){
			total += fi.getDataObject().getRepulicans().get(i);
			total += fi.getDataObject().getDemocratics().get(i);
			total += fi.getDataObject().getOthers().get(i);
		}
		return total;
	}
}
