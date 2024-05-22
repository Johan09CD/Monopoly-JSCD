package monopoly.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import monopoly.Dado;

public class TiradaDeDados extends JDialog {
    private static final long serialVersionUID = -2985807932807855607L;
    
    public static int showDialog(PanelJug panel) {
        TiradaDeDados dialog = new TiradaDeDados(panel);
        dialog.setVisible(true);
        return dialog.diceValue;
    }
    
    private final JButton diceButton = new JButton("¡Tirar los Dados!");
    private final JButton okButton = new JButton("Aceptar");
    private int diceValue;
    private final JLabel promptLabel = new JLabel();
    
    public TiradaDeDados(PanelJug panel) {
        super.setModal(true);
        okButton.setEnabled(false);
        okButton.setVisible(false);
        promptLabel.setText("Por favor tira los dados para determinar tu cuenta de servicios públicos.");
        Container contentPane = super.getContentPane();
        JPanel panelButtons = new JPanel();
        panelButtons.add(diceButton);
        panelButtons.add(okButton);
        contentPane.setLayout(new BorderLayout());
        contentPane.add(promptLabel, BorderLayout.CENTER);
        contentPane.add(panelButtons, BorderLayout.SOUTH);
        diceButton.addActionListener((ActionEvent arg0) -> {
            rollDice();
            diceButton.setVisible(false);
            okButton.setVisible(true);
        });
        okButton.addActionListener((ActionEvent arg0) -> {
            okClicked();
        });
        super.setLocationRelativeTo(panel);
        int xOffset = 170;
        int yOffset = 60;
        super.setLocation(super.getX() - xOffset, super.getY() - yOffset);
        super.pack();
    }

    public final void okClicked(){
        this.dispose();
    }

    public final void rollDice() {
        Dado dice = new Dado(2);
        diceValue = dice.getTotal();
        StringBuilder text = new StringBuilder();
        text.append("You rolled " )
                .append(dice.getSingleDice(0))
                .append(", ")
                .append(dice.getSingleDice(1))
                .append(" which totals ")
                .append(dice.getTotal())
                .append(".");
        promptLabel.setText(text.toString());
        diceButton.setEnabled(false);
        okButton.setEnabled(true);
    }
}
