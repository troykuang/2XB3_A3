package cas2xb3_A3_Kuang_CK;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConnectedCitiesTest {
	private EdgeWeightedDigraph G;
	private ConnectedCities a;

	@Before
	public void setUp() throws Exception {
		a = new ConnectedCities("data/connectedCities.txt");
		G = new EdgeWeightedDigraph(ConnectedCities.st.size(),a.st);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDFS() throws FileNotFoundException {
		String dfsR = ConnectedCities.DFS(G, "NEW YORK CITY", "MIAMI").toString();
		String dfs = dfsR.substring(1, dfsR.length()-1);
		System.out.println("DFS: "+dfs);
		Set<String> s = new HashSet<String>();
		String[] cities = dfs.split(", ");
		for (String current : cities){
			s.add(current);
		}
		int setCount = s.size();
		assertTrue(setCount == cities.length);
		

		for (int i=0;i<cities.length-1;i++){
		assertTrue(a.st.get(cities[i]).adj.contains(a.st.get(cities[i+1])));
		}
		
	}

	@Test
	public void testBFS() {
		String bfsR = ConnectedCities.BFS(G, "NEW YORK CITY", "MIAMI").toString();
		String bfs = bfsR.substring(1, bfsR.length()-1);
		System.out.println("BFS: "+bfs);
		Set<String> s = new HashSet<String>();
		String[] cities = bfs.split(", ");
		for (String current : cities){
			s.add(current);
		}
		int setCount = s.size();
		assertTrue(setCount == cities.length);
		
		for (int i=0;i<cities.length-1;i++){
			assertTrue(a.st.get(cities[i]).adj.contains(a.st.get(cities[i+1])));
			}
	}

}
