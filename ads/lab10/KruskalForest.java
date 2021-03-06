package ads.lab10;

import java.util.*;
import ads.graph.*;
import ads.heap.*;
import ads.lab7.DisjointSets;

/**
 * A class for the Kruskal algorithm for unconnected graph
 */
public class KruskalForest {
	
	/**
	 * adds all the weighted edges of graph G to the minimum heap minHeap
	 */
	private static void fillHeap(BinaryHeap<WeightedEdge> minHeap, WeightedUnDiGraph G) throws FullHeapException {
		for ( int u = 0; u < G.nbVertices(); u++ ) {
			for ( Integer a : G.adjacents(u) ) {
				if ( u < a )
					minHeap.add(new WeightedEdge(u,a,G.weight(u, a)));
			}
		}
	}
	
	/**
	 * returns the set all edges of an MST of the graph G
	 * the graph G may net be connected
	 */
	public static Set<Edge> mst(WeightedUnDiGraph G) throws FullHeapException, EmptyHeapException {
		
		Set<Edge> mst = new HashSet<Edge>(); // the edges of the MST
		
		// to make a minimum-heap of weighted edges		
		Comparator<WeightedEdge> c = new Comparator<WeightedEdge>() {
			public int compare(WeightedEdge e1, WeightedEdge e2) {
				return e2.compareTo(e1);
			}
		};
		
		// a minimum-heap	
		BinaryHeap<WeightedEdge> minHeap = new BinaryHeap<WeightedEdge>(G.nbEdges(),c);
		// fill the minimum-heap with all the weighted edges from the graph G	
		fillHeap(minHeap,G);
		// disjoint sets of all the vertices of the graph G
		DisjointSets ds = new DisjointSets(G.nbVertices());

		// TO COMPLETE
		while(!minHeap.isEmpty()) {
			WeightedEdge currentEdge = minHeap.deleteExtreme();
			int x = currentEdge.origin();
			int y = currentEdge.destination();
			if(ds.find(x) != ds.find(y)) {
				ds.union(ds.find(x), ds.find(y));
				mst.add(currentEdge);
			}
		}
		
		return mst;
	}
	
	/**
	 * for testing
	 */
	public static void main(String args[]) throws FullHeapException, EmptyHeapException {
		WeightedUnDiGraph G = new WeightedUnDiGraph(9);
		G.addEdge(0,2,4);
		G.addEdge(0,5,8);
		G.addEdge(2,5,11);
		
		G.addEdge(1,4,2);
		G.addEdge(1,7,6);
		G.addEdge(3,1,1);
		G.addEdge(3,4,4);
		G.addEdge(3,7,2);
		
		G.addEdge(6,8,9);

		Set<Edge> mst = mst(G);
		
		for ( Edge e : mst )
			System.out.print(e + " ");
		System.out.println();
	}
	// expected output (the edges could show up in a different order)
	//
	// (0, 5) (3, 7) (1, 3) (1, 4) (6, 8) (0, 2)
}
