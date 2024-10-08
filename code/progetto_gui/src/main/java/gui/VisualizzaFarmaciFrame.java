package gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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
 * Classe contenente il frame per visualizzare i farmaci da somministrare al paziente
 * contiene solo parte grafica, è resa utilizzabile dal progetto_logico
 * sono stati utilizzati java swing e WindowBuilder
 */
public class VisualizzaFarmaciFrame {
	
	private ImageIcon farmaciImage = new ImageIcon("../progetto_gui/src/main/resources/farmaci.png");
	public JFrame sfondoFrame;
	public JTextArea farmaciTextArea;
	private JLabel farmaciLabel;
	private JLabel modificatoLabel;
	
	@SuppressWarnings("serial")
	public VisualizzaFarmaciFrame() {
		
		try {
			UIManager.setLookAndFeel(new FlatIntelliJLaf());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		sfondoFrame = new JFrame();
		sfondoFrame.setSize(530, 518);
		sfondoFrame.setTitle("<html><font color='white'>M.E.D Visualizza farmaci</font></html>");
		sfondoFrame.getRootPane().setBackground(Stile.AZZURRO.getColore());
		sfondoFrame.getRootPane().setForeground(Color.WHITE);
		sfondoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		sfondoFrame.setLocationRelativeTo(null);
		sfondoFrame.setResizable(false);
		
		JPanel sfondoPanel = new JPanel();
		sfondoFrame.getContentPane().add(sfondoPanel);
		sfondoPanel.setLayout(null);
		
		JPanel farmaciPanel = new JPanel();
		farmaciPanel.setBounds(10, 10, 496, 460);
		farmaciPanel.setBackground(Color.WHITE);
		farmaciPanel.setLayout(null);
		sfondoPanel.add(farmaciPanel);
		
		JLabel titoloLabel = new JLabel("Visualizza farmaci");
		titoloLabel.setBounds(100, 30, 366, 48);
		titoloLabel.setForeground(Stile.BLU_SCURO.getColore());
		titoloLabel.setFont(Stile.TITOLO_FINESTRE.getFont());
		titoloLabel.setHorizontalAlignment(SwingConstants.LEFT);
		farmaciPanel.add(titoloLabel);
		
		JLabel immagineLabel = new JLabel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(farmaciImage.getImage(), 0 , 0, this.getWidth(), this.getHeight(), this);
			}
		};
		immagineLabel.setBounds(30, 30, 48, 48);
		farmaciPanel.add(immagineLabel);
		
		farmaciLabel = new JLabel("Farmaci");
		farmaciLabel.setBounds(30, 96, 436, 30);
		farmaciLabel.setForeground(Color.GRAY);
		farmaciLabel.setFont(Stile.TESTO.getFont());
		farmaciPanel.add(farmaciLabel);
		
		modificatoLabel = new JLabel("Ultima modifica:");
		modificatoLabel.setForeground(Color.GRAY);
		modificatoLabel.setFont(Stile.TESTO.getFont());
		modificatoLabel.setBounds(30, 129, 436, 30);
		farmaciPanel.add(modificatoLabel);
		
		farmaciTextArea = new JTextArea();
		farmaciTextArea.setBounds(30, 129, 436, 301);
		farmaciTextArea.setBackground(Color.WHITE);
		farmaciTextArea.setLineWrap(true); 
        farmaciTextArea.setWrapStyleWord(true); 
        farmaciTextArea.setEditable(false);
		farmaciPanel.add(farmaciTextArea);
		
        JScrollPane scrollPane = new JScrollPane(farmaciTextArea);
		scrollPane.setBounds(30, 170, 436, 268);
		scrollPane.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
		farmaciPanel.add(scrollPane);
		
		sfondoFrame.setVisible(true);
	}
	
	/**
	 * @param persona per aggiornare farmaciLabel con nome e cognome del paziente
	 */
	public void setPersonaView(String persona) {
		farmaciLabel.setText("Farmaci di " + persona);
	}
}
