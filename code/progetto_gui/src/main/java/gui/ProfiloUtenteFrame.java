package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.formdev.flatlaf.FlatIntelliJLaf;
import modelli.ModelloGestoreLogicaGenerale;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

/**
 * Classe contenente il frame per visualizzare le info dell'utente
 * contiene solo parte grafica, è resa utilizzabile dal progetto_logico
 * sono stati utilizzati java swing e WindowBuilder
 */
public class ProfiloUtenteFrame {

	ImageIcon profiloImage = new ImageIcon("../progetto_gui/src/main/resources/profilo.png");
	private ImageIcon dipendenteImage = new ImageIcon("../progetto_gui/src/main/resources/fototessera_default.png");
	public JFrame sfondoFrame;
	public JButton logoutButton;
	private ModelloGestoreLogicaGenerale modello;
	private JLabel nomeUtenteLabel;
	private JLabel idUtenteLabel;
	private JLabel ruoloUtenteLabel;
	private JLabel cognomeUtenteLabel;
	private JLabel specializzazioneUtenteLabel;
	public JButton cambiaPassButton;
	private JLabel nomeStringLabel;
	private JLabel cognomeStringLabel;
	private JLabel idStringLabel;
	private Component ruoloStringLabel;
	private Component specializzazioneStringLabel;
	private JLabel fototesseraLabel;

	/**
	 * @param modello utilizzato aggiornate le stringhe con i valori contunuti nel progetto_model
	 */
	@SuppressWarnings("serial")
	public ProfiloUtenteFrame(ModelloGestoreLogicaGenerale modello) {
		
		this.modello = modello;
		
		try {
			UIManager.setLookAndFeel(new FlatIntelliJLaf());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		sfondoFrame = new JFrame();
		sfondoFrame.setSize(530, 368);
		sfondoFrame.setTitle("<html><font color='white'>M.E.D Profilo utente</font></html>");
		sfondoFrame.getRootPane().setBackground(Stile.AZZURRO.getColore());
		sfondoFrame.getRootPane().setForeground(Color.WHITE);
		sfondoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		sfondoFrame.setLocationRelativeTo(null);
		sfondoFrame.setResizable(false);

		JPanel sfondoPanel = new JPanel();
		sfondoFrame.getContentPane().add(sfondoPanel);
		sfondoPanel.setLayout(null);

		JPanel profiloPanel = new JPanel();
		profiloPanel.setBounds(10, 10, 496, 310);
		profiloPanel.setBackground(Color.WHITE);
		profiloPanel.setLayout(null);
		sfondoPanel.add(profiloPanel);

		JLabel titoloLabel = new JLabel("Profilo Utente");
		titoloLabel.setBounds(100, 30, 366, 48);
		titoloLabel.setForeground(Stile.BLU_SCURO.getColore());
		titoloLabel.setFont(Stile.TITOLO_FINESTRE.getFont());
		titoloLabel.setHorizontalAlignment(SwingConstants.LEFT);
		profiloPanel.add(titoloLabel);
		
		JLabel immagineLabel = new JLabel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(profiloImage.getImage(), 0 , 0, this.getWidth(), this.getHeight(), this);
			}
		};
		immagineLabel.setBounds(30, 30, 48, 48);
		profiloPanel.add(immagineLabel);
		
