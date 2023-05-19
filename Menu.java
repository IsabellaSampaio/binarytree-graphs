
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
    private ArvoreBinaria<Aluno> arvMat;
    private ArvoreAVL<Aluno> arvMatAVL;
    private ArvoreAVL<Aluno> arvNomeAVL;
    private ArvoreBinaria<Aluno> arvNome;

    public Menu(Scanner scan, int opcao) {
        this.scan = scan;
        // Criação das árvores passando como parâmetro o comparador respectivos.
        arvNome = new ArvoreBinaria<Aluno>(new ComparadorPorNome());
        arvMat = new ArvoreBinaria<Aluno>(new ComparadorPorMatricula());
        arvMatAVL = new ArvoreAVL<Aluno>(new ComparadorPorMatricula());
        arvNomeAVL = new ArvoreAVL<Aluno>(new ComparadorPorNome());
    }

    /**
     * Chama a função que percorrerá a ávore e devolverá uma lista com os alunos (em
     * NIVEL ou ORDEM de matricula, ou nome, dependendo da função chamada dentro
     * dela).
     */

    public ArrayList<Aluno> getAlunos() {
        return arvMat.caminhaEmOrdem(); // Função que devolve uma lista com alunos em Ordem Crescente de matricula
    }

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
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] aluno = linha.split(";");
                int tam = aluno.length;
                if (tam > 1) {
                    Aluno nodeAluno = new Aluno(aluno[1], Integer.parseInt(aluno[0]), Integer.parseInt(aluno[2]));
                    arvNome.addNovoNo(nodeAluno);
                    arvMat.addNovoNo(nodeAluno);
                    arvMatAVL.addNovoNo(nodeAluno);
                    arvNomeAVL.addNovoNo(nodeAluno);
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
        System.out.println(arvMat.quantElem());
        try {
            File arquivo = new File("saida123.txt");
            if (!arquivo.exists()) {
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
                System.out.println("*Arvore AVL*");
                System.out.println("Altura da árvore: " + arvMatAVL.getRaiz().getAltura());
                arvMatAVL.printIndented(arvMatAVL.getRaiz(), "", true);
                System.out.println();
                System.out.println("*Arvore Binaria*");
                System.out.println("Altura da árvore: " + arvMat.getRaiz().getAltura());
                arvMat.printIndented(arvMat.getRaiz(), "", true);
                System.out.println();
                System.out.println("Aperte enter pra continuar.");
                scan.nextLine();
                return true;
            default:
                return true;

        }
    }

}
