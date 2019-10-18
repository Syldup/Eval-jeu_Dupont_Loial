package bo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Partie {
    private int id = 0;
    private int score = 0;
    private String date;
    private User user;
    private List<Calcul> calculs = new ArrayList<>();

    public Partie(User user) {
        this.user = user;
    }

    public Partie(int id, int score, String date, User user) {
        this.id = id;
        this.score = score;
        this.date = date;
        this.user = user;
    }

    public Partie(ResultSet rs) {
        try { id = rs.getInt("idpartie"); }
        catch (SQLException e) { id = 0; }

        try { score = rs.getInt("score"); }
        catch (SQLException e) { score = 0; }

        try { date = rs.getString("date"); }
        catch (SQLException e) { date = ""; }
    }

    public static Partie getInstance(ResultSet rs, User user) {
        Partie partie = new Partie( rs );
        if (partie.getId() == 0)
            return null;
        partie.setUser(user);
        return partie;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public List<Calcul> getCalculs() { return calculs; }
    public void addCalcul(Calcul calcul) { calculs.add(calcul); }
    public void setCalculs(List<Calcul> calculs) { this.calculs = calculs; }
}
