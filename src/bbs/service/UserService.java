package bbs.service;

import static bbs.utils.CloseableUtil.*;
import static bbs.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import bbs.beans.User;
import bbs.dao.UserDao;
import bbs.utils.CipherUtil;

public class UserService {

	public void userStopped(int stopped){
		Connection connection = null;
		try {
			connection = getConnection();
			UserDao userDao = new UserDao();
			userDao.stopped(connection, stopped);
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
	public void userResurrection(int resurrection){
		Connection connection = null;
		try {
			connection = getConnection();
			UserDao userDao = new UserDao();
			userDao.resurrection(connection, resurrection);
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
	public void userSetting(User user){
		Connection connection = null;
		try {
			connection = getConnection();

			if(StringUtils.isEmpty(user.getPassword()) == false) {
				String encPassword = CipherUtil.encrypt(user.getPassword());
				user.setPassword(encPassword);
			}

			UserDao userDao = new UserDao();
			userDao.userSetting(connection,  user);
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
	public void register(User user) {
		Connection connection = null;
		try {
			connection = getConnection();
			String encPassword = CipherUtil.encrypt(user.getPassword());
			user.setPassword(encPassword);
			UserDao userDao = new UserDao();
			userDao.insert(connection, user);
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
	public List<User> getUser() {
		Connection connection = null;
		try {
			connection = getConnection();
			UserDao userDao = new UserDao();
			List<User> ret = userDao.getUser(connection);
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
	public User checkLogin(String checkLoginId) {
		Connection connection = null;
		try {
			connection = getConnection();
			UserDao userDao = new UserDao();
			User user = userDao.getUser(connection, checkLoginId);
			commit(connection);
			return user;

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

	public User checkId(int checkId) {
		Connection connection = null;
		try {
			connection = getConnection();
			UserDao userDao = new UserDao();
			User user = userDao.getUser(connection, checkId);
			commit(connection);
			return user;

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

	public User getSettingUser(int userId) {
		Connection connection = null;
		try {
			connection = getConnection();
			UserDao userDao = new UserDao();
			User user = userDao.getSettingUser(connection, userId);
			commit(connection);
			return user;

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
