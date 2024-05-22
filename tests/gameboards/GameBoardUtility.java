package tests.gameboards;

import monopoly.TableroDeJuego;
import monopoly.cells.CeldaEstacionamiento;
import monopoly.cells.IrCeldaDeCarcel;
import monopoly.cells.CeldaDeCarcel;
import monopoly.cells.PropiedadCelda;
import monopoly.cells.CeldaDeTren;
import monopoly.cells.CeldaDeUtilidad;
import monopoly.enums.GrupoDeColores;

public class GameBoardUtility extends TableroDeJuego {
    public GameBoardUtility() {
        super();
        
        int railroadBaseRent = 25;
        int railroadPrice = 200;
	
        PropiedadCelda blue1 = new PropiedadCelda();
        PropiedadCelda blue2 = new PropiedadCelda();
        PropiedadCelda green1 = new PropiedadCelda();
        PropiedadCelda green2 = new PropiedadCelda();
        CeldaDeCarcel jail = new CeldaDeCarcel();
        IrCeldaDeCarcel goToJail = new IrCeldaDeCarcel();
        CeldaEstacionamiento freeParking = new CeldaEstacionamiento();
        CeldaDeTren railroad1 = new CeldaDeTren();
        CeldaDeTren railroad2 = new CeldaDeTren();
        CeldaDeUtilidad utility1 = new CeldaDeUtilidad();
        CeldaDeUtilidad utility2 = new CeldaDeUtilidad();

        blue1.setName("Blue 1");
        blue2.setName("Blue 2");
        green1.setName("Green 1");
        green2.setName("Green 2");

        blue1.setColorGroup(GrupoDeColores.BLUE);
        blue2.setColorGroup(GrupoDeColores.BLUE);
        green1.setColorGroup(GrupoDeColores.GREEN);
        green2.setColorGroup(GrupoDeColores.GREEN);

        railroad1.setName("Ferrocarril A");
        railroad1.setPrice(railroadPrice);
        railroad1.setBaseRent(railroadBaseRent);

        railroad2.setName("Ferrocarril C");
        railroad2.setPrice(railroadPrice);
        railroad2.setBaseRent(railroadBaseRent);


        utility1.setName("Utility 1");
        utility2.setName("Utility 2");
        CeldaDeUtilidad.setPrice(150);

        blue1.setPrice(100);
        blue2.setPrice(100);
        green1.setPrice(200);
        green2.setPrice(240);

        blue1.setRent(10);
        blue2.setRent(10);
        green1.setRent(20);
        green2.setRent(20);

        blue1.setHousePrice(50);
        blue2.setHousePrice(50);
        green1.setHousePrice(70);
        green2.setHousePrice(70);

        addCell(railroad1);
        super.addCell(blue1);
        addCell(jail);
        addCell(utility1);
        super.addCell(blue2);
        addCell(freeParking);
        super.addCell(green1);
        addCell(railroad2);
        addCell(goToJail);
        super.addCell(green2);
        addCell(utility2);
    }
}
