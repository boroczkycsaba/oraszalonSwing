package hu.oraszalon.adatbazis;

import hu.oraszalon.entity.OraSzalon;
import hu.oraszalon.entity.OraSzalonTipusEnum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author csaba
 */
public class OraBoltDML {

    private OraBoltDML() {
    }

    public static void oraSzalonTipusFeltoltes() throws SQLException {
        final Connection connection = SQLKapcsolat.getConnection();
        List<String> oraBoltEnumLista = Stream.of(OraSzalonTipusEnum.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        PreparedStatement ps = connection.prepareStatement("INSERT INTO " + OraBoltDDL.oraSzalonTipusTablaNev + "(TipusNev) VALUES(?)");
        for (String oraBoltEnumErtek : oraBoltEnumLista) {
            ps.setString(1, oraBoltEnumErtek);
            ps.executeUpdate();
        }
    }

    public static void oraSzalonMentes(final OraSzalon oraSzalon) throws SQLException {
        SQLKapcsolat.csatlakozas();
        final Connection connectionMentes = SQLKapcsolat.getConnection();
        PreparedStatement ps = null;
        ps = connectionMentes.prepareStatement("SELECT TipusID FROM " + OraBoltDDL.oraSzalonTipusTablaNev + " WHERE TipusNev = ?");
        ps.setString(1, oraSzalon.getTipus().name());
        ResultSet res = ps.executeQuery();
        int tipusId = 0;
        while (res.next()) {
            tipusId = res.getInt("TipusID");
        }
        ps = connectionMentes.prepareStatement("INSERT INTO " + OraBoltDDL.oraSzalonTablaNev + "(Megnevezes, TipusID, AR, VizalloE) VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, oraSzalon.getMegnevezes());
        ps.setInt(2, tipusId);
        ps.setInt(3, oraSzalon.getAr());
        ps.setInt(4, oraSzalon.isVizalloe() ? 1 : 0);
        ps.executeUpdate();

        ResultSet resId = ps.getGeneratedKeys();

        if (resId.next()) {
            oraSzalon.setOraszalonId(resId.getInt(1));
        }
    }

    public static OraSzalon oraSzalonAdatLekerdezesSQL(final int id) throws SQLException {
        SQLKapcsolat.csatlakozas();
        final Connection connectionLekerdezes = SQLKapcsolat.getConnection();
        final OraSzalon oraSzalon = new OraSzalon();

        PreparedStatement ps = connectionLekerdezes.prepareStatement("SELECT * FROM " + OraBoltDDL.oraSzalonTablaNev + " WHERE OraszalonID = ?");
        ps.setInt(1, id);
        ResultSet res = ps.executeQuery();

        while (res.next()) {
            oraSzalon.setMegnevezes(res.getString("Megnevezes"));
            oraSzalon.setAr(res.getInt("AR"));
            oraSzalon.setVizalloe((res.getInt("VizalloE") != 0));
        }

        return oraSzalon;
    }

    public static List<OraSzalon> oraSzalonAdatokLekerdezesSQL() throws SQLException {
        SQLKapcsolat.csatlakozas();
        final Connection connectionLekerdezes = SQLKapcsolat.getConnection();

        List<OraSzalon> oraSzalonList = new ArrayList<>();
        OraSzalon oraSzalon = null;

        PreparedStatement ps = connectionLekerdezes.prepareStatement("SELECT * FROM " + OraBoltDDL.oraSzalonTablaNev + "");
        ResultSet res = ps.executeQuery();

        while (res.next()) {
            oraSzalon = new OraSzalon();
            oraSzalon.setOraszalonId(res.getInt("OraszalonID"));
            oraSzalon.setMegnevezes(res.getString("Megnevezes"));
            oraSzalonList.add(oraSzalon);
        }

        return oraSzalonList;
    }
}
