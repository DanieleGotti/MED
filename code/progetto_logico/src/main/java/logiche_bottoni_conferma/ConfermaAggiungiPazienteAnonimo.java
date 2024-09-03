package logiche_bottoni_conferma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.ImageIcon;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import gestore_db.CreateDB;
import gestore_db.InserimentoJooq;
import gui.InserisciAnonimoFrame;
import gui.PazientiFrame;
import logiche_bottoni.LogicaCaricaPdf;
import logiche_frame_sezioni_ospedaliere.LogicaDellaPosizionePazienteTabella;
import med_db.jooq.generated.tables.Degente;
import modelli.ModelloGestoreLogicaGenerale;

public class ConfermaAggiungiPazienteAnonimo {

	private InserisciAnonimoFrame frame;
	private ModelloGestoreLogicaGenerale modello;
	private PazientiFrame frameDeiPazienti;
	private LogicaDellaPosizionePazienteTabella TabellaProntoSoccorso;

	/** 
	 * Classe che prende i dati digitati nel relativo frame e li inserisce nel database
	 * @param v1 riferimento al frame per l'inserimento pazienti
	 * @param v2 riferimento al frame principale
	 * @param m riferimento al modello
	 */
	public ConfermaAggiungiPazienteAnonimo(InserisciAnonimoFrame v1, PazientiFrame v2, ModelloGestoreLogicaGenerale m) {
		frame = v1;
		frameDeiPazienti = v2;
		modello = m;
		TabellaProntoSoccorso = new LogicaDellaPosizionePazienteTabella(frameDeiPazienti,modello,"in Pronto Soccorso");
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
				Connection conn;
				try {
					conn = DriverManager.getConnection(CreateDB.DB_URL);
					DSLContext contesto = DSL.using(conn, SQLDialect.SQLITE);
						String codice = "Anonimo";
						String nome = "Anonimo";
						String cognome = "Anonimo";
						String cod = contesto
				                .select(Degente.DEGENTE.CODICE)
				                .from(Degente.DEGENTE)
				                .where(DSL.condition("CODICE GLOB '[0-9]*'"))  // Filtra solo i codici numerici usando GLOB
				                .orderBy(DSL.cast(Degente.DEGENTE.CODICE, Integer.class).desc())
				                .limit(1)
				                .fetchOneInto(String.class);

				if (cod == null) {
				    codice = "1";
				} else {
				    codice = String.valueOf(Integer.parseInt(cod) + 1);
				}
						Integer risultato = contesto.select(Degente.DEGENTE.COUNT).from(Degente.DEGENTE).where(Degente.DEGENTE.CODICE.eq(codice)).orderBy(Degente.DEGENTE.COUNT.desc()).limit(1).fetchOneInto(int.class);
							int count;
							if (risultato == null) {
								count = 0;
							}
							else {
								count = risultato;
							}
							String sesso;
							if (frame.sessoComboBox.getSelectedItem().equals("maschio")) {
								sesso = "M";
							}
							else {
								sesso = "F";
							}
							String urgenza = (String) frame.urgenzaComboBox.getSelectedItem();
							if (InserimentoJooq.getIstanza().degente(codice,count+1,nome,cognome,sesso,"Indefinito",0,LocalDate.now(),LocalTime.now().withNano(0),urgenza,"in Pronto Soccorso") == 1) {
								modello.modelloGestorePaziente.deselezionaPaziente();
								frameDeiPazienti.pazienteImage = new ImageIcon("../progetto_gui/src/main/resources/fototessera_default.png");
								frameDeiPazienti.immagineLabel.repaint();
								frameDeiPazienti.updateStringaPaziente();
								TabellaProntoSoccorso.update();
								frameDeiPazienti.updateViewTabella();
								File sottoCartella = new File("./../../pazienti/id_" + codice + "/dati_anagrafici/count_" + (count+1) + "__data__" + LocalDate.now());
								if (!sottoCartella.exists()) {
									sottoCartella.mkdirs();
								}
								
								File destinazione = new File("./../../pazienti/id_" + codice + "/dati_anagrafici/count_" + (count+1) + "__data__" + LocalDate.now() + "/Fototessera.png");
								LogicaCaricaPdf carica = new LogicaCaricaPdf("");
								carica.CopiaFileEsempio("../progetto_gui/src/main/resources/fototessera_default.png", destinazione.toString());
							
							}
							frameDeiPazienti.sfondoFrame.setEnabled(true);
							frame.sfondoFrame.dispose();
					}
				catch(SQLException e2) {
					System.out.println(e2.getMessage());
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
