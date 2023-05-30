import java.util.ArrayList;
import java.util.Comparator;

public class Grafo<T> {
    private final ArrayList<Aresta<T>> arestas;  // Lista de arestas do grafo
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
        arestas = new ArrayList<Aresta<T>>();
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
        Aresta<T> novaAresta;
        v_origem = obterVertice(origem);
        if (v_origem == null) {
            v_origem = adicionarVertice(origem);
        }
        v_destino = obterVertice(destino);
        if (v_destino == null) {
            v_destino = adicionarVertice(destino);
        }
        if (peso != 0) {
            novaAresta = new Aresta<T>(v_origem, v_destino, peso);
            arestas.add(novaAresta);
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
}