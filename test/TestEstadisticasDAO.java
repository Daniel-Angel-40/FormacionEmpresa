import DAO.EstadisticaDAO;
import Modelo.Contrato;
import Modelo.Vivienda;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestEstadisticasDAO {

    @Test
    void testViviendasLibres() {
        ArrayList<Vivienda> resultado = EstadisticaDAO.viviendasLibres();
        assertNotNull(resultado);
    }

    @Test
    void testViviendasActivasPropietario() {
        ArrayList<Vivienda> resultado = EstadisticaDAO.viviendasActivasPropietario(1);
        assertNotNull(resultado);
    }

    @Test
    void testHistoricoInquilino() {
        ArrayList<Contrato> resultado = EstadisticaDAO.historicoInquilino(1);
        assertNotNull(resultado);
    }
}
