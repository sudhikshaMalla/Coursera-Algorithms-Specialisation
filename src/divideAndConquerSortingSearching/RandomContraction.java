package src.divideAndConquerSortingSearching;

import java.util.Random;

public class RandomContraction {

    public static class Edge
    {
        int src, dest;
        Edge(int src, int dest){
            this.src = src;
            this.dest = dest;
        }
    }

    public static class Graph
    {
        int vertices, edges;
        Edge edge[];
        Graph(int vertices, int edges){
            this.vertices = vertices;
            this.edges = edges;
            this.edge = new Edge[edges];
        }
    }

    public static class subset
    {
        int parent;
        int rank;
        subset(int parent, int rank){
            this.parent = parent;
            this.rank = rank;
        }
    }

    public static int kargerMinCut(Graph graph)
    {
        int V = graph.vertices, E = graph.edges;
        Edge edge[] = graph.edge;

        subset subsets[] = new subset[V];

        for (int v = 0; v < V; ++v)
        {
            subsets[v] = new subset(v,0);
        }

        int vertices = V;

        while (vertices > 2)
        {
            Random random = new Random();
            int i = random.nextInt(vertices);

            int subset1 = find(subsets, edge[i].src);
            int subset2 = find(subsets, edge[i].dest);

            if (subset1 == subset2){
                continue;
            }
            else
            {
                vertices--;
                Union(subsets, subset1, subset2);
            }
        }

        int cutedges = 0;
        for (int i=0; i<E; i++)
        {
            int subset1 = find(subsets, edge[i].src);
            int subset2 = find(subsets, edge[i].dest);
            if (subset1 != subset2){
                cutedges++;
            }
        }
        return cutedges;
    }

    public static int find(subset subsets[], int i)
    {
        if (subsets[i].parent != i){
            subsets[i].parent = find(subsets, subsets[i].parent);
        }
        return subsets[i].parent;
    }

    public static void Union(subset subsets[], int x, int y)
    {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (subsets[xroot].rank < subsets[yroot].rank){
            subsets[xroot].parent = yroot;
        }
        else{
            if (subsets[xroot].rank > subsets[yroot].rank){
                subsets[yroot].parent = xroot;
            }
            else
            {
                subsets[yroot].parent = xroot;
                subsets[xroot].rank++;
            }
        }
    }

    public static void main (String[] args) {

        int V = 4;
        int E = 5;

        Graph graph = new Graph(V, E);

        graph.edge[0] = new Edge(0,1);

        graph.edge[1] = new Edge(0,2);

        graph.edge[2] = new Edge(0,3);

        graph.edge[3] = new Edge(1,3);

        graph.edge[4] = new Edge(2,3);

        System.out.println("No.of Cuts : "+kargerMinCut(graph));
    }

}