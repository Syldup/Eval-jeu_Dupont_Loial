package bo;

public class Calcul {
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
    }

    public Calcul(String calcul, String resultat, String reponce, String date, Partie partie) {
        this.calcul = calcul;
        this.resultat = resultat;
        this.reponce = reponce;
        this.date = date;
        this.partie = partie;
    }

    public Calcul(int id, String calcul, String resultat, String reponce, String date, Partie partie) {
        this.id = id;
        this.calcul = calcul;
        this.resultat = resultat;
        this.reponce = reponce;
        this.date = date;
        this.partie = partie;
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
