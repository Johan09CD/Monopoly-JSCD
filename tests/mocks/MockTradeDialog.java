package tests.mocks;

import monopoly.Celda;
import monopoly.Controlador;
import monopoly.Jugador;
import monopoly.AcuerdosComerciales;
import monopoly.DialogosComerciales;

public class MockTradeDialog implements DialogosComerciales {

    @Override
    public AcuerdosComerciales getTradeDeal(Controlador mainController) {
        Celda property = mainController.getGameBoard().getCell(1);
        Jugador buyer = mainController.getPlayer(1);
        int dealAmount = 200;
        
        AcuerdosComerciales deal = new AcuerdosComerciales(property, buyer, dealAmount);
        return deal;
    }
}
