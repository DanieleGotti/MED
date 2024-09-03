package logiche_frame_sezioni_ospedaliere;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import org.jooq.DSLContext;
import org.jooq.Record10;
import org.jooq.Record11;
import org.jooq.Record12;
import org.jooq.Record13;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import gui.PazientiFrame;
import med_db.jooq.generated.tables.Assegnazioneletto;
import med_db.jooq.generated.tables.Degente;
import med_db.jooq.generated.tables.Dimesso;
import med_db.jooq.generated.tables.Reparto;
import med_db.jooq.generated.tables.VisitaIntervento;
import modelli.ModelloGestoreLogicaGenerale;

public class LogicaDellaPosizionePazienteTabella extends LogicaFrame{

	private String filtro;
	
	/**
	 * Classe che tiene conto dei dati del paziente presenti nel database,
	 * permettendone la visualizzazione a schermo in base alla sezione selezionata del programma
	 * Nel caso in cui la sezione attiva sia "in reparto", permette di visualizzare anche i dati relativi al letto assegnato a ogni degente
	 * @param p riferimento agli elementi grafici
	 * @param m riferimento al modello generale
	 * @param filtro corrisponde alla sezione attiva correntemente nel programma, 
	 * definisce quali degenti devono essere mostrati a schermo in un determinato momento
	 */
	public LogicaDellaPosizionePazienteTabella(PazientiFrame p, ModelloGestoreLogicaGenerale m, String filtro) {
		super(p,m);
		this.filtro=filtro;
	}
	
