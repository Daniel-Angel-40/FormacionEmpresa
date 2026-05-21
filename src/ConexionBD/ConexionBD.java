package ConexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = "jdbc:mysql://localhost:3306/alquilaria_bd";
    private static final String USUARIO = "alumno";
    private static final String PASSWD = "alumno";

    public static Connection getConnection() throws SQLException {

            return DriverManager.getConnection(URL, USUARIO, PASSWD);
    }
}
