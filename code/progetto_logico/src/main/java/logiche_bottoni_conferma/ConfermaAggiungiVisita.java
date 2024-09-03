package logiche_bottoni_conferma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import gestore_db.CreateDB;
import gui.InserisciVisitaFrame;
import gui.ErroreFrame;
import gui.InserisciPazientePerVisitaFrame;
import gui.PazientiFrame;
import logiche_bottoni.LogicaCaricaPdf;
import logiche_frame_sezioni_ospedaliere.LogicaGenereAutomatico;
import logiche_frame_sezioni_ospedaliere.LogicaGiaPresente;
import med_db.jooq.generated.tables.Personale;
import med_db.jooq.generated.tables.VisitaIntervento;
import modelli.ModelloGestoreLogicaGenerale;

public class ConfermaAggiungiVisita {

	private InserisciVisitaFrame frame;
	private ModelloGestoreLogicaGenerale modello;
	private PazientiFrame frameDeiPazienti;

	/** 
	 * Classe che prende i dati digitati nel relativo frame e li inserisce nel database
	 * @param v1 riferimento al frame per l'inserimento pazienti
	 * @param v2 riferimento al frame principale
	 * @param m riferimento al modello
	 */
	public ConfermaAggiungiVisita(InserisciVisitaFrame v1, PazientiFrame v2, ModelloGestoreLogicaGenerale m) {
		frame = v1;
		frameDeiPazienti = v2;
		modello = m;
		start();
	}

	/**
	 * Una volta premuto il pulsante "conferma" nel frame di inserimento, prende i dati scritti e li utilizza
	 * per eseguire un insert nella tabella visitaIntervento del database
	 * Inoltre permette la chiusura della finestra, premendo la X oppure confermando l'inserimento
	 * Alla chiusura della finestra il frame principale viene riabilitato e aggiornato con il nuovo dato
	 */
	protected void start() {
		frame.avantiButton.addActionListener(new ActionListener() {
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
							    Integer risultato = contesto.select(VisitaIntervento.VISITA_INTERVENTO.ID).from(VisitaIntervento.VISITA_INTERVENTO).orderBy(VisitaIntervento.VISITA_INTERVENTO.ID.desc()).limit(1).fetchOneInto(int.class);
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
									InserisciPazientePerVisitaFrame frame2 = new InserisciPazientePerVisitaFrame();
									LogicaCaricaPdf carica = new LogicaCaricaPdf("");
									carica.impostaDropTargetSingleFile(frame2.pdfPanel, frame2.filesPanel, frameDeiPazienti.sfondoFrame);
									new ConfermaAggiungiPazienteVisita(frame2,frameDeiPazienti,modello,carica,parsedDate,parsedOrario,descrizione,medico,(count+1));
									frameDeiPazienti.sfondoFrame.setEnabled(false);
									frame.sfondoFrame.dispose();
									new LogicaGenereAutomatico(frame2);
									new LogicaGiaPresente(frame2);
								}
							} catch (Exception ex) {
								new ErroreFrame(frame.sfondoFrame, "Il formato dell'ora è errato, il formato deve essere oo:mm");
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
