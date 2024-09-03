package gui;

import java.awt.Color;
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
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;

/**
 * Classe contenente il frame per gestire la modifica della password utente
 * contiene solo parte grafica, è resa utilizzabile dal progetto_logico
 * sono stati utilizzati java swing e WindowBuilder
 */
public class CambiaPasswordFrame {

	ImageIcon passwordImage = new ImageIcon("../progetto_gui/src/main/resources/cambia_password.png");
	ImageIcon occhioImage = new ImageIcon("../progetto_gui/src/main/resources/occhio.png");
	public JFrame sfondoFrame;
	public JButton confermaButton;
	private JLabel vecchiaPassLabel;
	public JPasswordField vecchiaPasswordField;
	public JPasswordField inserisciPasswordField;
	public JPasswordField confermaNuovaPasswordField;
	public JCheckBox visibileCheckBox;

	/**
	 * @param modello utilizzato aggiornate le stringhe con i valori contunuti nel progetto_model
	 */
	@SuppressWarnings("serial")
	public CambiaPasswordFrame() {
		
		try {
			UIManager.setLookAndFeel(new FlatIntelliJLaf());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		sfondoFrame = new JFrame();
		sfondoFrame.setSize(530, 401);
		sfondoFrame.setTitle("<html><font color='white'>M.E.D Cambia password</font></html>");
		sfondoFrame.getRootPane().setBackground(Stile.AZZURRO.getColore());
		sfondoFrame.getRootPane().setForeground(Color.WHITE);
		sfondoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		sfondoFrame.setLocationRelativeTo(null);
		sfondoFrame.setResizable(false);

		JPanel sfondoPanel = new JPanel();
		sfondoFrame.getContentPane().add(sfondoPanel);
		sfondoPanel.setLayout(null);

		JPanel passwordPanel = new JPanel();
		passwordPanel.setBounds(10, 10, 496, 343);
		passwordPanel.setBackground(Color.WHITE);
		passwordPanel.setLayout(null);
		sfondoPanel.add(passwordPanel);

		JLabel titoloLabel = new JLabel("Cambia password");
		titoloLabel.setBounds(100, 30, 366, 48);
		titoloLabel.setForeground(Stile.BLU_SCURO.getColore());
		titoloLabel.setFont(Stile.TITOLO_FINESTRE.getFont());
		titoloLabel.setHorizontalAlignment(SwingConstants.LEFT);
		passwordPanel.add(titoloLabel);
		
		JLabel immagineLabel = new JLabel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(passwordImage.getImage(), 0 , 0, this.getWidth(), this.getHeight(), this);
			}
		};
		immagineLabel.setBounds(30, 30, 48, 48);
		passwordPanel.add(immagineLabel);
		
		vecchiaPassLabel = new JLabel("Inserisci vecchia password");
		vecchiaPassLabel.setBounds(30, 96, 436, 30);
		vecchiaPassLabel.setForeground(Color.GRAY);
		vecchiaPassLabel.setFont(Stile.TESTO.getFont());
		passwordPanel.add(vecchiaPassLabel);

		JLabel moduloLabel = new JLabel("Inserisci nuova password");
		moduloLabel.setBounds(30, 162, 436, 30);
		moduloLabel.setForeground(Color.GRAY);
		moduloLabel.setFont(Stile.TESTO.getFont());
		passwordPanel.add(moduloLabel);

		JLabel postoLabel = new JLabel("Conferma nuova password");
		postoLabel.setBounds(30, 228, 436, 30);
		postoLabel.setForeground(Color.GRAY);
		postoLabel.setFont(Stile.TESTO.getFont());
		passwordPanel.add(postoLabel);

		confermaButton = new JButton("Conferma");
		confermaButton.setBounds(346, 302, 120, 30);
		confermaButton.setBackground(Stile.AZZURRO.getColore());
		confermaButton.setForeground(Color.WHITE);
		confermaButton.setFont(Stile.SOTTOTITOLO.getFont());
		passwordPanel.add(confermaButton);
		
		vecchiaPasswordField = new JPasswordField();
		vecchiaPasswordField.setBounds(30, 131, 436, 30);
		passwordPanel.add(vecchiaPasswordField);
		
		inserisciPasswordField = new JPasswordField();
		inserisciPasswordField.setBounds(30, 195, 436, 30);
		passwordPanel.add(inserisciPasswordField);
		
		confermaNuovaPasswordField = new JPasswordField();
		confermaNuovaPasswordField.setBounds(30, 261, 436, 30);
		passwordPanel.add(confermaNuovaPasswordField);
		
		visibileCheckBox = new JCheckBox("");
		visibileCheckBox.setBounds(30, 302, 30, 30);
		visibileCheckBox.setBackground(Color.WHITE);
		passwordPanel.add(visibileCheckBox);
		
		JLabel visibileLabel = new JLabel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(occhioImage.getImage(), 0 , 0, this.getWidth(), this.getHeight(), this);
			}
		};
		visibileLabel.setBounds(60, 302, 30, 30);
		passwordPanel.add(visibileLabel);
		
		sfondoFrame.setVisible(true);

	}
}
