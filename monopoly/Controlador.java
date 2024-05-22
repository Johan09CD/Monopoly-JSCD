package monopoly;

import java.util.List;
import monopoly.cells.TarjetaDeCeldas;
import monopoly.cells.CeldaDeCarcel;
import monopoly.enums.TiposDeCartas;
import monopoly.enums.GrupoDeColores;
import monopoly.gameboards.TableroDeJuegoDef;
import monopoly.gui.MonopolyGUI;
import monopoly.gui.PanelJug;

public class Controlador {
    private final TableroControlador boardController;

    private final Dado dice;
    private TableroDeJuego gameBoard;
    private MonopolyGUI gui;
    private final ControladorDePropiedad propertyController;
    private int utilityDiceRoll;
    
    public Controlador() {
        gameBoard = new TableroDeJuegoDef();
        boardController = new TableroControlador(gameBoard);
        propertyController = new ControladorDePropiedad(boardController);
        dice = new Dado(2);
    }

    public void buttonBuyHouseClicked() {
        gui.showBuyHouseDialog(getCurrentPlayer());
    }

    public Tarjeta buttonDrawCardClicked() {
        gui.setDrawCardEnabled(false);
        TarjetaDeCeldas cell = (TarjetaDeCeldas)getCurrentPlayer().getPosition();
        Tarjeta card;
        if (cell.getType() == TiposDeCartas.CC) {
            card = getGameBoard().drawCCCard();
            card.applyAction(this);
        } else {
            card = getGameBoard().drawChanceCard();
            card.applyAction(this);
        }
        gui.setEndTurnEnabled(true);
        return card;
    }

    public void buttonEndTurnClicked() {
        setAllButtonEnabled(false);
        getCurrentPlayer().getPosition().playAction(this);
        
        if (getCurrentPlayer().isBankrupt()) {
            getCurrentPlayer().setOutOfGame();
            boardController.removePlayer();
        }
        switchTurn();
        gui.update();
    }

    public void buttonGetOutOfJailClicked() {
        getOutOfJail();
        if (getCurrentPlayer().isBankrupt()) {
            setAllButtonEnabled(false);
            getCurrentPlayer().setOutOfGame();
            int positionIndex = boardController.getCurrentPositionIndex(getCurrentPlayer());
            gui.removePlayer(getPlayerIndex(getCurrentPlayer()), positionIndex);
            boardController.removePlayer();
            switchTurn();
            gui.update();
        } else {
            gui.setRollDiceEnabled(true);
            gui.setBuyHouseEnabled(propertyController.canBuyHouse());
            gui.setGetOutOfJailEnabled(getCurrentPlayer().isInJail());
            gui.setTradeEnabled(getTurn(), true);
        }
    }

    public void buttonPurchasePropertyClicked() {
        purchase();
        gui.setPurchasePropertyEnabled(false);
        gui.update();
    }
    
    public void buttonRollDiceClicked(PanelJug panel) {
        dice.roll();
        if ((dice.getTotal()) > 0) {
            Jugador player = getCurrentPlayer();
            gui.setRollDiceEnabled(false);
            StringBuilder msg = new StringBuilder();
            msg.append("You rolled ")
                    .append(dice.getSingleDice(0))
                    .append(" and ")
                    .append(dice.getSingleDice(1));
            gui.showMessage(msg.toString(), panel);
            movePlayer(player, dice.getTotal());
            gui.setBuyHouseEnabled(false);
        }
    }

    public void buttonTradeClicked() {
        DialogosComerciales dialog = gui.openTradeDialog();
        AcuerdosComerciales deal = dialog.getTradeDeal(this);
        if (deal != null) {
            RespuestaDeDialogos respondDialog = gui.openRespondDialog(deal);
            if (respondDialog.getResponse()) {
                completeTrade(deal);
                gui.update();
            }
        }
    }
    
    public boolean canBuyHouse() {
        return propertyController.canBuyHouse();
    }

    public void completeTrade(AcuerdosComerciales deal) {
        propertyController.sellProperty(deal);
        propertyController.buyProperty(deal);
    }

    public Tarjeta drawCCCard() {
        return gameBoard.drawCCCard();
    }

    public Tarjeta drawChanceCard() {
        return gameBoard.drawChanceCard();
    }
    
    private void finishPlayerMove(Jugador player) {
        Celda cell = player.getPosition();
        int playerIndex = getPlayerIndex(player);
        if (cell instanceof TarjetaDeCeldas) {
            gui.setDrawCardEnabled(true);
        } else {
            if (cell.isAvailable()) {
                int price = cell.getPrice();
                if (price <= player.getMoney() && price > 0)
                    gui.enablePurchaseButton(playerIndex);
            }
            gui.enableEndTurnButton(playerIndex);
        }
        gui.setTradeEnabled(boardController.getTurn(), false);
    }

