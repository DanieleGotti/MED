package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import com.formdev.flatlaf.FlatIntelliJLaf;
import modelli.ModelloGestoreLogicaGenerale;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JToggleButton;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import java.awt.BorderLayout;

/**
 * Classe contenente il frame principale, è diviso in tre pannelli principali:
 * 1. infoPanel: contenete qualche informazione sul paziente selezionato, profilo, e i 5 bottoni 
 * principali (prontoSoccorsoToggleButton, inCaricoToggleButton, repartoToggleButton, visiteInterventiToggleButton e dimessiToggleButton)
 * 2. sinistraPanel: contente il logo e i pulsanti a seconda della sezione in cui ci troviamo
 * (indicata dal bottone principale dei 5 selezionato)
 * 3. centroPanel: contenente la tabella con i pazienti a seconda della sezione in cui ci troviamo
 * e i filtri per facilitare la navigazione
 * Ognuno di questi può contenere altri panel, label, button, ecc ... per migliorare la visualizzazione
 * il frame contiene solo parte grafica, è resa utilizzabile dal progetto_logico;
 * sono stati utilizzati java swing e WindowBuilder
 */
@SuppressWarnings("serial")
public class PazientiFrame {
	
	private final ImageIcon ripristinaImage = new ImageIcon("../progetto_gui/src/main/resources/ripristina.png");
	private final ImageIcon cercaImage = new ImageIcon("../progetto_gui/src/main/resources/cerca.png");
	private final ImageIcon logoImage = new ImageIcon("../progetto_gui/src/main/resources/logo.png");
	public ImageIcon pazienteImage = new ImageIcon("../progetto_gui/src/main/resources/fototessera_default.png");
	private final int WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private final int HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	public JFrame sfondoFrame;
	public JToggleButton prontoSoccorsoToggleButton;
	public JToggleButton repartoToggleButton;
	public JToggleButton inCaricoToggleButton;
	public JToggleButton visiteInterventiToggleButton;
	public JToggleButton dimessiToggleButton;
	public JComboBox<String> urgenzaComboBox;
	public JTable table;
	public JPanel prontoSoccorsoBottoniPanel;
	public JPanel prendereCaricoBottoniPanel;
	public JPanel repartoBottoniPanel;
	public String posizioneAttuale;
	public JTextField cercaTextField;
	public JButton cercaButton;
	public JButton indietroButton;
	public JButton visualizzaInformazioniButton;
	public JButton visualizzaFarmaciButton;
	public JButton visualizzaStoricoButton;
	public JButton inserisciDiariaInfButton;
	public JButton modificaDiariaMedButton;
	public JButton visualizzaDiarieInfButton;
	public JButton inserisciRilevazioneButton;
	public JButton visualizzaRilevazioniButton;
	public JButton assegnaLettoButton;
	public JButton inserisciDiariaButton;
	public JButton inserisciPazienteButton1;
	public JButton spostaLettoButton;
	public JButton profiloButton;
	public JComboBox<String> repartoComboBox;
	public JLabel repartoLabel;
    private JLabel dataPazienteLabel;
    private JLabel motivoPazienteLabel;
	private JLabel pazienteLabel;
	private DefaultTableModel tableModel;
	private JLabel utenteLabel;
	private JLabel pazienteTitoloLabel;
	private ModelloGestoreLogicaGenerale modello;
    public boolean updating = false;
	public JPanel dimessiBottoniPanel;
	public JPanel visiteInterventiBottoniPanel;
	public JScrollPane repartoScrollPane;
	public JButton inserisciAnonimoButton;
	public JButton modificaPazienteButton1;
	public JButton apriCartellaButton1;
	public JButton prenotaInterventoButton;
	public JButton prenotaVisitaButton;
	public JButton modificaVisitaButton;
	public JButton modificaInterventoButton;
	public JButton caricaDocumentiButton1;
	public JButton modificaPazienteButton2;
	public JButton caricaDocumentiButton2;
	public JButton apriCartellaButton2;
	public JButton caricaDocumentiButton3;
	public JButton apriCartellaButton3;
	public JButton modificaPazienteButton3;
	public JButton caricaDocumentiButton4;
	public JButton apriCartellaButton4;
	public JButton modificaPazienteButton4;
	public JButton inserisciPazienteButton4;
	public JButton dimettiButton1;
	public JButton dimettiButton2;
	public JButton dimettiButton3;
	public JButton dimettiButton4;
	public JButton apriCartellaButton5;
	public JLabel immagineLabel;
	public JLabel urgenzaLabel;
	public JCheckBox mieiPazientiCheckBox;
	public JLabel tipoLabel;
	public JComboBox<String> tipoComboBox;
	
	/**
	 * @param modello utilizzato aggiornate le stringhe con i valori contunuti nel progetto_model;
	 */
	public PazientiFrame(ModelloGestoreLogicaGenerale modello) {
		
		this.modello = modello;
		
		try {
			UIManager.setLookAndFeel(new FlatIntelliJLaf());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		sfondoFrame = new JFrame();
		sfondoFrame.setSize(1280, 720);
		sfondoFrame.setTitle("<html><font color='white'>M.E.D Pazienti</font></html>");
		sfondoFrame.getRootPane().setBackground(Stile.AZZURRO.getColore());
		sfondoFrame.getRootPane().setForeground(Color.WHITE);
		sfondoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sfondoFrame.setLocationRelativeTo(null);
		sfondoFrame.setResizable(true);
		sfondoFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JPanel sfondoPanel = new JPanel();
		sfondoPanel.setLayout(null);
		sfondoFrame.getContentPane().add(sfondoPanel);
		
        JPanel infoPanel = new JPanel();
        infoPanel.setBounds(0, 0, WIDTH, (int)(HEIGHT * 0.25));
        infoPanel.setLayout(null);
        sfondoPanel.add(infoPanel);
        
        JPanel pazientePanel = new JPanel();
        pazientePanel.setBounds(0, 0, WIDTH, (int) (infoPanel.getHeight() * 0.8));
        pazientePanel.setBackground(Stile.BLU.getColore());
        pazientePanel.setLayout(null);
        infoPanel.add(pazientePanel);
        
        profiloButton = new JButton("Profilo");
        profiloButton.setBounds(pazientePanel.getWidth() - 20 - 100, 20, 100, 30);
        profiloButton.setBackground(Stile.BLU.getColore());
        profiloButton.setForeground(Color.WHITE);
        profiloButton.setFont(Stile.TESTO.getFont());
        profiloButton.setFocusPainted(false);
        pazientePanel.add(profiloButton);  
        
        immagineLabel = new JLabel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(pazienteImage.getImage(), 0 , 0, this.getWidth(), this.getHeight(), this);
			}
		};
		immagineLabel.setBounds(20, 27, pazientePanel.getHeight() - 44, pazientePanel.getHeight() - 44);
		pazientePanel.add(immagineLabel);
        
