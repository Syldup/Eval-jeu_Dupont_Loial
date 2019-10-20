package model;

import bo.User;
import dal.DAOFactory;
import dal.IDAO;
import dal.UserDAOJDBC;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HomeBean implements Serializable {

private HomeBean(){}

private static final String DISPPLAYER = "topListe";
private static final String ATT_SESSION = "homebean";
private static final String PAGE_QUEST = "/question";
private static final String BUTTON_FORM="homeform";



    public static HomeBean getInstence(HttpServletRequest req ) {
        HttpSession session = req.getSession(true);
        HomeBean model = (HomeBean) session.getAttribute( ATT_SESSION );
        if (model == null) {
            LoginBean loginModel = LoginBean.getInstence( req );
            model = new HomeBean();
            session.setAttribute(ATT_SESSION, model);
        }
        return model;
    }


public void dispListTopPlayer(HttpServletRequest request) throws SQLException {

    UserDAOJDBC dao = ( UserDAOJDBC ) DAOFactory.getUserDAO();
    List<User> listtop=(ArrayList) request.getSession().getAttribute(DISPPLAYER);
    listtop=dao.topPlayer();
    request.getSession().setAttribute(DISPPLAYER,listtop);
}

}
