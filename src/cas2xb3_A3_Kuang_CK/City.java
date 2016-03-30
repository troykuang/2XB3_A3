package cas2xb3_A3_Kuang_CK;

import java.util.ArrayList;

public class City {
	private final String cityName;
	private ArrayList<String> stateAbbr = new ArrayList<String>();
	private double latitude;
	private double longitude;
	public ArrayList<City> adj;
	public ArrayList<DirectedEdge> edges;

	/**
	 * The constructor of City
	 * @param a - A string includes the name of the city and the state
	 */
	public City(String a){
		this.adj = new ArrayList<City>();
		this.edges = new ArrayList<DirectedEdge>();
		String[] info = a.split("\\(",2);
		this.cityName = info[0].substring(0, info[0].length()-1);
		// A string contains all the states that this city is on.
		String stateInfo = info[1].substring(0, info[1].length());
		String[] states = stateInfo.split(",");
		// Save all states' abbreviations in an ArrayList
		for (int i =0;i<states.length;i++){
			if (states[i].startsWith(" ")){
				this.stateAbbr.add(states[i].substring(1));
			}
			else{
				this.stateAbbr.add(states[i]);
			}
			
		}
	}
	// Return the states that this city is on
	public ArrayList<String> getState(){
		return stateAbbr;
	}
	// Return latitude of the city
	public double getLatitude(){
		return latitude;
	}
	// Return longitude of the city
	public double getLongitude(){
		return longitude;
	}
	// Set longitude
	public void setLA(String a){
		this.latitude = Double.parseDouble(a);
	}
	// Set latitude
	public void setLO(String a){
		this.longitude = Double.parseDouble(a);
	}
	// Return city name
	public String getName(){
		return cityName;
	}
	
	public String toString(){
		String state = "";
		String connections = "";
		for (String a : stateAbbr){
			state = state + a + " ";
		}
		for (City currentCity: adj){
			connections = connections + currentCity.getName() +" ";
		}
		return this.cityName+"|"+state+"|latitude|"+this.latitude+"|longitute|"+this.longitude+"|"+connections;
	}
	

//	public static void main(String[] args) {
//		String a = "St. Louis (MO, IL)";
//		City current = new City(a);
//		System.out.println(current);
//	}

}

