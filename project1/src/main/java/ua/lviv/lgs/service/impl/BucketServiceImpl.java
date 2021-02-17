package ua.lviv.lgs.service.impl;

import java.sql.SQLException;
import java.util.List;

import ua.lviv.lgs.dao.BucketDao;
import ua.lviv.lgs.dao.impl.BucketDaoImpl;
import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.service.BucketService;

public class BucketServiceImpl implements BucketService {
	private BucketDao bucketDao;

	public BucketServiceImpl()
			throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		bucketDao = new BucketDaoImpl();
	}

	@Override
	public Bucket create(Bucket bucket) throws SQLException {
		return bucketDao.create(bucket);
	}

	@Override
	public Bucket read(Integer id) throws SQLException {
		return bucketDao.read(id);
	}

	@Override
	public void update(Integer id, Bucket bucket) throws SQLException {
		bucketDao.update(id, bucket);
	}

	@Override
	public void delete(Integer id) throws SQLException {
		bucketDao.delete(id);
	}

	@Override
	public List<Bucket> readAll() throws SQLException {
		return bucketDao.readAll();
	}
}