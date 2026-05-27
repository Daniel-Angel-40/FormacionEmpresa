package Controlador;

import DAO.PropietarioDAO;
import Modelo.Propietario;

public class ControladorPropietario {


    public static int insertar(String DNI,String nombre,String correo,String telefono) {

        Propietario p = new Propietario(DNI,nombre,correo,telefono);

        int id = PropietarioDAO.insertarPropietario(p);

        return id;
    }

    public static Propietario consultar(int id){
        return PropietarioDAO.consultarPropietario(id);
    }

    public static int actualizar(int id, String DNI,String nombre,String correo,String telefono){

        Propietario p = new Propietario(id,DNI,nombre,correo,telefono);

        return PropietarioDAO.actualizarPropietario(p);
    }

    public static void eliminar(int id){

        PropietarioDAO.eliminarPropietario(id);
    }
}
