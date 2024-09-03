package logiche_bottoni;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import gui.ErroreFrame;
import gui.PdfPanel;

public class LogicaCaricaPdf {

    private List<PdfPanel> pdfPanels;
    public File destinazione;
    private List<Path> fileInseriti;
    private JPanel filesPanel;
    
    /**
	 * Classe che permette di caricare documenti nelle cartelle dei pazienti e personale
	 * @param percorso di destinazione del file
	 */
    public LogicaCaricaPdf(String percorso) {
        destinazione = new File(percorso);
        fileInseriti = new ArrayList<>();
        pdfPanels = new ArrayList<>();
    }

    /**
	 * Gestisce i file inseriti attraverso drag and drop 
	 * @param panel è il contenitore dell'area drag and drop
	 * @param filesPanel è il panel contenente il nome del file caricato e la X per togliere il file caricato
	 */
    @SuppressWarnings("serial")
	public void impostaDropTarget(JPanel panel, JPanel filesPanel) {
        this.filesPanel = filesPanel;
        panel.setDropTarget(new DropTarget() {
            @Override
            public synchronized void drop(DropTargetDropEvent event) {
                event.acceptDrop(DnDConstants.ACTION_COPY);
                try {
                    Transferable transferable = event.getTransferable();
                    DataFlavor[] flavors = transferable.getTransferDataFlavors();
                    for (DataFlavor flavor : flavors) {
                        if (flavor.isFlavorJavaFileListType()) {
                            @SuppressWarnings("unchecked")
                            java.util.List<File> fileList = (java.util.List<File>) transferable.getTransferData(flavor);
                            if (!fileList.isEmpty()) {
                                for (File file : fileList) {
                                    Path filePath = file.toPath();
                                    fileInseriti.add(filePath);
                                    PdfPanel pdfPanel = new PdfPanel(filesPanel, filePath);
                                    pdfPanels.add(pdfPanel);
                                    eliminaFile(pdfPanel);
                                }
                                panel.revalidate();
                                panel.repaint();
                            }
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Errore durante il drag and drop del file: " + e.getMessage());
                }
            }
        });
    }
    
    /**
	 * Gestisce solo un singolo file (per le fototessere) inserito attraverso drag and drop 
	 * @param panel è il contenitore dell'area drag and drop
	 * @param filesPanel è il panel contenente il nome del file caricato e la X per togliere il file caricato
	 */
    @SuppressWarnings("serial")
    public void impostaDropTargetSingleFile(JPanel panel, JPanel filesPanel, JFrame frame) {
        this.filesPanel = filesPanel;
        panel.setDropTarget(new DropTarget() {
            @Override
            public synchronized void drop(DropTargetDropEvent event) {
                event.acceptDrop(DnDConstants.ACTION_COPY);
                try {
                    Transferable transferable = event.getTransferable();
                    DataFlavor[] flavors = transferable.getTransferDataFlavors();
                    for (DataFlavor flavor : flavors) {
                        if (flavor.isFlavorJavaFileListType()) {
                            @SuppressWarnings("unchecked")
                            java.util.List<File> fileList = (java.util.List<File>) transferable.getTransferData(flavor);

                            //verifica se sono stati trascinati più di un file
                            if (fileList.size() > 1) {
                            	new ErroreFrame(frame, "Inserire solo un file.");
                                return;  
                            }
                            
                            //verifica se c'è già un file presente nell'area di testo
                            if (!fileInseriti.isEmpty()) {
                            	new ErroreFrame(frame, "È già presente un file. Eliminare il file esistente se si desidera aggiungerne uno nuovo.");
                                return;  
                            }

                            if (!fileList.isEmpty()) {
                                File file = fileList.get(0);
                                Path filePath = file.toPath();

                                fileInseriti.clear();
                                fileInseriti.add(filePath);
                                
                                PdfPanel pdfPanel = new PdfPanel(filesPanel, filePath);
                                pdfPanels.clear(); 
                                pdfPanels.add(pdfPanel);
                                eliminaFile(pdfPanel);
                                
                                panel.revalidate();
                                panel.repaint();
                            }
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Errore durante il drag and drop del file: " + e.getMessage());
                }
            }
        });
    }

    /**
	 * Elimina file inserito con drag and drop
	 * @param pdfPanel è il panel per la destione dei documenti
	 */
    private void eliminaFile(PdfPanel pdfPanel) {
        pdfPanel.removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileInseriti.remove(pdfPanel.getFilePath());
                filesPanel.remove(pdfPanel.filePanel);
                filesPanel.revalidate();
                filesPanel.repaint();
                pdfPanels.remove(pdfPanel);
            }
        });
    }

    /**
	 * Salva nella cartella il file caricato con drag and drop
	 * @param nuovo_nome con cui il file viene salvato nella cartella
	 * @param vuoto_default per le fototessere, imposta la foto default se impostato a true
	 */
    public void salvaConTrascinamento(String nuovo_nome, boolean vuoto_default) {
    	Path fotoDiDefault = Paths.get("./../../code/progetto_gui/src/main/resources/fototessera_default.png");
        if (!fileInseriti.isEmpty()) {
            for (Path fileInserito : fileInseriti) {
                
                //percorso di destinazione finale
                Path destPath = Paths.get(destinazione.toString(), nuovo_nome);
                
                try {
                    //copia il file con il nuovo nome
                    Files.copy(fileInserito, destPath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    System.err.println("Errore durante il salvataggio del file: " + e.getMessage());
                }
            }
        }
        else if(vuoto_default){
        	//utilizza la foto di default
            Path destPath = Paths.get(destinazione.toString(), "Fototessera.png");

            try {
                //copia la foto di default nella destinazione con il nome "Fototessera.png"
                Files.copy(fotoDiDefault, destPath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                System.err.println("Errore durante il salvataggio della foto di default: " + e.getMessage());
            }
        }
        fileInseriti.clear(); 
        filesPanel.removeAll(); 
        filesPanel.revalidate();
        filesPanel.repaint();
    }
    
    /**
	 * Permette di copiare un file da una cartella e incollarlo in un altra
	 * @param file_origine è il percorso di origine
	 * @param file_destinazione è il percorso di destinazione
	 */
    public void CopiaFileEsempio(String file_origine, String file_destinazione) {
        //percorso del file di origine
        Path fileDiOrigine = Paths.get(file_origine);
        
        //percorso completo del file di destinazione
        Path fileDiDestinazione = Paths.get(file_destinazione);
        
        try {
            //copia del file
            Files.copy(fileDiOrigine, fileDiDestinazione, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println("Errore durante la copia del file: " + e.getMessage());
        }
    }
    
    /**
	 * Salva nella cartella il file caricato con drag and drop
	 */
    public void salvaConTrascinamento() {
        if (!fileInseriti.isEmpty()) {
            for (Path fileInserito : fileInseriti) {
                Path destPath = Paths.get(destinazione.toString(), fileInserito.getFileName().toString());
                try {
                    Files.copy(fileInserito, destPath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    System.err.println("Errore durante il salvataggio del file: " + e.getMessage());
                }
            }
            fileInseriti.clear();
            filesPanel.removeAll(); 
            filesPanel.revalidate();
            filesPanel.repaint();
        } 
    }
    
    /**
	 * Rinomina cartella
	 * @param percorsoCorrente del file da modificare
	 * @param nuovoNome da dare al file
	 */
    public boolean rinominaCartella(String percorsoCorrente, String nuovoNome) {
        //percorso della cartella corrente
        Path cartellaCorrente = Paths.get(percorsoCorrente);
        
        //percorso della cartella con il nuovo nome
        Path nuovaCartella = cartellaCorrente.resolveSibling(nuovoNome);

        try {
            //rinomina la cartella
            Files.move(cartellaCorrente, nuovaCartella, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            System.err.println("Errore durante la rinomina della cartella: " + e.getMessage());
            return false;
        }
    }
    
}
