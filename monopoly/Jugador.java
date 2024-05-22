package monopoly;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import monopoly.cells.PropiedadCelda;
import monopoly.cells.CeldaDeTren;
import monopoly.cells.CeldaDeUtilidad;
import monopoly.enums.GrupoDeColores;

public class Jugador {
    private final int INITIAL_MONEY = 2000;
    private boolean inJail;
    private int money;
    private String name;
    private boolean isOutOfGame;
    private Color playerColor;
    private Celda position;
    private List<PropiedadCelda> properties = new ArrayList<>();
    
    private final Map<GrupoDeColores, Integer> propertyColors = new HashMap<>();
    private List<CeldaDeTren> railroads = new ArrayList<>();
    private List<CeldaDeUtilidad> utilities = new ArrayList<>();
    
    public Jugador(Celda position) {
        this.position = position;
        inJail = false;
        isOutOfGame = false;
        playerColor = Color.GREEN;
        money = INITIAL_MONEY;
    }
    
    public void addMoney(int money) {
        this.money += money;
    }

    public void addProperty(PropiedadCelda property) {
        properties.add(property);
        addPropertyColor(property.getColorGroup());
    }
    
    private void addPropertyColor(GrupoDeColores colorGroup) {
        propertyColors.put(colorGroup, getPropertyNumberForColor(colorGroup) + 1);
    }
    
    public void addRailRoad(CeldaDeTren railroad) {
        railroads.add(railroad);
        addPropertyColor(GrupoDeColores.RAILROAD);
    }
    
    public void addUtility (CeldaDeUtilidad utility) {
        utilities.add(utility);
        addPropertyColor(GrupoDeColores.UTILITY);
    }

    public boolean checkProperty(String property) {
        return properties.stream().map((propertie) -> 
                (Celda) propertie).anyMatch((cell) -> 
                (cell.getName().equals(property)));
    }

    public List<Celda> getAllProperties() {
        List<Celda> list = new ArrayList<>();
        list.addAll(properties);
        list.addAll(utilities);
        list.addAll(railroads);
        return list;
    }

    public int getMoney() {
            return this.money;
    }

    public String getName() {
        return name;
    }
    
    public Color getPlayerColor() {
        return playerColor;
    }

    public Celda getPosition() {
        return this.position;
    }
	
    public PropiedadCelda getProperty(int index) {
        return properties.get(index);
    }
    
    public List<PropiedadCelda> getPropertyCells() {
        return properties;
    }
    
    public Map<GrupoDeColores, Integer> getPropertyColors() {
        return propertyColors;
    }
	
    public int getPropertyCount() {
        return properties.size();
    }
    
    private int getPropertyNumberForColor(GrupoDeColores colorGroup) {
        Integer number = propertyColors.get(colorGroup);
        if (number != null) {
            return number;
        }
        return 0;
    }
    
    public List<CeldaDeTren> getRailRoadCells() {
        return railroads;
    }

    public List<CeldaDeUtilidad> getUtilityCells() {
        return utilities;
    }
    
    public boolean isBankrupt() {
        return money <= 0;
    }

    public boolean isInJail() {
        return inJail;
    }

    public boolean isOutOfGame() {
        return isOutOfGame;
    }
    
    public int numberOfRailroads() {
        return railroads.size();
    }

    public int numberOfUtilities() {
        return utilities.size();
    }
    
    public void removePropertyCell(PropiedadCelda property) {
        properties.remove(property);
        removePropertyColor(property.getColorGroup());
    }
    
    private void removePropertyColor(GrupoDeColores colorGroup) {
        propertyColors.remove(colorGroup);
    }
    
    public void removeRailroadCell(CeldaDeTren railroad) {
        railroads.remove(railroad);
        removePropertyColor(GrupoDeColores.RAILROAD);
    }
    
    public void removeUtilityCell(CeldaDeUtilidad utility) {
        utilities.remove(utility);
        removePropertyColor(GrupoDeColores.UTILITY);
    }
    
    public void resetProperties() {
        properties = new ArrayList<>();
        railroads = new ArrayList<>();
        utilities = new ArrayList<>();
    }
    
    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setOutOfGame() {
        isOutOfGame = true;
    }
    
    public void setPlayerColor(Color color) {
        this.playerColor = color;
    }

    public void setPosition(Celda newPosition) {
        this.position = newPosition;
    }
    
    public void subtractMoney(int money) {
        this.money -= money;
    }
  
    @Override
    public String toString() {
        return name;
    }
}
