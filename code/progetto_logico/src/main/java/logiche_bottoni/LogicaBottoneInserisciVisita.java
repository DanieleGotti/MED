package logiche_bottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import gui.*;
import logiche_bottoni_conferma.ConfermaAggiungiVisita;
import modelli.ModelloGestoreLogicaGenerale;

public class LogicaBottoneInserisciVisita extends LogicaBottone{

	/**
	 * Controller del bottone "inserisci visita"
	 * Il pulsante può essere utilizzato soltanto da un operatore
	 */
	public LogicaBottoneInserisciVisita(PazientiFrame v2, ModelloGestoreLogicaGenerale m) {
		super(v2,m);
		start();
	}
	
	/**
	 * Alla pressione del bottone verifica la mansione dell'utilizzatore corrente:
	 * se si tratta di un operatore, delega l'operazione di inserimento alla classe logica "ConfermaAggiungiVisita", 
	 * avviando anche i frame appositi
	 * Alternativamente, mostra a schermo un relativo messaggio di errore
	 */
	protected void start() {
		//si registra al bottone prontoSoccorsoToggleButton
		frameDeiPazienti.prenotaVisitaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (modello.modelloGestoreUtente.getMansioneUtente().equals("Operatore") || modello.modelloGestoreUtente.getMansioneUtente().equals("Medico")) { 
					InserisciVisitaFrame frame = new InserisciVisitaFrame();
					frameDeiPazienti.sfondoFrame.setEnabled(false);
					new ConfermaAggiungiVisita(frame,frameDeiPazienti,modello);
					if(modello.modelloGestoreUtente.getMansioneUtente().equals("Medico")) {
						frame.medicoTextField.setText(modello.modelloGestoreUtente.getCodiceUtente());
						frame.medicoTextField.setEnabled(false);
					}
				}
				else {
					new ErroreFrame(frameDeiPazienti.sfondoFrame, "Ci dispiace informarla che, secondo le nostre politiche, il suo account da " + modello.modelloGestoreUtente.getMansioneUtente() + " non è abilitato alla prenotazione di nuove visite, provi a contattare il reparto di logistica");
				}
			}
		});
	}
}
