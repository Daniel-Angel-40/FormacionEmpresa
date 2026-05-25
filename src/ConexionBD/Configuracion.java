package ConexionBD;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuracion {

    // Almacena las claves
    private static final Properties prop = new Properties();

    // Static para que se ejecute solo una vez
    static {
        // Abre el archivo y busca las claves
        try(InputStream inputStream = Configuracion.class.getClassLoader().getResourceAsStream("config.properties")) {

            // Si no existe el archivo se lanza un error
            if(inputStream == null) throw new RuntimeException("No se encontró config.properties");

            // Lo lee y carga las claves
            prop.load(inputStream);


        }catch (IOException e){
            // Por si hay error al leerlo
            throw new RuntimeException("Error al cargar la configuracion",e);
        }
    }

    // Metodo para obtener el valor de la clave
    public static String get(String clave) {
        return prop.getProperty(clave);
    }
}
