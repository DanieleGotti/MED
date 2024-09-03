package logiche_frame_sezioni_ospedaliere;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import org.jooq.DSLContext;
import org.jooq.Record12;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import gui.PazientiFrame;
import med_db.jooq.generated.tables.Degente;
import med_db.jooq.generated.tables.VisitaIntervento;
import modelli.ModelloGestoreLogicaGenerale;

public class LogicaDeiMieiPazientiTabella extends LogicaFrame{

	private String filtro;
	
	/**
	 * Classe che si occupa di mostrare a schermo i soli degenti che corrispondono al filtro di tipologia selezionato
	 * @param p riferimento al frame principale dei pazienti
	 * @param m riferimento al modello generale
	 * @param filtro valore di urgenza richiesto nella ricerca
	 */
	public LogicaDeiMieiPazientiTabella(PazientiFrame p, ModelloGestoreLogicaGenerale m, String filtro) {
		super(p,m);
		this.filtro=filtro;
	}
	
	/**
	 * All'attivazione questo metodo compila la tabella di degenti a schermo con i degenti della sezione attiva con il tipo scelto
	 */
	public void update() {
		if(!frameDeiPazienti.updating) {
			try {
				List<String> nomi = new ArrayList<>();
				List<String> codice = new ArrayList<>();
	            List<String> cognomi = new ArrayList<>();
	            List<String> sesso = new ArrayList<>();
	            List<LocalDate> datePrenotazione = new ArrayList<>();
	            List<LocalTime> oraPrenotazione = new ArrayList<>();
	            List<String> tipoPrenotazione = new ArrayList<>();
	            List<String> genere = new ArrayList<>();
	            List<String> medicoVisita = new ArrayList<>();
	            List<String> motivoVisita = new ArrayList<>();
	            List<Integer> eta = new ArrayList<>();
	            List<Integer> count = new ArrayList<>();
				Connection conn = DriverManager.getConnection(DB_URLLOGIC);
				if (conn != null) {
					DSLContext contesto = DSL.using(conn, SQLDialect.SQLITE);
						Result<Record12<String,String,String,LocalDate,LocalTime,String,String,String,Integer,Integer,String,String>> degenti = contesto.select(Degente.DEGENTE.NOME,Degente.DEGENTE.COGNOME,Degente.DEGENTE.SESSO,VisitaIntervento.VISITA_INTERVENTO.DATA_PRENOTAZIONE,VisitaIntervento.VISITA_INTERVENTO.ORA_PRENOTAZIONE,VisitaIntervento.VISITA_INTERVENTO.TIPO,Degente.DEGENTE.CODICE,Degente.DEGENTE.GENERE,Degente.DEGENTE.ETA,Degente.DEGENTE.COUNT,VisitaIntervento.VISITA_INTERVENTO.CODICE_MEDICO,VisitaIntervento.VISITA_INTERVENTO.MOTIVO).from(Degente.DEGENTE,VisitaIntervento.VISITA_INTERVENTO).where(VisitaIntervento.VISITA_INTERVENTO.CODICE_MEDICO.eq(filtro),Degente.DEGENTE.POSIZIONE.eq(frameDeiPazienti.posizioneAttuale),VisitaIntervento.VISITA_INTERVENTO.CODICE_DEGENTE.eq(Degente.DEGENTE.CODICE),VisitaIntervento.VISITA_INTERVENTO.COUNT_DEGENTE.eq(Degente.DEGENTE.COUNT)).fetch();
						for (Record12<String, String, String, LocalDate, LocalTime, String,String,String,Integer,Integer,String,String> degenteRecord : degenti) {
						    nomi.add(degenteRecord.value1());
						    cognomi.add(degenteRecord.value2());
						    sesso.add(degenteRecord.value3());
						    datePrenotazione.add(degenteRecord.value4());
						    oraPrenotazione.add(degenteRecord.value5());
						    tipoPrenotazione.add(degenteRecord.value6());
						    codice.add(degenteRecord.value7());
						    genere.add(degenteRecord.value8());
						    eta.add(degenteRecord.value9());
						    count.add(degenteRecord.value10());
						    medicoVisita.add(degenteRecord.value11());
						    motivoVisita.add(degenteRecord.value12());
						    
						}
					modello.modelloGestoreTabella.setTableNomi(nomi);
					modello.modelloGestoreTabella.setTableCognomi(cognomi);
					modello.modelloGestoreTabella.setTableSesso(sesso);
					modello.modelloGestoreTabella.setTableDataPrenotazione(datePrenotazione);
					modello.modelloGestoreTabella.setTableOraPrenotazione(oraPrenotazione);
					modello.modelloGestoreTabella.setTableTipo(tipoPrenotazione);;
					modello.modelloGestoreTabella.setTableCodice(codice);
					modello.modelloGestoreTabella.setTableCount(count);
					modello.modelloGestoreTabella.setTableEta(eta);
					modello.modelloGestoreTabella.setTableGenere(genere);
					modello.modelloGestoreTabella.setTableMedicoVisita(medicoVisita);
					modello.modelloGestoreTabella.setTableMotivoVisita(motivoVisita);;
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




