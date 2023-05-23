
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

/**
 * Menu - Exibe as configurações e realiza as chamadas de métodos para cada
 * processo.
 */

public class Menu {
    private Scanner scan;
    private Grafo<Cidade> grafo;


    public Menu(Scanner scan, int opcao) {
        this.scan = scan;
        grafo = new Grafo<Cidade>();
        // Criação dos grafos.


    }

    /**
     * Chama a função que percorrerá a ávore e devolverá uma lista com os alunos (em
     * NIVEL ou ORDEM de matricula, ou nome, dependendo da função chamada dentro
     * dela).
     */

    public static void limpaTela() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
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

    public void menu() throws InterruptedException, IOException {

        System.out.println("BEM-VINDO(A)\n");
        System.out.println("_________________________________________\n");
        System.out.println("[1] Adicionar aluno");
        System.out.println("[2] Buscar aluno");
        System.out.println("[3] Excluir aluno");
        System.out.println("[4] Exibir estatísticas dos alunos");
        System.out.println("[5] Sair");
        System.out.println("[6] Imprimir árvores");
        System.out.println("_________________________________________\n");

    }

    public int lerOpcaoMenu() throws InterruptedException, IOException {
        System.out.println("-> ESCOLHA UMA OPÇÃO: ");
        int opcao = scan.nextInt();
        scan.nextLine();
        return opcao;
    }

    
    public void lerArq() {
        String arq = "";
        File arquivo = new File(arq);


        do {
            limpaTela();
            System.out.println("Informe o nome do arquivo que deseja ler: ");
            arq = scan.nextLine();
            arquivo = new File(arq);
        } while (!arquivo.exists());

        System.out.println("Lendo arquivo...");
        try {
            Scanner scanner = new Scanner(arquivo);
            int quant =  Integer.parseInt(scanner.nextLine());
            
            for(int i=0; i<quant; i++) {
                String linha = scanner.nextLine();
                String[] l = linha.split(",");
                Cidade c = new Cidade( Integer.parseInt(l[0]), l[1]);
                grafo.adicionarVertice(c);
            }

            for(int j=0; j<quant; j++) {
                String linha = scanner.nextLine();
                String[] str = linha.split(",");
                for(int c=0; c<str.length; c++){
                    grafo.adicionarAresta(grafo.verticePorIndex(j), grafo.verticePorIndex(c), Float.parseFloat(str[c]));
                    System.out.println("Aresta adicionada com sucesso");
                    System.out.println(grafo.verticePorIndex(j).getNome()+ " -------> " + grafo.verticePorIndex(c).getNome() + " = "+ Float.parseFloat(str[c]));
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("O arquivo não foi encontrado.");
            e.printStackTrace();
        }

    }

    /**
     * Cria um arquivo com os nomes dos alunos organizados em ordem crescente de
     * matricula.
     */

    public void criaArq() {
        try {
            File arquivo = new File("saida123.txt");
            if (!arquivo.exists()) {
                arquivo.createNewFile();
            }
            FileWriter escreveArq = new FileWriter("saida123.txt", false);
            BufferedWriter bufferWritter = new BufferedWriter(escreveArq);

            bufferWritter.close();
        } catch (IOException e) {
            System.out.println("Não foi possível adicionar os itens no arquivo.");
            e.printStackTrace();
        }
    }

    /**
     * Chama o método respectivo a opção recebida como parâmetro.
     */

    public boolean escolheMenu(int opcao) throws InterruptedException, IOException {
        switch (opcao) {
            case 1:
                return true;
            case 2:
                return true;
            case 3:
                return true;
            case 4:
                return true;
            case 5:
                System.out.println("Saindo do programa e salvando arquivo...");
                Thread.sleep(2000);
                criaArq();
                return true;
            case 6:
                return true;
            default:
                return true;

        }
    }

}
