package logiche_bottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import gestore_db.CreateDB;
import gui.*;
import logiche_bottoni_conferma.ConfermaModificaIntervento;
import med_db.jooq.generated.tables.VisitaIntervento;
import modelli.ModelloGestoreLogicaGenerale;

public class LogicaBottoneModificaIntervento extends LogicaBottone{

	/**
	 * Controller del bottone "modifica intervento"
	 * Il pulsante può essere utilizzato soltanto da un operatore o medico
	 */
	public LogicaBottoneModificaIntervento(PazientiFrame v2, ModelloGestoreLogicaGenerale m) {
		super(v2,m);
		start();
	}
	
	/**
	 * Alla pressione del bottone verifica la mansione dell'utilizzatore corrente:
	 * se si tratta di un operatore o medico, delega l'operazione di inserimento alla classe logica "ConfermaModificaIntervento", 
	 * avviando anche i frame appositi
	 * Alternativamente, mostra a schermo un relativo messaggio di errore
	 */
	protected void start() {
		//si registra al bottone prontoSoccorsoToggleButton
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Connection conn;
				try {
					conn = DriverManager.getConnection(CreateDB.DB_URL);
					DSLContext contesto = DSL.using(conn, SQLDialect.SQLITE);
					if (modello.modelloGestoreUtente.getMansioneUtente().equals("Operatore") || modello.modelloGestoreUtente.getMansioneUtente().equals("Medico")) {
						if(modello.modelloGestorePaziente.qualcunoSelezionato()) {
								String data = contesto.select(VisitaIntervento.VISITA_INTERVENTO.DATA_PRENOTAZIONE)
									    .from(VisitaIntervento.VISITA_INTERVENTO)
									    .where(VisitaIntervento.VISITA_INTERVENTO.CODICE_DEGENTE.eq(modello.modelloGestorePaziente.getCodice()).and(VisitaIntervento.VISITA_INTERVENTO.COUNT_DEGENTE.eq(modello.modelloGestorePaziente.getCount())))
									    .limit(1)
									    .fetchOneInto(String.class);
								String ora = contesto.select(VisitaIntervento.VISITA_INTERVENTO.ORA_PRENOTAZIONE)
									    .from(VisitaIntervento.VISITA_INTERVENTO)
									    .where(VisitaIntervento.VISITA_INTERVENTO.CODICE_DEGENTE.eq(modello.modelloGestorePaziente.getCodice()).and(VisitaIntervento.VISITA_INTERVENTO.COUNT_DEGENTE.eq(modello.modelloGestorePaziente.getCount())))
									    .limit(1)
									    .fetchOneInto(String.class);
								String medico = contesto.select(VisitaIntervento.VISITA_INTERVENTO.CODICE_MEDICO)
									    .from(VisitaIntervento.VISITA_INTERVENTO)
									    .where(VisitaIntervento.VISITA_INTERVENTO.CODICE_DEGENTE.eq(modello.modelloGestorePaziente.getCodice()).and(VisitaIntervento.VISITA_INTERVENTO.COUNT_DEGENTE.eq(modello.modelloGestorePaziente.getCount())))
									    .limit(1)
									    .fetchOneInto(String.class);
								String descrizione = contesto.select(VisitaIntervento.VISITA_INTERVENTO.MOTIVO)
									    .from(VisitaIntervento.VISITA_INTERVENTO)
									    .where(VisitaIntervento.VISITA_INTERVENTO.CODICE_DEGENTE.eq(modello.modelloGestorePaziente.getCodice()).and(VisitaIntervento.VISITA_INTERVENTO.COUNT_DEGENTE.eq(modello.modelloGestorePaziente.getCount())))
									    .limit(1)
									    .fetchOneInto(String.class);
								String codice = contesto.select(VisitaIntervento.VISITA_INTERVENTO.ID)
									    .from(VisitaIntervento.VISITA_INTERVENTO)
									    .where(VisitaIntervento.VISITA_INTERVENTO.CODICE_DEGENTE.eq(modello.modelloGestorePaziente.getCodice()).and(VisitaIntervento.VISITA_INTERVENTO.COUNT_DEGENTE.eq(modello.modelloGestorePaziente.getCount())))
									    .limit(1)
									    .fetchOneInto(String.class);
							ModificaInterventoFrame frame = new ModificaInterventoFrame();
							frameDeiPazienti.sfondoFrame.setEnabled(false);
							new ConfermaModificaIntervento(frame,frameDeiPazienti,modello,codice,data);
							frame.dataTextField.setText(data);
							frame.oraTextField.setText(ora);
							frame.medicoTextField.setText(medico);
							frame.descrizioneTextArea.setText(descrizione);
							if(modello.modelloGestoreUtente.getMansioneUtente().equals("Medico")) {
								frame.medicoTextField.setEnabled(false);
							}
						}
						else {
							new ErroreFrame(frameDeiPazienti.sfondoFrame, "Deve selezionare prima il paziente di cui vuole modificare i dati");
						}
					}
					else {
						new ErroreFrame(frameDeiPazienti.sfondoFrame, "Ci dispiace informarla che, secondo le nostre politiche, il suo account da " + modello.modelloGestoreUtente.getMansioneUtente() + " non è abilitato alla modifica dei dati sugli interventi");
					}
				} catch(SQLException e2) {
				System.out.println(e2.getMessage());
				}
			}
		};
		frameDeiPazienti.modificaInterventoButton.addActionListener(listener);
	}
}
