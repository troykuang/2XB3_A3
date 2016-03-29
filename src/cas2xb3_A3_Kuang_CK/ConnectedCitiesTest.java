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

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDFS() throws FileNotFoundException {
		ConnectedCities a = new ConnectedCities("data/connectedCities.txt");
		G = new EdgeWeightedDigraph(a.st.size(),a.st);
		Iterable<String> dfs = ConnectedCities.DFS(G,"New York City","Miami");
		System.out.println(dfs);
		int dfsCount = 0;
		Set<String> s = new HashSet<String>();
		for (String current : dfs){
			dfsCount++;
			s.add(current);
		}
		int setCount = s.size();
		assertTrue(setCount == dfsCount);
		
	}

	@Test
	public void testBFS() {
		fail("Not yet implemented");
	}

}
