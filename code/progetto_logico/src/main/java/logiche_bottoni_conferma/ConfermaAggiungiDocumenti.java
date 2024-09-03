package logiche_bottoni_conferma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.ImageIcon;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import gestore_db.CreateDB;
import gui.CaricaDocumentiFrame;
import gui.PazientiFrame;
import logiche_bottoni.LogicaCaricaPdf;
import med_db.jooq.generated.tables.Degente;
import med_db.jooq.generated.tables.VisitaIntervento;
import modelli.ModelloGestoreLogicaGenerale;

public class ConfermaAggiungiDocumenti {

	private CaricaDocumentiFrame frame;
	private ModelloGestoreLogicaGenerale modello;
	private PazientiFrame frameDeiPazienti;
	private LogicaCaricaPdf carica;

	/** 
	 * Classe che prende i file nel drag and drop nel relativo frame e li inserisce nel database
	 * @param v1 riferimento al frame per l'inserimento pazienti
	 * @param v2 riferimento al frame principale
	 * @param m riferimento al modello
	 * @param c riferimento al frame del caricamento file
	 */
	public ConfermaAggiungiDocumenti(CaricaDocumentiFrame v1, PazientiFrame v2, ModelloGestoreLogicaGenerale m, LogicaCaricaPdf c) {
		frame = v1;
		frameDeiPazienti = v2;
		modello = m;
		carica = c;
		start();
	}

	/**
	 * Una volta premuto il pulsante "conferma" nel frame di inserimento, prende i file inseriti con drag and drop 
	 * Inoltre permette la chiusura della finestra, premendo la X
	 * Alla chiusura della finestra il frame principale viene riabilitato
	 */
	protected void start() {
		frame.confermaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modello.modelloGestorePaziente.deselezionaPaziente();
				frameDeiPazienti.pazienteImage = new ImageIcon("../progetto_gui/src/main/resources/fototessera_default.png");
				frameDeiPazienti.immagineLabel.repaint();
				frameDeiPazienti.updateStringaPaziente();
				String cartellaCountPaz = "count_" + modello.modelloGestorePaziente.getCount()+ "__data__" + LocalDate.now();
				
				if(modello.modelloGestorePaziente.getPosizione().equals("Visita Intervento")) {
					
					try {
						Connection conn = DriverManager.getConnection(CreateDB.DB_URL);
			            DSLContext contesto = DSL.using(conn, SQLDialect.SQLITE);

						String dataVisita = contesto.select(VisitaIntervento.VISITA_INTERVENTO.DATA_PRENOTAZIONE)
				                .from(Degente.DEGENTE, VisitaIntervento.VISITA_INTERVENTO)
				                .where(Degente.DEGENTE.POSIZIONE.eq("Visita Intervento")
				                    .and(Degente.DEGENTE.CODICE.eq(modello.modelloGestorePaziente.getCodice())
				                    .and(Degente.DEGENTE.COUNT.eq(modello.modelloGestorePaziente.getCount())
				                    .and(VisitaIntervento.VISITA_INTERVENTO.CODICE_DEGENTE.eq(Degente.DEGENTE.CODICE)
				                    .and(VisitaIntervento.VISITA_INTERVENTO.COUNT_DEGENTE.eq(Degente.DEGENTE.COUNT))))))
				                .limit(1)
				                .fetchOneInto(String.class);
						
						String cartellaCountVisita = "count_" + modello.modelloGestorePaziente.getCount()+ "__data__" + dataVisita;
						
						File sottoCartella = new File("./../../pazienti/id_" + modello.modelloGestorePaziente.getCodice() + "/dati_visite_e_interventi/" + cartellaCountVisita);
						if (!sottoCartella.exists()) {
							sottoCartella.mkdirs();
						}
						carica.destinazione = new File("./../../pazienti/id_" + modello.modelloGestorePaziente.getCodice() + "/dati_visite_e_interventi/" + cartellaCountVisita);
					
					} catch (SQLException e1) {
			            System.out.println(e1.getMessage());
			        }
					
				}else {
					File sottoCartella = new File("./../../pazienti/id_" + modello.modelloGestorePaziente.getCodice() + "/documenti_caricati_durante_la_degenza/" + cartellaCountPaz);
					if (!sottoCartella.exists()) {
						sottoCartella.mkdirs();
					}
					carica.destinazione = new File("./../../pazienti/id_" + modello.modelloGestorePaziente.getCodice() + "/documenti_caricati_durante_la_degenza/" + cartellaCountPaz);
				}
				carica.salvaConTrascinamento();
				frameDeiPazienti.sfondoFrame.setEnabled(true);
				frame.sfondoFrame.dispose();
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
