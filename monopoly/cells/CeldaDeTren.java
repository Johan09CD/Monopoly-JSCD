package monopoly.cells;

import monopoly.Celda;
import monopoly.Controlador;
import monopoly.Jugador;

public class CeldaDeTren extends Celda {
    public static String COLOR_GROUP = "FERROCARRIL";
    private int baseRent = 0;
    private int price;
    private int rent;
    
    public int getBaseRent() {
        return baseRent;
    }

    public int getRent() {
        return rent;
    }

    public void setBaseRent(int baseRent) {
        this.baseRent = baseRent;
        this.rent = baseRent;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setRent(int rent) {
        if (baseRent == 0) {
            baseRent = rent;
        }
        this.rent = rent;
    }
    
    @Override
    public int getPrice() {
        return price;
    }
    
    @Override
    public void playAction(Controlador mainController) {
        Jugador currentPlayer;
        if (isAvailable())
            return;
        currentPlayer = mainController.getCurrentPlayer();
        if (player != currentPlayer)
            mainController.payRentTo(player, getRent());
    }
}
