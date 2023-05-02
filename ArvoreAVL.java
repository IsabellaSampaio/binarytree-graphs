import java.util.Comparator;

/**
 * ÁrvoreBináriaAVL
 * @authors Ludmila Dias e Isabella Sampaio
 */

public class ArvoreAVL<T> extends ArvoreBinaria<T>{
    public ArvoreAVL(Comparator<T> comp){
        super(comp);
    }

// ------------------------------------------------------

/**
 * Calcula o fator de balanceamento da árvore
 */

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

//O método calcfb calcula e define o fator de balanceamento (fb) de um nó em uma árvore AVL, que é a diferença entre a altura do filho direito e a altura do filho esquerdo. Ele verifica se o nó possui filhos direito e esquerdo e, se sim, obtém suas alturas e subtrai para obter o valor do fb. O resultado é definido como o valor do fb para o nó atual.

// ------------------------------------------------------

/**
 * Adicionar novo nó a árvore
 */

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
        return r;
    }

//Semelhante ao método de addNovoNo da árvore binária, com uma diferença apenas que a inserção aqui é feita obedecendo o cálculo de balanceamento (já que a árvore AVL é balanceada), a partir da linha 57. 

//Se o fb for maior que 1, verifica se o filho direito de r tem um fb positivo ou negativo e executa a rotação apropriada (rotação simples à esquerda ou rotação  direira e esquerda). Se o fb for menor que -1, verifica se o filho esquerdo de r tem um fb negativo ou positivo e executa a rotação apropriada (rotação simples à direita ou rotação esquerda e direita). O método retorna o nó r, que pode ser atualizado com a nova raiz da subárvore após a rotação.

// ------------------------------------------------------

/**
 * Faz rotação a direita na árvore binária
 */

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

//O método rotacaoDir é utilizado em árvores AVL para realizar uma rotação simples à direita em um nó específico r. A rotação consiste em pegar o filho esquerdo de r (chamado de f) e torná-lo a nova raiz da subárvore, enquanto r se torna o filho direito de f. Além disso, o método atualiza as alturas dos nós envolvidos e recalcula o fator de balanceamento para manter a propriedade da árvore AVL. O método retorna o nó f, que é a nova raiz da subárvore.

// ------------------------------------------------------

/**
 * Faz rotação a esquerda na árvore binária
 */

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

//O método rotacaoEsq é utilizado em árvores AVL para realizar uma rotação simples à esquerda em um nó específico r. A rotação consiste em pegar o filho direito de r (chamado de f) e torná-lo a nova raiz da subárvore, enquanto r se torna o filho esquerdo de f. Além disso, o método atualiza as alturas dos nós envolvidos e recalcula o fator de balanceamento para manter a propriedade da árvore AVL. O método retorna o nó f, que é a nova raiz da subárvore.

// ------------------------------------------------------

/**
 * Faz rotação esquerda e depois a direita na árvore binária
 */

    private Node<T> rotacaoEsqDir(Node<T> r){
        r.setFilho_Esq(rotacaoEsq(r.getFilho_esq()));
        return rotacaoDir(r);
    }

//O método rotacaoEsqDir é utilizado em árvores AVL para realizar uma rotação dupla à esquerda-direita em um nó específico r. Essa rotação consiste em fazer uma rotação simples à esquerda no filho esquerdo de r e depois uma rotação simples à direita em r. O método utiliza as funções rotacaoEsq e rotacaoDir e retorna o novo nó raiz da subárvore.

// ------------------------------------------------------

/**
 * Faz rotação a direita e depois a esquerda na árvore binária
 */

    private Node<T> rotacaoDirEsq(Node<T> r){
        r.setFilho_dir(rotacaoDir(r.getFilho_dir()));
        return rotacaoEsq(r);
    }

//No método rotacaoDirEsq é realizada uma rotação simples à direita em um nó específico r seguindo-se de uma rotação simples à esquerda em um nó filho do nó r. Além disso, o método atualiza as alturas dos nós envolvidos e recalcula o fator de balanceamento para manter a propriedade da árvore AVL.  O método retorna o nó resultante após as duas rotações, que é a nova raiz da subárvore.

// ------------------------------------------------------

/**
 * Busca nó recebido como parâmetro e retorna a quantidade de nós percorridos até encontra-lo. 
 */
    
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

//Semelhante ao método de busca da árvore binária com uma diferença apenas no print (linha 156) que imprime da tela nesse caso a quantidade de elementos na árvore AVL ao invés dos elementos na árvore binária 

}
