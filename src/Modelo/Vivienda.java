package Modelo;
/**
 * Representa una vivienda disponible en el sistema de gestión inmobiliaria.
 * Contiene información sobre su ubicación, características físicas, costo de alquiler
 * y la relación con su propietario.
 * * @author Daniel
 * @version 1.0
 */
public class Vivienda {

    // ATRIBUTOS
    private String id;
    private int id_propietario;
    private String direccion;
    private double alquiler_mensual;
    private double superficie;
    private String descripcion;
    private boolean permite_mascota;
    private String tipo;

    // SETTERS

    public void setId(String id) {
        this.id = id;
    }

    public void setPropietario(int propietario) {
        this.id_propietario = propietario;
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

    public String getId() {
        return id;
    }

    public int getPropietario() {
        return id_propietario;
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

    /**
     * Constructor completo para instanciar una vivienda con todos sus datos,
     * incluyendo su identificador único, pensado para lecturas desde Base de Datos.
     * @param id Identificador único de la vivienda.
     * @param propietario ID del propietario.
     * @param alquiler_mensual Costo mensual del alquiler.
     * @param direccion Dirección física.
     * @param superficie Metros cuadrados de la vivienda.
     * @param descripcion Detalles adicionales del inmueble.
     * @param permite_mascota Estado de admisión de mascotas.
     * @param tipo tipo de vivienda.
     */
    public Vivienda(String id, int propietario, double alquiler_mensual, String direccion, double superficie,
                    String descripcion, boolean permite_mascota, String tipo) {
        this.id = id;
        this.id_propietario = propietario;
        this.alquiler_mensual = alquiler_mensual;
        this.direccion = direccion;
        this.superficie = superficie;
        this.descripcion = descripcion;
        this.permite_mascota = permite_mascota;
        this.tipo = tipo;
    }

    /**
     * Constructor alternativo sin el ID de la vivienda pensado para inserciones
     * donde el ID se genera de manera autoincremental.
     * @param id_propietario ID del propietario.
     * @param direccion Dirección física.
     * @param alquiler_mensual Costo mensual del alquiler.
     * @param superficie Metros cuadrados de la vivienda.
     * @param permite_mascota Estado de admisión de mascotas.
     * @param descripcion Detalles adicionales del inmueble.
     * @param tipo tipo de vivienda.
     */
    public Vivienda(int id_propietario, String direccion, double alquiler_mensual, double superficie, boolean permite_mascota, String descripcion, String tipo) {
        this.id_propietario = id_propietario;
        this.direccion = direccion;
        this.alquiler_mensual = alquiler_mensual;
        this.superficie = superficie;
        this.permite_mascota = permite_mascota;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    // METODOS

    @Override
    public String toString() {
        return "╔══════════════════════════════════╗\n" +
                "║        DATOS DE LA VIVIENDA      ║\n" +
                "╠══════════════════════════════════╣\n" +
                "║ ID          : " + id + "\n" +
                "║ Propietario : " + id_propietario + "\n" +
                "║ Dirección   : " + direccion + "\n" +
                "║ Alquiler    : " + alquiler_mensual + " €/mes\n" +
                "║ Superficie  : " + superficie + " m²\n" +
                "║ Descripción : " + descripcion + "\n" +
                "║ Mascotas    : " + (permite_mascota ? "Sí" : "No") + "\n" +
                "║ Tipo        : " + tipo + "\n" +
                "╚══════════════════════════════════╝";
    }
}
