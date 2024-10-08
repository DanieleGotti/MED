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
 * Classe contenente il frame per visualizzare lo storico del paziente
 * contiene solo parte grafica, è resa utilizzabile dal progetto_logico
 * sono stati utilizzati java swing e WindowBuilder
 */
public class VisualizzaStoricoFrame {
	
	private ImageIcon storicoImage = new ImageIcon("../progetto_gui/src/main/resources/storico.png");
	public JFrame sfondoFrame;
	public JTextArea storicoTextArea;
	private JLabel storicoLabel;
	private JLabel modificatoLabel;
	
	@SuppressWarnings("serial")
	public VisualizzaStoricoFrame() {
		
		try {
			UIManager.setLookAndFeel(new FlatIntelliJLaf());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		sfondoFrame = new JFrame();
		sfondoFrame.setSize(530, 518);
		sfondoFrame.setTitle("<html><font color='white'>M.E.D Visualizza storico</font></html>");
		sfondoFrame.getRootPane().setBackground(Stile.AZZURRO.getColore());
		sfondoFrame.getRootPane().setForeground(Color.WHITE);
		sfondoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		sfondoFrame.setLocationRelativeTo(null);
		sfondoFrame.setResizable(false);
		
		JPanel sfondoPanel = new JPanel();
		sfondoFrame.getContentPane().add(sfondoPanel);
		sfondoPanel.setLayout(null);
		
		JPanel storicoPanel = new JPanel();
		storicoPanel.setBounds(10, 10, 496, 460);
		storicoPanel.setBackground(Color.WHITE);
		storicoPanel.setLayout(null);
		sfondoPanel.add(storicoPanel);
		
		JLabel titoloLabel = new JLabel("Visualizza storico");
		titoloLabel.setBounds(100, 30, 366, 48);
		titoloLabel.setForeground(Stile.BLU_SCURO.getColore());
		titoloLabel.setFont(Stile.TITOLO_FINESTRE.getFont());
		titoloLabel.setHorizontalAlignment(SwingConstants.LEFT);
		storicoPanel.add(titoloLabel);
		
		JLabel immagineLabel = new JLabel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(storicoImage.getImage(), 0 , 0, this.getWidth(), this.getHeight(), this);
			}
		};
		immagineLabel.setBounds(30, 30, 48, 48);
		storicoPanel.add(immagineLabel);
		
		storicoLabel = new JLabel("Storico");
		storicoLabel.setBounds(30, 96, 436, 30);
		storicoLabel.setForeground(Color.GRAY);
		storicoLabel.setFont(Stile.TESTO.getFont());
		storicoPanel.add(storicoLabel);
		
		modificatoLabel = new JLabel("Ultima modifica:");
		modificatoLabel.setForeground(Color.GRAY);
		modificatoLabel.setFont(Stile.TESTO.getFont());
		modificatoLabel.setBounds(30, 129, 436, 30);
		storicoPanel.add(modificatoLabel);
		
		storicoTextArea = new JTextArea();
		storicoTextArea.setBounds(30, 129, 436, 301);
        storicoTextArea.setBackground(Color.WHITE);
		storicoTextArea.setLineWrap(true); 
        storicoTextArea.setWrapStyleWord(true); 
        storicoTextArea.setEditable(false);
		storicoPanel.add(storicoTextArea);
		
        JScrollPane scrollPane = new JScrollPane(storicoTextArea);
		scrollPane.setBounds(30, 170, 436, 268);
		scrollPane.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
		storicoPanel.add(scrollPane);

		sfondoFrame.setVisible(true);
	
	}
	
	/**
	 * @param persona per aggiornare storicoLabel con nome e cognome del paziente
	 */
	public void setPersonaView(String persona) {
		storicoLabel.setText("Storico di " + persona);
	}
}
