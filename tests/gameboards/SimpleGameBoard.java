package tests.gameboards;

import monopoly.TableroDeJuego;
import monopoly.cells.PropiedadCelda;
import monopoly.enums.GrupoDeColores;

public class SimpleGameBoard extends TableroDeJuego {
    public SimpleGameBoard() {
        super();
        
        PropiedadCelda blue1 = new PropiedadCelda();
        PropiedadCelda blue2 = new PropiedadCelda();
        PropiedadCelda blue3 = new PropiedadCelda();
        PropiedadCelda green1 = new PropiedadCelda();
        PropiedadCelda green2 = new PropiedadCelda();

        blue1.setName("Blue 1");
        blue2.setName("Blue 2");
        blue3.setName("Blue 3");
        green1.setName("Green 1");
        green2.setName("Green 2");

        blue1.setColorGroup(GrupoDeColores.BLUE);
        blue2.setColorGroup(GrupoDeColores.BLUE);
        blue3.setColorGroup(GrupoDeColores.BLUE);
        green1.setColorGroup(GrupoDeColores.GREEN);
        green2.setColorGroup(GrupoDeColores.GREEN);

        blue1.setPrice(100);
        blue2.setPrice(100);
        blue3.setPrice(120);
        green1.setPrice(200);
        green2.setPrice(240);

        blue1.setRent(10);
        blue2.setRent(10);
        blue3.setRent(10);
        green1.setRent(1600);
        green2.setRent(20);

        blue1.setHousePrice(50);
        blue2.setHousePrice(50);
        blue3.setHousePrice(50);
        green1.setHousePrice(70);
        green2.setHousePrice(70);

        super.addCell(blue1);
        super.addCell(blue2);
        super.addCell(blue3);
        super.addCell(green1);
        super.addCell(green2);
    }
}
