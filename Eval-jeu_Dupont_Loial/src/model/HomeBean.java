package model;

import bo.Partie;
import bo.User;
import dal.DAOFactory;
import dal.PartieDAOJDBC;
import dal.UserDAOJDBC;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HomeBean implements Serializable {

    private static final String ATT_SESSION = "homeBean";

    private boolean scorePerso;
    private String valBtStart;
    private String errResult;
    private List<Partie> listTop;
    private List<Partie> listTopPerso;

    private HomeBean(){
        listTop = new ArrayList<>();
        listTopPerso = new ArrayList<>();
        valBtStart = "Démarrer le questionnaire";
        scorePerso = false;
    }

    public static HomeBean getInstence(HttpServletRequest req ) {
        HttpSession session = req.getSession(true);
        HomeBean model = (HomeBean) session.getAttribute( ATT_SESSION );
        if (model == null) {
            model = new HomeBean();
            session.setAttribute(ATT_SESSION, model);
        }
        model.setErrResult("");
        return model;
    }

    public void updateListTopPlayer() {
        PartieDAOJDBC dao = (PartieDAOJDBC) DAOFactory.getPartieDAO();
        listTop.clear();
        try {
            listTop = dao.getTopParty();
        } catch (SQLException e) {
            e.printStackTrace();
            errResult = "Une erreur est survenue !";
        }
    }

    public void updateListTopPlayerByUser(HttpServletRequest req ) {
        LoginBean loginModel = LoginBean.getInstence( req );
        PartieDAOJDBC dao = (PartieDAOJDBC) DAOFactory.getPartieDAO();
        listTopPerso.clear();
        try {
            listTopPerso = dao.getTopParty(loginModel.getCurUser());
        } catch (SQLException e) {
            e.printStackTrace();
            errResult = "Une erreur est survenue !";
        }
    }

    public String getErrResult() { return errResult; }

    public void setErrResult(String errResult) {
        this.errResult = errResult;
    }

    public List<Partie> getListTop() { return listTop; }
    public List<Partie> getListTopPerso() { return listTopPerso; }

    public int getListTopSize() { return listTop.size(); }
    public int getListTopPersoSize() { return listTopPerso.size(); }

    public String getValBtStart() { return valBtStart; }

    public boolean isScorePerso() { return scorePerso; }

    public void setScorePerso(boolean scorePerso) {
        this.scorePerso = scorePerso;
        if (scorePerso)
            valBtStart = "Recommencer une partie";
        else
            valBtStart = "Démarrer le questionnaire";
    }
}
