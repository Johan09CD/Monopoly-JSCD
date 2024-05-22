package monopoly.gameboards;

import monopoly.TableroDeJuego;
import monopoly.cards.TarjetaDeCarcel;
import monopoly.cards.CartaDeDinero;
import monopoly.cards.CartaDeMoverJug;
import monopoly.cells.TarjetaDeCeldas;
import monopoly.cells.CeldaEstacionamiento;
import monopoly.cells.IrCeldaDeCarcel;
import monopoly.cells.CeldaDeCarcel;
import monopoly.cells.PropiedadCelda;
import monopoly.cells.CeldaDeTren;
import monopoly.cells.CeldaDeUtilidad;
import monopoly.enums.TiposDeCartas;
import monopoly.enums.GrupoDeColores;

public class TableroDeJuegoDef extends TableroDeJuego {
    public TableroDeJuegoDef() {
        super();
        
        int rrBaseRent = 25;
        int rrPrice = 200;
        
        PropiedadCelda dp1 = new PropiedadCelda();
        TarjetaDeCeldas cc1 = new TarjetaDeCeldas(TiposDeCartas.CC, "Comunidad de cofres 1");
        PropiedadCelda dp2 = new PropiedadCelda();
        PropiedadCelda dp3 = new PropiedadCelda();
        CeldaDeTren rr1 = new CeldaDeTren();
        PropiedadCelda lb1 = new PropiedadCelda();
        TarjetaDeCeldas c1 = new TarjetaDeCeldas(TiposDeCartas.CHANCE, "Chance 1");
        PropiedadCelda lb2 = new PropiedadCelda();
        PropiedadCelda lb3 = new PropiedadCelda();
        CeldaDeCarcel jail = new CeldaDeCarcel();
        PropiedadCelda p1 = new PropiedadCelda();
        CeldaDeUtilidad u1 = new CeldaDeUtilidad();
        PropiedadCelda p2 = new PropiedadCelda();
        PropiedadCelda p3 = new PropiedadCelda();
        CeldaDeTren rr2 = new CeldaDeTren();
        PropiedadCelda o1 = new PropiedadCelda();
        TarjetaDeCeldas cc2 = new TarjetaDeCeldas(TiposDeCartas.CC, "Comunidad de cofres 2");
        PropiedadCelda o2 = new PropiedadCelda();
        PropiedadCelda o3 = new PropiedadCelda();
        CeldaEstacionamiento fp = new CeldaEstacionamiento();
        PropiedadCelda r1 = new PropiedadCelda();
        TarjetaDeCeldas c2 = new TarjetaDeCeldas(TiposDeCartas.CHANCE, "Chance 2");
        PropiedadCelda r2 = new PropiedadCelda();
        PropiedadCelda r3 = new PropiedadCelda();
        CeldaDeTren rr3 = new CeldaDeTren();
        PropiedadCelda y1 = new PropiedadCelda();
        PropiedadCelda y2 = new PropiedadCelda();
        CeldaDeUtilidad u2 = new CeldaDeUtilidad();
        PropiedadCelda y3 = new PropiedadCelda();
        IrCeldaDeCarcel goToJail = new IrCeldaDeCarcel();
        PropiedadCelda g1 = new PropiedadCelda();
        PropiedadCelda g2 = new PropiedadCelda();
        TarjetaDeCeldas cc3 = new TarjetaDeCeldas(TiposDeCartas.CC, "Comunidad de cofres 3");
        PropiedadCelda g3 = new PropiedadCelda();
        CeldaDeTren rr4 = new CeldaDeTren();
        TarjetaDeCeldas c3 = new TarjetaDeCeldas(TiposDeCartas.CHANCE, "Chance 3");
        
        PropiedadCelda db1 = new PropiedadCelda();
        PropiedadCelda db2 = new PropiedadCelda();
        PropiedadCelda db3 = new PropiedadCelda();
        
        dp1.setPrice(60);
        dp1.setColorGroup(GrupoDeColores.PURPLE);
        dp1.setHousePrice(50);
        dp1.setName("Avenida Mediterráneo");
        dp1.setRent(2);

        dp2.setPrice(60);
        dp2.setColorGroup(GrupoDeColores.PURPLE);
        dp2.setHousePrice(50);
        dp2.setName("Avenida Báltica");
        dp2.setRent(4);

        dp3.setPrice(60);
        dp3.setColorGroup(GrupoDeColores.PURPLE);
        dp3.setHousePrice(50);
        dp3.setName("Avenida Sarah");
        dp3.setRent(4);

        lb1.setPrice(100);
        lb1.setColorGroup(GrupoDeColores.TEAL);
        lb1.setHousePrice(50);
        lb1.setName("Avenida Oriental");
        lb1.setRent(6);

        lb2.setPrice(100);
        lb2.setColorGroup(GrupoDeColores.TEAL);
        lb2.setHousePrice(50);
        lb2.setName("Avenida Vermont");
        lb2.setRent(6);

        lb3.setPrice(120);
        lb3.setColorGroup(GrupoDeColores.TEAL);
        lb3.setHousePrice(50);
        lb3.setName("Avenida Connecticut");
        lb3.setRent(8);

        p1.setPrice(140);
        p1.setColorGroup(GrupoDeColores.FUCHSIA);
        p1.setHousePrice(100);
        p1.setName("Plaza St. Charles");
        p1.setRent(10);

        p2.setPrice(140);
        p2.setColorGroup(GrupoDeColores.FUCHSIA);
        p2.setHousePrice(100);
        p2.setName("Avenida States");
        p2.setRent(10);

        p3.setPrice(160);
        p3.setColorGroup(GrupoDeColores.FUCHSIA);
        p3.setHousePrice(100);
        p3.setName("Avenida Virginia");
        p3.setRent(12);

        o1.setPrice(180);
        o1.setColorGroup(GrupoDeColores.MAROON);
        o1.setHousePrice(100);
        o1.setName("Avenida St. James");
        o1.setRent(14);

        o2.setPrice(180);
        o2.setColorGroup(GrupoDeColores.MAROON);
        o2.setHousePrice(100);
        o2.setName("Avenida Tennessee");
        o2.setRent(14);

        o3.setPrice(200);
        o3.setColorGroup(GrupoDeColores.MAROON);
        o3.setHousePrice(100);
        o3.setName("Avenida New York");
        o3.setRent(16);

        r1.setPrice(220);
        r1.setColorGroup(GrupoDeColores.RED);
        r1.setHousePrice(150);
        r1.setName("Avenida Kentucky");
        r1.setRent(18);

        r2.setPrice(220);
        r2.setColorGroup(GrupoDeColores.RED);
        r2.setHousePrice(150);
        r2.setName("Avenida Indiana");
        r2.setRent(18);

        r3.setPrice(240);
        r3.setColorGroup(GrupoDeColores.RED);
        r3.setHousePrice(150);
        r3.setName("Avenida Illinois");
        r3.setRent(20);

        y1.setPrice(260);
        y1.setColorGroup(GrupoDeColores.ORANGE);
        y1.setHousePrice(150);
        y1.setName("Avenida Atlántico");
        y1.setRent(22);

        y2.setPrice(260);
        y2.setColorGroup(GrupoDeColores.ORANGE);
        y2.setHousePrice(150);
        y2.setName("Avenida Ventnor");
        y2.setRent(22);

        y3.setPrice(280);
        y3.setColorGroup(GrupoDeColores.ORANGE);
        y3.setHousePrice(150);
        y3.setName("Jardines Marvin");
        y3.setRent(24);

        g1.setPrice(300);
        g1.setColorGroup(GrupoDeColores.GREEN);
        g1.setHousePrice(200);
        g1.setName("Avenida Pacífico");
        g1.setRent(26);

        g2.setPrice(300);
        g2.setColorGroup(GrupoDeColores.GREEN);
        g2.setHousePrice(200);
        g2.setName("Avenida Carolina del Norte");
        g2.setRent(26);

        g3.setPrice(320);
        g3.setColorGroup(GrupoDeColores.GREEN);
        g3.setHousePrice(200);
        g3.setName("Avenida Pennsylvania");
        g3.setRent(28);

        db1.setPrice(350);
        db1.setColorGroup(GrupoDeColores.BLUE);
        db1.setHousePrice(200);
        db1.setName("Parque");
        db1.setRent(35);

        db2.setPrice(350);
        db2.setColorGroup(GrupoDeColores.BLUE);
        db2.setHousePrice(200);
        db2.setName("Derecho");
        db2.setRent(35);

        db3.setPrice(400);
        db3.setColorGroup(GrupoDeColores.BLUE);
        db3.setHousePrice(200);
        db3.setName("Paseo");
        db3.setRent(50);

        rr1.setName("Ferrocarril Reading");
        rr1.setBaseRent(rrBaseRent);
        rr1.setPrice(rrPrice);

        rr2.setName("Ferrocarril de Pensilvania");
        rr2.setBaseRent(rrBaseRent);
        rr2.setPrice(rrPrice);

        rr3.setName("Ferrocarril B. & O.");
        rr3.setBaseRent(rrBaseRent);
        rr3.setPrice(rrPrice);

        rr4.setName("Línea Corta");
        rr4.setBaseRent(rrBaseRent);

        CeldaDeUtilidad.setPrice(150);

        u1.setName("Compañía Eléctrica");
        u2.setName("Compañía de Agua");

        
        super.addCell(dp1);
        super.addCell(cc1);
        super.addCell(dp2);
        super.addCell(dp3);
        super.addCell(rr1);
        super.addCell(lb1);
        super.addCell(c1);
        super.addCell(lb2);
        super.addCell(lb3);
        super.addCell(jail);
        super.addCell(p1);
        super.addCell(u1);
        super.addCell(p2);
        super.addCell(p3);
        super.addCell(rr2);
        super.addCell(o1);
        super.addCell(cc2);
        super.addCell(o2);
        super.addCell(o3);
        super.addCell(fp);
        super.addCell(r1);
        super.addCell(c2);
        super.addCell(r2);
        super.addCell(r3);
        super.addCell(rr3);
        super.addCell(y1);
        super.addCell(y2);
        super.addCell(u2);
        super.addCell(y3);
        super.addCell(goToJail);
        super.addCell(g1);
        super.addCell(g2);
        super.addCell(cc3);
        super.addCell(g3);
        super.addCell(rr4);
        super.addCell(c3);
        super.addCell(db1);
        super.addCell(db2);
        super.addCell(db3);
        
        super.addCard(new CartaDeDinero("Ganar $50", 50, TiposDeCartas.CC));
        super.addCard(new CartaDeDinero("Ganar $20", 20, TiposDeCartas.CC));
        super.addCard(new CartaDeDinero("Ganar $10", 10, TiposDeCartas.CC));
        super.addCard(new CartaDeDinero("Perder $100", -100, TiposDeCartas.CC));
        super.addCard(new CartaDeDinero("Perder $50", -50, TiposDeCartas.CC));
        super.addCard(new TarjetaDeCarcel(TiposDeCartas.CC));
        super.addCard(new CartaDeMoverJug("Plaza St. Charles", TiposDeCartas.CC));
        super.addCard(new CartaDeMoverJug("Paseo Tablado", TiposDeCartas.CC));

        super.addCard(new CartaDeDinero("Ganar $50", 50, TiposDeCartas.CHANCE));
        super.addCard(new CartaDeDinero("Ganar $20", 20, TiposDeCartas.CHANCE));
        super.addCard(new CartaDeDinero("Ganar $10", 10, TiposDeCartas.CHANCE));
        super.addCard(new CartaDeDinero("Perder $100", -100, TiposDeCartas.CHANCE));
        super.addCard(new CartaDeDinero("Perder $50", -50, TiposDeCartas.CHANCE));
        super.addCard(new TarjetaDeCarcel(TiposDeCartas.CHANCE));
        super.addCard(new CartaDeMoverJug("Avenida Illinois", TiposDeCartas.CHANCE));

        super.shuffleCards();
    }
}
