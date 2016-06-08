package ads.graph;

import java.util.*;

/**
 * A class for weighted undirected graphs
 */
public class WeightedDiGraph extends DiGraph {

	// to map the edges to their weight
	private Map<Edge,Double> weights;
	
	/**
	 * builds an undirected weighted graph with n vertices
	 */	
	public WeightedDiGraph(int n) {
		super(n);
		weights = new HashMap<Edge,Double>();
		inDegree = new int[n];
		for ( int i = 0; i < n; i++ )
			inDegree[i] = 0;
        }
	
	/**
	 * adds the edge (u,v) of weight w to the graph
	 */
	public void addEdge(int u, int v, double w) {
		if ( ! adjacencyList.get(u).contains(v) ) {
			adjacencyList.get(u).add(v);
			nbEdges++;
			weights.put(new Edge(u,v),w);
            inDegree[v]++;
		}
	}

	/**
	 * removes the weighted edge (u,v) to the graph
	 */    
    public void removeEdge(int u, int v){
		if ( adjacencyList.get(u).remove(v) != null ) {
			nbEdges--;
            inDegree[v]--;
		}
    }
	
	/**
	 * adds the weighted edge e to the graph
	 */
	public void addEdge(WeightedEdge e) {
		addEdge(e.origin(),e.destination(),e.weight());
	}

	/**
	 * returns the weight of the edge (x,y)
	 * or 0 if the edge (x,y) is not in the graph
	 */
	public double weight(int x, int y) {
		if( ! adjacencyList.get(x).contains(y))
            return 0.0;
        return weights.get(new Edge(x, y));
	}
	
	/**
	 * returns the weight of the edge e
	 * or 0 if the edge e is not in the graph
	 */
	public double weight(Edge e) {
		return weight(e.origin(),e.destination());
	}

	public void reverse() {
		WeightedDiGraph res;

	}
}