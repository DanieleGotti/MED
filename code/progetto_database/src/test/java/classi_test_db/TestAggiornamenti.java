package classi_test_db;

import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import gestore_db.AggiornamentiJooq;

/**
 * Classe che testa metodi della classe "AggiornamentiJooq"
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAggiornamenti {

    private AggiornamentiJooq aggiornamentoJooq;

    /**
	 * metodo che verrà prima dell'esecuzione di tutti i test
	 */
    @Before
    public void setUp() {
        aggiornamentoJooq = AggiornamentiJooq.getIstanza();
    }
    
    @Test
    public void test20AggiornamentoDegente() {
        int result = aggiornamentoJooq.degente("T1", 1, "urgenza", "verde");
        assertEquals(1, result);
    }
    
    @Test
    public void test21AggiornamentoDegenteErrato() {
        int result = aggiornamentoJooq.degente("T1", 1, "urgenza", "blu");
        assertEquals(0, result);
    }
    
    @Test
    public void test22AggiornamentoPersonale() {
        int result = aggiornamentoJooq.personale("t1", "t");
        assertEquals(1, result);
    }
    
    @Test
    public void test23AggiornamentoPersonaleErrato() {
        int result = aggiornamentoJooq.personale("t2", "t");
        assertEquals(0, result);
    }
    
    @Test
    public void test24AggiornamentoDiariaMedica() {
        int result = aggiornamentoJooq.diariaMed(1, "T1", 1, "storico", "Storico");
        assertEquals(1, result);
    }
    
    @Test
    public void test25AggiornamentoDiariaMedicaErrato() {
        int result = aggiornamentoJooq.diariaMed(1, "T1", 1, "altro", "Prova");
        assertEquals(0, result);
    }
    
    @Test
    public void test26AggiornamentoDiariaInfermieristica() {
        int result = aggiornamentoJooq.diariaInf(1, "T1", 1, "false");
        assertEquals(1, result);
    }
    
    @Test
    public void test27AggiornamentoDiariaInfermieristicaErrato() {
        int result = aggiornamentoJooq.diariaInf(1, "T1", 1, "altro");
        assertEquals(0, result);
    }
    
    @Test
    public void test28AggiornamentoVisitaIntervento() {
        int result = aggiornamentoJooq.visitaIntervento(0, LocalDate.now(), LocalTime.now(), "Intervento d'urgenza", "t1");	
        assertEquals(1, result);
    }
    
    @Test
    public void test29AggiornamentoDegenteNome() {
        int result = aggiornamentoJooq.degente("T1", 1, "nome", "Mario");
        assertEquals(1, result);
    }

    @Test
    public void test30AggiornamentoDegenteCognome() {
        int result = aggiornamentoJooq.degente("T1", 1, "cognome", "Neri");
        assertEquals(1, result);
    }

    @Test
    public void test31AggiornamentoDegenteEta() {
        int result = aggiornamentoJooq.degente("T1", 1, "eta", "45");
        assertEquals(1, result);
    }

    @Test
    public void test32AggiornamentoDegenteGenere() {
        int result = aggiornamentoJooq.degente("T1", 1, "genere", "Maschio");
        assertEquals(1, result);
    }

    @Test
    public void test33AggiornamentoDegenteAttributoNonValido() {
        int result = aggiornamentoJooq.degente("T1", 1, "invalid", "valore");
        assertEquals(0, result);
    }

    @Test
    public void test34AggiornamentoDiariaMedicaMotivo() {
        int result = aggiornamentoJooq.diariaMed(1, "T1", 1, "motivo", "Nuovo motivo");
        assertEquals(1, result);
    }

    @Test
    public void test35AggiornamentoDiariaMedicaFarmaci() {
        int result = aggiornamentoJooq.diariaMed(1, "T1", 1, "farmaci", "Aspirina");
        assertEquals(1, result);
    }

    @Test
    public void test36AggiornamentoDiariaMedicaAllergie() {
        int result = aggiornamentoJooq.diariaMed(1, "T1", 1, "allergie", "Polline");
        assertEquals(1, result);
    }

    @Test
    public void test37AggiornamentoDiariaMedicaMedico() {
        int result = aggiornamentoJooq.diariaMed(1, "T1", 1, "medico", "Dr. House");
        assertEquals(1, result);
    }
    
}
    
