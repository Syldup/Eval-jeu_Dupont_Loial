package dal;

import bo.Calcul;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CalculDAOJDBC extends DAOJDBC<Integer, Calcul> {

	private static final String INSERT_QUERY = "INSERT INTO calcul (calcul, resultat, reponce, date, idpartie) VALUES (?, ?, ?, NOW(), ?)";
	private static final String UPDATE_QUERY = "UPDATE calcul SET calcul=?, resultat=?, reponce=? WHERE idcalcul=?";
	private static final String FIND_ALL_QUERY = "SELECT * FROM calcul";
	private static final String FIND_BY_ID_QUERY = "SELECT * FROM calcul WHERE idcalcul=?";
	private static final String DELETE_QUERY = "DELETE FROM calcul WHERE idcalcul=?";

	public CalculDAOJDBC(String dbUrl, String dbLogin, String dbPwd ) {
		super( dbUrl, dbLogin, dbPwd );
	}

	public void save(Calcul calcul) throws SQLException {
		if (calcul.getId() == 0)
			 create(calcul);
		else update(calcul);
	}

	@Override
	public void create(Calcul object) throws SQLException {
		try (Connection conn = DriverManager.getConnection( dbUrl, dbLogin, dbPwd );
			 PreparedStatement ps = conn.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, object.getCalcul());
			ps.setString(2, object.getResultat());
			ps.setString(3, object.getReponce());
			ps.setInt(4, object.getPartie().getId());
			ps.executeUpdate();
			try(ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next())
					object.setId(rs.getInt(1));
			}
		}
	}

	@Override
	public void update(Calcul object) throws SQLException {
		if (object.getId() > 0)
			try (Connection conn = DriverManager.getConnection( dbUrl, dbLogin, dbPwd );
				 PreparedStatement ps = conn.prepareStatement(UPDATE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
				ps.setString(1, object.getCalcul());
				ps.setString(2, object.getResultat());
				ps.setString(3, object.getReponce());
				ps.setInt(4, object.getId());
				ps.executeUpdate();
			}
	}

	@Override
	public List<Calcul> findAll() throws SQLException {
		List<Calcul> objects = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection( dbUrl, dbLogin, dbPwd );
			 Statement s = conn.createStatement();
			 ResultSet rs = s.executeQuery(FIND_ALL_QUERY)) {
			while (rs.next())
				objects.add(new Calcul(rs));
		}
		return objects;
	}

	@Override
	public Calcul findById(Integer integer) throws SQLException {
		Calcul object = null;
		try (Connection conn = DriverManager.getConnection( dbUrl, dbLogin, dbPwd );
			 PreparedStatement ps = conn.prepareStatement(FIND_BY_ID_QUERY, Statement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, integer);
			try(ResultSet rs = ps.executeQuery();) {
				if (rs.next())
					object = new Calcul(rs);
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
}
