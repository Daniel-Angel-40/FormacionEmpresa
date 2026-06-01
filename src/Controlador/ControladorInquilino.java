package Controlador;

import DAO.InquilinoDAO;
import Modelo.Inquilino;

public class ControladorInquilino {

    public static int insertar(String DNI, String nombre, String correo, String telefono, boolean mascota) {

        Inquilino inquilino = new Inquilino(DNI, nombre, correo, telefono, mascota);

        return InquilinoDAO.insertarInquilino(inquilino);
    }

    public static Inquilino consultar(int idInquilino) {

        return InquilinoDAO.consultarInquilino(idInquilino);
    }

    public static int actualizar(int id, String DNI, String nombre, String correo, String telefono, boolean mascota) {

        Inquilino inquilino = new Inquilino(id, DNI, nombre, correo, telefono, mascota);

        return InquilinoDAO.actualizarInquilino(inquilino);
    }

    public static void eliminar(int idInquilino) {

        InquilinoDAO.eliminarInquilino(idInquilino);
    }
}
