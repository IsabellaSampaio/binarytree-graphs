import java.util.ArrayList;

public class Grafo<T> {
    ArrayList<Aresta> arestas;
    ArrayList<Vertice<T>> vertices;
    Matriz pesos;

    public Grafo(){}

    public Vertice<T> adicionarVertice(T valor){
        pesos = new Matriz();
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


    public void adicionarAresta(T origem, T destino, float peso){
        Vertice<T> verticeOrigem, verticeDestino;
        Aresta novaAresta;
        verticeOrigem = obterVertice(origem);
        if(verticeOrigem == null){
            verticeOrigem=adicionarVertice(origem);
        }
        verticeDestino=obterVertice(destino);
        if(verticeDestino==null){
            verticeDestino=adicionarVertice(destino);
        }
        novaAresta=new Aresta(verticeOrigem, verticeDestino, peso);
        arestas.add(novaAresta);
    }

    public void setPesos(Matriz pesos) {
        this.pesos = pesos;
    }

}
