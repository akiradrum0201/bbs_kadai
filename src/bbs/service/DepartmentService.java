package bbs.service;

import static bbs.utils.CloseableUtil.*;
import static bbs.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import bbs.beans.Department;
import bbs.dao.DepartmentDao;

public class DepartmentService {
	private static final int LIMIT_NUM = 1000;

	public List<Department> getDepartment() {
		Connection connection = null;
		try {
			connection = getConnection();
			DepartmentDao DepartmentDao = new DepartmentDao();
			List<Department> ret = DepartmentDao.getDepartments(connection, LIMIT_NUM);
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
