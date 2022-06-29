package src.divideAndConquerSortingSearching;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class BreadthFirstSearch {

    public static void bfs(Graph graph, int startNode) {

        int vertices = graph.getVertices();
        boolean[] visited = new boolean[vertices];
        Deque<Integer> queue = new ArrayDeque<>();

        visited[startNode] = true;
        queue.offer(startNode);

        while(queue.size() > 0) {
            int current_element = queue.pollFirst();

            System.out.print(current_element + " ");
            ArrayList<Integer> adjacent_elements = graph.getEdges()[current_element];

            for(int i=0; i<adjacent_elements.size(); i++) {
                int element = adjacent_elements.get(i);
                if(!visited[element]) {
                    visited[element] = true;
                    queue.add(element);
                }
            }
        }

    }

    public static void main(String[] args) {
        Graph graph = new Graph(4);

        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(1,2);
        graph.addEdge(2,0);
        graph.addEdge(2,3);
        graph.addEdge(3,3);

        int start_node = 2;

        System.out.println("Breadth First Search Traversal from node " + start_node + ": ");

        bfs(graph, start_node);
    }

}

class Graph {
    private int vertices;
    private ArrayList<Integer> edges[];

    public Graph(int vertices) {
        this.vertices = vertices;
        edges = new ArrayList[vertices];
        for(int i=0; i<vertices; i++) {
            edges[i] = new ArrayList<>();
        }
    }

    public void addEdge(int start_node, int end_node) {
        edges[start_node].add(end_node);
    }

    public int getVertices() {
        return vertices;
    }

    public ArrayList<Integer>[] getEdges() {
        return edges;
    }
}