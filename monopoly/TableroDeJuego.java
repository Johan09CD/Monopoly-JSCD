package monopoly;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import monopoly.cells.IdaDeCelda;
import monopoly.cells.PropiedadCelda;
import monopoly.enums.TiposDeCartas;
import monopoly.enums.GrupoDeColores;

public class TableroDeJuego {

    private final List<Celda> cells = new ArrayList<>();
    private final List<Tarjeta> chanceCards = new ArrayList<>();
    private final List<Tarjeta> communityChestCards = new ArrayList<>();
   
    private final Map<GrupoDeColores, Integer> propertyColors = new HashMap<>();

    public TableroDeJuego() {
        Celda go = new IdaDeCelda();
        addCell(go);
    }

    public void addCard(Tarjeta card) {
        if (card.getCardType() == TiposDeCartas.CC)
            communityChestCards.add(card);
        else
            chanceCards.add(card);
    }

    public final void addCell(Celda cell) {
        cells.add(cell);
    }
	
    public void addCell(PropiedadCelda cell) {
        int propertyNumber = getPropertyNumberForColor(cell.getColorGroup());
        propertyColors.put(cell.getColorGroup(), propertyNumber + 1);
        cells.add(cell);
    }

    public Tarjeta drawCCCard() {
        Tarjeta card = communityChestCards.remove(0);
        addCard(card);
        return card;
    }

    public Tarjeta drawChanceCard() {
        Tarjeta card = chanceCards.remove(0);
        addCard(card);
        return card;
    }

    public Celda getCell(int index) {
        return cells.get(index);
    }
	
    public int getCellSize() {
        return cells.size();
    }
	
    public List<PropiedadCelda> getPropertiesInMonopoly(GrupoDeColores color) {
        List<PropiedadCelda> monopolyCells = new ArrayList<>();
        cells.stream().filter((cell) 
                            -> (cell instanceof PropiedadCelda)).map((cell)
                            -> (PropiedadCelda)cell).filter((pc) 
                            -> (pc.getColorGroup().equals(color))).forEach((pc) -> {
                                monopolyCells.add(pc);
                            });
        return monopolyCells;
    }
	
    public int getPropertyNumberForColor(GrupoDeColores colorGroup) {
        Integer number = propertyColors.get(colorGroup);
        if (number != null)
            return number;
        return 0;
    }

    public Celda queryCell(String string) {
        for (Celda cell : cells) {
            if (cell.getName().equals(string))
                return cell;
        }
        return null;
    }
	
    public int queryCellIndex(String string){
        for (int i = 0; i < cells.size(); i++) {
            if (cells.get(i).getName().equals(string))
                return i;
        }
        return -1;
    }

    public void removeCards() {
        communityChestCards.clear();
    }
    
    public final void shuffleCards() {
        Collections.shuffle(communityChestCards);
        Collections.shuffle(chanceCards);
    }
}
