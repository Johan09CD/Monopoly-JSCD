package monopoly.gui.infoformatters;

import monopoly.Celda;
import monopoly.Jugador;
import monopoly.cells.PropiedadCelda;
import monopoly.gui.InfoCelda;

public class PropertyCellInfoFormatter implements InfoCelda {
    @Override
    public String format(Celda cell) {
        PropiedadCelda c = (PropiedadCelda)cell;
        StringBuilder buf = new StringBuilder();
        buf.append("<html><b><font color='")
                .append(c.getColorGroup().name())
                .append("'>")
                .append(cell.getName())
                .append("</font>")
                .append("</html>");
        return buf.toString();
    }
    
    @Override
    public String formatToolTip(Celda cell) {
        PropiedadCelda c = (PropiedadCelda)cell;
        StringBuilder buf = new StringBuilder();
        Jugador owner = cell.getOwner();
        String ownerName = "";
        if (owner != null) {
            ownerName = owner.getName();
        }
        buf.append("<html><b><font color='")
                .append(c.getColorGroup())
                .append("'>")
                .append(cell.getName())
                .append("</font></b><br>")
                .append("Precio de la propiedad: $")
                .append(c.getPrice())
                .append("<br>Precio de alquiler: $")
                .append(c.getRent())
                .append("<br><br>Dueño: ")
                .append(ownerName)
                .append("<br><br>Casas ⌂: ")
                .append(c.getNumHouses())
                .append("<br>Precio de casas: $")
                .append(c.getHousePrice())
                .append("</html>");
        return buf.toString();
    }
}
