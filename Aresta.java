public class Aresta<T> {
    private final Vertice<T> origem;  // Vertice de origem da aresta
    private final Vertice<T> destino;  // Vertice de destino da aresta
    private final float peso;  // Peso da aresta

    public Aresta(Vertice<T> verticeOrigem, Vertice<T> verticeDestino, float peso) {
        origem = verticeOrigem;
        destino = verticeDestino;
        this.peso = peso;
    }

    /**
     * Obtém o vértice de destino da aresta.
     *
     * @return O vértice de destino da aresta.
     */
    public Vertice<T> getDestino() {
        return destino;
    }

    /**
     * Obtém o vértice de origem da aresta.
     *
     * @return O vértice de origem da aresta.
     */
    public Vertice<T> getOrigem() {
        return origem;
    }

    /**
     * Obtém o peso da aresta.
     *
     * @return O peso da aresta.
     */
    public float getPeso() {
        return peso;
    }
}
