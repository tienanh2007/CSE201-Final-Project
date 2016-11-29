import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
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
	private double scale  = 0.65;
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
		
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		RenderingHints hint = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHints(hint);
		g2.setColor(Color.GRAY);
		while((int)(0.15*getHeight()+10*((int)roundForAxis((int)max, 10)*10/max*(int)(scale/10*getHeight()))) > getHeight()*0.95) scale-=0.05;
		repul = (r/max)*(int)(scale*getHeight());
		demo = (d/max)*(int)(scale*getHeight());
		other = (o/max)*(int)(scale*getHeight());
		g2.setFont(new Font("scalable", Font.PLAIN, (int)(0.01*(getHeight()+getWidth()))));
		g2.drawLine((int)(0.08*getWidth()), getHeight()-(int)(0.15*getHeight()), (int)(0.08*getWidth()), (int)(getHeight()-10*((int)roundForAxis((int)max, 10)*10/max*(int)(scale/10*getHeight()))-(int)(0.15*getHeight())));
		for(int i=0;i<11;i++){
			g2.drawLine((int)(0.07*getWidth()), (int)(getHeight()-i*((int)roundForAxis((int)max, 10)*10/max*(int)(scale/10*getHeight()))-(int)(0.15*getHeight())),
					(int)(0.09*getWidth()), (int)(getHeight()-i*((int)roundForAxis((int)max, 10)*10/max*(int)(scale/10*getHeight()))-(int)(0.15*getHeight())));
			g2.drawString(""+(int)roundForAxis((int)max, 10)*i, (int)(0.008*getWidth()), (int)(getHeight()-i*((int)roundForAxis((int)max, 10)*10/max*(int)(scale/10*getHeight()))-(int)(0.15*getHeight())));
		}
		g2.setColor(Color.RED);
		g2.fillRect((int)(0.2*getWidth()), (int) (getHeight()-(int)(0.15*getHeight())-repul), (int)(0.15*getWidth()), (int)repul);
		g2.drawString("Republicans  "+r, (int)(0.2*getWidth()), getHeight()-(int)(0.07*getHeight()));
		g2.setColor(Color.BLUE);
		g2.fillRect((int)(0.4*getWidth()), (int) (getHeight()-(int)(0.15*getHeight())-demo), (int)(0.15*getWidth()), (int)demo);
		g2.drawString("Democrats  "+d, (int)(0.4*getWidth()), getHeight()-(int)(0.07*getHeight()));
		g2.setColor(Color.GRAY);
		g2.fillRect((int)(0.6*getWidth()), (int) (getHeight()-(int)(0.15*getHeight())-other), (int)(0.15*getWidth()), (int)other);
		g2.drawString("Others  "+o, (int)(0.6*getWidth()), getHeight()-(int)(0.07*getHeight()));
	}
	public double roundForAxis(int range, int tickCount){
		double unroundedTickSize = range/tickCount*2;
		double x = Math.ceil(Math.log10(unroundedTickSize)-1);
		double pow10x = Math.pow(10, x);
		double roundedTickRange = Math.ceil(unroundedTickSize / pow10x)/2 * pow10x;
		return roundedTickRange;
	}
}
