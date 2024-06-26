package tests.gameboards;

import monopoly.TableroDeJuego;
import monopoly.cells.PropiedadCelda;
import monopoly.enums.GrupoDeColores;

public class GameBoard14 extends TableroDeJuego {
    public GameBoard14() {
        super();
        
        PropiedadCelda blue1 = new PropiedadCelda();
        PropiedadCelda blue2 = new PropiedadCelda();
        PropiedadCelda blue3 = new PropiedadCelda();
        PropiedadCelda green1 = new PropiedadCelda();
        PropiedadCelda green2 = new PropiedadCelda();
        PropiedadCelda green3 = new PropiedadCelda();
        PropiedadCelda red1 = new PropiedadCelda();
        PropiedadCelda red2 = new PropiedadCelda();
        PropiedadCelda red3 = new PropiedadCelda();
        PropiedadCelda purple1 = new PropiedadCelda();
        PropiedadCelda purple2 = new PropiedadCelda();
        PropiedadCelda yellow1 = new PropiedadCelda();
        PropiedadCelda yellow2 = new PropiedadCelda();

        blue1.setName("Blue 1");
        blue1.setColorGroup(GrupoDeColores.BLUE);
        blue2.setName("Blue 2");
        blue2.setColorGroup(GrupoDeColores.BLUE);
        blue3.setName("Blue 3");
        blue3.setColorGroup(GrupoDeColores.BLUE);
        green1.setName("Green 1");
        green1.setColorGroup(GrupoDeColores.GREEN);
        green2.setName("Green 2");
        green2.setColorGroup(GrupoDeColores.GREEN);
        green3.setName("Green 3");
        green3.setColorGroup(GrupoDeColores.GREEN);
        red1.setName("Red 1");
        red1.setColorGroup(GrupoDeColores.RED);
        red2.setName("Red 2");
        red2.setColorGroup(GrupoDeColores.RED);
        red3.setName("Red 3");
        red3.setColorGroup(GrupoDeColores.RED);
        purple1.setName("Purple 1");
        purple1.setColorGroup(GrupoDeColores.PURPLE);
        purple2.setName("Purple 2");		
        purple2.setColorGroup(GrupoDeColores.PURPLE);
        yellow1.setName("Yellow 1");
        yellow1.setColorGroup(GrupoDeColores.YELLOW);
        yellow2.setName("Yellow 2");
        yellow2.setColorGroup(GrupoDeColores.YELLOW);
		
        blue1.setPrice(100);
        blue2.setPrice(100);
        blue3.setPrice(120);
        green1.setPrice(200);
        green2.setPrice(240);
        green3.setPrice(260);
        red1.setPrice(300);
        red2.setPrice(300);
        red3.setPrice(320);
        purple1.setPrice(340);
        purple2.setPrice(360);
        yellow1.setPrice(400);
        yellow2.setPrice(420);
		

        blue1.setRent(10);
        blue2.setRent(10);
        blue3.setRent(12);
        green1.setRent(20);
        green2.setRent(24);
        green3.setRent(26);
        red1.setRent(30);
        red2.setRent(30);
        red3.setRent(32);
        purple1.setRent(34);
        purple2.setRent(36);
        yellow1.setRent(40);
        yellow2.setRent(42);

        blue1.setHousePrice(30);
        blue2.setHousePrice(30);
        blue3.setHousePrice(30);
        green1.setHousePrice(40);
        green2.setHousePrice(40);
        green3.setHousePrice(40);
        red1.setHousePrice(50);
        red2.setHousePrice(50);
        red3.setHousePrice(50);
        purple1.setHousePrice(60);
        purple2.setHousePrice(60);
        yellow1.setHousePrice(70);
        yellow2.setHousePrice(70);

        super.addCell(blue1);
        super.addCell(blue2);
        super.addCell(blue3);
        super.addCell(green1);
        super.addCell(green2);
        super.addCell(green3);
        super.addCell(red1);
        super.addCell(red2);
        super.addCell(red3);
        super.addCell(purple1);
        super.addCell(purple2);
        super.addCell(yellow1);
        super.addCell(yellow2);
    }
}
