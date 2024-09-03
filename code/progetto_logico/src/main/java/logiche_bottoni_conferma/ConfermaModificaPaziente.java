package logiche_bottoni_conferma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import gestore_db.AggiornamentiJooq;
import gui.ModificaPazienteFrame;
import gui.ErroreFrame;
import gui.PazientiFrame;
import logiche_bottoni.LogicaCaricaPdf;
import logiche_frame_sezioni_ospedaliere.LogicaDellaPosizionePazienteTabella;
import modelli.ModelloGestoreLogicaGenerale;

public class ConfermaModificaPaziente {

	private ModificaPazienteFrame frame;
	private ModelloGestoreLogicaGenerale modello;
	private PazientiFrame frameDeiPazienti;
	private LogicaDellaPosizionePazienteTabella TabellaProntoSoccorso;
	private LogicaCaricaPdf carica;

	/** 
	 * Classe che prende i dati digitati nel relativo frame e li inserisce nel database
	 * @param v1 riferimento al frame per l'inserimento pazienti
	 * @param v2 riferimento al frame principale
	 * @param m riferimento al modello
	 */
	public ConfermaModificaPaziente(ModificaPazienteFrame v1, PazientiFrame v2, ModelloGestoreLogicaGenerale m, LogicaCaricaPdf c) {
		frame = v1;
		frameDeiPazienti = v2;
		modello = m;
		carica = c;
		TabellaProntoSoccorso = new LogicaDellaPosizionePazienteTabella(frameDeiPazienti,modello,modello.modelloGestorePaziente.getPosizione());
		start();
	}

	/**
	 * Una volta premuto il pulsante "conferma" nel frame di inserimento, prende i dati scritti e li utilizza
	 * per eseguire un insert nella tabella degente del database
	 * Inoltre permette la chiusura della finestra, premendo la X oppure confermando l'inserimento
	 * Alla chiusura della finestra il frame principale viene riabilitato e aggiornato con il nuovo dato
	 */
	protected void start() {
		frame.confermaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
						String nome = frame.nomeTextField.getText();
						String cognome = frame.cognomeTextField.getText();
						String genere = frame.genereTextField.getText();
							String etaTest = frame.etaTextField.getText();
							if (etaTest.matches("\\d+")) {
								String sesso;
								if (frame.sessoComboBox.getSelectedItem().equals("maschio")) {
									sesso = "M";
								}
								else {
									sesso = "F";
								}
								
								String urgenza = (String) frame.urgenzaComboBox.getSelectedItem();
								if(!frame.nomeTextField.getText().isBlank()) {
									AggiornamentiJooq.getIstanza().degente(modello.modelloGestorePaziente.getCodice(), modello.modelloGestorePaziente.getCount(), "nome" , nome );
								}
								if(!frame.cognomeTextField.getText().isBlank()) {
									AggiornamentiJooq.getIstanza().degente(modello.modelloGestorePaziente.getCodice(), modello.modelloGestorePaziente.getCount(), "cognome" , cognome );
								}
								if(!frame.etaTextField.getText().isBlank()) {
									AggiornamentiJooq.getIstanza().degente(modello.modelloGestorePaziente.getCodice(), modello.modelloGestorePaziente.getCount(), "eta" , frame.etaTextField.getText() );
								}
								if(!frame.genereTextField.getText().isBlank()) {
									AggiornamentiJooq.getIstanza().degente(modello.modelloGestorePaziente.getCodice(), modello.modelloGestorePaziente.getCount(), "genere" , genere);
								}
								AggiornamentiJooq.getIstanza().degente(modello.modelloGestorePaziente.getCodice(), modello.modelloGestorePaziente.getCount(), "sesso" , sesso );
								AggiornamentiJooq.getIstanza().degente(modello.modelloGestorePaziente.getCodice(), modello.modelloGestorePaziente.getCount(), "urgenza" , urgenza );
								modello.modelloGestorePaziente.deselezionaPaziente();
								frameDeiPazienti.pazienteImage = new ImageIcon("../progetto_gui/src/main/resources/fototessera_default.png");
								frameDeiPazienti.immagineLabel.repaint();
								frameDeiPazienti.updateStringaPaziente();
								TabellaProntoSoccorso.update();
								frameDeiPazienti.updateViewTabella();
								String cartellaCountPaz = "count_" + modello.modelloGestorePaziente.getCount() + "__data__" + modello.modelloGestorePaziente.getData();
								File sottoCartella = new File("./../../pazienti/id_" + modello.modelloGestorePaziente.getCodice() + "/dati_anagrafici/" + cartellaCountPaz);
								if (!sottoCartella.exists()) {
									sottoCartella.mkdirs();
								}
								carica.destinazione = new File("./../../pazienti/id_" + modello.modelloGestorePaziente.getCodice() + "/dati_anagrafici/" + cartellaCountPaz);
								carica.salvaConTrascinamento("Fototessera.png",true);
								frameDeiPazienti.sfondoFrame.setEnabled(true);
								frame.sfondoFrame.dispose();
							}
							else {
								new ErroreFrame(frame.sfondoFrame, "L'età deve essere un numero");
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
