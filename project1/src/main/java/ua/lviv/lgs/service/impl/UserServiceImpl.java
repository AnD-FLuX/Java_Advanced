package ua.lviv.lgs.service.impl;

import java.sql.SQLException;
import java.util.List;

import ua.lviv.lgs.dao.UserDao;
import ua.lviv.lgs.dao.impl.UserDaoImpl;
import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao userDao;

	public UserServiceImpl()
			throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		userDao = new UserDaoImpl();
	}

	@Override
	public User create(User user) throws SQLException {
		return userDao.create(user);
	}

	@Override
	public User read(Integer id) throws SQLException {
		return userDao.read(id);
	}

	
	@Override
	public void update(Integer id, User user) throws SQLException {
		userDao.update(id, user);
	}

	@Override
	public void delete(Integer id) throws SQLException {
		userDao.delete(id);
	}

	@Override
	public List<User> readAll() throws SQLException {
		return userDao.readAll();
	}
}