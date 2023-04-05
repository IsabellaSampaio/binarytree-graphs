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
                r.setFilho_Esq(novo);
            }else{
                setNovoNo(r.getFilho_esq(), novo);
            }
        }
        if(comparador.compare(novo.getValor(), r.getValor())>0){
            if(r.getFilho_dir()==null){
                r.setFilho_dir(novo);
            }else{
                setNovoNo(r.getFilho_dir(), novo);
            }
        }

    }
// -----------------------------------------
    public ArrayList<T> caminhaEmOrdem(){
        ArrayList<T> lista = new ArrayList<T>();
        if(this.raiz!=null){
            lista = caminhaEmOrdemRec(this.raiz, lista);
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

// -----------------------------------------
public ArrayList<T> getListaOrdenada(){
    ArrayList<T> lista = new ArrayList<T>();
    if(this.raiz!=null){
        lista = getListaOrdenada(this.raiz, lista);
    }

    return lista;
}

private ArrayList<T> getListaOrdenada(Node<T> r, ArrayList<T> lista){
    if(r.getFilho_esq()!=null){
        lista = getListaOrdenada(r.getFilho_esq(), lista);
    }
    
    lista.add(r.getValor());

    if(r.getFilho_dir()!= null){

        lista = getListaOrdenada(r.getFilho_dir(), lista);
        
    }
    
    return lista;
    
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

// -----------------------------------------

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

// ---------------------------------------------------------------------

    public int busca(T valor){
        Node<T> r = this.raiz;
        int contador = 0;

        while(r != null){
            
            if(comparador.compare(r.getValor(), valor) == 0){
                return contador;
            }
            
            contador+=1;
            if(comparador.compare(r.getValor(), valor) < 0){
                    r = r.getFilho_dir();
            }

            else if(comparador.compare(r.getValor(), valor) > 0){
                    r = r.getFilho_esq();
            }


        }
        
        return -1;
    }


// ----------------------------------------------------------------------

    //retorna o menor valor da árvore 
    public T minVal(){
        while(this.raiz.getFilho_esq() != null){
            this.raiz = this.raiz.getFilho_esq();
        }

        return this.raiz.getValor();
    }

    //retorna o maior valor da árvore
    //problema: retornando o mesmo valor para maior e menor valor no caso de árvore de matrícula
    public T maxVal(){
        while(this.raiz.getFilho_dir() != null){
            this.raiz = this.raiz.getFilho_dir();
        }

        return this.raiz.getValor();
    }


    public void maiorMenor(){
        Node<T> menorVal = this.raiz;
        Node<T> maiorVal = this.raiz;

        if (this.raiz == null) {
            System.out.println("Árvore não existe, raiz nula");

        }else {
            while(menorVal.getFilho_esq() != null){
                menorVal = menorVal.getFilho_esq();
            }
            
            while(maiorVal.getFilho_dir() != null){
                maiorVal = maiorVal.getFilho_dir();
            }
            
            System.out.println("O menor valor na árvore é: " + menorVal.getValor());
            System.out.println("O maior valor na árvore é: " + maiorVal.getValor());
        }
    }

} // fecha classe
