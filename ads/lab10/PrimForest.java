package ads.lab10;

import java.util.*;
import ads.graph.*;
import ads.heap.*;

/**
 * A class for the Prim algorithm for unconnected graph
 */
public class PrimForest {

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

		// the minimum-heap of weighted edges
		BinaryHeap<WeightedEdge> minHeap = new BinaryHeap<WeightedEdge>(G.nbEdges(),c);

		// known[u] == true <==> u is known
		boolean known[] = new boolean[G.nbVertices()];

		// TO COMPLETE

		for(int k = 0; k < G.nbVertices(); k++) {
			// init knowns - everybody is unknown
			//for (int i = 0; i < known.length; i++) {
			//	known[i] = false;
			//}
			known[k] = true; // "random" node becomes known
			for (Edge e : G.incidents(k)) {
				minHeap.add(new WeightedEdge(e, G.weight(e)));
			}

			// System.out.println(" is heap empty? " + minHeap.isEmpty());
			while (!minHeap.isEmpty()) {

				WeightedEdge currentEdge = minHeap.deleteExtreme();
				/*known[currentEdge.destination()] = true;
				mst.add(currentEdge);

				minHeap = new BinaryHeap<WeightedEdge>(G.nbEdges(), c);
				for (int i = 0; i < G.nbVertices(); i++) {
					if (known[i]) {
						for (int j : G.adjacents(i)) {
							if (!known[j]) {
								minHeap.add(new WeightedEdge(i, j, G.weight(i, j)));
							}
						}
					}
				}*/
				if(!known[currentEdge.destination()]){
					known[currentEdge.destination()] = true;
					mst.add(currentEdge);
					for(Edge e: G.incidents(currentEdge.destination())) {
						minHeap.add(new WeightedEdge(e, G.weight(e)));
					}
				}
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
