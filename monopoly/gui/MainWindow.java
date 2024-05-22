package monopoly.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import monopoly.Celda;
import monopoly.TableroDeJuego;
import monopoly.Controlador;
import monopoly.Jugador;
import monopoly.AcuerdosComerciales;
import monopoly.DialogosComerciales;
import monopoly.RespuestaDeDialogos;

public class MainWindow extends JFrame implements MonopolyGUI {
    private static final long serialVersionUID = 3146365872410925008L;
    private final JPanel eastPanel = new JPanel();
    private final ArrayList<CeldaGUI> guiCells = new ArrayList<>();
    private final Controlador mainController;
    private final JPanel northPanel = new JPanel();
    private PanelJug[] playerPanels;
    private final JPanel southPanel = new JPanel();
    private final JPanel westPanel = new JPanel();

    public MainWindow(Controlador mainCtl) {
        this.mainController = mainCtl;
        
        northPanel.setBorder(new LineBorder(Color.BLACK));
        southPanel.setBorder(new LineBorder(Color.BLACK));
        westPanel.setBorder(new LineBorder(Color.BLACK));
        eastPanel.setBorder(new LineBorder(Color.BLACK));

        Container container = super.getContentPane();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        super.setSize(dimension);
        container.add(northPanel, BorderLayout.NORTH);
        container.add(southPanel, BorderLayout.SOUTH);
        container.add(eastPanel, BorderLayout.EAST);
        container.add(westPanel, BorderLayout.WEST);

        super.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                    System.exit(0);
            }
        });
    }

    private void addCells(JPanel panel, List<Celda> cells) {
        cells.stream().map((cell) -> new CeldaGUI(cell)).map((guiCell) -> {
                                        panel.add(guiCell);
                                        return guiCell;
                                    }).forEach((guiCell) -> {
                                        guiCells.add(guiCell);
                                    });
    }

    private void buildPlayerPanels() {
        JPanel infoPanel = new JPanel();
        int players = mainController.getNumberOfPlayers();
        infoPanel.setLayout(new GridLayout(2, (players+1)/2));
        getContentPane().add(infoPanel, BorderLayout.CENTER);
        playerPanels = new PanelJug[mainController.getNumberOfPlayers()];
        for (int i = 0; i < mainController.getNumberOfPlayers(); i++){
            playerPanels[i] = new PanelJug(mainController, mainController.getPlayer(i));
            infoPanel.add(playerPanels[i]);
            playerPanels[i].displayInfo();
        }
    }
	
    private CeldaGUI queryCell(int index) {
        Celda cell = mainController.getGameBoard().getCell(index);
            for (Object guiCell1 : guiCells) {
                CeldaGUI guiCell = (CeldaGUI) guiCell1;
                if (guiCell.getCell() == cell)
                    return guiCell;
            }
        return null;
    }
	
    public void setupGameBoard(TableroDeJuego board) {
        Dimension dimension = TableroUtil.calculateDimension(board.getCellSize());
        northPanel.setLayout(new GridLayout(1, dimension.width + 2));
        southPanel.setLayout(new GridLayout(1, dimension.width + 2));
        westPanel.setLayout(new GridLayout(dimension.height, 1));
        eastPanel.setLayout(new GridLayout(dimension.height, 1));
        addCells(northPanel, TableroUtil.getNorthCells(board));
        addCells(southPanel, TableroUtil.getSouthCells(board));
        addCells(eastPanel, TableroUtil.getEastCells(board));
        addCells(westPanel, TableroUtil.getWestCells(board));
        buildPlayerPanels();
    }

    @Override
    public void enableEndTurnButton(int playerIndex) {
        playerPanels[playerIndex].setEndTurnEnabled(true);
    }

    @Override
    public void enablePlayerTurn(int playerIndex) {
        playerPanels[playerIndex].setRollDiceEnabled(true);
    }

    @Override
    public void enablePurchaseButton(int playerIndex) {
        playerPanels[playerIndex].setPurchasePropertyEnabled(true);
    }

    @Override
    public boolean isDrawCardButtonEnabled() {
        int currentPlayerIndex = mainController.getTurn();
        return playerPanels[currentPlayerIndex].isDrawCardButtonEnabled();
    }

    @Override
    public boolean isEndTurnButtonEnabled() {
        int currentPlayerIndex = mainController.getTurn();
        return playerPanels[currentPlayerIndex].isEndTurnButtonEnabled();
    }

    @Override
    public boolean isGetOutOfJailButtonEnabled() {
        int currentPlayerIndex = mainController.getTurn();
        return playerPanels[currentPlayerIndex].isGetOutOfJailButtonEnabled();
    }

    @Override
    public boolean isTradeButtonEnabled(int i) {
        return playerPanels[i].isTradeButtonEnabled();
    }
	
    @Override
    public void movePlayer(int index, int from, int to) {
        CeldaGUI fromCell = queryCell(from);
        CeldaGUI toCell = queryCell(to);
        fromCell.removePlayer(index);
        toCell.addPlayer(mainController, index);
    }

    @Override
    public RespuestaDeDialogos openRespondDialog(AcuerdosComerciales deal) {
        int sellerIdx = mainController.getPlayerIndex(deal.getSeller());
        ResponderComentariosGUI dialog = new ResponderComentariosGUI(playerPanels[sellerIdx]);
        dialog.setDeal(deal);
        dialog.setVisible(true);
        return dialog;
    }

    @Override
    public DialogosComerciales openTradeDialog() {
        ComentariosComercioGUI dialog = new ComentariosComercioGUI(mainController, this);
        dialog.setVisible(true);
        return dialog;
    }

    @Override
    public void removePlayer(int index, int from) {
        CeldaGUI cell = queryCell(from);
        cell.removePlayer(index);
    }
    
    @Override
    public void setBuyHouseEnabled(boolean enabled) {
        int currentPlayerIndex = mainController.getTurn();
        playerPanels[currentPlayerIndex].setBuyHouseEnabled(enabled);
    }

    @Override
    public void setDrawCardEnabled(boolean enabled) {
        int currentPlayerIndex = mainController.getTurn();
        playerPanels[currentPlayerIndex].setDrawCardEnabled(enabled);
    }

    @Override
    public void setEndTurnEnabled(boolean enabled) {
        int currentPlayerIndex = mainController.getTurn();
        playerPanels[currentPlayerIndex].setEndTurnEnabled(enabled);
    }

    @Override
    public void setGetOutOfJailEnabled(boolean enabled) {
        int currentPlayerIndex = mainController.getTurn();
        playerPanels[currentPlayerIndex].setGetOutOfJailEnabled(enabled);
    }

    @Override
    public void setPurchasePropertyEnabled(boolean enabled) {
        int currentPlayerIndex = mainController.getTurn();
        playerPanels[currentPlayerIndex].setPurchasePropertyEnabled(enabled);
    }

    @Override
    public void setRollDiceEnabled(boolean enabled) {
        int currentPlayerIndex = mainController.getTurn();
        playerPanels[currentPlayerIndex].setRollDiceEnabled(enabled);
    }

    @Override
    public void setTradeEnabled(int index, boolean enabled) {
        playerPanels[index].setTradeEnabled(enabled);
    }

    @Override
    public void showBuyHouseDialog(Jugador currentPlayer) {
        int currentPlayerIndex = mainController.getPlayerIndex(currentPlayer);
        PanelJug panel = playerPanels[currentPlayerIndex];
        DialogosComprarCasas dialog = new DialogosComprarCasas(mainController, currentPlayer, panel);
        dialog.setVisible(true);
    }

    @Override
    public void showMessage(String message, PanelJug panel) {
        JOptionPane.showMessageDialog(panel, message);
    }

    @Override
    public int showUtilityDiceRoll() {
        int currentPlayerIndex = mainController.getPlayerIndex(mainController.getCurrentPlayer());
        return TiradaDeDados.showDialog(playerPanels[currentPlayerIndex]);
    }

    @Override
    public void startGame() {
        int numberOfPlayers = mainController.getNumberOfPlayers();
        for (int i = 0; i < numberOfPlayers; i++) {
            movePlayer(i, 0, 0);
        }
    }

    @Override
    public void update() {
        for (PanelJug playerPanel : playerPanels) {
            playerPanel.displayInfo();
        }
        
        guiCells.stream().map((guiCell) -> guiCell).forEach((cell) -> {
            cell.displayInfo();
        });
    }
}
