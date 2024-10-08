package logiche_bottoni_conferma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import gestore_db.CreateDB;
import gestore_db.InserimentoJooq;
import gui.InserisciRilevazioneFrame;
import gui.ErroreFrame;
import gui.PazientiFrame;
import med_db.jooq.generated.tables.Rilevazione;
import modelli.ModelloGestoreLogicaGenerale;

public class ConfermaAggiungiRilevazione {

	private InserisciRilevazioneFrame frame;
	private PazientiFrame frameDeiPazienti;
	private ModelloGestoreLogicaGenerale modello;

	/** 
	 * Classe che prende i dati digitati nel relativo frame e li inserisce nel database
	 * @param v1 riferimento al frame per l'inserimento rilevazioni
	 * @param v2 riferimento al frame principale
	 * @param m riferimento al modello
	 */
	public ConfermaAggiungiRilevazione(InserisciRilevazioneFrame v1, PazientiFrame v2, ModelloGestoreLogicaGenerale m) {
		frame = v1;
		frameDeiPazienti = v2;
		modello = m;
		start();
	}

	/**
	 * Una volta premuto il pulsante "conferma" nel frame di inserimento, prende i dati scritti e li utilizza
	 * per eseguire un insert nella tabella rilevazione del database
	 * Inoltre permette la chiusura della finestra, premendo la X oppure confermando l'inserimento
	 * Alla chiusura della finestra il frame principale viene riabilitato
	 */
	protected void start() {
		frame.confermaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Connection conn;
				try {
					conn = DriverManager.getConnection(CreateDB.DB_URL);
					DSLContext contesto = DSL.using(conn, SQLDialect.SQLITE);
					if (!frame.glicemiaTextField.getText().isBlank() && !frame.doloreTextField.getText().isBlank() && !frame.temperaturaTextField.getText().isBlank() && !frame.pressioneMaxTextField.getText().isBlank() && !frame.frequenzaTextField.getText().isBlank() && !frame.pressioneMinTextField.getText().isBlank()) {
						int glicemia = Integer.parseInt(frame.glicemiaTextField.getText());
						Double temperatura = Double.parseDouble(frame.temperaturaTextField.getText());
						int pressioneMax = Integer.parseInt(frame.pressioneMaxTextField.getText());
						int pressioneMin = Integer.parseInt(frame.pressioneMinTextField.getText());
						int frequenza = Integer.parseInt(frame.frequenzaTextField.getText());
						int dolore = Integer.parseInt(frame.doloreTextField.getText());
						Integer risultato = contesto.select(Rilevazione.RILEVAZIONE.ID).from(Rilevazione.RILEVAZIONE).where(Rilevazione.RILEVAZIONE.CODICE_DEGENTE.eq(modello.modelloGestorePaziente.getCodice())).orderBy(Rilevazione.RILEVAZIONE.ID.desc()).limit(1).fetchOneInto(int.class);
						int ultimoCodice;
						if (risultato == null) {
							ultimoCodice = 0;
						}
						else {
							ultimoCodice = risultato;
						}
						if ((InserimentoJooq.getIstanza().rilevazione(ultimoCodice+1,modello.modelloGestorePaziente.getCodice(),modello.modelloGestorePaziente.getCount(),modello.modelloGestoreUtente.getCodiceUtente(), temperatura,pressioneMax,pressioneMin,glicemia, LocalDate.now(),LocalTime.now().withNano(0),frequenza,dolore)) == 1) {
						}
						else {
							new ErroreFrame(frame.sfondoFrame, "E' avvenuto un problema nell'inserimento della rilevazione, se il problema persiste chiamare un tecnico");
						}
						frameDeiPazienti.sfondoFrame.setEnabled(true);
						frame.sfondoFrame.dispose();
					}
					else {
						new ErroreFrame(frame.sfondoFrame, "Alcuni campi sono vuoti");
					}
				} catch (SQLException e1) {
					System.out.println(e1.getMessage());
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
