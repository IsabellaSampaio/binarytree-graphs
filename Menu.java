import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

/**
 * Menu - Exibe as configurações e realiza as chamadas de métodos para cada processo.
 */

public class Menu {
    private Scanner scan;
    private ArvoreBinaria<Aluno> arvMat;
    private ArvoreBinaria<Aluno> arvNome;


    public Menu(Scanner scan, int opcao){
        this.scan = scan;
        // Criação das árvores passando como parâmetro o comparador respectivos.
        arvNome = new ArvoreBinaria<Aluno>(new ComparadorPorNome());
        arvMat = new ArvoreBinaria<Aluno>(new ComparadorPorMatricula());
    }

    /**
     * Chama a função que percorrerá a ávore e devolverá uma lista com os alunos (em NIVEL ou ORDEM de matricula, ou nome, dependendo da função chamada dentro dela).
     */
    public ArrayList<Aluno> getAlunos() {
        return arvMat.caminhaEmOrdem(); // Função que devolve uma lista com alunos em Ordem Crescente de matricula
    }

    public static void limpaTela() {
        try {
            if(System.getProperty("os.name").contains("Windows")){
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }else{
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            // lidando com a exceção
            e.printStackTrace();
        }
    }
    
    /**
     * Imprime menu de funcionalidades disponíveis
     */
    public void menu() throws InterruptedException, IOException{
        limpaTela();
        System.out.println("BEM-VINDO(A)\n");
        System.out.println("_________________________________________\n");
        System.out.println("[1] Adicionar aluno");
        System.out.println("[2] Buscar aluno");
        System.out.println("[3] Excluir aluno");
        System.out.println("[4] Exibir estatísticas dos alunos");
        System.out.println("[5] Sair");
        System.out.println("_________________________________________\n");

    }
    
    /**
     * Realiza os inputs, outputs e chamadas de métodos necessários para cadastro de alunos.
     */
    public void menuAddAlunos(){

        int mat = 0;
        int nota = 0;
        String nome = "";
        String resp = "";


        do{
            limpaTela();

            // Tentativa de realizar cadastro dos alunos
            try{
            
                System.out.println("Matrícula do aluno: ");
                mat = scan.nextInt();
                scan.nextLine();
                System.out.println("Verificando...");

                // Definição de chamada de exceção
                if(arvMat.busca(new Aluno("", mat, 0)) != null){
                    throw new Exception("Matricula já existe");
                }

                System.out.println("Matricula ainda não cadastrada, prossiga o cadastro.");
                System.out.println("Nome do aluno: ");
                nome = scan.nextLine();
                System.out.println("Nota do aluno: ");
                nota = scan.nextInt();
                scan.nextLine();
                
                arvMat.addNovoNo(new Aluno(nome, mat, nota));
                arvNome.addNovoNo(new Aluno(nome, mat, nota));
        
                System.out.print("Aluno adicionado com sucesso!\n");

                System.out.println("Deseja adicionar mais um aluno? (S, s) ou (N, n)");
                resp = scan.nextLine();


                limpaTela();

            }catch(Exception e){
                System.out.println(e.getMessage()+"\nDeseja tentar novamente? (S, s) ou (N, n)");
                resp = scan.nextLine();
            }

        }while(!((resp.equals("N")) || (resp.equals("n"))));

    }

    /**
     * Realiza os inputs, outputs e chamadas de métodos necessários para busca de alunos.
     */

    public void menuBuscaAlunos(){
        limpaTela();
        int op = 0;
        int mat = 0;
        String nome = "";
        String resp = "";

        do{
            try{ 
                System.out.println("[1] Buscar aluno por matrícula");
                System.out.println("[2] Buscar aluno por nome");
                op = scan.nextInt();
                scan.nextLine();    
                limpaTela();
                if(op == 1){
                    System.out.println("Informe a matrícula do aluno que deseja buscar: ");
                    mat = scan.nextInt();
                    scan.nextLine();
                    Aluno alunoMat = arvMat.busca(new Aluno("", mat, 0));

                    if(alunoMat == null){
                        System.out.println("Matrícula não encontrada");
                    }else{
                        System.out.println("Dados do aluno: ");
                        System.out.println(alunoMat);
                    }
        
                }else if(op == 2){
                    System.out.println("Informe o nome do aluno que deseja buscar: ");
                    nome = scan.nextLine();
                    Aluno alunoNome = arvNome.busca(new Aluno(nome, 0, 0));

                    if(alunoNome == null){
                        System.out.println("Nome não encontrado");
                    }else{
                        System.out.println("Dados do aluno: ");
                        System.out.println(alunoNome);
                    }

                }else{
                    throw new Exception("Opção invalida tente novamente.");
                }
                
                System.out.println("Deseja buscar mais um aluno? (S, s) ou (N, n)");
                resp = scan.nextLine();

                limpaTela();

            }catch(Exception e){
                System.out.println(e.getMessage()+"\nDeseja tentar novamente? (S, s) ou (N, n)");
                resp = scan.nextLine();
                limpaTela();
            }


        }while(!((resp.equals("N")) || (resp.equals("n"))));


    }

    /**
     * Realiza os inputs, outputs e chamadas de métodos necessários para remoção de alunos.
     */
    public void menuRrAlunos(){
        limpaTela();
        int op = 0;
        int mat = 0;
        String nome = "";
        String resp = "";

    
        do{
            try{

                System.out.println("[1] Excluir aluno por matrícula");
                System.out.println("[2] Excluir aluno por nome");
                op = scan.nextInt();
                scan.nextLine();
                limpaTela();
                
                if(op == 1){
                    System.out.println("Informe a matrícula do aluno que deseja excluir: ");
                    mat = scan.nextInt();
                    scan.nextLine();
                    Aluno alunoMat = arvMat.busca(new Aluno("", mat, 0));
                    if (alunoMat == null) {
                        System.out.println("Matrícula não encontrada.");
                    } else {
                        System.out.println("Dados do aluno: ");
                        System.out.println(alunoMat);
                        arvMat.remove(alunoMat);
                        arvNome.remove(alunoMat);
                        System.out.print("Aluno excluído com sucesso!\n");
        
                    }
        
                }else if(op == 2){
                    System.out.println("Informe o nome do aluno que deseja excluir: ");
                    nome = scan.nextLine();
                    Aluno alunoNome = arvNome.busca(new Aluno(nome, 0, 0));
                    if (alunoNome == null) {
                        System.out.println("Nome não encontrado.");
                    } else {
                        System.out.println("Dados do aluno: ");
                        System.out.println(alunoNome);
                        arvNome.remove(alunoNome);
                        arvMat.remove(alunoNome);
                        System.out.print("Aluno excluído com sucesso!\n");
                    }
                }else{
                    throw new Exception("Opção invalida tente novamente.");
                }
                
                System.out.println("Deseja excluir mais um aluno? (S, s) ou (N, n)");
                resp = scan.nextLine();
                limpaTela();
            }catch(Exception e){
                System.out.println(e.getMessage()+"\nDeseja tentar novamente? (S, s) ou (N, n)");
                resp = scan.nextLine();
                limpaTela();
                }

        }while(!((resp.equals("N")) || (resp.equals("n"))));
    
    }
    

    /**
     * Realiza os inputs, outputs e chamadas de métodos necessários para exibir as estatisticas das árvores.
     */
    public void menuEstatsAlunos(){
        limpaTela();
        int op = 0;
        String resp = "";

        do{ 
            try{ 
            System.out.println("[1] Estatisticas por matrícula");
            System.out.println("[2] Estatisticas por nome");
            op = scan.nextInt();
            scan.nextLine();
            limpaTela();

            if(op == 1){
                System.out.println("Quantidade total de elementos: " + arvMat.quantElem());
                System.out.println("Altura da arvore: " + arvMat.calcAltura());
                System.out.println("Aluno de Maior matricula: " + arvMat.maxVal());
                System.out.println("Aluno de Menor matricula: " + arvMat.minVal());
                
            }else if(op == 2){
                System.out.println("Quantidade total de elementos: " + arvNome.quantElem());
                System.out.println("Altura da arvore: " + arvNome.calcAltura());
                System.out.println("Ultimo aluno na ordem alfabetica: " + arvNome.maxVal());
                System.out.println("Primeiro aluno na ordem alfabetica: " + arvNome.minVal());
            }else{
                throw new Exception("Opção invalida tente novamente.");
            }

            System.out.println("Deseja gerar mais estatisticas? (S, s) ou (N, n)");
            resp = scan.nextLine();

            limpaTela();
        }catch(Exception e){
            System.out.println(e.getMessage()+"\nDeseja tentar novamente? (S, s) ou (N, n)");
            resp = scan.nextLine();
            limpaTela();
        }


        }while(!((resp.equals("N")) || (resp.equals("n"))));
    }

    /**
     * Retorna a opção de funcionalidade escolhida pelo usuário
     */
    public int lerOpcaoMenu() throws InterruptedException, IOException{
        System.out.println("-> ESCOLHA UMA OPÇÃO: ");
        int opcao = scan.nextInt();
        scan.nextLine();
        return opcao;

    }

    /**
     * Lê o arquivo desejado e monta as Árvores
     */

    public void lerArq(){
        String arq = "";
        File arquivo = new File(arq);

        do{
        limpaTela();
        System.out.println("Informe o nome do arquivo que deseja ler: ");
        arq = scan.nextLine();
        arquivo = new File(arq);
        }while(!arquivo.exists());
        

        System.out.println("Lendo arquivo...");
        try {
            Scanner scanner = new Scanner(arquivo);
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] aluno = linha.split(";");
                int tam = aluno.length;
                if(tam>1){
                    Aluno nodeAluno = new Aluno(aluno[1],Integer.parseInt(aluno[0]), Integer.parseInt(aluno[2]));
                    arvNome.addNovoNo(nodeAluno);
                    arvMat.addNovoNo(nodeAluno);
                    
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("O arquivo não foi encontrado.");
            e.printStackTrace();
        }


    }

    
    /**
     * Cria um arquivo com os nomes dos alunos organizados em ordem crescente de matricula.
     */
    public void criaArq() {
        System.out.println(arvMat.quantElem());
        try {
            File arquivo = new File("saida123.txt"); 
            if(!arquivo.exists()){
                arquivo.createNewFile();
            }
            FileWriter escreveArq = new FileWriter("saida123.txt", false);
            BufferedWriter bufferWritter = new BufferedWriter(escreveArq);
            ArrayList<Aluno> alunos = getAlunos();
            for (Aluno aluno : alunos) {
                bufferWritter.write(aluno.getMatricula() + ";");
                bufferWritter.write(aluno.getNome() + ";");
                bufferWritter.write(aluno.getNota() + "");
                bufferWritter.newLine();
            }
            
            bufferWritter.close();
        } catch (IOException e) {
            System.out.println("Não foi possível adicionar os itens no arquivo.");
            e.printStackTrace();
        }
    }
    
    /**
     * Chama o método respectivo a opção recebida como parâmetro.
     */

    public boolean escolheMenu(int opcao) throws InterruptedException, IOException{
        switch(opcao){
            case 1:
                menuAddAlunos();
                return true;
            case 2:
                menuBuscaAlunos();
                return true;
            case 3:
                menuRrAlunos();
                return true;
            case 4:
                menuEstatsAlunos();
                return true;
            case 5:
                System.out.println("Saindo do programa e salvando arquivo...");
                Thread.sleep(2000);
                criaArq();
                return true;
            default:
                return true;
                
        }
    }  

    

}
