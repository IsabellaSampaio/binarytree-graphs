import java.util.ArrayList;
import java.util.Comparator;

public class Grafo<T> {
    private ArrayList<Aresta<T>> arestas;
    private ArrayList<Vertice<T>> vertices;
    protected Comparator<T> comparador;

    public Grafo(Comparator<T> comp) {
        comparador = comp;
        arestas = new ArrayList<Aresta<T>>();
        vertices = new ArrayList<Vertice<T>>();
    }

    public Vertice<T> adicionarVertice(T valor){
        Vertice<T> novo = new Vertice<T>(valor);
        vertices.add(novo);
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

    

    public T verticePorIndex(int index){
        return (vertices.get(index)).getValor();
    }


    public void adicionarAresta(T origem, T destino, float peso){
        Vertice<T> verticeOrigem, verticeDestino;
        Aresta<T> novaAresta;
        verticeOrigem = obterVertice(origem);
        if(verticeOrigem == null){
            verticeOrigem=adicionarVertice(origem);
        }
        verticeDestino=obterVertice(destino);
        if(verticeDestino==null){
            verticeDestino=adicionarVertice(destino);
        }
        novaAresta=new Aresta<T>(verticeOrigem, verticeDestino, peso);
        arestas.add(novaAresta);
    }

    public void obterVerVizinhos(T valor){
        for(Aresta<T> aresta:arestas){
            T vo = aresta.getOrigem().getValor();
            T vd = aresta.getOrigem().getValor();
            if(comparador.compare(vo, valor)!=0 && comparador.compare(vd, valor)==0){
                System.out.println(vo.toString());
            }
        }
    }

}