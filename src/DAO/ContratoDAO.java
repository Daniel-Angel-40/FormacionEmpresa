package DAO;

import ConexionBD.ConexionBD;
import Modelo.Contrato;

import java.sql.*;

public class ContratoDAO {

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
            int id = cs.getInt(7);

            switch (error) {
                case 0:
                    return id;

                case 1:
                    return -1;

                case 2:
                    return -2;
            }

            cs.close();

        } catch (SQLException ex) {
            System.out.println("Error SQL: " + ex.getMessage());
            return -1;
        }
        return -1;
    }

    public static Contrato consultarContrato(int id) {

        try (Connection con = ConexionBD.getConnection()) {

            String sql = "{CALL sp_get_contrato(?)}";

            CallableStatement cs = con.prepareCall(sql);

            cs.setInt(1, id);

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                Contrato c = new Contrato(rs.getInt("id"), rs.getString("id_vivienda"),
                        rs.getInt("id_inquilino"), rs.getDouble("precio"), rs.getString("fecha_fin"),
                        rs.getString("fecha_inicio"), rs.getString("estado"));
                return c;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
            return null;
        }
    }

    public static void eliminarContrato(int id) {

        try (Connection con = ConexionBD.getConnection()) {

            String sql = "{CALL sp_del_contrato(?,?)}";

            CallableStatement cs = con.prepareCall(sql);

            cs.setInt(1, id);
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

    public static int actualizarestadoContrato(int id, int estado) {

        try (Connection con = ConexionBD.getConnection()) {

            String sql = "{CALL sp_actualizar_estado_contrato(?,?,?)}";

            CallableStatement cs = con.prepareCall(sql);

            cs.setInt(1, id);
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
}
