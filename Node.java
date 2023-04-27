public class Node<T>{
    protected T valor;
    protected Node<T> filho_esq;
    protected Node<T> filho_dir;
    protected int fb; // Fator de Balanceamento

    public Node(T novoValor){
        valor=novoValor;
    }

    public void setFilho_Esq(Node<T> filho_esq){
        this.filho_esq = filho_esq;
    }

    public Node<T> getFilho_esq() {
        return filho_esq;
    }

    public void setFilho_dir(Node<T> filho_dir) {
        this.filho_dir = filho_dir;
    }

    public Node<T> getFilho_dir() {
        return filho_dir;
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor){
        this.valor = valor;
    }

    public void setfb(int fb) {
        this.fb = fb;
    }

    public int getFb() {
        return fb;
    }




}
