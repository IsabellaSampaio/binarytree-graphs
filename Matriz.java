import java.util.ArrayList;

// index do elemento: coluna - index de elemento da lista, linha - index da listas entre as listas
// Ou seja o primeiro index é a linha e o segundo a coluna assim: elem = matriz[linha][coluna]

public class Matriz {
    ArrayList<ArrayList<Float>> linhas;

    public Matriz(){}

    public void setNovaLinha(ArrayList<Float> linha){
        linhas.add(linha);
    }
}
