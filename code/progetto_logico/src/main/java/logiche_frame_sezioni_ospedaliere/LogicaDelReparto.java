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
import org.jooq.Record13;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import gui.PazientiFrame;
import med_db.jooq.generated.tables.Assegnazioneletto;
import med_db.jooq.generated.tables.Degente;
import med_db.jooq.generated.tables.Reparto;
import modelli.ModelloGestoreLogicaGenerale;

public class LogicaDelReparto extends LogicaFrame{

	private String filtroNomeReparto;
	
	/**
	 * Classe che si occupa di mostrare a schermo i soli degenti che corrispondono al filtro di reparto selezionato
	 * @param p riferimento al frame principale dei pazienti
	 * @param m riferimento al modello generale
	 * @param filtro valore di reparto richiesto nella ricerca
	 */
	public LogicaDelReparto(PazientiFrame p, ModelloGestoreLogicaGenerale m, String filtro) {
		super(p,m);
		this.filtroNomeReparto=filtro;
	}
	
	/**
	 * All'attivazione questo metodo compila la tabella di degenti a schermo con i degenti della sezione attiva posti nel reparto scelto
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
				Connection conn = DriverManager.getConnection(DB_URLLOGIC);
				if (conn != null) {
					DSLContext contesto = DSL.using(conn, SQLDialect.SQLITE);
					Result<Record13<String, String, String, LocalDate, LocalTime, String, String, String, String, Integer,String,Integer,Integer>> degenti = contesto.selectDistinct(Degente.DEGENTE.NOME,Degente.DEGENTE.COGNOME,Degente.DEGENTE.SESSO,Degente.DEGENTE.DATA_ARRIVO,Degente.DEGENTE.ORA_ARRIVO,Degente.DEGENTE.URGENZA,Degente.DEGENTE.CODICE,Reparto.REPARTO.NOME_REPARTO,Assegnazioneletto.ASSEGNAZIONELETTO.NOME_MODULO,Assegnazioneletto.ASSEGNAZIONELETTO.NUMERO_LETTO,Degente.DEGENTE.GENERE,Degente.DEGENTE.ETA,Degente.DEGENTE.COUNT).from(Degente.DEGENTE,Assegnazioneletto.ASSEGNAZIONELETTO,Reparto.REPARTO).where(Assegnazioneletto.ASSEGNAZIONELETTO.CODICE_REPARTO.eq(Reparto.REPARTO.CODICE),Reparto.REPARTO.NOME_REPARTO.eq(filtroNomeReparto),Degente.DEGENTE.POSIZIONE.eq(frameDeiPazienti.posizioneAttuale),Degente.DEGENTE.CODICE.eq(Assegnazioneletto.ASSEGNAZIONELETTO.CODICE_DEGENTE),Assegnazioneletto.ASSEGNAZIONELETTO.CODICE_REPARTO.eq(Reparto.REPARTO.CODICE)).fetch();
					for (Record13<String, String, String, LocalDate, LocalTime, String,String,String,String,Integer,String,Integer,Integer> degenteRecord : degenti) {
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




