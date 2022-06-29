package src.divideAndConquerSortingSearching;

import java.util.ArrayList;

public class DepthFirstSearch {

    static boolean[] visited;
    static Graph graph;

    public static void traverse(Graph g, int startNode) {

        int vertices = g.getVertices();
        visited = new boolean[vertices];
        graph = g;

        dfs(startNode);
    }

    public static void dfs(int currentNode) {
        visited[currentNode] = true;

        System.out.print(currentNode + " ");

        ArrayList<Integer> adjacent_nodes = graph.getEdges()[currentNode];

        for(int i=0; i< adjacent_nodes.size(); i++) {
            if(!visited[adjacent_nodes.get(i)]) {
                dfs(adjacent_nodes.get(i));
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

        System.out.println("Depth First Search Traversal from node " + start_node + ": ");

        traverse(graph, start_node);
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