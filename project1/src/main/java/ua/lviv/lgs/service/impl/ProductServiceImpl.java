package ua.lviv.lgs.service.impl;

import java.util.List;

import ua.lviv.lgs.dao.ProductDao;
import ua.lviv.lgs.dao.impl.ProductDaoImpl;
import ua.lviv.lgs.domain.Product;
import ua.lviv.lgs.service.ProductService;

public class ProductServiceImpl implements ProductService {
	private ProductDao productDao;
	private static ProductService productServiceImpl;

	public ProductServiceImpl() {
		productDao = new ProductDaoImpl();
	}

	public static ProductService getProductService() {
		if (productServiceImpl == null) {
			productServiceImpl = new ProductServiceImpl();
		}
		return productServiceImpl;
	}

	@Override
	public Product create(Product product) {
		return productDao.create(product);
	}

	@Override
	public Product read(Integer id) {
		return productDao.read(id);
	}

	@Override
	public void update(Integer id, Product product) {
		productDao.update(id, product);
	}

	@Override
	public void delete(Integer id) {

		productDao.delete(id);
	}

	@Override
	public List<Product> readAll() {
		return productDao.readAll();
	}
}