package monopoly.gui.infoformatters;

import monopoly.Celda;
import monopoly.gui.InfoCelda;

public class JailCellInfoFormatter implements InfoCelda {

    public static final String JAIL_CELL_LABEL = "<html><b>Cárcel</b></html>";

    @Override
    public String format(Celda cell) {
        return JAIL_CELL_LABEL;
    }
    
    @Override
    public String formatToolTip(Celda cell) {
        return JAIL_CELL_LABEL;
    }

}
