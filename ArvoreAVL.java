import java.util.Comparator;

public class ArvoreAVL<T> extends ArvoreBinaria<T>{
    public ArvoreAVL(Comparator<T> comp){
        super(comp);
    }

    private void calcfb(Node<T> r){
        int h_dir = -1;
        int h_esq = -1;

        
        if(r.getFilho_dir()!=null){
            h_dir = r.getFilho_dir().getAltura();
        }
        if(r.getFilho_esq()!=null){
            h_esq = r.getFilho_esq().getAltura();
        }
            
        r.setfb(h_dir-h_esq);
    }


    @Override
    protected Node<T> addNovoNo(Node<T> r, Node<T> novo){
        if(comparador.compare(novo.getValor(), r.getValor())<0){
            if(r.getFilho_esq()==null)
                r.setFilho_Esq(novo);
            else
                r.setFilho_Esq(addNovoNo(r.getFilho_esq(), novo));
        }
        else if(comparador.compare(novo.getValor(), r.getValor())>0){
            if(r.getFilho_dir()==null)
                r.setFilho_dir(novo);
            else
                r.setFilho_dir(addNovoNo(r.getFilho_dir(), novo));
        }
        else{
            return r;
        }
        r.setAltura(maiorAltura(r.getFilho_dir(), r.getFilho_esq()));
        calcfb(r);
        //System.out.println(r.getFb());
        if(r.getFb()>1){
            if(r.getFilho_dir().getFb()>0)
                r = rotacaoEsq(r);
            else
                r = rotacaoDirEsq(r);
            
        }
        else if(r.getFb()<-1){
            if(r.getFilho_esq().getFb()<0)
                r=rotacaoDir(r);
            else
                r=rotacaoEsqDir(r);
        }
        //System.out.println("Altura: " + r.getAltura());
        return r;
    }




    private Node<T> rotacaoDir(Node<T> r){
        Node<T> f = r.getFilho_esq();
        r.setFilho_Esq(f.getFilho_dir());
        f.setFilho_dir(r);

        r.setAltura(maiorAltura(r.getFilho_dir(), r.getFilho_esq()));
        f.setAltura(maiorAltura(f.getFilho_dir(), f.getFilho_esq()));
        calcfb(r);
        calcfb(f);


        return f;

    }

    private Node<T> rotacaoEsq(Node<T> r){
        Node<T> f = r.getFilho_dir();
        r.setFilho_dir(f.getFilho_esq());
        f.setFilho_Esq(r);

        
        
        r.setAltura(maiorAltura(r.getFilho_dir(), r.getFilho_esq()));
        f.setAltura(maiorAltura(f.getFilho_dir(), f.getFilho_esq()));
        calcfb(r);
        calcfb(f);

        return f;
    }

    private Node<T> rotacaoEsqDir(Node<T> r){
        r.setFilho_Esq(rotacaoEsq(r.getFilho_esq()));
        return rotacaoDir(r);
    }

    private Node<T> rotacaoDirEsq(Node<T> r){
        r.setFilho_dir(rotacaoDir(r.getFilho_dir()));
        return rotacaoEsq(r);
    }
    
    
    @Override
    public T busca(T valor){
        Node<T> r = this.raiz;
        int contador = 0;

        while(r != null){
            
            if(comparador.compare(r.getValor(), valor) == 0){
                System.out.println("Quantidade de elementos percorridos na árvore AVL: " + contador);
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
}
