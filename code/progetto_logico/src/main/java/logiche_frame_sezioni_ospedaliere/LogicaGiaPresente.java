package logiche_frame_sezioni_ospedaliere;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import gestore_db.CreateDB;
import gui.InserisciPazienteFrame;
import gui.InserisciPazientePerVisitaFrame;
import med_db.jooq.generated.tables.Degente;
import javax.swing.event.*;

public class LogicaGiaPresente {
	
	private InserisciPazientePerVisitaFrame frame;
	private InserisciPazienteFrame frame2;
	private Boolean blocca;
	
	/**
	 * Inizializza il frame e imposta la variabile `blocca` a true se il paziente è già presente nel db
	 */
	public LogicaGiaPresente(InserisciPazientePerVisitaFrame f) {
		frame = f;
		blocca = true;
		start();
	}
	
	/**
	 * Inizializza il frame e imposta la variabile `blocca` a false se il paziente non è presente nel db
	 */
	public LogicaGiaPresente(InserisciPazienteFrame f) {
		frame2 = f;
		blocca = false;
		start();
	}
	
	/**
	 * Metodo che aggiunge un listener al campo codiceTextField
	 * Se `blocca` è true, aggiunge il listener al campo codiceTextField del frame InserisciPazientePerVisitaFrame
	 * Altrimenti, aggiunge il listener al campo codiceTextField del frame InserisciPazienteFrame
	 * Il listener verifica le modifiche nel campo codice e chiama il metodo controllaCodice o controllaCodice2
	 */
	protected void start() {
		if(blocca) {
			frame.codiceTextField.getDocument().addDocumentListener(new DocumentListener() {
	            @Override
	            public void insertUpdate(DocumentEvent e) {
	                controllaCodice();
	            }

	            @Override
	            public void removeUpdate(DocumentEvent e) {
	                controllaCodice();
	            }

	            @Override
	            public void changedUpdate(DocumentEvent e) {
	                controllaCodice();
	            }
	        });
		}
		else {
			frame2.codiceTextField.getDocument().addDocumentListener(new DocumentListener() {
	            @Override
	            public void insertUpdate(DocumentEvent e) {
	                controllaCodice2();
	            }

	            @Override
	            public void removeUpdate(DocumentEvent e) {
	                controllaCodice2();
	            }

	            @Override
	            public void changedUpdate(DocumentEvent e) {
	                controllaCodice2();
	            }
	        });
		}
    }
    
