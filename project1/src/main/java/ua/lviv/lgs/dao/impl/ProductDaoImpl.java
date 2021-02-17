package ua.lviv.lgs.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.lviv.lgs.dao.ProductDao;
import ua.lviv.lgs.domain.Product;
import ua.lviv.lgs.utils.ConnectionUtils;

public class ProductDaoImpl implements ProductDao {

	static String READ_ALL = "SELECT * from product";
	static String CREATE = "INSERT into product(`name`,`description`,`price`) values (?,?,?)";
	static String UPDATE_BY_ID = "UPDATE product set name=? ,description=?,price=? WHERE id=?";
	static String READ_BY_ID = "SELECT * from product WHERE id=?";
	static String DELETE_BY_ID = "DELETE from product WHERE id=?";

	private static Logger LOGGER = Logger.getLogger(ProductDaoImpl.class);

	private Connection connection;
	private PreparedStatement preparedStatement;

	public ProductDaoImpl() {
		try {
			this.connection = ConnectionUtils.connect();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			LOGGER.error(e);
		}
	}

	@Override
	public Product create(Product product) {
		try {
			preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, product.getName());
			preparedStatement.setString(2, product.getDescription());
			preparedStatement.setDouble(3, product.getPrice());
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			product.setId(resultSet.getInt(1));
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return product;
	}

	@Override
	public Product read(Integer id) {
		Product product = null;
		try {
			preparedStatement = connection.prepareStatement(READ_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int productId = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String description = resultSet.getString("description");
			double price = resultSet.getDouble("price");
			product = new Product(productId, name, description, price);
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return product;
	}

	@Override
	public void update(Integer id, Product product) {
		try {
			preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
			preparedStatement.setString(1, product.getName());
			preparedStatement.setString(2, product.getDescription());
			preparedStatement.setDouble(3, product.getPrice());
			preparedStatement.setInt(4, id);
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
	public List<Product> readAll() {
		List<Product> list = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(READ_ALL);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String description = resultSet.getString("description");
				double price = resultSet.getDouble("price");
				list.add(new Product(id, name, description, price));
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return list;
	}
}