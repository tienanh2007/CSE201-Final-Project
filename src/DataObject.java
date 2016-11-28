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
	private HashMap<String, Integer> auth = new HashMap<>();
	private ArrayList<File> files;
	public DataObject(ArrayList<File> files) throws Exception{
		this.files = files;
		for(File file:files)
			if(file.getName().equals("RegisteredOhioVoters.csv")){
				Scanner in = new Scanner(file);
				while(in.hasNextLine()){
					String[] datas = in.nextLine().split(",");
					auth.put(datas[0], Integer.parseInt(datas[1]));
				}
			}
		takeData();
		checkForMoreError();
	}
	public void takeData() throws Exception{
		for(File file:files){
			Scanner in = new Scanner(file);
			if(!file.getName().equals("RegisteredOhioVoters.csv")){
				int lines = 0;
				while(in.hasNextLine()){
					lines++;
					String[] datas = in.nextLine().split(",");
					if(!checkForError(datas, lines, file.getName())){
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
		}
	}
	private void checkForMoreError(){
		for(int i=0;i<states.size();i++){
			if(repulicans.get(i)+others.get(i)+democratics.get(i) > auth.get(states.get(i))){
				addError(-1, "", "Voters exceed registered voters for county " + states.get(i));
				repulicans.remove(i); others.remove(i); democratics.remove(i); states.remove(i);
			}
		}
	}
	private boolean checkForError(String[] datas, int lines, String file){
		boolean isError = false;
		if(Integer.parseInt(datas[4]) < 0 || Integer.parseInt(datas[2]) < 0
				|| Integer.parseInt(datas[3]) < 0){
			addError(lines, file, "Negative number");
			isError = true;
		}
		if(!auth.containsKey(datas[0])){
			addError(lines, file, "Invalid county: " + datas[0]);
			isError = true;
		}
		return isError;
	}
	private void addError(int lines, String file, String error){
		if(lines == -1){
			if(errors.containsKey(lines)) 
				errors.get(lines).add(error);
			else{
				errors.put(lines, new ArrayList<>());
				errors.get(lines).add(error);
			}
		}
		else if(errors.containsKey(lines)) 
			errors.get(lines).add(error + " error on line "+ lines +" in "+ file);
		else{
			errors.put(lines, new ArrayList<>());
			errors.get(lines).add(error + " error on line "+ lines +" in "+ file);
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
