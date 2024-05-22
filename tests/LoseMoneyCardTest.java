package tests;

import junit.framework.TestCase;
import monopoly.Tarjeta;
import monopoly.Controlador;
import monopoly.cards.CartaDeDinero;
import monopoly.enums.TiposDeCartas;
import tests.gameboards.GameBoardCCLoseMoney;
import tests.mocks.MockGUI;

public class LoseMoneyCardTest extends TestCase {
    private Controlador mainController;
    private Tarjeta loseMoneyCard;

    @Override
    protected void setUp() {
        mainController = new Controlador();
        mainController.setGameBoard(new GameBoardCCLoseMoney());
        mainController.setNumberOfPlayers(1);
        mainController.reset();
        mainController.setGUI(new MockGUI());
        loseMoneyCard = new CartaDeDinero("Pagar 20 dolares", -20, TiposDeCartas.CC);
        mainController.getGameBoard().addCard(loseMoneyCard);
    }
    
    public void testLoseMoneyCardAction() {
        int originalMoney = mainController.getPlayer(0).getMoney();
        Tarjeta card = mainController.drawCCCard();
        assertEquals(loseMoneyCard, card);
        card.applyAction(mainController);
        assertEquals(originalMoney - 20, mainController.getPlayer(0).getMoney());
    }
    
    public void testLoseMoneyCardUI() {
        mainController.movePlayer(mainController.getPlayer(0), 1);
        assertTrue(mainController.getGUI().isDrawCardButtonEnabled());
        assertFalse(mainController.getGUI().isEndTurnButtonEnabled());
        mainController.buttonDrawCardClicked();
        assertFalse(mainController.getGUI().isDrawCardButtonEnabled());
        assertTrue(mainController.getGUI().isEndTurnButtonEnabled());
    }
}
