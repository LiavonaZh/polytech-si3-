package ads.lab9;

import java.util.*;
import ads.graph.*;

/**
 * A class to compute the topological sorting of an acyclic digraph
 */
public class TopSort {

	/**
	 * returns a list of the verticies of G so that they
	 * appear in topological order (from first to last)
	 */
	public static List<Integer> sort(DiGraph G) {
		List<Integer> list = new ArrayList<>();
		Queue<Integer> queue = new ArrayDeque<>();
		int [] indegree = new int[G.nbVertices()];

		// fill indegree
		for (int i = 0; i<G.nbVertices();i++) {
			indegree[i] = G.inDegree(i);
			if (G.inDegree(i) == 0) {
				queue.add(i);
			}
		}

		// loop to sort
		while(!queue.isEmpty()) {
			int currentVertex = queue.poll();
			list.add(currentVertex);
			for (int adjacent : G.adjacents(currentVertex)) {
				// find adjacent in indegree and decrease degree
				// if it = 0 : add to queue
				if(--indegree[adjacent] == 0) {
					queue.add(adjacent);
				}
			}
		}
		return list;
	}
	
	/**
	 * a programm for testing
	 */
	public static void main(String[] args) {
		DiGraph a = new DiGraph(10);
		a.addEdge(0,9);
		a.addEdge(0,5);
		a.addEdge(1,3);
		a.addEdge(1,8);
		a.addEdge(2,6);
		a.addEdge(2,4);
		a.addEdge(5,9);
		a.addEdge(5,7);
		a.addEdge(7,9);
		a.addEdge(9,1);
		a.addEdge(3,2);
		a.addEdge(8,4);
		a.addEdge(7,6);
		System.out.println("topological sorting of a: " + sort(a));
		// expected output (not unique)
		// topological sorting of a: [0, 5, 7, 9, 1, 3, 8, 2, 6, 4]

		DiGraph b = new DiGraph(9);
		b.addEdge(0,5);
		b.addEdge(0,8);
		b.addEdge(1,3);
		b.addEdge(1,7);
		b.addEdge(3,7);
		b.addEdge(2,4);
		b.addEdge(4,6);
		b.addEdge(5,8);
		b.addEdge(1,0);
		b.addEdge(8,2);
		b.addEdge(7,6);
		b.addEdge(5,3);
		System.out.println("topological sorting of b: " + sort(b));
		// expected output (not unique)
		// topological sorting of b: [1, 0, 5, 8, 3, 2, 7, 4, 6]

		DiGraph c = new DiGraph(9);
		c.addEdge(6,7);
		c.addEdge(7,4);
		c.addEdge(4,8);
		c.addEdge(8,2);
		c.addEdge(2,1);
		c.addEdge(1,0);
		c.addEdge(0,5);
		c.addEdge(5,3);
		c.addEdge(6,4);
		c.addEdge(7,2);
		c.addEdge(8,3);
		c.addEdge(1,5);
		System.out.println("topological sorting of c: " + sort(c));
		// expected output (unique)
		// topological sorting of c: [6, 7, 4, 8, 2, 1, 0, 5, 3]
	}
}
