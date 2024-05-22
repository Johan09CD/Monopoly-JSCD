package tests;

import junit.framework.TestCase;
import monopoly.Celda;
import monopoly.TableroDeJuego;
import monopoly.Controlador;
import tests.gameboards.SimpleGameBoard;
import tests.mocks.MockGUI;

public class PlayerTest extends TestCase {

    private Controlador mainController;
	
    @Override
    protected void setUp() throws Exception {
        mainController = new Controlador();
        mainController.setGameBoard(new SimpleGameBoard());
        mainController.setGUI(new MockGUI());
        mainController.reset();
    }

    public void testInit() {
        mainController.setNumberOfPlayers(1);
        assertEquals(1500, mainController.getPlayer(0).getMoney());
    }
    
    public void testSameGoCell() {
        TableroDeJuego gameboard = mainController.getGameBoard();
        mainController.setNumberOfPlayers(2);
        
        Celda go = gameboard.queryCell("Vamos");
        assertSame(go, mainController.getPlayer(0).getPosition());
        assertSame(go, mainController.getPlayer(1).getPosition());
    }
	
    public void testPayRentTo() {
        mainController.setNumberOfPlayers(2);
        mainController.movePlayer(mainController.getPlayer(0), 4);
        mainController.purchase();
        mainController.buttonEndTurnClicked();
        mainController.movePlayer(mainController.getPlayer(1), 4);
        mainController.buttonEndTurnClicked();
        assertTrue(mainController.getPlayer(1).isBankrupt());
        assertEquals(2800, mainController.getPlayer(0).getMoney());
    }
	
    public void testGiveAllProperties() {
        mainController.setNumberOfPlayers(2);
        mainController.movePlayer(mainController.getPlayer(0), 3);
        mainController.purchase();
        mainController.buttonEndTurnClicked();
        mainController.giveAllProperties(mainController.getPlayer(0), mainController.getPlayer(1));
        assertEquals(1, mainController.getPlayer(1).getPropertyCount());
    }

    public void testResetProperty() {
        mainController.setNumberOfPlayers(1);
        mainController.movePlayer(mainController.getPlayer(0), 1);
        mainController.purchase();
        assertEquals(mainController.getGameBoard().getCell(1), 
            mainController.getPlayer(0).getAllProperties().get(0)
        );
        mainController.getPlayer(0).resetProperties();
        assertEquals(0, mainController.getPlayer(0).getAllProperties().size());
    }
}
