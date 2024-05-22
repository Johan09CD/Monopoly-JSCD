package tests;

import junit.framework.TestCase;
import monopoly.Tarjeta;
import monopoly.Controlador;
import monopoly.cards.CartaDeDinero;
import monopoly.enums.TiposDeCartas;
import tests.gameboards.GameBoardCCGainMoney;
import tests.mocks.MockGUI;

public class GainMoneyCardTest extends TestCase {
    private Tarjeta gainMoneyCard;
    private Controlador mainController;

    @Override
    protected void setUp() {
        mainController = new Controlador();
        mainController.setGameBoard(new GameBoardCCGainMoney());
        mainController.setNumberOfPlayers(1);
	mainController.reset();
	mainController.setGUI(new MockGUI());
	gainMoneyCard = new CartaDeDinero("Obtener 50 dólares", 50, TiposDeCartas.CC);
        mainController.getGameBoard().addCard(gainMoneyCard);
    }
    
    public void testGainMoneyCardAction() {
        int originalMoney = mainController.getPlayer(0).getMoney();
	Tarjeta card = mainController.drawCCCard();
	assertEquals(gainMoneyCard, card);
	card.applyAction(mainController);
	assertEquals(originalMoney + 50, mainController.getPlayer(0).getMoney());
    }
    
    public void testGainMoneyCardUI() {
        mainController.movePlayer(mainController.getPlayer(0), 1);
        assertTrue(mainController.getGUI().isDrawCardButtonEnabled());
        assertFalse(mainController.getGUI().isEndTurnButtonEnabled());
        mainController.buttonDrawCardClicked();
        assertFalse(mainController.getGUI().isDrawCardButtonEnabled());
	assertTrue(mainController.getGUI().isEndTurnButtonEnabled());
    }
}
