package gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.formdev.flatlaf.FlatIntelliJLaf;
import modelli.ModelloGestoreLogicaGenerale;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;

/**
 * Classe contenente il frame per gestire l'assegnazione del posto letto al paziente
 * contiene solo parte grafica, è resa utilizzabile dal progetto_logico
 * sono stati utilizzati java swing e WindowBuilder
 */
public class AssegnaPostoFrame {

	ImageIcon assegnaImage = new ImageIcon("../progetto_gui/src/main/resources/sposta_paziente.png");
	public JFrame sfondoFrame;
	public JComboBox<String> repartoComboBox;
	public JComboBox<String> moduloComboBox;
	public JComboBox<Integer> postoComboBox;
	public JButton confermaButton;
	private ModelloGestoreLogicaGenerale modello;
	private JLabel repartoLabel;

	/**
	 * @param modello utilizzato aggiornate le stringhe con i valori contunuti nel progetto_model
	 */
	@SuppressWarnings("serial")
	public AssegnaPostoFrame(ModelloGestoreLogicaGenerale modello) {
		
		this.modello = modello;
		
		try {
			UIManager.setLookAndFeel(new FlatIntelliJLaf());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		sfondoFrame = new JFrame();
		sfondoFrame.setSize(530, 401);
		sfondoFrame.setTitle("<html><font color='white'>M.E.D Assegna posto letto</font></html>");
		sfondoFrame.getRootPane().setBackground(Stile.AZZURRO.getColore());
		sfondoFrame.getRootPane().setForeground(Color.WHITE);
		sfondoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		sfondoFrame.setLocationRelativeTo(null);
		sfondoFrame.setResizable(false);

		JPanel sfondoPanel = new JPanel();
		sfondoFrame.getContentPane().add(sfondoPanel);
		sfondoPanel.setLayout(null);

		JPanel pazientePanel = new JPanel();
		pazientePanel.setBounds(10, 10, 496, 343);
		pazientePanel.setBackground(Color.WHITE);
		pazientePanel.setLayout(null);
		sfondoPanel.add(pazientePanel);

		JLabel assegnaPazienteLabel = new JLabel("Assegna posto letto");
		assegnaPazienteLabel.setBounds(100, 30, 366, 48);
		assegnaPazienteLabel.setForeground(Stile.BLU_SCURO.getColore());
		assegnaPazienteLabel.setFont(Stile.TITOLO_FINESTRE.getFont());
		assegnaPazienteLabel.setHorizontalAlignment(SwingConstants.LEFT);
		pazientePanel.add(assegnaPazienteLabel);

		JLabel immagineLabel = new JLabel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(assegnaImage.getImage(), 0 , 0, this.getWidth(), this.getHeight(), this);
			}
		};
		immagineLabel.setBounds(30, 30, 48, 48);
		pazientePanel.add(immagineLabel);
		
		repartoLabel = new JLabel("");
		repartoLabel.setBounds(30, 96, 436, 30);
		repartoLabel.setForeground(Color.GRAY);
		repartoLabel.setFont(Stile.TESTO.getFont());
		pazientePanel.add(repartoLabel);
		
		repartoComboBox = new JComboBox<String>();
		repartoComboBox.addItem(" ");
		for (String nomeReparto : modello.modelloGestoreLogistica.getNomiReparti()) {
			repartoComboBox.addItem(nomeReparto);
		}
		repartoComboBox.setBounds(30, 129, 436, 30);
		pazientePanel.add(repartoComboBox);

		JLabel moduloLabel = new JLabel("Modulo");
		moduloLabel.setBounds(30, 162, 436, 30);
		moduloLabel.setForeground(Color.GRAY);
		moduloLabel.setFont(Stile.TESTO.getFont());
		pazientePanel.add(moduloLabel);
		
		moduloComboBox = new JComboBox<String>();
		moduloComboBox.addItem(" ");
		moduloComboBox.setBounds(30, 195, 436, 30);
		pazientePanel.add(moduloComboBox);

		JLabel postoLabel = new JLabel("Posto Letto");
		postoLabel.setBounds(30, 228, 436, 30);
		postoLabel.setForeground(Color.GRAY);
		postoLabel.setFont(Stile.TESTO.getFont());
		pazientePanel.add(postoLabel);
		
		postoComboBox = new JComboBox<Integer>();
		postoComboBox.setBounds(30, 261, 436, 30);
		pazientePanel.add(postoComboBox);

		confermaButton = new JButton("Conferma");
		confermaButton.setBounds(346, 302, 120, 30);
		confermaButton.setBackground(Stile.AZZURRO.getColore());
		confermaButton.setForeground(Color.WHITE);
		confermaButton.setFont(Stile.SOTTOTITOLO.getFont());
		pazientePanel.add(confermaButton);
		
		sfondoFrame.setVisible(true);

	}
	
	/**
	 * Aggiorna la stringa contenente il reparto consigliato dal medico scritto nella diaria medica e salvato nel progetto_model
	 * @param repartoConsigliato reparto consigliato dal medico
	 */
	public void aggiornaRepartoView(String repartoConsigliato) {
		repartoLabel.setText(repartoConsigliato);
	}
	
	/**
	 * Aggiorna le stringhe nel menu a tendina moduloComboBox con i moduli disponibili nel reparto selezionato, salvati nel progetto_model
	 */
	public void aggiornaModuliRepartoView() {
		moduloComboBox.removeAllItems();
		postoComboBox.removeAllItems();
		moduloComboBox.addItem(" ");
		for (String nomeModulo : modello.modelloGestoreLogistica.getNomiModuli()) {
			moduloComboBox.addItem(nomeModulo);
		}
	}
	
	/**
	 * Aggiorna le stringhe nel menu a tendina postoJComboBox con i posti letto disponibili nel modulo selezionato, salvati nel progetto_model
	 */
	public void aggiornaLettiRepartoView() {
		postoComboBox.removeAllItems();
		postoComboBox.addItem(null);
		for (Integer numeroLetto : modello.modelloGestoreLogistica.getNumeroLettiDisponibili()) {
			postoComboBox.addItem(numeroLetto);
		}
	}
	
}