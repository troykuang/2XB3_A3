package cas2xb3_A3_Kuang_CK;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class EdgeWeightedDigraph {
  
    private final int V;                // number of vertices in this digraph
    private int E;                      // number of edges in this digraph
    private static final String NEWLINE = System.getProperty("line.separator");
    public ST<String, City> st; // cityNames -> city object
    
    /**
     * Initializes an empty edge-weighted digraph
     *
     * @param  V the number of vertices
     * @throws FileNotFoundException 
     * 
     */
    public EdgeWeightedDigraph(int V, ST<String, City> st) throws FileNotFoundException {
        if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        this.V = V;
        this.E = 0;
        this.st = st;
        // According the assignment description: fuel efficiency is 8.2L/100km
        // The weight of each edge would be: distance * gas price * fuel efficiency
        for (String currentCity:st.cityNames()){
        	for (City B: st.get(currentCity).adj){
        		double weight = distance2B(st.get(currentCity),B);
        		addEdge(new DirectedEdge(currentCity, B.getName(), (weight/100)*8.2*getGasPrice(st.get(currentCity), B)));
        		addEdge(new DirectedEdge(B.getName(), currentCity, (weight/100)*8.2*getGasPrice(B ,st.get(currentCity))));
        	}
        }
    }
    
    private double getGasPrice(City A,City B) throws FileNotFoundException{
    	// Read StateGasPrice.csv
    	// Using a symbol table to associate all the state abbr(String) with gas price
    	ST<String,Double> gasPrice = new ST<String,Double>();
    	Scanner in = new Scanner(new File(("data/StateGasPrice.csv")));
    	while (in.hasNextLine()) {
           String[] a = in.nextLine().split(",");
           gasPrice.put(a[0], Double.parseDouble(a[1]));
    	}
    	in.close();
    	ArrayList<String> sharedStates = new ArrayList<String>();
    	// find if city A and city B are in the same state
    	for (String a : A.getState() ){
    		if (B.getState().contains(a)){
    			sharedStates.add(a);
    		}
    	}
    	int numberOfStates = 0;
		double totalPrice = 0.0;
		// Take average of all the states
    	if (sharedStates.size() == 0){
    		numberOfStates = A.getState().size()+B.getState().size();
    		totalPrice = 0.0;
    		for (String currentState : A.getState()){
    			totalPrice += gasPrice.get(currentState);
    		}
    		for (String currentState : B.getState()){
    			totalPrice += gasPrice.get(currentState);
    		}
    	}
    	else{
    		numberOfStates = sharedStates.size();
    		totalPrice = 0.0;
    		for (String currentState : sharedStates){
    			totalPrice += gasPrice.get(currentState);
    		}
    	}
    	double averageGas = (totalPrice/numberOfStates)/100;
    	
    	return averageGas;
    }
    
    // Add an directed edge to the graph
    public void addEdge(DirectedEdge e) {
        String v = e.from();
        String w = e.to();
        st.get(v).edges.add(e);
        st.get(w).edges.add(e);
    }
    
    // Calculate the distance(km) between two points on earth
    private static double distance2B(double lat1, double lon1, double lat2, double lon2){
    	double radiusEarth = 6371;
    	double deg2rad = 0.017453292519943295;
        double dLat = deg2rad*(lat2 - lat1); 
        double dLon = deg2rad*(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(deg2rad*(lat1)) * Math.cos(deg2rad*(lat2)) *Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = radiusEarth * c; // Distance in km
        return d;
    }
 // Calculate the distance(km) between two cities
    private static double distance2B(City A,City B){
    	double lat1 = A.getLatitude();
    	double lon1 = A.getLongitude();
    	double lat2 = B.getLatitude();
    	double lon2 = B.getLongitude();
    	return distance2B(lat1,lon1,lat2,lon2);
    }

    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in this edge-weighted digraph.
     *
     * @return the number of edges in this edge-weighted digraph
     */
    public int E() {
        return E;
    }



    /**
     * Returns the directed edges incident from vertex <tt>v</tt>.
     *
     * @param  v the vertex
     * @return the directed edges incident from vertex <tt>v</tt> as an Iterable
     * @throws IndexOutOfBoundsException unless 0 <= v < V
     */
    public Iterable<DirectedEdge> edges(String cityName) {
    	return st.get(cityName).edges;

    }

    /**
     * Returns all directed edges in this edge-weighted digraph.
     * To iterate over the edges in this edge-weighted digraph, use foreach notation:
     * <tt>for (DirectedEdge e : G.edges())</tt>.
     *
     * @return all edges in this edge-weighted digraph, as an iterable
     */
    public Iterable<DirectedEdge> allEdges() {
        LinkedList<DirectedEdge> list = new LinkedList<DirectedEdge>();
        for (String currentCityName: st.cityNames()){
        	for (DirectedEdge currentEdge : st.get(currentCityName).edges){
        		list.add(currentEdge);
        	}
        }
        return list;
    } 
    
//	public static void main(String[] args) {
//		
//		System.out.println(distance2B(44.981562,93.23928,40.785697,111.929054));
//	}

    

}
