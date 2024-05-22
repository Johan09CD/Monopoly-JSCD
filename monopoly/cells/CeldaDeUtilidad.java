package monopoly.cells;

import monopoly.Celda;
import monopoly.Controlador;
import monopoly.Jugador;

public class CeldaDeUtilidad extends Celda {
    private static int PRICE;

    public static void setPrice(int price) {
        CeldaDeUtilidad.PRICE = price;
    }

    public int getRent(int diceRoll) {
        if (player.numberOfUtilities() == 1)
                return diceRoll * 4;
        else if (player.numberOfUtilities() >= 2)
                return diceRoll * 10;
        return 0;
    }

    @Override
    public int getPrice() {
        return CeldaDeUtilidad.PRICE;
    }
    
    @Override
    public void playAction(Controlador mainController) {
        Jugador currentPlayer;
        if (isAvailable())
            return;
        currentPlayer = mainController.getCurrentPlayer();
        if (player != currentPlayer) {
            mainController.utilityRollDice();
            int diceRoll = mainController.getUtilityDiceRoll();
            mainController.payRentTo(player, getRent(diceRoll));
        }
    }
}
