package model;

import bo.Partie;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PartieBean {
    public static Partie getObject(ResultSet rs) {
        int id;
        try { id = rs.getInt("idpartie"); }
        catch (SQLException e) { id = 0; }

        int score;
        try { score = rs.getInt("score"); }
        catch (SQLException e) { score = 0; }

        int date;
        try { date = rs.getInt("date"); }
        catch (SQLException e) { date = 0; }
        return new Partie(id, score, "", null);
    }
}
