package monopoly;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TableroControlador {
    public static final int MAX_PLAYER = 6;
    
    private TableroDeJuego gameBoard;
    
    private final List<Color> playerColors = new ArrayList<>(Arrays.asList(
    new Color(255, 249, 102), /* Jugador 1 */
    new Color(66, 134, 244),  /* Jugador 2 */
    new Color(143, 99, 158),  /* Jugador 3 */
    new Color(209, 155, 20),  /* Jugador 4 */
    new Color(209, 96, 20),   /* Jugador 5 */
    new Color(120, 230, 30)   /* Jugador 6 */
    ));
    private int outOfGamePlayers = 0;
    private int playerTurnIndex = 0;
    private final List<Jugador> players = new ArrayList<>();


    public TableroControlador(TableroDeJuego gameBoard) {
        this.gameBoard = gameBoard;
    }
    
    public Jugador getCurrentPlayer() {
        return getPlayer(playerTurnIndex);
    }

    public int getCurrentPositionIndex(Jugador player) {
        Celda currentPosition = player.getPosition();
        return gameBoard.queryCellIndex(currentPosition.getName());
    }

    public TableroDeJuego getGameBoard() {
        return gameBoard;
    }

    public int getNewPositionIndex(int positionIndex, int diceValue) {
        return (positionIndex + diceValue) % gameBoard.getCellSize();    
    }

    public int getNumberOfPlayers() {
        return players.size();
    }

    public int getOutOfGamePlayersNumber() {
        return outOfGamePlayers;
    }
    
    public Jugador getPlayer(int index) {
        return players.get(index);
    }
    
    public int getPlayerIndex(Jugador player) {
        return players.indexOf(player);
    }
    
    public List<Jugador> getPlayers() {
        return players;
    }
    
    public int getTurn() {
        return playerTurnIndex;
    }
	
    public void movePlayer(Jugador player, int diceValue) {
        int positionIndex = getCurrentPositionIndex(player);
        int newIndex = getNewPositionIndex(positionIndex, diceValue);
        if (newIndex <= positionIndex || diceValue > gameBoard.getCellSize())
            player.setMoney(player.getMoney() + 200);
        player.setPosition(gameBoard.getCell(newIndex));
    }
    
    public void removePlayer() {
        outOfGamePlayers++;
    }
    
    public void reset() {    
        for (int i = 0; i < getNumberOfPlayers(); i++) {
            Jugador player = players.get(i);
            player.setPosition(gameBoard.getCell(0));
        }
        playerTurnIndex = 0;
    }
    
    public void setGameBoard(TableroDeJuego board) {
        this.gameBoard = board;
    }
    
    public void setNumberOfPlayers(int number) {
        players.clear();
        for (int i = 0; i < number; i++) {
            Jugador player = new Jugador(gameBoard.getCell(0));
            player.setPlayerColor(playerColors.get(i));
            players.add(player);
        }
    }
    
    public void switchTurn() {
        playerTurnIndex = (playerTurnIndex + 1) % getNumberOfPlayers();
    }
}
