package logiche_bottoni_conferma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.ImageIcon;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import gestore_db.CreateDB;
import gestore_db.InserimentoJooq;
import gui.InserisciPazientePerVisitaFrame;
import gui.ErroreFrame;
import gui.PazientiFrame;
import logiche_bottoni.LogicaCaricaPdf;
import logiche_frame_sezioni_ospedaliere.LogicaDellaPosizionePazienteTabella;
import med_db.jooq.generated.tables.Degente;
import modelli.ModelloGestoreLogicaGenerale;

public class ConfermaAggiungiPazienteVisita {

	private InserisciPazientePerVisitaFrame frame;
	private ModelloGestoreLogicaGenerale modello;
	private PazientiFrame frameDeiPazienti;
	private LogicaDellaPosizionePazienteTabella Tabella;
	private LogicaCaricaPdf carica;
	private LocalDate dataVisita;
	private LocalTime oraVisita;
	private String descrizione_visita;
	private String medicoVisita;
	private int idVisita;

	/** 
	 * Classe che prende i dati digitati nel relativo frame e li inserisce nel database
	 * @param v1 riferimento al frame per l'inserimento pazienti
	 * @param v2 riferimento al frame principale
	 * @param m riferimento al modello
	 */
	public ConfermaAggiungiPazienteVisita(InserisciPazientePerVisitaFrame v1, PazientiFrame v2, ModelloGestoreLogicaGenerale m, LogicaCaricaPdf c, LocalDate inputDate, LocalTime inputOrario, String descrizione, String medico, int count) {
		frame = v1;
		frameDeiPazienti = v2;
		modello = m;
		carica = c;
		dataVisita = inputDate;
		oraVisita = inputOrario;
		descrizione_visita = descrizione;
		medicoVisita = medico;
		idVisita = count;
		Tabella = new LogicaDellaPosizionePazienteTabella(frameDeiPazienti,modello,"Visita Intervento");
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
				Connection conn;
				try {
					conn = DriverManager.getConnection(CreateDB.DB_URL);
					DSLContext contesto = DSL.using(conn, SQLDialect.SQLITE);
					if (!frame.codiceTextField.getText().isBlank() && !frame.nomeTextField.getText().isBlank() && !frame.cognomeTextField.getText().isBlank() && !frame.etaTextField.getText().isBlank() && !frame.genereTextField.getText().isBlank()) {
						String codice = frame.codiceTextField.getText();
						String nome = frame.nomeTextField.getText();
						String cognome = frame.cognomeTextField.getText();
						int eta;
						Integer risultato = contesto.select(Degente.DEGENTE.COUNT).from(Degente.DEGENTE).where(Degente.DEGENTE.CODICE.eq(codice)).orderBy(Degente.DEGENTE.COUNT.desc()).limit(1).fetchOneInto(int.class);
							String genere = frame.genereTextField.getText();
							int count;
							if (risultato == null) {
								count = 0;
							}
							else {
								count = risultato;
							}
							String etaTest = frame.etaTextField.getText();
							if (etaTest.matches("\\d+")) {
								eta = Integer.parseInt(frame.etaTextField.getText());
								String sesso;
								if (frame.sessoComboBox.getSelectedItem().equals("maschio")) {
									sesso = "M";
								}
								else {
									sesso = "F";
								}
								LocalDate data = LocalDate.now();
								if (InserimentoJooq.getIstanza().degente(codice,count+1,nome,cognome,sesso,genere,eta,data,LocalTime.now().withNano(0),"verde","Visita Intervento") == 1) {
									InserimentoJooq.getIstanza().visitaIntervento(idVisita, "Visita", codice, medicoVisita, count+1, dataVisita, oraVisita, descrizione_visita);
									modello.modelloGestorePaziente.deselezionaPaziente();
									frameDeiPazienti.pazienteImage = new ImageIcon("../progetto_gui/src/main/resources/fototessera_default.png");
									frameDeiPazienti.immagineLabel.repaint();
									frameDeiPazienti.updateStringaPaziente();
									Tabella.update();
									frameDeiPazienti.updateViewTabella();
									File sottoCartella = new File("./../../pazienti/id_" + codice + "/dati_anagrafici/count_" + (count+1) + "__data__" + dataVisita);
									if (!sottoCartella.exists()) {
										sottoCartella.mkdirs();
									}
									carica.destinazione = new File("./../../pazienti/id_" + codice + "/dati_anagrafici/count_" + (count+1) + "__data__" + dataVisita);
									carica.salvaConTrascinamento("Fototessera.png",true);
								}
								else {
									new ErroreFrame(frame.sfondoFrame, "E' avvenuto un problema durante l'agginta del paziente, se il problema persiste chiamare un tecnico");
								}
								frameDeiPazienti.sfondoFrame.setEnabled(true);
								frame.sfondoFrame.dispose();
							}
							else {
								new ErroreFrame(frame.sfondoFrame, "L'età deve essere un numero");
							}
					}
					else {
						new ErroreFrame(frame.sfondoFrame, "Alcuni campi sono vuoti");
						
					}
				} catch(SQLException e2) {
					System.out.println(e2.getMessage());
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
