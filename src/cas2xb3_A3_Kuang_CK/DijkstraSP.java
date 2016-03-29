package cas2xb3_A3_Kuang_CK;



public class DijkstraSP {

	private DirectedEdge[] edgeTo;
	private double[] distTo;
	private IndexMinPQ<Double> pq;
	
	private HashTableLP<String,Integer> StoI;
	private HashTableLP<Integer,String> ItoS;	
	
	public DijkstraSP(EdgeWeightedDigraph G,String src){
		StoI=new HashTableLP<String,Integer>();
		ItoS=new HashTableLP<Integer,String>();
		
		
		for (String current: G.st.cityNames()){
			 ItoS.put(StoI.size(), current);
			 StoI.put(current, StoI.size());
		}
		
		
		
		
		edgeTo=new DirectedEdge[G.V()];
		distTo=new double[G.V()];
		pq=new IndexMinPQ<Double>(G.V());
		for(int i=0;i<G.V();i++){
			distTo[i]=Double.POSITIVE_INFINITY;
		}
		
		distTo[StoI.get(src)]=0.0;

		
		pq.insert(StoI.get(src),0.0);
		while(!pq.isEmpty()){
			relax(G,pq.delMin());
		}		
	}
	
	
	private void relax(EdgeWeightedDigraph G,int v){
		for(DirectedEdge e:G.edges(ItoS.get(v))){
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
		if(!hasPathTo(v)) return null;
		Stack<DirectedEdge> path=new Stack<DirectedEdge>();
		for(DirectedEdge e=edgeTo[StoI.get(v)];e!=null;e=edgeTo[StoI.get(e.from())]){
			path.push(e);
		}
		return path;
	}
	
	
	
	
	
	
	
	
	
}
