package monopoly.cards;

import monopoly.Tarjeta;
import monopoly.Controlador;
import monopoly.Jugador;
import monopoly.enums.TiposDeCartas;

public class TarjetaDeCarcel extends Tarjeta {
    private final TiposDeCartas type;
    
    public TarjetaDeCarcel(TiposDeCartas cardType) {
        type = cardType;
    }

    @Override
    public void applyAction(Controlador mainController) {
        Jugador currentPlayer = mainController.getCurrentPlayer();
        mainController.sendToJail(currentPlayer);
    }

    @Override
    public TiposDeCartas getCardType() {
        return type;
    }

    @Override
    public String toString() {
        return "Ve directamente a la cárcel sin cobrar $200 al pasar por la casilla de salida";
    }
}
