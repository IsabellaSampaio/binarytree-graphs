import java.util.Comparator;

public class ArvoreAVL<T> extends ArvoreBinaria<T>{
    public ArvoreAVL(Comparator<T> comp){
        super(comp);
    }



    private void calcfb(Node<T> r){
        int h_dir = 0;
        int h_esq = 0;

        if(r.getFilho_dir()!=null){
            h_dir = calcAltura(r.getFilho_dir());
        }
        if(r.getFilho_esq()!=null){
            h_esq = calcAltura(r.getFilho_esq());
        }
            r.setfb(h_dir-h_esq);
    }


    protected void addNovoNo(Node<T> r, Node<T> novo){
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

            calcfb(r);
            System.out.println(r.getValor());
            System.out.println(r.getFb());
            System.out.println("------------------------");
        }



    private void rotacaoDir(){


    }

    private void rotacaoEsq(){

    }

    private void rotacaoEsqDir(){

    }

    private void rotacaoDirEsq(){

    }
}
