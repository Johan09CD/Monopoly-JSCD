package tests;

import junit.framework.TestCase;
import monopoly.Tarjeta;
import monopoly.Controlador;
import monopoly.cards.CartaDeDinero;
import monopoly.enums.TiposDeCartas;
import tests.gameboards.GameBoardCCGainMoney;
import tests.mocks.MockGUI;

public class CardsTest extends TestCase {
    private Tarjeta ccCard, chanceCard;
    private Controlador mainController;

    @Override
    protected void setUp() {
        mainController = new Controlador();
        mainController.setGameBoard(new GameBoardCCGainMoney());
        mainController.setNumberOfPlayers(1);
        mainController.reset();
        mainController.setGUI(new MockGUI());
        ccCard = new CartaDeDinero("Obtener 50 dólares", 50, TiposDeCartas.CC);
        chanceCard = new CartaDeDinero("Perder 50 dólares", -50, TiposDeCartas.CHANCE);
        mainController.getGameBoard().addCard(ccCard);
    }
    
    public void testCardType() {
        assertEquals(TiposDeCartas.CC, ccCard.getCardType());
        assertEquals(TiposDeCartas.CHANCE, chanceCard.getCardType());
    }
}
