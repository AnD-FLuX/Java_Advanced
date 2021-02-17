package ua.lviv.lgs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.lviv.lgs.dao.UserDao;
import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.utils.ConnectionUtils;

public class UserDaoImpl implements UserDao {

	static String READ_ALL = "select * from user";
	static String CREATE = "insert into user(`email`,`firstName`,`lastName`,`role`, `password`) values (?,?,?,?,?)";
	static String UPDATE_BY_ID = "update user set email=?,firstName=?,lastName=?,role=?,password=? where id=?";
	static String READ_BY_ID = "select * from user where id=?";
	static String READ_BY_EMAIL = "select * from user where email=?";
	static String DELETE_BY_ID = "delete from user where id=?";

	private static Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

	private Connection connection;
	private PreparedStatement preparedStatement;

	public UserDaoImpl() {
		try {
			this.connection = ConnectionUtils.connect();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			LOGGER.error(e);
		}
	}

	@Override
	public User create(User user) {
		try {
			preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4, user.getRole());
			preparedStatement.setString(5, user.getPassword());
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			user.setId(resultSet.getInt(1));
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return user;
	}

	@Override
	public User read(Integer id) {
		User user = null;
		try {
			preparedStatement = connection.prepareStatement(READ_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			String email = resultSet.getString("email");
			String firstName = resultSet.getString("firstName");
			String lastName = resultSet.getString("lastName");
			String role = resultSet.getString("role");
			String password = resultSet.getString("password");
			user = new User(id, email, firstName, lastName, role, password);
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return user;
	}

	@Override
	public void update(Integer id, User user) {
		try {
			preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4, user.getRole());
			preparedStatement.setString(5, user.getPassword());
			preparedStatement.setInt(6, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e);
		}
	}

	@Override
	public void delete(Integer id) {
		try {
			preparedStatement = connection.prepareStatement(DELETE_BY_ID);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e);
		}
	}

	@Override
	public List<User> readAll() {
		List<User> list = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(READ_ALL);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String email = resultSet.getString("email");
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				String role = resultSet.getString("role");
				String password = resultSet.getString("password");
				list.add(new User(id, email, firstName, lastName, role, password));
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return list;
	}

	@Override
	public User getUserByEmail(String email) {
		User user = null;
		try {
			preparedStatement = connection.prepareStatement(READ_BY_EMAIL);
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			Integer userId = resultSet.getInt("id");
			String firstName = resultSet.getString("firstName");
			String lastName = resultSet.getString("lastName");
			String role = resultSet.getString("role");
			String password = resultSet.getString("password");
			user = new User(userId, email, firstName, lastName, role, password);
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return user;
	}

}