    public Jugador getCurrentPlayer() {
        return boardController.getCurrentPlayer();
    }
    
    public Dado getDice() {
        return dice;
    }

    public MonopolyGUI getGUI() {
        return gui;
    }
    
    public TableroDeJuego getGameBoard() {
        return gameBoard;
    }
    
    public List<GrupoDeColores> getMonopolies(Jugador player) {
        return propertyController.getMonopolies(player);
    }

    public int getNumberOfPlayers() {
        return boardController.getNumberOfPlayers();
    }
    
    private void getOutOfJail() {
        Jugador currentPlayer = boardController.getCurrentPlayer();
        currentPlayer.subtractMoney(CeldaDeCarcel.BAIL);
        if (currentPlayer.isBankrupt()) {
            currentPlayer.setMoney(0);
            giveAllProperties(currentPlayer, null);
        }
        currentPlayer.setInJail(false);
        gui.update();
    }

    public Jugador getPlayer(int index) {
        return boardController.getPlayer(index);
    }

    public int getPlayerIndex(Jugador player) {
        return boardController.getPlayerIndex(player);
    }

    public List<Jugador> getSellerList() {
        return propertyController.getSellerList();
    }
    
    public int getTurn() {
        return boardController.getTurn();
    }

    public int getUtilityDiceRoll() {
        return this.utilityDiceRoll;
    }
    
    public void giveAllProperties(Jugador fromPlayer, Jugador toPlayer) {
        propertyController.giveAllProperties(fromPlayer, toPlayer);
    }

    public void movePlayer(Jugador player, int diceValue) {
        int positionIndex = boardController.getCurrentPositionIndex(player);
        int newIndex = boardController.getNewPositionIndex(positionIndex, diceValue);
        
        boardController.movePlayer(player, diceValue);
        gui.movePlayer(getPlayerIndex(player), positionIndex, newIndex);
        finishPlayerMove(player);

        gui.update();
    }
    
    public void payRentTo(Jugador owner, int rent) {
        propertyController.payRentTo(owner, rent);
    }
    
    public void purchase() {
        propertyController.purchase();
    }
    
    public void purchaseHouse(GrupoDeColores selectedMonopoly, int houses) {
        if (propertyController.purchaseHouse(selectedMonopoly, houses) <= 5)
            gui.update();
    }

    public void reset() {
        boardController.reset();
        if (gameBoard != null)
            gameBoard.removeCards();
    }
	
    public void sendToJail(Jugador player) {
        String currentPlayerName = getCurrentPlayer().getPosition().getName();
        int oldPosition = gameBoard.queryCellIndex(currentPlayerName);
        player.setPosition(gameBoard.queryCell("Cárcel"));
        player.setInJail(true);
        int jailIndex = gameBoard.queryCellIndex("Cárcel");
        gui.movePlayer(getPlayerIndex(player), oldPosition, jailIndex);
    }
    
    private void setAllButtonEnabled(boolean enabled) {
        gui.setRollDiceEnabled(enabled);
        gui.setPurchasePropertyEnabled(enabled);
        gui.setEndTurnEnabled(enabled);
        gui.setTradeEnabled(boardController.getTurn(), enabled);
        gui.setBuyHouseEnabled(enabled);
        gui.setDrawCardEnabled(enabled);
        gui.setGetOutOfJailEnabled(enabled);
    }
    
    public void setGUI(MonopolyGUI gui) {
        this.gui = gui;
    }

    public void setGameBoard(TableroDeJuego board) {
        this.gameBoard = board;
        boardController.setGameBoard(board);
    }

    public void setNumberOfPlayers(int number) {
        boardController.setNumberOfPlayers(number);
    }

    public void startGame() {
        gui.startGame();
        gui.enablePlayerTurn(0);
        gui.setTradeEnabled(0, true);
    }

    public void switchTurn() {
        boardController.switchTurn();
        
        if (getCurrentPlayer().isOutOfGame()) {
            switchTurn();
            return;
        }
        if (boardController.getOutOfGamePlayersNumber() + 1 >= boardController.getNumberOfPlayers()) {
            setAllButtonEnabled(false);
            return;
        }
        if (!getCurrentPlayer().isInJail()) {
            gui.enablePlayerTurn(boardController.getTurn());
            gui.setBuyHouseEnabled(propertyController.canBuyHouse());
            gui.setTradeEnabled(boardController.getTurn(), true);
        } else {
            gui.setGetOutOfJailEnabled(true);
        }
    }

    public void utilityRollDice() {
        this.utilityDiceRoll = gui.showUtilityDiceRoll();
    }
    
}
