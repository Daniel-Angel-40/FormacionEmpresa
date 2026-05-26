package Modelo;

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
