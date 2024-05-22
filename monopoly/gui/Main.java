package monopoly.gui;

import javax.swing.JOptionPane;
import monopoly.*;

public class Main {

    private static final Controlador MAIN_CONTROLLER = new Controlador();
    
    private static int inputNumberOfPlayers(MainWindow window) {
        int numPlayers = 0;
        while(numPlayers < 2 || numPlayers > TableroControlador.MAX_PLAYER) {
            String numberOfPlayers = JOptionPane.showInputDialog(
                window, 
                "¿Cuántos jugadores?"
            );
            if (numberOfPlayers == null) {
                System.exit(0);
            }
            try {
                numPlayers = Integer.parseInt(numberOfPlayers);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                    window, 
                    "Por favor ingresa un número"
                );
                continue;
            }
            if (numPlayers < 2 || numPlayers > TableroControlador.MAX_PLAYER) {
                JOptionPane.showMessageDialog(
                    window, 
                    "Por favor ingresa un número entre 2 y 6"
                );
            } else {
                MAIN_CONTROLLER.setNumberOfPlayers(numPlayers);
            }
        }
        return numPlayers;
    }

    public static void main(String[] args) {
        MainWindow window = new MainWindow(MAIN_CONTROLLER);
        if (args.length > 0) {
            try {
                TableroDeJuego board = (TableroDeJuego) Class.forName(args[1]).newInstance();
                MAIN_CONTROLLER.setGameBoard(board);
            } catch (ClassNotFoundException e) {
                JOptionPane.showMessageDialog(
                    window, 
                    "Clase no encontrada. El programa se cerrará."
                );
                System.exit(0);
            } catch (IllegalAccessException e ) {
                JOptionPane.showMessageDialog(
                    window, 
                    "Acceso ilegal a la clase. El programa se cerrará."
                );
                System.exit(0);
            } catch (InstantiationException e) {
                JOptionPane.showMessageDialog(
                    window, 
                    "La clase no puede ser instanciada. El programa se cerrará."
                );
                System.exit(0);
            }
        }
        
        int numPlayers = inputNumberOfPlayers(window);
        for (int i = 0; i < numPlayers; i++) {
            String name = JOptionPane.showInputDialog(
                window, 
                "Por favor ingresa el nombre del jugador " + (i + 1)
            );
            if (name.equals("") || name.trim().isEmpty()) {
                MAIN_CONTROLLER.getPlayer(i).setName("Jugador " + (i + 1));
            } else {
                MAIN_CONTROLLER.getPlayer(i).setName(name);
            }
        }
        window.setupGameBoard(MAIN_CONTROLLER.getGameBoard());
        window.setVisible(true);
        MAIN_CONTROLLER.setGUI(window);
        MAIN_CONTROLLER.startGame();
    }
}
