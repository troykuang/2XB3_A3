package cas2xb3_A3_Kuang_CK;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class ConnectedCities {
	
	private int V;               //number of vertices
	private int E;                     //number of edges
	public static ST<String, City> st; // City name-> City Object
//	private City[] cities;  // index -> City
	private EdgeWeightedDigraph G;
	
	
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
    	 
 
//       G = new EdgeWeightedDigraph(st.size(),st);
//       System.out.println(DFS(G, "NEW YORK CITY", "SAN FRANCISCO"));
//       System.out.println(BFS(G, "NEW YORK CITY", "SAN FRANCISCO"));
//       DijkstraSP shortestpath=new DijkstraSP(G,"SAN FRANCISCO");
//       for(DirectedEdge e:shortestpath.pathTo("NEW YORK CITY")){
//    	   System.out.print(e.from()+"->"+e.to()+",");
//       }
//       System.out.println();
 
       
       
//       for (DirectedEdge currentEdge: G.allEdges()){
//    	   System.out.println(currentEdge.from()+" "+st.get(currentEdge.from()).getLongitude()+" "+st.get(currentEdge.from()).getLatitude());
//    	   System.out.println(currentEdge.to()+" "+st.get(currentEdge.to()).getLongitude()+" "+st.get(currentEdge.to()).getLatitude());
//    	   System.out.println(currentEdge);
//       }
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
    //	st.printST();
    }

    public static Iterable<String> DFS(EdgeWeightedDigraph G, String src, String des){
    	ST<String,City> edgeTo = new ST<String, City>();
    	ArrayList<String> visited = new ArrayList<String>();

        dfs(G, src, visited, edgeTo);

        if (visited.contains(des)){
            ArrayList<String> path = new ArrayList<String>();
            for (String i = des; !i.equals(src); i = edgeTo.get(i).getName())
                path.add(i);
            path.add(src);
            Collections.reverse(path);
            return path;
        }else return null;
    }

    private static void dfs(EdgeWeightedDigraph G, String src, ArrayList<String> visited, ST<String,City> edgeTo){
    	visited.add(src);
    	for (City currentCity: st.get(src).adj){
    		if (!visited.contains(currentCity.getName())){
    			edgeTo.put(currentCity.getName(), st.get(src));
    			dfs(G,currentCity.getName(),visited,edgeTo);
    		}
    	}
    }

    public static Iterable<String> BFS(EdgeWeightedDigraph G, String src, String des){
   
        ArrayList<String> visited = new ArrayList<String>();
        ST<String,City> edgeTo = new ST<String,City>();
      
        bfs(G, src, visited, edgeTo);

        if (visited.contains(des)){
            ArrayList<String> path = new ArrayList<String>();
            for (String i = des; !i.equals(src); i = edgeTo.get(i).getName())
                path.add(i);
            path.add(src);
            Collections.reverse(path);
            return path;
        }else return null;
        
       
    }

    private static void bfs(EdgeWeightedDigraph G, String src,ArrayList<String> visited, ST<String,City> edgeTo){
    	Queue<String> queue = new ArrayDeque<String>();
    	queue.add(src);
    	visited.add(src);
    	
    	while(!queue.isEmpty()){
    		String current = queue.poll();
    		for (City child: st.get(current).adj){
    			if(!visited.contains(child.getName())){
    				queue.add(child.getName());
    				edgeTo.put(child.getName(), st.get(current));
    				visited.add(child.getName());
    			} 
    		}		
    	}

    }
    
  public static String getRestaurantInfo(String cityName, ArrayList<Restaurant> places){
	  Double cityLon = st.get(cityName).getLongitude();
	  Double cityLat = st.get(cityName).getLatitude();
	  for (Restaurant current : places){
		  Double restLon = current.getLon();
		  Double restLat = current.getLat();
		  if ((Math.abs(cityLon-restLon) <= 0.5) && (Math.abs(cityLat-restLat) <= 0.5)){
			  return current.getAddNStateNTele();
		  }
	  }
	  return null;
  }
    
  public static void main(String[] args) throws IOException {
	  PrintWriter writer = new PrintWriter("a3_out.txt");
	  
	  ConnectedCities a = new ConnectedCities("data/connectedCities.txt");
	  EdgeWeightedDigraph G = new EdgeWeightedDigraph(ConnectedCities.st.size(),a.st);
	  BufferedReader input = new BufferedReader(new FileReader(("data/a3_in.txt")));
	  String src = input.readLine().toUpperCase();
	  String des = input.readLine().toUpperCase();
	  input.close();
	  
	  
      System.out.println(DFS(G, src, des));
      String dfsR = DFS(G, src, des).toString();
      writer.println("DFS: "+dfsR.substring(1, dfsR.length()-1));
      System.out.println(BFS(G, src, des));
      String bfsR = BFS(G, src, des).toString();
      writer.println("BFS: "+bfsR.substring(1, bfsR.length()-1));
      DijkstraSP shortestpath=new DijkstraSP(G,src);
      for(DirectedEdge e:shortestpath.pathTo(des)){
   	   System.out.print(e.from()+"->"+e.to()+",");
      }
      System.out.println();
      MinPQ<MealPlans> minMeals = new MinPQ<MealPlans>();
     
      ArrayList<Restaurant> BK = new ArrayList<Restaurant>();
      ArrayList<Restaurant> WD = new ArrayList<Restaurant>();
      ArrayList<Restaurant> MD = new ArrayList<Restaurant>();
      
      Scanner bk = new Scanner(new File(("data/burgerking.csv")));
      Scanner wd = new Scanner(new File(("data/wendys.csv")));
      Scanner md = new Scanner(new File(("data/mcdonalds.csv")));
      
      while (bk.hasNextLine()){
    	  Restaurant current = new Restaurant(bk.nextLine());
    	  BK.add(current);
      }
      bk.close();
      
      while (wd.hasNextLine()){
    	  Restaurant current = new Restaurant(wd.nextLine());
    	  WD.add(current);
      }
      wd.close();
      while (md.hasNextLine()){
    	  Restaurant current = new Restaurant(md.nextLine());
    	  MD.add(current);
      }
      md.close();
      
      Scanner in = new Scanner(new File(("data/menu.csv")));
      while (in.hasNextLine()) {
    	  MealPlans currentMeal = new MealPlans(in.nextLine());
    	  minMeals.insert(currentMeal);
    	  }
      Double currentGas = 0.0;
      String cityName = src;
      Double costOfMeal = 0.0;
      Double totalCost = 0.0;
      Double totalMealCost = 0.0;
      Double totalFuelCost = 0.0;
      Double currentTotal = currentGas + costOfMeal;
      String restInfo;
      System.out.printf("%-15s%-70s%-15s%-15s%-15s","City","Meal Choice","Cost of Meal","Cost of Fuel","Current Total Cost");
      System.out.println();
      System.out.printf("%-15s%-70s%-15s%-15s%-15s",cityName,"N/A",costOfMeal,currentGas,currentTotal);
      System.out.println();
      for(DirectedEdge e:shortestpath.pathTo("MIAMI")){
    	  cityName = e.to();
    	  MealPlans currentMeal = minMeals.delMin();
    	  char name = currentMeal.getMealName().charAt(0);
    	  if (name == 'M'){
    		  restInfo = getRestaurantInfo(cityName, MD);
    	  }
    	  else if (name == 'W'){
    		  restInfo = getRestaurantInfo(cityName, WD);
    	  }
    	  else{
    		  restInfo = getRestaurantInfo(cityName, BK);
    	  }
    	  
    	  costOfMeal = currentMeal.getPrice();
    	  totalMealCost += costOfMeal;
    	  currentGas = ((int)(e.weight()*100))/100.0;
    	  totalFuelCost += currentGas;
    	  currentTotal = costOfMeal+currentGas;
    	  totalCost += currentTotal;
    	  System.out.printf("%-15s%-70s%-15s%-15s%.2f",cityName,currentMeal,costOfMeal,currentGas,currentTotal);
    	  System.out.println();
    	  System.out.printf("%-15s%-15s","",restInfo);
    	  System.out.println();
      }
      System.out.printf("%-85s%-15s%-15s%.2f","Total:",totalMealCost,totalFuelCost,totalCost);
      System.out.println();
      writer.close();

    	
    	
    	
    }
}
       
      

