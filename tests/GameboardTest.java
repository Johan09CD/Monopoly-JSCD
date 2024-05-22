package tests;

import java.util.List;
import junit.framework.TestCase;
import monopoly.Celda;
import monopoly.TableroDeJuego;
import monopoly.cells.PropiedadCelda;
import monopoly.enums.GrupoDeColores;
import tests.gameboards.SimpleGameBoard;

public class GameboardTest extends TestCase {

    private Celda cell;
    private TableroDeJuego gameBoard;

    @Override
    protected void setUp() throws Exception {
        gameBoard = new TableroDeJuego();
        cell = new PropiedadCelda();
        cell.setName("Celda temporal");
    }

    public void testAddCell() {
        assertEquals(1, gameBoard.getCellSize());
        gameBoard.addCell(cell);
        assertEquals(2, gameBoard.getCellSize());
    }

    public void testCellsForMonopoly() {
        TableroDeJuego gameboard = new SimpleGameBoard();
        List<PropiedadCelda> properties = gameboard.getPropertiesInMonopoly(GrupoDeColores.BLUE);
        assertEquals("Blue 1", properties.get(0).getName());
        assertEquals("Blue 2", properties.get(1).getName());
        assertEquals("Blue 3", properties.get(2).getName());
        assertEquals(3, properties.size());
    }

    public void testPropertyNumberForColor() {
        PropiedadCelda cell1 = new PropiedadCelda();
        cell1.setName("Blue 1");
        cell1.setColorGroup(GrupoDeColores.BLUE);
        PropiedadCelda cell2 = new PropiedadCelda();
        cell2.setName("Blue 2");
        cell2.setColorGroup(GrupoDeColores.BLUE);
        PropiedadCelda cell3 = new PropiedadCelda();
        cell3.setName("Green 1");
        cell3.setColorGroup(GrupoDeColores.GREEN);

        gameBoard.addCell(cell1);
        gameBoard.addCell(cell2);
        gameBoard.addCell(cell3);
        assertEquals(2, gameBoard.getPropertyNumberForColor(GrupoDeColores.BLUE));
        assertEquals(1, gameBoard.getPropertyNumberForColor(GrupoDeColores.GREEN));
    }

    public void testQueryCell() {
        gameBoard.addCell(cell);
        assertSame(cell,gameBoard.queryCell("Celda temporal"));
    }

    public void testQueryCellIndex() {
        gameBoard.addCell(cell);
        assertEquals(0,gameBoard.queryCellIndex("Vamos"));
        assertEquals(1,gameBoard.queryCellIndex("Celda temporal"));
    }
}
