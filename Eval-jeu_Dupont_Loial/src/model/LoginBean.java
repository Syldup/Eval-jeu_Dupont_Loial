package model;

import bo.User;
import dal.DAOFactory;
import dal.UserDAOJDBC;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.sql.SQLException;

public class LoginBean implements Serializable {

	public static final String ATT_AUTH_SESSION = "isConntected";
	private static final String FORM_FIELD_LOGIN = "form-login";
	private static final String FORM_FIELD_PWD = "form-pwd";
	private static final String FORM_FIELD_CONF_PWD = "form-pwd2";
	
	private String login;
	private String authentResult;
	
	public LoginBean() {}
	
	public void authenticate( HttpServletRequest request ) {
		login = request.getParameter( FORM_FIELD_LOGIN );
		String pwd = request.getParameter( FORM_FIELD_PWD );
		UserDAOJDBC dao = ( UserDAOJDBC ) DAOFactory.getUserDAO();
		User user = null;
		try {
			user = dao.authenticate( login, pwd );
			
			if ( user != null ) {
				HttpSession session = request.getSession( true );
				session.setAttribute( ATT_AUTH_SESSION, user );
				authentResult = "Authentification réussie : Bienvenue " + login;
			} else {
				authentResult = "Authentification échouée !!!";
			}
		} catch ( SQLException e ) {
			System.out.println(e.getMessage());
			authentResult = "Authentification échouée : Pb de connexion à la base de données !!! ";
		}
	}

	public void suscribe(HttpServletRequest request){
		login = request.getParameter(FORM_FIELD_LOGIN);
		String pwd = request.getParameter(FORM_FIELD_PWD);
		String pwd2 = request.getParameter(FORM_FIELD_CONF_PWD);
		UserDAOJDBC dao=(UserDAOJDBC)DAOFactory.getUserDAO();
		User user=null;

		if(pwd.equals(pwd2) && user==null){
			try{
			    user = new User();
				user.setUsername(FORM_FIELD_LOGIN);
				user.setPassword(FORM_FIELD_PWD);
				dao.create(user);
                authentResult="Sa marche";
			}catch(Exception e){
			    e.printStackTrace();
                authentResult="Sa ne fonctionne pas";
			}
		}
	}
	
	public boolean isConnected( HttpServletRequest req ) {
		HttpSession session = req.getSession();
		User connectedUser = ( User ) session.getAttribute( ATT_AUTH_SESSION );
		return connectedUser != null;
	}

	public String getLogin() {
		return login;
	}

	public String getAuthentResult() {
		return authentResult;
	}
}
