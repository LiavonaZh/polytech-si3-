package ads.lab8;

import ads.graph.*;

import java.util.Iterator;

/**
 * A class to find cycle in directed and undirected graphs
 */
public class Cycles {

	static private int[] c;
	/**
	 * returns true if graph 'G' has a cycle
	 */
	// DiGraph - directed graph
	public static boolean hasCycle(DiGraph G) {
		int n= G.nbVertices();
		int[] flags = new int[n];

		for(int i=0; i< n; i++){
			if(flags[i] == 0){
				if(findCycleD(G, flags, i))
					return true;
			}
		}
		return false;
	}

	private static boolean findCycleD(DiGraph G, int flags[], int i ) {
		flags[i] = 1;

		for(int adj : G.adjacents(i)){
			if(flags[adj] != 2){
				if(flags[adj] == 1) return true;
				if(findCycleD(G, flags, adj))
					return true;
			}
		}
		flags[i] = 2;
		return false;
	}

	
	/**
	 * returns true if graph 'G' has a cycle
	 */
	public static boolean hasCycle(UnDiGraph G) {
		int n= G.nbVertices();
		int[] flags = new int[n];

		for(int i=0; i< n; i++){
			if(flags[i] == 0){
				if(hasCycle(G, flags, -1, i))
					return true;
			}
		}
		return false;
	}

	private static boolean hasCycle(UnDiGraph G, int flags[], int pere, int i ) {
		flags[i] = 1;

		for(int adj : G.adjacents(i)){
			if(adj != pere){
				if(flags[adj] == 1) return true;
				if(hasCycle(G, flags, i, adj))
					return true;
			}
		}
		return false;
	}


	
	// a main to test your code
	public static void main(String[] s) {
		DiGraph a = new DiGraph(11);
		a.addEdge(0,9);
		a.addEdge(0,5);
		a.addEdge(1,3);
		a.addEdge(1,8);
		a.addEdge(2,6);
		a.addEdge(2,4);
		a.addEdge(5,9);
		a.addEdge(5,7);
		a.addEdge(7,9);
		System.out.println("a has cycle: " + Cycles.hasCycle(a));
		// expected output
		// a has cycle: false

		DiGraph b = new DiGraph(9);
		b.addEdge(0,5);
		b.addEdge(0,8);
		b.addEdge(1,3);
		b.addEdge(1,7);
		b.addEdge(3,7);
		b.addEdge(2,4);
		b.addEdge(4,6);
		b.addEdge(6,2);
		System.out.println("b has cycle: " + Cycles.hasCycle(b));
		// expected output
		// b has cycle: true

		DiGraph c = new DiGraph(11);
		c.addEdge(0,9);
		c.addEdge(0,5);
		c.addEdge(1,3);
		c.addEdge(1,8);
		c.addEdge(2,6);
		c.addEdge(2,4);
		c.addEdge(5,7);
		c.addEdge(7,9);
		c.addEdge(9,5);
		System.out.println("c has cycle: " + Cycles.hasCycle(c));
		// expected output
		// c has cycle: true
		
		UnDiGraph d = new UnDiGraph(11);
		d.addEdge(1,9);
		d.addEdge(1,5);
		d.addEdge(0,3);
		d.addEdge(0,8);
		d.addEdge(2,6);
		d.addEdge(2,4);
		d.addEdge(5,9);
		d.addEdge(5,7);
		d.addEdge(7,9);
		System.out.println("d has cyclique: " + Cycles.hasCycle(d));
		// expected output
		// d has cycle: true
		
		UnDiGraph e = new UnDiGraph(10);
		e.addEdge(0,5);
		e.addEdge(0,8);
		e.addEdge(0,4);
		e.addEdge(1,7);
		e.addEdge(3,7);
		e.addEdge(2,4);
		e.addEdge(8,3);
		e.addEdge(6,9);
		System.out.println("e has cyclique: " + Cycles.hasCycle(e));
		// expected output
		// e has cycle: false		
	}
}
