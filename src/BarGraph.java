import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

public class BarGraph extends JPanel{
	private ArrayList<Integer> repulicans, democrats, others;
	private int[] index;
	private int r,d,o;
	private double repul,demo,other;
	private double max, min;
	
	public BarGraph(ArrayList<Integer> repulicans,ArrayList<Integer> democrats,ArrayList<Integer> others, int[] index){
		this.repulicans = repulicans;
		this.democrats = democrats;
		this.others = others;
		this.index = index;
		for(int i=0;i<index.length;i++){
			r += repulicans.get(index[i]);
			d += democrats.get(index[i]);
			o += others.get(index[i]);
		}
		max = Math.max(Math.max(r, d),o);
		min = Math.min(Math.min(r, d),o);
		repul = (r/max)*300;
		demo = (d/max)*300;
		other = (o/max)*300;
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		RenderingHints hint = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHints(hint);
		g2.setColor(Color.GRAY);
		g2.drawLine(100, getHeight()-100, 100, getHeight()-600);
		for(int i=0;i<11;i++){
			g2.drawLine(90, (int)(getHeight()-i*((int)roundForAxis((int)max, 10)*10/max*30)-100), 110, (int)(getHeight()-i*((int)roundForAxis((int)max, 10)*10/max*30)-100));
			g2.drawString(""+(int)roundForAxis((int)max, 10)*i, 10, (int)(getHeight()-i*((int)roundForAxis((int)max, 10)*10/max*30)-100));
		}
		g2.setColor(Color.RED);
		g2.fillRect(200, (int) (getHeight()-100-repul), 200, (int)repul);
		g2.drawString("Republicans  "+r, 200, getHeight()-50);
		g2.setColor(Color.BLUE);
		g2.fillRect(450, (int) (getHeight()-100-demo), 200, (int)demo);
		g2.drawString("Democrats  "+d, 450, getHeight()-50);
		g2.setColor(Color.GRAY);
		g2.fillRect(700, (int) (getHeight()-100-other), 200, (int)other);
		g2.drawString("Others  "+o, 700, getHeight()-50);
	}
	public double roundForAxis(int range, int tickCount){
		double unroundedTickSize = range/tickCount*2;
		double x = Math.ceil(Math.log10(unroundedTickSize)-1);
		double pow10x = Math.pow(10, x);
		double roundedTickRange = Math.ceil(unroundedTickSize / pow10x)/2 * pow10x;
		return roundedTickRange;
	}
}
