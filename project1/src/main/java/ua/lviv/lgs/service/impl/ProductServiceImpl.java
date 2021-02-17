package ua.lviv.lgs.service.impl;

import java.sql.SQLException;
import java.util.List;

import ua.lviv.lgs.dao.ProductDao;
import ua.lviv.lgs.dao.impl.ProductDaoImpl;
import ua.lviv.lgs.domain.Product;
import ua.lviv.lgs.service.ProductService;

public class ProductServiceImpl implements ProductService {
	private ProductDao productDao;

	public ProductServiceImpl()
			throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		productDao = new ProductDaoImpl();
	}

	@Override
	public Product create(Product product) throws SQLException {
		return productDao.create(product);
	}

	@Override
	public Product read(Integer id) throws SQLException {
		return productDao.read(id);
	}

	@Override
	public void update(Integer id, Product product) throws SQLException {
		productDao.update(id, product);
	}

	@Override
	public void delete(Integer id) throws SQLException {

		productDao.delete(id);
	}

	@Override
	public List<Product> readAll() throws SQLException {
		return productDao.readAll();
	}
}