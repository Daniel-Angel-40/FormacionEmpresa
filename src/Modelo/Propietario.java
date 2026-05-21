package Modelo;

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

    public Propietario() {
        this.id = -1;
        this.DNI = null;
        this.nombre = null;
        this.correo = null;
        this.telefono = null;
    }


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
