package Vista;

import Controlador.ControladorPropietario;
import Controlador.ControladorVivienda;
import Modelo.Vivienda;

import java.util.Scanner;

public class VistaVivienda {

    public static final Scanner sc = new Scanner(System.in);

    public static void menuVivienda() {
        int opcion;

        do {
            System.out.println("\n╔══════════════════════════════════╗");
            System.out.println("║        GESTIÓN VIVIENDAS         ║");
            System.out.println("╠══════════════════════════════════╣");
            System.out.println("║  1. Añadir vivienda              ║");
            System.out.println("║  2. Consultar vivienda           ║");
            System.out.println("║  3. Modificar vivienda           ║");
            System.out.println("║  4. Eliminar vivienda            ║");
            System.out.println("║  0. Volver                       ║");
            System.out.println("╚══════════════════════════════════╝");
            System.out.print("Opción: ");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    insertarVivienda();
                    break;
                case 2:
                    consultarVivienda();
                    break;
                case 3:
                    modificarVivienda();
                    break;
                case 4:
                    eliminarVivienda();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }

        } while (opcion != 0);
    }

    private static void insertarVivienda() {

        sc.nextLine();
        System.out.println("\n── Añadir vivienda ──");

        System.out.print("ID (alfanumerico): ");
        String id = sc.nextLine().trim();

        System.out.print("ID propietario: ");
        int idPropietario = sc.nextInt();
        if (ControladorPropietario.consultar(idPropietario) == null) {
            System.out.println("No existe ese propietario");
            return;
        }

        sc.nextLine();
        System.out.print("Direccion: ");
        String direccion = sc.nextLine().trim();

        System.out.print("Alquiler Mensual (€): ");
        double alquilerMensual = sc.nextDouble();

        System.out.print("Superficie (m²): ");
        double superficie = sc.nextDouble();

        sc.nextLine();
        System.out.print("Descripcion: ");
        String descripcion = sc.nextLine().trim();

        System.out.print("¿Permite Mascota?(S/n): ");
        boolean mascota = sc.nextLine().trim().equalsIgnoreCase("s");

        System.out.print("Tipo (apartamento/atico/casa): ");
        String tipo = sc.nextLine().trim().toLowerCase();

        int resultado = ControladorVivienda.insertar(id, idPropietario, direccion, alquilerMensual, superficie, descripcion, mascota, tipo);

        switch (resultado) {
            case 0:
                System.out.println("Vivienda insertada correctamente.");
                break;
            case -1:
                System.out.println("Error al insertar vivienda.");
                break;
            case -2:
                System.out.println("Datos invalidos de la vivienda");
                break;
        }
    }

    private static void consultarVivienda() {

        sc.nextLine();
        System.out.println("\n── Consultar vivienda ──");
        System.out.print("ID de la vivienda: ");
        String id = sc.nextLine().trim();

        Vivienda v = ControladorVivienda.consultar(id);

        if (v != null) {
            System.out.println(v);
        } else {
            System.out.println("No existe una vivienda con el ID: " + id);
        }
    }

    private static void modificarVivienda() {

        Scanner sc = new Scanner(System.in);


        System.out.println("\n── Modificar vivienda ──");
        System.out.print("ID de la vivienda a modificar: ");
        String id = sc.nextLine().trim();

        Vivienda actual = ControladorVivienda.consultar(id);

        if (actual == null) {
            System.out.println("No existe una vivienda con el ID: " + id);
            return;
        }

        System.out.print("ID propietario [" + actual.getPropietario() + "]: ");
        int idPropietario = sc.nextInt();
        if (ControladorPropietario.consultar(idPropietario) == null) {
            System.out.println("No existe un propietario con el ID: " + idPropietario);
            return;
        }

        sc.nextLine();
        System.out.print("Direccion [" + actual.getDireccion() + "]: ");
        String direccion = sc.nextLine().trim();

        System.out.print("Alquiler Mensual [" + actual.getAlquiler_mensual() + "]: ");
        double alquilerMensual = sc.nextDouble();

        System.out.print("Superficie [" + actual.getSuperficie() + "]: ");
        double superficie = sc.nextDouble();

        sc.nextLine();
        System.out.print("Descripcion [" + actual.getDescripcion() + "]: ");
        String descripcion = sc.nextLine().trim();

        System.out.print("¿Permite Mascota?(S/n) [" + actual.isPermite_mascota() + "]: ");
        boolean mascota = sc.nextLine().trim().equalsIgnoreCase("s");

        System.out.print("Tipo [" + actual.getTipo() + "]: ");
        String tipo = sc.nextLine().trim();

        int resultado = ControladorVivienda.modificar(id,idPropietario, direccion,
                alquilerMensual, superficie, descripcion, mascota, tipo);

        switch (resultado) {
            case 0:
                System.out.println("Vivienda modificada correctamente.");
                break;
            case -1:
                System.out.println("Error al modificar vivienda.");
                break;
            case -2:
                System.out.println("Datos invalidos de la vivienda");
                break;
        }
    }

    private static void eliminarVivienda() {

        sc.nextLine();
        System.out.println("\n── Eliminar vivienda ──");
        System.out.print("ID de la vivienda a eliminar: ");
        String id = sc.nextLine().trim();

        System.out.print("Esta accion eliminara tambien sus contratos. ¿Confirmar? (S/n): ");
        String respuesta = sc.nextLine().trim();

        if (!respuesta.equalsIgnoreCase("s")) {
            System.out.println("Operacion cancelada.");
            return;
        }

        ControladorVivienda.eliminar(id);
    }
}
