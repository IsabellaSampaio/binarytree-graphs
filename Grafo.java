// LUDMILA DIAS E ISABELLA SAMPAIO
// Implementação do algoritmo de PRIM na geração de árvore Geradora Minima

import java.util.ArrayList;
import java.util.Comparator;

public class Grafo<T> {
    private final ArrayList<Vertice<T>> vertices;  // Lista de vértices do grafo
    private final float[][] pesos;  // Matriz de pesos das arestas do grafo
    int quantVertices;  // Quantidade de vértices do grafo
    protected Comparator<T> comparador;  // Comparador utilizado para comparar valores dos vértices

    /**
     * Construtor da classe Grafo.
     *
     * @param comp          O comparador utilizado para comparar valores dos vértices.
     * @param quantVertices A quantidade de vértices do grafo.
     */
    public Grafo(Comparator<T> comp, int quantVertices) {
        comparador = comp;
        vertices = new ArrayList<Vertice<T>>();
        pesos = new float[quantVertices][quantVertices];
        this.quantVertices = quantVertices;
    }

    /**
     * Adiciona um vértice ao grafo.
     *
     * @param valor O valor a ser armazenado no vértice.
     * @return O vértice adicionado.
     */
    public Vertice<T> adicionarVertice(T valor) {
        Vertice<T> novo = new Vertice<T>(valor);
        vertices.add(novo);
        novo.setIndex(vertices.size() - 1);
        return novo;
    }

    /**
     * Obtém um vértice com base em um valor.
     *
     * @param valor O valor do vértice a ser obtido.
     * @return O vértice correspondente ao valor, ou null se não for encontrado.
     */
    private Vertice<T> obterVertice(T valor) {
        Vertice<T> v;
        for (int i = 0; i < vertices.size(); i++) {
            v = vertices.get(i);
            if (v.getValor().equals(valor)) return v;
        }
        return null;
    }

    /**
     * Obtém o índice de um vértice com base em um valor.
     *
     * @param valor O valor do vértice.
     * @return O índice do vértice, ou -1 se não for encontrado.
     */
    private int obterIndex(T valor) {
        Vertice<T> v;
        for (int i = 0; i < vertices.size(); i++) {
            v = vertices.get(i);
            if (v.getValor().equals(valor)) return i;
        }
        return -1;
    }

    /**
     * Obtém o valor de um vértice com base em um índice.
     *
     * @param index O índice do vértice.
     * @return O valor do vértice correspondente ao índice.
     */
    public T verticePorIndex(int index) {
        return (vertices.get(index)).getValor();
    }

    /**
     * Obtém um vértice com base em um valor.
     *
     * @param valor O valor do vértice a ser obtido.
     * @return O vértice correspondente ao valor, ou null se não for encontrado.
     */
    public Vertice<T> verticePorValor(T valor) {
        for (Vertice<T> v : vertices) {
            if (comparador.compare(v.getValor(), valor) == 0) return v;
        }
        return null;
    }

    /**
     * Adiciona uma aresta ao grafo.
     *
     * @param origem  O valor do vértice de origem da aresta.
     * @param destino O valor do vértice de destino da aresta.
     * @param peso    O peso da aresta.
     */

    public void adicionarAresta(T origem, T destino, float peso) {
        Vertice<T> v_origem, v_destino;
        v_origem = obterVertice(origem);
        if (v_origem == null) {
            v_origem = adicionarVertice(origem);
        }
        v_destino = obterVertice(destino);
        if (v_destino == null) {
            v_destino = adicionarVertice(destino);
        }

        pesos[v_origem.getIndex()][v_destino.getIndex()] = peso;
    }

    /**
     * Obtém os vértices vizinhos de um vértice com base em um valor.
     *
     * @param valor O valor do vértice.
     * @return O vértice correspondente ao valor.
     */
    public Vertice<T> obterVerVizinhos(T valor) {
        Vertice<T> v = verticePorValor(valor);
        if (v != null) {
            for (int i = 0; i < vertices.size(); i++) {
                Vertice<T> vAux = vertices.get(i);
                if(pesos[v.getIndex()][i]>0 && i!=v.getIndex()){
                    System.out.println("-------------------------\nPonto destino da aresta: \n" + vAux.getValor().toString()+"\nPeso:" + pesos[v.getIndex()][i] + "\n");
                }
                if(pesos[i][v.getIndex()]>0 && i!=v.getIndex()){
                    System.out.println("--------------------------\nPonto origem da aresta: \n" + vAux.getValor().toString()+"\nPeso:" + pesos[i][v.getIndex()] + "\n");
                }
            }
            return v;
        } else {
            System.out.println("Valor inserido inválido.");
            return null;
        }
    }

    /**
     * Realiza a busca em largura (BFS) no grafo a partir de um vértice de origem.
     *
     * @param id O índice do vértice de origem.
     */
    public void buscaEmLargura(int id) {
        boolean[] marcados = new boolean[this.quantVertices];
        int atual = id - 1;
        ArrayList<Integer> fila = new ArrayList<Integer>();

        fila.add(atual);
        while (fila.size() > 0) {
            atual = fila.get(0);
            fila.remove(0);
            marcados[atual] = true;
            System.out.println(this.vertices.get(atual).getValor());
            for (int dest = 0; dest < this.quantVertices; dest++) {
                if (pesos[atual][dest] > 0)
                    if (!marcados[dest] && !fila.contains(dest))
                        fila.add(dest);
            }
        }
    }

