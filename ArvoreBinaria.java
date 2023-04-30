import java.util.ArrayList;
import java.util.Comparator;


/**
 * ÁrvoreBinária
 * @authors Ludmila Dias e Isabella Sampaio
 */

public class ArvoreBinaria<T> {
    protected Node<T> raiz;
    protected Comparator<T> comparador;
    //adicionar altura no nó

    public ArvoreBinaria(Comparator<T> comp){
        comparador = comp;
    }

// ------------------------------------------------------
/**
 * Retorna a Raiz da Árvore
 */

    public Node<T> getRaiz() {
        return raiz;
    }

// -----------------------------------------------------
/**
 * Adicionar novo nó a árvore
 */

    public void addNovoNo(T novo){ 
        Node<T> novoNo= new Node<T>(novo);
        if(this.raiz==null)
            this.raiz = novoNo;
        else
            this.raiz = addNovoNo(this.raiz, novoNo);
    }


// O valor do novo nó é recebido como parâmetro na função publica e usado para criação de um novo nó.
// Se a ávore não possuir raiz ele é definido com raiz, se não, é chamado o método recursivo addNovoNO
// enviando como parâmetro a raiz da árvore e o novo nó que se deseja inserir.


    protected Node<T> addNovoNo(Node<T> r, Node<T> novo){
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

        return r;
    }

// Usando o comparador que a árvore possui, o método percorre a árvore guiando o nó até o seu lugar.
// O método compara o novo no com a raiz para decidir com qual filho será chamado o método novamente.
// E assim recursivamente até que chegue a um nó que não possua o filho para chamar o método. Então,
// o nó novo será definido como filho do ultimo nó pai visitado.


 

// --------------------------------------------------
/**
 * Retorna a Altura da Árvore
 */

    public int calcAltura(){
        if(this.raiz!=null){
            return calcAltura(this.raiz);
        }
        else{
            return 0;
        }

    }

    

// Se a raiz for null o método retorna 0, caso contrário o método recursivo calcAltura é chamado enviando a raiz como parâmetro.


    protected int calcAltura(Node<T> r){

        int h_dir = 0;
        int h_esq = 0;

        if(r==null)
            return -1;
        else{
            h_esq = calcAltura(r.getFilho_esq());
            h_dir = calcAltura(r.getFilho_dir());
        }

        if(h_esq > h_dir){
            return h_esq+1;
        }
        else{
            return h_dir+1;
        }


        

// O método vai recursivamente calculando a quantidade de nós filhos de cada lado e retornando o maior lado e somando.


    }
// -------------------------------------------------------------------------
/**
 * Retorna uma lista com os itens da árvore em ordem crescente
 */
    public ArrayList<T> caminhaEmOrdem(){
        ArrayList<T> lista = new ArrayList<T>();
        if(this.raiz!=null){
            caminhaEmOrdem(this.raiz, lista);
           
        }

        return lista;
    }


// Se a raiz por null o método retorna uma lista vazia, caso contrário o método recursivo caminhaEmOrdem é chamado enviando a raiz e uma lista como parâmetro.


    private void caminhaEmOrdem(Node<T> r, ArrayList<T> lista){
        if(r.getFilho_esq()!=null){
            caminhaEmOrdem(r.getFilho_esq(), lista);

        }
        lista.add(r.getValor());
            
        if(r.getFilho_dir()!= null){
            caminhaEmOrdem(r.getFilho_dir(), lista);
        }
        
    }


//  O método recursivo verifica primeiro se o filho esquerdo é null, se não, ele chama a função caminhaEmOrdem enviando como parâmetro o filho esq como r e a lista.
//  Quando o filho esq for null ou quando a condição finalizar, o valor de r é adicionado a lista. Após isso a mesma condição ocorre envolvendo o filho direito.


// ---------------------------------------------------------------------

/**
 * Retorna uma lista com os itens da árvore organizados por nível.
 */
public ArrayList<T> caminhaEmNivel(){
    ArrayList<T> lista = new ArrayList<T>();
    ArrayList<Node<T>> fila = new ArrayList<Node<T>>();
    if(this.raiz !=null){
        fila.add(this.raiz);
        caminhaEmNivel(fila, lista);
    }

    return lista;
}


//Se a raiz for null o método retorna uma lista vazia, caso contrário a raiz é adicionada a fila e o método recursivo caminhaEmNivel é chamado enviando uma fila e uma lista como parâmetro.


private void caminhaEmNivel(ArrayList<Node<T>> fila, ArrayList<T> lista){
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
        caminhaEmNivel(fila, lista);
    }
    
}

//É atribuido a variavel node r o nó da fila que está na posição 0, e colocado também o seu valor na lista.
//Após isso, é verificado se o filho esq é null, se não, ele é add na fila, e o mesmo acontece para o filho direito.
//Depois, como já foi analisado o primeiro nó da fila, ele é removido, e é chamado o método novamente com o próximo item da fila.
//E assim continua recursivamente até que a fila esteja vazia.


/// -----------------------------------------------------------------------------
/**
 * Retorna a quantidade de elementos da árvore.
 */

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


//Se a raiz for null o método retorna 0, se não, a função é chamada recursivamente somando todos os valores retornados.
//Sempre que um nó não for null ele chama a função recursivamente primeiro para seu filho esquerdo e depois para seu filho direito.
//É parecido com o calculo da altura, porém não há comparação da quantidade de nós em cada lado.



// --------------------------------------------------------
/**
 * Remove nó de acordo com valor recebido como parâmetro.
 */

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


//O método faz, de maneira recursiva, uma busca pelo elemento do tipo T (genérico) e caso este elemento esteja na árvore então o remove. 
//Caso o nó tenha filhos, o então é definido um nó substituto como o menor valor na subárvore à direita do nó a ser removido e o remove recursivamente, atribuindo seu valor ao nó original.
//No final, a função retorna a raiz da árvore atualizada. 

// -----------------------------------------------------------------------------------------------
/**
 * Busca nó recebido como parâmetro e retorna a quantidade de nós percorridos até encontra-lo.
 */

    public T busca(T valor){
        Node<T> r = this.raiz;
        int contador = 0;

        while(r != null){
            
            if(comparador.compare(r.getValor(), valor) == 0){
                System.out.println("Quantidade de elementos percorridos na árvore binária: " + contador);
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

    

// O método realiza uma busca iterativa por um valor do tipo T (genérico). A função percorre a árvore comparando o valor com a raiz e os valores dos nós filhos se deslocando para a subárvore correta. 
//Se o valor for encontrado, a função retorna o nó contendo o valor e exibe a quantidade de nós percorridos. Caso contrário, se a árvore estiver vazia ou o valor não estiver presente na árvore, a função retorna null. 

// ----------------------------------------------------

/**
 * Retorna menor valor da árvore.
 */

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

/**
 * Retorna maior valor da árvore.
 */
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


//Para encontrar o maior valor o método percorre a árvore até seu nó mais a direita
//e para encontrar o menor a árvore é percorrida até seu nó mais a esquerda.

public void printIndented(Node<T> node, String indent, boolean last) {
    if(raiz != null){
        System.out.print(indent);
        if (last) {
            System.out.print("└─");
            indent += "  ";
        } else {
            System.out.print("├─");
            indent += "│ ";
        }
        System.out.println(node.getValor());

        if (node.getFilho_dir() != null) {
            printIndented(node.getFilho_dir(), indent, node.getFilho_esq() == null);
        }

        if (node.getFilho_esq() != null) {
            printIndented(node.getFilho_esq(), indent, true);
        }
    }else{
        System.out.println("Árvore está vazia!");
    }
     }

} // fecha classe
