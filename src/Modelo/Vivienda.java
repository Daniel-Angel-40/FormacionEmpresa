package Modelo;

public class Vivienda {

    // ATRIBUTOS
    private int id;
    private Propietario propietario;
    private String direccion;
    private double alquiler_mensual;
    private double superficie;
    private String descripcion;
    private boolean permite_mascota;
    private String tipo;

    // SETTERS

    public void setId(int id) {
        this.id = id;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setAlquiler_mensual(double alquiler_mensual) {
        this.alquiler_mensual = alquiler_mensual;
    }

    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPermite_mascota(boolean permite_mascota) {
        this.permite_mascota = permite_mascota;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // GETTERS

    public int getId() {
        return id;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public String getDireccion() {
        return direccion;
    }

    public double getAlquiler_mensual() {
        return alquiler_mensual;
    }

    public double getSuperficie() {
        return superficie;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isPermite_mascota() {
        return permite_mascota;
    }

    public String getTipo() {
        return tipo;
    }

    // CONTRUCTORES

    public Vivienda(int id, Propietario propietario, double alquiler_mensual, String direccion, double superficie,
                    String descripcion, boolean permite_mascota, String tipo) {
        this.id = id;
        this.propietario = propietario;
        this.alquiler_mensual = alquiler_mensual;
        this.direccion = direccion;
        this.superficie = superficie;
        this.descripcion = descripcion;
        this.permite_mascota = permite_mascota;
        this.tipo = tipo;
    }

    // METODOS

    @Override
    public String toString() {
        return "╔══════════════════════════════════╗\n" +
                "║        DATOS DE LA VIVIENDA      ║\n" +
                "╠══════════════════════════════════╣\n" +
                "║ ID          : " + id + "\n" +
                "║ Propietario : " + propietario.getId() + "\n" +
                "║ Dirección   : " + direccion + "\n" +
                "║ Alquiler    : " + alquiler_mensual + " €/mes\n" +
                "║ Superficie  : " + superficie + " m²\n" +
                "║ Descripción : " + descripcion + "\n" +
                "║ Mascotas    : " + (permite_mascota ? "Sí" : "No") + "\n" +
                "║ Tipo        : " + tipo + "\n" +
                "╚══════════════════════════════════╝";
    }
}
