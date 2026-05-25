package DAO;

import ConexionBD.ConexionBD;
import Modelo.Propietario;

import java.sql.*;

public class PropietarioDAO {

    // Funcion para insertar un desarrollador en la tabla Propietario
    public static void insertarPropietario(Propietario propietario) {

        // Inicio la conexion el try para que cuando termine cierre la conexion automaticamente
        try (Connection con = ConexionBD.getConnection()) {

            // Creacion de la sentencia sql
            String sql = "{CALL sp_ins_propietario(?, ?, ?, ?, ?, ?)}";

            // Inserto en CallableStatament la sentencia
            CallableStatement cs = con.prepareCall(sql);

            // Le asigno los valores
            cs.setString(1, propietario.getDNI());
            cs.setString(2, propietario.getNombre());
            cs.setString(3, propietario.getCorreo());
            cs.setString(4, propietario.getTelefono());
            cs.registerOutParameter(5, Types.INTEGER);
            cs.registerOutParameter(6, Types.INTEGER);

            // Ejecuto
            cs.execute();

            // Asigno a las varibles los parametros de salida
            int error = cs.getInt(5);
            int id = cs.getInt(6);

            // Compruebo que no haya error
            if (error == 0) {
                System.out.println("Propietario insertado exitosamente");
                System.out.println("ID: " + id);
            } else {
                System.out.println("Error al insertar Propietario");
            }

            // Cierro el CallableStatment
            cs.close();
        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
        }
    }

    public static void consultarPropietario(int id) {

        try (Connection con = ConexionBD.getConnection()) {

            String sql = "{CALL sp_get_propietario(?)}";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Propietario propietario = new Propietario(rs.getInt("id"), rs.getString("DNI"),
                        rs.getString("nombre"), rs.getString("correo_electronico"), rs.getString("telefono"));
                System.out.println(propietario);
            } else {
                System.out.println("No existe un propietario con el ID: " + id);
            }


        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
        }
    }

    public static void eliminarPropietario(int id) {

        try (Connection con = ConexionBD.getConnection()) {

            String sql = "{CALL sp_del_propietario(?,?,?)}";

            CallableStatement cs = con.prepareCall(sql);

            cs.setInt(1, id);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.registerOutParameter(3, Types.INTEGER);

            cs.execute();

            int error = cs.getInt(2);
            int filas = cs.getInt(3);

            cs.close();

            if (error == 0) {
                System.out.println("Propietario eliminado exitosamente");
                System.out.println("Viviendas afectadas: " + filas);
            } else {
                System.out.println("Error al eliminar Propietario");
            }

        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
        }
    }

    public static void actualizarPropietario(Propietario propietario) {

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
                System.out.println("Propietario actualizado exitosamente");
            } else {
                System.out.println("Error al actualizar Propietario");
            }

        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
        }
    }
}
