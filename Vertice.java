public class Vertice<T> {
    private T valor;  // Valor armazenado no vértice
    private int index;  // Índice do vértice


    public Vertice(T valor) {
        this.valor = valor;
    }

    /**
     * Obtém o valor armazenado no vértice.
     *
     * @return O valor armazenado no vértice.
     */
    public T getValor() {
        return valor;
    }

    /**
     * Define o valor a ser armazenado no vértice.
     *
     * @param valor O valor a ser armazenado no vértice.
     */
    public void setValor(T valor) {
        this.valor = valor;
    }

    /**
     * Define o índice do vértice.
     *
     * @param index O índice do vértice.
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Obtém o índice do vértice.
     *
     * @return O índice do vértice.
     */
    public int getIndex() {
        return index;
    }
}
