package logiche_bottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import gui.*;
import logiche_bottoni_conferma.ConfermaModificaPazienteV2;
import logiche_frame_sezioni_ospedaliere.LogicaGenereAutomatico;
import modelli.ModelloGestoreLogicaGenerale;

public class LogicaBottoneModificaPazienteV2 extends LogicaBottone{

	/**
	 * Controller del bottone "modifica paziente" per sezione "in reparto" e "visite interventi" senza "urgenza"
	 * Il pulsante può essere utilizzato soltanto da un operatore
	 */
	public LogicaBottoneModificaPazienteV2(PazientiFrame v2, ModelloGestoreLogicaGenerale m) {
		super(v2,m);
		start();
	}
	
	/**
	 * Alla pressione del bottone verifica la mansione dell'utilizzatore corrente:
	 * se si tratta di un operatore, delega l'operazione di inserimento alla classe logica "ConfermaModificaPaziente", 
	 * avviando anche i frame appositi
	 * Alternativamente, mostra a schermo un relativo messaggio di errore
	 */
	protected void start() {
		//si registra al bottone prontoSoccorsoToggleButton
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (modello.modelloGestoreUtente.getMansioneUtente().equals("Operatore")) {
					if(modello.modelloGestorePaziente.qualcunoSelezionato()) {
						ModificaPazienteNoUrgenzaFrame frame = new ModificaPazienteNoUrgenzaFrame();
						frameDeiPazienti.sfondoFrame.setEnabled(false);
						LogicaCaricaPdf carica = new LogicaCaricaPdf("");
						carica.impostaDropTargetSingleFile(frame.pdfPanel, frame.filesPanel, frameDeiPazienti.sfondoFrame);
						new ConfermaModificaPazienteV2(frame,frameDeiPazienti,modello,carica);
						frame.nomeTextField.setText(modello.modelloGestorePaziente.getNome());
						frame.cognomeTextField.setText(modello.modelloGestorePaziente.getCognome());
						frame.etaTextField.setText(Integer.toString(modello.modelloGestorePaziente.getEta()));
						frame.genereTextField.setText(modello.modelloGestorePaziente.getGenere());
						new LogicaGenereAutomatico(frame);
						if(modello.modelloGestorePaziente.getSesso().equals("M")) {
							frame.sessoComboBox.setSelectedItem("maschio");
						}
						else {
							frame.sessoComboBox.setSelectedItem("femmina");
						}
						if(modello.modelloGestorePaziente.getCodice().equals("Anonimo")) {
							frame.codiceTextField.setText("Anonimo");
						}
						else {
							frame.codiceTextField.setText(modello.modelloGestorePaziente.getCodice());
							frame.codiceTextField.setEnabled(false);
						}
					}
					else {
						new ErroreFrame(frameDeiPazienti.sfondoFrame, "Deve selezionare prima il paziente di cui vuole modificare i dati");
					}
				}
				else {
					new ErroreFrame(frameDeiPazienti.sfondoFrame, "Ci dispiace informarla che, secondo le nostre politiche, il suo account da " + modello.modelloGestoreUtente.getMansioneUtente() + " non è abilitato alla modifica dei dati dei degenti, provi a contattare il reparto di logistica");
				}
			}
		};
		frameDeiPazienti.modificaPazienteButton3.addActionListener(listener);
		frameDeiPazienti.modificaPazienteButton4.addActionListener(listener);
	}
}
