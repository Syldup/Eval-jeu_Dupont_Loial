package model;

import bo.Calcul;
import bo.Equation;
import bo.Partie;
import dal.DAOFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CalculBean {
    public static final String ATT_CALCUL_SESSION = "curCalcul";
    public static final String FORM_FIELD_REPONCE = "form-reponce";

    public static Calcul getFromRequest(HttpServletRequest req ) {
        HttpSession session = req.getSession(true);
        Calcul calcul = ( Calcul ) session.getAttribute( ATT_CALCUL_SESSION );
        Partie partie = calcul.getPartie();

        calcul.setReponce(req.getParameter( FORM_FIELD_REPONCE ));
        partie.addCalcul(calcul);
        return calcul;
    }

    public static Calcul newCalcul(Partie partie) {
        Equation exp = new Equation();
        exp.createExpretion(5);
        return new Calcul(exp.toString(), exp.calculResult(), partie);
    }

    public static void save(Calcul calcul) {
        if (calcul.getId() == 0)
            DAOFactory.getCalculDAO().create(calcul);
        else
            DAOFactory.getCalculDAO().update(calcul);
    }

    public static Calcul getFromResultSet(ResultSet rs) {
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
