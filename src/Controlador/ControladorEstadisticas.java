package Controlador;

import DAO.EstadisticaDAO;
import Modelo.Contrato;
import Modelo.Vivienda;

import java.util.ArrayList;

public class ControladorEstadisticas {

    public static ArrayList<Vivienda> viviendasLibres(){
        return EstadisticaDAO.viviendasLibres();
    }

    public static ArrayList<Vivienda> viviendasActivasPropietario(int idPropietario){
        return EstadisticaDAO.viviendasActivasPropietario(idPropietario);
    }

    public static ArrayList<Contrato> historicoInquilino(int idInquilino){
        return EstadisticaDAO.historicoInquilino(idInquilino);
    }

    public static void viviendasLibresJson(){
        EstadisticaDAO.viviendasLibresJson();
    }

    public static void viviendasActivasPropietarioJson(int idPropietario){
        EstadisticaDAO.viviendasActivasPropietarioJson(idPropietario);
    }
}
