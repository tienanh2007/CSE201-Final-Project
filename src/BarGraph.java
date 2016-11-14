import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
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
			System.out.println(index[i]);
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
		g.setColor(Color.GRAY);
		g.drawLine(100, getHeight()-100, 100, getHeight()-500);
		for(int i=0;i<11;i++){
			g.drawLine(90, (int)(getHeight()-i*((int)roundForAxis((int)max, 10)*10/max*30)-100), 110, (int)(getHeight()-i*((int)roundForAxis((int)max, 10)*10/max*30)-100));
			g.drawString(""+(int)roundForAxis((int)max, 10)*i, 10, (int)(getHeight()-i*((int)roundForAxis((int)max, 10)*10/max*30)-100));
		}
		g.setColor(Color.RED);
		g.fillRect(200, (int) (getHeight()-100-repul), 200, (int)repul);
		g.drawString("Republicans  "+r, 200, getHeight()-50);
		g.setColor(Color.BLUE);
		g.fillRect(450, (int) (getHeight()-100-demo), 200, (int)demo);
		g.drawString("Democrats  "+d, 450, getHeight()-50);
		g.setColor(Color.GRAY);
		g.fillRect(700, (int) (getHeight()-100-other), 200, (int)other);
		g.drawString("Others  "+o, 700, getHeight()-50);
	}
	public double roundForAxis(int range, int tickCount){
		double unroundedTickSize = range/tickCount*2;
		double x = Math.ceil(Math.log10(unroundedTickSize)-1);
		double pow10x = Math.pow(10, x);
		double roundedTickRange = Math.ceil(unroundedTickSize / pow10x)/2 * pow10x;
		return roundedTickRange;
	}
}
