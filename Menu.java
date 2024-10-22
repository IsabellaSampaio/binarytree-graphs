import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Menu - Exibe as configurações e realiza as chamadas de métodos para cada processo.
 */
public class Menu {
    private final Scanner scan;
    private Grafo<Cidade> grafo;
    private Grafo<Cidade> arvoreGM;

    public Menu(Scanner scan, int opcao) {
        this.scan = scan;
        // Criação dos grafos.
    }

    /**
     * Limpa a tela do console.
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
     * Imprime o menu de funcionalidades disponíveis.
     */
    public void menu() throws InterruptedException, IOException {
        limpaTela();

        System.out.println("BEM-VINDO(A)\n");
        System.out.println("_________________________________________\n");
        System.out.println("[1] Obter Cidades Vizinhas");
        System.out.println("[2] Obter todos os caminhos de uma cidade à outra");
        System.out.println("[3] Calcular Árvore geradora mínima");
        System.out.println("[4] Calcular caminho minimo entre duas cidades");
        System.out.println("[5] Calcular caminho minimo entre duas cidades considerando apenas a AGM");
        System.out.println("[6] Calcular Fluxo Máximo");
        System.out.println("[7] Sair");
        System.out.println("_________________________________________\n");
    }

    /**
     * Lê a opção do menu escolhida pelo usuário.
     */
    public int lerOpcaoMenu() throws InterruptedException, IOException {
        System.out.println("-> ESCOLHA UMA OPÇÃO: ");
        int opcao = scan.nextInt();
        scan.nextLine();
        return opcao;
    }

    /**
     * Exibe as cidades vizinhas de uma cidade selecionada pelo usuário.
     */
    public void exibirCidadesVizinhas() {
        limpaTela();
        System.out.println("Insira o Id da cidade que deseja verificar os vizinhos: ");
        int opcao = scan.nextInt();
        scan.nextLine();
        System.out.print("Essas são as Cidades vizinhas a " + grafo.obterVerVizinhos(new Cidade(opcao, null)).getValor().getNome());
        System.out.println("\n\nAperte enter para voltar ao menu.");
        scan.nextLine();
    }

    /**
     * Exibe todos os caminhos possíveis a partir de uma cidade selecionada pelo usuário.
     */
    public void exibirCaminhosPossiveis() {
        limpaTela();
        System.out.println("Insira o Id da cidade que deseja verificar os caminhos: ");
        int opcao = scan.nextInt();
        scan.nextLine();
        grafo.buscaEmLargura(opcao);
        System.out.println("Aperte enter para voltar ao menu.");
        scan.nextLine();
    }

    public void gerarArvoreGM(){
        limpaTela();
        arvoreGM = grafo.gerarArvoreGM();
        float pesos[][] =  arvoreGM.getPesos();

        // Impressão para verificar se as arestas foram inseridas corretamente na ávore geradora minima
        System.out.println("\nMatriz de Adjacencia");
        for(int i=0; i<arvoreGM.getQuantVertices();i++){
            for(int j=0; j<arvoreGM.getQuantVertices();j++){
                System.out.print(pesos[i][j]);
                System.out.print(",");
            }
            System.out.println("\n");

        }
        System.out.println("Aperte enter para voltar ao menu.");
        scan.nextLine();
    }

    public void exibirCaminhoMinimo(){
        limpaTela();
        int origem;
        int dest;

        System.out.println("Informe a cidade de origem: ");
        origem = scan.nextInt();
        scan.nextLine();

        System.out.println("Informe a cidade de destino: ");
        dest = scan.nextInt();
        scan.nextLine(); // Consumir o caractere de nova linha

        grafo.caminhoMin(new Cidade(origem, ""), new Cidade(dest, ""));

        System.out.println("\nAperte enter para voltar ao menu.");
        scan.nextLine();
    }

    public void exibirCaminhoMinimoAGM(){
        limpaTela();
        arvoreGM = grafo.gerarArvoreGM();
        int origem;
        int dest;

        System.out.println("Informe a cidade de origem: ");
        origem = scan.nextInt();
        scan.nextLine(); // Consumir o caractere de nova linha

        System.out.println("Informe a cidade de destino: ");
        dest = scan.nextInt();
        scan.nextLine(); // Consumir o caractere de nova linha

        arvoreGM.caminhoMin(new Cidade(origem, ""), new Cidade(dest, ""));

        System.out.println("\nAperte enter para voltar ao menu.");
        scan.nextLine();

    }

    public void exibirFluxoMaximo(){
        limpaTela();
        int origem;
        int dest;

        System.out.println("Informe a cidade de origem");
        origem = scan.nextInt();
        scan.nextLine();

        System.out.println("Informe a cidade de destino");
        dest = scan.nextInt();
        scan.nextLine();

        System.out.println("O fluxo máximo entre os pontos: " + origem + " e " + dest + " é: " + grafo.fordFulkerson(origem, dest));
        System.out.println("\nAperte enter para voltar ao menu.");
        scan.nextLine();
    }


    /**
     * Lê um arquivo contendo informações sobre as cidades e cria o grafo correspondente.
     */
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
            int quant = Integer.parseInt(scanner.nextLine());
            grafo = new Grafo<Cidade>(new Comparador(), quant);

            for (int i = 0; i < quant; i++) {
                String linha = scanner.nextLine();
                String[] l = linha.split(",");
                Cidade c = new Cidade(Integer.parseInt(l[0]), l[1]);
                grafo.adicionarVertice(c);
            }

            for (int j = 0; j < quant; j++) {
                String linha = scanner.nextLine();
                String[] str = linha.split(",");
                for (int c = 0; c < str.length; c++) {
                    grafo.adicionarAresta(grafo.verticePorIndex(j), grafo.verticePorIndex(c), Float.parseFloat(str[c]));
                }
            }
            scanner.close();
            System.out.println("Grafo criado com sucesso!");
        } catch (FileNotFoundException e) {
            System.out.println("O arquivo não foi encontrado.");
            e.printStackTrace();
        }
    }

    /**
     * Executa a opção selecionada pelo usuário.
     */
    public boolean escolheMenu(int opcao) throws InterruptedException, IOException {
        switch (opcao) {
            case 1:
                exibirCidadesVizinhas();
                return true;
            case 2:
                exibirCaminhosPossiveis();
                return true;
            case 3:
                gerarArvoreGM();
                return true;
            case 4:
                exibirCaminhoMinimo();
                return true;
            case 5: 
                exibirCaminhoMinimoAGM();
                return true;
            case 6:
                exibirFluxoMaximo();
                return true;
            case 7:
                System.out.println("Saindo do programa.");
                return true;
            default:
                return true;
        }
    }
}
