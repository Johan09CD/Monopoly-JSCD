package tests;

import junit.framework.TestCase;
import monopoly.Jugador;
import monopoly.cells.IdaDeCelda;
import monopoly.cells.PropiedadCelda;
import monopoly.enums.GrupoDeColores;
import monopoly.gui.InfoFormatter;
import monopoly.gui.infoformatters.GoCellInfoFormatter;

public class CellInfoFormatterTest extends TestCase {
    
    public void testGoCellTest() {
        IdaDeCelda cell = new IdaDeCelda();
        String goLabel = GoCellInfoFormatter.GO_CELL_LABEL;
        assertEquals(goLabel, InfoFormatter.cellInfo(cell));
    }
    
    public void testPropertyCellText() {
        String propertyName = "Blue 1";
        GrupoDeColores propertyColor = GrupoDeColores.BLUE;
        String ownerName = "Propietario1";
        int numHouses = 2;
        int propertyValue = 120;
        int housePrice = 100;
        int rentPrice = 200;
        
        String propertyLabel = "<html><b><font color='" +
                                propertyColor +"'>" + 
                                propertyName + "</font></html>";
        String propertyToolTip = "<html><b><font color='" +
                                propertyColor +"'>" + 
                                propertyName + "</font></b><br>" +
				"Precio de propiedad: $" + propertyValue +
				"<br>Rent precio: $" + rentPrice +
				"<br><br>Dueño: " + ownerName +
				"<br><br>Casas ⌂: " + numHouses +
                                "<br>Precio de casas: $" + housePrice +
				"</html>";
        PropiedadCelda cell = new PropiedadCelda();
        cell.setName(propertyName);
        cell.setPrice(propertyValue);
        cell.setRent(rentPrice);
        cell.setColorGroup(propertyColor);
        cell.setHousePrice(100);
        Jugador player = new Jugador(cell);
        player.setName(ownerName);
        cell.setPlayer(player);
        cell.setNumHouses(numHouses);
        assertEquals(propertyLabel, InfoFormatter.cellInfo(cell));
        assertEquals(propertyToolTip, InfoFormatter.cellToolTip(cell));
    }
}
