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
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.formdev.flatlaf.FlatIntelliJLaf;

/**
 * Classe contenente il frame per gestire l'inserimento di informazioni aggiuntive sul paziente
 * contiene solo parte grafica, è resa utilizzabile dal progetto_logico
 * sono stati utilizzati java swing e WindowBuilder
 */
public class InserisciInformazioniFrame {
	private ImageIcon infoImage = new ImageIcon("../progetto_gui/src/main/resources/inserisci_info.png");
	public JFrame sfondoFrame;
	public JTextArea informazioniTextArea;
	public JButton confermaButton;
	
	@SuppressWarnings("serial")
	public InserisciInformazioniFrame() {
		
		try {
			UIManager.setLookAndFeel(new FlatIntelliJLaf());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		sfondoFrame = new JFrame();
		sfondoFrame.setSize(530, 540);
		sfondoFrame.setTitle("<html><font color='white'>M.E.D Inserisci informazioni paziente</font></html>");
		sfondoFrame.getRootPane().setBackground(Stile.AZZURRO.getColore());
		sfondoFrame.getRootPane().setForeground(Color.WHITE);
		sfondoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		sfondoFrame.setLocationRelativeTo(null);
		sfondoFrame.setResizable(false);
		
		JPanel sfondoPanel = new JPanel();
		sfondoFrame.getContentPane().add(sfondoPanel);
		sfondoPanel.setLayout(null);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBounds(10, 10, 496, 482);
		infoPanel.setBackground(Color.WHITE);
		infoPanel.setLayout(null);
		sfondoPanel.add(infoPanel);
		
		JLabel titoloLabel = new JLabel("Inserisci informazioni paziente");
		titoloLabel.setBounds(100, 30, 366, 48);
		titoloLabel.setForeground(Stile.BLU_SCURO.getColore());
		titoloLabel.setFont(Stile.TITOLO_FINESTRE.getFont());
		titoloLabel.setHorizontalAlignment(SwingConstants.LEFT);
		infoPanel.add(titoloLabel);
		
		JLabel immagineLabel = new JLabel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(infoImage.getImage(), 0 , 0, this.getWidth(), this.getHeight(), this);
			}
		};
		immagineLabel.setBounds(30, 30, 48, 48);
		infoPanel.add(immagineLabel);
		
		JLabel informazioniLabel = new JLabel("Informazioni (allergie, patologie, ecc...)");
		informazioniLabel.setBounds(30, 96, 436, 30);
		informazioniLabel.setForeground(Color.GRAY);
		informazioniLabel.setFont(Stile.TESTO.getFont());
		infoPanel.add(informazioniLabel);
		
		informazioniTextArea = new JTextArea();
		informazioniTextArea.setBounds(165, 129, 218, 30);
		informazioniTextArea.setLineWrap(true); 
        informazioniTextArea.setWrapStyleWord(true); 
		infoPanel.add(informazioniTextArea);
		
        JScrollPane scrollPane = new JScrollPane(informazioniTextArea);
		scrollPane.setBounds(30, 129, 436, 301);
		scrollPane.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
		infoPanel.add(scrollPane);
		
        confermaButton = new JButton("Conferma");
		confermaButton.setBounds(346, 441, 120, 30);
		confermaButton.setBackground(Stile.AZZURRO.getColore());
		confermaButton.setForeground(Color.WHITE);
		confermaButton.setFont(Stile.SOTTOTITOLO.getFont());
		infoPanel.add(confermaButton);
		
		sfondoFrame.setVisible(true);
	
	}
}
