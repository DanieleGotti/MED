package classi_test_db;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import gestore_db.RimozioneJooq;

/**
 * Classe che testa metodi della classe "RimozioneJooq"
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRimozioni {

    private RimozioneJooq rimozioneJooq;

    /**
	 * metodo che verrà prima dell'esecuzione di tutti i test
	 */
    @Before
    public void setUp() {
        rimozioneJooq = RimozioneJooq.getIstanza();
    }
    
    @Test
    public void test38RimozioneDiariaInfermieristica() {
        int result = rimozioneJooq.diariaInf(1, "T1", 1);
        assertEquals(1, result);
    }
    
    @Test
    public void test39RimozioneDiariaInfermieristicaErrato() {
        int result = rimozioneJooq.diariaInf(1, "T2", 1);
        assertEquals(0, result);
    }
    
    @Test
    public void test40RimozioneRilevazione() {
        int result = rimozioneJooq.rilevazione(1, "T1", 1);
        assertEquals(1, result);
    }
    
    @Test
    public void test41RimozioneRilevazioneErrato() {
        int result = rimozioneJooq.rilevazione(1, "T2", 1);
        assertEquals(0, result);
    }
    
    @Test
    public void test42RimozioneVisitaIntervento() {
        int result = rimozioneJooq.visite(0);	
        assertEquals(1, result);
    }
    
    @Test
    public void test43RimozioneVisitaInterventoErrato() {
        int result = rimozioneJooq.visite(5);	
        assertEquals(0, result);
    }
    
    @Test
    public void test44RimozioneAssegnazioneLetto() {;
        int result = rimozioneJooq.assegnazioneLetto("T1", 1, "Re0", "ModuloA", 1);
        assertEquals(1, result);
    }
    
    @Test
    public void test45RimozioneAssegnazioneLettoErrato() {;
        int result = rimozioneJooq.assegnazioneLetto("T1", 1, "Re0", "ModuloB", 2);
        assertEquals(0, result);
    }
    
    @Test
    public void test46RimozioneDiariaMedica() {
        int result = rimozioneJooq.diariaMed(1, "T1", 1);
        assertEquals(1, result);
    }
    
    @Test
    public void test47RimozioneDiariaMedicaErrore() {
    	int result = rimozioneJooq.diariaMed(1, "T2", 1);
        assertEquals(0, result);
    }
    
    @Test
    public void test48RimozionePersonale() {
        int result = rimozioneJooq.personale("t1");
        assertEquals(1, result);
    }
    
    @Test
    public void test49RimozionePersonaleErrato() {
        int result = rimozioneJooq.personale("t2");
        assertEquals(0, result);
    }
    
    @Test
    public void test50RimozioneLetto() {
        int result = rimozioneJooq.letto("Re0", "ModuloA", 1);
        assertEquals(1, result);
    }
    
    @Test
    public void test51RimozioneLettoErrato() {
        int result = rimozioneJooq.letto("Re0", "ModuloB", 1);
        assertEquals(0, result);
    }
    
    @Test
    public void test52RimozioneModulo() {
        int result = rimozioneJooq.modulo("Re0", "ModuloA");
        assertEquals(1, result);
    }
    
    @Test
    public void test53RimozioneModuloErrato() {
        int result = rimozioneJooq.modulo("ReT", "ModuloC");
        assertEquals(0, result);
    }
    
    @Test
    public void test54RimozioneReparto() {
        int result = rimozioneJooq.reparto("Re0");
        assertEquals(1, result);
    } 
    
    @Test
    public void test55RimozioneRepartoErrato() {
        int result = rimozioneJooq.reparto("ReT");
        assertEquals(0, result);
    }

}

