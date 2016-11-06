import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Screen extends JFrame{
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel(new GridLayout(1, 1));
	FileChooser fi;
	PickingPane pp;
	public Screen(){
		setSize(1300, 600);
		setTitle("A Bar Chart");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel1();

		setVisible(true);
	}
	public void panel1(){
		fi = new FileChooser();
		panel1.add(fi);
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
		panel1.add(next1);
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
		BarGraph bg = new BarGraph(fi.getDataObject().getRepulicans(), fi.getDataObject().getDemocratics()
				, fi.getDataObject().getOthers(), pp.getSelected());
		panel3.add(bg);
	}
	public static void main(String[] args) throws Exception {
		new Screen();
	}
}
