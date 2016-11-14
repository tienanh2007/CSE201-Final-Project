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
	private JPanel panel1 = new JPanel(new BorderLayout()),panel3 = new JPanel(new BorderLayout()), panel2 = new JPanel();
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
		String[] a = new String[fi.getDataObject().getOthers().size()];
		for(int i=0;i<a.length;i++)
			a[i] = fi.getDataObject().getStates().get(i);
		pp = new PickingPane(a);
		panel2.add(pp);
		JButton next2 = new JButton("next");
		next2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(pp.getSelected().length > 0){
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
		getGraphs();
		JPanel controlPanel = new JPanel();
		JTabbedPane tabs = new JTabbedPane();
		JButton pie = new JButton("Change counties");
		controlPanel.add(pie);
		pie.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pp.doClick();
				getGraphs();
				tabs.removeAll();
				tabs.add("Pie", pg.getPieChart());
				tabs.add("Bar", bg);
				repaint();
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
	public void getGraphs(){
		bg = new BarGraph(fi.getDataObject().getRepulicans(), fi.getDataObject().getDemocratics()
				, fi.getDataObject().getOthers(), pp.getSelected());
		pg = new PieGraph(fi.getDataObject().getRepulicans(), fi.getDataObject().getDemocratics()
				, fi.getDataObject().getOthers(), pp.getSelected());
	}
}
