package monopoly.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.border.BevelBorder;
import monopoly.TableroControlador;
import monopoly.Celda;
import monopoly.Controlador;
import monopoly.Jugador;

public class CeldaGUI extends JPanel {
    private static final long serialVersionUID = 2752137388247147409L;
    private final Celda cell;
    private JLabel infoLabel;
    private final JLabel[] playersLabel = new JLabel[TableroControlador.MAX_PLAYER];
    
    public CeldaGUI(Celda cell) {
        this.cell = cell;
        super.setLayout(new OverlayLayout(this));
        super.setBorder(new BevelBorder(BevelBorder.LOWERED));
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new GridLayout(2, 4, 0, 25));
        playerPanel.setOpaque(false);
        createPlayerLabels(playerPanel);
        super.add(playerPanel);
        super.setPreferredSize(new Dimension(100,100));
        addCellInfo();
        super.setToolTipText(InfoFormatter.cellToolTip(cell));
        super.doLayout();
    }
	
    private void addCellInfo() {
        infoLabel = new JLabel();
        displayInfo();
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(1, 1));
        infoPanel.add(infoLabel);
        add(infoPanel);
    }
	
    public void addPlayer(Controlador mainController, int index) {
        Jugador player = mainController.getPlayer(index);
        playersLabel[index].setOpaque(true);
        playersLabel[index].setBackground(player.getPlayerColor());
    }

    private void createPlayerLabels(JPanel playerPanel) {
        for (int i = 0; i < TableroControlador.MAX_PLAYER; i++) {
            playersLabel[i] = new JLabel();
            playerPanel.add(playersLabel[i]);
            
        }
    }

    public void displayInfo() {
        infoLabel.setText(InfoFormatter.cellInfo(cell));
        this.setToolTipText(InfoFormatter.cellToolTip(cell));
        this.invalidate();
        this.repaint();
    }

    public Celda getCell() {
        return cell;
    }

    public void removePlayer(int index) {
        playersLabel[index].setText("");
        playersLabel[index].setOpaque(false);
        this.repaint();
    }
}
