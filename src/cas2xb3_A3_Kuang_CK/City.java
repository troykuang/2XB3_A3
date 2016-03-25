package cas2xb3_A3_Kuang_CK;

import java.util.ArrayList;

public class City {
	private final String cityName;
	private ArrayList<String> stateAbbr = new ArrayList<String>();
	private double latitude;
	private double longitude;
	
	public City(String a){
		String[] info = a.split("\\(",2);
		this.cityName = info[0].substring(0, info[0].length()-1);
		String stateInfo = info[1].substring(0, info[1].length()-1);
		String[] states = stateInfo.split(",");
		for (int i =0;i<states.length;i++){
			if (states[i].startsWith(" ")){
				this.stateAbbr.add(states[i].substring(1));
			}
			else{
				this.stateAbbr.add(states[i]);
			}
			
		}
	}
	
	public void addLA(String a){
		this.latitude = Double.parseDouble(a);
	}
	
	public void addLO(String a){
		this.longitude = Double.parseDouble(a);
	}
	
	public String toString(){
		String state = "";
		for (String a : stateAbbr){
			state = state + a + " ";
		}
		return this.cityName+" "+state+"latitude"+this.latitude+"longitute"+this.longitude;
	}
	
	public static void main(String[] args) {
		String a = "St. Louis (MO, IL)";
		City current = new City(a);
		System.out.println(current);
	}

}

