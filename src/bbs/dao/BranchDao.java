package bbs.dao;

import static bbs.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bbs.beans.Branch;
import bbs.exception.SQLRuntimeException;

public class BranchDao {
	public List<Branch> getBranches(Connection connection) {
		PreparedStatement ps = null;

		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM bbs.branches " );
			ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			List<Branch> ret = toBranches(rs);
			return ret;

		} catch(SQLException e) {
			throw new SQLRuntimeException(e);

		} finally {
			close(ps);
		}
	}
	private List<Branch> toBranches(ResultSet rs) throws SQLException {

		List<Branch> ret = new ArrayList<Branch>();
		try {
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				Branch branch = new Branch();
				branch.setId(id);
				branch.setName(name);
				ret.add(branch);
			}
			return ret;

		} finally {
			close(rs);

		}
	}
}
