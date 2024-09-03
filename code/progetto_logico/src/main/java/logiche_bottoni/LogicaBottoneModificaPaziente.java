package logiche_bottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import gui.*;
import logiche_bottoni_conferma.ConfermaModificaPaziente;
import logiche_frame_sezioni_ospedaliere.LogicaGenereAutomatico;
import modelli.ModelloGestoreLogicaGenerale;

public class LogicaBottoneModificaPaziente extends LogicaBottone{

	/**
	 * Controller del bottone "modifica paziente"
	 * Il pulsante può essere utilizzato soltanto da un operatore
	 */
	public LogicaBottoneModificaPaziente(PazientiFrame v2, ModelloGestoreLogicaGenerale m) {
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
						String codice = modello.modelloGestorePaziente.getCodice();
						Boolean anonimo = false;
						try {
					        Integer.parseInt(codice);
					        anonimo = true;
					    } catch (NumberFormatException e1) {
					    	anonimo = false;
					    }
						finally {
							if(!anonimo) {
								ModificaPazienteFrame frame = new ModificaPazienteFrame();
								frameDeiPazienti.sfondoFrame.setEnabled(false);
								LogicaCaricaPdf carica = new LogicaCaricaPdf("");
								carica.impostaDropTargetSingleFile(frame.pdfPanel, frame.filesPanel, frameDeiPazienti.sfondoFrame);
								new ConfermaModificaPaziente(frame,frameDeiPazienti,modello,carica);
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
								frame.codiceTextField.setText(modello.modelloGestorePaziente.getCodice());
								frame.codiceTextField.setEnabled(false);
								frame.urgenzaComboBox.setSelectedItem(modello.modelloGestorePaziente.getUrgenza());
							}
							else {
								new ErroreFrame(frameDeiPazienti.sfondoFrame, "Ci dispiace informarle che al momento non è possibile effettuare la modifica dei dati di un paziente Anonimo, se pensa di aver scoperto l'identità del paziente dimetta il paziente anonimo in questione e risvolga le operazioni di accoglienza, si ricordi inoltre di copiare i dati importanti dalla sua cartella clinica nella nuova cartella");
							}
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
		
		frameDeiPazienti.modificaPazienteButton1.addActionListener(listener);
		frameDeiPazienti.modificaPazienteButton2.addActionListener(listener);
	}
}
