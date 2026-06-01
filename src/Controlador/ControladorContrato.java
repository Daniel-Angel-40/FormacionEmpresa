package Controlador;

import DAO.ContratoDAO;
import DAO.InquilinoDAO;
import Modelo.Contrato;

public class ControladorContrato {

    public static int insertar(String idVivienda, int idInquilino, double precio, String fechaInicio, String fechaFin) {

        Contrato contrato = new Contrato(idVivienda, idInquilino, precio, fechaFin, fechaInicio);

        return ContratoDAO.insertarContrato(contrato);
    }

    public static Contrato consultar(int idContrato) {

        return ContratoDAO.consultarContrato(idContrato);
    }

    public static int actualizar(int id, String fechaInicio, String fechaFin, double precio, String estado) {

        Contrato contrato = new Contrato(id, fechaInicio, fechaFin, precio, estado);

        return ContratoDAO.actualizarContrato(contrato);
    }

    public static void eliminar(int idContrato) {

        ContratoDAO.eliminarContrato(idContrato);
    }

    public static int estado(int idContrato, int estado) {

        return ContratoDAO.actualizarestadoContrato(idContrato, estado);
    }

    public static void actualizacionAutomatica() {
        ContratoDAO.actualizacionAutomatica();
    }
}
