import java.util.ArrayList;
import java.util.Comparator;


/**
 * ÁrvoreBinária
 * @authors Ludmila Dias e Isabella Sampaio
 */

public class ArvoreBinaria<T> {
    protected Node<T> raiz;
    protected Comparator<T> comparador;


    public ArvoreBinaria(Comparator<T> comp){
        comparador = comp;
    }
// ----------------------------------------

    public void setNovoNo(T novo){
        Node<T> novoNo= new Node<T>(novo);
        if(this.raiz==null)
            this.raiz = novoNo;
        else
            setNovoNo(this.raiz, novoNo);
    }

    private void setNovoNo(Node<T> r, Node<T> novo){
        if(comparador.compare(novo.getValor(), r.getValor())<0){
            if(r.getFilho_esq()==null){
                r.setFilho_esq(novo);
            }else{
                setNovoNo(r.getFilho_esq(), novo);
            }
        }
        else if(comparador.compare(novo.getValor(), r.getValor())>0){
            if(r.getFilho_dir()==null){
                r.setFilho_dir(novo);
            }else{
                setNovoNo(r.getFilho_dir(), novo);
            }
        }else{
            System.out.println("repetido: "+novo.getValor());
        }

    }
// -----------------------------------------


public int getAltura(){
    if(this.raiz!=null){
        return getAltura(this.raiz);
    }
    else{
        return 0;
    }

}

private int getAltura(Node<T> r){

    int h_dir = 0;
    int h_esq = 0;

    if(r.getFilho_esq()!=null){
        h_esq=getAltura(r.getFilho_esq());
    }
        
    if(r.getFilho_dir()!= null){
        h_dir = getAltura(r.getFilho_dir());
    }

    if(h_esq > h_dir){
        return h_esq +1;
    }
    else{
        return h_dir+1;
    }
    
    
}
// ----------------------------------------------
    public ArrayList<T> caminhaEmOrdem(){
        ArrayList<T> lista = new ArrayList<T>();
        if(this.raiz!=null){
            caminhaEmOrdemRec(this.raiz, lista);
            lista.add(this.raiz.getValor());
           
        }

        return lista;
    }

    private ArrayList<T> caminhaEmOrdemRec(Node<T> r, ArrayList<T> lista){
        if(r.getFilho_esq()!=null){
            caminhaEmOrdemRec(r.getFilho_esq(), lista);
            lista.add(r.getFilho_esq().getValor()); 
        }
            
        if(r.getFilho_dir()!= null){
            caminhaEmOrdemRec(r.getFilho_dir(), lista);
            lista.add(r.getFilho_dir().getValor());
        }
        
        return lista;
        
    }
// ----------------------------------------------

public ArrayList<T> caminhaEmNivel(){
    ArrayList<T> lista = new ArrayList<T>();
    ArrayList<Node<T>> fila = new ArrayList<Node<T>>();
    if(this.raiz !=null){
        fila.add(this.raiz);
        lista = caminhaEmNivelRec(fila, lista);
    }

    return lista;
}

private ArrayList<T>  caminhaEmNivelRec(ArrayList<Node<T>> fila, ArrayList<T> lista){
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
        lista = caminhaEmNivelRec(fila, lista);
    }
    
    return lista;
}

// ----------------------------------------------

    public int quantElem(){
        if(this.raiz==null){
            return 0;
        }
        else{
            return quantElem(this.raiz);
        }
    }

    private int quantElem(Node<T> r){
        int quant = 0;
        if (r.getFilho_esq()!=null){
            quant+= quantElem(r.getFilho_esq());
        }
        if (r.getFilho_dir()!=null){
            quant+=quantElem(r.getFilho_dir());
        }
        return quant+1;
    }

// -------------------------------------------------------------
    
    public Node<T> getRaiz() {
        return raiz;
    }



// -------------------------------------------------------------

public void retornaValorArv(){
    if(this.raiz != null){
        retornaValorArv(this.raiz);
    }
}

private void retornaValorArv(Node<T> raiz) {
    if (raiz != null) {
        System.out.println(raiz.getValor());
        retornaValorArv(raiz.getFilho_esq());
        retornaValorArv(raiz.getFilho_dir());
    }
} 

public void remove(T valor) {
    Node<T> novoNo = new Node<T>(valor);
    this.raiz = removeRec(this.raiz, novoNo);
}

private Node<T> removeRec(Node<T> no, Node<T> valor) {
    if (no == null) {
        return null;
    } else if (comparador.compare(valor.getValor(), no.getValor()) < 0) {
        no.setFilho_esq(removeRec(no.getFilho_esq(), valor));
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

} // fecha classe



