import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//to só inventando história por enquanto nao tem nada criado
public class FordFulkerson {
        private int numVertices;
    private boolean[] visited;
    private int[][] residualGraph;

    public FordFulkerson(int numVertices) {
        this.numVertices = numVertices;
        this.visited = new boolean[numVertices];
        this.residualGraph = new int[numVertices][numVertices];
    }

    public void addEdge(int source, int destination, int capacity) {
        residualGraph[source][destination] = capacity;
    }

    public int findMaxFlow(int source, int sink) {
        int maxFlow = 0;
        int[] pathFlow;

        while (bfs(source, sink)) {
            pathFlow = new int[numVertices];
            int currentFlow = dfs(source, sink, Integer.MAX_VALUE, pathFlow);
            maxFlow += currentFlow;
            updateResidualGraph(pathFlow, source, sink);
            Arrays.fill(visited, false);
        }

        return maxFlow;
    }

    private boolean bfs(int source, int sink) {
        Arrays.fill(visited, false);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int vertex = queue.poll();

            for (int neighbor = 0; neighbor < numVertices; neighbor++) {
                if (!visited[neighbor] && residualGraph[vertex][neighbor] > 0) {
                    queue.add(neighbor);
                    visited[neighbor] = true;
                }
            }
        }

        return visited[sink];
    }

    private int dfs(int vertex, int sink, int minCapacity, int[] pathFlow) {
        if (vertex == sink)
            return minCapacity;

        for (int neighbor = 0; neighbor < numVertices; neighbor++) {
            if (!visited[neighbor] && residualGraph[vertex][neighbor] > 0) {
                visited[neighbor] = true;

                int currentFlow = dfs(neighbor, sink, Math.min(minCapacity, residualGraph[vertex][neighbor]), pathFlow);

                if (currentFlow > 0) {
                    pathFlow[neighbor] = currentFlow;
                    return currentFlow;
                }
            }
        }

        return 0;
    }

    private void updateResidualGraph(int[] pathFlow, int source, int sink) {
        int vertex = sink;

        while (vertex != source) {
            int prevVertex = vertex;
            vertex = pathFlow[vertex];
            residualGraph[vertex][prevVertex] -= pathFlow[sink];
            residualGraph[prevVertex][vertex] += pathFlow[sink];
        }
    }
}
