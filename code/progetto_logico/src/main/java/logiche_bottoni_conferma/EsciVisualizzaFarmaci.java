package logiche_bottoni_conferma;

import gui.PazientiFrame;
import gui.VisualizzaFarmaciFrame;

public class EsciVisualizzaFarmaci {
	
	private VisualizzaFarmaciFrame frame;
	private PazientiFrame frameDeiPazienti;

	/**
	 * Classe che permette di riabilitare il frame principale alla chiusura della finestra
	 * @param v1 riferimento alla finestra di visualizzazione dei farmaci utilizzati
	 * @param v2 riferimento al frame principale
	 */
	public EsciVisualizzaFarmaci(VisualizzaFarmaciFrame v1, PazientiFrame v2) {
		frame = v1;
		frameDeiPazienti = v2;
		start();
	}

	/**
	 * Alla chiusura della finestra riabilita il frame di sfondo
	 */
	protected void start() {
		frame.sfondoFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                frameDeiPazienti.sfondoFrame.setEnabled(true);
                frame.sfondoFrame.dispose();
            }
        });
	}
}
