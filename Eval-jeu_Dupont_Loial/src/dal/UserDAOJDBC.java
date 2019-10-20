package dal;

import bo.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOJDBC extends DAOJDBC<Integer, User> {
	
	private static final String AUTHENT_QUERY = "SELECT user.iduser, username, idpartie, score, date FROM user LEFT JOIN partie ON idbestpartie=idpartie WHERE username = ? AND password = ?";
	private static final String INSERT_QUERY = "INSERT INTO user (username, password) VALUES (?, ?)";
	private static final String UPDATE_QUERY = "UPDATE user SET username=?, password=?, idbestpartie=? WHERE idUser=?";
	private static final String FIND_ALL_QUERY = "SELECT user.iduser, username, idpartie, score, date FROM user LEFT JOIN partie ON idbestpartie=idpartie";
	private static final String FIND_BY_ID_QUERY = "SELECT user.iduser, username, idpartie, score, date FROM user LEFT JOIN partie ON idbestpartie=idpartie WHERE user.idUser=?";
	private static final String DELETE_QUERY = "DELETE FROM user WHERE idUser=?";
	
	public UserDAOJDBC( String dbUrl, String dbLogin, String dbPwd ) {
		super( dbUrl, dbLogin, dbPwd );
	}

	@Override
	public void create(User object) throws SQLException {
		try (Connection conn = DriverManager.getConnection( dbUrl, dbLogin, dbPwd );
			 PreparedStatement ps = conn.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, object.getUsername());
			ps.setString(2, object.getPassword());
			ps.executeUpdate();
			try(ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next())
					object.setId(rs.getInt(1));
			}
		}
	}

	@Override
	public void update(User object) throws SQLException {
		if (object.getId() > 0)
			try (Connection conn = DriverManager.getConnection( dbUrl, dbLogin, dbPwd );
				 PreparedStatement ps = conn.prepareStatement(UPDATE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
				ps.setString(1, object.getUsername());
				ps.setString(2, object.getPassword());
				ps.setInt(3, object.getBestParte().getId());
				ps.setInt(4, object.getId());
				ps.executeUpdate();
			}
	}

	@Override
	public List<User> findAll() throws SQLException {
		List<User> objects = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection( dbUrl, dbLogin, dbPwd );
			 Statement s = conn.createStatement();
			 ResultSet rs = s.executeQuery(FIND_ALL_QUERY)) {
			while (rs.next())
				objects.add(new User( rs ));
		}
		return objects;
	}

	@Override
	public User findById(Integer integer) throws SQLException {
		User object = null;
		try (Connection conn = DriverManager.getConnection( dbUrl, dbLogin, dbPwd );
			 PreparedStatement ps = conn.prepareStatement(FIND_BY_ID_QUERY, Statement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, integer);
			try(ResultSet rs = ps.executeQuery();) {
				if (rs.next())
					object = new User( rs );
			}
		}
		return object;
	}

	@Override
	public void deleteById(Integer integer) throws SQLException {
		try (Connection conn = DriverManager.getConnection( dbUrl, dbLogin, dbPwd );
			 PreparedStatement ps = conn.prepareStatement(DELETE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, integer);
			ps.executeUpdate();
		}
	}

	public User authenticate(String login, String password ) throws SQLException {
		User user = null;
		try ( Connection connection = DriverManager.getConnection( dbUrl, dbLogin, dbPwd );
			  PreparedStatement ps = connection.prepareStatement(AUTHENT_QUERY) ) {
			ps.setString( 1, login );
			ps.setString( 2, password );
			try ( ResultSet rs = ps.executeQuery() ) {
				if ( rs.next() ) {
					user = new User(rs);
				}
			}
		}
		return user;
	}
}
