import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//to só inventando história por enquanto nao tem nada criado, código generico q eu peguei do github pra tentar entender o algoritmo
public class FordFulkerson {
	
	private int quant_vertices;
	private int[][] matrizAdjacencia; 
	private Queue<Integer> fila; 
	private boolean[] visitados;
	private int[] caminho; 
	private int[][] grafoResidual;
	private int inicio;
	private int destino;

	public FordFulkerson(int quant, int matriz[][], int inicio, int destino){
		this.quant_vertices = quant;
		this.matrizAdjacencia = matriz;
		this.grafoResidual = new int[quant][quant];
		this.inicio = inicio;
		this.destino = destino;
		this.caminho = new int[quant];
		this.visitados = new boolean[quant];
		this.fila = new LinkedList<Integer>();
	}
	
	//Executa o algoritmo e retorna o fluxo máximo
	public int run(){
		
		int fluxoMaximo = 0;
		
		//Faz uma cópia da matriz do grafo no grafo residual
        for (int v1 = 0; v1 < this.quant_vertices; v1++){
        	
            for (int v2 = 0; v2 < this.quant_vertices; v2++){
            	
                grafoResidual[v1][v2] = matrizAdjacencia[v1][v2];
                
            }
        }
        
        //Busca um caminho até que todos os caminhos sejam percorridos
        while (buscaCaminho())
        {
            int fluxo = Integer.MAX_VALUE; //Seta um valor suficiente grande para iniciar a variável
            int i;
            
            //Percorre o caminho e salva a aresta de menor capacidade para definir o valor do fluxo
            for (int v = this.destino; v != this.inicio; v = this.caminho[v])
            {
                i = this.caminho[v];
                if(this.grafoResidual[i][v] < fluxo){
                	fluxo = this.grafoResidual[i][v];
                }

            }
            //Atualiza os valores no grafo residual
            for (int v = this.destino; v != this.inicio; v = caminho[v])
            {
                i = caminho[v];
                grafoResidual[i][v] -= fluxo;
                grafoResidual[v][i] += fluxo;
            }
            fluxoMaximo += fluxo;	
        }
        
		return fluxoMaximo;
		
	}
	
	//Faz uma busca em largura no grafo (representado pela matriz de adjacência)
	public boolean buscaCaminho(){
        boolean existeCaminho = false;
        
        //"reseto" as variáveis
        for(int v = 0; v < this.quant_vertices; v++){
            caminho[v] = -1;
            visitados[v] = false;
        }
 
        fila.add(this.inicio); //Adiciono o vértice inicial na fila (FIFO)
        //this.caminho[this.inicio] = -1;
        visitados[this.inicio] = true;
 
        while (!fila.isEmpty()){ //Enquanto a fila não estiver vazia...
        	
            int vertice = fila.remove(); //tira o primeiro vértice adicionado
            
            for(int i = 0; i < this.quant_vertices; i++){
            	//Se existir uma ligação entre os vértices, a capacidade for maior que zero e ainda não foi visitado
            	if (this.grafoResidual[vertice][i] > 0 &&  !this.visitados[i]){
                    this.caminho[i] = vertice; //Adiciono ele no vetor que guarda o caminho
                    fila.add(i); //Adiciono o vértice no final da fila
                    this.visitados[i] = true; //Marco ele como visitado
                }
            	
            }

        }
        if(this.visitados[this.destino]){ //Verifica se um caminho até o destino foi formado
            existeCaminho = true;
        }
        
        return existeCaminho;
        
	}

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


    public float fordFulkerson(T origem, T destino) {
        int v_origem = obterIndex(origem);
        int v_destino = obterIndex(destino);
        float[][] grafoResidual = new float[quant_vertices][quant_vertices];

        for (int i = 0; i < quant_vertices; i++) {
            for (int j = 0; j < quant_vertices; j++) {
                grafoResidual[i][j] = matrizAdjacencia[i][j];
            }
        }

        float fluxoMaximo = 0;
        int[] caminho = new int[quant_vertices];

        while (buscaEmLargura(grafoResidual, v_origem, v_destino, caminho)) {
            float fluxoCaminho = Float.MAX_VALUE;
            for (int v = v_destino; v != v_origem; v = caminho[v]) {
                int u = caminho[v];
                fluxoCaminho = Math.min(fluxoCaminho, grafoResidual[u][v]);
            }

            for (int v = v_destino; v != v_origem; v = caminho[v]) {
                int u = caminho[v];
                grafoResidual[u][v] -= fluxoCaminho;
                grafoResidual[v][u] += fluxoCaminho;
            }

            fluxoMaximo += fluxoCaminho;
        }

        return fluxoMaximo;
    }

    private boolean buscaEmLargura(float[][] grafoResidual, int origem, int destino, int[] caminho) {
        boolean[] visitados = new boolean[quant_vertices];
        for (int i = 0; i < quant_vertices; i++) {
            visitados[i] = false;
        }

        Queue<Integer> fila = new LinkedList<>();
        fila.add(origem);
        visitados[origem] = true;
        caminho[origem] = -1;

        while (!fila.isEmpty()) {
            int u = fila.poll();

            for (int v = 0; v < quant_vertices; v++) {
                if (!visitados[v] && grafoResidual[u][v] > 0) {
                    fila.add(v);
                    caminho[v] = u;
                    visitados[v] = true;
                }
            }
        }

        return visitados[destino];
    }
}

