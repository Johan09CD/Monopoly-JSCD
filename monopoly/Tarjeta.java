package monopoly;

import monopoly.enums.TiposDeCartas;

public abstract class Tarjeta {

    public abstract void applyAction(Controlador mainController);
    public abstract TiposDeCartas getCardType();
}
