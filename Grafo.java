// LUDMILA DIAS E ISABELLA SAMPAIO

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

    public Grafo<T> gerarArvoreGM(){
        Grafo<T> arvoreGM = new Grafo<>(comparador, quantVertices);
        ArrayList<float[]> filaArestas = new ArrayList<>();
        float aresta[]; //[peso, vertice 1, vertice 2]
        int cont = 0; // quantidade de arestas inseridas
        ArrayList<Vertice<T>> filaVertices = new ArrayList<>();
        ArrayList<Vertice<T>> verticesVisitados = new ArrayList<>();
        Vertice<T> v;
        int i = 0;
        int j = 0;
        int d, o = 0;

        for(Vertice<T> vert: vertices){
            filaVertices.add(vert);
        }
        
        v = filaVertices.get(0);

        do{
            verticesVisitados.add(v);
            filaVertices.remove(v); 
            v = verticesVisitados.get(verticesVisitados.size()-1);
            i = v.getIndex();
            for(Vertice<T> adj:filaVertices){
                j = adj.getIndex();
                if(pesos[i][j]>0 && ((pesos[i][j]==pesos[j][i] && i<j) || pesos[i][j]<pesos[j][i])){
                    aresta = new float[3];
                    aresta[0] = pesos[i][j];
                    aresta[1] =  i;
                    aresta[2] = j;
                    filaArestas.add(aresta);
                } else if(pesos[j][i]>0 && pesos[j][i]<pesos[i][j]){
                    aresta = new float[3];
                    aresta[0] = pesos[j][i];
                    aresta[1] =  j;
                    aresta[2] = i;
                }
            }
            aresta = menorAresta(filaArestas);
            o = Math.round(aresta[1]);
            d = Math.round(aresta[2]);
            arvoreGM.adicionarAresta(verticePorIndex(o),verticePorIndex(d), aresta[0]);
            System.out.println("--------------------------\n" + verticePorIndex(Math.round(aresta[1])).toString() + verticePorIndex(Math.round(aresta[2])).toString() + "\n Peso: " + aresta[0] );
            filaArestas.remove(aresta);
            if(o!= i){
                System.out.println(o+" e diferente de " + i);
                v = vertices.get(o);
            } else if(d!=i){
                System.out.println(d+" e diferente de " + i);
                v = vertices.get(d);
            }


        }while(filaVertices.size()>0);
        return arvoreGM;
    }

    private float[] menorAresta(ArrayList<float[]> fila){
        float menorPeso[] = fila.get(0);
        for(float[] aresta : fila){
            System.out.println(aresta[0]);
            if(aresta[0]<menorPeso[0]){
                menorPeso= aresta;
            }
        }
        
        return menorPeso;
    }


}


