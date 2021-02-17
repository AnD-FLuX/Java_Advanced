package ua.lviv.lgs.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.lviv.lgs.dao.BucketDao;
import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.utils.ConnectionUtils;

public class BucketDaoImpl implements BucketDao {
	static String READ_ALL = "select * from bucket";
	static String CREATE = "insert into bucket(`userId`,`productId`) values (?,?)";
	static String READ_BY_ID = "select * from bucket where id=?";
	static String DELETE_BY_ID = "delete from bucket where id=?";

	private static Logger LOGGER = Logger.getLogger(BucketDaoImpl.class);

	private Connection connection;
	private PreparedStatement preparedStatement;

	public BucketDaoImpl() {
		try {
			this.connection = ConnectionUtils.connect();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			LOGGER.error(e);
		}
	}

	@Override
	public Bucket create(Bucket bucket) {
		try {
			preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, bucket.getUserId());
			preparedStatement.setInt(2, bucket.getProductId());
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			bucket.setId(resultSet.getInt(1));
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return bucket;
	}

	@Override
	public Bucket read(Integer id) {
		Bucket bucket = null;
		try {
			preparedStatement = connection.prepareStatement(READ_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int bucketId = resultSet.getInt("id");
			int userId = resultSet.getInt("userId");
			int productId = resultSet.getInt("productId");
			String nowDate = resultSet.getString("nowDate");
			bucket = new Bucket(bucketId, userId, productId, nowDate);
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return bucket;
	}

	@Override
	public void update(Integer id, Bucket bucket) {
		throw new IllegalStateException("no update");
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
	public List<Bucket> readAll() {
		List<Bucket> list = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(READ_ALL);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				int userId = resultSet.getInt("userId");
				int productId = resultSet.getInt("productId");
				String nowDate = resultSet.getString("nowDate");
				list.add(new Bucket(id, userId, productId, nowDate));
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return list;
	}
}