		fototesseraLabel = new JLabel(){
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(dipendenteImage.getImage(), 0 , 0, this.getWidth(), this.getHeight(), this);
			}
		};
		fototesseraLabel.setBounds(346, 96, 120, 120);
		profiloPanel.add(fototesseraLabel);
		
		nomeStringLabel = new JLabel("Nome: ");
		nomeStringLabel.setBounds(30, 96, 436, 30);
		nomeStringLabel.setForeground(Color.GRAY);
		nomeStringLabel.setFont(Stile.TESTO.getFont());
		profiloPanel.add(nomeStringLabel);
		
		nomeUtenteLabel = new JLabel("");
		nomeUtenteLabel.setBounds(150, 96, 436, 30);
		nomeUtenteLabel.setForeground(Color.GRAY);
		nomeUtenteLabel.setFont(Stile.TESTO_FINE.getFont());
		profiloPanel.add(nomeUtenteLabel);

		cognomeStringLabel = new JLabel("Cognome:");
		cognomeStringLabel.setForeground(Color.GRAY);
		cognomeStringLabel.setFont(Stile.TESTO.getFont());
		cognomeStringLabel.setBounds(30, 129, 436, 30);
		profiloPanel.add(cognomeStringLabel);
		
		cognomeUtenteLabel = new JLabel("");
		cognomeUtenteLabel.setForeground(Color.GRAY);
		cognomeUtenteLabel.setFont(Stile.TESTO_FINE.getFont());
		cognomeUtenteLabel.setBounds(150, 129, 436, 30);
		profiloPanel.add(cognomeUtenteLabel);
		
		idStringLabel = new JLabel("ID:");
		idStringLabel.setBounds(30, 162, 436, 30);
		idStringLabel.setForeground(Color.GRAY);
		idStringLabel.setFont(Stile.TESTO.getFont());
		profiloPanel.add(idStringLabel);
		
		idUtenteLabel = new JLabel("");
		idUtenteLabel.setBounds(150, 162, 436, 30);
		idUtenteLabel.setForeground(Color.GRAY);
		idUtenteLabel.setFont(Stile.TESTO_FINE.getFont());
		profiloPanel.add(idUtenteLabel);

		ruoloStringLabel = new JLabel("Ruolo:");
		ruoloStringLabel.setBounds(30, 195, 436, 30);
		ruoloStringLabel.setForeground(Color.GRAY);
		ruoloStringLabel.setFont(Stile.TESTO.getFont());
		profiloPanel.add(ruoloStringLabel);
		
		ruoloUtenteLabel = new JLabel("");
		ruoloUtenteLabel.setBounds(150, 195, 436, 30);
		ruoloUtenteLabel.setForeground(Color.GRAY);
		ruoloUtenteLabel.setFont(Stile.TESTO_FINE.getFont());
		profiloPanel.add(ruoloUtenteLabel);
		
		specializzazioneStringLabel = new JLabel("Specializzazione:");
		specializzazioneStringLabel.setForeground(Color.GRAY);
		specializzazioneStringLabel.setFont(Stile.TESTO.getFont());
		specializzazioneStringLabel.setBounds(30, 228, 436, 30);
		profiloPanel.add(specializzazioneStringLabel);
		
		specializzazioneUtenteLabel = new JLabel("");
		specializzazioneUtenteLabel.setForeground(Color.GRAY);
		specializzazioneUtenteLabel.setFont(Stile.TESTO_FINE.getFont());
		specializzazioneUtenteLabel.setBounds(150, 228, 436, 30);
		profiloPanel.add(specializzazioneUtenteLabel);
		
		cambiaPassButton = new JButton("Cambia password");
		cambiaPassButton.setBounds(30, 269, 306, 30);
		cambiaPassButton.setBackground(Stile.AZZURRO.getColore());
		cambiaPassButton.setForeground(Color.WHITE);
		cambiaPassButton.setFont(Stile.SOTTOTITOLO.getFont());
		cambiaPassButton.setFocusPainted(false);
		profiloPanel.add(cambiaPassButton);
		
		logoutButton = new JButton("Logout");
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		logoutButton.setBounds(346, 269, 120, 30);
		logoutButton.setBackground(Stile.AZZURRO.getColore());
		logoutButton.setForeground(Color.WHITE);
		logoutButton.setFont(Stile.SOTTOTITOLO.getFont());
		profiloPanel.add(logoutButton);		
		
		sfondoFrame.setVisible(true);
	}
	
	/**
	 * Prende dal modello i dati e riempie il frame
	 */
	public void riempi() {
		cognomeUtenteLabel.setText(modello.modelloGestoreUtente.getCognome());
		nomeUtenteLabel.setText(modello.modelloGestoreUtente.getNome());
		idUtenteLabel.setText(modello.modelloGestoreUtente.getCodiceUtente());
		ruoloUtenteLabel.setText(modello.modelloGestoreUtente.getMansioneUtente());
		specializzazioneUtenteLabel.setText(modello.modelloGestoreUtente.getSpecializzazione());
		
		String percorsoImmagine = "./../../personale/id_" + modello.modelloGestoreUtente.getCodiceUtente() +  "/Fototessera.png";
		File fileImmagine = new File(percorsoImmagine);
		if (fileImmagine.exists()) {
		    dipendenteImage = new ImageIcon(percorsoImmagine);
		} else {
			dipendenteImage = new ImageIcon("../progetto_gui/src/main/resources/fototessera_default.png");
		}
		
		fototesseraLabel.repaint();
	}
}
