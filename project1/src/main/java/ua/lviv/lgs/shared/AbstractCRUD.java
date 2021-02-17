package ua.lviv.lgs.shared;

import java.sql.SQLException;
import java.util.List;

public interface AbstractCRUD<T> {

	T create(T t) throws SQLException;

	T read(Integer id) throws SQLException;

	void update(Integer id, T t) throws SQLException;

	void delete(Integer id) throws SQLException;

	List<T> readAll() throws SQLException;

}
