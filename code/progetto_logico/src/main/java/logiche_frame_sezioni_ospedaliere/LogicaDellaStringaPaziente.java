package logiche_frame_sezioni_ospedaliere;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import gui.PazientiFrame;
import med_db.jooq.generated.tables.Degente;
import med_db.jooq.generated.tables.Diariamed;
import modelli.ModelloGestoreLogicaGenerale;

public class LogicaDellaStringaPaziente extends LogicaFrame{

	int row;
	private String codice;
	private int count;
	private String nome;
	private String cognome;
	private String sesso;
	private String genere;
	private int eta;
	private String data;
	private String ora;
	private String urgenza;
	private String posizione;
	private String condizione;
	
	/**
	 * Classe che permette di visualizzare i dati principali del degente selezionato
	 * @param p riferimento al frame principale
	 * @param m riferimento al modulo generale
	 */
	public LogicaDellaStringaPaziente(PazientiFrame p, ModelloGestoreLogicaGenerale m) {
		super(p,m);
		updateStringaPaziente();
	}
	
	/**
	 * Ogni qualvolta viene selezionato un degente, i suoi dati sono raccolti dal database e posti nell'apposito spazio grafico
	 * Se esiste una diaria medica assegnata al degente, viene mostrato anche il motivo del ricovero del degente
	 */
	private void updateStringaPaziente() {
		// si registra al mouse nella tabella
		frameDeiPazienti.table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			 public void valueChanged(ListSelectionEvent e) {
				try {
					Connection conn = DriverManager.getConnection(DB_URLLOGIC);
					if (conn != null) {
						DSLContext contesto = DSL.using(conn, SQLDialect.SQLITE);
						row = frameDeiPazienti.table.getSelectedRow();
						if (row >= 0) {						
							nome = (String) frameDeiPazienti.table.getValueAt(row, 0);
							cognome = (String) frameDeiPazienti.table.getValueAt(row, 1);
							codice = (String) frameDeiPazienti.table.getValueAt(row, 2);
							count = (Integer) frameDeiPazienti.table.getValueAt(row, 3);
							posizione = contesto.select(Degente.DEGENTE.POSIZIONE).from(Degente.DEGENTE).where(Degente.DEGENTE.CODICE.eq(codice).and(Degente.DEGENTE.COUNT.eq(count))).fetchOneInto(String.class);
							
							if(posizione.equals("in Pronto Soccorso") || posizione.equals("in Attesa")) {
								urgenza = (String) frameDeiPazienti.table.getValueAt(row, 4);
								data = frameDeiPazienti.table.getValueAt(row, 5).toString();
								ora = frameDeiPazienti.table.getValueAt(row, 6).toString();
								eta = (Integer) frameDeiPazienti.table.getValueAt(row, 7);
								sesso = (String) frameDeiPazienti.table.getValueAt(row, 8);
								genere = (String) frameDeiPazienti.table.getValueAt(row, 9);
								if (contesto.select(Diariamed.DIARIAMED.MOTIVO).from(Degente.DEGENTE,Diariamed.DIARIAMED).where(Degente.DEGENTE.CODICE.eq(Diariamed.DIARIAMED.CODICE_DEGENTE).and(Degente.DEGENTE.COUNT.eq(Diariamed.DIARIAMED.COUNT_DEGENTE)).and(Degente.DEGENTE.CODICE.eq(codice).and(Degente.DEGENTE.COUNT.eq(count)))).execute() == 1){
							   		condizione = contesto.select(Diariamed.DIARIAMED.MOTIVO).from(Degente.DEGENTE,Diariamed.DIARIAMED).where(Degente.DEGENTE.CODICE.eq(Diariamed.DIARIAMED.CODICE_DEGENTE).and(Degente.DEGENTE.COUNT.eq(Diariamed.DIARIAMED.COUNT_DEGENTE)).and(Degente.DEGENTE.CODICE.eq(codice).and(Degente.DEGENTE.COUNT.eq(count)))).fetchOneInto(String.class);
							   	}
							   	else {
							   		condizione = "ancora da definirsi";
							   	}
								modello.modelloGestorePaziente.SelezionaPaziente(codice, count, cognome, nome, sesso, genere, eta, data, ora, urgenza, posizione, condizione);
							   	
							}
							else if(posizione.equals("in Reparto")) {
								urgenza = contesto.select(Degente.DEGENTE.URGENZA).from(Degente.DEGENTE).where(Degente.DEGENTE.CODICE.eq(codice).and(Degente.DEGENTE.COUNT.eq(count))).fetchOneInto(String.class);
								data = frameDeiPazienti.table.getValueAt(row, 4).toString();
								ora = frameDeiPazienti.table.getValueAt(row, 5).toString();
								eta = (Integer) frameDeiPazienti.table.getValueAt(row, 6);
								sesso = (String) frameDeiPazienti.table.getValueAt(row, 7);
								genere = (String) frameDeiPazienti.table.getValueAt(row, 8);
								if (contesto.select(Diariamed.DIARIAMED.MOTIVO).from(Degente.DEGENTE,Diariamed.DIARIAMED).where(Degente.DEGENTE.CODICE.eq(Diariamed.DIARIAMED.CODICE_DEGENTE).and(Degente.DEGENTE.COUNT.eq(Diariamed.DIARIAMED.COUNT_DEGENTE)).and(Degente.DEGENTE.CODICE.eq(codice).and(Degente.DEGENTE.COUNT.eq(count)))).execute() == 1){
							   		condizione = contesto.select(Diariamed.DIARIAMED.MOTIVO).from(Degente.DEGENTE,Diariamed.DIARIAMED).where(Degente.DEGENTE.CODICE.eq(Diariamed.DIARIAMED.CODICE_DEGENTE).and(Degente.DEGENTE.COUNT.eq(Diariamed.DIARIAMED.COUNT_DEGENTE)).and(Degente.DEGENTE.CODICE.eq(codice).and(Degente.DEGENTE.COUNT.eq(count)))).fetchOneInto(String.class);
							   	}
							   	else {
							   		condizione = "ancora da definirsi";
							   	}
								modello.modelloGestorePaziente.SelezionaPaziente(codice, count, cognome, nome, sesso, genere, eta, data, ora, urgenza, posizione, condizione);
							   	
							}
							else if(posizione.equals("Visita Intervento")) {
								urgenza = null;
								data = frameDeiPazienti.table.getValueAt(row, 6).toString();
								ora = frameDeiPazienti.table.getValueAt(row, 7).toString();
								eta = (Integer) frameDeiPazienti.table.getValueAt(row, 9);
								sesso = frameDeiPazienti.table.getValueAt(row, 10).toString();
								genere = frameDeiPazienti.table.getValueAt(row, 11).toString();
								condizione = frameDeiPazienti.table.getValueAt(row, 5).toString();
								modello.modelloGestorePaziente.SelezionaPazienteVisita(codice, count, cognome, nome, sesso, genere, eta, data, ora, urgenza, posizione, condizione);	
							}
							else if(posizione.equals("Dimesso")) {
								urgenza = null;
								data = frameDeiPazienti.table.getValueAt(row, 6).toString();
								ora = frameDeiPazienti.table.getValueAt(row, 7).toString();
								eta = 0;
								sesso = null;
								genere = null;
								condizione = frameDeiPazienti.table.getValueAt(row, 5).toString();
								modello.modelloGestorePaziente.SelezionaPazienteDimesso(codice, count, cognome, nome, sesso, genere, eta, data, ora, urgenza, posizione, condizione);
							}
						   	
						   	SwingUtilities.invokeLater(new Runnable() {
								@Override
									public void run() {
										frameDeiPazienti.updateStringaPaziente();
										
										Path cartellaPadre = Paths.get("./../../pazienti/id_" + modello.modelloGestorePaziente.getCodice() + "/dati_anagrafici/");
								        String stringaDiRicerca = "count_" + modello.modelloGestorePaziente.getCount();  

								        try {
								            String ultimaCartella = trovaUltimaCartella(cartellaPadre, stringaDiRicerca);
								            
								            if (ultimaCartella != null) {
								            	String percorso = ultimaCartella + "/Fototessera.png";
								            	File fileImmagine = new File(percorso);
												frameDeiPazienti.pazienteImage.getImage().flush();
												if (fileImmagine.exists()) {
												    frameDeiPazienti.pazienteImage = new ImageIcon(percorso);
												} else {
													frameDeiPazienti.pazienteImage = new ImageIcon("../progetto_gui/src/main/resources/fototessera_default.png");
												}										
												frameDeiPazienti.immagineLabel.repaint();
								            } else {
								                System.out.println("Non ci sono cartelle che corrispondono al criterio di ricerca.");
								            }
								        } catch (IOException e) {
								            e.printStackTrace();
								        }						              
									}
							});
						}
					}
				}
				catch (SQLException ev) {
					System.out.println(ev.getMessage());
				}	
			}
		});
	}
	
	/**
	 * 
	 */
	public static String trovaUltimaCartella(Path directoryPath, String stringaDiRicerca) throws IOException {
        if (!Files.isDirectory(directoryPath)) {
            throw new IllegalArgumentException("Il percorso specificato non è una cartella.");
        }

        //lista delle cartelle che corrispondono al criterio di ricerca
        List<Path> cartelleCorrispondenti = new ArrayList<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directoryPath)) {
            for (Path entry : stream) {
                // Verifica se il nome della cartella corrisponde al pattern desiderato
                
                if (Files.isDirectory(entry) && entry.getFileName().toString().matches(stringaDiRicerca + "__data__\\d{4}-\\d{2}-\\d{2}")) {
                    cartelleCorrispondenti.add(entry);
                }
            }
        }

        //ordinare le cartelle corrispondenti per nome
        Collections.sort(cartelleCorrispondenti);
        if (cartelleCorrispondenti.isEmpty()) {
            return null;
        }
        Path ultima = cartelleCorrispondenti.get(cartelleCorrispondenti.size() - 1);
        return ultima.toAbsolutePath().toString().replace("\\", "/");
    }
}
