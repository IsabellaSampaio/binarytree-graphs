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

    public ArvoreBinaria(Comparator<T> comp){
        comparador = comp;
        this.lista = new ArrayList<T>();
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
                r.setFilhoEsq(novo);
            }else{
                setNovoNo(r.getFilho_esq(), novo);
            }
        }
        if(comparador.compare(novo.getValor(), r.getValor())>0){
            if(r.getFilho_esq()==null){
                r.setFilhoEsq(novo);
            }else{
                setNovoNo(r.getFilho_esq(), novo);
            }
        }

    }
// -----------------------------------------
    private void caminhaEmOrdem(){
        if(this.raiz!=null){
            caminhaEmOrdemRec(this.raiz);
            this.lista.add(this.raiz.getValor());
           
        }
    }

    private void caminhaEmOrdemRec(Node<T> r){
        if(r.getFilho_esq()!=null){
            caminhaEmOrdemRec(r.getFilho_esq());
            this.lista.add(r.getFilho_esq().getValor()); 
        }
            
        if(r.getFilho_dir()!= null){
            caminhaEmOrdemRec(r.getFilho_dir());
            this.lista.add(r.getFilho_dir().getValor());
        }
        
        
    }
// ----------------------------------------------

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
    
    public Node<T> getRaiz() {
        return raiz;
    }



// -------------------------------------------------------------

public ArrayList<T> getElementsList(){
    caminhaEmOrdem();
    return lista;
    }
}



