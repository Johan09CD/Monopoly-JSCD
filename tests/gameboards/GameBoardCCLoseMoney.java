package tests.gameboards;

import monopoly.Tarjeta;
import monopoly.TableroDeJuego;
import monopoly.cards.CartaDeDinero;
import monopoly.cells.TarjetaDeCeldas;
import monopoly.cells.CeldaDeCarcel;
import monopoly.cells.PropiedadCelda;
import monopoly.enums.TiposDeCartas;
import monopoly.enums.GrupoDeColores;

public class GameBoardCCLoseMoney extends TableroDeJuego {
    public GameBoardCCLoseMoney() {
        super();
	
        PropiedadCelda blue1 = new PropiedadCelda();
	PropiedadCelda blue2 = new PropiedadCelda();
        TarjetaDeCeldas cc1 = new TarjetaDeCeldas(TiposDeCartas.CC, "Cofre de la comunidad 1");
        CeldaDeCarcel jail = new CeldaDeCarcel();
        TarjetaDeCeldas chance1 = new TarjetaDeCeldas(TiposDeCartas.CHANCE, "Chance 1");
        
        Tarjeta ccCard1 = new CartaDeDinero("Pagas $20", -20, TiposDeCartas.CC);
		
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

        addCell(cc1);
        super.addCell(blue1);
        addCell(jail);
        super.addCell(blue2);
        addCell(chance1);	
    }
}