	/**
	 * Ogni qualvolta venga selezionata una nuova sezione o effettuata una modifica ai dati dei pazienti
	 * viene ricaricata la lista di degenti nella sezione attiva, utilizzando i dati aggiornati presenti nel database
	 */
	public void update() {
		if(!frameDeiPazienti.updating) {
			try {
				List<String> nomi = new ArrayList<>();
				List<String> codice = new ArrayList<>();
	            List<String> cognomi = new ArrayList<>();
	            List<String> sesso = new ArrayList<>();
	            List<LocalDate> dateArrivo = new ArrayList<>();
	            List<LocalTime> oreArrivo = new ArrayList<>();
	            List<String> urgenza = new ArrayList<>();
	            List<String> reparto = new ArrayList<>();
	            List<String> modulo = new ArrayList<>();
	            List<Integer> letto = new ArrayList<>();
	            List<String> genere = new ArrayList<>();
	            List<Integer> eta = new ArrayList<>();
	            List<Integer> count = new ArrayList<>();
	            
	            List<String> tipo = new ArrayList<>();
	            List<String> medicoVisita = new ArrayList<>();
	            List<String> motivoVisita = new ArrayList<>();
	            List<LocalDate> dataPrenotazione = new ArrayList<>();
	            List<LocalTime> oraPrenotazione = new ArrayList<>();
	            
	        	List<String> medicoDimissione = new ArrayList<>();
	        	List<String> motivoDimissione = new ArrayList<>();
	        	List<LocalDate> dataDimissione = new ArrayList<>();
	        	List<LocalTime> oraDimissione = new ArrayList<>();
				Connection conn = DriverManager.getConnection(DB_URLLOGIC);
				
				if (conn != null) {
					DSLContext contesto = DSL.using(conn, SQLDialect.SQLITE);
					if (contesto.select(Degente.DEGENTE).from(Degente.DEGENTE,Assegnazioneletto.ASSEGNAZIONELETTO).where(Degente.DEGENTE.POSIZIONE.eq(filtro),Degente.DEGENTE.CODICE.eq(Assegnazioneletto.ASSEGNAZIONELETTO.CODICE_DEGENTE)).fetch().isNotEmpty()) {
						Result<Record13<String, String, String, LocalDate, LocalTime, String, String, String, String, Integer, String, Integer, Integer>> degenti = contesto.selectDistinct(Degente.DEGENTE.NOME,Degente.DEGENTE.COGNOME,Degente.DEGENTE.SESSO,Degente.DEGENTE.DATA_ARRIVO,Degente.DEGENTE.ORA_ARRIVO,Degente.DEGENTE.URGENZA,Degente.DEGENTE.CODICE,Reparto.REPARTO.NOME_REPARTO,Assegnazioneletto.ASSEGNAZIONELETTO.NOME_MODULO,Assegnazioneletto.ASSEGNAZIONELETTO.NUMERO_LETTO,Degente.DEGENTE.GENERE,Degente.DEGENTE.ETA,Degente.DEGENTE.COUNT).from(Degente.DEGENTE,Reparto.REPARTO,Assegnazioneletto.ASSEGNAZIONELETTO).where(Degente.DEGENTE.POSIZIONE.eq(filtro),Degente.DEGENTE.CODICE.eq(Assegnazioneletto.ASSEGNAZIONELETTO.CODICE_DEGENTE).and(Degente.DEGENTE.COUNT.eq(Assegnazioneletto.ASSEGNAZIONELETTO.COUNT_DEGENTE)),Assegnazioneletto.ASSEGNAZIONELETTO.CODICE_REPARTO.eq(Reparto.REPARTO.CODICE)).fetch();
						for (Record13<String, String, String, LocalDate, LocalTime, String, String, String, String, Integer, String, Integer, Integer> degenteRecord : degenti) {
							nomi.add(degenteRecord.value1());
						    cognomi.add(degenteRecord.value2());
						    sesso.add(degenteRecord.value3());
						    dateArrivo.add(degenteRecord.value4());
						    oreArrivo.add(degenteRecord.value5());
						    urgenza.add(degenteRecord.value6());
						    codice.add(degenteRecord.value7());
						    reparto.add(degenteRecord.value8());
						    modulo.add(degenteRecord.value9());
						    letto.add(degenteRecord.value10());
						    genere.add(degenteRecord.value11());
						    eta.add(degenteRecord.value12());
						    count.add(degenteRecord.value13());
						    tipo.add(null);
						    medicoVisita.add("No prenotazioni");
						    motivoVisita.add("No prenotazioni");
						    dataPrenotazione.add(null);
						    oraPrenotazione.add(null);
						    medicoDimissione.add("Non dimesso");
						    motivoDimissione.add("Non dimesso");
						    dataDimissione.add(null);
						    oraDimissione.add(null);
						}
					}
					else if (contesto.select(Degente.DEGENTE).from(Degente.DEGENTE,Assegnazioneletto.ASSEGNAZIONELETTO).where(Degente.DEGENTE.POSIZIONE.eq(filtro),Degente.DEGENTE.CODICE.eq(Assegnazioneletto.ASSEGNAZIONELETTO.CODICE_DEGENTE)).fetch().isEmpty()){
						Result<Record10<String, String, String, LocalDate, LocalTime, String, String, String, Integer, Integer>> degenti = contesto.select(Degente.DEGENTE.NOME,Degente.DEGENTE.COGNOME,Degente.DEGENTE.SESSO,Degente.DEGENTE.DATA_ARRIVO,Degente.DEGENTE.ORA_ARRIVO,Degente.DEGENTE.URGENZA,Degente.DEGENTE.CODICE,Degente.DEGENTE.GENERE,Degente.DEGENTE.ETA,Degente.DEGENTE.COUNT).from(Degente.DEGENTE).where(Degente.DEGENTE.POSIZIONE.eq(filtro)).fetch();
						for (Record10<String, String, String, LocalDate, LocalTime, String,String, String, Integer, Integer> degenteRecord : degenti) {
							nomi.add(degenteRecord.value1());
						    cognomi.add(degenteRecord.value2());
						    sesso.add(degenteRecord.value3());
						    dateArrivo.add(degenteRecord.value4());
						    oreArrivo.add(degenteRecord.value5());
						    urgenza.add(degenteRecord.value6());
						    codice.add(degenteRecord.value7());
						    modulo.add("Nessun modulo");
						    reparto.add("Nessun reparto");
						    letto.add(0);
						    genere.add(degenteRecord.value8());
						    eta.add(degenteRecord.value9());
						    count.add(degenteRecord.value10());
						    tipo.add(null);
						    medicoVisita.add("No prenotazioni");
						    motivoVisita.add("No prenotazioni");
						    dataPrenotazione.add(null);
						    oraPrenotazione.add(null);
						    medicoDimissione.add("Non dimesso");
						    motivoDimissione.add("Non dimesso");
						    dataDimissione.add(null);
						    oraDimissione.add(null);
						}
					}
					
					if (contesto.select(Degente.DEGENTE).from(Degente.DEGENTE,VisitaIntervento.VISITA_INTERVENTO).where(Degente.DEGENTE.POSIZIONE.eq(filtro),Degente.DEGENTE.CODICE.eq(VisitaIntervento.VISITA_INTERVENTO.CODICE_DEGENTE),Degente.DEGENTE.COUNT.eq(VisitaIntervento.VISITA_INTERVENTO.COUNT_DEGENTE)).fetch().isNotEmpty()) {
						Result<Record12<String, String, String, Integer, String, String, String, LocalDate, LocalTime, String, String, Integer>> degenti = contesto.selectDistinct(Degente.DEGENTE.NOME,Degente.DEGENTE.COGNOME,Degente.DEGENTE.CODICE,Degente.DEGENTE.COUNT,VisitaIntervento.VISITA_INTERVENTO.TIPO,VisitaIntervento.VISITA_INTERVENTO.CODICE_MEDICO,VisitaIntervento.VISITA_INTERVENTO.MOTIVO,VisitaIntervento.VISITA_INTERVENTO.DATA_PRENOTAZIONE,VisitaIntervento.VISITA_INTERVENTO.ORA_PRENOTAZIONE,Degente.DEGENTE.SESSO,Degente.DEGENTE.GENERE,Degente.DEGENTE.ETA).from(Degente.DEGENTE,VisitaIntervento.VISITA_INTERVENTO).where(Degente.DEGENTE.CODICE.eq(VisitaIntervento.VISITA_INTERVENTO.CODICE_DEGENTE)).and(Degente.DEGENTE.COUNT.eq(VisitaIntervento.VISITA_INTERVENTO.COUNT_DEGENTE)).and(Degente.DEGENTE.POSIZIONE.eq(filtro)).fetch();
						for (Record12<String, String, String, Integer, String, String, String, LocalDate, LocalTime, String, String, Integer> degenteRecord : degenti) {
							nomi.add(degenteRecord.value1());
						    cognomi.add(degenteRecord.value2());
						    sesso.add(degenteRecord.value10());
						    dateArrivo.add(null);
						    oreArrivo.add(null);
						    urgenza.add(null);
						    codice.add(degenteRecord.value3());
						    modulo.add(null);
						    reparto.add(null);
						    letto.add(0);
						    genere.add(degenteRecord.value11());
						    eta.add(degenteRecord.value12());
						    count.add(degenteRecord.value4());
						    tipo.add(degenteRecord.value5());
						    medicoVisita.add(degenteRecord.value6());
						    motivoVisita.add(degenteRecord.value7());
						    dataPrenotazione.add(degenteRecord.value8());
						    oraPrenotazione.add(degenteRecord.value9());
						    medicoDimissione.add("Non dimesso");
						    motivoDimissione.add("Non dimesso");
						    dataDimissione.add(null);
						    oraDimissione.add(null);
						}
					}
					
					if (contesto.select(Degente.DEGENTE).from(Degente.DEGENTE,Dimesso.DIMESSO).where(Degente.DEGENTE.POSIZIONE.eq(filtro),Degente.DEGENTE.CODICE.eq(Dimesso.DIMESSO.CODICE_DEGENTE),Degente.DEGENTE.COUNT.eq(Dimesso.DIMESSO.COUNT_DEGENTE)).fetch().isNotEmpty()) {
						nomi.clear();
						cognomi.clear();
					    sesso.clear();
					    dateArrivo.clear();
					    oreArrivo.clear();
					    urgenza.clear();
					    codice.clear();
					    modulo.clear();
					    reparto.clear();
					    letto.clear();
					    genere.clear();
					    eta.clear();
					    count.clear();
					    tipo.clear();
					    medicoVisita.clear();
					    motivoVisita.clear();
					    dataPrenotazione.clear();
					    oraPrenotazione.clear();
					    medicoDimissione.clear();
					    motivoDimissione.clear();
					    dataDimissione.clear();
					    oraDimissione.clear();
						Result<Record11<String, String, String, Integer, String, String, LocalDate, LocalTime, String, String, Integer>> degenti = contesto.selectDistinct(Degente.DEGENTE.NOME,Degente.DEGENTE.COGNOME,Degente.DEGENTE.CODICE,Degente.DEGENTE.COUNT,Dimesso.DIMESSO.CODICE_MEDICO,Dimesso.DIMESSO.MOTIVO,Dimesso.DIMESSO.DATA_DIMISSIONE,Dimesso.DIMESSO.ORA_DIMISSIONE,Degente.DEGENTE.SESSO,Degente.DEGENTE.GENERE,Degente.DEGENTE.ETA).from(Degente.DEGENTE,Dimesso.DIMESSO).where(Degente.DEGENTE.CODICE.eq(Dimesso.DIMESSO.CODICE_DEGENTE)).and(Degente.DEGENTE.COUNT.eq(Dimesso.DIMESSO.COUNT_DEGENTE)).and(Degente.DEGENTE.POSIZIONE.eq(filtro)).fetch();
						for (Record11<String, String, String, Integer, String, String, LocalDate, LocalTime, String, String, Integer> degenteRecord : degenti) {
							nomi.add(degenteRecord.value1());
						    cognomi.add(degenteRecord.value2());
						    sesso.add(degenteRecord.value9());
						    dateArrivo.add(null);
						    oreArrivo.add(null);
						    urgenza.add(null);
						    codice.add(degenteRecord.value3());
						    modulo.add(null);
						    reparto.add(null);
						    letto.add(0);
						    genere.add(degenteRecord.value10());
						    eta.add(degenteRecord.value11());
						    count.add(degenteRecord.value4());
						    tipo.add(null);
						    medicoVisita.add("No prenotazioni");
						    motivoVisita.add("No prenotazioni");
						    dataPrenotazione.add(null);
						    oraPrenotazione.add(null);
							medicoDimissione.add(degenteRecord.value5());
						    motivoDimissione.add(degenteRecord.value6());
						    dataDimissione.add(degenteRecord.value7());
						    oraDimissione.add(degenteRecord.value8());
						}
					}
					
					modello.modelloGestoreTabella.setTableNomi(nomi);
					modello.modelloGestoreTabella.setTableCognomi(cognomi);
					modello.modelloGestoreTabella.setTableSesso(sesso);
					modello.modelloGestoreTabella.setTableDateArrivo(dateArrivo);
					modello.modelloGestoreTabella.setTableOreArrivo(oreArrivo);
					modello.modelloGestoreTabella.setTableUrgenza(urgenza);
					modello.modelloGestoreTabella.setTableCodice(codice);
					modello.modelloGestoreTabella.setTableReparto(reparto);
					modello.modelloGestoreTabella.setTableModulo(modulo);
					modello.modelloGestoreTabella.setTableNumeroLetto(letto);
					modello.modelloGestoreTabella.setTableCount(count);
					modello.modelloGestoreTabella.setTableEta(eta);
					modello.modelloGestoreTabella.setTableGenere(genere);
					
					modello.modelloGestoreTabella.setTableMedicoVisita(medicoVisita);
	    			modello.modelloGestoreTabella.setTableMotivoVisita(motivoVisita);
	    			modello.modelloGestoreTabella.setTableDataPrenotazione(dataPrenotazione);
	    			modello.modelloGestoreTabella.setTableOraPrenotazione(oraPrenotazione);
	    	   		modello.modelloGestoreTabella.setTableTipo(tipo);

					modello.modelloGestoreTabella.setTableMedicoDimissione(medicoDimissione);
					modello.modelloGestoreTabella.setTableMotivoDimissione(motivoDimissione);
					modello.modelloGestoreTabella.setTableDataDimissione(dataDimissione);
					modello.modelloGestoreTabella.setTableOraDimissione(oraDimissione);
					
					frameDeiPazienti.posizioneAttuale=filtro;
					
					frameDeiPazienti.pazienteImage = new ImageIcon("../progetto_gui/src/main/resources/fototessera_default.png");
					frameDeiPazienti.immagineLabel.repaint();

					SwingUtilities.invokeLater(new Runnable() {
					    @Override
					    public void run() {
					    	frameDeiPazienti.updateViewTabella();
					    }
					});
				}
			} catch (SQLException ev) {
				System.out.println(ev.getMessage());
			}
		}
	}
	
}




