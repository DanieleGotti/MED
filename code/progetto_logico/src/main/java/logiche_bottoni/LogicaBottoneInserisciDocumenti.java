package logiche_bottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import gui.*;
import logiche_bottoni_conferma.ConfermaAggiungiDocumenti;
import modelli.ModelloGestoreLogicaGenerale;


public class LogicaBottoneInserisciDocumenti extends LogicaBottone{

	/**
	 * Pulsante che permette l'inserimento di documenti nella cartella del paziente nel file system
	 */
	public LogicaBottoneInserisciDocumenti(PazientiFrame v2, ModelloGestoreLogicaGenerale m) {
		super(v2,m);
		start();
	}
	
	/**
	 * Alla pressione del bottone verifica la selezione di un utente e la mansione dell'utilizzatore corrente:
	 * se tutto è in regola delega l'operazione di inserimento alla classe logica "ConfermaAggiungiDiariaMedica", 
	 * avviando anche i frame appositi
	 */
	protected void start() {
		ActionListener listener = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	if(modello.modelloGestorePaziente.qualcunoSelezionato()) {
			    	CaricaDocumentiFrame frame = new CaricaDocumentiFrame();
					frameDeiPazienti.sfondoFrame.setEnabled(false);
					LogicaCaricaPdf carica = new LogicaCaricaPdf("");
					carica.impostaDropTarget(frame.pdfPanel, frame.filesPanel);
					new ConfermaAggiungiDocumenti(frame,frameDeiPazienti,modello,carica);
		    	}
		    	else {
		            new ErroreFrame(frameDeiPazienti.sfondoFrame, "Deve selezionare prima il paziente a cui vuole aggiungere la documentazione");
		        }
		    }
		};

		frameDeiPazienti.caricaDocumentiButton1.addActionListener(listener);
		frameDeiPazienti.caricaDocumentiButton2.addActionListener(listener);
		frameDeiPazienti.caricaDocumentiButton3.addActionListener(listener);
		frameDeiPazienti.caricaDocumentiButton4.addActionListener(listener);
	}
}
