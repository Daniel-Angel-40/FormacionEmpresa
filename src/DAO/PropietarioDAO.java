package DAO;

import ConexionBD.ConexionBD;
import Modelo.Propietario;

import java.sql.*;
/**
 * Clase encargada de realizar las operaciones CRUD sobre la tabla Propietario.
 * Utiliza procedimientos almacenados para interactuar con la base de datos.
 *
 * @author Daniel
 * @version 1.0
 */
public class PropietarioDAO {

    /**
     * Inserta un propietario en la base de datos mediante el procedimiento
     * almacenado sp_ins_propietario.
     *
     * @param propietario Objeto que contiene los datos del propietario a insertar.
     * @return El ID generado para el propietario si la inserción es correcta;
     *         -1 en caso de error.
     */
    public static int insertarPropietario(Propietario propietario) {

        try (Connection con = ConexionBD.getConnection()) {

            String sql = "{CALL sp_ins_propietario(?, ?, ?, ?, ?, ?)}";

            CallableStatement cs = con.prepareCall(sql);

            cs.setString(1, propietario.getDNI());
            cs.setString(2, propietario.getNombre());
            cs.setString(3, propietario.getCorreo());
            cs.setString(4, propietario.getTelefono());
            cs.registerOutParameter(5, Types.INTEGER);
            cs.registerOutParameter(6, Types.INTEGER);


            cs.execute();

            int error = cs.getInt(5);
            int ultimoIdInsertado = cs.getInt(6);

            if (error == 0) {
                System.out.println("Propietario insertado exitosamente");
                System.out.println("ID: " + ultimoIdInsertado);
                return ultimoIdInsertado;
            } else {
                System.out.println("Error al insertar Propietario");
                return -1;
            }


        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
            return -1;
        }
    }

    /**
     * Consulta un propietario a partir de su identificador.
     *
     * @param idPropietario Identificador único del propietario.
     * @return Un objeto Propietario con los datos recuperados de la base de datos
     *         o null si no existe o se produce un error.
     */
    public static Propietario consultarPropietario(int idPropietario) {

        try (Connection con = ConexionBD.getConnection()) {

            String sql = "{CALL sp_get_propietario(?)}";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, idPropietario);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Propietario propietario = new Propietario(rs.getInt("id"), rs.getString("DNI"),
                        rs.getString("nombre"), rs.getString("correo_electronico"), rs.getString("telefono"));
                return propietario;
            } else {
                System.out.println("No existe un propietario con el ID: " + idPropietario);
                return null;
            }


        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
            return null;
        }
    }



    /**
     * Elimina un propietario de la base de datos mediante el procedimiento
     * almacenado sp_del_propietario.
     *
     * @param id Identificador del propietario que se desea eliminar.
     */
    public static void eliminarPropietario(int id) {

        try (Connection con = ConexionBD.getConnection()) {

            String sql = "{CALL sp_del_propietario(?,?,?,?)}";

            CallableStatement cs = con.prepareCall(sql);

            cs.setInt(1, id);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.registerOutParameter(3, Types.INTEGER);
            cs.registerOutParameter(4, Types.INTEGER);

            cs.execute();

            int error = cs.getInt(2);
            int viviendasAfectadas = cs.getInt(3);
            int contratosAfectados = cs.getInt(4);

            cs.close();

            if (error == 0) {
                System.out.println("Propietario eliminado exitosamente");
                System.out.println("Viviendas afectadas: " + viviendasAfectadas);
                System.out.println("Contratos afectados: " + contratosAfectados);
            } else {
                System.out.println("Error al eliminar Propietario");
            }

        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
        }
    }

    /**
     * Actualiza los datos de un propietario existente en la base de datos.
     *
     * @param propietario Objeto que contiene los nuevos datos del propietario.
     * @return 0 si la actualización se realiza correctamente,
     *         -1 si ocurre algún error.
     */
    public static int actualizarPropietario(Propietario propietario) {

        try(Connection con = ConexionBD.getConnection()) {

            String sql = "{CALL sp_upd_propietario(?,?,?,?,?,?)}";

            CallableStatement cs = con.prepareCall(sql);

            cs.setInt(1, propietario.getId());
            cs.setString(2, propietario.getDNI());
            cs.setString(3, propietario.getNombre());
            cs.setString(4, propietario.getCorreo());
            cs.setString(5, propietario.getTelefono());
            cs.registerOutParameter(6, Types.INTEGER);

            cs.execute();

            int error = cs.getInt(6);

            if (error == 0) {
                return 0;
            } else {
                return -1;
            }

        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
            return -1;
        }
    }
}
