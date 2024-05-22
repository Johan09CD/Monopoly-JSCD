package monopoly.cards;

import monopoly.Tarjeta;
import monopoly.Celda;
import monopoly.Controlador;
import monopoly.Jugador;
import monopoly.enums.TiposDeCartas;

public class CartaDeMoverJug extends Tarjeta {
    
    private final String destination;
    private final TiposDeCartas type;

    public CartaDeMoverJug(String destination, TiposDeCartas cardType) {
        this.destination = destination;
        this.type = cardType;
    }

    @Override
    public void applyAction(Controlador mainController) {
        Jugador currentPlayer = mainController.getCurrentPlayer();
        Celda currentPosition = currentPlayer.getPosition();
        int newCell = mainController.getGameBoard().queryCellIndex(destination);
        int currentCell = mainController.getGameBoard().queryCellIndex(currentPosition.getName());
        int diceValue = 0;
        if (currentCell > newCell) {
            diceValue = (mainController.getGameBoard().getCellSize() + 
                         (newCell - currentCell));
        } else if (currentCell <= newCell) {
            diceValue = newCell - currentCell;
        }
        mainController.movePlayer(currentPlayer, diceValue);
    }

    @Override
    public TiposDeCartas getCardType() {
        return type;
    }

    @Override
    public String toString() {
        return "Ve a " + destination;
    }
}
