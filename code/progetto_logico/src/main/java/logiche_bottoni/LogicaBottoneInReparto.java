package logiche_bottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import gui.*;
import logiche_frame_sezioni_ospedaliere.LogicaDellaPosizionePazienteTabella;
import modelli.ModelloGestoreLogicaGenerale;

public class LogicaBottoneInReparto extends LogicaBottone{
	
	private LogicaDellaPosizionePazienteTabella tabellaInReparto;
	private ImageIcon defaultImage = new ImageIcon("../progetto_gui/src/main/resources/fototessera_default.png");
	
	/**
	 * Logica del pulsante di selezione della sezione "In reparto"
	 */
	public LogicaBottoneInReparto(PazientiFrame v2, ModelloGestoreLogicaGenerale m) {
		super(v2,m);
		tabellaInReparto = new LogicaDellaPosizionePazienteTabella(frameDeiPazienti, modello,"in Reparto");
		start();
	}
	
	/**
	 * Alla selezione della sezione, vengono sostituiti i pulsanti laterali 
	 * e viene mostrata a schermo la lista dei degenti in reparto
	 */
	protected void start() {
		frameDeiPazienti.pazienteImage = defaultImage;
		frameDeiPazienti.immagineLabel.repaint();
		frameDeiPazienti.repartoToggleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frameDeiPazienti.pazienteImage = defaultImage;
				frameDeiPazienti.immagineLabel.repaint();
				frameDeiPazienti.prontoSoccorsoToggleButton.setSelected(false);
				frameDeiPazienti.inCaricoToggleButton.setSelected(false);
				frameDeiPazienti.repartoToggleButton.setSelected(true);
				frameDeiPazienti.visiteInterventiToggleButton.setSelected(false);
				frameDeiPazienti.dimessiToggleButton.setSelected(false);
				frameDeiPazienti.repartoScrollPane.setVisible(true);
				frameDeiPazienti.urgenzaComboBox.setSelectedItem(" ");
				frameDeiPazienti.cercaTextField.setText("");
				frameDeiPazienti.repartoComboBox.setSelectedItem(" ");
				modello.modelloGestorePaziente.deselezionaPaziente();
				tabellaInReparto.update();
				SwingUtilities.invokeLater(new Runnable() {
				@Override
					public void run() {
						frameDeiPazienti.urgenzaLabel.setVisible(false);
						frameDeiPazienti.urgenzaComboBox.setVisible(false);
						frameDeiPazienti.repartoLabel.setVisible(true);
						frameDeiPazienti.repartoComboBox.setVisible(true);
						frameDeiPazienti.tipoLabel.setVisible(false);
						frameDeiPazienti.tipoComboBox.setVisible(false);
						frameDeiPazienti.mieiPazientiCheckBox.setVisible(false);
						frameDeiPazienti.prontoSoccorsoBottoniPanel.setVisible(false);
						frameDeiPazienti.prendereCaricoBottoniPanel.setVisible(false);
						frameDeiPazienti.repartoBottoniPanel.setVisible(true);
						frameDeiPazienti.visiteInterventiBottoniPanel.setVisible(false);
						frameDeiPazienti.dimessiBottoniPanel.setVisible(false);
						frameDeiPazienti.repartoScrollPane.setVisible(true);
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
