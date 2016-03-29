package cas2xb3_A3_Kuang_CK;

import java.util.ArrayList;

public class Restaurant {
	private final String nameNstate;
	private final double latitude;
	private final double longitude;
	private final String addNstateNtele;
	
	public Restaurant(String a){
		String[] info = a.split(",\"");
		String[] coor = info[0].split(",");
		this.longitude = -(Double.parseDouble(coor[0]));
		this.latitude = (Double.parseDouble(coor[1]));
		this.nameNstate = info[1].substring(0, info[1].length()-1);
		this.addNstateNtele = info[2].substring(0, info[2].length()-1);
	}
	
	public Double getLon(){
		return longitude;
	}
	
	public Double getLat(){
		return latitude;
	}
	
	public String getNameNState(){
		return nameNstate;
	}
	
	public String getAddNStateNTele(){
		return addNstateNtele;
	}

}
