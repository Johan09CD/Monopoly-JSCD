package monopoly.cards;

import monopoly.Tarjeta;
import monopoly.Controlador;
import monopoly.Jugador;
import monopoly.enums.TiposDeCartas;

public class CartaDeDinero extends Tarjeta {
    private final int amount;
    private final TiposDeCartas cardType;
    
    private final String label;
    
    public CartaDeDinero(String label, int amount, TiposDeCartas cardType){
        this.label = label;
        this.amount = amount;
        this.cardType = cardType;
    }

    @Override
    public void applyAction(Controlador mainController) {
        Jugador currentPlayer = mainController.getCurrentPlayer();
        currentPlayer.setMoney(currentPlayer.getMoney() + amount);
    }

    @Override
    public TiposDeCartas getCardType() {
        return cardType;
    }

    @Override
    public String toString() {
        return label;
    }
}
