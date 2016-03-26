package cas2xb3_A3_Kuang_CK;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;



public class ConnectedCities {
	
	private int V;               //number of vertices
	private int E;                     //number of edges
	private static ST<String, City> st; // City name-> City Object
//	private City[] cities;  // index -> City
	
	
	 /**  
     * Initializes an edge-weighted graph from an input stream.
     * @param  in the input stream
     * @throws IndexOutOfBoundsException if the endpoints of any edge are not in prescribed range
     * @throws IllegalArgumentException if the number of vertices or edges is negative
     */
    public ConnectedCities(String file)throws FileNotFoundException {
    	Scanner in = new Scanner(new File(file));
    	st = new ST<String, City>();
    	while (in.hasNextLine()) {
           String[] a = in.nextLine().split("\\), ");
           String cityA = a[0].toUpperCase();
           String cityB = a[1].substring(0, a[1].length()-1).toUpperCase();
           City A = new City(cityA);
           City B = new City(cityB);
           if (!st.contains(A.getName())){
        	   st.put(A.getName(),A);
           }
           if (!st.contains(B.getName())){
        	   st.put(B.getName(),B);
           }
           st.get(A.getName()).adj.add(st.get(B.getName()));
           st.get(B.getName()).adj.add(st.get(A.getName()));
           
           
       }
    	 in.close();
    	 setLALO("data/zips1990.csv");
//    	 all the distances
//    	 for (String currentCity:st.cityNames()){
//    		 for (City B: st.get(currentCity).adj){
//    			 System.out.println("The distance from "+currentCity+" to "+B.getName()+" is "+distance2B(st.get(currentCity),B));
//    		 }	
//    	 }
    	 
 
       EdgeWeightedDigraph G = new EdgeWeightedDigraph(st.size(),st);
       for (DirectedEdge currentEdge: G.allEdges()){
    	   System.out.println(currentEdge.from()+" "+st.get(currentEdge.from()).getLongitude()+" "+st.get(currentEdge.from()).getLatitude());
    	   System.out.println(currentEdge.to()+" "+st.get(currentEdge.to()).getLongitude()+" "+st.get(currentEdge.to()).getLatitude());
    	   System.out.println(currentEdge);
       }
    }
    
    private static void setLALO(String file) throws FileNotFoundException{
    	Scanner in = new Scanner(new File(file));
    	while (in.hasNextLine()) {
    		String[] a = in.nextLine().split(",");
    		String stateABBR = a[2];
    		String cityName = a[3];
    		if ((st.contains(cityName)) && (st.get(cityName).getState().contains(stateABBR))){
    			String longi = a[4];
        		String lati = a[5];
        		st.get(cityName).setLA(lati);
        		st.get(cityName).setLO(longi);
    		}
    	}
    	in.close();
    	st.printST();
    }

    
    public static void main(String[] args) throws FileNotFoundException {
    	ConnectedCities a = new ConnectedCities("data/connectedCities.txt");
    	
    	
    	
    }
}
       
       
       
//       while (in.hasNextLine()) {
//           String[] a = in.nextLine().split(",");
//           String cityA = a[0];
//           String cityB = a[1].substring(1);
//           City A = new City(cityA);
//           City B = new City(cityB);
//           int v = st.get(A);
//           int w = st.get(B);
//           G.addEdge(v,w,0);
//         
//       in.close();
//   }

       
       
       
       
       
//       this.V=x.nextInt();
//       this.E=x.nextInt();
//       if (E < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
//       adj=(LinkedList<Edge>[])new LinkedList[V];
//       for(int i=0;i<V;i++){
//			adj[i]=new LinkedList<Edge>();
//		}
//        
//       for (int i = 0; i < E; i++) {
//            int v = x.nextInt();
//            int w = x.nextInt();
//            double weight = x.nextDouble();
//            Edge e = new Edge(v, w, weight);
//            addEdge(e);
//        }
//        x.close();
//    }
//	
//	
//	public int V(){return V;}
//	
//	public int E(){return E;}
//	
//	public void addEdge(Edge e){
//		int v=e.either();
//		int w=e.other(v);
//		adj[v].add(e);
//		adj[w].add(e);
//		E++;
//	}
//	
//	public Iterable<Edge> adj(int v){
//		return adj[v];
//	}
//	
//	public int degree(int v) {
//        return adj[v].size();
//    }
//	
//	public Iterable<Edge> edges(){
//		LinkedList<Edge> list=new LinkedList<Edge>();
//		for(int v=0;v<V;v++){
//			 int selfLoops = 0;
//	         for (Edge e : adj(v)) {
//	             if (e.other(v) > v) {
//	                 list.add(e);
//	             }
//	             // only add one copy of each self loop (self loops will be consecutive)
//	             else if (e.other(v) == v) {
//	                 if (selfLoops % 2 == 0) list.add(e);
//                     selfLoops++;
//                }
//	        }
//		}
//		return list;
//	}
//	
//	public String toString() {
//        StringBuilder s = new StringBuilder();
//        s.append(V + " " + E + NEWLINE);
//        for (int v = 0; v < V; v++) {
//            s.append(v + ": ");
//            for (Edge e : adj[v]) {
//                s.append(e + "  ");
//            }
//            s.append(NEWLINE);
//        }
//        return s.toString();
//    }

	
	

