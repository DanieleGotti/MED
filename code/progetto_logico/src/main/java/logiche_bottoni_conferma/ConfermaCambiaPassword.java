package logiche_bottoni_conferma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import gestore_db.AggiornamentiJooq;
import gui.CambiaPasswordFrame;
import gui.ErroreFrame;
import gui.LoginFrame;
import gui.PazientiFrame;
import logiche_bottoni.LogicaLogin;
import med_db.jooq.generated.tables.Personale;
import modelli.ModelloGestoreLogicaGenerale;

public class ConfermaCambiaPassword {
	private static String DB_REL_FILELOGIC = "../progetto_database/db/db.db3";
	private static String DB_URLLOGIC = "jdbc:sqlite:" + DB_REL_FILELOGIC;
	private PazientiFrame frameDeiPazienti;
	private ModelloGestoreLogicaGenerale modello;
	private CambiaPasswordFrame cambiaPw;
	private String vecchia_pw;
	private String nuova_pw;
	private String conferma_pw;

	public ConfermaCambiaPassword(CambiaPasswordFrame pw, PazientiFrame v2, ModelloGestoreLogicaGenerale m) {
		cambiaPw = pw;
		frameDeiPazienti = v2;
		modello = m;
		start();
	}

	/**
	 * Una volta premuto il pulsante "conferma" nel frame di inserimento, controlla le password e se tutto è corretto la cambia
	 */
	private void start() {
		cambiaPw.confermaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn = DriverManager.getConnection(DB_URLLOGIC);
					if (conn != null) {
						DSLContext contesto = DSL.using(conn, SQLDialect.SQLITE);
						vecchia_pw = new String(cambiaPw.vecchiaPasswordField.getPassword());
						int result = contesto.select().from(Personale.PERSONALE).where(Personale.PERSONALE.PASSWORD.eq(vecchia_pw).and(Personale.PERSONALE.CODICE.eq(modello.modelloGestoreUtente.getCodiceUtente()))).execute();
						if (result == 1) {
							nuova_pw = new String(cambiaPw.inserisciPasswordField.getPassword());
							conferma_pw = new String(cambiaPw.confermaNuovaPasswordField.getPassword());
							if (nuova_pw.equals(conferma_pw)) {
								AggiornamentiJooq.getIstanza().personale(modello.modelloGestoreUtente.getCodiceUtente(),nuova_pw);
								new ErroreFrame(cambiaPw.sfondoFrame, "Il cambiamento è stato eseguito con successo");
								cambiaPw.sfondoFrame.dispose();
								frameDeiPazienti.sfondoFrame.dispose();
								LoginFrame loginFrame = new LoginFrame();
								new LogicaLogin(loginFrame,modello);
							}
							else {
								new ErroreFrame(cambiaPw.sfondoFrame, "Le due password non coincidono");
								cambiaPw.inserisciPasswordField.setText("");
								cambiaPw.vecchiaPasswordField.setText("");
								cambiaPw.confermaNuovaPasswordField.setText("");							}
						}
						else {
							new ErroreFrame(cambiaPw.sfondoFrame, "La password inserita è errata");
							cambiaPw.inserisciPasswordField.setText("");
							cambiaPw.vecchiaPasswordField.setText("");
							cambiaPw.confermaNuovaPasswordField.setText("");
						}
					}
				} catch (SQLException ev) {
					System.out.println(ev.getMessage());
				}	
				
			}
		});
		
		cambiaPw.visibileCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cambiaPw.vecchiaPasswordField.getEchoChar();
            	if (cambiaPw.visibileCheckBox.isSelected()) {
                    cambiaPw.vecchiaPasswordField.setEchoChar((char) 0);
                    cambiaPw.inserisciPasswordField.setEchoChar((char) 0);
                    cambiaPw.confermaNuovaPasswordField.setEchoChar((char) 0);
                } else {
                    cambiaPw.vecchiaPasswordField.setEchoChar('\u2022');
                    cambiaPw.inserisciPasswordField.setEchoChar('\u2022');
                    cambiaPw.confermaNuovaPasswordField.setEchoChar('\u2022');
                }
            }
        });
		
		cambiaPw.sfondoFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                frameDeiPazienti.sfondoFrame.setEnabled(true);
                cambiaPw.sfondoFrame.dispose();
            }
        });
	}
}
