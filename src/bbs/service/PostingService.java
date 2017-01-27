package bbs.service;

import static bbs.utils.CloseableUtil.*;
import static bbs.utils.DBUtil.*;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;

import bbs.beans.Posting;
import bbs.beans.UserPosting;
import bbs.dao.PostingDao;
import bbs.dao.UserPostingDao;

public class PostingService {

	public Timestamp getDate(Timestamp pastdate) {
		Connection connection = null;
		try {
			connection = getConnection();
			UserPostingDao userPostingDao = new UserPostingDao();
			Timestamp ret =  userPostingDao.getDate(connection,pastdate);
			commit(connection);
			return ret;

		} catch(RuntimeException e) {
			rollback(connection);
			throw e;

		} catch(Error e) {
			rollback(connection);
			throw e;

		} finally {
			close(connection);
		}
	}
	public void deletePosting(int deletePosting) {
		Connection connection = null;

		try {
			connection = getConnection();
			PostingDao PostingDao = new PostingDao();
			PostingDao.delete(connection, deletePosting);
			commit(connection);

		} catch(RuntimeException e) {
			rollback(connection);
			throw e;

		} catch(Error e) {
			rollback(connection);
			throw e;

		} finally {
			close(connection);
		}
	}
	public void register(Posting posting) {
		Connection connection = null;
		try {
			connection = getConnection();
			PostingDao postingDao = new PostingDao();
			postingDao.insert(connection, posting);
			commit(connection);

		} catch(RuntimeException e) {
			rollback(connection);
			throw e;

		} catch(Error e) {
			rollback(connection);
			throw e;

		} finally {
			close(connection);
		}
	}
	private static final int LIMIT_NUM = 1000;

	public List<UserPosting> getPosting(String start_date, String end_date, String findCategory) {
		Connection connection = null;
		try {
			connection = getConnection();
			UserPostingDao postingDao = new UserPostingDao();
			List<UserPosting> ret = postingDao.getUserPostings(connection, LIMIT_NUM, start_date, end_date, findCategory);
			commit(connection);
			return ret;

		} catch(RuntimeException e) {
			rollback(connection);
			throw e;

		} catch(Error e) {
			rollback(connection);
			throw e;

		} finally {
			close(connection);
		}
	}




}