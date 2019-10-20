package dal;

import bo.Partie;
import bo.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartieDAOJDBC extends DAOJDBC<Integer, Partie> {

	private static final String INSERT_QUERY = "INSERT INTO partie (score, date, iduser) VALUES (?, NOW(), ?)";
	private static final String UPDATE_QUERY = "UPDATE partie SET score=? WHERE idpartie=? AND iduser=?";
	private static final String FIND_ALL_QUERY = "SELECT * FROM partie LIMIT 10";
	private static final String FIND_BY_ID_QUERY = "SELECT * FROM partie WHERE idpartie=?";
	private static final String DELETE_QUERY = "DELETE FROM partie WHERE idpartie=?";
	private static final String TOP_10_PARTIE = "SELECT user.iduser, username, idpartie, score, date FROM partie LEFT JOIN user ON partie.iduser=user.iduser ORDER BY score DESC LIMIT 10";
	private static final String TOP_10_PARTIE_ID = "SELECT idpartie, score, date, iduser FROM partie WHERE iduser=? ORDER BY score DESC LIMIT 10";

	public PartieDAOJDBC(String dbUrl, String dbLogin, String dbPwd ) {
		super( dbUrl, dbLogin, dbPwd );
	}

	@Override
	public void create(Partie object) throws SQLException {
		try (Connection conn = DriverManager.getConnection( dbUrl, dbLogin, dbPwd );
			 PreparedStatement ps = conn.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, object.getScore());
			ps.setInt(2, object.getUser().getId());
			ps.executeUpdate();
			try(ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next())
					object.setId(rs.getInt(1));
			}
		}
	}

	public void update(Partie object, User user) throws SQLException {
		object.setUser(user);
		update(object);
	}

	@Override
	public void update(Partie object) throws SQLException {
		if (object.getId() > 0)
			try (Connection conn = DriverManager.getConnection( dbUrl, dbLogin, dbPwd );
				 PreparedStatement ps = conn.prepareStatement(UPDATE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
				ps.setInt(1, object.getScore());
				ps.setInt(2, object.getId());
				ps.setInt(3, object.getUser().getId());
				ps.executeUpdate();
			}
	}

	@Override
	public List<Partie> findAll() throws SQLException {
		List<Partie> objects = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection( dbUrl, dbLogin, dbPwd );
			 Statement s = conn.createStatement();
			 ResultSet rs = s.executeQuery(FIND_ALL_QUERY)) {
			while (rs.next())
				objects.add(new Partie(rs));
		}
		return objects;
	}

	@Override
	public Partie findById(Integer integer) throws SQLException {
		Partie object = null;
		try (Connection conn = DriverManager.getConnection( dbUrl, dbLogin, dbPwd );
			 PreparedStatement ps = conn.prepareStatement(FIND_BY_ID_QUERY, Statement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, integer);
			try(ResultSet rs = ps.executeQuery();) {
				if (rs.next())
					object = new Partie(rs);
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

	public List<Partie> getTopParty() throws SQLException {
		List<Partie> objects = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection( dbUrl, dbLogin, dbPwd );
			 Statement s = conn.createStatement();
			 ResultSet rs = s.executeQuery(TOP_10_PARTIE)) {
			while (rs.next())
				objects.add(new Partie( rs ));
		}
		return objects;
	}

	public List<Partie> getTopParty(User user) throws SQLException {
		List<Partie> objects = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection( dbUrl, dbLogin, dbPwd );
			 PreparedStatement ps = conn.prepareStatement(TOP_10_PARTIE_ID, Statement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, user.getId());
			try(ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Partie p = new Partie(rs);
					p.setUser( user );
					objects.add( p );
				}
			}
		}
		return objects;
	}
}
