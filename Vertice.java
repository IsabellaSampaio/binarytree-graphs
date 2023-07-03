public class Vertice<T> {
    private T valor;  // Valor armazenado no vértice
    private int index;  // Índice do vértice
    private float dist;
    private T pred;
    private boolean visitado = false;

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    public float getDist() {
        return dist;
    }

    public void setDist(float dist) {
        this.dist = dist;
    }

    public T getPred() {
        return pred;
    }

    public void setPred(T pred) {
        this.pred = pred;
    }

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

    public int getIntValor() {
        if (valor instanceof Integer) {
            return (int) valor;
        } else {
            // Handle other cases accordingly
            return 0;
        }
    }
}
