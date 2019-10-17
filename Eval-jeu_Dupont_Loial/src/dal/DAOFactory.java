package dal;

import bo.Calcul;
import bo.Partie;
import bo.User;

import javax.servlet.ServletContext;

public class DAOFactory {
	
	private static String mode;
	private static String dbUrl = "jdbc:mysql://localhost:3306/dbEvalJeu?useSSL=false&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
	private static String dbLogin = "root";
	private static String dbPwd = "";
	
	
	public static void init( ServletContext context ) throws ClassNotFoundException {
		mode = context.getInitParameter( "DS_MODE" );
		switch ( mode ) {
			case "JDBC" :
				Class.forName( context.getInitParameter( "DB_DRIVER" ) );/*
				dbUrl = context.getInitParameter( "DB_URL" );
				dbLogin = context.getInitParameter( "DB_LOGIN" );
				dbPwd = context.getInitParameter( "DB_PWD" );*/
				break;
			default:
				//TODO
		}
	}

	public static IDAO<Integer, User> getUserDAO() {
		IDAO<Integer, User> dao = null;
		switch ( mode ) {
			case "JDBC" :
				dao = new UserDAOJDBC(dbUrl, dbLogin, dbPwd);
				break;
			default:
				//TODO
		}
		return dao;
	}

	public static IDAO<Integer, Partie> getPartieDAO() {
		IDAO<Integer, Partie> dao = null;
		switch ( mode ) {
			case "JDBC" :
				dao = new PartieDAOJDBC(dbUrl, dbLogin, dbPwd);
				break;
			default:
				//TODO
		}
		return dao;
	}

	public static IDAO<Integer, Calcul> getCalculDAO() {
		IDAO<Integer, Calcul> dao = null;
		switch ( mode ) {
			case "JDBC" :
				dao = new CalculDAOJDBC(dbUrl, dbLogin, dbPwd);
				break;
			default:
				//TODO
		}
		return dao;
	}
	
	
}
