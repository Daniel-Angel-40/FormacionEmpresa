package ConexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = Configuracion.get("db.url");
    private static final String USUARIO = Configuracion.get("db.user");
    private static final String PASSWD = Configuracion.get("db.password");

    public static Connection getConnection() throws SQLException {

            return DriverManager.getConnection(URL, USUARIO, PASSWD);
    }
}
