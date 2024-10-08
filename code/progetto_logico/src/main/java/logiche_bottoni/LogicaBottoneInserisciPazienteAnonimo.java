package logiche_bottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.*;
import logiche_bottoni_conferma.ConfermaAggiungiPazienteAnonimo;
import modelli.ModelloGestoreLogicaGenerale;


public class LogicaBottoneInserisciPazienteAnonimo extends LogicaBottone{

	/**
	 * Controller del bottone "inserisci paziente anonimo"
	 * Il pulsante può essere utilizzato soltanto da un operatore
	 */
	public LogicaBottoneInserisciPazienteAnonimo(PazientiFrame v2, ModelloGestoreLogicaGenerale m) {
		super(v2,m);
		start();
	}
	
	/**
	 * Alla pressione del bottone verifica la mansione dell'utilizzatore corrente:
	 * se si tratta di un operatore, delega l'operazione di inserimento alla classe logica "ConfermaAggiungiPazienteAnonimo", 
	 * avviando anche i frame appositi
	 * Alternativamente, mostra a schermo un relativo messaggio di errore
	 */
	protected void start() {
		//si registra al bottone prontoSoccorsoToggleButton
		frameDeiPazienti.inserisciAnonimoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (modello.modelloGestoreUtente.getMansioneUtente().equals("Operatore")) {
					InserisciAnonimoFrame frame = new InserisciAnonimoFrame();
					frameDeiPazienti.sfondoFrame.setEnabled(false);
					new ConfermaAggiungiPazienteAnonimo(frame,frameDeiPazienti,modello);
				}
				else {
					new ErroreFrame(frameDeiPazienti.sfondoFrame, "Ci dispiace informarla che, secondo le nostre politiche, il suo account da " + modello.modelloGestoreUtente.getMansioneUtente() + " non è abilitato all'inserimento di nuovi degenti, provi a contattare il reparto di logistica");
				}
			}
		});
	}
}
