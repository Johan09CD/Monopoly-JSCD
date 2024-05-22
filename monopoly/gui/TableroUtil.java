package monopoly.gui;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import monopoly.Celda;
import monopoly.TableroDeJuego;

public class TableroUtil {
    
    public static Dimension calculateDimension(int i) {
        i = i - 4;
        int shortSide = i / 4;
        int longSide = (i - (shortSide * 2)) / 2;
        return new Dimension(longSide, shortSide);
    }
	
    public static List<Celda> getEastCells(TableroDeJuego board) {
        Dimension dimension = calculateDimension(board.getCellSize());
        int shortSide = dimension.height;
        List<Celda> cells = new ArrayList<>();
        for (int i = board.getCellSize() - shortSide; i <= board.getCellSize() - 1; i++) {
                cells.add(board.getCell(i));
        }
        return cells;
    }
	
    public static List<Celda> getNorthCells(TableroDeJuego board) {
        Dimension dimension = calculateDimension(board.getCellSize());
        int longSide = dimension.width;
        int shortSide = dimension.height;
        List<Celda> cells = new ArrayList<>();
        for (int i = longSide + 2 + shortSide; i <= longSide + 2 + shortSide + longSide + 1; i++)
                cells.add(board.getCell(i));
        return cells;
    }
	
    public static List<Celda> getSouthCells(TableroDeJuego board) {
        Dimension dimension = calculateDimension(board.getCellSize());
        int longSide = dimension.width;
        List<Celda> cells = new ArrayList<>();
        for (int i = longSide + 1; i >= 0; i--)
                cells.add(board.getCell(i));
        return cells;
    }

    public static List<Celda> getWestCells(TableroDeJuego board) {
        Dimension dimension = calculateDimension(board.getCellSize());
        int longSide = dimension.width;
        int shortSide = dimension.height;
        List<Celda> cells = new ArrayList<>();
        for (int i = longSide + 1 + shortSide; i > longSide + 1; i--)
                cells.add(board.getCell(i));
        return cells;
    }
}
