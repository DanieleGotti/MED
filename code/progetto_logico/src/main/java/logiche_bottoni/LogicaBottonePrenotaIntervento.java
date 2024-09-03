package logiche_bottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import gui.*;
import logiche_bottoni_conferma.ConfermaAggiungiIntervento;
import modelli.ModelloGestoreLogicaGenerale;

public class LogicaBottonePrenotaIntervento extends LogicaBottone{

	/**
	 * Controller del bottone "prenota intervento"
	 * Il pulsante può essere utilizzato soltanto da un operatore o medico
	 */
	public LogicaBottonePrenotaIntervento(PazientiFrame v2, ModelloGestoreLogicaGenerale m) {
		super(v2,m);
		start();
	}
	
	/**
	 * Alla pressione del bottone verifica la mansione dell'utilizzatore corrente:
	 * se si tratta di un operatore o intervento, delega l'operazione di inserimento alla classe logica "ConfermaPrenotaIntervento", 
	 * avviando anche i frame appositi
	 * Alternativamente, mostra a schermo un relativo messaggio di errore
	 */
	protected void start() {
		//si registra al bottone prontoSoccorsoToggleButton
		frameDeiPazienti.prenotaInterventoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (modello.modelloGestoreUtente.getMansioneUtente().equals("Operatore") || modello.modelloGestoreUtente.getMansioneUtente().equals("Medico")) { 
					if(modello.modelloGestorePaziente.qualcunoSelezionato()) {
						InserisciInterventoFrame frame = new InserisciInterventoFrame();
						frameDeiPazienti.sfondoFrame.setEnabled(false);
						new ConfermaAggiungiIntervento(frame,frameDeiPazienti,modello);
						if(modello.modelloGestoreUtente.getMansioneUtente().equals("Medico")) {
							frame.medicoTextField.setText(modello.modelloGestoreUtente.getCodiceUtente());
							frame.medicoTextField.setEnabled(false);
						}
					}
					else {
						new ErroreFrame(frameDeiPazienti.sfondoFrame, "Deve selezionare prima il paziente di cui vuole modificare i dati");
					}
				}
				else {
					new ErroreFrame(frameDeiPazienti.sfondoFrame, "Ci dispiace informarla che, secondo le nostre politiche, il suo account da " + modello.modelloGestoreUtente.getMansioneUtente() + " non è abilitato alla prenotazione di interventi, provi a contattare il reparto di logistica o il medico di turno");
				}
			}
		});
	}
}
