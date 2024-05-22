package monopoly.cells;

import monopoly.Celda;
import monopoly.Controlador;
import monopoly.Jugador;
import monopoly.enums.GrupoDeColores;

public class PropiedadCelda extends Celda {
    private GrupoDeColores colorGroup;
    private int housePrice;
    private int numHouses;
    private int originalRent = 0;
    private int rent;
    private int sellPrice;
    
    public GrupoDeColores getColorGroup() {
        return colorGroup;
    }

    public int getHousePrice() {
        return housePrice;
    }

    public int getNumHouses() {
        return numHouses;
    }
    
    public int getRent() {
        return rent;
    }
    
    public int originalRent() {
        return originalRent;
    }

    public void setColorGroup(GrupoDeColores colorGroup) {
        this.colorGroup = colorGroup;
    }

    public void setHousePrice(int housePrice) {
        this.housePrice = housePrice;
    }

    public void setNumHouses(int numHouses) {
        this.numHouses = numHouses;
    }

    public void setPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public void setRent(int rent) {
        if (originalRent == 0)
            originalRent = rent;
        this.rent = rent;
    }
    
    @Override
    public int getPrice() {
        return sellPrice;
    }

    @Override
    public void playAction(Controlador mainController) {
        Jugador currentPlayer;
        if (!isAvailable()) {
            currentPlayer = mainController.getCurrentPlayer();
            if (player != currentPlayer) {
                mainController.payRentTo(player, rent);
            }
        }
    }
}