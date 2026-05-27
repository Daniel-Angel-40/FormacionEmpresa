package Vista;

import Controlador.ControladorInquilino;
import Controlador.ControladorVivienda;
import DAO.InquilinoDAO;
import Modelo.Inquilino;

import java.util.Scanner;

public class VistaInquilino {

    private static final Scanner sc = new Scanner(System.in);

    public static void menuInquilino() {

        int opcion;

        do {


            System.out.println("\n╔══════════════════════════════════╗");
            System.out.println("║        GESTIÓN INQUILINOS        ║");
            System.out.println("╠══════════════════════════════════╣");
            System.out.println("║  1. Añadir inquilino             ║");
            System.out.println("║  2. Consultar inquilino          ║");
            System.out.println("║  3. Modificar inquilino          ║");
            System.out.println("║  4. Eliminar inquilino           ║");
            System.out.println("║  0. Volver                       ║");
            System.out.println("╚══════════════════════════════════╝");
            System.out.print("Opción: ");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    insertarInquilino();
                    break;
                case 2:
                    consultarInquilino();
                    break;
                case 3:
                    actualizarInquilino();
                    break;
                case 4:
                    eliminarInquilino();
                    break;
                case 0:
                    System.out.println("Volviendo al menu principal...");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }

        } while (opcion != 0);
    }

    private static void insertarInquilino() {

        System.out.println("\n── Añadir inquilino ──");

        sc.nextLine();
        System.out.print("DNI: ");
        String DNI = sc.nextLine().trim();

        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();

        System.out.print("Correo: ");
        String correo = sc.nextLine().trim();

        System.out.print("Telefono: ");
        String telefono = sc.nextLine().trim();

        System.out.print("¿Tiene mascota? (S/n): ");
        boolean mascota = sc.nextLine().trim().equalsIgnoreCase("s");

        int id = ControladorInquilino.insertar(DNI, nombre, correo, telefono, mascota);

        if (id != -1) {
            System.out.println("Inquilino insertado exitosamente");
            System.out.println("ID: " + id);
        }
    }

    private static void consultarInquilino() {

        System.out.println("\n── Consultar inquilino ──");
        System.out.print("ID: ");
        int id = sc.nextInt();

        Inquilino i = ControladorInquilino.consultar(id);

        if (i != null) {
            System.out.println(i);
        } else {
            System.out.println("No existe un inquilino con el ID: " + id);
        }

    }

    private static void actualizarInquilino() {

        System.out.println("\n── Modificar inquilino ──");
        System.out.print("ID del inquilino a modificar: ");
        int id = sc.nextInt();

        Inquilino actual = ControladorInquilino.consultar(id);

        if (actual == null) {
            System.out.println("No existe un inquilino con el ID: " + id);
            return;
        }

        sc.nextLine();
        System.out.print("DNI [" + actual.getDNI() + "]: ");
        String DNI = sc.nextLine().trim();

        System.out.print("Nombre [" + actual.getNombre() + "]: ");
        String nombre = sc.nextLine().trim();

        System.out.print("Correo [" + actual.getCorreo() + "]: ");
        String correo = sc.nextLine().trim();

        System.out.print("Telefono [" + actual.getTelefono() + "]: ");
        String telefono = sc.nextLine().trim();

        System.out.print("¿Tiene mascota?(S/n) [" + actual.isTiene_mascota() + "]: ");
        boolean mascota = sc.nextLine().trim().equalsIgnoreCase("s");

        int respuesta = ControladorInquilino.actualizar(id, DNI, nombre, correo, telefono, mascota);

        if (respuesta == 0) {
            System.out.println("Inquilino actualizado exitosamente");
        } else {
            System.out.println("Error al actualizar inquilino");
        }
    }

    private static void eliminarInquilino() {

        System.out.println("\n── Eliminar inquilino ──");
        System.out.print("ID del inquilino a eliminar: ");
        int id = sc.nextInt();

        Inquilino i = ControladorInquilino.consultar(id);

        if (i == null) {
            System.out.println("No existe un inquilino con el ID: " + id);
            return;
        }

        sc.nextLine();
        System.out.print("Esta accion eliminara tambien sus contratos. ¿Confirmar?(S/n): ");
        String respuesta = sc.nextLine().trim();

        if (!respuesta.equalsIgnoreCase("s")) {
            System.out.println("Operacion cancelada");
            return;
        }

        ControladorInquilino.eliminar(id);
    }
}
