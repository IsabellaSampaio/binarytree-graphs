import java.util.Scanner;
import java.io.IOException;
public class Menu {
    private Scanner scan;
    private int opcao;

    public Menu(Scanner scan, int opcao){
        this.scan = scan;
        this.opcao = opcao;
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
    


    public void menu() throws InterruptedException, IOException{
        limpaTela();
        System.out.println("BEM-VINDO(A)\n");
        System.out.println("_________________________________________\n");
        System.out.println("[1] Adicionar aluno");
        System.out.println("[2] Buscar aluno");
        System.out.println("[3] Excluir aluno");
        System.out.println("[4] Exibir estatísticas do aluno");
        System.out.println("[5] Sair");
        System.out.println("_________________________________________\n");

    }

    public void menuAddAlunos() throws InterruptedException, IOException{
        limpaTela();
        int mat = 0;
        int nota = 0;
        String nome = "";
        String resp = "";

        //conferir se o aluno já existe no sistema, se existir informar isso ao usuário
        //conferir também se o aluno está sendo adicionado ao sistema corretamente ao final da execução da função

        do{
            System.out.println("Matrícula do aluno: ");
            mat = scan.nextInt();
            scan.nextLine();
            System.out.println("Nome do aluno: ");
            nome = scan.nextLine();
            System.out.println("Nota do aluno: ");
            nota = scan.nextInt();
            scan.nextLine();
    
            System.out.print("Aluno adicionado com sucesso!\n");
            System.out.println("Deseja adicionar mais um aluno? (S, s) ou (N, n)");
            resp = scan.nextLine();


            Aluno aluno = new Aluno(nome, mat, nota);

            limpaTela();

            //importante verificar se o usuário inseriu o dado corretamente em "op", caso ele insira algo diferente do que é pedido é necessário informar o erro a ele e pedir que digite novamente

        }while(!((resp.equals("N")) || (resp.equals("n"))));


    }

    public void menuBuscaAlunos(){
        limpaTela();
        int op = 0;
        int mat = 0;
        String nome = "";
        String resp = "";
        String respError = "";

        do{
            System.out.println("[1] Buscar aluno por matrícula");
            System.out.println("[2] Buscar aluno por nome");
            op = scan.nextInt();
            scan.nextLine();    
            limpaTela();
            if(op == 1){
                System.out.println("Informe a matrícula do aluno que deseja buscar: ");
                mat = scan.nextInt();
                scan.nextLine();
                //verifica se o aluno está na árvore, se sim retorna os dados do aluno, se não retorna "Aluno não está registrado no sistema"
    
            }else if(op == 2){
                System.out.println("Informe o nome do aluno que deseja buscar: ");
                nome = scan.nextLine();
                //verifica se o aluno está na árvore, se sim retorna os dados do aluno, se não retorna "Aluno não está registrado no sistema"
            }

            
            System.out.println("Deseja buscar mais um aluno? (S, s) ou (N, n)");
            resp = scan.nextLine();

            limpaTela();


            //importante verificar se o usuário inseriu o dado corretamente em "op", caso ele insira algo diferente de 1 ou 2 é necessário informar o erro a ele e pedir que digite novamente

        }while(!((resp.equals("N")) || (resp.equals("n"))));


    }

    public void menuRrAlunos(){
        limpaTela();
        int op = 0;
        int mat = 0;
        String nome = "";
        String resp = "";

        do{

            System.out.println("[1] Excluir aluno por matrícula");
            System.out.println("[2] Excluir aluno por nome");
            op = scan.nextInt();
            scan.nextLine();
            limpaTela();
            
            if(op == 1){
                System.out.println("Informe a matrícula do aluno que deseja excluir: ");
                mat = scan.nextInt();
                scan.nextLine();
                //verifica se o aluno está na árvore, se sim retorna os dados do aluno para o usuário verificar e depois exclui do sistema, se não retorna "Aluno não está registrado no sistema"
    
            }else if(op == 2){
                System.out.println("Informe o nome do aluno que deseja excluir: ");
                nome = scan.nextLine();
                //verifica se o aluno está na árvore, se sim retorna os dados do aluno para o usuário verificar e depois exclui do sistema, se não retorna "Aluno não está registrado no sistema"
            }
            
            System.out.print("Aluno excluído com sucesso!\n");
            System.out.println("Deseja excluir mais um aluno? (S, s) ou (N, n)");
            resp = scan.nextLine();
            limpaTela();


        }while(!((resp.equals("N")) || (resp.equals("n"))));
        
    }

    public void menuEstatsAlunos(){
        limpaTela();
        int op = 0;
        int mat = 0;
        String nome = "";
        String resp = "";

        do{
            System.out.println("[1] Estatistica do aluno por matrícula");
            System.out.println("[2] Estatistica do aluno por nome");
            op = scan.nextInt();
            scan.nextLine();
            limpaTela();

            if(op == 1){
                System.out.println("Informe a matrícula do aluno que deseja gerar as estatísticas: ");
                mat = scan.nextInt();
                scan.nextLine();
                //verifica se o aluno está na árvore, se sim retorna os dados do aluno para o usuário verificar, se não retorna "Aluno não está registrado no sistema"
    
            }else if(op == 2){
                System.out.println("Informe o nome do aluno que deseja gerar as estatísticas: ");
                nome = scan.nextLine();
                //verifica se o aluno está na árvore, se sim retorna os dados do aluno para o usuário verificar, se não retorna "Aluno não está registrado no sistema"
            }

            System.out.println("Deseja gerar as estatisticas de mais um aluno? (S, s) ou (N, n)");
            resp = scan.nextLine();

            limpaTela();


        }while(!((resp.equals("N")) || (resp.equals("n"))));
    }

    public int lerOpcaoMenu() throws InterruptedException, IOException{
        System.out.println("-> ESCOLHA UMA OPÇÃO: ");
        int opcao = scan.nextInt();
        scan.nextLine();
        return opcao;

        //importante verificar se o usuário inseriu o dado corretamente em "op", caso ele insira algo diferente dos números no menu é necessário informar o erro a ele e pedir que digite novamente
    }

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
                return true;
            default:
                return true;
                
        }
    }  

}