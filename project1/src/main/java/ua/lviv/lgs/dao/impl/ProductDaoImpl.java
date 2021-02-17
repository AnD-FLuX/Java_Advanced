package ua.lviv.lgs.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ua.lviv.lgs.dao.ProductDao;
import ua.lviv.lgs.domain.Product;
import ua.lviv.lgs.utils.ConnectionUtils;

public class ProductDaoImpl implements ProductDao {

	static String READ_ALL = "SELECT * from product";
	static String CREATE = "INSERT into product(`name`,`description`,`price`) values (?,?,?)";
	static String UPDATE_BY_ID = "UPDATE product set name=? ,description=?,price=? WHERE id=?";
	static String READ_BY_ID = "SELECT * from product WHERE id=?";
	static String DELETE_BY_ID = "DELETE from product WHERE id=?";

	private Connection con;
	private PreparedStatement rpp;

	public ProductDaoImpl() {
		try {
			this.con = ConnectionUtils.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Product create(Product product) throws SQLException {
		rpp = con.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
		rpp.setString(1, product.getName());
		rpp.setString(2, product.getDescription());
		rpp.setDouble(3, product.getPrice());
		rpp.executeUpdate();
		ResultSet resultSet = rpp.getGeneratedKeys();
		resultSet.next();
		product.setId(resultSet.getInt(1));
		return product;
	}

	@Override
	public Product read(Integer id) throws SQLException {
		Product product;
		rpp = con.prepareStatement(READ_BY_ID);
		rpp.setInt(1, id);
		ResultSet res = rpp.executeQuery();
		res.next();
		int productId = res.getInt("id");
		String name = res.getString("name");
		String description = res.getString("description");
		double price = res.getDouble("price");

		product = new Product(productId, name, description, price);
		return product;
	}

	@Override
	public void update(Integer id, Product product) throws SQLException {
		rpp = con.prepareStatement(UPDATE_BY_ID);
		rpp.setString(1, product.getName());
		rpp.setString(2, product.getDescription());
		rpp.setDouble(3, product.getPrice());
		rpp.setInt(4, id);
		rpp.executeUpdate();
	}

	@Override
	public void delete(Integer id) throws SQLException {
		rpp = con.prepareStatement(DELETE_BY_ID);
		rpp.setInt(1, id);
		rpp.executeUpdate();
	}

	@Override
	public List<Product> readAll() throws SQLException {
		List<Product> list = new ArrayList<>();
		rpp = con.prepareStatement(READ_ALL);
		ResultSet res = rpp.executeQuery();
		while (res.next()) {
			int id = res.getInt("id");
			String name = res.getString("name");
			String description = res.getString("description");
			double price = res.getDouble("price");
			list.add(new Product(id, name, description, price));
		}
		return list;
	}
}