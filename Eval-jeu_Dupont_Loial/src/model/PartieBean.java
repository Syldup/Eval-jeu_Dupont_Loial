package model;

import bo.Calcul;
import bo.Partie;
import bo.User;
import model.UserBean;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PartieBean {

    public PartieBean() {}

    private  Partie partie;

    public Partie getPartie() {return partie;}

    public void setPartie(Partie partie) {this.partie = partie;}

    public void createNewPartie(HttpServletRequest request) {

        int id;
        try {
            id = Integer.parseInt( request.getParameter( "form-id" ) );
        } catch ( Exception e ) {
            id = -1;
        }

        int score=0;
        String date=new Date().toString();
        User user =new User();
        List<Calcul> listcal=new ArrayList<>();
    }

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
