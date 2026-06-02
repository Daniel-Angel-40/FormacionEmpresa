package Vista;

import Controlador.ControladorPropietario;
import Controlador.ControladorVivienda;
import Modelo.Vivienda;

import java.util.Scanner;
/**
 * Clase encargada de la interfaz de usuario por consola para el control de inventario de Viviendas.
 * Ofrece un menú interactivo para coordinar el alta de inmuebles, sus características constructivas, precios y tipologías.
 * @author Daniel
 * @version 1.0
 */
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

    /**
     * Genera un nuevo registro de vivienda en el sistema tras validar explícitamente
     * que el ID de su propietario exista legalmente en los registros previos.
     */
    private static void insertarVivienda() {

        sc.nextLine();
        System.out.println("\n── Añadir vivienda ──");

        System.out.print("ID (alfanumerico): ");
        String idVivienda = sc.nextLine().trim();

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

        int resultado = ControladorVivienda.insertar(idVivienda, idPropietario, direccion, alquilerMensual, superficie, descripcion, mascota, tipo);

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

    /**
     * Solicita la clave primaria alfanumérica de una vivienda para imprimir su desglose técnico.
     */
    private static void consultarVivienda() {

        sc.nextLine();
        System.out.println("\n── Consultar vivienda ──");
        System.out.print("ID de la vivienda: ");
        String idVivienda = sc.nextLine().trim();

        Vivienda vivienda = ControladorVivienda.consultar(idVivienda);

        if (vivienda != null) {
            System.out.println(vivienda);
        } else {
            System.out.println("No existe una vivienda con el ID: " + idVivienda);
        }
    }

    /**
     * Permite redefinir las características funcionales y económicas de un inmueble.
     * Instancia un flujo de escaneo propio para asegurar el aislamiento de datos durante la edición concurrente por terminal.
     */
    private static void modificarVivienda() {

        Scanner sc = new Scanner(System.in);


        System.out.println("\n── Modificar vivienda ──");
        System.out.print("ID de la vivienda a modificar: ");
        String idVivienda = sc.nextLine().trim();

        Vivienda actual = ControladorVivienda.consultar(idVivienda);

        if (actual == null) {
            System.out.println("No existe una vivienda con el ID: " + idVivienda);
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

        int resultado = ControladorVivienda.modificar(idVivienda,idPropietario, direccion,
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

    /**
     * Borra del catálogo comercial un inmueble por su ID, condicionando el éxito operativo a la confirmación
     * de baja de todos los acuerdos contractuales que recaen sobre ella.
     */
    private static void eliminarVivienda() {

        sc.nextLine();
        System.out.println("\n── Eliminar vivienda ──");
        System.out.print("ID de la vivienda a eliminar: ");
        String idVivienda = sc.nextLine().trim();

        System.out.print("Esta accion eliminara tambien sus contratos. ¿Confirmar? (S/n): ");
        String respuesta = sc.nextLine().trim();

        if (!respuesta.equalsIgnoreCase("s")) {
            System.out.println("Operacion cancelada.");
            return;
        }

        ControladorVivienda.eliminar(idVivienda);
    }
}
