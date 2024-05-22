package monopoly;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import monopoly.cells.PropiedadCelda;
import monopoly.cells.CeldaDeTren;
import monopoly.cells.CeldaDeUtilidad;
import monopoly.enums.GrupoDeColores;

public class ControladorDePropiedad {
    private final TableroControlador boardController;
    private final TableroDeJuego gameBoard;
    
    public ControladorDePropiedad(TableroControlador boardController) {
        this.boardController = boardController;
        this.gameBoard = boardController.getGameBoard();
    }

    public void buyProperty(AcuerdosComerciales deal) {
        Celda property = deal.getProperty();
        Jugador buyer = deal.getBuyer();
        property.setPlayer(buyer);
        
        if (property instanceof PropiedadCelda) {
            buyer.addProperty((PropiedadCelda) property);
            updatePropertyRent((PropiedadCelda) property);
        }
        if (property instanceof CeldaDeTren) {
            buyer.addRailRoad((CeldaDeTren) property);
            updateRailRoadRent((CeldaDeTren)property);
        }
        if (property instanceof CeldaDeUtilidad) {
            buyer.addUtility((CeldaDeUtilidad) property);
        }
        buyer.subtractMoney(deal.getAmount());
    }
    
    public boolean canBuyHouse() {
        return (!getMonopolies(boardController.getCurrentPlayer()).isEmpty());
    }
    
    private void doublePropertyRent(GrupoDeColores colorGroup) {
        List<PropiedadCelda> properties = gameBoard.getPropertiesInMonopoly(colorGroup);
        
        properties.stream().forEach((property) -> {
            property.setRent(property.originalRent() * 2);
        });
    }
    
    public List<GrupoDeColores> getMonopolies(Jugador player) {
        Map<GrupoDeColores, Integer> propertyColors = player.getPropertyColors();
        List<GrupoDeColores> monopolies = new ArrayList<>();
        Set<GrupoDeColores> colors = propertyColors.keySet();
        
        for (int i = 0; i < colors.size(); i++) {
            GrupoDeColores propertyColor = (GrupoDeColores) colors.toArray()[i];
            if (!propertyColor.equals(GrupoDeColores.RAILROAD) && !propertyColor.equals(GrupoDeColores.UTILITY)) {
                Integer num = propertyColors.get(propertyColor);
                if (num == gameBoard.getPropertyNumberForColor(propertyColor)) {
                    monopolies.add(propertyColor);
                }
            }
        }
        return monopolies;
    }
    
    public List<Jugador> getSellerList() {
        List<Jugador> sellers = new ArrayList<>();
        boardController.getPlayers().stream().filter((player) ->
                (player != boardController.getCurrentPlayer())).forEach((player) -> {
                    sellers.add(player);
                });
        return sellers;
    }
    
    public void giveAllProperties(Jugador fromPlayer, Jugador toPlayer) {
        List<PropiedadCelda> properties = fromPlayer.getPropertyCells();
        List<CeldaDeTren> railroads = fromPlayer.getRailRoadCells();
        List<CeldaDeUtilidad> utilities = fromPlayer.getUtilityCells();
        
        properties.stream().map((property) -> {
            property.setPlayer(toPlayer);
            return property;
        }).forEach((property) -> {
            if (toPlayer == null) {
                property.setAvailable(true);
                property.setNumHouses(0);
            } else {
                toPlayer.addProperty(property);
            }
        });
        properties.clear();
        railroads.stream().map((railroad) -> {
            railroad.setPlayer(toPlayer);
            return railroad;
        }).forEach((railroad) -> {
            if (toPlayer == null) {
                railroad.setAvailable(true);
            } else {
                toPlayer.addRailRoad(railroad);
            }
        });
        railroads.clear();
        utilities.stream().map((utility) -> {
            utility.setPlayer(toPlayer);
            return utility;
        }).forEach((utility) -> {
            if (toPlayer == null) {
                utility.setAvailable(true);
            } else {
                toPlayer.addUtility(utility);
            }
        });
        utilities.clear();
    }
	
