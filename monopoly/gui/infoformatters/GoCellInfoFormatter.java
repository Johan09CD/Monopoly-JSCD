package monopoly.gui.infoformatters;

import monopoly.Celda;
import monopoly.gui.InfoCelda;

public class GoCellInfoFormatter implements InfoCelda {
    
    public static final String GO_CELL_LABEL = "<html><b>Go</b></html>";
    
    @Override
    public String format(Celda cell) {
        return GO_CELL_LABEL;
    }
    
    @Override
    public String formatToolTip(Celda cell) {
        return GO_CELL_LABEL;
    }
}
