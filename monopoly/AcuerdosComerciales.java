package monopoly;

public class AcuerdosComerciales {
    private final int amount;
    private final Jugador buyer;
    private final Celda property;
    private final String propertyName;
    private final Jugador seller;
    
    public AcuerdosComerciales(Celda property, Jugador buyer, int amount) {
        this.propertyName = property.getName();
        this.seller = property.getOwner();
        this.buyer = buyer;
        this.amount = amount;
        this.property = property;
    }
    
    public int getAmount() {
        return amount;
    }
    
    public Jugador getBuyer() {
        return this.buyer;
    }
    
    public Celda getProperty() {
        return property;
    }
    
    public String getPropertyName() {
        return propertyName;
    }

    public Jugador getSeller() {
        return this.seller;
    }
    
    public String makeMessage() {
        String message =
                this.buyer + " desea comprar " + propertyName +
                " de ti por $" + this.amount +
                ". ¿Deseas vender tu propiedad?";
        return message;
    }
}