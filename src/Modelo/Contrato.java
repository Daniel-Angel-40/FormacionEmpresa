package Modelo;
/**
 * Representa el contrato de arrendamiento formalizado en el sistema.
 * Vincula a un {@link Inquilino} con una {@link Vivienda}, determinando los
 * plazos temporales, costos acordados y el estado legal del trámite.
 * @author Daniel
 * @version 1.0
 */
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

    /**
     * Constructor completo ideal para recuperar toda la información histórica
     * de un contrato desde la base de datos.
     * @param id Identificador único del contrato.
     * @param id_vivienda Identificador del inmueble.
     * @param id_inquilino Identificador del inquilino.
     * @param precio Mensualidad acordada.
     * @param fecha_fin Fecha de término del acuerdo.
     * @param fecha_inicio Fecha de entrada en vigor.
     * @param estado Situación del contrato.
     */
    public Contrato(int id, String id_vivienda, int id_inquilino, double precio, String fecha_fin, String fecha_inicio, String estado) {
        this.id = id;
        this.estado = estado;
        this.precio = precio;
        this.fecha_fin = fecha_fin;
        this.fecha_inicio = fecha_inicio;
        this.id_inquilino = id_inquilino;
        this.id_vivienda = id_vivienda;
    }

    /**
     * Constructor utilizado al registrar un nuevo contrato.
     * Inicializa por defecto el estado como "pendiente" y prescinde del ID.
     * @param id_vivienda Identificador del inmueble.
     * @param id_inquilino Identificador del inquilino.
     * @param precio Mensualidad estipulada.
     * @param fecha_fin Fecha de expiración pactada.
     * @param fecha_inicio Fecha en la que inicia el arrendamiento.
     */
    public Contrato(String id_vivienda, int id_inquilino, double precio, String fecha_fin, String fecha_inicio) {
        this.id_vivienda = id_vivienda;
        this.id_inquilino = id_inquilino;
        this.precio = precio;
        this.fecha_fin = fecha_fin;
        this.fecha_inicio = fecha_inicio;
        this.estado = "pendiente";
    }

    /**
     * Constructor parcial utilizado en actualizaciones
     * específicas de plazos, costos o estados de un contrato conocido.
     * @param id Identificador del contrato.
     * @param fecha_inicio Fecha de inicio del acuerdo.
     * @param fecha_fin Fecha de finalización del acuerdo.
     * @param precio Costo de la renta.
     * @param estado Estado del contrato.
     */
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
