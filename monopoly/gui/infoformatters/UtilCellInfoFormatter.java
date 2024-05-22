package monopoly.gui.infoformatters;

import monopoly.Celda;
import monopoly.Jugador;
import monopoly.cells.CeldaDeUtilidad;
import monopoly.gui.InfoCelda;

public class UtilCellInfoFormatter implements InfoCelda {

    @Override
    public String format(Celda cell) {
        StringBuilder buf = new StringBuilder();
        buf.append("<html><b><font color='olive'>")
                .append(cell.getName())
                .append("</font>")
                .append("</html>");
        return buf.toString();
    }
    
    @Override
    public String formatToolTip(Celda cell) {
        CeldaDeUtilidad c = (CeldaDeUtilidad)cell;
        StringBuilder buf = new StringBuilder();
        Jugador owner = cell.getOwner();
        String ownerName = "";
        if (owner != null) {
                ownerName = owner.getName();
        }
        buf.append("<html><b><font color='olive'>")
                .append(cell.getName())
                .append("</font></b><br>")
                .append("Precio: $")
                .append(c.getPrice())
                .append("<br><br>Dueño: ")
                .append(ownerName)
                .append("</html>");
        return buf.toString();
    }
}
