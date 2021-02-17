package ua.lviv.lgs.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ua.lviv.lgs.dao.BucketDao;
import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.utils.ConnectionUtils;

public class BucketDaoImpl implements BucketDao {
	static String READ_ALL = "select * from bucket";
	static String CREATE = "insert into bucket(`userId`,`productId`) values (?,?)";
	static String READ_BY_ID = "select * from bucket where id=?";
	static String DELETE_BY_ID = "delete from bucket where id=?";

	private Connection con;
	private PreparedStatement rpp;

	public BucketDaoImpl() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		this.con = ConnectionUtils.connect();
	}

	@Override
	public Bucket create(Bucket bucketDao) throws SQLException {
		rpp = con.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
		rpp.setInt(1, bucketDao.getUserId());
		rpp.setInt(2, bucketDao.getProductId());
		rpp.executeUpdate();
		ResultSet resultSet = rpp.getGeneratedKeys();
		resultSet.next();
		bucketDao.setId(resultSet.getInt(1));
		return bucketDao;
	}

	@Override
	public Bucket read(Integer id) throws SQLException {
		Bucket bucket;
		rpp = con.prepareStatement(READ_BY_ID);
		rpp.setInt(1, id);
		ResultSet res = rpp.executeQuery();
		res.next();
		int bucketId = res.getInt("id");
		int userId = res.getInt("userId");
		int productId = res.getInt("productId");
		String nowDate = res.getString("nowDate");
		bucket = new Bucket(bucketId, userId, productId, nowDate);
		return bucket;
	}

	@Override
	public void update(Integer id, Bucket bucketDao) {
		throw new IllegalStateException("no update");
	}

	@Override
	public void delete(Integer id) throws SQLException {
		rpp = con.prepareStatement(DELETE_BY_ID);
		rpp.setInt(1, id);
		rpp.executeUpdate();
	}

	@Override
	public List<Bucket> readAll() throws SQLException {
		List<Bucket> list = new ArrayList<>();
		rpp = con.prepareStatement(READ_ALL);
		ResultSet res = rpp.executeQuery();
		while (res.next()) {
			int id = res.getInt("id");
			int userId = res.getInt("userId");
			int productId = res.getInt("productId");
			String nowDate = res.getString("nowDate");
			list.add(new Bucket(id, userId, productId, nowDate));
		}
		return list;
	}
}