package DAO;

import ConexionBD.ConexionBD;
import Modelo.Inquilino;

import java.sql.*;

public class InquilinoDAO {

    public static void insertarInquilino(Inquilino inquilino) {

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
            int id = cs.getInt(7);

            if (error == 0) {
                System.out.println("Inquilino insertado correctamente");
                System.out.println("ID: " + id);
            } else {
                System.out.println("Error al insertar inquilino");
            }
            cs.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }

    public static void consultarInquilino(int id) {

        try (Connection con = ConexionBD.getConnection()) {

            String sql = "{CALL sp_get_inquilino(?)}";

            CallableStatement cs = con.prepareCall(sql);

            cs.setInt(1, id);

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                Inquilino i = new Inquilino(rs.getInt("id"), rs.getString("DNI"), rs.getString("nombre"),
                        rs.getString("correo_electronico"), rs.getString("telefono"), rs.getBoolean("tiene_mascota"));
                System.out.println(i);
            } else {
                System.out.println("Error al consultar inquilino");
            }
            cs.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }

    public static void eliminarInquilino(int id) {

        try(Connection con = ConexionBD.getConnection()) {

            String sql = "{CALL sp_del_inquilino(?,?,?)}";

            CallableStatement cs = con.prepareCall(sql);

            cs.setInt(1, id);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.registerOutParameter(3, Types.INTEGER);

            cs.execute();

            int error = cs.getInt(2);
            int filas = cs.getInt(3);

            if (error == 0) {
                System.out.println("Inquilino eliminado correctamente");
                System.out.println("Contratos afectados: " + filas);
            } else {
                System.out.println("Error al eliminar inquilino");
            }

            cs.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }

    public static void actualizarInquilino(Inquilino inquilino) {

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
                System.out.println("Inquilino actualizado correctamente");
            } else {
                System.out.println("Error al actualizar inquilino");
            }
            cs.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }
}
