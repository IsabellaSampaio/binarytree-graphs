import java.util.ArrayList;
import java.util.Comparator;


/**
 * ÁrvoreBinária
 * @authors Ludmila Dias e Isabella Sampaio
 */

public class ArvoreBinaria<T> {
    protected Node<T> raiz;
    protected Comparator<T> comparador;
    protected ArrayList<T> lista;

    public Node<T> getRaiz() {
        return raiz;
    }

    public ArvoreBinaria(Comparator<T> comp){
        comparador = comp;
    }

// -------- ADICIONAR NOVO NÓ --------------------------------
    public void addNovoNo(T novo){ 
        Node<T> novoNo= new Node<T>(novo);
        if(this.raiz==null)
            this.raiz = novoNo;
        else
            addNovoNo(this.raiz, novoNo);
    }

    private void addNovoNo(Node<T> r, Node<T> novo){
        if(comparador.compare(novo.getValor(), r.getValor())<0){
            if(r.getFilho_esq()==null){
                r.setFilho_Esq(novo);
            }else{
                addNovoNo(r.getFilho_esq(), novo);
            }
        }
        if(comparador.compare(novo.getValor(), r.getValor())>0){
            if(r.getFilho_dir()==null){
                r.setFilho_dir(novo);
            }else{
                addNovoNo(r.getFilho_dir(), novo);
            }
        }

    }

// ------- RETORNA A ALTURA DA ÁRVORE ----------------------------------

    public int calcAltura(){
        if(this.raiz!=null){
            return calcAltura(this.raiz);
        }
        else{
            return 0;
        }

    }

    private int calcAltura(Node<T> r){

        int h_dir = 0;
        int h_esq = 0;

        if(r.getFilho_esq()!=null){
            h_esq = calcAltura(r.getFilho_esq());
        }

        if(r.getFilho_dir()!= null){
            h_dir = calcAltura(r.getFilho_dir());
        }

        if(h_esq > h_dir){
            return h_esq + 1;
        }
        else{
            return h_dir + 1;
        }


    }
// ----- RETORNA UMA LISTA COM OS ITENS DA ARVORE EM ORDEM CRESCENTE --------
    public ArrayList<T> caminhaEmOrdem(){
        ArrayList<T> lista = new ArrayList<T>();
        if(this.raiz!=null){
            caminhaEmOrdemRec(this.raiz, lista);
           
        }

        return lista;
    }

    private void caminhaEmOrdemRec(Node<T> r, ArrayList<T> lista){
        if(r.getFilho_esq()!=null){
            caminhaEmOrdemRec(r.getFilho_esq(), lista);

        }
        lista.add(r.getValor());
            
        if(r.getFilho_dir()!= null){
            caminhaEmOrdemRec(r.getFilho_dir(), lista);
        }
        
        
    }
// -------- RETORNA UMA LISTA COM OS ITENS DA ÁRVORE ORGANIZADOS POR NÍVEL ------------

public ArrayList<T> caminhaEmNivel(){
    lista = new ArrayList<T>();
    ArrayList<Node<T>> fila = new ArrayList<Node<T>>();
    if(this.raiz !=null){
        fila.add(this.raiz);
        caminhaEmNivelRec(fila);
    }

    return lista;
}

private void caminhaEmNivelRec(ArrayList<Node<T>> fila){
    Node<T> r = fila.get(0);
    lista.add(r.getValor());
    if(r.getFilho_esq()!=null){
        fila.add(r.getFilho_esq()); 
    }
    if(r.getFilho_dir()!= null){
        fila.add(r.getFilho_dir());
    }

    fila.remove(0);
    if(!fila.isEmpty()){
        caminhaEmNivelRec(fila);
    }
    
}

// ------------ RETORNA A QUANTIDADE DE ELEMENTOS DA ARVORE ----------------

    public int quantElem(){
        return quantElem(this.raiz);
    }

    private int quantElem(Node<T> r){
        if(r==null)
            return 0;
        else{
            return quantElem(r.getFilho_esq())+quantElem(r.getFilho_dir())+1;
        }
        
    }


// ------------ REMOVE NÓ RECEBIDO COMO PARÂMETRO -----------------------------

    public void remove(T valor) {
        Node<T> novoNo = new Node<T>(valor);
        this.raiz = removeRec(this.raiz, novoNo);
    }
    
    private Node<T> removeRec(Node<T> no, Node<T> valor) {
        if (no == null) {
            return null;
        } else if (comparador.compare(valor.getValor(), no.getValor()) < 0) {
            no.setFilho_Esq(removeRec(no.getFilho_esq(), valor));
        } else if (comparador.compare(valor.getValor(), no.getValor()) > 0) {
            no.setFilho_dir(removeRec(no.getFilho_dir(), valor));
        } else {
            if (no.getFilho_esq() == null) {
                return no.getFilho_dir();
            } else if (no.getFilho_dir() == null) {
                return no.getFilho_esq();
            } else {
                Node<T> min = no.getFilho_dir();
                while (min.getFilho_esq() != null) {
                    min = min.getFilho_esq();
                }
                no.setValor(min.getValor());
                no.setFilho_dir(removeRec(no.getFilho_dir(), min));
            }
        }
        return no;
    }

    

// ------------ BUSCA NÓ DESEJADO ----------------------


    public T busca(T valor){
        Node<T> r = this.raiz;
        int contador = 0;

        while(r != null){
            
            if(comparador.compare(r.getValor(), valor) == 0){
                System.out.println("Quantidade de elementos percorridos: " + contador);
                return r.getValor();
            }
            
            contador+=1;
            if(comparador.compare(r.getValor(), valor) < 0){
                    r = r.getFilho_dir();
            }

            else if(comparador.compare(r.getValor(), valor) > 0){
                    r = r.getFilho_esq();
            }

        }

        return null;
    }

// ------------- RETORNA MENOR E MAIOR VALOR DA ARVORE --------------------


    public T minVal(){
        T min = null;
        if(this.raiz!=null){
            min = minVal(this.raiz, min);
        }

        return min;
    }

    private T minVal(Node<T> r, T min){
        if(r.getFilho_esq() == null){
            min = r.getValor();
        }
        else{
            min = minVal(r.getFilho_esq(), min);
        }

        return min;

    }

    public T maxVal(){
        T max = null;
        if (this.raiz != null) {
            max = maxVal(this.raiz, max);
        }
        return max;
    }

    private T maxVal(Node<T> r, T max){
        if(r.getFilho_dir() == null){
            max = r.getValor();
        }
        else{
            max = maxVal(r.getFilho_dir(), max);
        }   
        
        return max;

    }


} // fecha classe
