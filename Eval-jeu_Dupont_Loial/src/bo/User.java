package bo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User implements Serializable {
	private int id = 0;
	private String username;
	private String password;
	private Partie bestParte = null;
	
	public User() {}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User(int id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public User(ResultSet rs) {
		try { id = rs.getInt("iduser"); }
		catch (SQLException e) { id = 0; }

		try { username = rs.getString("username"); }
		catch (SQLException e) { username = ""; }

		bestParte = Partie.getInstance( rs, this );
	}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }

	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }

	public Partie getBestParte() { return bestParte; }
	public void setBestParte(Partie bestParte) { this.bestParte = bestParte; }

}
