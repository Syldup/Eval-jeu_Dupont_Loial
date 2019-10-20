package bo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Calcul implements Serializable {
    private int id = 0;
    private String calcul;
    private String resultat;
    private String reponce;
    private String date;
    private Partie partie;

    public Calcul(String calcul, String resultat, Partie partie) {
        this.calcul = calcul;
        this.resultat = resultat;
        this.partie = partie;
        this.reponce = null;
    }

    public Calcul(int id, String calcul, String resultat, String reponce, String date, Partie partie) {
        this.id = id;
        this.calcul = calcul;
        this.resultat = resultat;
        this.reponce = reponce;
        this.date = date;
        this.partie = partie;
    }

    public Calcul(ResultSet rs) {
        try { id = rs.getInt("idcalcul"); }
        catch (SQLException e) { id = 0; }

        try { calcul = rs.getString("calcul"); }
        catch (SQLException e) { calcul = ""; }

        try { resultat = rs.getString("resultat"); }
        catch (SQLException e) { resultat = ""; }

        try { reponce = rs.getString("reponce"); }
        catch (SQLException e) { reponce = ""; }

        try { date = rs.getString("date"); }
        catch (SQLException e) { date = ""; }
    }

    public boolean isCorrect() {
        long factor = (long) Math.pow(10, 2);
        Double rep;
        try { rep = (double) (Math.round(Double.parseDouble(reponce)*factor))/factor; }
        catch (Exception e) { rep = null; }

        Double res;
        try { res = (double) (Math.round(Double.parseDouble(resultat)*factor))/factor; }
        catch (Exception e) { res = null; }

        if (res == null || rep == null)
            return Objects.equals(res, rep);
        return rep.equals(res);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id;}

    public String getCalcul() { return calcul;  }
    public void setCalcul(String calcul) { this.calcul = calcul;  }

    public String getResultat() { return resultat;  }
    public void setResultat(String resultat) { this.resultat = resultat;  }

    public String getReponce() { return reponce; }
    public void setReponce(String reponce) { this.reponce = reponce;  }

    public String getDate() { return date;  }
    public void setDate(String date) { this.date = date;  }

    public Partie getPartie() { return partie; }
    public void setPartie(Partie partie) {  this.partie = partie;   }
}
