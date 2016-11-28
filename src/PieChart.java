import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;

class Slice {
	double value;
	Color color;
	public Slice(double value, Color color) {  
		this.value = value;
		this.color = color;
	}
}
class PieChart extends JComponent {
	ArrayList<Slice> slices = new ArrayList<>();
	int r, d, o;
	PieChart(int r, int d, int o) {
		slices.add(new Slice(r, Color.RED));
		slices.add(new Slice(d, Color.BLUE));
		slices.add(new Slice(o, Color.GRAY));
		this.r = r; this.d = d; this.o = o;
	}
	public void paint(Graphics g) {
		drawPie((Graphics2D) g, getBounds(), slices);
	}
	void drawPie(Graphics2D g, Rectangle area, ArrayList<Slice> slices) {
		Graphics2D g2 = (Graphics2D) g;
		RenderingHints hint = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHints(hint);
		double total = 0.0D;
		for (int i = 0; i < slices.size(); i++) {
			total += slices.get(i).value;
		}
		double curValue = 0.0D;
		int startAngle = 0;
		for (int i = 0; i < slices.size(); i++) {
			startAngle = (int) (curValue * 360 / total)+1;
			int arcAngle = (int) (slices.get(i).value * 360 / total)+1;
			g2.setColor(slices.get(i).color);
			g2.fillArc(area.x+area.width/4, area.y, area.width/2,area.width/2, 
					startAngle, arcAngle);
			curValue += slices.get(i).value;
		}
		g2.setColor(Color.RED);
		g2.drawString("Republican: "+r , 25, 10);
		g2.fillRect(10, 0, 10, 10);
		g2.setColor(Color.BLUE);
		g2.drawString("Democrats: "+d , 25, 25);
		g2.fillRect(10, 15, 10, 10);
		g2.setColor(Color.GRAY);
		g2.drawString("Others: "+o , 25, 40);
		g2.fillRect(10, 30, 10, 10);
	}
	
}