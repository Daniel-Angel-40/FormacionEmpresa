package Modelo;
/**
 * Representa a un inquilino interesado en arrendar un inmueble.
 * Registra sus datos personales, información de contacto y si posee mascotas,
 * un factor clave para la compatibilidad con las viviendas.
 * @author Daniel
 * @version 1.0
 */
public class Inquilino {

    // ATRIBUTOS

    private int id;
    private String DNI;
    private String nombre;
    private String correo;
    private String telefono;
    private boolean tiene_mascota;

    // SETTERS

    public void setId(int id) {
        this.id = id;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setTiene_mascota(boolean tiene_mascota) {
        this.tiene_mascota = tiene_mascota;
    }

    // GETTERS

    public int getId() {
        return id;
    }

    public String getTelefono() {
        return telefono;
    }

    public boolean isTiene_mascota() {
        return tiene_mascota;
    }

    public String getCorreo() {
        return correo;
    }

    public String getDNI() {
        return DNI;
    }

    public String getNombre() {
        return nombre;
    }

    // CONSTRUCTORES

    /**
     * Constructor completo para cuando se leen datos de inquilinos existentes
     * desde una base de datos.
     * Filtra las cadenas en blanco para guardarlas como null.
     * @param id Identificador único del inquilino.
     * @param DNI Documento Nacional de Identidad.
     * @param nombre Nombre completo.
     * @param correo Correo electrónico.
     * @param telefono Teléfono de contacto.
     * @param tiene_mascota Estado de posesión de mascota.
     */
    public Inquilino(int id, String DNI, String nombre, String correo, String telefono, boolean tiene_mascota) {
        this.id = id;
        this.tiene_mascota = tiene_mascota;
        if (telefono.isBlank()) {
            this.telefono = null;
        } else {
            this.telefono = telefono;
        }
        if (correo.isBlank()) {
            this.correo = null;
        } else {
            this.correo = correo;
        }
        this.nombre = nombre;
        this.DNI = DNI;
    }

    /**
     * Constructor simplificado para la inserción de un nuevo inquilino,
     * delegando la generación del ID al sistema de base de datos.
     * @param DNI Documento Nacional de Identidad.
     * @param nombre Nombre completo.
     * @param correo Correo electrónico.
     * @param telefono Teléfono de contacto.
     * @param tiene_mascota Estado de posesión de mascota.
     */
    public Inquilino(String DNI, String nombre, String correo, String telefono, boolean tiene_mascota) {
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
        this.tiene_mascota = tiene_mascota;
    }

    // METODOS

    @Override
    public String toString() {
        return "╔══════════════════════════════════╗\n" +
                "║         DATOS DEL CLIENTE        ║\n" +
                "╠══════════════════════════════════╣\n" +
                "║ ID       : " + id + "\n" +
                "║ DNI      : " + DNI + "\n" +
                "║ Nombre   : " + nombre + "\n" +
                "║ Correo   : " + correo + "\n" +
                "║ Teléfono : " + telefono + "\n" +
                "║ Mascota  : " + (tiene_mascota ? "Sí" : "No") + "\n" +
                "╚══════════════════════════════════╝";    }
}
