package monopoly.gui;

import java.util.HashMap;
import java.util.Map;
import monopoly.Celda;
import monopoly.cells.TarjetaDeCeldas;
import monopoly.cells.CeldaEstacionamiento;
import monopoly.cells.IdaDeCelda;
import monopoly.cells.IrCeldaDeCarcel;
import monopoly.cells.CeldaDeCarcel;
import monopoly.cells.PropiedadCelda;
import monopoly.cells.CeldaDeTren;
import monopoly.cells.CeldaDeUtilidad;
import monopoly.gui.infoformatters.CCCellInfoFormatter;
import monopoly.gui.infoformatters.FreeParkingCellInfoFormatter;
import monopoly.gui.infoformatters.GoCellInfoFormatter;
import monopoly.gui.infoformatters.GotoJailCellInfoFormatter;
import monopoly.gui.infoformatters.JailCellInfoFormatter;
import monopoly.gui.infoformatters.PropertyCellInfoFormatter;
import monopoly.gui.infoformatters.RRCellInfoFormatter;
import monopoly.gui.infoformatters.UtilCellInfoFormatter;

public class InfoFormatter {
    static Map<Object, InfoCelda> cellInfoFormatters;
    
    static {
        cellInfoFormatters = new HashMap<>();
        addFormatters();
    }

    private static void addFormatters() {
        cellInfoFormatters.put(PropiedadCelda.class, new PropertyCellInfoFormatter());
        cellInfoFormatters.put(IdaDeCelda.class, new GoCellInfoFormatter());
        cellInfoFormatters.put(CeldaDeCarcel.class, new JailCellInfoFormatter());
        cellInfoFormatters.put(IrCeldaDeCarcel.class, new GotoJailCellInfoFormatter());
        cellInfoFormatters.put(CeldaEstacionamiento.class, new FreeParkingCellInfoFormatter());
        cellInfoFormatters.put(CeldaDeTren.class, new RRCellInfoFormatter());
        cellInfoFormatters.put(CeldaDeUtilidad.class, new UtilCellInfoFormatter());
        cellInfoFormatters.put(TarjetaDeCeldas.class, new CCCellInfoFormatter());
    }

    public static String cellInfo(Celda cell) {
        InfoCelda formatter = cellInfoFormatters.get(cell.getClass());
        return formatter.format(cell);
    }

    public static String cellToolTip(Celda cell) {
        InfoCelda formatter = cellInfoFormatters.get(cell.getClass());
        return formatter.formatToolTip(cell);
    }

}
