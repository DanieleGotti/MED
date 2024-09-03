package gestore_db;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import med_db.jooq.generated.tables.*;
import med_db.jooq.generated.tables.records.*;

/**
 * Classe contenente i metodi per gli inserimenti di tuple all'interno dei database utilizzando JOOQ
 * ogni metodo inserisce dati nella table omonima
 */
public class InserimentoJooq{
	//pattern singleton
	private static InserimentoJooq istanza = new InserimentoJooq();
	
	private InserimentoJooq() {}
	
	public static InserimentoJooq getIstanza() {
		return istanza;
	}
	
	/**
	 * @param codice identificativo del personale, solitamente è "P"+numero
	 * @param nome
	 * @param cognome
	 * @param mansione può solo essere "M" (medico), "I" (infermiere) o "S" (servizio/operatore)
	 * @param password per accedere al login
	 * @param ruolo per cui è stato assunto
	 * @return 1 se l'inserimento ha avuto successo, 0 se non è riuscito
	 */
	public int personale(String codice, String nome, String cognome, String mansione, String password, String ruolo) {
		int result=0;
		try {
			Connection conn = DriverManager.getConnection(CreateDB.DB_URL);
			if (conn != null) {
				if (mansione == "M" || mansione == "I" || mansione == "S") {
					DSLContext create = DSL.using(conn, SQLDialect.SQLITE);

					PersonaleRecord persona = new PersonaleRecord(codice, nome, cognome, mansione, password, ruolo);

					result = create.insertInto(Personale.PERSONALE).set(persona).execute();
					
					String idPersonale = "id_" + codice;
					File cartellaPersonale = new File("./../../personale/" + idPersonale);
					if (!cartellaPersonale.exists()) {
						cartellaPersonale.mkdirs();
						File filePersonale = new File(cartellaPersonale, "dati_anagrafici.txt");
				        try (FileWriter writer = new FileWriter(filePersonale)) {
				            writer.write("La cartella contiene la fototessera identificativa del dipendente.");
				        } catch (IOException e) {
				            System.err.println("Errore durante la creazione o scrittura del file: " + e.getMessage());
				        }
					}
				} else {
					System.out.println("mansione del membro del personale non valida");
				}

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}

	/**
	 * @param codice dovrebbe essere il codice fiscale
	 * @param count del degente per tenere traccia e distunguere il numero di volte in cui ha fatto accesso all'ospedale
	 * @param nome
	 * @param cognome
	 * @param sesso ammessi valori "M" o "F"
	 * @param genere come il paziente si identifica 
	 * @param eta 
	 * @param dataArrivo data arrivo in struttura
	 * @param oraArrivo ora arrivo in struttura
	 * @param urgenza ammessi solo valori "verde", "giallo" e "rosso"
	 * @param posizione sezione ospedaliera in cui si trova il paziente
	 * @return 1 se l'inserimento ha avuto successo, 0 se non è riuscito
	 */
	public int degente(String codice, int count, String nome, String cognome, String sesso, String genere, int eta, LocalDate dataArrivo, LocalTime oraArrivo,  String urgenza, String posizione) {
		int result=0;
		try {
			Connection conn = DriverManager.getConnection(CreateDB.DB_URL);
			if (conn != null) {
				if ((urgenza.equals("verde") || urgenza.equals("giallo") || urgenza.equals("rosso")) && (sesso.equals("M") || sesso.equals("F"))) {
					DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
					//il degente è in pronto soccorso all'inserimento nel DB
					DegenteRecord degente = new DegenteRecord(codice, count, nome, cognome, sesso, genere, eta, dataArrivo, oraArrivo, urgenza, posizione);

					result = create.insertInto(Degente.DEGENTE).set(degente).execute();
					//creazione delle cartelle
					String idPaziente = "id_" + codice;
					File cartellaPaziente = new File("./../../pazienti/" + idPaziente);
					if (!cartellaPaziente.exists()) {
						cartellaPaziente.mkdirs();
					}
					
					File cartellaDimissione = new File("./../../pazienti/" + idPaziente + "/dati_dimissione");
					if (!cartellaDimissione.exists()) {
						cartellaDimissione.mkdirs();
					}
					File fileDimissione = new File(cartellaDimissione, "dati_dimissione.txt");
					if (!fileDimissione.exists()) {
						fileDimissione.createNewFile();
						try (FileWriter writer = new FileWriter(fileDimissione)) {
				            writer.write("La cartella dati_dimissione contiene la lettera di dimissione e altri documenti relativi alle dimissioni.");
				        } catch (IOException e) {
				            System.err.println("Errore durante la creazione o scrittura del file: " + e.getMessage());
				        }
					}

					File cartellaAnagrafica = new File("./../../pazienti/" + idPaziente + "/dati_anagrafici");
					if (!cartellaAnagrafica.exists()) {
						cartellaAnagrafica.mkdirs();
					}
					File fileAnagrafica = new File(cartellaAnagrafica, "dati_anagrafici.txt");
					if (!fileAnagrafica.exists()) {
						fileAnagrafica.createNewFile();
				        try (FileWriter writer = new FileWriter(fileAnagrafica)) {
				            writer.write("La cartella dati_anagrafici contiene la fototessera identificativa del paziente.");
				        } catch (IOException e) {
				            System.err.println("Errore durante la creazione o scrittura del file: " + e.getMessage());
				        }
					}
					
					File cartellaEsamiVisite = new File("./../../pazienti/" + idPaziente + "/dati_visite_e_interventi");
					if (!cartellaEsamiVisite.exists()) {
						cartellaEsamiVisite.mkdirs();
					}
					File fileEsami = new File(cartellaEsamiVisite, "dati_visite_e_interventi.txt");
					if (!fileEsami.exists()) {
						fileEsami.createNewFile();
				        try (FileWriter writer = new FileWriter(fileEsami)) {
				            writer.write("La cartella dati_visite_e_interventi contiene tutti i documenti relativi a visite e interventi, inclusi referti, consensi informati, ecc.");
				        } catch (IOException e) {
				            System.err.println("Errore durante la creazione o scrittura del file: " + e.getMessage());
				        }
					}
					
					File cartellaDocumenti = new File("./../../pazienti/" + idPaziente + "/documenti_caricati_durante_la_degenza");
					if (!cartellaDocumenti.exists()) {
						cartellaDocumenti.mkdirs();
					}
					File fileDocumenti = new File(cartellaDocumenti, "documenti_caricati_durante_la_degenza.txt");
					if (!fileDocumenti.exists()) {
						fileDocumenti.createNewFile();
				        try (FileWriter writer = new FileWriter(fileDocumenti)) {
				            writer.write("La cartella dati_visite_e_interventi contiene tutti i documenti relativi al periodo di degenza, inclusi piani terapeutici, prescrizioni mediche, ecc.");
				        } catch (IOException e) {
				            System.err.println("Errore durante la creazione o scrittura del file: " + e.getMessage());
				        }
					}
				} else {
					System.out.println("urgenza e/o sesso inseriti non validi");
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * @param ID numero della rilevazione, relativa al singolo paziente
	 * @param codDeg deve essere già presente nel database per l'inserimento
	 * @param countDeg del degente per tenere traccia e distunguere il numero di volte in cui ha fatto accesso all'ospedale
	 * @param codInf codice dell'infermiere che ha eseguito la rilevazione
	 * @param temp temperatura corporea
	 * @param pressMax pressione massima
	 * @param pressMin pressione minima
	 * @param glicem glicemia
	 * @param data
	 * @param ora
	 * @param freqCard frequenza cardiaca
	 * @param dol si presuppone abbia un valore compreso tra 1 e 10
	 * @return 1 se l'inserimento ha avuto successo, 0 se non è riuscito
	 */
	public int rilevazione(int ID, String codDeg, int countDeg, String codInf, double temp, int pressMax,int pressMin, int glicem,LocalDate data, LocalTime ora, int freqCard, int dol) {
		int result=0;
		try {
		Connection conn = DriverManager.getConnection(CreateDB.DB_URL);
		if (conn != null) {
			DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
			if (create.select(Degente.DEGENTE.CODICE).from(Degente.DEGENTE).where(Degente.DEGENTE.CODICE.eq(codDeg).and(Degente.DEGENTE.COUNT.eq(countDeg))).fetch().isNotEmpty()) {
				RilevazioneRecord rilevazione = new RilevazioneRecord(ID, codDeg, countDeg, codInf, temp, pressMax, pressMin, glicem, data, ora, freqCard, dol);
				result = create.insertInto(Rilevazione.RILEVAZIONE).set(rilevazione).execute();	
			}
			

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return result;
}

	/**
	 * @param codice con la forma "Re"+numero crescente
	 * @param nome
	 * @return 1 se l'inserimento ha avuto successo, 0 se non è riuscito
	 */
	public int reparto(String codice, String nome) {
		int result=0;
		try {
			Connection conn = DriverManager.getConnection(CreateDB.DB_URL);
			if (conn != null) {
				DSLContext create = DSL.using(conn, SQLDialect.SQLITE);

				RepartoRecord reparto = new RepartoRecord(codice, nome);

				result = create.insertInto(Reparto.REPARTO).set(reparto).execute();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	/**
	 * @param codRep deve essere il codice di un reparto già nel database
	 * @param nome "Modulo"+ lettera a partire dalla "A", si presuppone di non avere più di 3 moduli per ogni reparto
	 * @return 1 se l'inserimento ha avuto successo, 0 se non è riuscito
	 */
	public int modulo(String codRep, String nome) {
		int result=0;
		try {
			Connection conn = DriverManager.getConnection(CreateDB.DB_URL);
			if (conn != null) {
				DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
				if (create.select(Reparto.REPARTO.CODICE).from(Reparto.REPARTO).where(Reparto.REPARTO.CODICE.eq(codRep)).fetch().isNotEmpty()) {
					ModuloRecord modulo = new ModuloRecord(codRep, nome);
					result = create.insertInto(Modulo.MODULO).set(modulo).execute();	
					}
				}
			}catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		return result;
	}
	
	/**
	 * @param codRep deve essere già presente nel database
	 * @param nomeMod deve essere già presente nel database
	 * @param numero da 1 a 15
	 * @return 1 se l'inserimento ha avuto successo, 0 se non è riuscito
	 */
	public int letto(String codRep, String nomeMod, int numero) {
		int result=0;
		try {
			Connection conn = DriverManager.getConnection(CreateDB.DB_URL);
			if (conn != null) {
				DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
				if (create.select(Modulo.MODULO.NOME).from(Modulo.MODULO).where(Modulo.MODULO.CODICE_REPARTO.eq(codRep), Modulo.MODULO.NOME.eq(nomeMod)).fetch().isNotEmpty()) {

				LettoRecord letto = new LettoRecord(codRep, nomeMod, numero);
				result = create.insertInto(Letto.LETTO).set(letto).execute();
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	
	/**
	 * @param codDeg degente assegnato al letto
	 * @param countDeg del degente per tenere traccia e distunguere il numero di volte in cui ha fatto accesso all'ospedale
	 * @param codRep reparto del letto
	 * @param nomeMod modulo del letto
	 * @param numero
	 * @param dataAss data di assegnazione del letto
	 * @return 1 se l'inserimento ha avuto successo, 0 se non è riuscito
	 */
	public int assegnazioneLetto(String codDeg, int countDeg, String codRep, String nomeMod, int numero, LocalDate dataAss) {
		int result=0;
		try {
			Connection conn = DriverManager.getConnection(CreateDB.DB_URL);
			if (conn != null) {
				DSLContext create = DSL.using(conn, SQLDialect.SQLITE);

				//se il degente scelto non esiste è impossibile assegnargli un letto, allo stesso modo non si può assegnare un letto che non esiste
				if (create.select(Degente.DEGENTE.CODICE).from(Degente.DEGENTE).where(Degente.DEGENTE.CODICE.eq(codDeg).and(Degente.DEGENTE.COUNT.eq(countDeg))).fetch().isNotEmpty() &&
						create.select(Letto.LETTO.NUMERO).from(Letto.LETTO).where(Letto.LETTO.CODICE_REPARTO.eq(codRep), Letto.LETTO.NOME_MODULO.eq(nomeMod),Letto.LETTO.NUMERO.eq(numero)).fetch().isNotEmpty()) {
					
					AssegnazionelettoRecord assLetto = new AssegnazionelettoRecord(codDeg, countDeg, codRep, nomeMod, numero, dataAss);
					result = create.insertInto(Assegnazioneletto.ASSEGNAZIONELETTO).set(assLetto).execute();
					//se assegno un letto, il degente non è più in attesa, bensì in reparto
					AggiornamentiJooq.getIstanza().degente(codDeg, countDeg, "posizione", "in Reparto");
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}	
		return result;
	}
	
	/**
	 * @param codice identificativo della diaria, parte da 1 per ogni degente
	 * @param codiceDegente
	 * @param countDegente del degente per tenere traccia e distunguere il numero di volte in cui ha fatto accesso all'ospedale
	 * @param codiceInfermiere
	 * @param data
	 * @param ora
	 * @param noteParticolari
	 * @param importante 
	 * @param farmaco indica uno o più farmaci somministrati durante la cura
	 * @return 1 se l'inserimento ha avuto successo, 0 se non è riuscito
	 */
	public int diariaInf(int codice, String codiceDegente, int countDegente, String codiceInfermiere, LocalDate data, LocalTime ora, String noteParticolari, Boolean importante, String farmaco) {
		int result=0;
		try {
			Connection conn = DriverManager.getConnection(CreateDB.DB_URL);
			if (conn != null) {
				DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
				
				//se il degente scelto non esiste è impossibile aggiungergli una diaria
				if (create.select(Degente.DEGENTE.CODICE).from(Degente.DEGENTE).where(Degente.DEGENTE.CODICE.eq(codiceDegente).and(Degente.DEGENTE.COUNT.eq(countDegente))).fetch().isNotEmpty()) {
					DiariainfRecord diariaInf = new DiariainfRecord(codice, codiceDegente, countDegente, codiceInfermiere, data, ora, noteParticolari, importante, farmaco);
					result = create.insertInto(Diariainf.DIARIAINF).set(diariaInf).execute();
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	/**
	 * @param codice identificativo della diaria, parte da 1 per ogni degente
	 * @param codiceDegente
	 * @param countDegente del degente per tenere traccia e distunguere il numero di volte in cui ha fatto accesso all'ospedale
	 * @param codiceMedico
	 * @param storico eventuali condizioni pregresse del paziente
	 * @param motivo ragione del ricovero
	 * @param reparto reparto consigliato dal medico, non obbligatoriamente è quello che sarà poi assegnato
	 * @param farmaci prescritti per la cura
	 * @param data
	 * @param ora
	 * @param allergie
	 * @return 1 se l'inserimento ha avuto successo, 0 se non è riuscito
	 */
	public int diariaMed(int codice, String codiceDegente, int countDegente, String codiceMedico, String storico, String motivo, String reparto, String farmaci, LocalDate data, LocalTime ora, String allergie) {
		int result=0;
		try {
			Connection conn = DriverManager.getConnection(CreateDB.DB_URL);
			if (conn != null) {
				DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
				if (create.select(Degente.DEGENTE.CODICE).from(Degente.DEGENTE).where(Degente.DEGENTE.CODICE.eq(codiceDegente).and(Degente.DEGENTE.COUNT.eq(countDegente))).fetch().isNotEmpty()) {
					DiariamedRecord diariaMed = new DiariamedRecord(codice, codiceDegente, countDegente, codiceMedico, storico, motivo, reparto,farmaci, data, ora, allergie);

					result = create.insertInto(Diariamed.DIARIAMED).set(diariaMed).execute();
					//alla scrittura della diaria medica, il degente viene posto in attesa finchè non gli viene assegnato il letto
					AggiornamentiJooq.getIstanza().degente(codiceDegente, countDegente, "posizione", "in Attesa");
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	/**
	 * @param id identificativo della dimissione
	 * @param codiceDegente
	 * @param codiceMedico
	 * @param countDegente del degente per tenere traccia e distunguere il numero di volte in cui ha fatto accesso all'ospedale
	 * @param dataDimissione
	 * @param oraDimissione
	 * @param motivo di dimissione
	 * @return 1 se l'inserimento ha avuto successo, 0 se non è riuscito
	 */
	public int dimesso(int id, String codiceDegente, String codiceMedico, int countDegente, LocalDate dataDimissione, LocalTime oraDimissione,  String motivo) {
		int result=0;
		try {
			Connection conn = DriverManager.getConnection(CreateDB.DB_URL);
			if (conn != null) {
				DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
				DimessoRecord dimesso = new DimessoRecord(id, codiceDegente, countDegente, codiceMedico, motivo, dataDimissione, oraDimissione);
				result = create.insertInto(Dimesso.DIMESSO).set(dimesso).execute();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	/**
	 * @param id identificativo della visita
	 * @param tipo "Visita" o "Intervento"
	 * @param codiceMedico
	 * @param countDegente del degente per tenere traccia e distunguere il numero di volte in cui ha fatto accesso all'ospedale
	 * @param dataPrenotazione
	 * @param oraPrenotazione
	 * @param motivo della prenotazione
	 * @return 1 se l'inserimento ha avuto successo, 0 se non è riuscito
	 */
	public int visitaIntervento(int id, String tipo, String codiceDegente, String codiceMedico, int countDegente, LocalDate dataPrenotazione, LocalTime oraPrenotazione,  String motivo) {
		int result=0;
		try {
			Connection conn = DriverManager.getConnection(CreateDB.DB_URL);
			if (conn != null) {
				DSLContext create = DSL.using(conn, SQLDialect.SQLITE);
				VisitaInterventoRecord visitaIntervento = new VisitaInterventoRecord(id, tipo, codiceDegente, countDegente, codiceMedico, motivo, dataPrenotazione, oraPrenotazione);
				result = create.insertInto(VisitaIntervento.VISITA_INTERVENTO).set(visitaIntervento).execute();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
}
