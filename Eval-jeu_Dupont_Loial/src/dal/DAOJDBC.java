package dal;

public abstract class DAOJDBC<ID, T> implements IDAO<ID, T> {

	protected String dbUrl = "";
	protected String dbLogin = "";
	protected String dbPwd = "";
	
	public DAOJDBC(String dbUrl, String dbLogin, String dbPwd ) {
		this.dbUrl = dbUrl;
		this.dbLogin = dbLogin;
		this.dbPwd = dbPwd;
	}
}
