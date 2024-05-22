package monopoly.gui.infoformatters;

import monopoly.Celda;
import monopoly.gui.InfoCelda;

public class FreeParkingCellInfoFormatter implements InfoCelda {
    
    public static final String FP_CELL_LABEL = "<html><b>Parqueadero gratis</b></html>";
    
    @Override
    public String format(Celda cell) {
        return FP_CELL_LABEL;
    }
    
    @Override
    public String formatToolTip(Celda cell) {
        return FP_CELL_LABEL;
    }
}
