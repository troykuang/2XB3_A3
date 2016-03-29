package cas2xb3_A3_Kuang_CK;



public class MealPlans implements Comparable<MealPlans> {
	private final String place;
	private final String mealName;
	private final Double price;
	
	public MealPlans(String a){
		String[] info = a.split(",");
		this.place = info[0];
		this.mealName = info[1];
		this.price = Double.parseDouble(info[2].substring(1));
	}
	
	public String getPlace(){
		return place;
	}

	@Override
	public int compareTo(MealPlans that) {
		if (price > that.price) return 1;
		else if (price < that.price) return -1;
		else return 0;
	}
	
	public String getMealName(){
		return mealName;
	}
	
	public String toString(){
		return (place +" - "+ mealName+" - $"+price);
	}

}