	/**
	 * Metodo che controlla se un codice paziente è già presente nel database
	 * Se il codice è presente, i campi nome, cognome, età, genere e sesso vengono disabilitati e
	 * riempiti con i dati del paziente corrispondente
	 * Se il codice non è presente, i campi vengono abilitati per l'inserimento
	 */ 
	private void controllaCodice() {
        try {
            Connection conn = DriverManager.getConnection(CreateDB.DB_URL);
            DSLContext contesto = DSL.using(conn, SQLDialect.SQLITE);

            String codice = frame.codiceTextField.getText();

            String testPresenza = contesto.select(Degente.DEGENTE.CODICE)
                .from(Degente.DEGENTE)
                .where(Degente.DEGENTE.POSIZIONE.eq("in Reparto")
                    .or(Degente.DEGENTE.POSIZIONE.eq("in Attesa"))
                    .or(Degente.DEGENTE.POSIZIONE.eq("in Pronto Soccorso"))
                    .or(Degente.DEGENTE.POSIZIONE.eq("Visita Intervento"))
                    .and(Degente.DEGENTE.CODICE.eq(codice)))
                .limit(1)
                .fetchOneInto(String.class);

            if (testPresenza == null) {
                frame.nomeTextField.setEnabled(true);
                frame.cognomeTextField.setEnabled(true);
                frame.etaTextField.setEnabled(true);
                frame.genereTextField.setEnabled(true);
                frame.sessoComboBox.setEnabled(true);
            }
            else {
                frame.nomeTextField.setEnabled(false);
                frame.cognomeTextField.setEnabled(false);
                frame.etaTextField.setEnabled(false);
                frame.genereTextField.setEnabled(false);
                frame.sessoComboBox.setEnabled(false);
            	frame.nomeTextField.setText(contesto.select(Degente.DEGENTE.NOME)
                .from(Degente.DEGENTE)
                .where(Degente.DEGENTE.POSIZIONE.eq("in Reparto")
                    .or(Degente.DEGENTE.POSIZIONE.eq("in Attesa"))
                    .or(Degente.DEGENTE.POSIZIONE.eq("in Pronto Soccorso"))
                    .or(Degente.DEGENTE.POSIZIONE.eq("Visita Intervento"))
                    .and(Degente.DEGENTE.CODICE.eq(codice)))
                .orderBy(Degente.DEGENTE.COUNT.desc())
                .limit(1)
                .fetchOneInto(String.class));
            	frame.cognomeTextField.setText(contesto.select(Degente.DEGENTE.COGNOME)
                        .from(Degente.DEGENTE)
                        .where(Degente.DEGENTE.POSIZIONE.eq("in Reparto")
                            .or(Degente.DEGENTE.POSIZIONE.eq("in Attesa"))
                            .or(Degente.DEGENTE.POSIZIONE.eq("in Pronto Soccorso"))
                            .or(Degente.DEGENTE.POSIZIONE.eq("Visita Intervento"))
                            .and(Degente.DEGENTE.CODICE.eq(codice)))
                        .orderBy(Degente.DEGENTE.COUNT.desc())
                        .limit(1)
                        .fetchOneInto(String.class));
            	frame.etaTextField.setText(contesto.select(Degente.DEGENTE.ETA)
                        .from(Degente.DEGENTE)
                        .where(Degente.DEGENTE.POSIZIONE.eq("in Reparto")
                            .or(Degente.DEGENTE.POSIZIONE.eq("in Attesa"))
                            .or(Degente.DEGENTE.POSIZIONE.eq("in Pronto Soccorso"))
                            .or(Degente.DEGENTE.POSIZIONE.eq("Visita Intervento"))
                            .and(Degente.DEGENTE.CODICE.eq(codice)))
                        .orderBy(Degente.DEGENTE.COUNT.desc())
                        .limit(1)
                        .fetchOneInto(String.class));
            	frame.genereTextField.setText(
            		    contesto.select(Degente.DEGENTE.GENERE)
            		        .from(Degente.DEGENTE)
            		        .where(Degente.DEGENTE.POSIZIONE.eq("in Reparto")
            		            .or(Degente.DEGENTE.POSIZIONE.eq("in Attesa"))
            		            .or(Degente.DEGENTE.POSIZIONE.eq("in Pronto Soccorso"))
                                .or(Degente.DEGENTE.POSIZIONE.eq("Visita Intervento"))
            		            .and(Degente.DEGENTE.CODICE.eq(codice)))
            		        .orderBy(Degente.DEGENTE.COUNT.desc())
            		        .limit(1)
            		        .fetchOneInto(String.class)
            		);
            	if(contesto.select(Degente.DEGENTE.SESSO)
                        .from(Degente.DEGENTE)
                        .where(Degente.DEGENTE.POSIZIONE.eq("in Reparto")
                            .or(Degente.DEGENTE.POSIZIONE.eq("in Attesa"))
                            .or(Degente.DEGENTE.POSIZIONE.eq("in Pronto Soccorso"))
                            .or(Degente.DEGENTE.POSIZIONE.eq("Visita Intervento"))
                            .and(Degente.DEGENTE.CODICE.eq(codice)))
                        .limit(1)
                        .fetchOneInto(String.class).equals("M")) {
					frame.sessoComboBox.setSelectedItem("maschio");
				}
				else {
					frame.sessoComboBox.setSelectedItem("femmina");
				}
            }
        } catch (SQLException e2) {
            System.out.println(e2.getMessage());
        }
    }
	
