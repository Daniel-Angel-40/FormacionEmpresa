package Controlador;

import DAO.ViviendaDAO;
import Modelo.Vivienda;

public class ControladorVivienda {

    public static int insertar(String id, int id_propietario, String direccion, double alquiler, double superficie, String descripcion,
                               boolean mascota, String tipo){
        Vivienda v = new Vivienda(id, id_propietario, alquiler, direccion, superficie, descripcion, mascota, tipo);

        return ViviendaDAO.insertarVivienda(v);
    }

    public static Vivienda consultar(String id){

        return ViviendaDAO.consultarViviendas(id);
    }

    public static int modificar(String id, int id_propietario, String direccion, double alquiler, double superficie, String descripcion,
                                boolean mascota, String tipo){

        Vivienda v = new Vivienda(id, id_propietario, alquiler, direccion, superficie, descripcion, mascota, tipo);

        return ViviendaDAO.actualizarVivienda(v);
    }

    public static void  eliminar(String id){

        ViviendaDAO.eliminarVivienda(id);
    }
}