        pazienteTitoloLabel = new JLabel("Selezionare un paziente");
        pazienteTitoloLabel.setBounds(immagineLabel.getWidth() + 40, 0, (int) (pazientePanel.getWidth() - 20), (int) (pazientePanel.getHeight() * 0.4));
        pazienteTitoloLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        pazienteTitoloLabel.setForeground(Color.WHITE);
        pazienteTitoloLabel.setFont(Stile.TITOLO_FINE.getFont());
        pazientePanel.add(pazienteTitoloLabel);

        pazienteLabel = new JLabel();
        pazienteLabel.setVerticalAlignment(SwingConstants.TOP);
        pazienteLabel.setBounds(immagineLabel.getWidth() + 40, pazienteTitoloLabel.getHeight(), (int) (pazientePanel.getWidth() - 20), 30);
        pazienteLabel.setForeground(Color.WHITE);
        pazienteLabel.setFont(Stile.SOTTOTITOLO_FINE.getFont());
        pazientePanel.add(pazienteLabel);

        dataPazienteLabel = new JLabel("    Data arrivo in struttura: ...");
        dataPazienteLabel.setBounds(immagineLabel.getWidth() + 40, pazienteTitoloLabel.getHeight() + 10, (int) (pazientePanel.getWidth() - 40), 30);
        dataPazienteLabel.setBorder(BorderFactory.createMatteBorder(2, 2, 0, 2, Color.LIGHT_GRAY));
        dataPazienteLabel.setForeground(Color.WHITE);
        dataPazienteLabel.setFont(Stile.SOTTOTITOLO_FINE.getFont());
        pazientePanel.add(dataPazienteLabel);
       
