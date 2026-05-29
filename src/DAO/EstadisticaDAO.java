package DAO;

import ConexionBD.ConexionBD;
import Modelo.Contrato;
import Modelo.Vivienda;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EstadisticaDAO {

    public static ArrayList<Vivienda> viviendasLibres() {

        ArrayList<Vivienda> listaViviendas = new ArrayList<>();

        try (Connection con = ConexionBD.getConnection()) {

            String sql = "{CALL sp_get_viviendas_libres()}";

            CallableStatement cs = con.prepareCall(sql);

            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                Vivienda v = new Vivienda(rs.getString("id"), rs.getInt("id_propietario"),
                        rs.getDouble("alquiler_mensual"), rs.getString("direccion"),
                        rs.getDouble("superficie"), rs.getString("descripcion"),
                        rs.getBoolean("permite_mascota"), rs.getString("tipo"));

                listaViviendas.add(v);
            }
            cs.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
        return listaViviendas;
    }

    public static ArrayList<Vivienda> viviendasActivasPropietario(int idPropietario) {

        ArrayList<Vivienda> listaViviendas = new ArrayList<>();

        try (Connection con = ConexionBD.getConnection()) {

            String sql = "{CALL sp_get_viviendas_activas(?)}";

            CallableStatement cs = con.prepareCall(sql);

            cs.setInt(1, idPropietario);

            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                Vivienda v = new Vivienda(rs.getString("id"), rs.getInt("id_propietario"),
                        rs.getDouble("alquiler_mensual"), rs.getString("direccion"), rs.getDouble("superficie"),
                        rs.getString("descripcion"), rs.getBoolean("permite_mascota"), rs.getString("tipo"));

                listaViviendas.add(v);
            }
            cs.close();
        } catch (SQLException ex) {
            System.out.println("Error SQL: " + ex.getMessage());

        }
        return listaViviendas;
    }

    public static ArrayList<Contrato> historicoInquilino(int idInquilino) {

        ArrayList<Contrato> listaContratos = new ArrayList<>();

        try (Connection con = ConexionBD.getConnection()) {

            String sql = "{CALL sp_get_historico_inquilino(?)}";

            CallableStatement cs = con.prepareCall(sql);

            cs.setInt(1, idInquilino);

            ResultSet rs = cs.executeQuery();

            while (rs.next()) {

                Contrato c = new Contrato(rs.getInt("id"), rs.getString("id_vivienda"), rs.getInt("id_inquilino"),
                        rs.getDouble("precio"), rs.getString("fecha_fin"),
                        rs.getString("fecha_inicio"), rs.getString("estado"));

                listaContratos.add(c);
            }

            cs.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }

        return listaContratos;
    }

    public static void viviendasLibresJson() {

        try (Connection con = ConexionBD.getConnection()) {

            String sql = "{CALL sp_get_viviendas_libres_JSON()}";

            CallableStatement cs = con.prepareCall(sql);

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                String json = rs.getString("resultado");

                String ruta = System.getProperty("user.home") + "/Descargas/";

                FileWriter fw = new FileWriter(ruta + "viviendasLibres.json");
                fw.write(json);
                fw.close();
            }

            cs.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void viviendasActivasPropietarioJson(int idPropietario) {

        try (Connection con = ConexionBD.getConnection()) {

            String sql = "{CALL sp_get_viviendas_activas_propietario_JSON(?)}";

            CallableStatement cs = con.prepareCall(sql);

            cs.setInt(1, idPropietario);

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                String json = rs.getString("resultado");

                String ruta = System.getProperty("user.home") + "/Descargas/";

                FileWriter fw = new FileWriter(ruta + "viviendasActivasPropietario.json");
                fw.write(json);
                fw.close();
            }

            cs.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void historicoInquilinoJson(int idInquilino) {


        try (Connection con = ConexionBD.getConnection()) {

            String sql = "{CALL sp_get_historico_inquilino_JSON(?)}";

            CallableStatement cs = con.prepareCall(sql);

            cs.setInt(1, idInquilino);

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                String json = rs.getString("resultado");

                String ruta = System.getProperty("user.home") + "/Descargas/";

                FileWriter fw = new FileWriter(ruta + "historicoContratosInquilino.json");
                fw.write(json);
                fw.close();
            }

            cs.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void viviendasLibresCsv() {

        String ruta = System.getProperty("user.home") + "/Descargas/";

        try (PrintWriter pw = new PrintWriter(ruta + "viviendasLibresCsv.csv")) {
            var lista = EstadisticaDAO.viviendasLibres();
            pw.println("id, idPropietario, direccion, alquiler_mensual, superficie, descripcion, permiteMascota, tipo");

            for (Vivienda v : lista) {
                pw.println("\"" + v.getId() + "\"," +
                        "\"" + v.getPropietario() + "\"," +
                        "\"" + v.getDireccion() + "\"," +
                        v.getAlquiler_mensual() + "," +
                        v.getSuperficie() + "," +
                        "\"" + v.getDescripcion() + "\"," +
                        "\"" + v.isPermite_mascota() + "\"," +
                        v.getTipo());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void viviendasActivasPropietarioCsv(int idPropietario){

        String ruta = System.getProperty("user.home") + "/Descargas/";

        try(PrintWriter pw = new PrintWriter(ruta + "viviendasActivasPropietarioCsv.csv")) {

            var lista = EstadisticaDAO.viviendasActivasPropietario(idPropietario);

            pw.println("id, idPropietario, direccion, alquiler_mensual, superficie, descripcion, permiteMascota, tipo");

            for (Vivienda v : lista) {
                pw.println("\"" + v.getId() + "\"," +
                        "\"" + v.getPropietario() + "\"," +
                        "\"" + v.getDireccion() + "\"," +
                        v.getAlquiler_mensual() + "," +
                        v.getSuperficie() + "," +
                        "\"" + v.getDescripcion() + "\"," +
                        "\"" + v.isPermite_mascota() + "\"," +
                        v.getTipo());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
