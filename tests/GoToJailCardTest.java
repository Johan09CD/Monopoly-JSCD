package tests;

import junit.framework.TestCase;
import monopoly.Tarjeta;
import monopoly.Celda;
import monopoly.Controlador;
import monopoly.cards.TarjetaDeCarcel;
import monopoly.enums.TiposDeCartas;
import tests.gameboards.GameBoardCCJail;
import tests.mocks.MockGUI;

public class GoToJailCardTest extends TestCase {
    private Controlador mainController;
    private final Tarjeta jailCard = new TarjetaDeCarcel(TiposDeCartas.CC);
    
    @Override
    protected void setUp() {
        mainController = new Controlador();
        mainController.setGameBoard(new GameBoardCCJail());
        mainController.setNumberOfPlayers(1);
        mainController.reset();
        mainController.setGUI(new MockGUI());
        mainController.getGameBoard().addCard(jailCard);
    }
    
    public void testJailCardAction() {
        Tarjeta card = mainController.drawCCCard();
        assertEquals(jailCard, card);
        card.applyAction(mainController);
        Celda cell = mainController.getPlayer(0).getPosition();
        assertEquals(mainController.getGameBoard().queryCell("Cárcel"), cell);
    }
    
    public void testJailCardLabel() {
        assertEquals(
            "Vaya directamente a la cárcel sin cobrar $200 al pasar vamos", 
            jailCard.toString()
        );
    }
    
    public void testJailCardUI() {
        mainController.movePlayer(mainController.getPlayer(0), 1);
        assertTrue(mainController.getGUI().isDrawCardButtonEnabled());
        assertFalse(mainController.getGUI().isEndTurnButtonEnabled());
        mainController.buttonDrawCardClicked();
        assertFalse(mainController.getGUI().isDrawCardButtonEnabled());
        Celda cell = mainController.getPlayer(0).getPosition();
        assertEquals(mainController.getGameBoard().queryCell("Cárcel"), cell);
        assertTrue(mainController.getGUI().isEndTurnButtonEnabled());
    }
}
