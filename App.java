import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
public class App {

    public static void main(String[] args) throws InterruptedException, IOException{

        //Menu 
        Scanner scan = new Scanner(System.in);
        int opcao = 0;
        Menu menu = new Menu(scan, opcao);


        // Ler arquivos e montar árvores

        ArrayList<Aluno> alunos = menu.lerArq();
        ArvoreBinaria<Aluno> arvNome = new ArvoreBinaria<Aluno>(new ComparadorPorNome());
        ArvoreBinaria<Aluno> arvMat = new ArvoreBinaria<Aluno>(new ComparadorPorMatricula());
        for(int i=0; i<alunos.size(); i++){
            arvNome.setNovoNo(alunos.get(i));
            arvMat.setNovoNo(alunos.get(i));
        }


        while(opcao != 5){
            menu.menu();
            opcao = menu.lerOpcaoMenu();
            menu.escolheMenu(opcao);             
        }       

        
        // ArrayList<Aluno> listaArvNome = arvNome.caminhaEmOrdem();

        // for(int i=0; i<listaArvNome.size(); i++){
        //     System.out.println((listaArvNome.get(i)).getNome());
        // }

        // ArrayList<Aluno> listaArvMat = arvMat.caminhaEmOrdem();
        
        // for(int i=0; i<listaArvMat.size(); i++){
        //     System.out.println((listaArvMat.get(i)).getMatricula());
        // }

        // ArrayList<Aluno> listaMatEmNivel = arvMat.caminhaEmNivel();
        
        // System.out.println("_________________________________________");
        // for(int i=0; i<listaMatEmNivel.size(); i++){
        //     System.out.println((listaMatEmNivel.get(i)).getMatricula());
        // }

    }

}