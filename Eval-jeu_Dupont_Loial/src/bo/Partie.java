package bo;

import java.util.List;

public class Partie {
    private int id = 0;
    private int score;
    private String date;
    private User user;
    private List<Calcul> calculs;

    public Partie(int id, int score, String date, User user) {
        this.id = id;
        this.score = score;
        this.date = date;
        this.user = user;
    }

    public Partie(int score, String date, User user) {
        this.score = score;
        this.date = date;
        this.user = user;
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

    public void setCalculs(List<Calcul> calculs) { this.calculs = calculs; }
}
