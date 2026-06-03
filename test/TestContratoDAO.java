import DAO.ContratoDAO;
import Modelo.Contrato;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestContratoDAO {

    private Contrato c;

    @BeforeEach
    void setUp() {
        c = new Contrato("VIV003", 3, 8000.30, "2025-05-30", "2025-05-20");
    }

    @Test
    void testInsContrato() {
        int id = ContratoDAO.insertarContrato(c);
        assertTrue(id > 0);
    }

    @Test
    void testInsContratoError() {
        c.setId_inquilino(-1);
        int id = ContratoDAO.insertarContrato(c);
        assertFalse(id > 0);
    }

    @Test
    void testInsContratoCheck() {
        c.setFecha_fin("2025-05-20");
        c.setFecha_inicio("2025-05-30");
        int id = ContratoDAO.insertarContrato(c);
        assertFalse(id > 0);
    }

    @Test
    void testGetContrato(){
        int id = ContratoDAO.insertarContrato(c);
        Contrato resultado = ContratoDAO.consultarContrato(id);
        assertEquals(8000.30, resultado.getPrecio());
    }

    @Test
    void testDelContrato(){
        int id = ContratoDAO.insertarContrato(c);
        ContratoDAO.eliminarContrato(id);
        Contrato resultado = ContratoDAO.consultarContrato(id);
        assertNull(resultado);
    }

    @Test
    void testUpdContrato(){
        int id = ContratoDAO.insertarContrato(c);
        Contrato resultado = ContratoDAO.consultarContrato(id);
        resultado.setEstado("activo");
        int res = ContratoDAO.actualizarContrato(resultado);
        assertEquals(0, res);
    }

    @Test
    void testUpdContratoCheck(){
        int id = ContratoDAO.insertarContrato(c);
        Contrato resultado = ContratoDAO.consultarContrato(id);
        resultado.setEstado("nada");
        int res = ContratoDAO.actualizarContrato(resultado);
        assertEquals(-2, res);
    }

    @Test
    void testUpdContratoError(){
        int id = ContratoDAO.insertarContrato(c);
        Contrato resultado = ContratoDAO.consultarContrato(id);
        resultado.setId(-1);
        int res = ContratoDAO.actualizarContrato(resultado);
        assertEquals(-1, res);
    }
}
