package monopoly.gui.infoformatters;

import monopoly.Celda;
import monopoly.gui.InfoCelda;

public class ChanceCellInfoFormatter implements InfoCelda {
    
    public static final String CHANCE_CELL_LABEL = "<html><font color='teal'><b>Chance</b></font></html>";
    
    @Override
    public String format(Celda cell) {
        return CHANCE_CELL_LABEL;
    }
    
    @Override
    public String formatToolTip(Celda cell) {
        return CHANCE_CELL_LABEL;
    }
}
