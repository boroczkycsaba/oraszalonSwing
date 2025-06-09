package hu.oraszalon.adatbazis;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Böröczky Csaba
 */
public class OraBoltDDL {

    final Connection connection;
    
    final static String oraSzalonTipusTablaNev = "OraszalonTipusAdatok";
    final static String oraSzalonTablaNev = "OraszalonAdatok";
    private String oraSzalonTipusTablaLetrehozoSQL = null;
    private String oraSzalonTablaLetrehozoSQL = null;
    
    public OraBoltDDL(final Connection connection) {
        this.connection = connection;
        oraSzalonTipusTablaLetrehozoSQL = "create table " + oraSzalonTipusTablaNev + " (  TipusID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) , "
                + "TipusNev VARCHAR(200) NOT NULL, PRIMARY KEY (TipusID))";
        oraSzalonTablaLetrehozoSQL = "create table " + oraSzalonTablaNev + " (  OraszalonID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) , "
                + "Megnevezes VARCHAR(200) NOT NULL, TipusID int, AR INT NOT NULL, VizalloE BOOLEAN NOT NULL, PRIMARY KEY (OraszalonID),\n"
                + "    FOREIGN KEY (TipusID) REFERENCES " + oraSzalonTipusTablaNev + "(TipusID))";
    }

    public void tablaLetrehozas() throws SQLException {
        tablaLetrehozasaSQLAdatbazisban(oraSzalonTipusTablaLetrehozoSQL);
        tablaLetrehozasaSQLAdatbazisban(oraSzalonTablaLetrehozoSQL);
    }
    
    private void tablaLetrehozasaSQLAdatbazisban(final String tablaLetrehozoSQL) throws SQLException {
        Statement stmt = connection.createStatement();    
        stmt.executeUpdate(tablaLetrehozoSQL);
    }
}
