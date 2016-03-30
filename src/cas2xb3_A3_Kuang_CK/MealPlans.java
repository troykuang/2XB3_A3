package cas2xb3_A3_Kuang_CK;

public class MealPlans implements Comparable<MealPlans> {
	private final String place;
	private final String mealName;
	private final Double price;
	
	/**
	 * Constructor of Meal Plans
	 * A MealPlans object contains the city of the restaurant, 
	 * the name of the meal and the price of the meal.
	 *
	 */
	public MealPlans(String a){
		String[] info = a.split(",");
		this.place = info[0];
		this.mealName = info[1];
		this.price = Double.parseDouble(info[2].substring(1));
	}
	
	// Return the location of the meal 
	public String getPlace(){
		return place;
	}

	// This is for MinPQ
	@Override
	public int compareTo(MealPlans that) {
		if (price > that.price) return 1;
		else if (price < that.price) return -1;
		else return 0;
	}
	// Return the name of the meal
	public String getMealName(){
		return mealName;
	}
	// Return the price of the meal
	public Double getPrice(){
		return price;
	}
	
	public String toString(){
		return (place +" - "+ mealName);
	}

}
