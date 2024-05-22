package monopoly.gui.infoformatters;

import monopoly.Celda;
import monopoly.gui.InfoCelda;

public class GotoJailCellInfoFormatter implements InfoCelda {

    public static final String GOTO_JAIL_LABEL = "<html><b>Vamos a la cárcel</b></html>";

    @Override
    public String format(Celda cell) {
    	return GOTO_JAIL_LABEL;
    }
    
    @Override
    public String formatToolTip(Celda cell) {
        return GOTO_JAIL_LABEL;
    }
}
