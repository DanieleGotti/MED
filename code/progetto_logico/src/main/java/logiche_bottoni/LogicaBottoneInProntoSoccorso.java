package logiche_bottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import gui.*;
import logiche_frame_sezioni_ospedaliere.LogicaDellaPosizionePazienteTabella;
import modelli.ModelloGestoreLogicaGenerale;

public class LogicaBottoneInProntoSoccorso extends LogicaBottone{
	
	private LogicaDellaPosizionePazienteTabella tabellaProntoSoccorso;
	private ImageIcon defaultImage = new ImageIcon("../progetto_gui/src/main/resources/fototessera_default.png");;
	
	/**
	 * Logica del pulsante di selezione della sezione "in pronto soccorso" 
	 */
	public LogicaBottoneInProntoSoccorso(PazientiFrame v2, ModelloGestoreLogicaGenerale m) {
		super(v2,m);
		tabellaProntoSoccorso = new LogicaDellaPosizionePazienteTabella(frameDeiPazienti, modello,"in Pronto Soccorso");
		start();
	}
	
	/**
	 * Alla selezione della sezione, vengono sostituiti i pulsanti laterali 
	 * e viene mostrata a schermo la lista dei degenti in pronto soccorso
	 */
	protected void start() {
		frameDeiPazienti.pazienteImage = defaultImage;
		frameDeiPazienti.immagineLabel.repaint();
		frameDeiPazienti.prontoSoccorsoToggleButton.setSelected(true);
		frameDeiPazienti.inCaricoToggleButton.setSelected(false);
		frameDeiPazienti.repartoToggleButton.setSelected(false);
		frameDeiPazienti.visiteInterventiToggleButton.setSelected(false);
		frameDeiPazienti.dimessiToggleButton.setSelected(false);
		frameDeiPazienti.prontoSoccorsoToggleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frameDeiPazienti.pazienteImage = defaultImage;
				frameDeiPazienti.immagineLabel.repaint();
				frameDeiPazienti.prontoSoccorsoToggleButton.setSelected(true);
				frameDeiPazienti.inCaricoToggleButton.setSelected(false);
				frameDeiPazienti.repartoToggleButton.setSelected(false);
				frameDeiPazienti.visiteInterventiToggleButton.setSelected(false);
				frameDeiPazienti.dimessiToggleButton.setSelected(false);
				frameDeiPazienti.repartoScrollPane.setVisible(false);
				frameDeiPazienti.urgenzaComboBox.setSelectedItem(" ");
				frameDeiPazienti.cercaTextField.setText("");
				frameDeiPazienti.repartoComboBox.setSelectedItem(" ");
				modello.modelloGestorePaziente.deselezionaPaziente();
				tabellaProntoSoccorso.update();
				SwingUtilities.invokeLater(new Runnable() {
				@Override
					public void run() {
						frameDeiPazienti.urgenzaLabel.setVisible(true);
						frameDeiPazienti.urgenzaComboBox.setVisible(true);
						frameDeiPazienti.repartoLabel.setVisible(false);
						frameDeiPazienti.repartoComboBox.setVisible(false);
						frameDeiPazienti.tipoLabel.setVisible(false);
						frameDeiPazienti.tipoComboBox.setVisible(false);
						frameDeiPazienti.mieiPazientiCheckBox.setVisible(false);
						frameDeiPazienti.prontoSoccorsoBottoniPanel.setVisible(true);
						frameDeiPazienti.prendereCaricoBottoniPanel.setVisible(false);
						frameDeiPazienti.repartoBottoniPanel.setVisible(false);
						frameDeiPazienti.visiteInterventiBottoniPanel.setVisible(false);
						frameDeiPazienti.dimessiBottoniPanel.setVisible(false);
						frameDeiPazienti.repartoScrollPane.setVisible(false);
						frameDeiPazienti.updateViewTabella();
						frameDeiPazienti.pazienteImage = defaultImage;
						frameDeiPazienti.immagineLabel.repaint();
						frameDeiPazienti.updateStringaPaziente();
					}
				});
			}
		});
	}
}
