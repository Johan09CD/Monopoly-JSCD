package monopoly.gui.infoformatters;

import monopoly.Celda;
import monopoly.Jugador;
import monopoly.cells.CeldaDeTren;
import monopoly.gui.InfoCelda;

public class RRCellInfoFormatter implements InfoCelda {
    @Override
    public String format(Celda cell) {
        StringBuilder buf = new StringBuilder();
        buf.append("<html><b><font color='lime'>")
                .append(cell.getName())
                .append("</font>")
                .append("</html>");
        return buf.toString();
    }
    
    @Override
    public String formatToolTip(Celda cell) {
        CeldaDeTren c = (CeldaDeTren)cell;
        StringBuilder buf = new StringBuilder();
        Jugador owner = cell.getOwner();
        String ownerName = "";
        if(owner != null) {
            ownerName = owner.getName();
        }
        buf.append("<html><b><font color='lime'>")
                .append(cell.getName())
                .append("</font></b><br>")
                .append("Precio de propiedad: $")
                .append(c.getPrice())
                .append("<br>Precio de alquiler: $")
                .append(c.getRent())
                .append("<br><br>Dueño: ")
                .append(ownerName)
                .append("</html>");
        return buf.toString();
    }
}
