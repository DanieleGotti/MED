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
import logiche_bottoni_conferma.EsciVisualizzaStorico;
import med_db.jooq.generated.tables.Diariamed;
import modelli.ModelloGestoreLogicaGenerale;

public class LogicaBottoneVisualizzaStorico extends LogicaBottone{
	
	/** 
	 * Classe che permette di aprire il frame delle visualizzazioni dello storico di un degente
	 */
	public LogicaBottoneVisualizzaStorico(PazientiFrame v2, ModelloGestoreLogicaGenerale m) {
		super(v2,m);
		start();
	}
	
	/**
	 * Alla pressione del bottone verifica la selezione di un utente e la mansione dell'utilizzatore corrente (ammessi solo medico o infermiere):
	 * se tutto è in regola, attiva e compila il frame dello storico, bloccando l'interazione con il frame principale
	 * Se qualcosa non è in linea con l'utilizzo previsto, mostra a schermo un relativo messaggio di errore
	 */
	protected void start() {
		frameDeiPazienti.visualizzaStoricoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(modello.modelloGestorePaziente.qualcunoSelezionato()) {
					if (!modello.modelloGestoreUtente.getMansioneUtente().equals("Operatore")) {
						VisualizzaStoricoFrame frame = new VisualizzaStoricoFrame();
						new EsciVisualizzaStorico(frame, frameDeiPazienti);
						Connection conn;
						try {
							conn = DriverManager.getConnection(CreateDB.DB_URL);
							DSLContext contesto = DSL.using(conn, SQLDialect.SQLITE);
							String storico = contesto.select(Diariamed.DIARIAMED.STORICO).from(Diariamed.DIARIAMED).where(Diariamed.DIARIAMED.CODICE_DEGENTE.eq(modello.modelloGestorePaziente.getCodice()).and(Diariamed.DIARIAMED.COUNT_DEGENTE.eq(modello.modelloGestorePaziente.getCount())),Diariamed.DIARIAMED.CODICE.eq(1)).fetchOneInto(String.class);
							frame.storicoTextArea.setText(storico);
							frame.setPersonaView(modello.modelloGestorePaziente.getNome() + " " + modello.modelloGestorePaziente.getCognome());
							frameDeiPazienti.sfondoFrame.setEnabled(false);
						} catch (SQLException e1) {
							System.out.println(e1.getMessage());
						}
					}
					else {
						new ErroreFrame(frameDeiPazienti.sfondoFrame, "Ci dispiace informarla che, secondo le nostre politiche di privacy, il suo account da " + modello.modelloGestoreUtente.getMansioneUtente() + " non è abilitato alla visualizzazione dello storico clinico dei pazienti");
					}
				}
				else {
					new ErroreFrame(frameDeiPazienti.sfondoFrame, "Deve selezionare prima il paziente del quale vuole visualizzare lo storico clinico");
				}
			}
		});
	}
}