    public void payRentTo(Jugador owner, int rentValue) {
        Jugador currentPlayer = boardController.getCurrentPlayer();
        int playerMoney = currentPlayer.getMoney();
        
        if (playerMoney < rentValue) {
            owner.addMoney(playerMoney);
            currentPlayer.subtractMoney(rentValue);
        } else {
            currentPlayer.subtractMoney(rentValue);
            owner.addMoney(rentValue);
        }
        
        if (currentPlayer.isBankrupt()) {
            currentPlayer.setMoney(0);
            giveAllProperties(currentPlayer, owner);
        }
    }
    
    public void purchase() {
        Jugador currentPlayer = boardController.getCurrentPlayer();
        
        if (currentPlayer.getPosition().isAvailable()) {
            Celda cell = currentPlayer.getPosition();
            AcuerdosComerciales deal = new AcuerdosComerciales(cell, currentPlayer, cell.getPrice());
            buyProperty(deal);
            cell.setAvailable(false);
        }
    }
    
    public int purchaseHouse(GrupoDeColores selectedMonopoly, int houses) {
        Jugador currentPlayer = boardController.getCurrentPlayer();
        
        int numOfHouses = 0;
        int money = currentPlayer.getMoney();
        List<PropiedadCelda> properties = gameBoard.getPropertiesInMonopoly(selectedMonopoly);
        if ((money >= (properties.size() * (properties.get(0).getHousePrice() * houses)))) {
            for (PropiedadCelda property : properties) {
                numOfHouses = property.getNumHouses() + houses;
                if (numOfHouses <= 5) {
                    property.setNumHouses(numOfHouses);
                    currentPlayer.subtractMoney(property.getHousePrice() * houses);
                    updatePropertyRent(property);
                }
            }
        }
        return numOfHouses;
    }
    
    private void resetPropertyRent(GrupoDeColores colorGroup) {
        List<PropiedadCelda> properties = gameBoard.getPropertiesInMonopoly(colorGroup);
        
        properties.stream().forEach((property) -> {
            property.setRent(property.originalRent());
        });
    }
    
    public void sellProperty(AcuerdosComerciales deal) {
        Jugador seller = deal.getSeller();
        Celda property = deal.getProperty();
        
        if (property instanceof PropiedadCelda) {
            seller.removePropertyCell((PropiedadCelda)property);
            updatePropertyRent((PropiedadCelda)property);    
        } else if (property instanceof CeldaDeTren) {
            seller.removeRailroadCell((CeldaDeTren)property);
            updateRailRoadRent((CeldaDeTren)property);
        } else if (property instanceof CeldaDeUtilidad) {
            seller.removeUtilityCell((CeldaDeUtilidad)property);
        }
        seller.addMoney(deal.getAmount());
    }
    
    private void updatePropertyRent(PropiedadCelda property) {
        int previousRent = property.getRent();
        int numHouses = property.getNumHouses();
        int newRent;
        Jugador owner = property.getOwner();
        
        resetPropertyRent(property.getColorGroup());
        if (owner == null)
            return;
        List<GrupoDeColores> monopolies = getMonopolies(owner);
        for (GrupoDeColores monopoly : monopolies) {
            if (monopoly.equals(property.getColorGroup()))
                doublePropertyRent(monopoly);            
            if (numHouses > 0) {
                newRent = previousRent * (numHouses + 1);
                property.setRent(newRent);
            }
        }
    }

    private void updateRailRoadRent(CeldaDeTren railroad) {
        Jugador owner = railroad.getOwner();
        int basePrice = railroad.getBaseRent();
        
        /* Reset to basePrice */
        railroad.setRent(basePrice);
        if (owner == null)
            return;
        List<CeldaDeTren> railRoads = owner.getRailRoadCells();
        int newRent = basePrice * (int)Math.pow(2, owner.numberOfRailroads() - 1);
            
        railRoads.stream().forEach((playersRailroad) -> {
            playersRailroad.setRent(newRent);
        });
    }
}
