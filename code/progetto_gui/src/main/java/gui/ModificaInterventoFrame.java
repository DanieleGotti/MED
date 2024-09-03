package gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.formdev.flatlaf.FlatIntelliJLaf;

/**
 * Classe contenente il frame per gestire la modifica di prenotazioni di visite e interventi
 * contiene solo parte grafica, è resa utilizzabile dal progetto_logico
 * sono stati utilizzati java swing e WindowBuilder
 */
public class ModificaInterventoFrame {
	
	ImageIcon aggiungiImage = new ImageIcon("../progetto_gui/src/main/resources/modifica_visita.png");
	public JFrame sfondoFrame;
	public JTextField dataTextField;
	public JTextField oraTextField;
	public JButton confermaButton;
	public JTextArea pdfTextArea;
	public JPanel pdfPanel;
	public JPanel filesPanel;
	public JTextArea descrizioneTextArea;
	public JTextField medicoTextField;

	@SuppressWarnings("serial")
	public ModificaInterventoFrame() {
		
		try {
			UIManager.setLookAndFeel(new FlatIntelliJLaf());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		sfondoFrame = new JFrame();
		sfondoFrame.setSize(530, 535);
		sfondoFrame.setTitle("<html><font color='white'>M.E.D Modifica visita / intervento</font></html>");
		sfondoFrame.getRootPane().setBackground(Stile.AZZURRO.getColore());
		sfondoFrame.getRootPane().setForeground(Color.WHITE);
		sfondoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		sfondoFrame.setLocationRelativeTo(null);
		sfondoFrame.setResizable(false);
	
		JPanel sfondoPanel = new JPanel();
		sfondoFrame.getContentPane().add(sfondoPanel);
		sfondoPanel.setLayout(null);
		
		JPanel inserisciVisitaPanel = new JPanel();
		inserisciVisitaPanel.setBounds(10, 10, 496, 550);
		inserisciVisitaPanel.setBackground(Color.WHITE);
		inserisciVisitaPanel.setLayout(null);
		sfondoPanel.add(inserisciVisitaPanel);
		
		JLabel titoloLabel = new JLabel("Modifica visita / intervento");
		titoloLabel.setBounds(100, 30, 366, 48);
		titoloLabel.setForeground(Stile.BLU_SCURO.getColore());
		titoloLabel.setFont(Stile.TITOLO_FINESTRE.getFont());
		titoloLabel.setHorizontalAlignment(SwingConstants.LEFT);
		inserisciVisitaPanel.add(titoloLabel);
		
		JLabel immagineLabel = new JLabel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(aggiungiImage.getImage(), 0 , 0, this.getWidth(), this.getHeight(), this);
			}
		};
		immagineLabel.setBounds(30, 30, 48, 48);
		inserisciVisitaPanel.add(immagineLabel);
		
		JLabel dataLabel = new JLabel("Data prenotazione (aaaa-mm-gg)");
		dataLabel.setBounds(30, 96, 436, 30);
		dataLabel.setForeground(Color.GRAY);
		dataLabel.setFont(Stile.TESTO.getFont());
		inserisciVisitaPanel.add(dataLabel);
		
		dataTextField = new JTextField();
		dataTextField.setBounds(30, 129, 436, 30);
		dataTextField.setColumns(30);
		inserisciVisitaPanel.add(dataTextField);
		
		JLabel oraLabel = new JLabel("Ora prenotazione (oo:mm)");
		oraLabel.setBounds(30, 162, 436, 30);
		oraLabel.setForeground(Color.GRAY);
		oraLabel.setFont(Stile.TESTO.getFont());
		inserisciVisitaPanel.add(oraLabel);
		
		oraTextField = new JTextField();
		oraTextField.setBounds(30, 195, 436, 30);
		oraTextField.setColumns(30);
		inserisciVisitaPanel.add(oraTextField);
		
		JLabel medicoLabel = new JLabel("Codice medico");
		medicoLabel.setBounds(30, 228, 436, 30);
		medicoLabel.setForeground(Color.GRAY);
		medicoLabel.setFont(Stile.TESTO.getFont());
		inserisciVisitaPanel.add(medicoLabel);
		
		medicoTextField = new JTextField();
		medicoTextField.setBounds(30, 261, 436, 30);
		medicoTextField.setColumns(30);
		inserisciVisitaPanel.add(medicoTextField);
		
		JLabel descrizioneLabel = new JLabel("Descrizione");
		descrizioneLabel.setBounds(30, 294, 436, 30);
		descrizioneLabel.setForeground(Color.GRAY);
		descrizioneLabel.setFont(Stile.TESTO.getFont());
		inserisciVisitaPanel.add(descrizioneLabel);
		
		descrizioneTextArea = new JTextArea();
		descrizioneTextArea.setBounds(30, 327, 436, 98);
		descrizioneTextArea.setLineWrap(true); 
		descrizioneTextArea.setWrapStyleWord(true);
		inserisciVisitaPanel.add(descrizioneTextArea);
        
		JScrollPane descrizioneScrollPane = new JScrollPane(descrizioneTextArea);
		descrizioneScrollPane.setBounds(30, 327, 436, 98);
		descrizioneScrollPane.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
		inserisciVisitaPanel.add(descrizioneScrollPane);
		
        confermaButton = new JButton("Conferma");
		confermaButton.setBounds(346, 436, 120, 30);
		confermaButton.setBackground(Stile.AZZURRO.getColore());
		confermaButton.setForeground(Color.WHITE);
		confermaButton.setFont(Stile.SOTTOTITOLO.getFont());
		inserisciVisitaPanel.add(confermaButton);

		sfondoFrame.setVisible(true);
	}

}
