package classi_test_db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import gestore_db.CreateDB;
import gestore_db.PopulateDB;
import med_db.jooq.generated.tables.Assegnazioneletto;
import med_db.jooq.generated.tables.Degente;
import med_db.jooq.generated.tables.Diariainf;
import med_db.jooq.generated.tables.Diariamed;
import med_db.jooq.generated.tables.Dimesso;
import med_db.jooq.generated.tables.Letto;
import med_db.jooq.generated.tables.Modulo;
import med_db.jooq.generated.tables.Personale;
import med_db.jooq.generated.tables.Reparto;
import med_db.jooq.generated.tables.Rilevazione;
import med_db.jooq.generated.tables.VisitaIntervento;

/**
 * Classe che testa metodi della classe "PopulateDB"
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestPopulate {

	/**
	 * metodo che verrà prima dell'esecuzione di tutti i test
	 */
    @Before
    public void setUp() throws IOException {

        try (Connection conn = DriverManager.getConnection(CreateDB.DB_URL)) {
            DSLContext create = DSL.using(conn, SQLDialect.SQLITE);

            // Inizio di una transazione
            conn.setAutoCommit(false);

            try {
                //cancellazione dei dati già presenti per evitare conflitti
                create.deleteFrom(Assegnazioneletto.ASSEGNAZIONELETTO).execute();
                create.deleteFrom(Diariainf.DIARIAINF).execute();
                create.deleteFrom(Diariamed.DIARIAMED).execute();
                create.deleteFrom(VisitaIntervento.VISITA_INTERVENTO).execute();
                create.deleteFrom(Dimesso.DIMESSO).execute();
                create.deleteFrom(Rilevazione.RILEVAZIONE).execute();
                create.deleteFrom(Letto.LETTO).execute();
                create.deleteFrom(Modulo.MODULO).execute();
                create.deleteFrom(Reparto.REPARTO).execute();
                create.deleteFrom(Degente.DEGENTE).execute();
                create.deleteFrom(Personale.PERSONALE).execute();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                System.err.println("Errore durante la rimozione dei dati di test: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println("Errore nella connessione al database: " + e.getMessage());
        }
        
        PopulateDB.inserisciDati();
    }
    
    @Test
    public void test56InserimentoDati() {
        Result result = JUnitCore.runClasses(TestInserimenti.class);
        int risultato;
		if(result.getFailureCount() != 0) {
        	risultato = 0;
        }
        else {
        	risultato = 1;
        }
        assertEquals(1, risultato);
    }
    
    @Test
    public void test57InserimentoFotoPersonale() {
    	PopulateDB.inserisciFotoPersonaleDefault("t1","../progetto_gui/src/main/resources/fototessera_default.png");
    	assertTrue(checkFileExists("./../../personale/id_t1/Fototessera.png"));
    }
    
    @Test
    public void test58InserimentoFotoPazienti() {
    	PopulateDB.inserisciFotoPazientiDefault("T1",1,LocalDate.now(),"../progetto_gui/src/main/resources/fototessera_default.png");
    	assertTrue(checkFileExists("./../../pazienti/id_T1/dati_anagrafici/count_1__data__" + LocalDate.now() + "/Fototessera.png"));
    }
    
    private boolean checkFileExists(String filePath) {
        Path path = Paths.get(filePath);
        return Files.exists(path);
    }
     
}
