package classi_test_db;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import gestore_db.CreateDB;

/**
 * Classe che testa metodi della classe "CreateDB"
 */
public class TestCreazione {
    
    private static final String DB_TEST_FILE = "../progetto_database/db/test_db.db3";
    private static final String DB_TEST_URL = "jdbc:sqlite:" + DB_TEST_FILE;
    private CreateDB createDB;

    /**
	 * metodo che verrà prima dell'esecuzione di tutti i test
	 */
    @Before
    public void setUp() throws IOException, SQLException {
        
        createDB = CreateDB.getIstanza();
        CreateDB.DB_REL_FILE = DB_TEST_FILE;
        CreateDB.DB_URL = DB_TEST_URL;

        File dbFile = new File(DB_TEST_FILE);
        if (dbFile.exists()) {
            dbFile.delete();
        }
    }

    /**
	 * metodo che verrà dopo dell'esecuzione di tutti i test
	 */
    @After
    public void tearDown() {
        File dbFile = new File(DB_TEST_FILE);
        if (dbFile.exists()) {
            dbFile.delete();
        }
    }

    @Test
    public void testDatabaseCreation() throws IOException, SQLException {
        createDB.creaDB();
        File dbFile = new File(DB_TEST_FILE);
        assertTrue(dbFile.exists());
    }
    
	@Test
    public void testTableCreation() throws IOException, SQLException {
        
        createDB.creaDB();
        createDB.creaTable();

        try (Connection conn = DriverManager.getConnection(DB_TEST_URL)) {
            assertTrue(isTableExists(conn, "PERSONALE"));
            assertTrue(isTableExists(conn, "DEGENTE"));
            assertTrue(isTableExists(conn, "DIARIAINF"));
            assertTrue(isTableExists(conn, "DIARIAMED"));
            assertTrue(isTableExists(conn, "ASSEGNAZIONELETTO"));
            assertTrue(isTableExists(conn, "REPARTO"));
            assertTrue(isTableExists(conn, "MODULO"));
            assertTrue(isTableExists(conn, "LETTO"));
            assertTrue(isTableExists(conn, "RILEVAZIONE"));
            assertTrue(isTableExists(conn, "DIMESSO"));
            assertTrue(isTableExists(conn, "VISITA_INTERVENTO"));
        }
    }

    private boolean isTableExists(Connection conn, String tableName) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            var resultSet = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='" + tableName + "'");
            return resultSet.next();
        }
    }
}
