package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.formdev.flatlaf.FlatIntelliJLaf;

/**
 * Classe contenente il frame per gestire il caricamento dei documenti del paziente
 * contiene solo parte grafica, è resa utilizzabile dal progetto_logico
 * sono stati utilizzati java swing e WindowBuilder
 */
public class CaricaDocumentiFrame {
	
	private ImageIcon immagineImage = new ImageIcon("../progetto_gui/src/main/resources/carica_documenti.png");
	public JFrame sfondoFrame;
	public JPanel pdfPanel;
	public JPanel filesPanel;
	public JButton confermaButton;
	
	@SuppressWarnings("serial")
	public CaricaDocumentiFrame() {
		
		try {
			UIManager.setLookAndFeel(new FlatIntelliJLaf());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		sfondoFrame = new JFrame();
		sfondoFrame.setSize(530, 342);
		sfondoFrame.setTitle("<html><font color='white'>M.E.D Carica documenti</font></html>");
		sfondoFrame.getRootPane().setBackground(Stile.AZZURRO.getColore());
		sfondoFrame.getRootPane().setForeground(Color.WHITE);
		sfondoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		sfondoFrame.setLocationRelativeTo(null);
		sfondoFrame.setResizable(false);
		
		JPanel sfondoPanel = new JPanel();
		sfondoFrame.getContentPane().add(sfondoPanel);
		sfondoPanel.setLayout(null);
		
		JPanel caricaPanel = new JPanel();
		caricaPanel.setBounds(10, 10, 496, 284);
		caricaPanel.setBackground(Color.WHITE);
		caricaPanel.setLayout(null);
		sfondoPanel.add(caricaPanel);
		
		JLabel titoloLabel = new JLabel("Carica documenti");
		titoloLabel.setBounds(100, 30, 366, 48);
		titoloLabel.setForeground(Stile.BLU_SCURO.getColore());
		titoloLabel.setFont(Stile.TITOLO_FINESTRE.getFont());
		titoloLabel.setHorizontalAlignment(SwingConstants.LEFT);
		caricaPanel.add(titoloLabel);
		
		JLabel immagineLabel = new JLabel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(immagineImage.getImage(), 0 , 0, this.getWidth(), this.getHeight(), this);
			}
		};
		immagineLabel.setBounds(30, 30, 48, 48);
		caricaPanel.add(immagineLabel);
		
		JLabel pdfLabel = new JLabel("Documenti (trascinare i file nell'area qui sotto)");
		pdfLabel.setBounds(30, 96, 436, 30);
		pdfLabel.setForeground(Color.GRAY);
		pdfLabel.setFont(Stile.TESTO.getFont());
		caricaPanel.add(pdfLabel);
		
		pdfPanel = new JPanel();
        pdfPanel.setBounds(30, 129, 436, 103);
        pdfPanel.setBackground(Color.WHITE);
        pdfPanel.setLayout(new BorderLayout());
        caricaPanel.add(pdfPanel);
        
        filesPanel = new JPanel();
        filesPanel.setLayout(new BoxLayout(filesPanel, BoxLayout.Y_AXIS));
        filesPanel.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(filesPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        pdfPanel.add(scrollPane, BorderLayout.CENTER);
		
        confermaButton = new JButton("Conferma");
		confermaButton.setBounds(346, 243, 120, 30);
		confermaButton.setBackground(Stile.AZZURRO.getColore());
		confermaButton.setForeground(Color.WHITE);
		confermaButton.setFont(Stile.SOTTOTITOLO.getFont());
		confermaButton.setFocusPainted(false);
		caricaPanel.add(confermaButton);

		sfondoFrame.setVisible(true);
	}

}
