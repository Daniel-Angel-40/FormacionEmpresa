package Modelo;

public class Contrato {

    // ATRIBUTOS

    private int id;
    private String id_vivienda;
    private int id_inquilino;
    private String fecha_inicio;
    private String fecha_fin;
    private double precio;
    private String estado;

    // SETTERS

    public void setId(int id) {
        this.id = id;
    }

    public void setId_vivienda(String id_vivienda) {
        this.id_vivienda = id_vivienda;
    }

    public void setId_inquilino(int id_inquilino) {
        this.id_inquilino = id_inquilino;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // GETTERS

    public int getId() {
        return id;
    }

    public String getEstado() {
        return estado;
    }

    public double getPrecio() {
        return precio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public int getId_inquilino() {
        return id_inquilino;
    }

    public String getId_vivienda() {
        return id_vivienda;
    }

    // CONSTRUCTORES

    public Contrato(int id, String id_vivienda, int id_inquilino, double precio, String fecha_fin, String fecha_inicio, String estado) {
        this.id = id;
        this.estado = estado;
        this.precio = precio;
        this.fecha_fin = fecha_fin;
        this.fecha_inicio = fecha_inicio;
        this.id_inquilino = id_inquilino;
        this.id_vivienda = id_vivienda;
    }

    public Contrato(String id_vivienda, int id_inquilino, double precio, String fecha_fin, String fecha_inicio) {
        this.id_vivienda = id_vivienda;
        this.id_inquilino = id_inquilino;
        this.precio = precio;
        this.fecha_fin = fecha_fin;
        this.fecha_inicio = fecha_inicio;
        this.estado = "pendiente";
    }

    public Contrato(int id, String fecha_inicio, String fecha_fin, double precio, String estado) {
        this.id = id;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.precio = precio;
        this.estado = estado;
    }

// METODOS

    @Override
    public String toString() {
        return "╔══════════════════════════════════╗\n" +
                "║       DATOS DEL CONTRATO         ║\n" +
                "╠══════════════════════════════════╣\n" +
                "║ ID          : " + id + "\n" +
                "║ Vivienda    : " + id_vivienda + "\n" +
                "║ Inquilino   : " + id_inquilino + "\n" +
                "║ Inicio      : " + fecha_inicio + "\n" +
                "║ Fin         : " + fecha_fin + "\n" +
                "║ Precio      : " + precio + " €/mes\n" +
                "║ Estado      : " + estado + "\n" +
                "╚══════════════════════════════════╝";
    }
}
