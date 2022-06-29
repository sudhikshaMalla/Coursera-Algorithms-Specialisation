package src.divideAndConquerSortingSearching;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class TopologicalSort {

    public static void sort(Graph graph) {

        Deque<Integer> queue = new ArrayDeque<>();
        int[] in_degrees = graph.getIn_degrees();
        ArrayList<Integer> edges[] = graph.getEdges();

        for(int i=0; i< graph.getVertices(); i++) {
            if(in_degrees[i]==0) {
                queue.offer(i);
            }
        }

        while(queue.size()!=0) {
            int vertex = queue.poll();
            System.out.print(vertex + " ");
            for(int i=0; i<edges[vertex].size(); i++) {
                int adjacent_vertex = edges[vertex].get(i);
                in_degrees[adjacent_vertex]--;
                if(in_degrees[adjacent_vertex] == 0) {
                    queue.offer(adjacent_vertex);
                }
            }
        }

    }

    public static void main(String[] args) {
        Graph graph = new Graph(6);

        graph.addEdge(5,2);
        graph.addEdge(5,0);
        graph.addEdge(4,0);
        graph.addEdge(4,1);
        graph.addEdge(2,3);
        graph.addEdge(3,1);

        System.out.println("Topological Sort : ");

        sort(graph);
    }
}

class Graph {
    private int vertices;
    private ArrayList<Integer> edges[];
    private int[] in_degrees;

    public Graph(int vertices) {
        this.vertices = vertices;
        edges = new ArrayList[vertices];
        in_degrees = new int[vertices];
        for(int i=0; i<vertices; i++) {
            edges[i] = new ArrayList<>();
        }
    }

    public void addEdge(int start_node, int end_node) {
        edges[start_node].add(end_node);
        in_degrees[end_node]++;
    }

    public int getVertices() {
        return vertices;
    }

    public ArrayList<Integer>[] getEdges() {
        return edges;
    }

    public int[] getIn_degrees() {
        return in_degrees;
    }
}