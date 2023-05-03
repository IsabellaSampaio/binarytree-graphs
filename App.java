
import java.util.Scanner;
import java.io.IOException;

/**
 * Programa principal
 */

public class App {

    public static void main(String[] args) throws InterruptedException, IOException {

        Scanner scan = new Scanner(System.in);
        int opcao = 0;

        Menu menu = new Menu(scan, opcao);
        menu.lerArq();

        while (opcao != 5) {
            menu.menu();
            opcao = menu.lerOpcaoMenu();
            menu.escolheMenu(opcao);
        }

    }

}