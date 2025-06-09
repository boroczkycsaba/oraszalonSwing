package hu.oraszalon.inditas;

import hu.oraszalon.ablak.OraSzalonKepernyo;
import hu.oraszalon.adatbazis.SQLKapcsolat;
import hu.oraszalon.adatbazis.OraBoltDDL;
import hu.oraszalon.adatbazis.OraBoltDML;
import java.sql.SQLException;

public class Start {

    public static void main(String[] args) {
        try {
            SQLKapcsolat.csatlakozas();
            OraBoltDDL oraBoltDDL = new OraBoltDDL(SQLKapcsolat.getConnection());
            oraBoltDDL.tablaLetrehozas();
            OraBoltDML.oraSzalonTipusFeltoltes();
            java.awt.EventQueue.invokeLater(() -> new OraSzalonKepernyo().setVisible(true));
        } catch (SQLException e) {
            System.err.println("SQL hiba történt! " + e.getMessage());
        } finally {
            SQLKapcsolat.kapcsolatBezarasa();
        }
    }
}
