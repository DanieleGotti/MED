package logiche_bottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import gui.*;
import logiche_bottoni_conferma.ConfermaAggiungiPaziente;
import logiche_frame_sezioni_ospedaliere.LogicaGenereAutomatico;
import logiche_frame_sezioni_ospedaliere.LogicaGiaPresente;
import modelli.ModelloGestoreLogicaGenerale;

public class LogicaBottoneInserisciPaziente extends LogicaBottone{

	/**
	 * Controller del bottone "inserisci paziente"
	 * Il pulsante può essere utilizzato soltanto da un operatore
	 */
	public LogicaBottoneInserisciPaziente(PazientiFrame v2, ModelloGestoreLogicaGenerale m) {
		super(v2,m);
		start();
	}
	
	/**
	 * Alla pressione del bottone verifica la mansione dell'utilizzatore corrente:
	 * se si tratta di un operatore, delega l'operazione di inserimento alla classe logica "ConfermaAggiungiPaziente", 
	 * avviando anche i frame appositi
	 * Alternativamente, mostra a schermo un relativo messaggio di errore
	 */
	protected void start() {
		//si registra al bottone prontoSoccorsoToggleButton
		frameDeiPazienti.inserisciPazienteButton1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (modello.modelloGestoreUtente.getMansioneUtente().equals("Operatore")) {
					InserisciPazienteFrame frame = new InserisciPazienteFrame();
					frameDeiPazienti.sfondoFrame.setEnabled(false);
					LogicaCaricaPdf carica = new LogicaCaricaPdf("");
					carica.impostaDropTargetSingleFile(frame.pdfPanel, frame.filesPanel, frameDeiPazienti.sfondoFrame);
					new ConfermaAggiungiPaziente(frame,frameDeiPazienti,modello,carica);
					new LogicaGenereAutomatico(frame);
					new LogicaGiaPresente(frame);
				}
				else {
					new ErroreFrame(frameDeiPazienti.sfondoFrame, "Ci dispiace informarla che, secondo le nostre politiche, il suo account da " + modello.modelloGestoreUtente.getMansioneUtente() + " non è abilitato all'inserimento di nuovi degenti, provi a contattare il reparto di logistica");
				}
			}
		});
	}
}
