import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class DataObject {
	private ArrayList<String> states = new ArrayList<>();
	private ArrayList<Integer> repulicans = new ArrayList<>();
	private ArrayList<Integer> democratics = new ArrayList<>();
	private ArrayList<Integer> others = new ArrayList<>();
	private ArrayList<File> files;
	public DataObject(ArrayList<File> files) throws Exception{
		this.files = files;
		takeData();
	}
	public void takeData() throws Exception{
		for(File file:files){
			Scanner in = new Scanner(file);
			while(in.hasNextLine()){
				String[] datas = in.nextLine().split(",");
				if(states.contains(datas[0])){
					int index = states.indexOf(datas[0]);
					repulicans.set(index, repulicans.get(index)+Integer.parseInt(datas[4]));
					democratics.set(index, democratics.get(index)+Integer.parseInt(datas[2]));
					others.set(index, others.get(index)+Integer.parseInt(datas[3]));
				}
				else{
					states.add(datas[0]);
					repulicans.add(Integer.parseInt(datas[4]));
					democratics.add(Integer.parseInt(datas[2]));
					others.add(Integer.parseInt(datas[3]));
				}
			}
		}
	}
	public ArrayList<String> getStates() {
		return states;
	}
	public ArrayList<Integer> getRepulicans() {
		return repulicans;
	}
	public ArrayList<Integer> getDemocratics() {
		return democratics;
	}
	public ArrayList<Integer> getOthers() {
		return others;
	}

}
