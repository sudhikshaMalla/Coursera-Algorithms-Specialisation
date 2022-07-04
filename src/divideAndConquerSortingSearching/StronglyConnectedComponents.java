package src.divideAndConquerSortingSearching;

import java.util.ArrayList;

public class StronglyConnectedComponents {

    static boolean[] visited;
    static Graph components;
    static boolean[] rev_visited;
    static ArrayList<Integer> node_order;

    public static void traverse() {

        int vertices = components.getVertices();
        visited = new boolean[vertices];
        rev_visited = new boolean[vertices];
        node_order = new ArrayList<>();
        int strong_components = 0;

        for(int i=vertices-1; i>=0; i--) {
            if(!rev_visited[i]) {
                dfs_rev(i);
            }
        }

        for(int i=0; i<node_order.size(); i++) {
            if(!visited[i]) {
                dfs(node_order.get(i));
                ++strong_components;
                System.out.println();
            }
        }
        System.out.println("No.of Strong Components : " + strong_components);
    }

    public static void dfs_rev(int currentNode) {
        rev_visited[currentNode] = true;

        node_order.add(currentNode);

        ArrayList<Integer> adjacent_nodes = components.getReverse_edges()[currentNode];

        for(int i=0; i< adjacent_nodes.size(); i++) {
            if(!rev_visited[adjacent_nodes.get(i)]) {
                dfs_rev(adjacent_nodes.get(i));
            }
        }
    }

    public static void dfs(int currentNode) {
        visited[currentNode] = true;

        System.out.print(currentNode + " ");

        ArrayList<Integer> adjacent_nodes = components.getEdges()[currentNode];

        for(int i=0; i< adjacent_nodes.size(); i++) {
            if(!visited[adjacent_nodes.get(i)]) {
                dfs(adjacent_nodes.get(i));
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);

        graph.addEdge(1,0);
        graph.addEdge(0,2);
        graph.addEdge(2,1);
        graph.addEdge(0,3);
        graph.addEdge(3,4);

        components = graph;

        System.out.println("Strongly Connected Components : ");

        traverse();

    }
}

class Graph {
    private int vertices;
    private ArrayList<Integer> edges[];
    private ArrayList<Integer> reverse_edges[];


    public Graph(int vertices) {
        this.vertices = vertices;
        edges = new ArrayList[vertices];
        reverse_edges = new ArrayList[vertices];
        for(int i=0; i<vertices; i++) {
            edges[i] = new ArrayList<>();
            reverse_edges[i] = new ArrayList<>();
        }
    }

    public void addEdge(int start_node, int end_node) {
        edges[start_node].add(end_node);
        reverse_edges[end_node].add(start_node);
    }

    public int getVertices() {
        return vertices;
    }

    public ArrayList<Integer>[] getEdges() {
        return edges;
    }

    public ArrayList<Integer>[] getReverse_edges() {
        return reverse_edges;
    }
}