    /**
     * Gera uma árvore geradora minima do grafo.
     */
    public Grafo<T> gerarArvoreGM(){
        Grafo<T> arvoreGM = new Grafo<>(comparador, quantVertices); // Árvore geradora minima
        ArrayList<float[]> filaArestas = new ArrayList<>();// Fila de arestas
        float aresta[]; // [peso, vertice origem, vertice destino]
        int cont = 0; // quantidade de arestas inseridas
        ArrayList<Vertice<T>> filaVertices = new ArrayList<>();// fila de vértices ainda não visitados
        ArrayList<Vertice<T>> verticesVisitados = new ArrayList<>();// Lista de vertices visitados
        Vertice<T> v; // Variavel para armazenar o vertice que será análisado
        int i, j = 0; // variaveis para navegar pelos indexes
        Vertice<T> d, o; // variaveis auxiliares para salvar destino e origem da aresta
        float soma = 0; // Variável para a soma de todos os pesos das arestas adicionadas.
        
        // Adiciona os vertices do grafo na fila de vertices e na árvore.
        for(Vertice<T> vert: vertices){
            filaVertices.add(vert);
            arvoreGM.adicionarVertice(vert.getValor());
        }
        
        // Adiciona o vertice da posição 0 na lista de visitados, e inicia o loop visitante esse vértice e suas arestas.
        v = filaVertices.get(0);
        verticesVisitados.add(v);
        filaVertices.remove(v); 

        /*
         Esse loop pega o ultimo valor adicionado na lista de vertices visitados e adiciona as suas arestas ligadas aos vertices ainda não acessados
         na lista de arestas.Logo após chama o método menorAresta() que retorna a aresta de menor peso, analisando também os vértices da aresta para que não
         seja retornado uma aresta que liga a um vertice que já foi visitado, gerando um ciclo.
         Como estamos utilizando matriz de adjacências o acesso é feito através dos indices, e é comparado o peso das arestas nos dois sentidos para caso ocorra de
         ter arestas com mesmos vertices - destino e origem - mas valores diferentes.
        */
        do{
            v = verticesVisitados.get(verticesVisitados.size()-1);
            i = v.getIndex();
            for(Vertice<T> adj:filaVertices){
                j = adj.getIndex();
                if(pesos[i][j]>0 && pesos[i][j]<=pesos[j][i]){
                    aresta = new float[3];
                    aresta[0] = pesos[i][j];
                    aresta[1] =  i;
                    aresta[2] = j;
                    filaArestas.add(aresta);
                } else if((pesos[i][j]==0 && pesos[j][i]>0) || (pesos[i][j]>0 && pesos[j][i]<pesos[i][j])){
                    aresta = new float[3];
                    aresta[0] = pesos[j][i];
                    aresta[1] =  j;
                    aresta[2] = i;
                    filaArestas.add(aresta);
                }
            }
            aresta = menorAresta(filaArestas, filaVertices);
            o = vertices.get(Math.round(aresta[1]));
            d = vertices.get(Math.round(aresta[2]));
            // Nova aresta é adicionada na Arvore Geradora Minima
            arvoreGM.adicionarAresta(o.getValor(),d.getValor(), aresta[0]);
            cont+=1; // quantidade de arestas aumenta +1
            soma+=aresta[0]; // soma o valor da aresta a soma total

            // Print da aresta adicionada e seus vértices
            System.out.println("--------------------------\nOrigem:\n" + verticePorIndex(Math.round(aresta[1])).toString() + "\nDestino:\n"+verticePorIndex(Math.round(aresta[2])).toString() + "\n Peso: " + aresta[0] );
            
            /*A aresta adicionada na árvore é removida da fila e o vértice destino da aresta é removido da fila e adicionado em vertices visitados e na variável v, 
            para que ele seja o próximo vertice análisado na próxima iteração do loop.*/
            filaArestas.remove(aresta); 
            if(filaVertices.contains(o)){
                v = o;
            } else if(filaVertices.contains(d)){
                v = d;
            }
            verticesVisitados.add(v);
            filaVertices.remove(v); 

        }while(cont<quantVertices-1); // condição de repetição do loop 

        System.out.println("Peso total da arvore: "+soma);
        return arvoreGM;
    }

    /**
     * Retorna a aresta de menor peso da fila e que possui algum vértice que ainda não foi visitado.
     *
     * @param fila Fila de arestas.
     * @param filaVertices Fila de vertices ainda não visitados.
     */
    private float[] menorAresta(ArrayList<float[]> fila, ArrayList<Vertice<T>> filaVertices){
        float menorPeso[] = null; // variável para armazenar aresta de menor peso
        float aresta[];
        Vertice<T> v1, v2;
        // Loop que acessa cada vértice da fila
        for(int i=0; i<fila.size(); i++){
            aresta = fila.get(i); 
            v1 = vertices.get(Math.round(aresta[1]));
            v2 = vertices.get(Math.round(aresta[2]));
            // Verificador se algum dos vértices da aresta ainda não foi visitado, para evitar loops.
            if((filaVertices.contains(v1) || filaVertices.contains(v2))){
                // Se for a primeira aresta a ser análisada o valor é atribuido a variavel.
                if(menorPeso == null){
                    menorPeso = aresta;
                }
                // Se não uma comparação é feita para se encontrar a menor
                else if(aresta[0]<menorPeso[0]){
                    menorPeso= aresta;
                }
            }
        }
        return menorPeso;
    }

    public ArrayList<Vertice<T>> getVertices() {
        return vertices;
    }

    public float[][] getPesos() {
        return pesos;
    }

    public int getQuantVertices() {
        return quantVertices;
    }

}


