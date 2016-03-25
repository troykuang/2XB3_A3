package cas2xb3_A3_Kuang_CK;


public class Edge implements Comparable<Edge> {

	private final double weight;
	private final String v;
	private final String w;
	
	//initializing constructor
	public Edge(String v,String w,double weight){
        if (Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");
		this.v=v;
		this.w=w;
		this.weight=weight;
	}
	
	//weight of this edge
	public double weight(){return weight;}
	
	//compare this edge to that
	public int compareTo(Edge that){
		if(this.weight()>that.weight()) return +1;
		else if(this.weight()>that.weight()) return -1;
		else return 0;
	}
	
	//String representation 
	public String toString(){
		return String.format("%d-%d %.5f", v,w,weight);
	}
}
