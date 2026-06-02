package Vista;

import Controlador.ControladorInquilino;
import Controlador.ControladorVivienda;
import DAO.InquilinoDAO;
import Modelo.Inquilino;

import java.util.Scanner;
/**
 * Clase encargada de la interfaz de usuario por consola para gestionar el censo de Inquilinos.
 * Permite registrar nuevos perfiles, validar campos, actualizar datos de contacto e indicar la tenencia de mascotas.
 * @author Daniel
 * @version 1.0
 */
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

    /**
     * Recoge los datos personales esenciales de un inquilino (DNI, Nombre, Contacto y Mascota),
     * los procesa mediante el controlador pertinente e imprime la confirmación operacional con su ID generado.
     */
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
        boolean tieneMascota = sc.nextLine().trim().equalsIgnoreCase("s");

        int id = ControladorInquilino.insertar(DNI, nombre, correo, telefono, tieneMascota);

        if (id != -1) {
            System.out.println("Inquilino insertado exitosamente");
            System.out.println("ID: " + id);
        }
    }

    /**
     * Recupera un registro de inquilino según su ID y formatea su salida por pantalla.
     */
    private static void consultarInquilino() {

        System.out.println("\n── Consultar inquilino ──");
        System.out.print("ID: ");
        int idInquilino = sc.nextInt();

        Inquilino inquilino = ControladorInquilino.consultar(idInquilino);

        if (inquilino != null) {
            System.out.println(inquilino);
        } else {
            System.out.println("No existe un inquilino con el ID: " + idInquilino);
        }

    }

    /**
     * Modifica los atributos de un inquilino previamente registrado.
     * Presenta la información existente como referencia entre corchetes antes de la nueva captura de texto.
     */
    private static void actualizarInquilino() {

        System.out.println("\n── Modificar inquilino ──");
        System.out.print("ID del inquilino a modificar: ");
        int idInquilino = sc.nextInt();

        Inquilino actual = ControladorInquilino.consultar(idInquilino);

        if (actual == null) {
            System.out.println("No existe un inquilino con el ID: " + idInquilino);
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
        boolean tieneMascota = sc.nextLine().trim().equalsIgnoreCase("s");

        int respuesta = ControladorInquilino.actualizar(idInquilino, DNI, nombre, correo, telefono, tieneMascota);

        if (respuesta == 0) {
            System.out.println("Inquilino actualizado exitosamente");
        } else {
            System.out.println("Error al actualizar inquilino");
        }
    }

    /**
     * Coordina la eliminación lógica y física de un inquilino del sistema por ID.
     * Advierte explícitamente al operador sobre la eliminación colateral de sus contratos de alquiler en vigor.
     */
    private static void eliminarInquilino() {

        System.out.println("\n── Eliminar inquilino ──");
        System.out.print("ID del inquilino a eliminar: ");
        int idInquilino = sc.nextInt();

        Inquilino inquilino = ControladorInquilino.consultar(idInquilino);

        if (inquilino == null) {
            System.out.println("No existe un inquilino con el ID: " + idInquilino);
            return;
        }

        sc.nextLine();
        System.out.print("Esta accion eliminara tambien sus contratos. ¿Confirmar?(S/n): ");
        String respuesta = sc.nextLine().trim();

        if (!respuesta.equalsIgnoreCase("s")) {
            System.out.println("Operacion cancelada");
            return;
        }

        ControladorInquilino.eliminar(idInquilino);
    }
}