	/**
	 * Metodo che controlla se un codice paziente è già presente nel database
	 * nel contesto del frame InserisciPazienteFrame
	 * A differenza del metodo controllaCodice, include anche la posizione
	 * "Dimesso" tra quelle da verificare
	 * Se il codice è presente, i campi nome, cognome, età, genere e sesso vengono 
	 * riempiti con i dati del paziente corrispondente
	 */
	private void controllaCodice2() {
        try {
            Connection conn = DriverManager.getConnection(CreateDB.DB_URL);
            DSLContext contesto = DSL.using(conn, SQLDialect.SQLITE);

            String codice = frame2.codiceTextField.getText();

            String testPresenza = contesto.select(Degente.DEGENTE.CODICE)
                .from(Degente.DEGENTE)
                .where(Degente.DEGENTE.POSIZIONE.eq("in Reparto")
                    .or(Degente.DEGENTE.POSIZIONE.eq("in Attesa"))
                    .or(Degente.DEGENTE.POSIZIONE.eq("in Pronto Soccorso"))
                    .or(Degente.DEGENTE.POSIZIONE.eq("Visita Intervento"))
                    .or(Degente.DEGENTE.POSIZIONE.eq("Dimesso"))
                    .and(Degente.DEGENTE.CODICE.eq(codice)))
                .limit(1)
                .fetchOneInto(String.class);

            if (testPresenza == null) {
            }
            else {
            	frame2.nomeTextField.setText(contesto.select(Degente.DEGENTE.NOME)
                .from(Degente.DEGENTE)
                .where(Degente.DEGENTE.POSIZIONE.eq("in Reparto")
                    .or(Degente.DEGENTE.POSIZIONE.eq("in Attesa"))
                    .or(Degente.DEGENTE.POSIZIONE.eq("in Pronto Soccorso"))
                    .or(Degente.DEGENTE.POSIZIONE.eq("Visita Intervento"))
                    .or(Degente.DEGENTE.POSIZIONE.eq("Dimesso"))
                    .and(Degente.DEGENTE.CODICE.eq(codice)))
                .orderBy(Degente.DEGENTE.COUNT.desc())
                .limit(1)
                .fetchOneInto(String.class));
            	frame2.cognomeTextField.setText(contesto.select(Degente.DEGENTE.COGNOME)
                        .from(Degente.DEGENTE)
                        .where(Degente.DEGENTE.POSIZIONE.eq("in Reparto")
                            .or(Degente.DEGENTE.POSIZIONE.eq("in Attesa"))
                            .or(Degente.DEGENTE.POSIZIONE.eq("in Pronto Soccorso"))
                            .or(Degente.DEGENTE.POSIZIONE.eq("Visita Intervento"))
                            .or(Degente.DEGENTE.POSIZIONE.eq("Dimesso"))
                            .and(Degente.DEGENTE.CODICE.eq(codice)))
                        .orderBy(Degente.DEGENTE.COUNT.desc())
                        .limit(1)
                        .fetchOneInto(String.class));
            	frame2.etaTextField.setText(contesto.select(Degente.DEGENTE.ETA)
                        .from(Degente.DEGENTE)
                        .where(Degente.DEGENTE.POSIZIONE.eq("in Reparto")
                            .or(Degente.DEGENTE.POSIZIONE.eq("in Attesa"))
                            .or(Degente.DEGENTE.POSIZIONE.eq("in Pronto Soccorso"))
                            .or(Degente.DEGENTE.POSIZIONE.eq("Visita Intervento"))
                            .or(Degente.DEGENTE.POSIZIONE.eq("Dimesso"))
                            .and(Degente.DEGENTE.CODICE.eq(codice)))
                        .orderBy(Degente.DEGENTE.COUNT.desc())
                        .limit(1)
                        .fetchOneInto(String.class));
            	frame2.genereTextField.setText(contesto.select(Degente.DEGENTE.GENERE)
                        .from(Degente.DEGENTE)
                        .where(Degente.DEGENTE.POSIZIONE.eq("in Reparto")
                            .or(Degente.DEGENTE.POSIZIONE.eq("in Attesa"))
                            .or(Degente.DEGENTE.POSIZIONE.eq("in Pronto Soccorso"))
                            .or(Degente.DEGENTE.POSIZIONE.eq("Visita Intervento"))
                            .or(Degente.DEGENTE.POSIZIONE.eq("Dimesso"))
                            .and(Degente.DEGENTE.CODICE.eq(codice)))
                        .orderBy(Degente.DEGENTE.COUNT.desc())
                        .limit(1)
                        .fetchOneInto(String.class));
            	if(contesto.select(Degente.DEGENTE.SESSO)
                        .from(Degente.DEGENTE)
                        .where(Degente.DEGENTE.POSIZIONE.eq("in Reparto")
                            .or(Degente.DEGENTE.POSIZIONE.eq("in Attesa"))
                            .or(Degente.DEGENTE.POSIZIONE.eq("in Pronto Soccorso"))
                            .or(Degente.DEGENTE.POSIZIONE.eq("Visita Intervento"))
                            .or(Degente.DEGENTE.POSIZIONE.eq("Dimesso"))
                            .and(Degente.DEGENTE.CODICE.eq(codice)))
                        .orderBy(Degente.DEGENTE.COUNT.desc())
                        .limit(1)
                        .fetchOneInto(String.class).equals("M")) {
					frame2.sessoComboBox.setSelectedItem("maschio");
				}
				else {
					frame2.sessoComboBox.setSelectedItem("femmina");
				}
            }
        } catch (SQLException e2) {
            System.out.println(e2.getMessage());
        }
    }
}