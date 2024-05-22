package monopoly.cells;

import monopoly.Celda;
import monopoly.enums.TiposDeCartas;

public class TarjetaDeCeldas extends Celda {
    private final TiposDeCartas type;
    
    public TarjetaDeCeldas(TiposDeCartas type, String name) {
        super.setName(name);
        this.type = type;
    }
    
    public TiposDeCartas getType() {
        return type;
    }
}
