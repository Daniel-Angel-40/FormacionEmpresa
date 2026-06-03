import DAO.ViviendaDAO;
import Modelo.Vivienda;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestViviendaDAO {

    private Vivienda v;
    private String idVivienda;

    @BeforeEach
    void setUp() {
        idVivienda = "V" + (System.currentTimeMillis() % 10000);
        v = new Vivienda(idVivienda, 1, 800.0, "Calle Test 1",
                70.0, "Descripcion test", false, "casa");
    }

    @Test
    void testInsVivienda() {
        int res = ViviendaDAO.insertarVivienda(v);
        assertEquals(0, res);
    }

    @Test
    void testInsViviendaErrorCheck() {
        String idVivienda = "V" + (System.currentTimeMillis() % 10000);
        v = new Vivienda(idVivienda, 2, 800.0, "Calle Test 1",
                70.0, "Descripcion test", false, "piso");
        int res = ViviendaDAO.insertarVivienda(v);
        assertEquals(-2, res);
    }

    @Test
    void testInsViviendaError() {
        v = new Vivienda("VIV001", 2, 800.0, "Calle Test 1",
                70.0, "Descripcion test", false, "casa");
        int res = ViviendaDAO.insertarVivienda(v);
        assertEquals(-1, res);
    }

    @Test
    void testGetVivienda() {
        Vivienda resultado = ViviendaDAO.consultarViviendas("VIV001");
        assertEquals(75.50, resultado.getSuperficie());
    }

    @Test
    void testGetViviendaError() {

        Vivienda v = ViviendaDAO.consultarViviendas("adawd");
        assertNull(v);
    }

    @Test
    void testDelVivienda(){
        ViviendaDAO.insertarVivienda(v);
        ViviendaDAO.eliminarVivienda(idVivienda);
        Vivienda resultado = ViviendaDAO.consultarViviendas(idVivienda);
        assertNull(resultado);

    }

    @Test
    void testUpdVivienda() {
        ViviendaDAO.insertarVivienda(v);
        v.setDireccion("Calle Nueva 99");
        int res = ViviendaDAO.actualizarVivienda(v);
        assertEquals(0, res);
    }

    @Test
    void testUpdViviendaError() {
        ViviendaDAO.insertarVivienda(v);
        v.setId(null);
        int res = ViviendaDAO.actualizarVivienda(v);
        assertEquals(-1, res);
    }

    @Test
    void testUpdViviendaErrorCheck() {
        ViviendaDAO.insertarVivienda(v);
        v.setTipo("piso");
        int res = ViviendaDAO.actualizarVivienda(v);
        assertEquals(-2, res);
    }
}