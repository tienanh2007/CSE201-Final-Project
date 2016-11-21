import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DataObject {
	private HashMap<Integer, ArrayList<String>> errors = new HashMap<>();
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
			int lines = 0;
			while(in.hasNextLine()){
				lines++;
				String[] datas = in.nextLine().split(",");
				checkForError(datas, lines, file.getName());
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
	private void checkForError(String[] datas, int lines, String file){
		if(Integer.parseInt(datas[4]) < 0 || Integer.parseInt(datas[2]) < 0
				|| Integer.parseInt(datas[3]) < 0){
			if(errors.containsKey(lines)) errors.get(lines).add("Negative number error on line "+ lines +" in "+file);
			else{
				errors.put(lines, new ArrayList<>());
				errors.get(lines).add("Negative number error on line "+ lines +" in "+file);
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
	public HashMap<Integer, ArrayList<String>> getErrors(){
		return errors;
	}
}
