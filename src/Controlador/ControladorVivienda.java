package Controlador;

import DAO.ViviendaDAO;
import Modelo.Vivienda;

public class ControladorVivienda {

    public static int insertar(String id, int id_propietario, String direccion, double alquiler, double superficie, String descripcion,
                               boolean mascota, String tipo) {
        Vivienda vivienda = new Vivienda(id, id_propietario, alquiler, direccion, superficie, descripcion, mascota, tipo);

        return ViviendaDAO.insertarVivienda(vivienda);
    }

    public static Vivienda consultar(String idVivienda) {

        return ViviendaDAO.consultarViviendas(idVivienda);
    }

    public static int modificar(String id, int id_propietario, String direccion, double alquiler, double superficie, String descripcion,
                                boolean mascota, String tipo) {

        Vivienda vivienda = new Vivienda(id, id_propietario, alquiler, direccion, superficie, descripcion, mascota, tipo);

        return ViviendaDAO.actualizarVivienda(vivienda);
    }

    public static void eliminar(String idVivienda) {

        ViviendaDAO.eliminarVivienda(idVivienda);
    }
}
