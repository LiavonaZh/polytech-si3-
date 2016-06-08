package ads.lab11;

import ads.graph.Edge;
import ads.graph.WeightedDiGraph;
import ads.graph.WeightedEdge;
import ads.graph.WeightedUnDiGraph;
import ads.heap.*;

import java.util.Comparator;

public class Dijkstra {

    public static double [] dijkstra(WeightedUnDiGraph G, int start) throws EmptyHeapException, FullHeapException {
        boolean [] known = new boolean[G.nbVertices()];
        double [] cost = new double[G.nbVertices()];
        for(int i = 0; i < G.nbVertices(); i++) {
            known[i] = false;
            cost[i] = Integer.MAX_VALUE;
        }

        cost[start] = 0;


        // to make a minimum-heap of weighted edges
        Comparator<WeightedEdge> c = new Comparator<WeightedEdge>() {
            public int compare(WeightedEdge e1, WeightedEdge e2) {
                return e2.compareTo(e1);
            }
        };
        BinaryHeap<WeightedEdge> heap = new BinaryHeap<WeightedEdge>(G.nbEdges(),c);
        for (Edge e: G.incidents(0)) {
            heap.add(new WeightedEdge(e, G.weight(e)));
        }

        while(!heap.isEmpty()) {
            WeightedEdge currentEdge = heap.deleteExtreme();

            int origin = currentEdge.origin();
            known[origin] = true;

            for(int e : G.adjacents(origin)) {
                if(!known[e] && cost[origin] != Integer.MAX_VALUE && cost[origin] + G.weight(origin, e) < cost[e]) {
                    cost[e] = cost[origin] + G.weight(origin, e);
                }
            }

            heap = new BinaryHeap<WeightedEdge>(G.nbEdges(), c);
            for(int l = 0; l < cost.length; l++) {
                if(cost[l] != Integer.MAX_VALUE && !known[l] ) {
                    heap.add(new WeightedEdge(l, 0, cost[l]));
                }
            }
        }

        return cost;
    }

    public static double [] dijkstraDiGraph(WeightedDiGraph G, int start) throws EmptyHeapException, FullHeapException {
        boolean [] known = new boolean[G.nbVertices()];
        double [] cost = new double[G.nbVertices()];
        for(int i = 0; i < G.nbVertices(); i++) {
            known[i] = false;
            cost[i] = Integer.MAX_VALUE;
        }

        cost[start] = 0;


        // to make a minimum-heap of weighted edges
        Comparator<WeightedEdge> c = new Comparator<WeightedEdge>() {
            public int compare(WeightedEdge e1, WeightedEdge e2) {
                return e2.compareTo(e1);
            }
        };
        BinaryHeap<WeightedEdge> heap = new BinaryHeap<WeightedEdge>(G.nbEdges(),c);
        for (Edge e: G.incidents(0)) {
            heap.add(new WeightedEdge(e, G.weight(e)));
        }

        while(!heap.isEmpty()) {
            WeightedEdge currentEdge = heap.deleteExtreme();

            int origin = currentEdge.origin();
            known[origin] = true;

            for(int e : G.adjacents(origin)) {
                if(!known[e] && cost[origin] != Integer.MAX_VALUE && cost[origin] + G.weight(origin, e) < cost[e]) {
                    cost[e] = cost[origin] + G.weight(origin, e);
                }
            }

            heap = new BinaryHeap<WeightedEdge>(G.nbEdges(), c);
            for(int l = 0; l < cost.length; l++) {
                if(cost[l] != Integer.MAX_VALUE && !known[l] ) {
                    heap.add(new WeightedEdge(l, 0, cost[l]));
                }
            }
        }

        return cost;
    }

    public static void main(String[] args) throws EmptyHeapException, FullHeapException {
        /*WeightedUnDiGraph G = new WeightedUnDiGraph(9);
        G.addEdge(0,2,4);
        G.addEdge(0,5,8);
        G.addEdge(1,4,2);
        G.addEdge(1,5,7);
        G.addEdge(1,7,6);
        G.addEdge(2,4,8);
        G.addEdge(2,5,11);
        G.addEdge(3,4,4);
        G.addEdge(3,6,10);
        G.addEdge(3,7,2);
        G.addEdge(3,8,14);
        G.addEdge(4,8,7);
        G.addEdge(5,7,1);
        G.addEdge(6,8,9);*/
        WeightedDiGraph g = new WeightedDiGraph(12);
        g.addEdge(0, 1, 2);
        g.addEdge(0, 4, 13);
        g.addEdge(0, 10, 7);
        g.addEdge(0, 11, 10);
        g.addEdge(1, 6, 1);
        g.addEdge(2, 4, 1);
        g.addEdge(2, 7, 1);
        g.addEdge(2, 9, 2);
        g.addEdge(3, 5, 8);
        g.addEdge(3, 7, 4);
        g.addEdge(4, 9, 5);
        g.addEdge(6, 3, 5);
        g.addEdge(6, 7, 2);
        g.addEdge(6, 11, 3);
        g.addEdge(7, 5, 10);
        g.addEdge(7, 8, 4);
        g.addEdge(7, 6, 7);
        g.addEdge(8, 5, 5);
        g.addEdge(9, 8, 9);
        g.addEdge(9, 5, 1);
        g.addEdge(10, 4, 8);
        g.addEdge(10, 2, 3);
        g.addEdge(10, 11, 5);
        g.addEdge(11, 7, 12);

        double [] cost = dijkstraDiGraph(g, 0);

        for(int i = 0; i<cost.length; i++) {
            System.out.println("e: " + i + ", cost= " + cost[i]);
        }

    }
}
