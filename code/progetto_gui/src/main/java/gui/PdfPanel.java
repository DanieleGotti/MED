package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.nio.file.Path;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Classe contenente il panel per la gestione del drag and drop dei file del paziente
 * contiene solo parte grafica, è resa utilizzabile dal progetto_logico
 * sono stati utilizzati java swing e WindowBuilder
 */
public class PdfPanel {

    public JPanel filePanel;
    public JButton removeButton;
    private Path filePath;

    @SuppressWarnings("serial")
    public PdfPanel(JPanel filesPanel, Path filePath) {
        this.filePath = filePath; // Memorizza il percorso del file

        final ImageIcon deleteImage = new ImageIcon("../progetto_gui/src/main/resources/x.png");

        filePanel = new JPanel(new BorderLayout());
        filePanel.setMaximumSize(new Dimension(filesPanel.getWidth(), 30));
        filePanel.setPreferredSize(new Dimension(filesPanel.getWidth(), 30));
        filePanel.setBackground(Color.WHITE);
        filesPanel.add(filePanel);

        JLabel fileNameLabel = new JLabel(" " + filePath.getFileName().toString());
        filePanel.add(fileNameLabel, BorderLayout.WEST);

        removeButton = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(deleteImage.getImage(), 5, 5, this.getWidth() - 10, this.getHeight() - 10, this);
            }
        };
        removeButton.setPreferredSize(new Dimension(30, 30));
        filePanel.add(removeButton, BorderLayout.EAST);
    }

    public Path getFilePath() {
        return filePath;
    }
}
