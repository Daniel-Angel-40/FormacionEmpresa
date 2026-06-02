package DAO;

import ConexionBD.ConexionBD;
import Modelo.Inquilino;

import java.sql.*;
/**
 * Clase encargada de gestionar las operaciones CRUD sobre la tabla Inquilino.
 * Utiliza procedimientos almacenados para interactuar con la base de datos.
 *
 * @author Daniel
 * @version 1.0
 */
public class InquilinoDAO {

    /**
     * Inserta un nuevo inquilino en la base de datos.
     *
     * @param inquilino Objeto que contiene los datos del inquilino a insertar.
     * @return El identificador generado para el inquilino si la operación se
     *         realiza correctamente; -1 en caso de error.
     */
    public static int insertarInquilino(Inquilino inquilino) {

        try (Connection con = ConexionBD.getConnection()) {

            String sql = "{CALL sp_ins_inquilino(?,?,?,?,?,?,?)}";

            CallableStatement cs = con.prepareCall(sql);

            cs.setString(1, inquilino.getDNI());
            cs.setString(2, inquilino.getNombre());
            cs.setString(3, inquilino.getCorreo());
            cs.setString(4, inquilino.getTelefono());
            cs.setBoolean(5, inquilino.isTiene_mascota());
            cs.registerOutParameter(6, Types.INTEGER);
            cs.registerOutParameter(7, Types.INTEGER);

            cs.execute();

            int error = cs.getInt(6);
            int ultimoIdInsertado = cs.getInt(7);

            if (error == 0) {
                return ultimoIdInsertado;
            } else {
                return -1;
            }

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
            return -1;
        }
    }

    /**
     * Consulta un inquilino a partir de su identificador.
     *
     * @param idInquilino Identificador único del inquilino.
     * @return Un objeto Inquilino con los datos recuperados de la base de datos
     *         o null si no existe o se produce un error.
     */
    public static Inquilino consultarInquilino(int idInquilino) {

        try (Connection con = ConexionBD.getConnection()) {

            String sql = "{CALL sp_get_inquilino(?)}";

            CallableStatement cs = con.prepareCall(sql);

            cs.setInt(1, idInquilino);

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {

                Inquilino inquilino = new Inquilino(rs.getInt("id"), rs.getString("DNI"), rs.getString("nombre"),
                        rs.getString("correo_electronico"), rs.getString("telefono"), rs.getBoolean("tiene_mascota"));

                return inquilino;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
            return null;
        }
    }

    /**
     * Elimina un inquilino de la base de datos.
     * También muestra el número de contratos afectados por la eliminación.
     *
     * @param idInquilino Identificador del inquilino que se desea eliminar.
     */
    public static void eliminarInquilino(int idInquilino) {

        try(Connection con = ConexionBD.getConnection()) {

            String sql = "{CALL sp_del_inquilino(?,?,?)}";

            CallableStatement cs = con.prepareCall(sql);

            cs.setInt(1, idInquilino);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.registerOutParameter(3, Types.INTEGER);

            cs.execute();

            int error = cs.getInt(2);
            int contratosAfectados = cs.getInt(3);

            if (error == 0) {
                System.out.println("Inquilino eliminado correctamente");
                System.out.println("Contratos afectados: " + contratosAfectados);
            } else {
                System.out.println("Error al eliminar inquilino");
            }

            cs.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }

    /**
     * Actualiza los datos de un inquilino existente en la base de datos.
     *
     * @param inquilino Objeto que contiene los nuevos datos del inquilino.
     * @return 0 si la actualización se realiza correctamente;
     *         -1 si ocurre algún error.
     */
    public static int actualizarInquilino(Inquilino inquilino) {

        try(Connection con = ConexionBD.getConnection()) {

            String sql = "{CALL sp_upd_inquilino(?,?,?,?,?,?,?)}";

            CallableStatement cs = con.prepareCall(sql);

            cs.setInt(1, inquilino.getId());
            cs.setString(2, inquilino.getDNI());
            cs.setString(3, inquilino.getNombre());
            cs.setString(4, inquilino.getCorreo());
            cs.setString(5, inquilino.getTelefono());
            cs.setBoolean(6, inquilino.isTiene_mascota());
            cs.registerOutParameter(7, Types.INTEGER);

            cs.execute();

            int error = cs.getInt(7);

            if(error == 0) {
                return 0;
            } else {
               return -1;
            }

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
            return -1;
        }
    }
}
