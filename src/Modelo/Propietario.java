package Modelo;
/**
 * Representa a un propietario registrado en el sistema.
 * Almacena su información de identificación legal y datos de contacto directo.
 * * @author Daniel
 * @version 1.0
 */
public class Propietario {

    // ATRIBUTOS
    private int id;
    private String DNI;
    private String nombre;
    private String correo;
    private String telefono;

    // SETTERS

    public void setId(int id) {
        this.id = id;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // GETTERS

    public int getId() {
        return id;
    }

    public String getDNI() {
        return DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    // CONSTRUCTORES

    /**
     * Constructor completo utilizado para procesar consultas de la base de datos,
     * donde ya se conoce el ID asignado.
     * Controla que los campos de contacto vacíos se almacenen como null.
     * @param id Identificador único del propietario.
     * @param DNI Documento Nacional de Identidad.
     * @param nombre Nombre completo.
     * @param correo Correo electrónico.
     * @param telefono Número de teléfono.
     */
    public Propietario(int id, String DNI, String nombre, String correo, String telefono) {
        this.id = id;
        this.DNI = DNI;
        this.nombre = nombre;
        if (correo.isBlank()) {
            this.correo = null;
        } else {
            this.correo = correo;
        }
        if (telefono.isBlank()) {
            this.telefono = null;
        } else {
            this.telefono = telefono;
        }
    }

    /**
     * Constructor utilizado para preparar el registro de un nuevo propietario
     * en la base de datos.
     * Controla que los campos de contacto vacíos se almacenen como null.
     * @param DNI Documento Nacional de Identidad.
     * @param nombre Nombre completo.
     * @param correo Correo electrónico.
     * @param telefono Número de teléfono.
     */
    public Propietario(String DNI, String nombre, String correo, String telefono) {
        this.DNI = DNI;
        this.nombre = nombre;
        if (correo.isBlank()) {
            this.correo = null;
        } else {
            this.correo = correo;
        }
        if (telefono.isBlank()) {
            this.telefono = null;
        } else {
            this.telefono = telefono;
        }
    }

    // METODOS


    @Override
    public String toString() {
        return """
                ╔══════════════════════════════════╗
                ║         DATOS DEL CLIENTE        ║
                ╠══════════════════════════════════╣
                ║ ID      : """ + id + "\n" +
                "║ DNI     : " + DNI + "\n" +
                "║ Nombre  : " + nombre + "\n" +
                "║ Correo  : " + correo + "\n" +
                "║ Teléfono: " + telefono + "\n" +
                "╚══════════════════════════════════╝";
    }
}
