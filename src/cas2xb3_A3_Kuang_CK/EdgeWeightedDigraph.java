package cas2xb3_A3_Kuang_CK;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class EdgeWeightedDigraph {
  
    private final int V;                // number of vertices in this digraph
    private int E;                      // number of edges in this digraph
    private int[] indegree;             // indegree[v] = indegree of vertex v
    private static final String NEWLINE = System.getProperty("line.separator");
    private ST<String, City> st; // cityNames -> city object
    
    /**
     * Initializes an empty edge-weighted digraph with <tt>V</tt> vertices and 0 edges.
     *
     * @param  V the number of vertices
     * @throws IllegalArgumentException if <tt>V</tt> < 0
     */
    public EdgeWeightedDigraph(int V, ST<String, City> st) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        this.V = V;
        this.E = 0;
        this.st = st;
        
        for (String currentCity:st.cityNames()){
        	for (City B: st.get(currentCity).adj){
        		double weight = distance2B(st.get(currentCity),B);
        		addEdge(new DirectedEdge(currentCity, B.getName(), weight));
        		addEdge(new DirectedEdge(B.getName(),currentCity, weight));
        	}
        }
    }
    
    public void addEdge(DirectedEdge e) {
        String v = e.from();
        String w = e.to();
        st.get(v).edges.add(e);
        st.get(w).edges.add(e);
    }
    
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
    
	public static void main(String[] args) {
		
		System.out.println(distance2B(44.981562,93.23928,40.785697,111.929054));
	}

    

}
