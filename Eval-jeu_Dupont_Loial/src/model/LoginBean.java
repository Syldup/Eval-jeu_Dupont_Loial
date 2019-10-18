package model;

import bo.User;
import dal.DAOFactory;
import dal.UserDAOJDBC;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

public class LoginBean implements Serializable {

	public static final String ATT_AUTH_SESSION = "isConntected";
	private static final String ATT_SESSION = "loginBean";
	private static final String FORM_FIELD_LOGIN = "form-login";
	private static final String FORM_FIELD_PWD = "form-pwd";
	private static final String FORM_FIELD_CONF_PWD = "form-pwd2";
	private static final String FORM_FIELD_TYPE = "form-type";

	private String login;
	private String pwd;
	private String authentResult;
	private User curUser;
	
	private LoginBean() {}

	public static LoginBean getInstence( HttpServletRequest req ) {
		HttpSession session = req.getSession(true);
		LoginBean model = (LoginBean) session.getAttribute( ATT_SESSION );
		if (model == null) {
			model = new LoginBean();
			session.setAttribute(ATT_SESSION, model);
		}
		return model;
	}
	
	public void processForm( HttpServletRequest req ) {
		String formType = req.getParameter( FORM_FIELD_TYPE );

		login = req.getParameter( FORM_FIELD_LOGIN );
		pwd = req.getParameter( FORM_FIELD_PWD );
		if (pwd == null || pwd == "")
			formType = "login";

		if (login == null || login == "")
			formType = "login";

		switch (req.getParameter( FORM_FIELD_TYPE )) {
			case "sign-up-form": suscribe(req); break;
			case "sign-in-form": authenticate(req); break;
			default:
				authentResult = "Le champ "+formType+" est vide !";
		}
	}

	private void authenticate( HttpServletRequest req ) {
		UserDAOJDBC dao = ( UserDAOJDBC ) DAOFactory.getUserDAO();
		try {
			curUser = dao.authenticate( login, pwd );

			if ( curUser != null )
				authentResult = "Bienvenue " + login;
			else authentResult = "Authentification échouée !!!";
		} catch ( Exception e ) {
			System.out.println(e.getMessage());
			authentResult = "Une erreur est survenue !";
		}
	}

	private void suscribe(HttpServletRequest req){
		String pwd = req.getParameter(FORM_FIELD_PWD);
		String pwd2 = req.getParameter(FORM_FIELD_CONF_PWD);

		if (pwd.equals(pwd2)) {
			try {
				curUser = new User(login, pwd);
				DAOFactory.getUserDAO().create(curUser);
				authentResult = "Bienvenue " + login + " !";
			} catch(Exception e){
			    e.printStackTrace();
				curUser = null;
          		authentResult = "Une erreur est survenue !";
			}
		} else authentResult = "Les deux mots de passe doivent être identique !";
	}

	public boolean isConnected() {
		return curUser != null;
	}

	public String getLogin() {
		return login;
	}
	public String getAuthentResult() {
		return authentResult;
	}
	public User getCurUser() { return curUser; }

	public String getFormFieldLogin() { return FORM_FIELD_LOGIN; }
	public String getFormFieldPwd() { return FORM_FIELD_PWD; }
	public String getFormFieldConfPwd() { return FORM_FIELD_CONF_PWD; }
	public String getFormFieldType() { return FORM_FIELD_TYPE; }

}
