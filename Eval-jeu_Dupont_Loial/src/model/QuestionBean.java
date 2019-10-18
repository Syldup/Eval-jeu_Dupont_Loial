package model;

import bo.Calcul;
import bo.Equation;
import bo.Partie;
import bo.User;
import dal.CalculDAOJDBC;
import dal.DAOFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

public class QuestionBean implements Serializable {

	private static final String ATT_SESSION = "questBean";
	private static final String FORM_FIELD_RESP = "form-reponse";

	private User curUser;
	private Partie curPartie = null;
	private Calcul curCalcul = null;

	private QuestionBean( User curUser ) {
		this.curUser = curUser;
	}

	public static QuestionBean getInstence(HttpServletRequest req ) {
		HttpSession session = req.getSession(true);
		QuestionBean model = (QuestionBean) session.getAttribute( ATT_SESSION );
		if (model == null) {
			LoginBean loginModel = LoginBean.getInstence( req );
			model = new QuestionBean(loginModel.getCurUser());
			session.setAttribute(ATT_SESSION, model);
		}
		return model;
	}

	public void initPartie() {
		if (curPartie == null) {
			curPartie = new Partie(curUser);
			DAOFactory.getPartieDAO().create(curPartie);
		}
	}

	public void initCalcul() {
		Equation exp = new Equation();
		exp.createExpretion();
		curCalcul = new Calcul(exp.toString(), exp.calculResult(), curPartie);
	}

	public void processForm( HttpServletRequest req ) {
		curCalcul.setReponce(req.getParameter( FORM_FIELD_RESP ));
		curPartie.addCalcul(curCalcul);
		((CalculDAOJDBC) DAOFactory.getCalculDAO()).save(curCalcul);
	}

	public int getCalculCount() {
		if (curPartie == null) return 0;
		return curPartie.getCalculs().size();
	}

	public String getCalcul() {
		if (curCalcul == null) return "?";
		return curCalcul.getCalcul();
	}

	public String getResultat() {
		if (curCalcul == null) return "?";
		return curCalcul.getResultat();
	}

	public String getFormFieldResp() {
		return FORM_FIELD_RESP;
	}
}
