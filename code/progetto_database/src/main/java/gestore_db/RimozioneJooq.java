package gestore_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import med_db.jooq.generated.tables.*;

/**
 * Classe contenente i metodi per gli inserimenti di tuple all'interno dei database utilizzando JOOQ
 * ogni metodo rimuove dati dalla tabella omonima
 */
public class RimozioneJooq {
	//pattern singleton
	private static RimozioneJooq istanza = new RimozioneJooq();
	
	private RimozioneJooq() {}
	
	public static RimozioneJooq getIstanza() {
		return istanza;
	}
	
	/**
	 * @param codice del membro del personale da rimuovere
	 * @return 1 se la rimozione ha avuto successo, 0 se non è andata a buon fine
	 */
	public int personale(String codice) {
		int result=0;
		try {
			Connection conn = DriverManager.getConnection(CreateDB.DB_URL);
			if (conn != null) {
				DSLContext delete = DSL.using(conn, SQLDialect.SQLITE);
				result = delete.deleteFrom(Personale.PERSONALE).where(Personale.PERSONALE.CODICE.eq(codice)).execute();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	/**
	 * @param ID della rilevazione che si desidera rimuovere
	 * @param codDeg codice del degente a cui è associata
	 * @param countDeg del degente per tenere traccia e distunguere il numero di volte in cui ha fatto accesso all'ospedale
	 * @return 1 se la rimozione ha avuto successo, 0 se non è andata a buon fine
	 */
	public int rilevazione(int ID, String codDeg, int countDeg) {
		int result=0;
		try {
			Connection conn = DriverManager.getConnection(CreateDB.DB_URL);
			if (conn != null) {
				DSLContext delete = DSL.using(conn, SQLDialect.SQLITE);
				result = delete.deleteFrom(Rilevazione.RILEVAZIONE).where(Rilevazione.RILEVAZIONE.ID.eq(ID).and(Rilevazione.RILEVAZIONE.CODICE_DEGENTE.eq(codDeg)).and(Rilevazione.RILEVAZIONE.COUNT_DEGENTE.eq(countDeg))).execute();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	/**
	 * @param codice del reparto che si desidera rimuovere
	 * @return 0 se la rimozione non ha avuto successo, 1+(1 per ogni tabella entità debole associata al reparto in cui sono state eseguite rimozioni, quali modulo, letto e assegnazioneLetto) se la rimozione è avvenuta con successo
	 */
	public int reparto(String codice) {
		int result=0;
		try {
			Connection conn = DriverManager.getConnection(CreateDB.DB_URL);
			if (conn != null) {
				DSLContext delete = DSL.using(conn, SQLDialect.SQLITE);
				//cancello il reparto
				result = delete.deleteFrom(Reparto.REPARTO).where(Reparto.REPARTO.CODICE.eq(codice)).execute();
				
				//cancellazione di entità deboli: modulo, letto e assegnazione letto
				result+= delete.deleteFrom(Modulo.MODULO).where(Modulo.MODULO.CODICE_REPARTO.eq(codice)).execute();
				result+= delete.deleteFrom(Letto.LETTO).where(Letto.LETTO.CODICE_REPARTO.eq(codice)).execute();
				result+= delete.deleteFrom(Assegnazioneletto.ASSEGNAZIONELETTO).where(Assegnazioneletto.ASSEGNAZIONELETTO.CODICE_REPARTO.eq(codice)).execute();
				//il risultato restituito dalla funzione è utilizzato nelle fasi di testing
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	/**
	 * @param codRep codice del reparto che contiene il modulo
	 * @param nome nome del modulo che si desidera rimuovere
	 * @return 0 se la rimozione non ha avuto successo, 1+(1 per ogni tabella entità debole associata al modulo in cui sono state eseguite rimozioni, quali letto e assegnazioneLetto) se la rimozione è avvenuta con successo
	 */
	public int modulo(String codRep, String nome) {
		int result=0;
		try {
			Connection conn = DriverManager.getConnection(CreateDB.DB_URL);
			if (conn != null) {
				DSLContext delete = DSL.using(conn, SQLDialect.SQLITE);
				result = delete.deleteFrom(Modulo.MODULO).where(Modulo.MODULO.CODICE_REPARTO.eq(codRep),Modulo.MODULO.NOME.eq(nome)).execute();
				
				//cancelazione entità deboli: letto e assegnazione letto
				result+= delete.deleteFrom(Letto.LETTO).where(Letto.LETTO.CODICE_REPARTO.eq(codRep),Letto.LETTO.NOME_MODULO.eq(nome)).execute();
				result+= delete.deleteFrom(Assegnazioneletto.ASSEGNAZIONELETTO).where(Assegnazioneletto.ASSEGNAZIONELETTO.NOME_MODULO.eq(nome),Assegnazioneletto.ASSEGNAZIONELETTO.CODICE_REPARTO.eq(codRep)).execute();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	
	/**
	 * @param codRep codice del reparto contenente il letto
	 * @param nomeMod nome del modulo contentente il letto
	 * @param numero del letto che si desidera rimuovere
	 * @return 0 se la rimozione non ha avuto successo, 1+(1 se è stata rimossa un'assegnazioneLetto) se la rimozione è avvenuta con successo
	 */
	public int letto(String codRep, String nomeMod, int numero) {
		int result=0;
		try {
			Connection conn = DriverManager.getConnection(CreateDB.DB_URL);
			if (conn != null) {
				DSLContext delete = DSL.using(conn, SQLDialect.SQLITE);
				result = delete.deleteFrom(Letto.LETTO).where(Letto.LETTO.CODICE_REPARTO.eq(codRep), Letto.LETTO.NOME_MODULO.eq(nomeMod), Letto.LETTO.NUMERO.eq(numero)).execute();
				
				//rimozione dell'entità debole: assegnazione letto
				result+= delete.deleteFrom(Assegnazioneletto.ASSEGNAZIONELETTO).where(Assegnazioneletto.ASSEGNAZIONELETTO.CODICE_REPARTO.eq(codRep), Assegnazioneletto.ASSEGNAZIONELETTO.NOME_MODULO.eq(nomeMod), Assegnazioneletto.ASSEGNAZIONELETTO.NUMERO_LETTO.eq(numero)).execute();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	
	/**
	 * @param codDeg codice del degente assegnato al letto
	 * @param countDeg del degente per tenere traccia e distunguere il numero di volte in cui ha fatto accesso all'ospedale
	 * @param codRep codice del reparto contenente il letto
	 * @param nomeMod nome del modulo contentente il letto
	 * @param numLetto numero del letto
	 * @return 1 se la rimozione ha avuto successo, 0 se non è andata a buon fine
	 */
	public int assegnazioneLetto(String codDeg, int countDeg, String codRep, String nomeMod, int numLetto) {
		int result=0;
		try {
			Connection conn = DriverManager.getConnection(CreateDB.DB_URL);
			if (conn != null) {
				DSLContext delete = DSL.using(conn, SQLDialect.SQLITE);
				result = delete.deleteFrom(Assegnazioneletto.ASSEGNAZIONELETTO).where(Assegnazioneletto.ASSEGNAZIONELETTO.CODICE_DEGENTE.eq(codDeg).and(Assegnazioneletto.ASSEGNAZIONELETTO.COUNT_DEGENTE.eq(countDeg)).and(Assegnazioneletto.ASSEGNAZIONELETTO.CODICE_REPARTO.eq(codRep)).and(Assegnazioneletto.ASSEGNAZIONELETTO.NOME_MODULO.eq(nomeMod)).and(Assegnazioneletto.ASSEGNAZIONELETTO.NUMERO_LETTO.eq(numLetto))).execute();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	/**
	 * @param codice della diaria
	 * @param codDeg codice del degente a cui la diaria è associata
	 * @param countDeg del degente per tenere traccia e distunguere il numero di volte in cui ha fatto accesso all'ospedale
	 * @return 1 se la rimozione ha avuto successo, 0 se non è andata a buon fine
	 */
	public int diariaMed(int codice, String codDeg, int countDeg) {
		int result=0;
		try {
			Connection conn = DriverManager.getConnection(CreateDB.DB_URL);
			if (conn != null) {
				DSLContext delete = DSL.using(conn, SQLDialect.SQLITE);
				result = delete.deleteFrom(Diariamed.DIARIAMED).where(Diariamed.DIARIAMED.CODICE.eq(codice).and(Diariamed.DIARIAMED.CODICE_DEGENTE.eq(codDeg)).and(Diariamed.DIARIAMED.COUNT_DEGENTE.eq(countDeg))).execute();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	/**
	 * @param codice della diaria
	 * @param codDeg codice del degente a cui la diaria è associata
	 * @param countDeg del degente per tenere traccia e distunguere il numero di volte in cui ha fatto accesso all'ospedale
	 * @return 1 se la rimozione ha avuto successo, 0 se non è andata a buon fine
	 */
	public int diariaInf(int codice, String codDeg, int countDeg) {
		int result=0;
		try {
			Connection conn = DriverManager.getConnection(CreateDB.DB_URL);
			if (conn != null) {
				DSLContext delete = DSL.using(conn, SQLDialect.SQLITE);
				result = delete.deleteFrom(Diariainf.DIARIAINF).where(Diariainf.DIARIAINF.CODICE.eq(codice).and(Diariainf.DIARIAINF.CODICE_DEGENTE.eq(codDeg)).and(Diariainf.DIARIAINF.COUNT_DEGENTE.eq(countDeg))).execute();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	/**
	 * @param id identificativo della visita
	 * @return 1 se la rimozione ha avuto successo, 0 se non è andata a buon fine
	 */
	public int visite(int id) {
		int result=0;
		try {
			Connection conn = DriverManager.getConnection(CreateDB.DB_URL);
			if (conn != null) {
				DSLContext delete = DSL.using(conn, SQLDialect.SQLITE);
				result = delete.deleteFrom(VisitaIntervento.VISITA_INTERVENTO).where(VisitaIntervento.VISITA_INTERVENTO.ID.eq(id)).execute();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
}
