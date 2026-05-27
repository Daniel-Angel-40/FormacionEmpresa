package Controlador;

import DAO.ContratoDAO;
import DAO.InquilinoDAO;
import Modelo.Contrato;

public class ControladorContrato {

    public static int insertar(String idVivienda, int idInquilino, double precio, String fechaInicio, String fechaFin) {

        Contrato c = new Contrato(idVivienda, idInquilino, precio, fechaFin,  fechaInicio);

        return ContratoDAO.insertarContrato(c);
    }

    public static Contrato consultar(int id){

        return ContratoDAO.consultarContrato(id);
    }

    public static int actualizar(int id, String fechaInicio, String fechaFin, double precio, String estado) {

        Contrato c = new Contrato(id, fechaInicio, fechaFin, precio, estado);

        return ContratoDAO.actualizarContrato(c);
    }

    public static void eliminar(int id){

        ContratoDAO.eliminarContrato(id);
    }
}
