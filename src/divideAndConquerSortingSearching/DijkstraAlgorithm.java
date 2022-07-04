package src.divideAndConquerSortingSearching;

public class DijkstraAlgorithm {

    private static int graph[][];
    private static int min_distance[];
    private static boolean[] visited;

    public static void dijkstra(int source) {
        int vertices = graph.length;
        min_distance = new int[vertices];
        visited = new boolean[vertices];

        for(int i=0; i<vertices; i++) {
            min_distance[i] = Integer.MAX_VALUE;
        }

        min_distance[source] = 0;

        int i=1;

        while(i<vertices) {
            int startNode = getNodeWithMinDistance();
            visited[startNode] = true;

            for(int j=0; j<vertices; j++) {
                if(!visited[j] && graph[startNode][j]!=0 && min_distance[startNode]!=Integer.MAX_VALUE && min_distance[startNode]+graph[source][j] < min_distance[j]) {
                    min_distance[j] = min_distance[startNode] + graph[startNode][j];
                }
            }
            ++i;
        }
    }

    public static int getNodeWithMinDistance() {
        int min_dist = Integer.MAX_VALUE;
        int min_index = -1;

        for(int i=0; i<graph.length; i++) {
            if(visited[i]==false && min_distance[i]<=min_dist) {
                min_dist = min_distance[i];
                min_index = i;
            }
        }

        return min_index;
    }

    public static void main(String[] args) {
        graph = new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
                { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
                { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
                { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
                { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
                { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
                { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
                { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
                { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };

        dijkstra(0);

        for(int i=0; i< graph.length; i++) {
            System.out.println("Node " + i + " - " + min_distance[i]);
        }
    }

}