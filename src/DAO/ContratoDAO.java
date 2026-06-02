package DAO;

import ConexionBD.ConexionBD;
import Modelo.Contrato;

import java.sql.*;

/**
 * Clase encargada de gestionar las operaciones CRUD sobre la tabla Contrato.
 * Utiliza procedimientos almacenados para interactuar con la base de datos.
 *
 * @author Daniel
 * @version 1.0
 */
public class ContratoDAO {
    /**
     * Inserta un nuevo contrato en la base de datos.
     *
     * @param contrato Objeto que contiene los datos del contrato a insertar.
     * @return ID del contrato insertado si la operación es correcta,
     * -1 si ocurre un error general.
     * -2 si se incumple alguna restricción CHECK.
     */
    public static int insertarContrato(Contrato contrato) {

        try (Connection con = ConexionBD.getConnection()) {

            String sql = "{CALL sp_ins_contrato(?,?,?,?,?,?,?)}";

            CallableStatement cs = con.prepareCall(sql);

            cs.setString(1, contrato.getId_vivienda());
            cs.setInt(2, contrato.getId_inquilino());
            cs.setString(3, contrato.getFecha_inicio());
            cs.setString(4, contrato.getFecha_fin());
            cs.setDouble(5, contrato.getPrecio());
            cs.registerOutParameter(6, Types.INTEGER);
            cs.registerOutParameter(7, Types.INTEGER);

            cs.execute();

            int error = cs.getInt(6);
            int ultimoIdInsertado = cs.getInt(7);

            switch (error) {
                case 0:
                    return ultimoIdInsertado;

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
     * Consulta un contrato a partir de su identificador.
     *
     * @param idContrato Identificador único del contrato.
     * @return Un objeto Contrato con los datos recuperados de la base de datos
     * o null si no existe o se produce un error.
     */
    public static Contrato consultarContrato(int idContrato) {

        try (Connection con = ConexionBD.getConnection()) {

            String sql = "{CALL sp_get_contrato(?)}";

            CallableStatement cs = con.prepareCall(sql);

            cs.setInt(1, idContrato);

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                Contrato contrato = new Contrato(rs.getInt("id"), rs.getString("id_vivienda"),
                        rs.getInt("id_inquilino"), rs.getDouble("precio"), rs.getString("fecha_fin"),
                        rs.getString("fecha_inicio"), rs.getString("estado"));
                return contrato;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
            return null;
        }
    }

    /**
     * Elimina un contrato de la base de datos.
     *
     * @param idContrato Identificador del contrato que se desea eliminar.
     */
    public static void eliminarContrato(int idContrato) {

        try (Connection con = ConexionBD.getConnection()) {

            String sql = "{CALL sp_del_contrato(?,?)}";

            CallableStatement cs = con.prepareCall(sql);

            cs.setInt(1, idContrato);
            cs.registerOutParameter(2, Types.INTEGER);

            cs.execute();

            int error = cs.getInt(2);

            if (error == 0) {
                System.out.println("Contrato eliminado exitosamente");
            } else {
                System.out.println("Error al eliminar contrato");
            }
            cs.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }

    /**
     * Actualiza los datos de un contrato existente.
     *
     * @param contrato Objeto que contiene los nuevos datos del contrato.
     * @return 0 si la actualización se realiza correctamente.
     * -1 si ocurre un error general.
     * -2 si se incumple alguna restricción CHECK.
     */
    public static int actualizarContrato(Contrato contrato) {

        try (Connection con = ConexionBD.getConnection()) {

            String sql = "{CALL sp_upd_contrato(?,?,?,?,?,?)}";

            CallableStatement cs = con.prepareCall(sql);

            cs.setInt(1, contrato.getId());
            cs.setString(2, contrato.getFecha_inicio());
            cs.setString(3, contrato.getFecha_fin());
            cs.setDouble(4, contrato.getPrecio());
            cs.setString(5, contrato.getEstado());
            cs.registerOutParameter(6, Types.INTEGER);

            cs.execute();

            int error = cs.getInt(6);

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
     * Modifica el estado de un contrato.
     *
     * @param idContrato Identificador del contrato cuyo estado se desea actualizar.
     * @param estado     Nuevo estado que se asignará al contrato.
     * @return 0 si la actualización se realiza correctamente.
     * -1 si ocurre algún error durante la operación.
     *
     */
    public static int actualizarestadoContrato(int idContrato, int estado) {

        try (Connection con = ConexionBD.getConnection()) {

            String sql = "{CALL sp_actualizar_estado_contrato(?,?,?)}";

            CallableStatement cs = con.prepareCall(sql);

            cs.setInt(1, idContrato);
            cs.setInt(2, estado);
            cs.registerOutParameter(3, Types.INTEGER);

            cs.execute();

            int error = cs.getInt(3);

            switch (error) {
                case 0:
                    return 0;

                case 1:
                    return -1;
            }

            cs.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
            return -1;
        }
        return -1;
    }

    /**
     * Ejecuta la actualización automática de contratos.
     * Este metodo llama a un procedimiento almacenado
     * y actualizar automáticamente el estado de los contratos según las
     * reglas definidas dentro del procedimiento.
     */
    public static void actualizacionAutomatica() {

        try (Connection con = ConexionBD.getConnection()) {

            String sql = "{CALL sp_actualizacion_automatica()}";

            CallableStatement cs = con.prepareCall(sql);

            cs.execute();

            cs.close();

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }
}
