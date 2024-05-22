package monopoly.gui.infoformatters;

import monopoly.Celda;
import monopoly.gui.InfoCelda;

public class CCCellInfoFormatter implements InfoCelda {
    @Override
    public String format(Celda cell) {
        return "<html><font color='black'><b>" + cell.getName() + "</b></font></html>";
    }
    
    @Override
    public String formatToolTip(Celda cell) {
        return "<html><font color='black'><b>" + cell.getName() + "</b></font></html>";
    }
}
