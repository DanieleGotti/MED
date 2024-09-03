package logiche_bottoni;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ImageIcon;
import gui.*;
import logiche_frame_sezioni_ospedaliere.LogicaDelTipoPrenotazioneTabella;
import logiche_frame_sezioni_ospedaliere.LogicaDellaPosizionePazienteTabella;
import modelli.ModelloGestoreLogicaGenerale;

public class LogicaBottoneFiltroVisiteInterventi extends LogicaBottone{
	
	private LogicaDelTipoPrenotazioneTabella tabellaFiltrataTipo;
	private LogicaDellaPosizionePazienteTabella tabellaPosizioneNonFiltrata;
	private String opzione;
	
	/**
	 * Filtro che permette la ricerca dei degenti attraverso il tipo di trattamento prenotato, "visita" o "intervento"
	 */
	public LogicaBottoneFiltroVisiteInterventi(PazientiFrame v2, ModelloGestoreLogicaGenerale m) {
		super(v2,m);
		start();
	}
	
	/**
	 * Alla selezione di un campo della tendina vengono selezionati e mostrati all'utente 
	 * solo i degenti che sono in visita o in intervento
	 * Se viene selezionato il riquadro vuoto la selezione viene annullata e vengono posti a schermo
	 * i dati di tutti i degenti nella sezione
	 */
	protected void start() {
		frameDeiPazienti.tipoComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent  e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					modello.modelloGestorePaziente.deselezionaPaziente();
					frameDeiPazienti.updateStringaPaziente();
					opzione = (String) frameDeiPazienti.tipoComboBox.getSelectedItem();
                    switch(opzione) {
                    	case "Visite":
                    		tabellaFiltrataTipo = new LogicaDelTipoPrenotazioneTabella(frameDeiPazienti, modello,"Visita");
                    		tabellaFiltrataTipo.update();
                        	break;
                    	case "Interventi":
                    		tabellaFiltrataTipo = new LogicaDelTipoPrenotazioneTabella(frameDeiPazienti, modello,"Intervento");
                    		tabellaFiltrataTipo.update();
                        	break;
                    	default:
                    		tabellaPosizioneNonFiltrata = new LogicaDellaPosizionePazienteTabella(frameDeiPazienti, modello, frameDeiPazienti.posizioneAttuale);
                    		tabellaPosizioneNonFiltrata.update();
                    		break;
                    }
                }
				frameDeiPazienti.cercaTextField.setText("");
				frameDeiPazienti.mieiPazientiCheckBox.setSelected(false);
				
				frameDeiPazienti.pazienteImage = new ImageIcon("../progetto_gui/src/main/resources/fototessera_default.png");
				frameDeiPazienti.immagineLabel.repaint();
			}
		});
	}
}
