package tests;

import junit.framework.TestCase;
import monopoly.Controlador;
import monopoly.cells.CeldaDeTren;
import tests.gameboards.GameBoardRailRoad;
import tests.mocks.MockGUI;

public class RailRoadCellTest extends TestCase {
    private Controlador mainController;

    @Override
    protected void setUp() {
        mainController = new Controlador();
        mainController.setGameBoard(new GameBoardRailRoad());
        mainController.setNumberOfPlayers(2);
        mainController.reset();
        mainController.setGUI(new MockGUI());
    }

    public void testPlayerAction() {
        CeldaDeTren cell =
                (CeldaDeTren) mainController.getGameBoard().queryCell("Ferrocarril A");
        int cellIndex = mainController.getGameBoard().queryCellIndex("Ferrocarril A");
        mainController.movePlayer(mainController.getPlayer(0), cellIndex);
        mainController.purchase();
        mainController.switchTurn();
        mainController.movePlayer(mainController.getPlayer(1), cellIndex);
        cell.playAction(mainController);
        assertEquals(1500 - cell.getRent(), mainController.getPlayer(1).getMoney());
        assertEquals(1300 + cell.getRent(), mainController.getPlayer(0).getMoney());
    }

    public void testPurchaseRailroad() {
        assertEquals(0, mainController.getPlayer(0).numberOfRailroads());
        int cellIndex = mainController.getGameBoard().queryCellIndex("Railroad A");
        mainController.movePlayer(mainController.getPlayer(0), cellIndex);
        mainController.purchase();
        assertEquals(1300, mainController.getPlayer(0).getMoney());
        assertEquals(1, mainController.getPlayer(0).numberOfRailroads());
    }

    public void testRent() {
        CeldaDeTren railroad1 =
                (CeldaDeTren) mainController.getGameBoard().queryCell("Ferrocarril A");
        int cellIndex1 = mainController.getGameBoard().queryCellIndex("Ferrocarril A");
        mainController.movePlayer(mainController.getPlayer(0), cellIndex1);
        mainController.purchase();
        assertEquals(25, railroad1.getRent());

        CeldaDeTren railroad2 =
                (CeldaDeTren) mainController.getGameBoard().queryCell("Ferrocarril B");
        int cellIndex2 = mainController.getGameBoard().queryCellIndex("Ferrocarril B");
        mainController.movePlayer(mainController.getPlayer(0), cellIndex2 - cellIndex1);
        mainController.purchase();
        assertEquals(50, railroad1.getRent());
        assertEquals(50, railroad2.getRent());
    }
}
