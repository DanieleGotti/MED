package logiche_bottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import gui.CambiaPasswordFrame;
import gui.PazientiFrame;
import gui.ProfiloUtenteFrame;
import logiche_bottoni_conferma.ConfermaCambiaPassword;
import modelli.ModelloGestoreLogicaGenerale;

public class LogicaBottoneCambioPw extends LogicaBottone{
	
	public ProfiloUtenteFrame profilo;
	
	/**
	 * Controllore del pulsante "cambio pw"
	 * permette a un utente di cambiare la password
	 */
	public LogicaBottoneCambioPw(ProfiloUtenteFrame p, PazientiFrame v2, ModelloGestoreLogicaGenerale m) {
		super(v2,m);
		profilo = p;
		start();
	}
	
	/**
	 * Alla pressione del pulsante, si apre il frame associato
	 */
	protected void start() {
		//si registra al bottone cambiaPassButton
		profilo.cambiaPassButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CambiaPasswordFrame cambia = new CambiaPasswordFrame();
				profilo.sfondoFrame.dispose();
				frameDeiPazienti.sfondoFrame.setEnabled(false);
				new ConfermaCambiaPassword(cambia,frameDeiPazienti,modello);
			}
		});
		
		profilo.sfondoFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                frameDeiPazienti.sfondoFrame.setEnabled(true);
                profilo.sfondoFrame.dispose();
            }
        });
	}
	
}
