package presencial.service;

import org.junit.jupiter.api.Test;
import presencial.dao.BD;
import presencial.dao.MedicamentoDaoH2;
import presencial.modelo.Medicamento;

import static org.junit.jupiter.api.Assertions.*;

class MedicamentoServiceTest {
    private MedicamentoService medicamentoService = new MedicamentoService(new MedicamentoDaoH2());

    @Test
    void guardarMedicamentoTest() throws Exception{
        BD.crearBD();
        Medicamento medicamento = new Medicamento("ibuprofeno", "bayer", 3, 25.0);
        medicamentoService.guardar(medicamento);
        assertTrue(medicamento.getId()==1);
    }
}