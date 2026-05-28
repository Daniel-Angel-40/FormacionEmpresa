package Vista;

import Controlador.ControladorPropietario;
import Modelo.Propietario;

import java.util.Scanner;

public class VistaPropietario {

    // Pongo aqui el scaner para que me sirva en toda la clase
    private static final Scanner sc = new Scanner(System.in);

    public static void menuPropietario() {

        int opcion;

        do {
            System.out.println("\n╔══════════════════════════════════╗");
            System.out.println("║       GESTIÓN PROPIETARIOS       ║");
            System.out.println("╠══════════════════════════════════╣");
            System.out.println("║  1. Añadir propietario           ║");
            System.out.println("║  2. Consultar propietario        ║");
            System.out.println("║  3. Modificar propietario        ║");
            System.out.println("║  4. Eliminar propietario         ║");
            System.out.println("║  0. Volver                       ║");
            System.out.println("╚══════════════════════════════════╝");
            System.out.print("Opción: ");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    insertarPropietario();
                    break;
                case 2:
                    consultarPropietario();
                    break;
                case 3:
                    actualizarPropietario();
                    break;
                case 4:
                    eliminarPropietario();
                    break;
                case 0:
                    System.out.println("Volviendo al menu principal...");
                    break;
                    default:
                        System.out.println("Opcion no valida");
                        break;
            }

        } while (opcion != 0);
    }

    private static void insertarPropietario() {

        System.out.println("\n── Añadir propietario ──");

        System.out.print("DNI: ");
        String dni = sc.next().trim();

        System.out.print("Nombre: ");
        String nombre = sc.next().trim();

        System.out.print("Correo: ");
        String correo = sc.next().trim();

        System.out.print("Telefono: ");
        String telefono = sc.next().trim();

        int id = ControladorPropietario.insertar(dni, nombre, correo, telefono);

        if (id != -1) {
            System.out.println("Propietario insertado exitosamente");
            System.out.println("ID: " + id);
        } else {
            System.out.println("Error al insertar Propietario");
        }
    }

    private static void consultarPropietario() {

        System.out.println("\n── Consultar propietario ──");
        System.out.print("ID: ");
        int id = sc.nextInt();

        Propietario p =  ControladorPropietario.consultar(id);

        if (p != null) {
            System.out.println(p);
        } else {
            System.out.println("No existe Propietario con el ID: " + id);
        }

    }

    private static void actualizarPropietario() {

        System.out.println("\n── Modificar propietario ──");
        System.out.print("ID del propietario a modificar: ");
        int id = sc.nextInt();

        Propietario actual = ControladorPropietario.consultar(id);

        if (actual == null) {
            System.out.println("No existe un propietario con el ID: " + id);
            return;
        }


        System.out.print("DNI [" + actual.getDNI() + "]: " );
        String DNI = sc.nextLine().trim();
        if (DNI.isBlank()){
            DNI = actual.getDNI();
        }

        System.out.print("Nombre [" + actual.getNombre() + "]: " );
        String nombre = sc.nextLine().trim();
        if (nombre.isBlank()){
            nombre = actual.getNombre();
        }
        System.out.print("Correo [" + actual.getCorreo() + "]: " );
        String correo = sc.nextLine().trim();
        if (correo.isBlank()){
            correo = actual.getCorreo();
        }

        System.out.print("Telefono [" + actual.getTelefono() + "]: " );
        String telefono = sc.nextLine().trim();
        if (telefono.isBlank()){
            telefono = actual.getTelefono();
        }

        int respuesta = ControladorPropietario.actualizar(id, DNI, nombre, correo, telefono);

        if (respuesta == 0) {
            System.out.println("Propietario modificado exitosamente");
        } else {
            System.out.println("Error al modificar propietario");
        }
    }

    private static void eliminarPropietario() {

        System.out.println("\n── Eliminar propietario ──");
        System.out.print("ID del propietario eliminar: ");
        int id = sc.nextInt();

        Propietario p = ControladorPropietario.consultar(id);
        if (p == null) {
            return;
        }

        System.out.print("Esta accion eliminara tambien sus viviendas y contratos asocidaso. ¿Confirmar?(S/n): ");
        String confirmar = sc.next().trim();

        if (!confirmar.equalsIgnoreCase("s")) {
            System.out.println("Operacion cancelada");
            return;
        }

        ControladorPropietario.eliminar(id);
    }

}
