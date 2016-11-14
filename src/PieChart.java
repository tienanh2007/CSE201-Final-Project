import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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
		double total = 0.0D;
		for (int i = 0; i < slices.size(); i++) {
			total += slices.get(i).value;
		}
		double curValue = 0.0D;
		int startAngle = 0;
		for (int i = 0; i < slices.size(); i++) {
			startAngle = (int) (curValue * 360 / total)+1;
			int arcAngle = (int) (slices.get(i).value * 360 / total)+1;
			g.setColor(slices.get(i).color);
			g.fillArc(area.x+area.width/4, area.y, area.width/2,area.width/2, 
					startAngle, arcAngle);
			curValue += slices.get(i).value;
		}
		g.setColor(Color.RED);
		g.drawString("Republican: "+r , 25, 10);
		g.fillRect(10, 0, 10, 10);
		g.setColor(Color.BLUE);
		g.drawString("Democrats: "+d , 25, 25);
		g.fillRect(10, 15, 10, 10);
		g.setColor(Color.GRAY);
		g.drawString("Others: "+o , 25, 40);
		g.fillRect(10, 30, 10, 10);
	}
	
}