import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class PickingPane extends JButton{
	private int[] selected;
	private String[] options = {};
	public PickingPane(String[] option){
		setText("Change");
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JList list = new JList(option);
				JOptionPane.showMessageDialog(
						null, list, "Multi-Select Example", JOptionPane.PLAIN_MESSAGE);
				selected = list.getSelectedIndices();
				if(selected.length == 0){
					selected = new int[option.length];
					for(int i=0;i<option.length;i++) selected[i] = i;
				}
			}
		});
	}
	public int[] getSelected(){
		return selected;
	}
}
