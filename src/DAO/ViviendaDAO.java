package DAO;

import ConexionBD.ConexionBD;
import Modelo.Vivienda;

import java.sql.*;

/**
 * Clase encargada de realizar las operaciones CRUD sobre la tabla Vivienda.
 * Utiliza procedimientos almacenados para interactuar con la base de datos.
 *
 * @author Daniel
 * @version 1.0
 */
public class ViviendaDAO {

    /**
     * Inserta una nueva vivienda en la base de datos.
     *
     * @param vivienda Objeto Vivienda con los datos que se desean registrar.
     * @return 0 si la inserción se realiza correctamente,
     * -1 si ocurre un error general,
     * -2 si se incumple una restricción CHECK.
     */
    public static int insertarVivienda(Vivienda vivienda) {

        try (Connection connection = ConexionBD.getConnection()) {

            String sql = "{CALL sp_ins_vivienda(?,?,?,?,?,?,?,?,?)}";

            CallableStatement cs = connection.prepareCall(sql);

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

            cs.close();

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
            return -1;
        }
        return -1;
    }

    /**
     * Consulta una vivienda a partir de su identificador.
     *
     * @param idVivienda Identificador único de la vivienda.
     * @return Un objeto Vivienda con los datos recuperados de la base de datos
     * o null si la vivienda no existe o se produce un error.
     */
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

    /**
     * Elimina una vivienda de la base de datos.
     * También informa del número de contratos afectados por la eliminación.
     *
     * @param idVivienda Identificador de la vivienda que se desea eliminar.
     */
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
    /**
     * Actualiza los datos de una vivienda existente.
     *
     * @param vivienda Objeto Vivienda con los nuevos datos.
     * @return 0 si la actualización se realiza correctamente,
     *         -1 si ocurre un error general,
     *         -2 si se incumple una restricción CHECK.
     */
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
