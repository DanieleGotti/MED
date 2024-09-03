package logiche_frame_sezioni_ospedaliere;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import gui.InserisciPazienteFrame;
import gui.InserisciPazientePerVisitaFrame;
import gui.ModificaPazienteFrame;
import gui.ModificaPazienteNoUrgenzaFrame;

public class LogicaGenereAutomatico {
	
	private InserisciPazienteFrame frame;
	private InserisciPazientePerVisitaFrame frame2;
	private ModificaPazienteFrame frame3;
	private ModificaPazienteNoUrgenzaFrame frame4;
	private String casistica;
	private String opzione;
	
	/**
	 * Costruttore utilizzato per la gestione automatica del genere dopo l'inserimento del sesso
	 * @param f il frame di inserisci paziente
	 */
	public LogicaGenereAutomatico(InserisciPazienteFrame f) {
		frame = f;
		casistica = "Inserimento";
		start();
	}
	
	/**
	 * Costruttore utilizzato per la gestione automatica del genere dopo l'inserimento del sesso
	 * @param f il frame di modifica paziente
	 */
	public LogicaGenereAutomatico(ModificaPazienteFrame f) {
		frame3 = f;
		casistica = "ModificaV1";
		start();
	}
	
	/**
	 * Costruttore utilizzato per la gestione automatica del genere dopo l'inserimento del sesso
	 * @param f il frame di modifica senza urgenza
	 */
	public LogicaGenereAutomatico(ModificaPazienteNoUrgenzaFrame f) {
		frame4 = f;
		casistica = "ModificaV2";
		start();
	}
	
	/**
	 * Costruttore utilizzato per la gestione automatica del genere dopo l'inserimento del sesso
	 * @param f il frame di inserimento visita del paziente
	 */
	public LogicaGenereAutomatico(InserisciPazientePerVisitaFrame f) {
		frame2 = f;
		casistica = "InserimentoVisita";
		start();
	}
	
	/**
	 * A seconda del frame associato, registra un listener che aggiorna il campo di testo del genere in base all'opzione selezionata
	 */
	protected void start() {
		//si registra al bottone prontoSoccorsoToggleButton
		switch (casistica) {
			case "InserimentoVisita":
				frame2.sessoComboBox.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent  e) {
						if (e.getStateChange() == ItemEvent.SELECTED) {
							opzione = (String) frame2.sessoComboBox.getSelectedItem();
		                    switch(opzione) {
		                    	case "maschio":
		                    		frame2.genereTextField.setText("Maschio");
		                        	break;
		                    	case "femmina":
		                    		frame2.genereTextField.setText("Femmina");
		                        	break;
		                    }
		                }
					}
				});
				break;
			case "ModificaV1":
				frame3.sessoComboBox.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent  e) {
						if (e.getStateChange() == ItemEvent.SELECTED) {
							opzione = (String) frame3.sessoComboBox.getSelectedItem();
		                    switch(opzione) {
		                    	case "maschio":
		                    		frame3.genereTextField.setText("Maschio");
		                        	break;
		                    	case "femmina":
		                    		frame3.genereTextField.setText("Femmina");
		                        	break;
		                    }
		                }
					}
				});
				break;
			case "Inserimento":
				frame.sessoComboBox.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent  e) {
						if (e.getStateChange() == ItemEvent.SELECTED) {
							opzione = (String) frame.sessoComboBox.getSelectedItem();
		                    switch(opzione) {
		                    	case "maschio":
		                    		frame.genereTextField.setText("Maschio");
		                        	break;
		                    	case "femmina":
		                    		frame.genereTextField.setText("Femmina");
		                        	break;
		                    }
		                }
					}
				});
				break;
			case "ModificaV2":
				frame4.sessoComboBox.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent  e) {
						if (e.getStateChange() == ItemEvent.SELECTED) {
							opzione = (String) frame4.sessoComboBox.getSelectedItem();
		                    switch(opzione) {
		                    	case "maschio":
		                    		frame4.genereTextField.setText("Maschio");
		                        	break;
		                    	case "femmina":
		                    		frame4.genereTextField.setText("Femmina");
		                        	break;
		                    }
		                }
					}
				});
		};
	}
}