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
    
        menu.lerArq(); 
        while(opcao != 5){
            menu.menu();
            opcao = menu.lerOpcaoMenu();
            menu.escolheMenu(opcao);       
        }     
        
        
        // for(int j = 0; j < menu.getAlunos().size(); j++){
        //     alunos.add(menu.getAlunos().get(j));
        // }



        // arvNome.retornaValorArv();
        // System.out.println(arvMat.minVal());
        // System.out.println(arvMat.maxVal());

        
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