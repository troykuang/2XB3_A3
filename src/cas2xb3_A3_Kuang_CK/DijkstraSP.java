package cas2xb3_A3_Kuang_CK;



public class DijkstraSP {

	private DirectedEdge[] edgeTo;
	private double[] distTo;
	private IndexMinPQ<Double> pq;
	// Using two hash tables to convert every city to an integer
	// And convert it back
	// So we can use index to explore the graph using Dijkstra's algorithm
	private HashTableLP<String,Integer> StoI; 
	private HashTableLP<Integer,String> ItoS;	
	
	public DijkstraSP(EdgeWeightedDigraph G,String src){
		StoI=new HashTableLP<String,Integer>();
		ItoS=new HashTableLP<Integer,String>();
		
		// Associate all the cities with integers
		// And all the integers with cities
		for (String current: G.st.cityNames()){
			 ItoS.put(StoI.size(), current);
			 StoI.put(current, StoI.size());
		}
		// Dijkstra's algorithm
		edgeTo=new DirectedEdge[G.V()];
		distTo=new double[G.V()];
		pq=new IndexMinPQ<Double>(G.V());
		// Set the distance all to infinity at first
		for(int i=0;i<G.V();i++){
			distTo[i]=Double.POSITIVE_INFINITY;
		}
		distTo[StoI.get(src)]=0.0;
		pq.insert(StoI.get(src),0.0);
		while(!pq.isEmpty()){
			// Relax related edges
			relax(G,pq.delMin());
		}		
	}
	
	private void relax(EdgeWeightedDigraph G,int v){
		for(DirectedEdge e:G.edges(ItoS.get(v))){
		// If we find a distance is shorter than the current one 
		// we replace the current one with the shorter one
			int w=StoI.get(e.to());
			if(distTo[w]> (distTo[v]+e.weight())){
				distTo[w]=distTo[v]+e.weight();
				edgeTo[w]=e;
				if(pq.contains(w)) pq.changeKey(w, distTo[w]);
				else pq.insert(w,distTo[w]);
			}
		}
	}
	
	public double distTo(String v){return distTo[StoI.get(v)];}
	
	public boolean hasPathTo(String v){return distTo[StoI.get(v)]<Double.POSITIVE_INFINITY;}
	
	public Iterable<DirectedEdge> pathTo(String v){
		// Moving backwards to get the whole path
		if(!hasPathTo(v)) return null;
		Stack<DirectedEdge> path=new Stack<DirectedEdge>();
		for(DirectedEdge e=edgeTo[StoI.get(v)];e!=null;e=edgeTo[StoI.get(e.from())]){
			path.push(e);
		}
		return path;
	}
}
