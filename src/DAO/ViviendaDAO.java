package DAO;

import ConexionBD.ConexionBD;
import Modelo.Vivienda;

import java.sql.*;

public class ViviendaDAO {

    // Funcion para insertar una vivienda en la tabla Vivienda
    public static void insertarVivienda(Vivienda vivienda) {

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
                    System.out.println("Vivienda insertada exitosamente");
                    break;
                case 1:
                    System.out.println("Error al insertar la vivienda");
                    break;
                case 2:
                    System.out.println("El tipo de casa de ser apartamento/atico/casa");
                    System.out.println("No se ha insertado la vivienda");
                    break;
            }

            // Cierro el CallableStatment
            cs.close();

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }

    public static void consultarViviendas(String id) {

        try (Connection con = ConexionBD.getConnection()) {

            String sql = "{CALL sp_get_vivienda(?)}";

            CallableStatement cs = con.prepareCall(sql);

            cs.setString(1, id);

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {

                Vivienda v = new Vivienda(rs.getString("id"), rs.getInt("id_propietario"),
                        rs.getDouble("alquiler_mensual"), rs.getString("direccion"),
                        rs.getDouble("superficie"), rs.getString("descripcion"),
                        rs.getBoolean("permite_mascota"), rs.getString("tipo"));

                System.out.println(v);
            } else {
                System.out.println("No existe una vivienda con el ID: " + id);
            }

            cs.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }

    public static void eliminarVivienda(String id) {

        try (Connection con = ConexionBD.getConnection()) {

            String sql = "{CALL sp_del_vivienda(?, ?, ?)}";

            CallableStatement cs = con.prepareCall(sql);

            cs.setString(1, id);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.registerOutParameter(3, Types.INTEGER);

            cs.execute();

            int error = cs.getInt(2);
            int filas = cs.getInt(3);

            if (error == 0) {
                System.out.println("Vivienda eliminada exitosamente");
                System.out.println("Contratos afectados: " + filas);
            } else {
                System.out.println("Error al eliminar la vivienda");
                System.out.println("No se ha eliminado la vivienda");
            }

            cs.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }
}
