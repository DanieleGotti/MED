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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.formdev.flatlaf.FlatIntelliJLaf;

/**
 * Classe contenente il frame per gestire la dimissione del paziente
 * contiene solo parte grafica, è resa utilizzabile dal progetto_logico
 * sono stati utilizzati java swing e WindowBuilder
 */
public class DimettiPazienteFrame {
	
	ImageIcon aggiungiImage = new ImageIcon("../progetto_gui/src/main/resources/dimetti_paziente.png");
	public JFrame sfondoFrame;
	public JTextField dataTextField;
	public JTextField oraTextField;
	public JTextField motivoTextField;
	public JButton confermaButton;
	public JTextArea pdfTextArea;
	public JTextField pdfPercorsoTextField;
	public JPanel pdfPanel;
	public JPanel filesPanel;

	@SuppressWarnings("serial")
	public DimettiPazienteFrame() {
		
		try {
			UIManager.setLookAndFeel(new FlatIntelliJLaf());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		sfondoFrame = new JFrame();
		sfondoFrame.setSize(530, 540);
		sfondoFrame.setTitle("<html><font color='white'>M.E.D Dimetti paziente</font></html>");
		sfondoFrame.getRootPane().setBackground(Stile.AZZURRO.getColore());
		sfondoFrame.getRootPane().setForeground(Color.WHITE);
		sfondoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		sfondoFrame.setLocationRelativeTo(null);
		sfondoFrame.setResizable(false);
	
		JPanel sfondoPanel = new JPanel();
		sfondoFrame.getContentPane().add(sfondoPanel);
		sfondoPanel.setLayout(null);
		
		JPanel dimettiPazientePanel = new JPanel();
		dimettiPazientePanel.setBounds(10, 10, 496, 482);
		dimettiPazientePanel.setBackground(Color.WHITE);
		dimettiPazientePanel.setLayout(null);
		sfondoPanel.add(dimettiPazientePanel);
		
		JLabel titoloLabel = new JLabel("Dimetti paziente");
		titoloLabel.setBounds(100, 30, 366, 48);
		titoloLabel.setForeground(Stile.BLU_SCURO.getColore());
		titoloLabel.setFont(Stile.TITOLO_FINESTRE.getFont());
		titoloLabel.setHorizontalAlignment(SwingConstants.LEFT);
		dimettiPazientePanel.add(titoloLabel);
		
		JLabel immagineLabel = new JLabel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(aggiungiImage.getImage(), 0 , 0, this.getWidth(), this.getHeight(), this);
			}
		};
		immagineLabel.setBounds(30, 30, 48, 48);
		dimettiPazientePanel.add(immagineLabel);
		
		JLabel dataLabel = new JLabel("Data dimissione (aaaa-mm-gg)");
		dataLabel.setBounds(30, 96, 436, 30);
		dataLabel.setForeground(Color.GRAY);
		dataLabel.setFont(Stile.TESTO.getFont());
		dimettiPazientePanel.add(dataLabel);
		
		dataTextField = new JTextField();
		dataTextField.setBounds(30, 129, 436, 30);
		dataTextField.setColumns(30);
		dimettiPazientePanel.add(dataTextField);
		
		JLabel oraLabel = new JLabel("Ora dimissione (oo:mm)");
		oraLabel.setBounds(30, 162, 436, 30);
		oraLabel.setForeground(Color.GRAY);
		oraLabel.setFont(Stile.TESTO.getFont());
		dimettiPazientePanel.add(oraLabel);
		
		oraTextField = new JTextField();
		oraTextField.setBounds(30, 195, 436, 30);
		oraTextField.setColumns(30);
		dimettiPazientePanel.add(oraTextField);
		
		JLabel motivoLabel = new JLabel("Motivo");
		motivoLabel.setBounds(30, 228, 436, 30);
		motivoLabel.setForeground(Color.GRAY);
		motivoLabel.setFont(Stile.TESTO.getFont());
		dimettiPazientePanel.add(motivoLabel);
		
		motivoTextField = new JTextField();
		motivoTextField.setBounds(30, 261, 436, 30);
		motivoTextField.setColumns(30);
		dimettiPazientePanel.add(motivoTextField);
		
		JLabel pdfLabel = new JLabel("Lettera di dimissione (trascinare i file nell'area qui sotto)");
		pdfLabel.setBounds(30, 294, 436, 30);
		pdfLabel.setForeground(Color.GRAY);
		pdfLabel.setFont(Stile.TESTO.getFont());
		dimettiPazientePanel.add(pdfLabel);
		
		pdfPanel = new JPanel();
        pdfPanel.setBounds(30, 327, 436, 103);
        pdfPanel.setBackground(Color.WHITE);
        pdfPanel.setLayout(new BorderLayout());
        dimettiPazientePanel.add(pdfPanel);
        
        filesPanel = new JPanel();
        filesPanel.setLayout(new BoxLayout(filesPanel, BoxLayout.Y_AXIS));
        filesPanel.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(filesPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        pdfPanel.add(scrollPane, BorderLayout.CENTER);
		
        confermaButton = new JButton("Conferma");
		confermaButton.setBounds(346, 441, 120, 30);
		confermaButton.setBackground(Stile.AZZURRO.getColore());
		confermaButton.setForeground(Color.WHITE);
		confermaButton.setFont(Stile.SOTTOTITOLO.getFont());
		dimettiPazientePanel.add(confermaButton);

		sfondoFrame.setVisible(true);
	}
	
}
