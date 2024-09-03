package classi_test_db;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import gestore_db.InserimentoJooq;

/**
 * Classe che testa metodi della classe "InserimentoJooq"
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestInserimenti {

    private InserimentoJooq inserimentoJooq;

    /**
	 * metodo che verrà prima dell'esecuzione di tutti i test
	 */
    @Before
    public void setUp() throws IOException {
        inserimentoJooq = InserimentoJooq.getIstanza();
    }
    
    @Test
    public void test01InserimentoDegente() {
        int result = inserimentoJooq.degente("T1", 1, "Luca", "Verdi", "M", "Maschio", 30, LocalDate.now(), LocalTime.now(), "verde", "in Pronto Soccorso");
        assertEquals(1, result);
    }
    
    @Test
    public void test02InserimentoDegenteErrato() {
        int result = inserimentoJooq.degente("T2", 1, "Giulia", "Bianchi", "F", "Femmina", 40, LocalDate.now(), LocalTime.now(), "blu", "in Pronto Soccorso");
        assertEquals(0, result);
    }
    
    @Test
    public void test03InserimentoPersonale() {
        int result = inserimentoJooq.personale("t1", "Mario", "Bianchi", "M", "t", "Neurologo");
        assertEquals(1, result);
    }
    
    @Test
    public void test04InserimentoPersonaleErrato() {
        int result = inserimentoJooq.personale("t2", "Paolo", "Verdi", "A", "t", "Chirurgo");
        assertEquals(0, result);
    }

    @Test
    public void test05InserimentoReparto() {
        int result = inserimentoJooq.reparto("Re0", "Neurologia");
        assertEquals(1, result);
    } 
    
    @Test
    public void test06InserimentoModulo() {
        int result = inserimentoJooq.modulo("Re0", "ModuloA");
        assertEquals(1, result);
    }
    
    @Test
    public void test07InserimentoModuloErrato() {
        int result = inserimentoJooq.modulo("ReT", "ModuloC");
        assertEquals(0, result);
    }
    
    @Test
    public void test08InserimentoLetto() {
        int result = inserimentoJooq.letto("Re0", "ModuloA", 1);
        assertEquals(1, result);
    }
    
    @Test
    public void test09InserimentoLettoErrato() {
        int result = inserimentoJooq.letto("Re0", "ModuloB", 1);
        assertEquals(0, result);
    }
    
    @Test
    public void test10InserimentoDiariaMedica() {
        int result = inserimentoJooq.diariaMed(1, "T1", 1, "t1", "Nessuno", "Trauma cranico", "Neurologia", "Farmaci", LocalDate.now(), LocalTime.now(), "Nessuna");
        assertEquals(1, result);
    }
    
    @Test
    public void test11InserimentoDiariaMedicaErrore() {
    	int result = inserimentoJooq.diariaMed(1, "T1", 2, "t1", "Nessuno", "Trauma cranico", "Neurologia", "Farmaci", LocalDate.now(), LocalTime.now(), "Nessuna");
        assertEquals(0, result);
    }

    @Test
    public void test12InserimentoAssegnazioneLetto() {;
        int result = inserimentoJooq.assegnazioneLetto("T1", 1, "Re0", "ModuloA", 1, LocalDate.now());
        assertEquals(1, result);
    }
    
    @Test
    public void test13InserimentoAssegnazioneLettoErrato() {;
        int result = inserimentoJooq.assegnazioneLetto("T1", 1, "Re0", "ModuloA", 2, LocalDate.now());
        assertEquals(0, result);
    }
    
    @Test
    public void test14InserimentoDiariaInfermieristica() {
        int result = inserimentoJooq.diariaInf(1, "T1", 1, "t1", LocalDate.now(), LocalTime.now(), "Nessuna nota", true, "Farmaci");
        assertEquals(1, result);
    }
    
    @Test
    public void test15InserimentoDiariaInfermieristicaErrato() {
        int result = inserimentoJooq.diariaInf(1, "T1", 2, "t1", LocalDate.now(), LocalTime.now(), "Nessuna nota", true, "Farmaci");
        assertEquals(0, result);
    }
    
    @Test
    public void test16InserimentoRilevazione() {
        int result = inserimentoJooq.rilevazione(1, "T1", 1, "t1", 36.5, 120, 80, 90, LocalDate.now(), LocalTime.now(), 72, 5);
        assertEquals(1, result);
    }
    
    @Test
    public void test17InserimentoRilevazioneErrato() {
        int result = inserimentoJooq.rilevazione(1, "T1", 2, "t1", 36.5, 120, 80, 90, LocalDate.now(), LocalTime.now(), 72, 5);
        assertEquals(0, result);
    }
    
    @Test
    public void test18InserimentoVisitaIntervento() {
        int result = inserimentoJooq.visitaIntervento(0, "Intervento", "T1", "t1", 2 , LocalDate.now(), LocalTime.now(), "Intervento d'urgenza");	
        assertEquals(1, result);
    }
    
    @Test
    public void test19InserimentoDimessi() {
        int result = inserimentoJooq.dimesso(0, "T1", "t1", 1, LocalDate.now(), LocalTime.now(), "Cura conclusa");
        assertEquals(1, result);
    }

}
