package model;

import bo.Calcul;
import bo.Partie;
import bo.User;
import dal.DAOFactory;
import model.UserBean;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PartieBean {
    public static final String ATT_PARTIE_SESSION = "curPartie";

    public static Partie getPartieFromSession(HttpServletRequest req) {
        HttpSession session = req.getSession(true);

        if (session.getAttribute( ATT_PARTIE_SESSION ) == null) {
            User user = (User) session.getAttribute( LoginBean.ATT_AUTH_SESSION );
            session.setAttribute( ATT_PARTIE_SESSION , new Partie(user));
        }
        return (Partie) session.getAttribute( ATT_PARTIE_SESSION );
    }

    public static void save(Partie partie) {
        if (partie.getId() == 0)
            DAOFactory.getPartieDAO().create(partie);
        else
            DAOFactory.getPartieDAO().update(partie);
    }

    public static Partie getFromResultSet(ResultSet rs) {
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
