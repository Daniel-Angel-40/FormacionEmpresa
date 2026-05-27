package Controlador;

import DAO.InquilinoDAO;
import Modelo.Inquilino;

public class ControladorInquilino {

    public static int insertar(String DNI, String nombre, String correo, String telefono, boolean mascota){

        Inquilino i = new Inquilino(DNI, nombre, correo, telefono, mascota);

        return InquilinoDAO.insertarInquilino(i);
    }

    public static Inquilino consultar(int id){

        return InquilinoDAO.consultarInquilino(id);
    }

    public static int actualizar(int id, String DNI, String nombre, String correo, String telefono, boolean mascota){

        Inquilino i = new Inquilino(id, DNI, nombre, correo, telefono, mascota);

        return InquilinoDAO.actualizarInquilino(i);
    }

    public static void eliminar(int id){

        InquilinoDAO.eliminarInquilino(id);
    }
}
