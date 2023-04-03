import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
public class App {

    public static void main(String[] args) throws InterruptedException, IOException{

        // LER ARQUIVOS
        ArrayList<Aluno> alunos = new ArrayList<Aluno>();

        //Menu 
        Scanner scan = new Scanner(System.in);
        int opcao = 0;
        Menu menu = new Menu(scan, opcao);

        while(opcao != 5){
            menu.menu();
            opcao = menu.lerOpcaoMenu();
            menu.escolheMenu(opcao);             
        }       

        // alunos.add(new Aluno("Soraia", 1014, 9));

        //comentei o resto do cod para poder testar o menu acima :D (isa)

        /* 
        try {
            File arquivo = new File("entradaBalanceada10000000.txt");
            Scanner scanner = new Scanner(arquivo);
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] aluno = linha.split(";");
                int tam = aluno.length;
                if(tam>1){
                    alunos.add(new Aluno(aluno[1],Integer.parseInt(aluno[0]), Integer.parseInt(aluno[2])));
                    
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("O arquivo não foi encontrado.");
            e.printStackTrace();
        }

        System.out.println(alunos.size());
        ArvoreBinaria<Aluno> arvNome = new ArvoreBinaria<Aluno>(new ComparadorPorNome());
        ArvoreBinaria<Aluno> arvMat = new ArvoreBinaria<Aluno>(new ComparadorPorMatricula());

        for(int i=0; i<alunos.size(); i++){
            arvNome.setNovoNo(alunos.get(i));
            arvMat.setNovoNo(alunos.get(i));
        }

        System.out.println(arvMat.quantElem());
        System.out.println(arvNome.quantElem());
        
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

        System.out.println(arvMat.getAltura());
        System.out.println(arvNome.getAltura());
        
        */



        
    }

}