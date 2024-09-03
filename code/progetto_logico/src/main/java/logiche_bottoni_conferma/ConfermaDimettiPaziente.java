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
import gestore_db.AggiornamentiJooq;
import gestore_db.CreateDB;
import gestore_db.InserimentoJooq;
import gui.DimettiPazienteFrame;
import gui.ErroreFrame;
import gui.PazientiFrame;
import logiche_bottoni.LogicaCaricaPdf;
import logiche_frame_sezioni_ospedaliere.LogicaDellaPosizionePazienteTabella;
import med_db.jooq.generated.tables.Dimesso;
import modelli.ModelloGestoreLogicaGenerale;

public class ConfermaDimettiPaziente {

	private DimettiPazienteFrame frame;
	private ModelloGestoreLogicaGenerale modello;
	private PazientiFrame frameDeiPazienti;
	private LogicaDellaPosizionePazienteTabella tabella;
	private LogicaCaricaPdf carica;

	/** 
	 * Classe che permette la dimissione del degente selezionato
	 * @param v1 riferimento al frame per la rimozione del degente
	 * @param v2 riferimento al frame principale
	 * @param m riferimento al modello
	 */
	public ConfermaDimettiPaziente(DimettiPazienteFrame v1, PazientiFrame v2, ModelloGestoreLogicaGenerale m, LogicaCaricaPdf c) {
		frame = v1;
		frameDeiPazienti = v2;
		modello = m;
		carica = c;
		String posizione = modello.modelloGestorePaziente.getPosizione();
		tabella = new LogicaDellaPosizionePazienteTabella(frameDeiPazienti,modello,posizione);
		start();
	}

	/**
	 * Una volta premuto il pulsante "conferma" nel frame di rimozione, rimuove il paziente selezionato
	 * dalla tabella degente del database
	 * Inoltre permette la chiusura della finestra, premendo la X oppure confermando l'inserimento
	 * Alla chiusura della finestra il frame principale viene riabilitato e aggiornato
	 */
	protected void start() {
		frame.confermaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!frame.dataTextField.getText().isBlank() && !frame.oraTextField.getText().isBlank() && !frame.motivoTextField.getText().isBlank()) {
					Connection conn;
					try {
						conn = DriverManager.getConnection(CreateDB.DB_URL);
						DSLContext contesto = DSL.using(conn, SQLDialect.SQLITE);
						String inputDate = frame.dataTextField.getText();
						String inputOrario = frame.oraTextField.getText();
						String motivo = frame.motivoTextField.getText();
						LocalTime parsedOrario;
						LocalDate parsedDate;
						DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						try {
							parsedDate = LocalDate.parse(inputDate, dateFormat);
							try {
							    parsedOrario = LocalTime.parse(inputOrario);
							    Integer risultato = contesto.select(Dimesso.DIMESSO.ID).from(Dimesso.DIMESSO).orderBy(Dimesso.DIMESSO.ID.desc()).limit(1).fetchOneInto(int.class);
							    int count;
								if (risultato == null) {
									count = 0;
								}
								else {
									count = risultato;
								}
								try {
									if (AggiornamentiJooq.getIstanza().degente(modello.modelloGestorePaziente.getCodice(), modello.modelloGestorePaziente.getCount(), "posizione", "Dimesso")>0) {
								    	InserimentoJooq.getIstanza().dimesso(count+1, modello.modelloGestorePaziente.getCodice(), modello.modelloGestoreUtente.getCodiceUtente(), modello.modelloGestorePaziente.getCount(), parsedDate, parsedOrario.withNano(0), motivo);
								    	String cartellaCountPaz = "count_" + modello.modelloGestorePaziente.getCount() + "__data__" + parsedDate;

								    	File sottoCartella = new File("./../../pazienti/id_" + modello.modelloGestorePaziente.getCodice() + "/dati_dimissione/" + cartellaCountPaz);
										if (!sottoCartella.exists()) {
											sottoCartella.mkdirs();
										}
								    	carica.destinazione = new File("./../../pazienti/id_" + modello.modelloGestorePaziente.getCodice()+ "/dati_dimissione/" + cartellaCountPaz);
										carica.salvaConTrascinamento();
									}
								}
								catch (Exception ex) {
								new ErroreFrame(frame.sfondoFrame, "E' avvenuto un problema durante la dimissione del paziente, se il problema persiste chiamare un tecnico");
								}
								modello.modelloGestorePaziente.deselezionaPaziente();
								frameDeiPazienti.pazienteImage = new ImageIcon("../progetto_gui/src/main/resources/fototessera_default.png");
								frameDeiPazienti.immagineLabel.repaint();
								frameDeiPazienti.updateStringaPaziente();
								tabella.update();
								frameDeiPazienti.updateViewTabella();
								frameDeiPazienti.sfondoFrame.setEnabled(true);
								frame.sfondoFrame.dispose();
							} catch (Exception ex) {
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

