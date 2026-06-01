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

        ArrayList<Vivienda> viviendasLibres = ControladorEstadisticas.viviendasLibres();

        if (viviendasLibres.isEmpty()) {
            System.out.println("No hay viviendas libres en este momento");
            return;
        }

        System.out.println("Total de viviendas libres: " + viviendasLibres.size());

        for (Vivienda vivienda : viviendasLibres) {
            System.out.println(vivienda);
            System.out.print("¿Mostrar siguiente?(S/n): ");
            sc.nextLine();
            boolean respuesta = sc.nextLine().equalsIgnoreCase("n");
            if (respuesta) {
                break;
            }
        }

        System.out.print("¿Quieres exportar la informacion a formato json?(S/n): ");
        boolean respuesta = sc.nextLine().equalsIgnoreCase("s");

        if (respuesta) {
            ControladorEstadisticas.viviendasLibresJson();
        }

        System.out.print("¿Quieres exportar la informacion a formato CSV?(S/n): ");
        respuesta = sc.nextLine().equalsIgnoreCase("s");

        if (respuesta) {
            ControladorEstadisticas.viviendasLibresCsv();
        }
    }

    private static void viviendasActivasPropietario() {

        System.out.println("\n── Viviendas activas de propietario ──");
        System.out.print("ID del propietario: ");

        int idPropietario = sc.nextInt();

        ArrayList<Vivienda> viviendasActivas = ControladorEstadisticas.viviendasActivasPropietario(idPropietario);

        if (viviendasActivas.isEmpty()) {
            System.out.println("El propietario con ID " + idPropietario + " no tiene viviendas con contrato activo");
            return;
        }

        System.out.println("\nViviendas en alquiler activo: " + viviendasActivas.size());

        for (Vivienda vivienda : viviendasActivas) {
            System.out.println(vivienda);
            System.out.print("¿Mostrar siguiente?(S/n): ");
            sc.nextLine();
            boolean respuesta = sc.nextLine().equalsIgnoreCase("n");
            if (respuesta) {
                break;
            }
        }

        System.out.print("¿Quieres exportar la informacion a formato json?(S/n): ");
        boolean respuesta = sc.nextLine().equalsIgnoreCase("s");

        if (respuesta) {
            ControladorEstadisticas.viviendasActivasPropietarioJson(idPropietario);
        }

        System.out.print("¿Quieres exportar la informacion a formato Csv?(S/n): ");
        respuesta = sc.nextLine().equalsIgnoreCase("s");

        if (respuesta) {
            ControladorEstadisticas.viviendasActivasPropietarioCsv(idPropietario);
        }
    }

    private static void historicoInquilino() {

        System.out.println("\n── Histórico de inquilino ──");
        System.out.print("ID del inquilino: ");
        int idInquilino = sc.nextInt();

        ArrayList<Contrato> contratosInquilino = EstadisticaDAO.historicoInquilino(idInquilino);

        if (contratosInquilino.isEmpty()) {
            System.out.println("No se encontraron contratos para el inquilino con ID: " + idInquilino);
            return;
        }

        System.out.println("\nTotal de contratos: " + contratosInquilino.size());

        for (Contrato contrato : contratosInquilino) {
            System.out.println(contrato);
            System.out.print("¿Mostrar siguiente?(S/n): ");
            sc.nextLine();
            boolean respuesta = sc.nextLine().equalsIgnoreCase("n");
            if (respuesta) {
                break;
            }
        }

        System.out.print("¿Quieres exportar la informacion a formato json?(S/n): ");
        boolean respuesta = sc.nextLine().equalsIgnoreCase("s");

        if (respuesta) {
            ControladorEstadisticas.historicoInquilinoJson(idInquilino);
        }

        System.out.print("¿Quieres exportar la informacion a formato Csv?(S/n): ");
        respuesta = sc.nextLine().equalsIgnoreCase("s");

        if (respuesta) {
            ControladorEstadisticas.historicoInquilinoCsv(idInquilino);
        }
    }
}
