package Controlador;

import DAO.PropietarioDAO;
import Modelo.Propietario;

public class ControladorPropietario {


    public static int insertar(String DNI, String nombre, String correo, String telefono) {

        Propietario propietario = new Propietario(DNI, nombre, correo, telefono);

        int ultimoIdInsertado = PropietarioDAO.insertarPropietario(propietario);

        return ultimoIdInsertado;
    }

    public static Propietario consultar(int idPropietario) {
        return PropietarioDAO.consultarPropietario(idPropietario);
    }

    public static int actualizar(int id, String DNI, String nombre, String correo, String telefono) {

        Propietario propietario = new Propietario(id, DNI, nombre, correo, telefono);

        return PropietarioDAO.actualizarPropietario(propietario);
    }

    public static void eliminar(int idPropietario) {

        PropietarioDAO.eliminarPropietario(idPropietario);
    }
}
