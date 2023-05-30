
import java.util.Scanner;
import java.io.IOException;

/**
 * Programa principal
 */

public class App2 {

    public static void main(String[] args) throws InterruptedException, IOException {

        Scanner scan = new Scanner(System.in);
        int opcao = 0;

        Menu2 menu = new Menu2(scan, opcao);
        menu.lerArq();

        while (opcao != 5) {
            menu.menu();
            opcao = menu.lerOpcaoMenu();
            menu.escolheMenu(opcao);
        }

    }

}