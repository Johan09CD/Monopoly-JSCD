package monopoly.cells;

import monopoly.Celda;
import monopoly.Controlador;
import monopoly.Jugador;

public class IrCeldaDeCarcel extends Celda {
	
    public IrCeldaDeCarcel() {
        super.setName("Vamos a la cárcel");
    }

    @Override
    public void playAction(Controlador mainController) {
        Jugador currentPlayer = mainController.getCurrentPlayer();
        mainController.sendToJail(currentPlayer);
    }
}
