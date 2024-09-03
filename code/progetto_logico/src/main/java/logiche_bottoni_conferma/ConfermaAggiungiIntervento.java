package logiche_bottoni_conferma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.ImageIcon;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import gestore_db.CreateDB;
import gestore_db.InserimentoJooq;
import gui.ErroreFrame;
import gui.InserisciInterventoFrame;
import gui.PazientiFrame;
import logiche_bottoni.LogicaCaricaPdf;
import logiche_frame_sezioni_ospedaliere.LogicaDellaPosizionePazienteTabella;
import med_db.jooq.generated.tables.Degente;
import med_db.jooq.generated.tables.Personale;
import med_db.jooq.generated.tables.VisitaIntervento;
import modelli.ModelloGestoreLogicaGenerale;

public class ConfermaAggiungiIntervento {

	private InserisciInterventoFrame frame;
	private ModelloGestoreLogicaGenerale modello;
	private PazientiFrame frameDeiPazienti;
	private LogicaDellaPosizionePazienteTabella Tabella;

	/** 
	 * Classe che prende i dati digitati nel relativo frame e li inserisce nel database
	 * @param v1 riferimento al frame per l'inserimento pazienti
	 * @param v2 riferimento al frame principale
	 * @param m riferimento al modello
	 */
	public ConfermaAggiungiIntervento(InserisciInterventoFrame v1, PazientiFrame v2, ModelloGestoreLogicaGenerale m) {
		frame = v1;
		frameDeiPazienti = v2;
		modello = m;
		Tabella = new LogicaDellaPosizionePazienteTabella(frameDeiPazienti,modello,"in Reparto");
		start();
	}

	/**
	 * Una volta premuto il pulsante "conferma" nel frame di inserimento, prende i dati scritti e li utilizza
	 * per eseguire un insert nella tabella visitaIntervento del database
	 * Inoltre permette la chiusura della finestra, premendo la X oppure confermando l'inserimento
	 * Alla chiusura della finestra il frame principale viene riabilitato e aggiornato con il nuovo dato
	 */
	protected void start() {
		frame.confermaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!frame.dataTextField.getText().isBlank() && !frame.oraTextField.getText().isBlank() && !frame.medicoTextField.getText().isBlank() && !frame.descrizioneTextArea.getText().isBlank()) {
					Connection conn;
					try {
						conn = DriverManager.getConnection(CreateDB.DB_URL);
						DSLContext contesto = DSL.using(conn, SQLDialect.SQLITE);
						String inputDate = frame.dataTextField.getText();
						String inputOrario = frame.oraTextField.getText();
						String descrizione = frame.descrizioneTextArea.getText();
						String medico = frame.medicoTextField.getText();
						LocalTime parsedOrario;
						LocalDate parsedDate;
						DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						try {
							parsedDate = LocalDate.parse(inputDate, dateFormat);
							try {
							    parsedOrario = LocalTime.parse(inputOrario);
							    Integer risultato1 = contesto.select(VisitaIntervento.VISITA_INTERVENTO.ID).from(VisitaIntervento.VISITA_INTERVENTO).orderBy(VisitaIntervento.VISITA_INTERVENTO.ID.desc()).limit(1).fetchOneInto(int.class);
							    int count1;
								if (risultato1 == null) {
									count1 = 0;
								}
								else {
									count1 = risultato1;
								}
								Integer risultato = contesto.select(Degente.DEGENTE.COUNT).from(Degente.DEGENTE).where(Degente.DEGENTE.CODICE.eq(modello.modelloGestorePaziente.getCodice())).orderBy(Degente.DEGENTE.COUNT.desc()).limit(1).fetchOneInto(int.class);
								int count;
								if (risultato == null) {
									count = 0;
								}
								else {
									count = risultato;
								}
								Integer risultato2 = contesto.select(Personale.PERSONALE.CODICE).from(Personale.PERSONALE).where(Personale.PERSONALE.CODICE.eq(medico).and(Personale.PERSONALE.MANSIONE.eq("M"))).limit(1).fetchOneInto(int.class);
								if(risultato2 == null) {
									new ErroreFrame(frame.sfondoFrame, "Il medico con id: " + medico + " non esiste, ricontrollare");
								}else {
									if (InserimentoJooq.getIstanza().degente(modello.modelloGestorePaziente.getCodice(),(count+1), modello.modelloGestorePaziente.getNome(),modello.modelloGestorePaziente.getCognome(),modello.modelloGestorePaziente.getSesso(),modello.modelloGestorePaziente.getGenere(),modello.modelloGestorePaziente.getEta(),LocalDate.now(),LocalTime.now().withNano(0),modello.modelloGestorePaziente.getUrgenza(),"Visita Intervento") == 1) {
										InserimentoJooq.getIstanza().visitaIntervento((count1+1), "Intervento", modello.modelloGestorePaziente.getCodice(), modello.modelloGestoreUtente.getCodiceUtente(), count+1, parsedDate, parsedOrario, descrizione);
										modello.modelloGestorePaziente.deselezionaPaziente();
										frameDeiPazienti.pazienteImage = new ImageIcon("../progetto_gui/src/main/resources/fototessera_default.png");
										frameDeiPazienti.immagineLabel.repaint();
										frameDeiPazienti.updateStringaPaziente();
										Tabella.update();
										frameDeiPazienti.updateViewTabella();
										LogicaCaricaPdf copia = new LogicaCaricaPdf("");
										File sottoCartella = new File("./../../pazienti/id_" + modello.modelloGestorePaziente.getCodice() + "/dati_anagrafici/count_" + (count+1)+ "__data__" + parsedDate);
										if (!sottoCartella.exists()) {
											sottoCartella.mkdirs();
										}
										copia.CopiaFileEsempio(
											    "./../../pazienti/id_" + modello.modelloGestorePaziente.getCodice() + "/dati_anagrafici/count_" + modello.modelloGestorePaziente.getCount() + "__data__" + modello.modelloGestorePaziente.getData() + "/Fototessera.png",
											    "./../../pazienti/id_" + modello.modelloGestorePaziente.getCodice() + "/dati_anagrafici/count_" + (count + 1) + "__data__" + parsedDate + "/Fototessera.png"
											);
										frameDeiPazienti.sfondoFrame.setEnabled(true);
										frame.sfondoFrame.dispose();
									}
								}
							} catch (Exception ex) {
								System.out.println(ex.toString());
								new ErroreFrame(frame.sfondoFrame, "Il formato dell ora è errato, il formato deve essere oo:mm");
							}
						} catch (Exception ex) {
							new ErroreFrame(frame.sfondoFrame, "Il formato della data è errato, il formato deve essere aaaa-mm-gg");
						}
					} catch(SQLException e2) {
						System.out.println(e2.getMessage());
					}
				}
				else {
					new ErroreFrame(frame.sfondoFrame, "Alcuni campi sono vuoti, inserisci tutti i dati");
					
				}
			}	
			
		});
		
		frame.sfondoFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                frameDeiPazienti.sfondoFrame.setEnabled(true);
                frame.sfondoFrame.dispose();
            }
        });
	}
}
