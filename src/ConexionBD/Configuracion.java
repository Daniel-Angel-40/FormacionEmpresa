package ConexionBD;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuracion {

    private static final Properties prop = new Properties();

    static {
        try(InputStream inputStream = Configuracion.class.getClassLoader().getResourceAsStream("config.properties")) {

            if(inputStream == null) throw new RuntimeException("No se encontró config.properties");

            prop.load(inputStream);


        }catch (IOException e){
            throw new RuntimeException("Error al cargar la configuracion",e);
        }
    }

    public static String get(String clave) {
        return prop.getProperty(clave);
    }
}
