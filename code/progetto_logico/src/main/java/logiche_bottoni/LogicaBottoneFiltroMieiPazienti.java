package logiche_bottoni;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ImageIcon;
import gui.*;
import logiche_frame_sezioni_ospedaliere.LogicaDeiMieiPazientiTabella;
import logiche_frame_sezioni_ospedaliere.LogicaDellaPosizionePazienteTabella;
import modelli.ModelloGestoreLogicaGenerale;

public class LogicaBottoneFiltroMieiPazienti extends LogicaBottone{
	
	private LogicaDeiMieiPazientiTabella tabellaFiltrataMieiPazienti;
	private LogicaDellaPosizionePazienteTabella tabellaPosizioneNonFiltrata;
	
	/**
	 * Filtro che permette la ricerca dei degenti presi in cura dal medico attivo come utente
	 */
	public LogicaBottoneFiltroMieiPazienti(PazientiFrame v2, ModelloGestoreLogicaGenerale m) {
		super(v2,m);
		start();
	}
	
	/**
	 * Alla selezione del checkbox vengono mostrati all'utente solo i degenti del medico attivo
	 */
	protected void start() {
		frameDeiPazienti.mieiPazientiCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent  e) {
				modello.modelloGestorePaziente.deselezionaPaziente();
				frameDeiPazienti.updateStringaPaziente();
				frameDeiPazienti.cercaTextField.setText("");
				frameDeiPazienti.tipoComboBox.setSelectedItem(" ");
				if (e.getStateChange() == ItemEvent.SELECTED) {
					frameDeiPazienti.mieiPazientiCheckBox.setSelected(true);
					tabellaFiltrataMieiPazienti = new LogicaDeiMieiPazientiTabella(frameDeiPazienti, modello, modello.modelloGestoreUtente.getCodiceUtente());
					tabellaFiltrataMieiPazienti.update();
		            
		        } else if (e.getStateChange() == ItemEvent.DESELECTED) {
					frameDeiPazienti.mieiPazientiCheckBox.setSelected(false);
		        	tabellaPosizioneNonFiltrata = new LogicaDellaPosizionePazienteTabella(frameDeiPazienti, modello, frameDeiPazienti.posizioneAttuale);
            		tabellaPosizioneNonFiltrata.update();
		        }
				
				frameDeiPazienti.pazienteImage = new ImageIcon("../progetto_gui/src/main/resources/fototessera_default.png");
				frameDeiPazienti.immagineLabel.repaint();
			}
		});
	}
}
