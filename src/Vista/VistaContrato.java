package Vista;

import Controlador.ControladorContrato;
import Modelo.Contrato;

import java.util.Scanner;

public class VistaContrato {

    private static final Scanner sc = new Scanner(System.in);

    public static void menuContrato() {

        int opcion;

        do {
            System.out.println("\n╔══════════════════════════════════╗");
            System.out.println("║        GESTIÓN CONTRATOS         ║");
            System.out.println("╠══════════════════════════════════╣");
            System.out.println("║  1. Añadir contrato              ║");
            System.out.println("║  2. Consultar contrato           ║");
            System.out.println("║  3. Modificar contrato           ║");
            System.out.println("║  4. Eliminar contrato            ║");
            System.out.println("║  5. Cambiar estado               ║");
            System.out.println("║  0. Volver                       ║");
            System.out.println("╚══════════════════════════════════╝");
            System.out.print("Opción: ");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    insetarContrato();
                    break;
                case 2:
                    consultarContrato();
                    break;
                case 3:
                    actualizarContrato();
                    break;
                case 4:
                    eliminarContrato();
                    break;
                case 5:
                    cambiarEstadoContrato();
                    break;
                case 0:
                    System.out.println("Volviendo al menu principal...");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }

        } while (opcion != 0);
    }

    private static void insetarContrato() {

        sc.nextLine();
        System.out.println("\n── Añadir contrato ──");
        System.out.print("ID vivienda: ");
        String idVivienda = sc.nextLine().trim();

        System.out.println("ID inquilino: ");
        int idInquilino = sc.nextInt();

        System.out.println("Precio (€/mes): ");
        double precio = sc.nextDouble();

        sc.nextLine();
        System.out.println("Fecha inicio (AAAA-MM-DD): ");
        String fechaInicio = sc.nextLine().trim();

        System.out.println("Fecha fin (AAAA-MM-DD): ");
        String fechaFin = sc.nextLine().trim();

        int ultimoIdInsertado = ControladorContrato.insertar(idVivienda, idInquilino, precio, fechaInicio, fechaFin);

        if (ultimoIdInsertado != -1 || ultimoIdInsertado != -2) {
            System.out.println("Contrato insertado con exito");
            System.out.println("ID asignado: " + ultimoIdInsertado);
        } else if (ultimoIdInsertado == -1) {
            System.out.println("Error al insertar el contrato");
        } else if (ultimoIdInsertado == -2) {
            System.out.println("Datos invalidos del contrato");
        }
    }

    private static void consultarContrato() {

        System.out.println("\n── Consultar contrato ──");
        System.out.print("ID: ");

        int idContrato = sc.nextInt();

        Contrato contrato = ControladorContrato.consultar(idContrato);

        if (contrato != null) {
            System.out.println(contrato);
        } else {
            System.out.println("No existe el contrato con el ID: " + idContrato);
        }
    }

    private static void actualizarContrato() {

        System.out.println("\n── Modificar contrato ──");
        System.out.print("ID del contrato a modificar: ");
        int idContrato = sc.nextInt();

        Contrato actual = ControladorContrato.consultar(idContrato);

        if (actual == null) {
            System.out.println("No existe el contrato con el ID: " + idContrato);
            return;
        }

        sc.nextLine();
        System.out.print("Fecha Inicio[" + actual.getFecha_inicio() + "]: ");
        String fechaInicio = sc.nextLine();

        System.out.print("Fecha Fin[" + actual.getFecha_fin() + "]: ");
        String fechaFin = sc.nextLine();

        System.out.print("Precio [" + actual.getPrecio() + "]: ");
        double precio = sc.nextDouble();

        sc.nextLine();
        System.out.print("Estado [" + actual.getEstado() + "]: ");
        String estado = sc.nextLine().trim().toLowerCase();

        int resultado = ControladorContrato.actualizar(idContrato, fechaInicio, fechaFin, precio, estado);

        switch (resultado) {
            case 0:
                System.out.println("Contrato actualizado correctamente");
                break;
            case -1:
                System.out.println("Error al actualizar el contrato");
                break;
            case -2:
                System.out.println("Datos invalidos");
                break;
        }
    }

    private static void eliminarContrato() {

        System.out.println("\n── Eliminar contrato ──");
        System.out.print("ID del contrato a eliminar: ");
        int idContrato = sc.nextInt();

        Contrato contrato = ControladorContrato.consultar(idContrato);

        if (contrato == null) {
            System.out.println("No existe el contrato con el ID: " + idContrato);
            return;
        }

        sc.nextLine();
        System.out.println("¿Estas seguro de eliminar el contrato?(S/n): ");
        boolean respuesta = sc.nextLine().trim().equalsIgnoreCase("s");

        if (!respuesta) {
            System.out.println("Operacion cancelada");
            return;
        }

        ControladorContrato.eliminar(idContrato);
    }

    private static void cambiarEstadoContrato() {

        System.out.println("\n── Cambiar estado del contrato ──");
        System.out.print("ID del contrato: ");

        int idContrato = sc.nextInt();

        Contrato contrato = ControladorContrato.consultar(idContrato);

        if (contrato == null) {
            System.out.println("No existe el contrato con el ID: " + idContrato);
            return;
        }

        System.out.println("Estado actual: " + contrato.getEstado());

        System.out.println("Elige una opcion: \n1.Pendiente\n2.Activo\n3.Vencido");
        System.out.print("Nuevo estado: ");
        int nuevoEstado = sc.nextInt();
        if (nuevoEstado != 1 || nuevoEstado != 2 || nuevoEstado != 3) {
            System.out.println("Opcion no valida");
            return;
        }

        int resultado = ControladorContrato.estado(idContrato, nuevoEstado);

        switch (resultado) {
            case 0:
                System.out.println("Estado actualizado correctamente");
                break;
            case -1:
                System.out.println("Error al actualizar el estado");
                break;
        }
    }
}