        motivoPazienteLabel = new JLabel("    Motivo: ...");
        motivoPazienteLabel.setBounds(immagineLabel.getWidth() + 40, pazienteTitoloLabel.getHeight() + dataPazienteLabel.getHeight() + 10, dataPazienteLabel.getWidth(), 30);
        motivoPazienteLabel.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, Color.LIGHT_GRAY));
        motivoPazienteLabel.setForeground(Color.WHITE);
        motivoPazienteLabel.setFont(Stile.SOTTOTITOLO_FINE.getFont());
        pazientePanel.add(motivoPazienteLabel);
        
        JPanel menuPanel = new JPanel();
        menuPanel.setBounds((int) (WIDTH *  0.162), infoPanel.getHeight() - ((int) (infoPanel.getHeight() * 0.2)), (int) (WIDTH *  (1 - 0.155)), (int) (infoPanel.getHeight() * 0.2));
        menuPanel.setBackground(Color.WHITE);
        menuPanel.setLayout(null);
        infoPanel.add(menuPanel);
        
        prontoSoccorsoToggleButton = new JToggleButton("IN PRONTO SOCCORSO");
        prontoSoccorsoToggleButton.setBounds(0, 0, (int) (menuPanel.getWidth() / 5), menuPanel.getHeight());
        prontoSoccorsoToggleButton.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.LIGHT_GRAY)); 
        prontoSoccorsoToggleButton.setForeground(Stile.BLU_SCURO.getColore());
        prontoSoccorsoToggleButton.setFont(Stile.TESTO.getFont());
        menuPanel.add(prontoSoccorsoToggleButton);
        
        inCaricoToggleButton = new JToggleButton("DA PRENDERE IN CARICO");
        inCaricoToggleButton.setBounds(prontoSoccorsoToggleButton.getWidth(), 0, prontoSoccorsoToggleButton.getWidth(), menuPanel.getHeight());
        inCaricoToggleButton.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.LIGHT_GRAY)); 
        inCaricoToggleButton.setForeground(Stile.BLU_SCURO.getColore());
        inCaricoToggleButton.setFont(Stile.TESTO.getFont());
        menuPanel.add(inCaricoToggleButton);
        
        repartoToggleButton = new JToggleButton("IN REPARTO");
        repartoToggleButton.setBounds(prontoSoccorsoToggleButton.getWidth() * 2, 0, prontoSoccorsoToggleButton.getWidth(), menuPanel.getHeight());
        repartoToggleButton.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.LIGHT_GRAY)); 
        repartoToggleButton.setForeground(Stile.BLU_SCURO.getColore());
        repartoToggleButton.setFont(Stile.TESTO.getFont());
        menuPanel.add(repartoToggleButton);
        
        visiteInterventiToggleButton = new JToggleButton("VISITE / INTERVENTI");
        visiteInterventiToggleButton.setBounds(prontoSoccorsoToggleButton.getWidth() * 3, 0, prontoSoccorsoToggleButton.getWidth(), menuPanel.getHeight());
        visiteInterventiToggleButton.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.LIGHT_GRAY)); 
        visiteInterventiToggleButton.setForeground(Stile.BLU_SCURO.getColore());
        visiteInterventiToggleButton.setFont(Stile.TESTO.getFont());
        menuPanel.add(visiteInterventiToggleButton);
        
        dimessiToggleButton = new JToggleButton("DIMESSI");
        dimessiToggleButton.setBounds(prontoSoccorsoToggleButton.getWidth() * 4, 0, prontoSoccorsoToggleButton.getWidth() + 1, menuPanel.getHeight());
        dimessiToggleButton.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.LIGHT_GRAY)); 
        dimessiToggleButton.setForeground(Stile.BLU_SCURO.getColore());
        dimessiToggleButton.setFont(Stile.TESTO.getFont());
        menuPanel.add(dimessiToggleButton);
        
		JPanel spazioPanel = new JPanel();
        spazioPanel.setBounds(0, menuPanel.getY(), menuPanel.getX(), menuPanel.getHeight());
        spazioPanel.setBackground(Stile.BLU_SCURO.getColore());
        spazioPanel.setLayout(null);
        infoPanel.add(spazioPanel);
        
		JPanel sinistraPanel = new JPanel();
		sinistraPanel.setBounds(0, spazioPanel.getY() + spazioPanel.getHeight(), spazioPanel.getWidth(), HEIGHT - infoPanel.getHeight());
		sinistraPanel.setBackground(Stile.BLU_SCURO.getColore());
		sinistraPanel.setLayout(null);
		sfondoPanel.add(sinistraPanel);
		sinistraPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.red)); 
		
		JPanel centroPanel = new JPanel();
		centroPanel.setBounds(sinistraPanel.getWidth(), (int) (HEIGHT * 0.25), (int) (WIDTH - sinistraPanel.getWidth()), sinistraPanel.getHeight());
        centroPanel.setLayout(null);
		sfondoPanel.add(centroPanel);
        
		JPanel filtriPanel = new JPanel();
		filtriPanel.setBounds(0, 0, centroPanel.getWidth(), (int) (centroPanel.getHeight() * 0.1));
		filtriPanel.setBackground(Stile.AZZURRO.getColore());
        filtriPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.LIGHT_GRAY));
        filtriPanel.setLayout(null);
        centroPanel.add(filtriPanel);
		
        JPanel logoPanel = new JPanel();
        logoPanel.setBounds(20, 0, sinistraPanel.getWidth() - 40, filtriPanel.getHeight());
        logoPanel.setLayout(new BorderLayout(0, 0));
        logoPanel.setBackground(Stile.BLU_SCURO.getColore());
        sinistraPanel.add(logoPanel);
        
        JLabel logoLabel = new JLabel();
        logoLabel.setIcon(logoImage);
        logoPanel.add(logoLabel, BorderLayout.CENTER);
        
        JLabel titoloLabel = new JLabel("<html>Medical<br>Environment<br>Database</html>");
        titoloLabel.setBounds(20, logoPanel.getY() + logoPanel.getHeight() + 5, sinistraPanel.getWidth() - 20, 56);
        titoloLabel.setForeground(Color.WHITE);
        titoloLabel.setFont(Stile.SOTTOTITOLO.getFont());
        sinistraPanel.add(titoloLabel);
        
        utenteLabel = new JLabel();
        utenteLabel.setBounds(sinistraPanel.getWidth() + 20, menuPanel.getY(), WIDTH - sinistraPanel.getWidth() - menuPanel.getWidth() - 20, menuPanel.getHeight());
        utenteLabel.setForeground(Stile.BLU_SCURO.getColore());
        utenteLabel.setFont(Stile.TESTO_FINE.getFont());
        infoPanel.add(utenteLabel);
        
        //Pulsanti pronto soccorso
		
		prontoSoccorsoBottoniPanel = new JPanel();
		prontoSoccorsoBottoniPanel.setBounds(0, (int) (sinistraPanel.getHeight() * 0.32), sinistraPanel.getWidth(), (int) (sinistraPanel.getHeight() * 0.55));
		prontoSoccorsoBottoniPanel.setBackground(Stile.BLU_SCURO.getColore()); 
		prontoSoccorsoBottoniPanel.setLayout(null);
		prontoSoccorsoBottoniPanel.setVisible(true);
		sinistraPanel.add(prontoSoccorsoBottoniPanel);
		
		inserisciPazienteButton1 = new JButton("Inserisci nuovo paziente");
		inserisciPazienteButton1.setBounds(0, 0, sinistraPanel.getWidth(), 34);
		inserisciPazienteButton1.setBackground(Stile.BLU_SCURO.getColore());
		inserisciPazienteButton1.setForeground(Color.WHITE);
		inserisciPazienteButton1.setFont(Stile.TESTO.getFont());
		inserisciPazienteButton1.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Stile.BLU.getColore()));
		prontoSoccorsoBottoniPanel.add(inserisciPazienteButton1);
		
		inserisciAnonimoButton = new JButton("Inserisci paziente anonimo");
		inserisciAnonimoButton.setBounds(0, inserisciPazienteButton1.getHeight(), sinistraPanel.getWidth(), 34);
		inserisciAnonimoButton.setBackground(Stile.BLU_SCURO.getColore());
		inserisciAnonimoButton.setForeground(Color.WHITE);
		inserisciAnonimoButton.setFont(Stile.TESTO.getFont());
		inserisciAnonimoButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Stile.BLU.getColore()));
		prontoSoccorsoBottoniPanel.add(inserisciAnonimoButton);
		
		inserisciDiariaButton = new JButton("Inserisci prima diaria medica");
		inserisciDiariaButton.setBounds(0, inserisciPazienteButton1.getHeight() * 2, sinistraPanel.getWidth(), 34);
		inserisciDiariaButton.setBackground(Stile.BLU_SCURO.getColore());
		inserisciDiariaButton.setForeground(Color.WHITE);
		inserisciDiariaButton.setFont(Stile.TESTO.getFont());
		inserisciDiariaButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Stile.BLU.getColore()));
		prontoSoccorsoBottoniPanel.add(inserisciDiariaButton);
		
		modificaPazienteButton1 = new JButton("Modifica dati");
		modificaPazienteButton1.setBounds(0, inserisciPazienteButton1.getHeight() * 3, sinistraPanel.getWidth(), 34);
		modificaPazienteButton1.setBackground(Stile.BLU_SCURO.getColore());
		modificaPazienteButton1.setForeground(Color.WHITE);
		modificaPazienteButton1.setFont(Stile.TESTO.getFont());
		modificaPazienteButton1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Stile.BLU.getColore()));
		prontoSoccorsoBottoniPanel.add(modificaPazienteButton1);
		
		apriCartellaButton1 = new JButton("Apri cartella clinica");
		apriCartellaButton1.setBounds(0, inserisciPazienteButton1.getHeight() * 4, sinistraPanel.getWidth(), 34);
		apriCartellaButton1.setBackground(Stile.BLU_SCURO.getColore());
		apriCartellaButton1.setForeground(Color.WHITE);
		apriCartellaButton1.setFont(Stile.TESTO.getFont());
		apriCartellaButton1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Stile.BLU.getColore()));
        prontoSoccorsoBottoniPanel.add(apriCartellaButton1);
        
		caricaDocumentiButton1 = new JButton("Carica documenti");
		caricaDocumentiButton1.setBounds(0, inserisciPazienteButton1.getHeight() * 5, sinistraPanel.getWidth(), 34);
		caricaDocumentiButton1.setBackground(Stile.BLU_SCURO.getColore());
		caricaDocumentiButton1.setForeground(Color.WHITE);
		caricaDocumentiButton1.setFont(Stile.TESTO.getFont());
		caricaDocumentiButton1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Stile.BLU.getColore()));
        prontoSoccorsoBottoniPanel.add(caricaDocumentiButton1);
		
		dimettiButton1 = new JButton("Dimetti");
		dimettiButton1.setBounds(0, inserisciPazienteButton1.getHeight() * 6, sinistraPanel.getWidth(), 34);
		dimettiButton1.setBackground(Stile.BLU_SCURO.getColore());
		dimettiButton1.setForeground(Color.WHITE);
		dimettiButton1.setFont(Stile.TESTO.getFont());
        dimettiButton1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Stile.BLU.getColore()));
        prontoSoccorsoBottoniPanel.add(dimettiButton1);
		
		//Pulsanti da prendere in carico
		
		prendereCaricoBottoniPanel = new JPanel();
		prendereCaricoBottoniPanel.setBounds(0, (int) (sinistraPanel.getHeight() * 0.32), sinistraPanel.getWidth(), (int) (sinistraPanel.getHeight() * 0.55));
		prendereCaricoBottoniPanel.setBackground(Stile.BLU_SCURO.getColore()); 
		prendereCaricoBottoniPanel.setLayout(null);
		prendereCaricoBottoniPanel.setVisible(false);
		sinistraPanel.add(prendereCaricoBottoniPanel);
		
		assegnaLettoButton = new JButton("Assegna posto letto");
		assegnaLettoButton.setBounds(0, 0, sinistraPanel.getWidth(), 34);
		assegnaLettoButton.setBackground(Stile.BLU_SCURO.getColore());
		assegnaLettoButton.setForeground(Color.WHITE);
		assegnaLettoButton.setFont(Stile.TESTO.getFont());
        assegnaLettoButton.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Stile.BLU.getColore()));
		prendereCaricoBottoniPanel.add(assegnaLettoButton);	
		
		modificaPazienteButton2 = new JButton("Modifica dati");
		modificaPazienteButton2.setBounds(0, assegnaLettoButton.getHeight(), sinistraPanel.getWidth(), 34);
		modificaPazienteButton2.setBackground(Stile.BLU_SCURO.getColore());
		modificaPazienteButton2.setForeground(Color.WHITE);
		modificaPazienteButton2.setFont(Stile.TESTO.getFont());
		modificaPazienteButton2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Stile.BLU.getColore()));
		prendereCaricoBottoniPanel.add(modificaPazienteButton2);
		
		apriCartellaButton2 = new JButton("Apri cartella clinica");
		apriCartellaButton2.setBounds(0, assegnaLettoButton.getHeight() * 2, sinistraPanel.getWidth(), 34);
		apriCartellaButton2.setBackground(Stile.BLU_SCURO.getColore());
		apriCartellaButton2.setForeground(Color.WHITE);
		apriCartellaButton2.setFont(Stile.TESTO.getFont());
		apriCartellaButton2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Stile.BLU.getColore()));
		prendereCaricoBottoniPanel.add(apriCartellaButton2);
		
		caricaDocumentiButton2 = new JButton("Carica documenti");
		caricaDocumentiButton2.setBounds(0, assegnaLettoButton.getHeight() * 3, sinistraPanel.getWidth(), 34);
		caricaDocumentiButton2.setBackground(Stile.BLU_SCURO.getColore());
		caricaDocumentiButton2.setForeground(Color.WHITE);
		caricaDocumentiButton2.setFont(Stile.TESTO.getFont());
		caricaDocumentiButton2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Stile.BLU.getColore()));
		prendereCaricoBottoniPanel.add(caricaDocumentiButton2);
		
		dimettiButton2 = new JButton("Dimetti");
		dimettiButton2.setBounds(0, assegnaLettoButton.getHeight() * 4, sinistraPanel.getWidth(), 34);
		dimettiButton2.setBackground(Stile.BLU_SCURO.getColore());
		dimettiButton2.setForeground(Color.WHITE);
		dimettiButton2.setFont(Stile.TESTO.getFont());
        dimettiButton2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Stile.BLU.getColore()));
        prendereCaricoBottoniPanel.add(dimettiButton2);
		
		//Pulsanti reparto
		
		repartoBottoniPanel = new JPanel(new GridLayout(0, 1));
		repartoBottoniPanel.setBackground(Stile.BLU_SCURO.getColore()); 
		repartoBottoniPanel.setPreferredSize(new Dimension(sinistraPanel.getWidth(), 34 * 14));
		repartoBottoniPanel.setVisible(false);		
		
		modificaDiariaMedButton = new JButton("Modifica diaria medica");
		modificaDiariaMedButton.setBounds(0, 0, sinistraPanel.getWidth(), 34);
		modificaDiariaMedButton.setBackground(Stile.BLU_SCURO.getColore());
		modificaDiariaMedButton.setForeground(Color.WHITE);
		modificaDiariaMedButton.setFont(Stile.TESTO.getFont());
        modificaDiariaMedButton.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Stile.BLU.getColore()));
		repartoBottoniPanel.add(modificaDiariaMedButton);
		
		modificaPazienteButton3 = new JButton("Modifica dati");
		modificaPazienteButton3.setBounds(0, modificaDiariaMedButton.getHeight(), sinistraPanel.getWidth(), 34);
		modificaPazienteButton3.setBackground(Stile.BLU_SCURO.getColore());
		modificaPazienteButton3.setForeground(Color.WHITE);
		modificaPazienteButton3.setFont(Stile.TESTO.getFont());
		modificaPazienteButton3.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Stile.BLU.getColore()));
		repartoBottoniPanel.add(modificaPazienteButton3);
		
		inserisciDiariaInfButton = new JButton("Inserisci diaria infermieristica");
		inserisciDiariaInfButton.setBounds(0, modificaDiariaMedButton.getHeight()* 2, sinistraPanel.getWidth(), 34);
		inserisciDiariaInfButton.setBackground(Stile.BLU_SCURO.getColore());
		inserisciDiariaInfButton.setForeground(Color.WHITE);
		inserisciDiariaInfButton.setFont(Stile.TESTO.getFont());
        inserisciDiariaInfButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Stile.BLU.getColore()));
		repartoBottoniPanel.add(inserisciDiariaInfButton);
		
		inserisciRilevazioneButton = new JButton("Inserisci rilevazione");
		inserisciRilevazioneButton.setBounds(0, modificaDiariaMedButton.getHeight() * 3, sinistraPanel.getWidth(), 34);
		inserisciRilevazioneButton.setBackground(Stile.BLU_SCURO.getColore());
		inserisciRilevazioneButton.setForeground(Color.WHITE);
		inserisciRilevazioneButton.setFont(Stile.TESTO.getFont());
        inserisciRilevazioneButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Stile.BLU.getColore()));
		repartoBottoniPanel.add(inserisciRilevazioneButton);
		
		spostaLettoButton = new JButton("Riassegna posto letto");
		spostaLettoButton.setBounds(0, modificaDiariaMedButton.getHeight() * 4, sinistraPanel.getWidth(), 34);
		spostaLettoButton.setBackground(Stile.BLU_SCURO.getColore());
		spostaLettoButton.setForeground(Color.WHITE);
		spostaLettoButton.setFont(Stile.TESTO.getFont());
		spostaLettoButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Stile.BLU.getColore()));
		repartoBottoniPanel.add(spostaLettoButton);
		
		prenotaInterventoButton = new JButton("Prenota intervento");
		prenotaInterventoButton.setBounds(0, modificaDiariaMedButton.getHeight() * 5, sinistraPanel.getWidth(), 34);
		prenotaInterventoButton.setBackground(Stile.BLU_SCURO.getColore());
		prenotaInterventoButton.setForeground(Color.WHITE);
		prenotaInterventoButton.setFont(Stile.TESTO.getFont());
		prenotaInterventoButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Stile.BLU.getColore()));
		repartoBottoniPanel.add(prenotaInterventoButton);
		
		visualizzaDiarieInfButton = new JButton("Diarie infermieristiche");
		visualizzaDiarieInfButton.setBounds(0, modificaDiariaMedButton.getHeight() * 6, sinistraPanel.getWidth(), 34);
		visualizzaDiarieInfButton.setBackground(Stile.BLU_SCURO.getColore());
		visualizzaDiarieInfButton.setForeground(Color.WHITE);
		visualizzaDiarieInfButton.setFont(Stile.TESTO.getFont());
        visualizzaDiarieInfButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Stile.BLU.getColore()));
		repartoBottoniPanel.add(visualizzaDiarieInfButton);
		
		visualizzaRilevazioniButton = new JButton("Rilevazioni");
		visualizzaRilevazioniButton.setBounds(0, modificaDiariaMedButton.getHeight() * 7, sinistraPanel.getWidth(), 34);
		visualizzaRilevazioniButton.setBackground(Stile.BLU_SCURO.getColore());
		visualizzaRilevazioniButton.setForeground(Color.WHITE);
		visualizzaRilevazioniButton.setFont(Stile.TESTO.getFont());
        visualizzaRilevazioniButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Stile.BLU.getColore()));
		repartoBottoniPanel.add(visualizzaRilevazioniButton);
		
		visualizzaStoricoButton = new JButton("Storico");
		visualizzaStoricoButton.setBounds(0, modificaDiariaMedButton.getHeight() * 8, sinistraPanel.getWidth(), 34);
		visualizzaStoricoButton.setBackground(Stile.BLU_SCURO.getColore());
		visualizzaStoricoButton.setForeground(Color.WHITE);
		visualizzaStoricoButton.setFont(Stile.TESTO.getFont());
        visualizzaStoricoButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Stile.BLU.getColore()));
		repartoBottoniPanel.add(visualizzaStoricoButton);
		
		visualizzaFarmaciButton = new JButton("Farmaci");
		visualizzaFarmaciButton.setBounds(0, modificaDiariaMedButton.getHeight() * 9, sinistraPanel.getWidth(), 34);
		visualizzaFarmaciButton.setBackground(Stile.BLU_SCURO.getColore());
		visualizzaFarmaciButton.setForeground(Color.WHITE);
		visualizzaFarmaciButton.setFont(Stile.TESTO.getFont());
        visualizzaFarmaciButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Stile.BLU.getColore()));
		repartoBottoniPanel.add(visualizzaFarmaciButton);
		
		visualizzaInformazioniButton = new JButton("Informazioni");
		visualizzaInformazioniButton.setBounds(0, modificaDiariaMedButton.getHeight() * 10, sinistraPanel.getWidth(), 34);
		visualizzaInformazioniButton.setBackground(Stile.BLU_SCURO.getColore());
		visualizzaInformazioniButton.setForeground(Color.WHITE);
		visualizzaInformazioniButton.setFont(Stile.TESTO.getFont());
        visualizzaInformazioniButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Stile.BLU.getColore()));
		repartoBottoniPanel.add(visualizzaInformazioniButton);
		
		apriCartellaButton3 = new JButton("Apri cartella clinica");
		apriCartellaButton3.setBounds(0, modificaDiariaMedButton.getHeight() * 11, sinistraPanel.getWidth(), 34);
		apriCartellaButton3.setBackground(Stile.BLU_SCURO.getColore());
		apriCartellaButton3.setForeground(Color.WHITE);
		apriCartellaButton3.setFont(Stile.TESTO.getFont());
		apriCartellaButton3.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Stile.BLU.getColore()));
		repartoBottoniPanel.add(apriCartellaButton3);
		
		caricaDocumentiButton3 = new JButton("Carica documenti");
		caricaDocumentiButton3.setBounds(0, modificaDiariaMedButton.getHeight() * 12, sinistraPanel.getWidth(), 34);
		caricaDocumentiButton3.setBackground(Stile.BLU_SCURO.getColore());
		caricaDocumentiButton3.setForeground(Color.WHITE);
		caricaDocumentiButton3.setFont(Stile.TESTO.getFont());
		caricaDocumentiButton3.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Stile.BLU.getColore()));
        repartoBottoniPanel.add(caricaDocumentiButton3);
		
		dimettiButton3 = new JButton("Dimetti");
		dimettiButton3.setBounds(0, modificaDiariaMedButton.getHeight() * 13, sinistraPanel.getWidth(), 34);
		dimettiButton3.setBackground(Stile.BLU_SCURO.getColore());
		dimettiButton3.setForeground(Color.WHITE);
		dimettiButton3.setFont(Stile.TESTO.getFont());
        dimettiButton3.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Stile.BLU.getColore()));
		repartoBottoniPanel.add(dimettiButton3);
		
		repartoScrollPane = new JScrollPane(repartoBottoniPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		repartoScrollPane.setBounds(0, (int) (sinistraPanel.getHeight() * 0.32), sinistraPanel.getWidth() - 1, (int) (sinistraPanel.getHeight() * 0.55));
		repartoScrollPane.setBorder(BorderFactory.createEmptyBorder()); 
		repartoScrollPane.setViewportView(repartoBottoniPanel);
		sinistraPanel.add(repartoScrollPane);
		
		//Pulsanti visite/interventi

		visiteInterventiBottoniPanel = new JPanel();
		visiteInterventiBottoniPanel.setBounds(0, (int) (sinistraPanel.getHeight() * 0.32), sinistraPanel.getWidth(), (int) (sinistraPanel.getHeight() * 0.55));
		visiteInterventiBottoniPanel.setBackground(Stile.BLU_SCURO.getColore()); 
		visiteInterventiBottoniPanel.setLayout(null);
		visiteInterventiBottoniPanel.setVisible(false);
		sinistraPanel.add(visiteInterventiBottoniPanel);
		
		prenotaVisitaButton = new JButton("Prenota visita");
		prenotaVisitaButton.setBounds(0, 0, sinistraPanel.getWidth(), 34);
		prenotaVisitaButton.setBackground(Stile.BLU_SCURO.getColore());
		prenotaVisitaButton.setForeground(Color.WHITE);
		prenotaVisitaButton.setFont(Stile.TESTO.getFont());
		prenotaVisitaButton.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Stile.BLU.getColore()));
		visiteInterventiBottoniPanel.add(prenotaVisitaButton);
		
		modificaPazienteButton4 = new JButton("Modifica dati");
		modificaPazienteButton4.setBounds(0, prenotaVisitaButton.getHeight(), sinistraPanel.getWidth(), 34);
		modificaPazienteButton4.setBackground(Stile.BLU_SCURO.getColore());
		modificaPazienteButton4.setForeground(Color.WHITE);
		modificaPazienteButton4.setFont(Stile.TESTO.getFont());
		modificaPazienteButton4.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Stile.BLU.getColore()));
		visiteInterventiBottoniPanel.add(modificaPazienteButton4);
		
		modificaInterventoButton = new JButton("Modifica visita / intervento");
		modificaInterventoButton.setBounds(0, prenotaVisitaButton.getHeight() * 2, sinistraPanel.getWidth(), 34);
		modificaInterventoButton.setBackground(Stile.BLU_SCURO.getColore());
		modificaInterventoButton.setForeground(Color.WHITE);
		modificaInterventoButton.setFont(Stile.TESTO.getFont());
		modificaInterventoButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Stile.BLU.getColore()));
		visiteInterventiBottoniPanel.add(modificaInterventoButton);
		
		apriCartellaButton4 = new JButton("Apri cartella clinica");
		apriCartellaButton4.setBounds(0, prenotaVisitaButton.getHeight() * 3, sinistraPanel.getWidth(), 34);
		apriCartellaButton4.setBackground(Stile.BLU_SCURO.getColore());
		apriCartellaButton4.setForeground(Color.WHITE);
		apriCartellaButton4.setFont(Stile.TESTO.getFont());
		apriCartellaButton4.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Stile.BLU.getColore()));
		visiteInterventiBottoniPanel.add(apriCartellaButton4);
		
		caricaDocumentiButton4 = new JButton("Carica documenti");
		caricaDocumentiButton4.setBounds(0, prenotaVisitaButton.getHeight() * 4, sinistraPanel.getWidth(), 34);
		caricaDocumentiButton4.setBackground(Stile.BLU_SCURO.getColore());
		caricaDocumentiButton4.setForeground(Color.WHITE);
		caricaDocumentiButton4.setFont(Stile.TESTO.getFont());
		caricaDocumentiButton4.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Stile.BLU.getColore()));
		visiteInterventiBottoniPanel.add(caricaDocumentiButton4);
		
		dimettiButton4 = new JButton("Dimetti");
		dimettiButton4.setBounds(0, prenotaVisitaButton.getHeight() * 5, sinistraPanel.getWidth(), 34);
		dimettiButton4.setBackground(Stile.BLU_SCURO.getColore());
		dimettiButton4.setForeground(Color.WHITE);
		dimettiButton4.setFont(Stile.TESTO.getFont());
        dimettiButton4.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Stile.BLU.getColore()));
        visiteInterventiBottoniPanel.add(dimettiButton4);
		
		//Pulsanti dimessi
		
		dimessiBottoniPanel = new JPanel();
		dimessiBottoniPanel.setBounds(0, (int) (sinistraPanel.getHeight() * 0.32), sinistraPanel.getWidth(), (int) (sinistraPanel.getHeight() * 0.55));
		dimessiBottoniPanel.setBackground(Stile.BLU_SCURO.getColore()); 
		dimessiBottoniPanel.setLayout(null);
		dimessiBottoniPanel.setVisible(false);	
		sinistraPanel.add(dimessiBottoniPanel);
		
		apriCartellaButton5 = new JButton("Apri cartella clinica");
		apriCartellaButton5.setBounds(0, 0, sinistraPanel.getWidth(), 34);
		apriCartellaButton5.setBackground(Stile.BLU_SCURO.getColore());
		apriCartellaButton5.setForeground(Color.WHITE);
		apriCartellaButton5.setFont(Stile.TESTO.getFont());
		apriCartellaButton5.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Stile.BLU.getColore()));
		dimessiBottoniPanel.add(apriCartellaButton5);
		
		//Filtri
		
		JLabel cercaLabel = new JLabel("Cerca Nome e Cognome");
		cercaLabel.setBounds(20, 0, 130, filtriPanel.getHeight());
		cercaLabel.setForeground(Color.WHITE);
		filtriPanel.add(cercaLabel);
        
        cercaTextField = new JTextField();
		cercaTextField.setBounds(cercaLabel.getWidth() + 40, (cercaLabel.getHeight() - 24) / 2, 200, 24);
		filtriPanel.add(cercaTextField);
		
		cercaButton = new JButton();
		cercaButton.setBounds(cercaTextField.getX() + cercaTextField.getWidth(), (cercaLabel.getHeight() - 24) / 2, 24, 24);
		cercaButton.setBackground(Stile.AZZURRO.getColore());
		cercaButton.setIcon(cercaImage);
        cercaButton.setBorder(BorderFactory.createEmptyBorder());
		filtriPanel.add(cercaButton);
		
		urgenzaLabel = new JLabel("Urgenza");
		urgenzaLabel.setBounds(cercaButton.getX() + cercaButton.getWidth() + 30, 0, 50, filtriPanel.getHeight());
		urgenzaLabel.setForeground(Color.WHITE);
		filtriPanel.add(urgenzaLabel);
		
		urgenzaComboBox = new JComboBox<String>();
		urgenzaComboBox.setBounds(urgenzaLabel.getX() + urgenzaLabel.getWidth(), (cercaLabel.getHeight() - 24) / 2, cercaLabel.getWidth(), 24);
	    urgenzaComboBox.addItem(" ");
        urgenzaComboBox.addItem("Rosso");
        urgenzaComboBox.addItem("Giallo");
        urgenzaComboBox.addItem("Verde");
		filtriPanel.add(urgenzaComboBox);
		
		repartoLabel = new JLabel("Reparto");
		repartoLabel.setBounds(cercaButton.getX() + cercaButton.getWidth() + 30, 0, 50, filtriPanel.getHeight());
		repartoLabel.setForeground(Color.WHITE);
		repartoLabel.setVisible(false);
		filtriPanel.add(repartoLabel);
		
		repartoComboBox = new JComboBox<String>();
		repartoComboBox.setBounds(urgenzaLabel.getX() + urgenzaLabel.getWidth(), (cercaLabel.getHeight() - 24) / 2, cercaLabel.getWidth(), 24);
	    repartoComboBox.addItem(" ");
		for (String nomeReparto : modello.modelloGestoreLogistica.getNomiReparti()) {
			repartoComboBox.addItem(nomeReparto);
		}
		repartoComboBox.setVisible(false);
		filtriPanel.add(repartoComboBox);
		
		tipoLabel = new JLabel("Tipo");
		tipoLabel.setBounds(cercaButton.getX() + cercaButton.getWidth() + 30, 0, 50, filtriPanel.getHeight());
		tipoLabel.setForeground(Color.WHITE);
		tipoLabel.setVisible(false);
		filtriPanel.add(tipoLabel);
		
		tipoComboBox = new JComboBox<String>();
		tipoComboBox.setBounds(urgenzaLabel.getX() + urgenzaLabel.getWidth(), (cercaLabel.getHeight() - 24) / 2, cercaLabel.getWidth(), 24);
	    tipoComboBox.addItem(" ");
        tipoComboBox.addItem("Visite");
        tipoComboBox.addItem("Interventi");
		tipoComboBox.setVisible(false);
		filtriPanel.add(tipoComboBox);
		
		mieiPazientiCheckBox = new JCheckBox("I miei pazienti");
		mieiPazientiCheckBox.setBounds(tipoComboBox.getX() + tipoComboBox.getWidth() + 30, (cercaLabel.getHeight() - 24) / 2, cercaLabel.getWidth(), 24);
		mieiPazientiCheckBox.setForeground(Color.WHITE);
		mieiPazientiCheckBox.setVisible(false);
		filtriPanel.add(mieiPazientiCheckBox);

		indietroButton = new JButton();
		indietroButton.setBounds(filtriPanel.getWidth() - 36, (cercaLabel.getHeight() - 24) / 2, 24, 24);
		indietroButton.setBackground(Stile.AZZURRO.getColore());
		indietroButton.setIcon(ripristinaImage);
	    indietroButton.setBorder(BorderFactory.createEmptyBorder());
		filtriPanel.add(indietroButton);
		
        tableModel = new DefaultTableModel();
        
        table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setRowHeight(30);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(true);
        table.setSelectionBackground(Stile.AZZURRO_TRASP.getColore());
        
        for(int i = 0; i < tableModel.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(new TabellaRenderer());
        }
        
		JScrollPane scrollPanel = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPanel.setBounds(0, filtriPanel.getHeight(), centroPanel.getWidth(), (int) (centroPanel.getHeight() * 0.78));
        scrollPanel.setBorder(BorderFactory.createEmptyBorder());
        scrollPanel.setViewportView(table);
        centroPanel.add(scrollPanel);
        
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setPreferredSize(new Dimension(table.getColumnModel().getTotalColumnWidth(), 30));
        tableHeader.setBackground(Stile.BLU.getColore());
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setFont(Stile.TESTO.getFont());
        tableHeader.setReorderingAllowed(false);
       
        TableRowSorter<DefaultTableModel> ordineColonna = new TableRowSorter<>(tableModel);
        table.setRowSorter(ordineColonna);
       
		sfondoFrame.setVisible(true);
	}
	
	/**
	 * Aggiorna i valori nella tabella prendendoli dal progetto_model a seconda della posizione del paziente
	 */
	public synchronized  void updateViewTabella() {
			if (updating) {
                return;
            }
			updating = true;
			tableModel.setRowCount(0);
			tableModel.setColumnCount(0);
			if (posizioneAttuale.equals("in Pronto Soccorso") || posizioneAttuale.equals("in Attesa")) {
		        tableModel.addColumn("Nome");
		        tableModel.addColumn("Cognome");
		        tableModel.addColumn("Codice");
		        tableModel.addColumn("ID Ingresso");
		        tableModel.addColumn("Urgenza");
		        tableModel.addColumn("Data Arrivo");
		        tableModel.addColumn("Ora Arrivo");
		        tableModel.addColumn("Età");
		        tableModel.addColumn("Sesso");
		        tableModel.addColumn("Genere");
				tableModel.setColumnCount(10);
				
				for (int i = 0; i < modello.modelloGestoreTabella.getTableNomi().size(); i++) {
						tableModel.addRow(new Object[] {
			    			   modello.modelloGestoreTabella.getTableNomi().get(i), 
			    			   modello.modelloGestoreTabella.getTableCognomi().get(i), 
			    			   modello.modelloGestoreTabella.getTableCodice().get(i), 
			    			   modello.modelloGestoreTabella.getTableCount().get(i),
			    			   modello.modelloGestoreTabella.getTableUrgenza().get(i), 
			    			   modello.modelloGestoreTabella.getTableDateArrivo().get(i),
			    			   modello.modelloGestoreTabella.getTableOraArrivo().get(i).format(DateTimeFormatter.ofPattern("HH:mm")),
			    			   modello.modelloGestoreTabella.getTableEta().get(i), 
			    			   modello.modelloGestoreTabella.getTableSesso().get(i), 
			    			   modello.modelloGestoreTabella.getTableGenere().get(i)});
				}
				

				TableRowSorter<DefaultTableModel> ordineColonna = new TableRowSorter<>(tableModel);
				table.setRowSorter(ordineColonna);
				List<RowSorter.SortKey> sortKeys = new ArrayList<>();
				sortKeys.add(new RowSorter.SortKey(tableModel.findColumn("Data Arrivo"), SortOrder.DESCENDING));
				sortKeys.add(new RowSorter.SortKey(tableModel.findColumn("Ora Arrivo"), SortOrder.DESCENDING));
				ordineColonna.setSortKeys(sortKeys);
				ordineColonna.sort();
			}
			else if (posizioneAttuale.equals("in Reparto")){
		        tableModel.addColumn("Nome");
		        tableModel.addColumn("Cognome");
		        tableModel.addColumn("Codice");
		        tableModel.addColumn("ID Ingresso");
		        tableModel.addColumn("Data Arrivo");
		        tableModel.addColumn("Ora Arrivo");
		        tableModel.addColumn("Età");
		        tableModel.addColumn("Sesso");
		        tableModel.addColumn("Genere");
				tableModel.addColumn("Reparto");
		        tableModel.addColumn("Modulo");
		        tableModel.addColumn("Letto");
		        tableModel.setColumnCount(12);
		        
		        for (int i = 0; i < modello.modelloGestoreTabella.getTableNomi().size(); i++) {
			    	   tableModel.addRow(new Object[] {
			    			   modello.modelloGestoreTabella.getTableNomi().get(i), 
			    			   modello.modelloGestoreTabella.getTableCognomi().get(i), 
			    			   modello.modelloGestoreTabella.getTableCodice().get(i), 
			    			   modello.modelloGestoreTabella.getTableCount().get(i),
			    			   modello.modelloGestoreTabella.getTableDateArrivo().get(i),
			    			   modello.modelloGestoreTabella.getTableOraArrivo().get(i).format(DateTimeFormatter.ofPattern("HH:mm")),
			    			   modello.modelloGestoreTabella.getTableEta().get(i), 
			    			   modello.modelloGestoreTabella.getTableSesso().get(i), 
			    			   modello.modelloGestoreTabella.getTableGenere().get(i), 
			    			   modello.modelloGestoreTabella.getTableReparto().get(i), 
			    			   modello.modelloGestoreTabella.getTableModulo().get(i), 
			    			   modello.modelloGestoreTabella.getTableNumeroLetto().get(i)});
				}
		        
				TableRowSorter<DefaultTableModel> ordineColonna = new TableRowSorter<>(tableModel);
				table.setRowSorter(ordineColonna);
				List<RowSorter.SortKey> sortKeys = new ArrayList<>();
				sortKeys.add(new RowSorter.SortKey(tableModel.findColumn("Data Arrivo"), SortOrder.DESCENDING));
				sortKeys.add(new RowSorter.SortKey(tableModel.findColumn("Ora Arrivo"), SortOrder.DESCENDING));
				ordineColonna.setSortKeys(sortKeys);
				ordineColonna.sort();
			}
			else if (posizioneAttuale.equals("Visita Intervento")){
		        tableModel.addColumn("Nome");
		        tableModel.addColumn("Cognome");
		        tableModel.addColumn("Codice");
		        tableModel.addColumn("ID Ingresso");
		        tableModel.addColumn("Medico");
		        tableModel.addColumn("Motivo");
		        tableModel.addColumn("Data Prenotazione");
		        tableModel.addColumn("Ora Prenotazione");
		        tableModel.addColumn("Tipo");
		        tableModel.addColumn("Età");
		        tableModel.addColumn("Sesso");
		        tableModel.addColumn("Genere");
		        tableModel.setColumnCount(12);
		        
		        for (int i = 0; i < modello.modelloGestoreTabella.getTableNomi().size(); i++) {
			    	   tableModel.addRow(new Object[] {
			    			   modello.modelloGestoreTabella.getTableNomi().get(i), 
			    			   modello.modelloGestoreTabella.getTableCognomi().get(i), 
			    			   modello.modelloGestoreTabella.getTableCodice().get(i), 
			    			   modello.modelloGestoreTabella.getTableCount().get(i),
			    			   modello.modelloGestoreTabella.getTableMedicoVisita().get(i),
			    			   modello.modelloGestoreTabella.getTableMotivoVisita().get(i), 
			    			   modello.modelloGestoreTabella.getTableDataPrenotazione().get(i), 
			    			   modello.modelloGestoreTabella.getTableOraPrenotazione().get(i).format(DateTimeFormatter.ofPattern("HH:mm")),
			    			   modello.modelloGestoreTabella.getTableTipo().get(i),
					    	   modello.modelloGestoreTabella.getTableEta().get(i), 
			    			   modello.modelloGestoreTabella.getTableSesso().get(i), 
			    			   modello.modelloGestoreTabella.getTableGenere().get(i)}); 
				}
		        
				TableRowSorter<DefaultTableModel> ordineColonna = new TableRowSorter<>(tableModel);
				table.setRowSorter(ordineColonna);
				List<RowSorter.SortKey> sortKeys = new ArrayList<>();
				sortKeys.add(new RowSorter.SortKey(tableModel.findColumn("Data Prenotazione"), SortOrder.DESCENDING));
				sortKeys.add(new RowSorter.SortKey(tableModel.findColumn("Ora Prenotazione"), SortOrder.DESCENDING));
				ordineColonna.setSortKeys(sortKeys);
				ordineColonna.sort();
			}
			else if (posizioneAttuale.equals("Dimesso")){
		        tableModel.addColumn("Nome");
		        tableModel.addColumn("Cognome");
		        tableModel.addColumn("Codice");
		        tableModel.addColumn("ID Ingresso");
		        tableModel.addColumn("Medico");
		        tableModel.addColumn("Motivo");
		        tableModel.addColumn("Data Dimissione");
		        tableModel.addColumn("Ora Dimissione");
		        tableModel.addColumn("Età");
		        tableModel.addColumn("Sesso");
		        tableModel.addColumn("Genere");
		        tableModel.setColumnCount(11);
		        
		        for (int i = 0; i < modello.modelloGestoreTabella.getTableNomi().size(); i++) {
			    	   tableModel.addRow(new Object[] {
			    			   modello.modelloGestoreTabella.getTableNomi().get(i), 
			    			   modello.modelloGestoreTabella.getTableCognomi().get(i), 
			    			   modello.modelloGestoreTabella.getTableCodice().get(i), 
			    			   modello.modelloGestoreTabella.getTableCount().get(i),
			    			   modello.modelloGestoreTabella.getTableMedicoDimissione().get(i),
			    			   modello.modelloGestoreTabella.getTableMotivoDimissione().get(i), 
			    			   modello.modelloGestoreTabella.getTableDataDimissione().get(i), 
			    			   modello.modelloGestoreTabella.getTableOraDimissione().get(i).format(DateTimeFormatter.ofPattern("HH:mm")),
					    	   modello.modelloGestoreTabella.getTableEta().get(i), 
			    			   modello.modelloGestoreTabella.getTableSesso().get(i), 
			    			   modello.modelloGestoreTabella.getTableGenere().get(i)}); 
				}
		        
		        TableRowSorter<DefaultTableModel> ordineColonna = new TableRowSorter<>(tableModel);
				table.setRowSorter(ordineColonna);
				List<RowSorter.SortKey> sortKeys = new ArrayList<>();
				sortKeys.add(new RowSorter.SortKey(tableModel.findColumn("Data Dimissione"), SortOrder.DESCENDING));
				sortKeys.add(new RowSorter.SortKey(tableModel.findColumn("Ora Dimissione"), SortOrder.DESCENDING));
				ordineColonna.setSortKeys(sortKeys);
				ordineColonna.sort();
			}
			
	        for (int i = 0; i < tableModel.getColumnCount(); i++) {
	            table.getColumnModel().getColumn(i).setCellRenderer(new TabellaRenderer());
	        }
			updating = false;
			
	}
	
	/**
	 * Aggiorna i label con i dati del paziente selezionato
	 */
	public void updateStringaPaziente() {
		pazienteTitoloLabel.setText(modello.modelloGestoreVisualizzazioneDatiPaziente.getStringaDatiPaziente());
		dataPazienteLabel.setText(modello.modelloGestoreVisualizzazioneDatiPaziente.getStringaArrivoPaziente());
		motivoPazienteLabel.setText(modello.modelloGestoreVisualizzazioneDatiPaziente.getStringaCondizionePaziente());
	}
	
	/**
	 * Classe per gestire la grafica delle celle della tabella in base al valore contenuto
	 * in base al valore possono essere cambiate le immagini
	 */
    static class TabellaRenderer extends DefaultTableCellRenderer {

    	private final ImageIcon rossoImage = new ImageIcon("../progetto_gui/src/main/resources/rosso.png");
    	private final ImageIcon gialloImage = new ImageIcon("../progetto_gui/src/main/resources/giallo.png");
    	private final ImageIcon verdeImage = new ImageIcon("../progetto_gui/src/main/resources/verde.png");
    	private final ImageIcon maschioImage = new ImageIcon("../progetto_gui/src/main/resources/maschio.png");
    	private final ImageIcon femminaImage = new ImageIcon("../progetto_gui/src/main/resources/femmina.png");

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            
            this.setHorizontalAlignment(SwingConstants.CENTER);
            if (value instanceof String) {
                if ("rosso".equals(value)) {
                    setIcon(rossoImage);
                    setText("");
                } else if ("giallo".equals(value)) {
                    setIcon(gialloImage);
                    setText(""); 
                } else if ("verde".equals(value)) {
                    setIcon(verdeImage);
                    setText("");  
                } else if ("M".equals(value)) {
                    setIcon(maschioImage);
                    setText("");
                } else if ("F".equals(value)) {
                    setIcon(femminaImage);
                    setText("");
                }
                
            }
            return component;
        }
    }

}
