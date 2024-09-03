package classi_test_db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import gestore_db.CreateDB;
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
 * Specifica le classi di test nell'ordine da eseguire
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    TestInserimenti.class,  
    TestAggiornamenti.class,
    TestRimozioni.class,
    TestPopulate.class,
    TestCreazione.class
})

/**
 * Classe principale per l'esecuzione dei test
 * ATTENZIONE: l'esecuzione dei test cancella tutti i dati presenti nel database e nel file system,
 * riportandolo allo stato iniziale con i pazienti e personale di default
 */
public class MetodiDaTestare {

	/**
	 * metodo statico che verrà eseguito prima dell'esecuzione di tutti i test
	 */
    @org.junit.BeforeClass
    public static void setUpBeforeClass() {
    	
    }

    /**
	 * metodo statico che verrà eseguito dopo l'esecuzione di tutti i test
	 */
    @org.junit.AfterClass
    public static void tearDownAfterClass() {
    	 try (Connection conn = DriverManager.getConnection(CreateDB.DB_URL)) {
             DSLContext create = DSL.using(conn, SQLDialect.SQLITE);

             //rimozione dei dati di test inseriti per ogni metodo
             create.deleteFrom(Personale.PERSONALE).execute();
             create.deleteFrom(Degente.DEGENTE).execute();
             create.deleteFrom(Rilevazione.RILEVAZIONE).execute();
             create.deleteFrom(Reparto.REPARTO).execute();
             create.deleteFrom(Modulo.MODULO).execute();
             create.deleteFrom(Letto.LETTO).execute();
             create.deleteFrom(Assegnazioneletto.ASSEGNAZIONELETTO).execute();
             create.deleteFrom(Diariainf.DIARIAINF).execute();
             create.deleteFrom(Diariamed.DIARIAMED).execute();
             create.deleteFrom(VisitaIntervento.VISITA_INTERVENTO).execute();
             create.deleteFrom(Dimesso.DIMESSO).execute();
         } catch (SQLException e) {
             System.err.println("Errore durante la rimozione dei dati di test: " + e.getMessage());
         }
    	 
    	 File dbFile = new File("../progetto_database/db/test_db.db3");
         if (dbFile.exists()) {
             dbFile.delete();
         }
    }
   
}
