package ua.lviv.lgs.service;

import java.util.Map;

import ua.lviv.lgs.domain.Product;
import ua.lviv.lgs.shared.AbstractCRUD;

public interface ProductService extends AbstractCRUD<Product> {

	public Map<Integer, Product> readAllMap();

}