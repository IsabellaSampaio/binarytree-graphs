public class Aresta<T> {
    private Vertice<T> origem, destino;
    private float peso;


    public Aresta(Vertice<T> verticeOrigem, Vertice<T> verticeDestino, float peso) {
        origem = verticeOrigem;
        destino = verticeDestino;
        this.peso = peso;
    }

    public Vertice<T> getDestino() {
        return destino;
    }

    public Vertice<T> getOrigem() {
        return origem;
    }

    public float getPeso() {
        return peso;
    }


}
