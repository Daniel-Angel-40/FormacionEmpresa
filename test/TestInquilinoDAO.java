import DAO.InquilinoDAO;
import Modelo.Inquilino;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestInquilinoDAO {

    private Inquilino i;

    @BeforeEach
    void setUp() {
        String dniAleatorio = "T" + (System.currentTimeMillis() % 100000);
        i = new Inquilino(dniAleatorio, "Daniel", "test@gmail.com", "600000000", false);
    }

    @Test
    void testInsInquilino() {
        int id = InquilinoDAO.insertarInquilino(i);
        assertTrue(id > 0);
    }

    @Test
    void testInsInquilinoError() {
        i.setDNI("12345678A");
        int id = InquilinoDAO.insertarInquilino(i);
        assertTrue(id < 0);
    }

    @Test
    void testGetInquilino() {
        int id = InquilinoDAO.insertarInquilino(i);
        Inquilino resultado = InquilinoDAO.consultarInquilino(id);
        assertEquals("Daniel", resultado.getNombre());
    }

    @Test
    void testDelInquilino() {
        int id = InquilinoDAO.insertarInquilino(i);
        InquilinoDAO.eliminarInquilino(id);
        Inquilino resultado = InquilinoDAO.consultarInquilino(id);
        assertNull(resultado);
    }

    @Test
    void testUpdInquilino() {
        int id = InquilinoDAO.insertarInquilino(i);
        i.setId(id);
        i.setNombre("NotDaniel");
        int res = InquilinoDAO.actualizarInquilino(i);
        assertEquals(0, res);
    }

    @Test
    void testUpdInquilinoError() {
        int id = InquilinoDAO.insertarInquilino(i);
        i.setId(-1);
        i.setNombre("NotDaniel");
        int res = InquilinoDAO.actualizarInquilino(i);
        assertEquals(-1, res);
    }
}
