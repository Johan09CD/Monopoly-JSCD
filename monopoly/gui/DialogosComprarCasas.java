
package monopoly.gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import monopoly.TableroDeJuego;
import monopoly.Controlador;
import monopoly.Jugador;
import monopoly.cells.PropiedadCelda;
import monopoly.enums.GrupoDeColores;

public class DialogosComprarCasas extends JDialog {
    private static final long serialVersionUID = -8707274599957567230L;
    private final JComboBox<Integer> housesCombobox;
    private final Controlador mainController;
    private JComboBox<GrupoDeColores> monopolyCombobox;
    private final Jugador player;
    
    public DialogosComprarCasas(Controlador mainController, Jugador player, PanelJug panel) {
        this.mainController = mainController;
        this.player = player;
        
        Container container = super.getContentPane();
        housesCombobox = new JComboBox<>();
        container.setLayout(new GridLayout(3, 2));
        container.add(new JLabel("Selecciona el monopolio"));
        container.add(buildMonopolyComboBox());
        container.add(new JLabel("Número de casas"));
        container.add(housesCombobox);
        container.add(buildOKButton());
        container.add(buildCancelButton());
        container.doLayout();
        super.pack();
        super.setLocationRelativeTo(panel);
        
        updateHousesComboBox(monopolyCombobox.getItemAt(0));
        
        monopolyCombobox.addActionListener((ActionEvent e) -> {
            GrupoDeColores monopoly = (GrupoDeColores)monopolyCombobox.getSelectedItem();
            updateHousesComboBox(monopoly);
        });
    }

    private JButton buildCancelButton() {
        JButton button = new JButton("Cancelar");
        button.addActionListener((ActionEvent e) -> {
            cancelClicked();
        });
        return button;
    }

    private JComboBox<GrupoDeColores> buildMonopolyComboBox() {
        monopolyCombobox = new JComboBox<>();
        List<GrupoDeColores> monopolies = mainController.getMonopolies(player);
        monopolies.stream().forEach((monopoly) -> {
            monopolyCombobox.addItem(monopoly);
        });
        return monopolyCombobox;
    }

    private JButton buildOKButton() {
        JButton button = new JButton("OK");
        button.addActionListener((ActionEvent e) -> {
            okClicked();
        });
        return button;
    }

    private void cancelClicked() {
        this.dispose();
    }

    private void okClicked() {
        GrupoDeColores monopoly = (GrupoDeColores)monopolyCombobox.getSelectedItem();
        int number = housesCombobox.getSelectedIndex() + 1;
        mainController.purchaseHouse(monopoly, number);
        this.dispose();
    }
    
    private void updateHousesComboBox(GrupoDeColores monopoly) {
        housesCombobox.removeAllItems();
        TableroDeJuego gameBoard = mainController.getGameBoard();
        List<PropiedadCelda> properties = gameBoard.getPropertiesInMonopoly(monopoly);
        int numHouses = properties.get(0).getNumHouses();
        int maxHouses = 5;
        int maxPurchase = maxHouses - numHouses;
        for (int i = 1; i <= maxPurchase; i++)
            housesCombobox.addItem(i);
    }
}
