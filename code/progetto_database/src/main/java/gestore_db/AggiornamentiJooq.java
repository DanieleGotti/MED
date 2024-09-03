package gestore_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import med_db.jooq.generated.tables.Degente;
import med_db.jooq.generated.tables.Diariainf;
import med_db.jooq.generated.tables.Diariamed;
import med_db.jooq.generated.tables.Personale;
import med_db.jooq.generated.tables.VisitaIntervento;

/**
 * Classe contenente i metodi per le modifiche dei dati nelle tuple all'interno dei database utilizzando JOOQ
 * ogni metodo apporta modifiche alla tabella omonima
 * non tutte le tabelle ammettono modifiche
 */
public class AggiornamentiJooq {
	//pattern singleton
	private static AggiornamentiJooq istanza=new AggiornamentiJooq();
	
	private AggiornamentiJooq() {};
	
	public static AggiornamentiJooq getIstanza() {
		return istanza;
	}

	/**
	 * @param ID codice del personale a cui si intende apportare modifiche
	 * @param newVal nuovo valore della password, l'unico attributo modificabile
	 * @return 1 se la modifica ha avuto successo, 0 se non è andata a buon fine
	 */
	public int personale(String ID, String newVal) {
		int result=0;
		try {
			Connection conn = DriverManager.getConnection(CreateDB.DB_URL);
			if (conn != null) {
				//nel caso del personale non si può modificare altro che la password, non serve includere un attributo per la selezione
				DSLContext cambiaPW = DSL.using(conn, SQLDialect.SQLITE);
				result = cambiaPW.update(Personale.PERSONALE).set(Personale.PERSONALE.PASSWORD, newVal).where(Personale.PERSONALE.CODICE.eq(ID)).execute();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	/**
	 * @param codice del degente di cui si intende modificare i dati
	 * @param count del degente per tenere traccia e distunguere il numero di volte in cui ha fatto accesso all'ospedale
	 * @param attr permette la selezione tra gli attributi: "urgenza", "posizione" e "sesso"
	 * @param newVal deve rispettare le limitazioni imposte dal tipo di attributo selezionato
	 * @return 1 se la modifica ha avuto successo, 0 se non è andata a buon fine
	 */
	public int degente(String codice, int count, String attr, String newVal) {
		int result=0;
		try {
			Connection conn = DriverManager.getConnection(CreateDB.DB_URL);
			if (conn != null) {
				DSLContext cambiaVal = DSL.using(conn, SQLDialect.SQLITE);
				
				/*in base all'attributo scelto si modifica 
				 * il relativo campo del degente identificato dal codice*/
				switch(attr) {
				case "urgenza":
					if(newVal=="verde" || newVal=="giallo" || newVal=="rosso") {
						result= cambiaVal.update(Degente.DEGENTE).set(Degente.DEGENTE.URGENZA, newVal).where(Degente.DEGENTE.CODICE.eq(codice).and(Degente.DEGENTE.COUNT.eq(count))).execute();
					}
					else
						System.out.println("Urgenza richiesta non ammessa");
					
					break;
					
				case "posizione": //deve essere chiamata SOLO all'inserimento di un letto o di una diaria medica
					result= cambiaVal.update(Degente.DEGENTE).set(Degente.DEGENTE.POSIZIONE, newVal).where(Degente.DEGENTE.CODICE.eq(codice).and(Degente.DEGENTE.COUNT.eq(count))).execute();
					break;
					
				case "sesso":
					if(newVal=="M" || newVal=="F") {
						result= cambiaVal.update(Degente.DEGENTE).set(Degente.DEGENTE.SESSO, newVal).where(Degente.DEGENTE.CODICE.eq(codice).and(Degente.DEGENTE.COUNT.eq(count))).execute();	
					}
					else
						System.out.println("Sesso del paziente richiesto non ammesso");
					break;
					
				case "nome":
						result= cambiaVal.update(Degente.DEGENTE).set(Degente.DEGENTE.NOME, newVal).where(Degente.DEGENTE.CODICE.eq(codice).and(Degente.DEGENTE.COUNT.eq(count))).execute();
					break;
				
				case "cognome":
					result= cambiaVal.update(Degente.DEGENTE).set(Degente.DEGENTE.COGNOME, newVal).where(Degente.DEGENTE.CODICE.eq(codice).and(Degente.DEGENTE.COUNT.eq(count))).execute();
				break;
				
				case "eta":
					result= cambiaVal.update(Degente.DEGENTE).set(Degente.DEGENTE.ETA, Integer.parseInt(newVal)).where(Degente.DEGENTE.CODICE.eq(codice).and(Degente.DEGENTE.COUNT.eq(count))).execute();
				break;
				
				case "genere":
					result= cambiaVal.update(Degente.DEGENTE).set(Degente.DEGENTE.GENERE, newVal).where(Degente.DEGENTE.CODICE.eq(codice).and(Degente.DEGENTE.COUNT.eq(count))).execute();
				break;
				
				default:
					System.out.println("Attributo da modificare selezionato non valido");
					break;
				}
		
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	
	/**
	 * @param codice della diaria
	 * @param codDeg del degente relativo alla diaria
	 * @param countDeg del degente per tenere traccia e distunguere il numero di volte in cui ha fatto accesso all'ospedale
	 * @param newVal "true" o "false", si può solo alterare il flag di importanza, gli altri dati devono restare invariabili
	 * @return 1 se la modifica ha avuto successo, 0 se non è andata a buon fine
	 */
	public int diariaInf(int codice ,String codDeg, int countDeg, String newVal) {
		int result=0;
		try {
			Connection conn = DriverManager.getConnection(CreateDB.DB_URL);
			if (conn != null) {
				DSLContext cambiaVal = DSL.using(conn, SQLDialect.SQLITE);
				/*l'unico attributo per cui è ammissibile la modifica è il flag d'importanza
				 non è necessario includere una variabile di selezione*/
				if(newVal=="true" || newVal=="false") {
					result= cambiaVal.update(Diariainf.DIARIAINF).set(Diariainf.DIARIAINF.IMPORTANTE, Boolean.parseBoolean(newVal)).where(Diariainf.DIARIAINF.CODICE_DEGENTE.eq(codDeg).and(Diariainf.DIARIAINF.CODICE.eq(codice)).and(Diariainf.DIARIAINF.COUNT_DEGENTE.eq(countDeg))).execute();	
				}
				
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	/**
	 * @param codice della diaria
	 * @param codDeg del degente a cui è associata la diaria medica
	 * @param countDeg del degente per tenere traccia e distunguere il numero di volte in cui ha fatto accesso all'ospedale
	 * @param attr permette la selezione tra gli attributi: "storico", "motivo", "farmaci" e "allergie"
	 * @param newVal nuovo valore dell'attributo selezionato
	 * @return 1 se la modifica ha avuto successo, 0 se non è andata a buon fine
	 */
	public int diariaMed(int codice ,String codDeg, int countDeg, String attr, String newVal) {
		int result=0;
		try {
			Connection conn = DriverManager.getConnection(CreateDB.DB_URL);
			if (conn != null) {
				DSLContext cambiaVal = DSL.using(conn, SQLDialect.SQLITE);
				
				switch(attr) {
				/* è possibile aggiornare diversi attributi su diariaMed,
				 * dunque è necassario un parametro attributo per la selezione
				   per ogni modifica viene aggiornata anche la data e l'ora a quella corrente*/
				
				case "storico":
					result= cambiaVal.update(Diariamed.DIARIAMED).set(Diariamed.DIARIAMED.STORICO, newVal).set(Diariamed.DIARIAMED.DATA, LocalDate.now()).set(Diariamed.DIARIAMED.ORA, LocalTime.now().withNano(0)).where(Diariamed.DIARIAMED.CODICE_DEGENTE.eq(codDeg).and(Diariamed.DIARIAMED.COUNT_DEGENTE.eq(countDeg)).and(Diariamed.DIARIAMED.CODICE.eq(codice))).execute();
					break;
					
				case "motivo":
					result= cambiaVal.update(Diariamed.DIARIAMED).set(Diariamed.DIARIAMED.MOTIVO, newVal).set(Diariamed.DIARIAMED.DATA, LocalDate.now()).set(Diariamed.DIARIAMED.ORA, LocalTime.now().withNano(0)).where(Diariamed.DIARIAMED.CODICE_DEGENTE.eq(codDeg).and(Diariamed.DIARIAMED.COUNT_DEGENTE.eq(countDeg)).and(Diariamed.DIARIAMED.CODICE.eq(codice))).execute();
					break;
					
				case "farmaci":
					result= cambiaVal.update(Diariamed.DIARIAMED).set(Diariamed.DIARIAMED.FARMACI, newVal).set(Diariamed.DIARIAMED.DATA, LocalDate.now()).set(Diariamed.DIARIAMED.ORA, LocalTime.now().withNano(0)).where(Diariamed.DIARIAMED.CODICE_DEGENTE.eq(codDeg).and(Diariamed.DIARIAMED.COUNT_DEGENTE.eq(countDeg)).and(Diariamed.DIARIAMED.CODICE.eq(codice))).execute();
					break;
					
				case "allergie":
					result= cambiaVal.update(Diariamed.DIARIAMED).set(Diariamed.DIARIAMED.ALLERGIE, newVal).set(Diariamed.DIARIAMED.DATA, LocalDate.now()).set(Diariamed.DIARIAMED.ORA, LocalTime.now().withNano(0)).where(Diariamed.DIARIAMED.CODICE_DEGENTE.eq(codDeg).and(Diariamed.DIARIAMED.COUNT_DEGENTE.eq(countDeg)).and(Diariamed.DIARIAMED.CODICE.eq(codice))).execute();
					break;
					
				case "medico":
					result= cambiaVal.update(Diariamed.DIARIAMED).set(Diariamed.DIARIAMED.CODICE_MEDICO, newVal).set(Diariamed.DIARIAMED.DATA, LocalDate.now()).set(Diariamed.DIARIAMED.ORA, LocalTime.now().withNano(0)).where(Diariamed.DIARIAMED.CODICE_DEGENTE.eq(codDeg).and(Diariamed.DIARIAMED.COUNT_DEGENTE.eq(countDeg)).and(Diariamed.DIARIAMED.CODICE.eq(codice))).execute();
					break;
					
				default:
					System.out.println("Attributo da modificare selezionato non valido");
					break;
				}
		
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public int visitaIntervento(int id ,LocalDate dataPrenotazione, LocalTime oraPrenotazione, String motivo, String medico) {
		int result=0;
		try {
			Connection conn = DriverManager.getConnection(CreateDB.DB_URL);
			if (conn != null) {
				DSLContext cambiaVal = DSL.using(conn, SQLDialect.SQLITE);
				result = cambiaVal.update(VisitaIntervento.VISITA_INTERVENTO).set(VisitaIntervento.VISITA_INTERVENTO.MOTIVO, motivo).where(VisitaIntervento.VISITA_INTERVENTO.ID.eq(id)).execute();
				result = cambiaVal.update(VisitaIntervento.VISITA_INTERVENTO).set(VisitaIntervento.VISITA_INTERVENTO.DATA_PRENOTAZIONE, dataPrenotazione).where(VisitaIntervento.VISITA_INTERVENTO.ID.eq(id)).execute();
				result = cambiaVal.update(VisitaIntervento.VISITA_INTERVENTO).set(VisitaIntervento.VISITA_INTERVENTO.ORA_PRENOTAZIONE, oraPrenotazione).where(VisitaIntervento.VISITA_INTERVENTO.ID.eq(id)).execute();
				result = cambiaVal.update(VisitaIntervento.VISITA_INTERVENTO).set(VisitaIntervento.VISITA_INTERVENTO.CODICE_MEDICO, medico).where(VisitaIntervento.VISITA_INTERVENTO.ID.eq(id)).execute();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

}
