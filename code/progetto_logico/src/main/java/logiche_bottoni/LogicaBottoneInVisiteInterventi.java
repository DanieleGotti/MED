package logiche_bottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import gui.*;
import logiche_frame_sezioni_ospedaliere.LogicaDellaPosizionePazienteTabella;
import modelli.ModelloGestoreLogicaGenerale;

public class LogicaBottoneInVisiteInterventi extends LogicaBottone{
	
	private LogicaDellaPosizionePazienteTabella tabellaInVisiteInterventi;
	private ImageIcon defaultImage = new ImageIcon("../progetto_gui/src/main/resources/fototessera_default.png");;
	
	/**
	 * Logica del pulsante di selezione della sezione "In visite ed interventi"
	 */
	public LogicaBottoneInVisiteInterventi(PazientiFrame v2, ModelloGestoreLogicaGenerale m) {
		super(v2,m);
		tabellaInVisiteInterventi = new LogicaDellaPosizionePazienteTabella(frameDeiPazienti, modello,"Visita Intervento");
		start();
	}
	
	/**
	 * Alla selezione della sezione, vengono sostituiti i pulsanti laterali 
	 * e viene mostrata a schermo la lista dei degenti in visite e interventi
	 */
	protected void start() {
		frameDeiPazienti.pazienteImage = defaultImage;
		frameDeiPazienti.immagineLabel.repaint();
		frameDeiPazienti.visiteInterventiToggleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frameDeiPazienti.pazienteImage = defaultImage;
				frameDeiPazienti.immagineLabel.repaint();
				frameDeiPazienti.prontoSoccorsoToggleButton.setSelected(false);
				frameDeiPazienti.inCaricoToggleButton.setSelected(false);
				frameDeiPazienti.repartoToggleButton.setSelected(false);
				frameDeiPazienti.visiteInterventiToggleButton.setSelected(true);
				frameDeiPazienti.dimessiToggleButton.setSelected(false);
				frameDeiPazienti.repartoScrollPane.setVisible(false);
				frameDeiPazienti.urgenzaComboBox.setSelectedItem(" ");
				frameDeiPazienti.cercaTextField.setText("");
				frameDeiPazienti.repartoComboBox.setSelectedItem(" ");
				modello.modelloGestorePaziente.deselezionaPaziente();
				tabellaInVisiteInterventi.update();
				SwingUtilities.invokeLater(new Runnable() {
				@Override
					public void run() {
						frameDeiPazienti.urgenzaLabel.setVisible(false);
						frameDeiPazienti.urgenzaComboBox.setVisible(false);
						frameDeiPazienti.repartoLabel.setVisible(false);
						frameDeiPazienti.repartoComboBox.setVisible(false);
						frameDeiPazienti.tipoLabel.setVisible(true);
						frameDeiPazienti.tipoComboBox.setVisible(true);
						frameDeiPazienti.mieiPazientiCheckBox.setVisible(true);
						frameDeiPazienti.prontoSoccorsoBottoniPanel.setVisible(false);
						frameDeiPazienti.prendereCaricoBottoniPanel.setVisible(false);
						frameDeiPazienti.repartoBottoniPanel.setVisible(false);
						frameDeiPazienti.visiteInterventiBottoniPanel.setVisible(true);
						frameDeiPazienti.dimessiBottoniPanel.setVisible(false);
						frameDeiPazienti.repartoScrollPane.setVisible(false);
						frameDeiPazienti.updateViewTabella();
						frameDeiPazienti.pazienteImage = defaultImage;
						frameDeiPazienti.immagineLabel.repaint();
						frameDeiPazienti.updateStringaPaziente();
						if(!modello.modelloGestoreUtente.getMansioneUtente().equals("Medico")) {
							frameDeiPazienti.mieiPazientiCheckBox.setVisible(false);
						}
					}
				});
			}
		});
	}
}