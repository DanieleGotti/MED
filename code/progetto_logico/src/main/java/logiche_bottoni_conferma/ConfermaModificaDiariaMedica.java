package logiche_bottoni_conferma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import gestore_db.AggiornamentiJooq;
import gui.ModificaDiariaFrame;
import gui.ErroreFrame;
import gui.PazientiFrame;
import modelli.ModelloGestoreLogicaGenerale;

public class ConfermaModificaDiariaMedica {
	
	private ModificaDiariaFrame frame;
	private ModelloGestoreLogicaGenerale modello;
	private PazientiFrame frameDeiPazienti;
	
	/** 
	 * Classe che prende i dati digitati nel relativo frame e li utilizza per modificare i dati nel database
	 * @param v1 riferimento al frame per le modifiche alle diarieMed
	 * @param v2 riferimento al frame principale
	 * @param m riferimento al modello
	 */
	public ConfermaModificaDiariaMedica(ModificaDiariaFrame v1, PazientiFrame v2, ModelloGestoreLogicaGenerale m) {
		frameDeiPazienti = v2;
		frame = v1;
		modello = m;
		start();
	}
	
	/**
	 * Una volta premuto il pulsante "conferma" nel frame di modifica, prende i dati scritti e li utilizza
	 * per eseguire un update nella tabella diariaMed del database
	 * Inoltre permette la chiusura della finestra, premendo la X oppure confermando l'inserimento
	 * Alla chiusura della finestra il frame principale viene riabilitato
	 */
	protected void start() {
		frame.confermaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					String motivo = frame.motivoTextField.getText();
					String storico = frame.storicoTextField.getText();
					String farmaci = frame.farmaciTextArea.getText();
					String informazioni = frame.infoTextArea.getText();
					String codicePaziente = modello.modelloGestorePaziente.getCodice();
					int countPaziente = modello.modelloGestorePaziente.getCount();
					if(!motivo.isBlank() && !storico.isBlank() && !farmaci.isBlank()) {
						AggiornamentiJooq.getIstanza().diariaMed(1, codicePaziente, countPaziente, "storico", storico);
						AggiornamentiJooq.getIstanza().diariaMed(1, codicePaziente, countPaziente, "motivo", motivo);
						AggiornamentiJooq.getIstanza().diariaMed(1, codicePaziente, countPaziente, "farmaci", farmaci);
						AggiornamentiJooq.getIstanza().diariaMed(1, codicePaziente, countPaziente, "allergie", informazioni);
						AggiornamentiJooq.getIstanza().diariaMed(1, codicePaziente, countPaziente, "medico", modello.modelloGestoreUtente.getCodiceUtente());
						int nriga = frameDeiPazienti.table.getSelectedRow();
						frameDeiPazienti.table.clearSelection();
						frameDeiPazienti.table.setRowSelectionInterval(nriga, nriga);
						frameDeiPazienti.updateStringaPaziente();
						frameDeiPazienti.sfondoFrame.setEnabled(true);
						frame.sfondoFrame.dispose();
					}else {
						new ErroreFrame(frame.sfondoFrame, "Alcuni campi sono vuoti");
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
