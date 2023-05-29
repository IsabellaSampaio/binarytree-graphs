import java.util.ArrayList;
import java.util.Comparator;

public class Grafo<T> {
    private ArrayList<Aresta<T>> arestas;
    private ArrayList<Vertice<T>> vertices;
    private float pesos[][];
    protected Comparator<T> comparador;

    public Grafo(Comparator<T> comp, int quantVertices) {
        comparador = comp;
        arestas = new ArrayList<Aresta<T>>();
        vertices = new ArrayList<Vertice<T>>();
        pesos = new float[quantVertices][quantVertices];
    }

    public Vertice<T> adicionarVertice(T valor){
        Vertice<T> novo = new Vertice<T>(valor);
        vertices.add(novo);
        novo.setIndex(vertices.size()-1);
        return novo;
    }

    private Vertice<T> obterVertice(T valor){
        Vertice<T> v;
        for(int i=0; i<vertices.size();i++){
            v=vertices.get(i);
            if(v.getValor().equals(valor))
            return v;
        }
        return null;
    }

    private int obterIndex(T valor){
        Vertice<T> v;
        for(int i=0; i<vertices.size();i++){
            v=vertices.get(i);
            if(v.getValor().equals(valor))
            return i;
        }
        return -1;
    }
    

    public T verticePorIndex(int index){
        return (vertices.get(index)).getValor();
    }

    public Vertice<T> verticePorValor(T valor){
        for(Vertice<T> v : vertices){
            if(comparador.compare(v.getValor(), valor)==0)
                return v;
        }
        return null;
    }


    public void adicionarAresta(T origem, T destino, float peso){
        Vertice<T> V_origem, V_destino;
        Aresta<T> novaAresta;
        V_origem = obterVertice(origem);
        if(V_origem==null){
            V_origem=adicionarVertice(origem);
        }
        V_destino=obterVertice(destino);
        if(V_destino==null){
            V_destino=adicionarVertice(destino);
        }
        if(peso!=0){
            novaAresta=new Aresta<T>(V_origem, V_destino, peso);
            arestas.add(novaAresta);
        }
        
        pesos[V_origem.getIndex()][V_destino.getIndex()] = peso;
    }

    public Vertice<T> obterVerVizinhos(T valor){
        Vertice<T> v = verticePorValor(valor);
        if(v!=null){
            for(int i=0; i<vertices.size(); i++){
                Vertice<T> vAux = vertices.get(i);
                if(pesos[v.getIndex()][i]>0 && i!=v.getIndex()){
                    System.out.println("-------------------------\n Ponto destino da aresta: " + vAux.getValor().toString()+"\nPeso:" + pesos[v.getIndex()][i]);
                }
                if(pesos[i][v.getIndex()]>0 && i!=v.getIndex()){
                    System.out.println("--------------------------\nPonto origem da aresta: " + vAux.getValor().toString()+"\nPeso:" + pesos[i][v.getIndex()]);
                }
            }
            return v;
        }
        else{
            System.out.println("Valor inserido invalido.");
            return null;
        }
    }

}