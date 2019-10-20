package dal;

import java.sql.SQLException;
import java.util.List;

public interface IDAO<ID, T> {
	public void create(T object) throws SQLException;
	public void update(T object) throws SQLException;
	public List<T> findAll() throws SQLException;
	public T findById(ID id) throws SQLException;
	public void deleteById(ID id) throws SQLException;
}
