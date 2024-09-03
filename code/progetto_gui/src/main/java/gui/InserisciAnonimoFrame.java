package gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.formdev.flatlaf.FlatIntelliJLaf;

/**
 * Classe contenente il frame per gestire l'inserimento di pazienti anonimi
 * contiene solo parte grafica, è resa utilizzabile dal progetto_logico
 * sono stati utilizzati java swing e WindowBuilder
 */
public class InserisciAnonimoFrame {
	
	ImageIcon aggiungiImage = new ImageIcon("../progetto_gui/src/main/resources/inserisci_anonimo.png");
	public JFrame sfondoFrame;
	public JButton confermaButton;
	public JComboBox<String> sessoComboBox;
	public JComboBox<String> urgenzaComboBox;

	@SuppressWarnings("serial")
	public InserisciAnonimoFrame() {
		try {
			UIManager.setLookAndFeel(new FlatIntelliJLaf());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		sfondoFrame = new JFrame();
		sfondoFrame.setSize(530, 335);
		sfondoFrame.setTitle("<html><font color='white'>M.E.D Inserisci paziente anonimo</font></html>");
		sfondoFrame.getRootPane().setBackground(Stile.AZZURRO.getColore());
		sfondoFrame.getRootPane().setForeground(Color.WHITE);
		sfondoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		sfondoFrame.setLocationRelativeTo(null);
		sfondoFrame.setResizable(false);
	
		JPanel sfondoPanel = new JPanel();
		sfondoFrame.getContentPane().add(sfondoPanel);
		sfondoPanel.setLayout(null);
		
		JPanel aggiungiAnonimoPanel = new JPanel();
		aggiungiAnonimoPanel.setBounds(10, 10, 496, 277);
		aggiungiAnonimoPanel.setBackground(Color.WHITE);
		aggiungiAnonimoPanel.setLayout(null);
		sfondoPanel.add(aggiungiAnonimoPanel);
		
		JLabel titoloLabel = new JLabel("Inserisci paziente anonimo");
		titoloLabel.setBounds(100, 30, 366, 48);
		titoloLabel.setForeground(Stile.BLU_SCURO.getColore());
		titoloLabel.setFont(Stile.TITOLO_FINESTRE.getFont());
		titoloLabel.setHorizontalAlignment(SwingConstants.LEFT);
		aggiungiAnonimoPanel.add(titoloLabel);
		
		JLabel immagineLabel = new JLabel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(aggiungiImage.getImage(), 0 , 0, this.getWidth(), this.getHeight(), this);
			}
		};
		immagineLabel.setBounds(30, 30, 48, 48);
		aggiungiAnonimoPanel.add(immagineLabel);
		
		JLabel sessoLabel = new JLabel("Sesso");
		sessoLabel.setBounds(30, 96, 436, 30);
		sessoLabel.setForeground(Color.GRAY);
		sessoLabel.setFont(Stile.TESTO.getFont());
		aggiungiAnonimoPanel.add(sessoLabel);

		sessoComboBox = new JComboBox<String>();
		sessoComboBox.addItem("maschio");
		sessoComboBox.addItem("femmina");
		sessoComboBox.setBounds(30, 129, 436, 30);
		aggiungiAnonimoPanel.add(sessoComboBox);
		
		JLabel urgenzaLabel = new JLabel("Urgenza");
		urgenzaLabel.setBounds(30, 162, 436, 30);
		urgenzaLabel.setForeground(Color.GRAY);
		urgenzaLabel.setFont(Stile.TESTO.getFont());
		aggiungiAnonimoPanel.add(urgenzaLabel);
		
		urgenzaComboBox = new JComboBox<String>();
		urgenzaComboBox.addItem("verde");
		urgenzaComboBox.addItem("giallo");
		urgenzaComboBox.addItem("rosso");
		urgenzaComboBox.setBounds(30, 195, 436, 30);
		aggiungiAnonimoPanel.add(urgenzaComboBox);
		
        confermaButton = new JButton("Conferma");
		confermaButton.setBounds(346, 236, 120, 30);
		confermaButton.setBackground(Stile.AZZURRO.getColore());
		confermaButton.setForeground(Color.WHITE);
		confermaButton.setFont(Stile.SOTTOTITOLO.getFont());
		aggiungiAnonimoPanel.add(confermaButton);

		sfondoFrame.setVisible(true);
	
	}
}
