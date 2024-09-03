package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.formdev.flatlaf.FlatIntelliJLaf;

/**
 * Classe contenente il frame per gestire l'inserimento dei dati un nuovo paziente per prenotare una visita
 * contiene solo parte grafica, è resa utilizzabile dal progetto_logico
 * sono stati utilizzati java swing e WindowBuilder
 */
public class InserisciPazientePerVisitaFrame {
    
    ImageIcon aggiungiImage = new ImageIcon("../progetto_gui/src/main/resources/inserisci_paziente.png");
    public JFrame sfondoFrame;
    public JTextField codiceTextField;
    public JTextField nomeTextField;
    public JTextField cognomeTextField;
    public JComboBox<String> sessoComboBox;
    public JButton confermaButton;
    public JTextField genereTextField;
    public JTextField etaTextField;
    public JPanel pdfPanel;
    public JPanel filesPanel;
    
    @SuppressWarnings("serial")
    public InserisciPazientePerVisitaFrame() {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        
        sfondoFrame = new JFrame();
        sfondoFrame.setSize(530, 586);
        sfondoFrame.setTitle("<html><font color='white'>M.E.D Inserisci paziente per visita</font></html>");
        sfondoFrame.getRootPane().setBackground(Stile.AZZURRO.getColore());
        sfondoFrame.getRootPane().setForeground(Color.WHITE);
        sfondoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        sfondoFrame.setLocationRelativeTo(null);
        sfondoFrame.setResizable(false);
    
        JPanel sfondoPanel = new JPanel();
        sfondoFrame.getContentPane().add(sfondoPanel);
        sfondoPanel.setLayout(null);
        
        JPanel aggiungiPazientePanel = new JPanel();
        aggiungiPazientePanel.setBounds(10, 10, 492, 528);
        aggiungiPazientePanel.setBackground(Color.WHITE);
        aggiungiPazientePanel.setLayout(null);
        sfondoPanel.add(aggiungiPazientePanel);
        
        JLabel titoloLabel = new JLabel("Inserisci paziente per visita");
        titoloLabel.setBounds(100, 30, 366, 48);
        titoloLabel.setForeground(Stile.BLU_SCURO.getColore());
        titoloLabel.setFont(Stile.TITOLO_FINESTRE.getFont());
        titoloLabel.setHorizontalAlignment(SwingConstants.LEFT);
        aggiungiPazientePanel.add(titoloLabel);
        
        JLabel immagineLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(aggiungiImage.getImage(), 0 , 0, this.getWidth(), this.getHeight(), this);
            }
        };
        immagineLabel.setBounds(30, 30, 48, 48);
        aggiungiPazientePanel.add(immagineLabel);
        
        JPanel scrollContentPanel = new JPanel();
        scrollContentPanel.setLayout(null);
        scrollContentPanel.setBackground(Color.WHITE);
        scrollContentPanel.setPreferredSize(new Dimension(470, 466));

        int yPosition = 0;
        
        JLabel codiceLabel = new JLabel("Codice");
        codiceLabel.setBounds(30, yPosition, 436, 30);
        codiceLabel.setForeground(Color.GRAY);
        codiceLabel.setFont(Stile.TESTO.getFont());
        scrollContentPanel.add(codiceLabel);
        yPosition += 33;  
        
        codiceTextField = new JTextField();
        codiceTextField.setBounds(30, yPosition, 436, 30);
        codiceTextField.setColumns(30);
        scrollContentPanel.add(codiceTextField);
        yPosition += 33;
        
        JLabel nomeLabel = new JLabel("Nome");
        nomeLabel.setBounds(30, yPosition, 436, 30);
        nomeLabel.setForeground(Color.GRAY);
        nomeLabel.setFont(Stile.TESTO.getFont());
        scrollContentPanel.add(nomeLabel);
        yPosition += 33;
        
        nomeTextField = new JTextField();
        nomeTextField.setBounds(30, yPosition, 436, 30);
        nomeTextField.setColumns(30);
        scrollContentPanel.add(nomeTextField);
        yPosition += 33;
        
        JLabel cognomeLabel = new JLabel("Cognome");
        cognomeLabel.setBounds(30, yPosition, 436, 30);
        cognomeLabel.setForeground(Color.GRAY);
        cognomeLabel.setFont(Stile.TESTO.getFont());
        scrollContentPanel.add(cognomeLabel);
        yPosition += 33;
        
        cognomeTextField = new JTextField();
        cognomeTextField.setBounds(30, yPosition, 436, 30);
        cognomeTextField.setColumns(30);
        scrollContentPanel.add(cognomeTextField);
        yPosition += 33;
        
        JLabel sessoLabel = new JLabel("Sesso");
        sessoLabel.setBounds(30, yPosition, 436, 30);
        sessoLabel.setForeground(Color.GRAY);
        sessoLabel.setFont(Stile.TESTO.getFont());
        scrollContentPanel.add(sessoLabel);
        yPosition += 33;
        
        sessoComboBox = new JComboBox<String>();
        sessoComboBox.addItem("maschio");
        sessoComboBox.addItem("femmina");
        sessoComboBox.setBounds(30, yPosition, 436, 30);
        scrollContentPanel.add(sessoComboBox);
        yPosition += 33;
        
        JLabel genereLabel = new JLabel("Genere");
        genereLabel.setForeground(Color.GRAY);
        genereLabel.setFont(Stile.TESTO.getFont());
        genereLabel.setBounds(30, yPosition, 436, 30);
        scrollContentPanel.add(genereLabel);
        yPosition += 33;
        
        genereTextField = new JTextField();
        genereTextField.setColumns(30);
        genereTextField.setBounds(30, yPosition, 436, 30);
        scrollContentPanel.add(genereTextField);
        genereTextField.setText("Maschio");
        yPosition += 33;
        
        JLabel etaLabel = new JLabel("Età");
        etaLabel.setForeground(Color.GRAY);
        etaLabel.setFont(Stile.TESTO.getFont());
        etaLabel.setBounds(30, yPosition, 436, 30);
        scrollContentPanel.add(etaLabel);
        yPosition += 33;
        
        etaTextField = new JTextField();
        etaTextField.setColumns(30);
        etaTextField.setBounds(30, yPosition, 436, 30);
        scrollContentPanel.add(etaTextField);    
        yPosition += 33;
        
        JLabel pdfLabel = new JLabel("Fototessera (trascinare il file nell'area qui sotto)");
        pdfLabel.setBounds(30, yPosition, 436, 30);
        pdfLabel.setForeground(Color.GRAY);
        pdfLabel.setFont(Stile.TESTO.getFont());
        scrollContentPanel.add(pdfLabel);
        yPosition += 33;
        
        pdfPanel = new JPanel();
        pdfPanel.setBounds(30, yPosition, 436, 36);
        pdfPanel.setBackground(Color.WHITE);
        pdfPanel.setLayout(new BorderLayout());
        scrollContentPanel.add(pdfPanel);
        
        filesPanel = new JPanel();
        filesPanel.setLayout(new BoxLayout(filesPanel, BoxLayout.Y_AXIS));
        filesPanel.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(filesPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        pdfPanel.add(scrollPane, BorderLayout.CENTER);
        
        JScrollPane mainScrollPane = new JScrollPane(scrollContentPanel);
        mainScrollPane.setBounds(0, 96, 489, 383); 
        mainScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainScrollPane.setBorder(null);
        mainScrollPane.setBackground(Color.WHITE);
        aggiungiPazientePanel.add(mainScrollPane);
        
        confermaButton = new JButton("Conferma");
        confermaButton.setBounds(346, 487, 120, 30);
        confermaButton.setBackground(Stile.AZZURRO.getColore());
        confermaButton.setForeground(Color.WHITE);
        confermaButton.setFont(Stile.SOTTOTITOLO.getFont());
        aggiungiPazientePanel.add(confermaButton);

        sfondoFrame.setVisible(true);
    }
 
}
