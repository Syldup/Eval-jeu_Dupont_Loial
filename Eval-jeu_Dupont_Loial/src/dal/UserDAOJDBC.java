package dal;

import bo.User;
import model.UserBean;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOJDBC extends DAOJDBC<Integer, User> {
	
	private static final String AUTHENT_QUERY = "SELECT idUser, username, idbestpartie FROM user WHERE username = ? AND password = ?";
	private static final String INSERT_QUERY = "INSERT INTO user (username, password) VALUES (?, ?)";
	private static final String UPDATE_QUERY = "UPDATE user SET username=?, password=?, idbestpartie=? WHERE idUser=?";
	private static final String FIND_ALL_QUERY = "SELECT idUser, username, idbestpartie FROM user";
	private static final String FIND_BY_ID_QUERY = "SELECT idUser, username, idbestpartie FROM user WHERE idUser=?";
	private static final String DELETE_QUERY = "DELETE FROM user WHERE idUser=?";
	private static final String TOP_PLAYER_DISPLAY = "SELECT username, score FROM user, partie WHERE user.iduser=partie.iduser ORDER BY score DESC LIMIT 10";
	
	public UserDAOJDBC( String dbUrl, String dbLogin, String dbPwd ) {
		super( dbUrl, dbLogin, dbPwd );
	}

	@Override
	public void create(User object) {
		try (Connection conn = DriverManager.getConnection( dbUrl, dbLogin, dbPwd );
			 PreparedStatement ps = conn.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, object.getUsername());
			ps.setString(2, object.getPassword());
			ps.executeUpdate();
			try(ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next())
					object.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void update(User object) {
		if (object.getId() > 0)
			try (Connection conn = DriverManager.getConnection( dbUrl, dbLogin, dbPwd );
				 PreparedStatement ps = conn.prepareStatement(UPDATE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
				ps.setString(1, object.getUsername());
				ps.setString(2, object.getPassword());
				ps.setInt(3, object.getBestParte().getId());
				ps.setInt(4, object.getId());
				ps.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
	}

	@Override
	public List<User> findAll() {
		List<User> objects = new ArrayList<User>();
		try (Connection conn = DriverManager.getConnection( dbUrl, dbLogin, dbPwd );
			 Statement s = conn.createStatement();
			 ResultSet rs = s.executeQuery(FIND_ALL_QUERY)) {
			while (rs.next())
				objects.add(UserBean.getUser(rs));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return objects;
	}

	@Override
	public User findById(Integer integer) {
		User object = null;
		try (Connection conn = DriverManager.getConnection( dbUrl, dbLogin, dbPwd );
			 PreparedStatement ps = conn.prepareStatement(FIND_BY_ID_QUERY, Statement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, integer);
			try(ResultSet rs = ps.executeQuery();) {
				if (rs.next())
					object = UserBean.getUser(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return object;
	}

	@Override
	public void deleteById(Integer integer) {
		try (Connection conn = DriverManager.getConnection( dbUrl, dbLogin, dbPwd );
			 PreparedStatement ps = conn.prepareStatement(DELETE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, integer);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public List<User> topPlayer() throws SQLException{
        List<User> objects = new ArrayList<User>();
        try (Connection conn = DriverManager.getConnection( dbUrl, dbLogin, dbPwd );
             Statement s = conn.createStatement();
             ResultSet rs = s.executeQuery(TOP_PLAYER_DISPLAY)) {
            while (rs.next())
                objects.add(UserBean.getUser(rs));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return objects;
	}

	public User authenticate(String login, String password ) throws SQLException {
		User user = null;
		try ( Connection connection = DriverManager.getConnection( dbUrl, dbLogin, dbPwd );
			  PreparedStatement ps = connection.prepareStatement(AUTHENT_QUERY) ) {
			ps.setString( 1, login );
			ps.setString( 2, password );
			try ( ResultSet rs = ps.executeQuery() ) {
				if ( rs.next() )
					user = UserBean.getUser(rs);
			}
		}
		return user;
	}
}
