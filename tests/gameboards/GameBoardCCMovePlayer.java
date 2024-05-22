package tests.gameboards;

import monopoly.Tarjeta;
import monopoly.TableroDeJuego;
import monopoly.cards.CartaDeMoverJug;
import monopoly.cells.TarjetaDeCeldas;
import monopoly.cells.CeldaDeCarcel;
import monopoly.cells.PropiedadCelda;
import monopoly.enums.TiposDeCartas;
import monopoly.enums.GrupoDeColores;

public class GameBoardCCMovePlayer extends TableroDeJuego {
    public GameBoardCCMovePlayer() {
        super();

        PropiedadCelda blue1 = new PropiedadCelda();
        PropiedadCelda blue2 = new PropiedadCelda();
        TarjetaDeCeldas cc1 = new TarjetaDeCeldas(TiposDeCartas.CC, "Cofre de la comunidad 1");
        CeldaDeCarcel jail = new CeldaDeCarcel();
        TarjetaDeCeldas chance1 = new TarjetaDeCeldas(TiposDeCartas.CHANCE, "Chance 1");
        
        Tarjeta ccCard1 = new CartaDeMoverJug("Blue 1" , TiposDeCartas.CC);
        Tarjeta ccCard2 = new CartaDeMoverJug("Blue 2", TiposDeCartas.CC);
		
        blue1.setName("Blue 1");
        blue2.setName("Blue 2");

        blue1.setColorGroup(GrupoDeColores.BLUE);
        blue2.setColorGroup(GrupoDeColores.BLUE);

        blue1.setPrice(100);
        blue2.setPrice(100);

        blue1.setRent(10);
        blue2.setRent(10);

        blue1.setHousePrice(50);
        blue2.setHousePrice(50);

        super.addCard(ccCard1);
        super.addCard(ccCard2);

        super.addCell(blue1);
        addCell(cc1);
        addCell(jail);
        super.addCell(blue2);
        addCell(chance1);
    }
}
