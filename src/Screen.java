import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Screen extends JFrame{
	private BarGraph bg;
	private PieGraph pg;
	private JPanel panel1, panel3, panel2;
	private FileChooser fi;
	private PickingPane pp;
	public Screen(){
		setSize(1300, 700);
		setTitle("A Bar Chart");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel1();
		setVisible(true);
	}
	public void panel1(){
		panel1 = new JPanel(new BorderLayout());
		fi = new FileChooser();
		JButton next1 = new JButton("next");
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
		panel1.add(next1, BorderLayout.SOUTH);
		setContentPane(panel1);
	}
	public void panel2(){
		panel2 = new JPanel();
		String[] a = new String[fi.getDataObject().getOthers().size()];
		for(int i=0;i<a.length;i++)
			a[i] = fi.getDataObject().getStates().get(i);
		pp = new PickingPane(a);
		panel2.add(pp);
		JButton next2 = new JButton("next");
		next2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(pp.getSelected() != null && pp.getSelected().length > 0){
					panel3();
					setContentPane(panel3);
					invalidate();
					validate();
				}
			}
		});
		panel2.add(next2);
	}
	public void panel3(){
		panel3 = new JPanel(new BorderLayout());
		getGraphs(pp.getSelected());
		JPanel controlPanel = new JPanel(new GridLayout(10, 1, 1, 10));
		JTabbedPane tabs = new JTabbedPane();
		JButton pie = new JButton("Change counties");
		JButton all = new JButton("Get All");
		JButton back = new JButton("Back");
		controlPanel.add(pie);
		controlPanel.add(all);
		controlPanel.add(back);
		all.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] a = new int[fi.getDataObject().getDemocratics().size()];
				for(int i=0;i<a.length;i++) a[i] = i;
				getGraphs(a);
				tabs.removeAll();
				tabs.add("Pie", pg.getPieChart());
				tabs.add("Bar", bg);
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
				repaint();
			}
		});
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel1();
				setContentPane(panel1);
				invalidate();
				validate();
			}
		});
		tabs.add("Pie", pg.getPieChart());
		tabs.add("Bar", bg);
		panel3.add(tabs, BorderLayout.CENTER);
		panel3.add(controlPanel, BorderLayout.WEST);
	}
	public static void main(String[] args) throws Exception {
		new Screen();
	}
	public void getGraphs(int[] selected){
		bg = new BarGraph(fi.getDataObject().getRepulicans(), fi.getDataObject().getDemocratics()
				, fi.getDataObject().getOthers(), selected);
		pg = new PieGraph(fi.getDataObject().getRepulicans(), fi.getDataObject().getDemocratics()
				, fi.getDataObject().getOthers(), selected);
	}
}
