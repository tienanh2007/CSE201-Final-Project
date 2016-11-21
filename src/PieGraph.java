import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class PieGraph{
	private ArrayList<Integer> repulicans, democrats, others;
	private int[] index;
	private PieChart chart;
	private int r, d, o;
	private double rp, dp, op, sum;
	public PieGraph(ArrayList<Integer> repulicans,ArrayList<Integer> democrats,ArrayList<Integer> others, int[] index){
		this.repulicans = repulicans;
		this.democrats = democrats;
		this.others = others;
		this.index = index;
		for(int i=0;i<index.length;i++){
			r += repulicans.get(index[i]);
			d += democrats.get(index[i]);
			o += others.get(index[i]);
		}
		chart = new PieChart(r, d, o);
	}
	public PieChart getPieChart(){
		return chart;
	}
}
