package tests;

import junit.framework.TestCase;
import monopoly.Tarjeta;
import monopoly.Celda;
import monopoly.Controlador;
import monopoly.cards.CartaDeMoverJug;
import monopoly.enums.TiposDeCartas;
import tests.gameboards.GameBoardCCMovePlayer;
import tests.mocks.MockGUI;

public class MovePlayerCardTest extends TestCase {
    private Controlador mainController;
    private Tarjeta movePlayerCard;
    
    @Override
    protected void setUp() {
        mainController = new Controlador();
        mainController.setGameBoard(new GameBoardCCMovePlayer());
        mainController.setNumberOfPlayers(1);
        mainController.reset();
        mainController.setGUI(new MockGUI());
        movePlayerCard = new CartaDeMoverJug("Blue 1", TiposDeCartas.CC);
        mainController.getGameBoard().addCard(movePlayerCard);
    }
    
    public void testJailCardLabel() {
        assertEquals("vamos a Blue 1", movePlayerCard.toString());
    }
    
    public void testMovePlayerCardAction() {
        Tarjeta card = mainController.drawCCCard();
        assertEquals(movePlayerCard, card);
        card.applyAction(mainController);
        Celda cell = mainController.getCurrentPlayer().getPosition();
        assertEquals(mainController.getGameBoard().queryCell("Blue 1"), cell);
    }
    
    public void testMovePlayerCardUI() {
        mainController.movePlayer(mainController.getCurrentPlayer(), 2);
        assertTrue(mainController.getGUI().isDrawCardButtonEnabled());
        assertFalse(mainController.getGUI().isEndTurnButtonEnabled());
        mainController.buttonDrawCardClicked();
        assertFalse(mainController.getGUI().isDrawCardButtonEnabled());
        Celda cell = mainController.getCurrentPlayer().getPosition();
        assertEquals(mainController.getGameBoard().queryCell("Blue 1"), cell);
        assertTrue(mainController.getGUI().isEndTurnButtonEnabled());
        assertEquals(1700, mainController.getCurrentPlayer().getMoney());
    }
}
