package logiche_bottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import gui.*;
import modelli.ModelloGestoreLogicaGenerale;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class LogicaBottoneApriCartella extends LogicaBottone{
	
	/**
	 * Controller del bottone "apri cartella", utilizzabile solo da "medico" (mansione "M") e "infermiere" (mansione "I")
	 */
	public LogicaBottoneApriCartella(PazientiFrame v2, ModelloGestoreLogicaGenerale m) {
		super(v2,m);
		start();
	}
	
	/**
	 * Alla pressione del bottone verifica la selezione di un utente e se è presente la cartella del paziente la apre
	 */
	protected void start() {
		
		ActionListener listener = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
			    	if(modello.modelloGestorePaziente.qualcunoSelezionato()) {
				    	if(!modello.modelloGestoreUtente.getMansioneUtente().equals("Operatore")) {
						String percorsoCartella = "./../../pazienti/id_" + modello.modelloGestorePaziente.getCodice();
				        //crea un oggetto File che rappresenta la cartella
				        File cartella = new File(percorsoCartella);
	
				        if (cartella.exists() && cartella.isDirectory()) {
				            //controlla se Desktop è supportato
				            if (Desktop.isDesktopSupported()) {
				                Desktop desktop = Desktop.getDesktop();
				                try {
				                    //apri la cartella
				                    desktop.open(cartella);
				                } catch (IOException er) {
				                    er.printStackTrace();
				                }
				            } else {
				            	new ErroreFrame(frameDeiPazienti.sfondoFrame, "Desktop non supportato su questo sistema");
				            }
				        } else {
				        	new ErroreFrame(frameDeiPazienti.sfondoFrame, "La cartella specificata non esiste o non è una directory");
				        }
					}
					else {
						new ErroreFrame(frameDeiPazienti.sfondoFrame, "Per motivi di privacy il suo account non è abilitato all' apertura di cartelle cliniche, si rivolga a un medico o a un infermiere");
					}
		    	}
		    	else {
		    		new ErroreFrame(frameDeiPazienti.sfondoFrame, "Deve selezionare prima il paziente di cui vuole aprire la cartella clinica");
		    	}
		    }
		};
		frameDeiPazienti.apriCartellaButton1.addActionListener(listener);
		frameDeiPazienti.apriCartellaButton2.addActionListener(listener);
		frameDeiPazienti.apriCartellaButton3.addActionListener(listener);
		frameDeiPazienti.apriCartellaButton4.addActionListener(listener);
		frameDeiPazienti.apriCartellaButton5.addActionListener(listener);
	}
}
