package DAO;

import ConexionBD.ConexionBD;
import Modelo.Contrato;

import java.sql.*;

public class ContratoDAO {

    public static void insertarContrato(Contrato contrato) {

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
                    System.out.println("Contrato registrado exitosamente");
                    System.out.println("ID del contrato: " + id);
                    break;
                case 1:
                    System.out.println("Error al registrar contrato");
                    break;
                case 2:
                    System.out.println("Datos invalidos del contrato");
                    break;
            }

            cs.close();

        } catch (SQLException ex) {
            System.out.println("Error SQL: " + ex.getMessage());
        }
    }

    public static void consultarContrato(int id) {

        try (Connection con = ConexionBD.getConnection()) {

            String sql = "{CALL sp_get_contrato(?)}";

            CallableStatement cs = con.prepareCall(sql);

            cs.setInt(1, id);

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                Contrato c = new Contrato(rs.getInt("id"), rs.getString("id_vivienda"),
                        rs.getInt("id_inquilino"), rs.getDouble("precio"), rs.getString("fecha_fin"),
                        rs.getString("fecha_inicio"), rs.getString("estado"));
                System.out.println(c);
            } else {
                System.out.println("No existe contrato con el ID: " + id);
            }
            cs.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }

    public static void eliminarContrato(int id) {

        try(Connection con = ConexionBD.getConnection()) {

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
}
