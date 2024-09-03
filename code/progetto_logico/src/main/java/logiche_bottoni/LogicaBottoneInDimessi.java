package logiche_bottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import gui.*;
import logiche_frame_sezioni_ospedaliere.LogicaDellaPosizionePazienteTabella;
import modelli.ModelloGestoreLogicaGenerale;

public class LogicaBottoneInDimessi extends LogicaBottone{
	
	private LogicaDellaPosizionePazienteTabella tabellaInDimessi;
	private ImageIcon defaultImage = new ImageIcon("../progetto_gui/src/main/resources/fototessera_default.png");
	
	/**
	 * Logica del pulsante di selezione della sezione "In dimessi"
	 */
	public LogicaBottoneInDimessi(PazientiFrame v2, ModelloGestoreLogicaGenerale m) {
		super(v2,m);
		tabellaInDimessi = new LogicaDellaPosizionePazienteTabella(frameDeiPazienti, modello,"Dimesso");
		start();
	}
	
	/**
	 * Alla selezione della sezione, vengono sostituiti i pulsanti laterali 
	 * e viene mostrata a schermo la lista dei degenti in dimessi
	 */
	protected void start() {
		frameDeiPazienti.pazienteImage = defaultImage;
		frameDeiPazienti.immagineLabel.repaint();
		frameDeiPazienti.dimessiToggleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frameDeiPazienti.pazienteImage = defaultImage;
				frameDeiPazienti.immagineLabel.repaint();
				frameDeiPazienti.prontoSoccorsoToggleButton.setSelected(false);
				frameDeiPazienti.inCaricoToggleButton.setSelected(false);
				frameDeiPazienti.repartoToggleButton.setSelected(false);
				frameDeiPazienti.visiteInterventiToggleButton.setSelected(false);
				frameDeiPazienti.dimessiToggleButton.setSelected(true);
				frameDeiPazienti.repartoScrollPane.setVisible(false);
				frameDeiPazienti.urgenzaComboBox.setSelectedItem(" ");
				frameDeiPazienti.cercaTextField.setText("");
				frameDeiPazienti.repartoComboBox.setSelectedItem(" ");
				modello.modelloGestorePaziente.deselezionaPaziente();
				tabellaInDimessi.update();
				SwingUtilities.invokeLater(new Runnable() {
				@Override
					public void run() {
						frameDeiPazienti.urgenzaLabel.setVisible(false);
						frameDeiPazienti.urgenzaComboBox.setVisible(false);
						frameDeiPazienti.repartoLabel.setVisible(false);
						frameDeiPazienti.repartoComboBox.setVisible(false);
						frameDeiPazienti.tipoLabel.setVisible(false);
						frameDeiPazienti.tipoComboBox.setVisible(false);
						frameDeiPazienti.mieiPazientiCheckBox.setVisible(false);
						frameDeiPazienti.prontoSoccorsoBottoniPanel.setVisible(false);
						frameDeiPazienti.prendereCaricoBottoniPanel.setVisible(false);
						frameDeiPazienti.repartoBottoniPanel.setVisible(false);
						frameDeiPazienti.visiteInterventiBottoniPanel.setVisible(false);
						frameDeiPazienti.dimessiBottoniPanel.setVisible(true);
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
