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
		g2.setFont(new Font("Cool", Font.BOLD, 30));
		g2.setColor(Color.RED);
		g2.drawString("" + r, 100, 100);
		g2.setColor(Color.BLUE);
		g2.drawString("" + d, 100, 200);
		g2.setColor(Color.GRAY);
		g2.drawString("" + o, 100, 300);
		g2.setFont(new Font("Cool", Font.PLAIN, 15));
		g2.setColor(Color.BLACK);
		g2.drawString("Repulicans", 100, 120);
		g2.drawString("Democrats", 100, 220);
		g2.drawString("Others", 100, 320);
	}
}
