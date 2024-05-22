package monopoly.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.List;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import monopoly.Celda;
import monopoly.Controlador;
import monopoly.Jugador;
import monopoly.AcuerdosComerciales;
import monopoly.DialogosComerciales;

public class ComentariosComercioGUI extends JDialog implements DialogosComerciales {
    private static final long serialVersionUID = -7231996263338389498L;
    private JButton cancelButton;
    private JButton okButton;
    private JComboBox<Celda> propertiesCombobox;
    private final int comboboxBorderSize = 3;
    private JComboBox<Jugador> sellersCombobox;
    private AcuerdosComerciales deal;
    private JTextField amountText;
    
    public ComentariosComercioGUI(Controlador mainController, Frame parent) {
        super(parent);
        int xOffset = 125;
        int yOffset = 100;
        super.setLocationRelativeTo(parent.getFocusOwner().getParent().getParent());
        super.setLocation(super.getX() - xOffset, super.getY() - yOffset);
        super.setTitle("Negociar Propiedad");
        sellersCombobox = new JComboBox<>();
        propertiesCombobox = new JComboBox<>();
        amountText = new JTextField();
        okButton = new JButton("Aceptar");
        cancelButton = new JButton("Cancelar");

        
        okButton.setEnabled(false);
        
        buildSellersComboBox(mainController);
        super.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
             
        Container contentPane = super.getContentPane();
        contentPane.setLayout(new GridLayout(4, 2));
        contentPane.add(new JLabel("Propietario de la propiedad"));
        contentPane.add(sellersCombobox);
        contentPane.add(new JLabel("Propiedad"));
        contentPane.add(propertiesCombobox);
        contentPane.add(new JLabel("Precio de la oferta"));
        contentPane.add(amountText);
        contentPane.add(okButton);
        contentPane.add(cancelButton);


        cancelButton.addActionListener((ActionEvent e) -> {
            ComentariosComercioGUI.this.setVisible(false);
        });
        
        sellersCombobox.addItemListener((ItemEvent e) -> {
            Jugador player = (Jugador)e.getItem();
            updatePropertiesComboBox(player);
        });
        
        sellersCombobox.setRenderer(new DefaultListCellRenderer() {
            private static final long serialVersionUID = -5460014450312978883L;
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component ret = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (isSelected || cellHasFocus) {
                    Jugador p = (Jugador)value;
                    list.setSelectionBackground(p.getPlayerColor());
                    list.setSelectionForeground(Color.black);
                }
                return ret;
            }
        });

        PopupMenuListener listener = new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                Color defaultColor = new Color(238, 238, 238);
                sellersCombobox.setBorder(new LineBorder(defaultColor, comboboxBorderSize));
                sellersCombobox.setBackground(defaultColor);
            }
            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                Jugador player = (Jugador)sellersCombobox.getSelectedItem();
                sellersCombobox.setBorder(new LineBorder(player.getPlayerColor(), comboboxBorderSize));
                sellersCombobox.setBackground(player.getPlayerColor());
            }
            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {}
        };

        sellersCombobox.addPopupMenuListener(listener);
        
        okButton.addActionListener((ActionEvent e) -> {
            int cantidad;
            try {
                cantidad = Integer.parseInt(amountText.getText());
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(ComentariosComercioGUI.this,
                        "La cantidad debe ser un número entero", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (cantidad < 0) {
                JOptionPane.showMessageDialog(ComentariosComercioGUI.this,
                        "La cantidad no debe ser negativa", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            
            Celda celda = (Celda) propertiesCombobox.getSelectedItem();
            if (celda == null) return;
            Jugador jugadorActual = mainController.getCurrentPlayer();
            if (jugadorActual.getMoney() > cantidad) {
                deal = new AcuerdosComerciales(celda, jugadorActual, cantidad);
            } else {
                JOptionPane.showMessageDialog(ComentariosComercioGUI.this,
                        "No tienes suficiente dinero para hacer este intercambio", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            
            ComentariosComercioGUI.this.setVisible(false);
        });
        
        super.pack();
    }

    private void buildSellersComboBox(Controlador mainCtl) {
        List<Jugador> sellers = mainCtl.getSellerList();
        sellers.stream().forEach((player) -> {
            sellersCombobox.addItem(player);
        });
        if(sellers.size() > 0) {
            Jugador topSeller = sellers.get(0);
            updatePropertiesComboBox(topSeller);
            sellersCombobox.setBackground(topSeller.getPlayerColor());
            sellersCombobox.setBorder(new LineBorder(topSeller.getPlayerColor(), comboboxBorderSize));
        }
    }

    private void updatePropertiesComboBox(Jugador player) {
        propertiesCombobox.removeAllItems();
        List<Celda> cells = player.getAllProperties();
        okButton.setEnabled(cells.size() > 0);
        cells.stream().forEach((cell) -> {
            propertiesCombobox.addItem(cell);
        });
    }

    @Override
    public AcuerdosComerciales getTradeDeal(Controlador mainCtl) {
        return deal;
    }
}
