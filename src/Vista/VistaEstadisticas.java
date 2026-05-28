package Vista;

import Controlador.ControladorEstadisticas;
import DAO.EstadisticaDAO;
import Modelo.Contrato;
import Modelo.Vivienda;

import java.util.ArrayList;
import java.util.Scanner;

public class VistaEstadisticas {

    private static final Scanner sc = new Scanner(System.in);

    public static void menuEstadisticas() {

        int opcion;

        do {


            System.out.println("\n╔══════════════════════════════════╗");
            System.out.println("║          ESTADÍSTICAS            ║");
            System.out.println("╠══════════════════════════════════╣");
            System.out.println("║  1. Histórico de inquilino       ║");
            System.out.println("║  2. Viviendas activas propietario║");
            System.out.println("║  3. Viviendas libres             ║");
            System.out.println("║  0. Volver                       ║");
            System.out.println("╚══════════════════════════════════╝");
            System.out.print("Opción: ");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    historicoInquilino();
                    break;
                case 2:
                    viviendasActivasPropietario();
                    break;
                case 3:
                    viviendasLibres();
                    break;
                case 0:
                    System.out.println("Volviendo al menu principal...");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (opcion != 0);
    }

    private static void viviendasLibres() {

        System.out.println("\n── Viviendas libres ──");

        ArrayList<Vivienda> libres = ControladorEstadisticas.viviendasLibres();

        if (libres.isEmpty()) {
            System.out.println("No hay viviendas libres en este momento");
            return;
        }

        System.out.println("Total de viviendas libres: " + libres.size());

        for (Vivienda v : libres) {
            System.out.println(v);
            System.out.print("¿Mostrar siguiente?(S/n): ");
            sc.nextLine();
            String res = sc.nextLine();
            if (res.equalsIgnoreCase("n")) {
                break;
            }
        }
    }

    private static void viviendasActivasPropietario() {

        System.out.println("\n── Viviendas activas de propietario ──");
        System.out.print("ID del propietario: ");

        int id = sc.nextInt();

        ArrayList<Vivienda> lista = ControladorEstadisticas.viviendasActivasPropietario(id);

        if (lista.isEmpty()) {
            System.out.println("El propietario con ID " + id + " no tiene viviendas con contrato activo");
            return;
        }

        System.out.println("\nViviendas en alquiler activo: " + lista.size());

        for (Vivienda v : lista) {
            System.out.println(v);
            System.out.print("¿Mostrar siguiente?(S/n): ");
            sc.nextLine();
            String res = sc.nextLine();
            if (res.equalsIgnoreCase("n")) {
                break;
            }
        }
    }

    private static void historicoInquilino() {

        System.out.println("\n── Histórico de inquilino ──");
        System.out.print("ID del inquilino: ");
        int id = sc.nextInt();

        ArrayList<Contrato> lista = EstadisticaDAO.historicoInquilino(id);

        if (lista.isEmpty()) {
            System.out.println("No se encontraron contratos para el inquilino con ID: " + id);
            return;
        }

        System.out.println("\nTotal de contratos: " + lista.size());

        for (Contrato c : lista) {
            System.out.println(c);
            System.out.print("¿Mostrar siguiente?(S/n): ");
            sc.nextLine();
            String res = sc.nextLine();
            if (res.equalsIgnoreCase("n")) {
                break;
            }
        }
    }
}
