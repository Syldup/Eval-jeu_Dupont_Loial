package model;

import bo.Calcul;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CalculBean {
    public static Calcul getObject(ResultSet rs) {
        int id;
        try { id = rs.getInt("idcalcul"); }
        catch (SQLException e) { id = 0; }

        String calcul;
        try { calcul = rs.getString("calcul"); }
        catch (SQLException e) { calcul = ""; }

        String resultat;
        try { resultat = rs.getString("resultat"); }
        catch (SQLException e) { resultat = ""; }

        String reponce;
        try { reponce = rs.getString("reponce"); }
        catch (SQLException e) { reponce = ""; }

        int date;
        try { date = rs.getInt("date"); }
        catch (SQLException e) { date = 0; }
        return new Calcul(id, calcul, resultat, reponce, "", null);
    }
}
