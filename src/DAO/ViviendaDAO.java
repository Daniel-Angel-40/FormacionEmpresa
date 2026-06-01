package DAO;

import ConexionBD.ConexionBD;
import Modelo.Vivienda;

import java.sql.*;

public class ViviendaDAO {

    // Funcion para insertar una vivienda en la tabla Vivienda
    public static int insertarVivienda(Vivienda vivienda) {

        // Inicio la conexion el try para que cuando termine cierre la conexion automaticamente
        try (Connection connection = ConexionBD.getConnection()) {

            // Creacion de la sentencia sql
            String sql = "{CALL sp_ins_vivienda(?,?,?,?,?,?,?,?,?)}";

            // Inserto en CallableStatament la sentencia
            CallableStatement cs = connection.prepareCall(sql);

            // Le asigno los valores
            cs.setString(1, vivienda.getId());
            cs.setInt(2, vivienda.getPropietario());
            cs.setString(3, vivienda.getDireccion());
            cs.setDouble(4, vivienda.getAlquiler_mensual());
            cs.setDouble(5, vivienda.getSuperficie());
            cs.setString(6, vivienda.getDescripcion());
            cs.setBoolean(7, vivienda.isPermite_mascota());
            cs.setString(8, vivienda.getTipo());
            cs.registerOutParameter(9, Types.INTEGER);

            // Ejecuto
            cs.execute();

            // Asigno a la varible el parametro de salida
            int error = cs.getInt(9);

            // En caso de que el error sea 1 es un error generico y si es 2 es un error de tipo CHECK
            switch (error) {
                case 0:
                    return 0;

                case 1:
                    return -1;

                case 2:
                    return -2;
            }

            // Cierro el CallableStatment
            cs.close();

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
            return -1;
        }
        return -1;
    }

    public static Vivienda consultarViviendas(String idVivienda) {

        try (Connection con = ConexionBD.getConnection()) {

            String sql = "{CALL sp_get_vivienda(?)}";

            CallableStatement cs = con.prepareCall(sql);

            cs.setString(1, idVivienda);

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {

                Vivienda vivienda = new Vivienda(rs.getString("id"), rs.getInt("id_propietario"),
                        rs.getDouble("alquiler_mensual"), rs.getString("direccion"),
                        rs.getDouble("superficie"), rs.getString("descripcion"),
                        rs.getBoolean("permite_mascota"), rs.getString("tipo"));

                return vivienda;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
            return null;
        }
    }

    public static void eliminarVivienda(String idVivienda) {

        try (Connection con = ConexionBD.getConnection()) {

            String sql = "{CALL sp_del_vivienda(?, ?, ?)}";

            CallableStatement cs = con.prepareCall(sql);

            cs.setString(1, idVivienda);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.registerOutParameter(3, Types.INTEGER);

            cs.execute();

            int error = cs.getInt(2);
            int contratosAfectados = cs.getInt(3);

            if (error == 0) {
                System.out.println("Vivienda eliminada exitosamente");
                System.out.println("Contratos afectados: " + contratosAfectados);
            } else {
                System.out.println("Error al eliminar la vivienda");
            }

            cs.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }

    public static int actualizarVivienda(Vivienda vivienda) {

        try (Connection con = ConexionBD.getConnection()) {

            String sql = "{CALL sp_upd_vivienda(?,?,?,?,?,?,?,?,?)}";

            CallableStatement cs = con.prepareCall(sql);

            cs.setString(1, vivienda.getId());
            cs.setInt(2, vivienda.getPropietario());
            cs.setString(3, vivienda.getDireccion());
            cs.setDouble(4, vivienda.getAlquiler_mensual());
            cs.setDouble(5, vivienda.getSuperficie());
            cs.setString(6, vivienda.getDescripcion());
            cs.setBoolean(7, vivienda.isPermite_mascota());
            cs.setString(8, vivienda.getTipo());
            cs.registerOutParameter(9, Types.INTEGER);

            cs.execute();

            int error = cs.getInt(9);

            switch (error) {
                case 0:
                    return 0;
                case 1:
                    return -1;
                case 2:
                    return -2;
            }

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
            return -1;
        }
        return -1;
    }
}
