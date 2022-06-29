package src.divideAndConquerSortingSearching;

import java.util.ArrayList;

public class ConnectedComponents {

    static boolean[] visited;
    static Graph graph;

    public static int countComponents(Graph g) {

        int vertices = g.getVertices();
        visited = new boolean[vertices];
        graph = g;

        int component_count = 0;

        for(int i=0; i< visited.length; i++) {
            if(!visited[i]) {
                ++component_count;
                dfs(i);
                System.out.println();
            }
        }

        return component_count;
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
        Graph graph = new Graph(5);

        graph.addEdge(1,0);
        graph.addEdge(2,3);
        graph.addEdge(3,4);

        int component_count = countComponents(graph);

        System.out.println("No.of connected components : " + component_count);
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
        edges[end_node].add(start_node);
    }

    public int getVertices() {
        return vertices;
    }

    public ArrayList<Integer>[] getEdges() {
        return edges;
    }
}