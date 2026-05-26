package DAO;

import ConexionBD.ConexionBD;
import Modelo.Inquilino;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

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

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }
}
