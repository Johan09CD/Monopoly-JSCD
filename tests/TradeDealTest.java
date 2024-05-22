package tests;

import junit.framework.TestCase;
import monopoly.Controlador;
import monopoly.Jugador;
import monopoly.AcuerdosComerciales;
import monopoly.cells.PropiedadCelda;

public class TradeDealTest extends TestCase {
    private Controlador mainController;
    
    @Override
    public void setUp() {
        mainController = new Controlador();
        mainController.reset();
        mainController.setNumberOfPlayers(2);
        mainController.getPlayer(0).setName("Comprador");
        mainController.getPlayer(1).setName("Vendedor");
    }

    public void testMakeMessage() {
        Jugador buyer = mainController.getPlayer(0);
        Jugador seller = mainController.getPlayer(1);
        int propertyPrice = 200;
        
        PropiedadCelda property = new PropiedadCelda();
        property.setName("Blue 1");
        property.setPrice(propertyPrice);
        property.setPlayer(seller);
        
        AcuerdosComerciales deal = new AcuerdosComerciales(property, buyer, propertyPrice);
        
        String message =
                "El comprador desea comprarle Blue 1 por $200 " +
                " ¿Desea comercializar su propiedad?";
        assertEquals(message, deal.makeMessage());
    }

}
