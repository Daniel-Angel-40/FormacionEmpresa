import Vista.VistaContrato;
import Vista.VistaInquilino;
import Vista.VistaPropietario;
import Vista.VistaVivienda;

import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int opcion;

        do {
            System.out.println("\n╔══════════════════════════════════╗");
            System.out.println("║         ALQUILARIA - MENÚ        ║");
            System.out.println("╠══════════════════════════════════╣");
            System.out.println("║  1. Propietarios                 ║");
            System.out.println("║  2. Viviendas                    ║");
            System.out.println("║  3. Inquilinos                   ║");
            System.out.println("║  4. Contratos                    ║");
            System.out.println("║  0. Salir                        ║");
            System.out.println("╚══════════════════════════════════╝");
            System.out.print("Opción: ");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    VistaPropietario.menuPropietario();
                    break;
                case 2:
                    VistaVivienda.menuVivienda();
                    break;
                case 3:
                    VistaInquilino.menuInquilino();
                    break;
                case 4:
                    VistaContrato.menuContrato();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (opcion != 0);
    }
}
