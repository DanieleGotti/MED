package logiche_bottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import org.jooq.Record13;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import gui.*;
import logiche_frame_sezioni_ospedaliere.LogicaDellaPosizionePazienteTabella;
import med_db.jooq.generated.tables.Assegnazioneletto;
import med_db.jooq.generated.tables.Degente;
import med_db.jooq.generated.tables.Reparto;
import modelli.ModelloGestoreLogicaGenerale;

public class LogicaBottoneFiltroNomeCognome extends LogicaBottone{
	
	private static String DB_REL_FILELOGIC = "../progetto_database/db/db.db3";
	private static String DB_URLLOGIC = "jdbc:sqlite:" + DB_REL_FILELOGIC;
	private String[] sezioni;
	private String valore1;
	private String valore2;
	private LogicaDellaPosizionePazienteTabella tabellaDeiPazienti;
	
	/**
	 * Pulsante che permette di filtrare i pazienti nella sezione correntemente attiva per nome e/o cognome
	 */
	public LogicaBottoneFiltroNomeCognome(PazientiFrame v2, ModelloGestoreLogicaGenerale m) {
		super(v2,m);
		start();
	}
	
	/**
	 * Il filtro si attiva alla pressione del pulsante di ricerca (lente di ingrandimento)
	 * Utilizza le stringhe di caratteri posti nella barra di ricerca, eseguendo controlli che permettano
	 * di trovare il degente richiesto attraverso una qualsiasi combinazione di nome e cognome 
	 * (solo uno o l'altro, oppure entrambi in qualunque ordine)
	 * Se alla pressione del pulsante di ricerca la barra di testo è vuota vengono presentati tutti i degenti
	 */
	protected void start() {
		//si registra al bottone prontoSoccorsoToggleButton
		frameDeiPazienti.cercaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn = DriverManager.getConnection(DB_URLLOGIC);
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
		            String testo = frameDeiPazienti.cercaTextField.getText();
		            modello.modelloGestorePaziente.deselezionaPaziente();
		            frameDeiPazienti.updateStringaPaziente();
					frameDeiPazienti.urgenzaComboBox.setSelectedItem(" ");
					frameDeiPazienti.repartoComboBox.setSelectedItem(" ");
					frameDeiPazienti.tipoComboBox.setSelectedItem(" ");
					frameDeiPazienti.mieiPazientiCheckBox.setSelected(false);
					
					if (conn != null) {								
						//ipotizzio che un paziente abbia un nome e un cognome, degenti con il doppio nome putroppo si dovranno cercare per cognome, stessa cosa per le persone con il doppio cognome che si dovranno cercare per nome
						DSLContext contesto = DSL.using(conn, SQLDialect.SQLITE);
						if(!testo.isEmpty()) {
							sezioni = testo.split(" ");
							if (sezioni.length >= 2) {
								valore1 = sezioni[0];
								valore2 = sezioni[1];
								if (contesto.select(Degente.DEGENTE.CODICE).from(Degente.DEGENTE,Assegnazioneletto.ASSEGNAZIONELETTO).where(Degente.DEGENTE.POSIZIONE.eq(frameDeiPazienti.posizioneAttuale),Degente.DEGENTE.CODICE.eq(Assegnazioneletto.ASSEGNAZIONELETTO.CODICE_DEGENTE)).fetch().isNotEmpty()) {
									Result<Record13<String,String,String,LocalDate,LocalTime,String,String,String,String,Integer,String,Integer,Integer>> degenti = contesto.selectDistinct(Degente.DEGENTE.NOME,Degente.DEGENTE.COGNOME,Degente.DEGENTE.SESSO,Degente.DEGENTE.DATA_ARRIVO,Degente.DEGENTE.ORA_ARRIVO,Degente.DEGENTE.URGENZA,Degente.DEGENTE.CODICE,Reparto.REPARTO.NOME_REPARTO,Assegnazioneletto.ASSEGNAZIONELETTO.NOME_MODULO,Assegnazioneletto.ASSEGNAZIONELETTO.NUMERO_LETTO,Degente.DEGENTE.GENERE,Degente.DEGENTE.ETA,Degente.DEGENTE.COUNT).from(Degente.DEGENTE,Assegnazioneletto.ASSEGNAZIONELETTO,Reparto.REPARTO).where(((Degente.DEGENTE.NOME.equalIgnoreCase(valore1).and(Degente.DEGENTE.COGNOME.equalIgnoreCase(valore2))).or((Degente.DEGENTE.COGNOME.equalIgnoreCase(valore1).and(Degente.DEGENTE.NOME.equalIgnoreCase(valore2))))).and(Degente.DEGENTE.POSIZIONE.eq(frameDeiPazienti.posizioneAttuale)).and(Degente.DEGENTE.CODICE.eq(Assegnazioneletto.ASSEGNAZIONELETTO.CODICE_DEGENTE)).and(Assegnazioneletto.ASSEGNAZIONELETTO.CODICE_REPARTO.eq(Reparto.REPARTO.CODICE))).fetch();
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
								}
								else {
									Result<Record10<String,String,String,LocalDate,LocalTime,String,String,String,Integer,Integer>> degenti = contesto.selectDistinct(Degente.DEGENTE.NOME,Degente.DEGENTE.COGNOME,Degente.DEGENTE.SESSO,Degente.DEGENTE.DATA_ARRIVO,Degente.DEGENTE.ORA_ARRIVO,Degente.DEGENTE.URGENZA,Degente.DEGENTE.CODICE,Degente.DEGENTE.GENERE,Degente.DEGENTE.ETA,Degente.DEGENTE.COUNT).from(Degente.DEGENTE).where(((Degente.DEGENTE.NOME.equalIgnoreCase(valore1).and(Degente.DEGENTE.COGNOME.equalIgnoreCase(valore2))).or((Degente.DEGENTE.COGNOME.equalIgnoreCase(valore1).and(Degente.DEGENTE.NOME.equalIgnoreCase(valore2)))))).fetch();
									for (Record10<String, String, String, LocalDate, LocalTime, String,String,String,Integer,Integer> degenteRecord : degenti) {
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
										}
								}
							}
							else {
								valore1 = sezioni[0];
								if (contesto.select(Degente.DEGENTE.CODICE).from(Degente.DEGENTE,Assegnazioneletto.ASSEGNAZIONELETTO).where(Degente.DEGENTE.POSIZIONE.eq(frameDeiPazienti.posizioneAttuale),Degente.DEGENTE.CODICE.eq(Assegnazioneletto.ASSEGNAZIONELETTO.CODICE_DEGENTE)).fetch().isNotEmpty()) {
									Result<Record13<String,String,String,LocalDate,LocalTime,String,String,String,String,Integer,String,Integer,Integer>> degenti = contesto.select(Degente.DEGENTE.NOME,Degente.DEGENTE.COGNOME,Degente.DEGENTE.SESSO,Degente.DEGENTE.DATA_ARRIVO,Degente.DEGENTE.ORA_ARRIVO,Degente.DEGENTE.URGENZA,Degente.DEGENTE.CODICE,Reparto.REPARTO.NOME_REPARTO,Assegnazioneletto.ASSEGNAZIONELETTO.NOME_MODULO,Assegnazioneletto.ASSEGNAZIONELETTO.NUMERO_LETTO,Degente.DEGENTE.GENERE,Degente.DEGENTE.ETA,Degente.DEGENTE.COUNT).from(Degente.DEGENTE,Reparto.REPARTO,Assegnazioneletto.ASSEGNAZIONELETTO).where(((Degente.DEGENTE.NOME.equalIgnoreCase(valore1).or(Degente.DEGENTE.COGNOME.equalIgnoreCase(valore1)))).and(Degente.DEGENTE.POSIZIONE.eq(frameDeiPazienti.posizioneAttuale)).and(Degente.DEGENTE.CODICE.eq(Assegnazioneletto.ASSEGNAZIONELETTO.CODICE_DEGENTE)).and(Assegnazioneletto.ASSEGNAZIONELETTO.CODICE_REPARTO.eq(Reparto.REPARTO.CODICE))).fetch();
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
								}
								else {
									Result<Record10<String,String,String,LocalDate,LocalTime,String,String,String,Integer,Integer>> degenti = contesto.select(Degente.DEGENTE.NOME,Degente.DEGENTE.COGNOME,Degente.DEGENTE.SESSO,Degente.DEGENTE.DATA_ARRIVO,Degente.DEGENTE.ORA_ARRIVO,Degente.DEGENTE.URGENZA,Degente.DEGENTE.CODICE,Degente.DEGENTE.GENERE,Degente.DEGENTE.ETA,Degente.DEGENTE.COUNT).from(Degente.DEGENTE).where(((Degente.DEGENTE.NOME.equalIgnoreCase(valore1).or(Degente.DEGENTE.COGNOME.equalIgnoreCase(valore1)))).and(Degente.DEGENTE.POSIZIONE.equalIgnoreCase(frameDeiPazienti.posizioneAttuale))).fetch();
									for (Record10<String, String, String, LocalDate, LocalTime, String,String,String,Integer,Integer> degenteRecord : degenti) {
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
									}
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
							modello.modelloGestoreTabella.setTableGenere(genere);
							modello.modelloGestoreTabella.setTableEta(eta);
							modello.modelloGestoreTabella.setTableCount(count);
							
						}
						else {
							tabellaDeiPazienti = new LogicaDellaPosizionePazienteTabella(frameDeiPazienti, modello, frameDeiPazienti.posizioneAttuale);
							tabellaDeiPazienti.update();
						}
						
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
		});
	}
}
