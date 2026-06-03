import DAO.PropietarioDAO;
import Modelo.Propietario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPropietarioDAO {

    private Propietario p;

    @BeforeEach
    void setUp() {
        String dniAleatorio = "T" + (System.currentTimeMillis() % 100000);
        p = new Propietario(dniAleatorio, "Daniel", "test@gmail.com", "243234");
    }

    @Test
    void testInsPropietario() {

        int id = PropietarioDAO.insertarPropietario(p);
        assertTrue(id > 0);
    }

    @Test
    void testInsPropietarioError() {
        p = new Propietario("12345678A", "Pepe", "test@gmail.com", "234234");
        int id = PropietarioDAO.insertarPropietario(p);
        assertFalse(id > 0);
    }

    @Test
    void testGetPropietario() {
        int id =  PropietarioDAO.insertarPropietario(p);
        Propietario resultado = PropietarioDAO.consultarPropietario(id);
        assertEquals("Daniel", resultado.getNombre());
    }

    @Test
    void testDelPropietario() {
        int id = PropietarioDAO.insertarPropietario(p);
        PropietarioDAO.eliminarPropietario(id);
        Propietario resultado = PropietarioDAO.consultarPropietario(id);
        assertNull(resultado);
    }

    @Test
    void testUpdPropietario() {
        int id = PropietarioDAO.insertarPropietario(p);
        p.setId(id);
        p.setNombre("NotDaniel");
        int res = PropietarioDAO.actualizarPropietario(p);
        assertEquals(0, res);
    }
}
