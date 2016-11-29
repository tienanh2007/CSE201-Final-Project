import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.TextLayout;
import java.util.ArrayList;

import javax.swing.JPanel;import javax.swing.border.StrokeBorder;

public class TextDisplay extends JPanel{
	private int r,d,o;
	public TextDisplay(ArrayList<Integer> repulicans,ArrayList<Integer> democrats,ArrayList<Integer> others, int[] index){
		for(int i=0;i<index.length;i++){
			r += repulicans.get(index[i]);
			d += democrats.get(index[i]);
			o += others.get(index[i]);
		}
	}
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		RenderingHints hint = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHints(hint);
		g2.setFont(new Font("Cool", Font.BOLD, (int)(0.02*(getHeight()+getWidth()))));
		g2.setColor(Color.RED);
		g2.drawString("" + r, (int)(0.07*(getWidth())), (int)(0.15*(getHeight())));
		g2.setColor(Color.BLUE);
		g2.drawString("" + d, (int)(0.07*(getWidth())), (int)(0.3*(getHeight())));
		g2.setColor(Color.GRAY);
		g2.drawString("" + o, (int)(0.07*(getWidth())), (int)(0.45*(getHeight())));
		g2.setFont(new Font("Cool", Font.PLAIN, (int)(0.01*(getHeight()+getWidth()))));
		g2.setColor(Color.BLACK);
		g2.drawString("Republicans", (int)(0.07*(getWidth())), (int)(0.17*(getHeight())));
		g2.drawString("Democrats", (int)(0.07*(getWidth())), (int)(0.32*(getHeight())));
		g2.drawString("Others", (int)(0.07*(getWidth())), (int)(0.47*(getHeight())));
	}
}
