package logiche_bottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import gui.PazientiFrame;
import gui.ProfiloUtenteFrame;
import modelli.ModelloGestoreLogicaGenerale;

public class LogicaBottoneProfilo extends LogicaBottone{
	
	/**
	 * Controllore del pulsante "Profilo"
	 * Permette a un utente di aprire le informazioni utili
	 */
	public LogicaBottoneProfilo(PazientiFrame v2, ModelloGestoreLogicaGenerale m) {
		super(v2,m);
		start();
	}
	
	/**
	 * Alla pressione del pulsante, si apre un frame contenente le informazioni dell'utente
	 */
	protected void start() {
		//si registra al bottone Logout
		frameDeiPazienti.profiloButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			ProfiloUtenteFrame profilo = new ProfiloUtenteFrame(modello);
			frameDeiPazienti.sfondoFrame.setEnabled(false);
			profilo.riempi();
			new LogicaBottoneLogout(profilo,frameDeiPazienti,modello);
			new LogicaBottoneCambioPw(profilo,frameDeiPazienti,modello);
			}
		});
	}
}
