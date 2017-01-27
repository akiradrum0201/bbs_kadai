package bbs.dao;

import static bbs.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import bbs.beans.UserPosting;
import bbs.exception.SQLRuntimeException;

public class UserPostingDao {
	public Timestamp  getDate(Connection connection, Timestamp date){
		PreparedStatement ps = null;
		try {
			String sql = "SELECT min(insert_date) AS insert_date from bbs.users_postings";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Timestamp ret = rs.getTimestamp("insert_date");
			return ret;
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);

		} finally {
			close(ps);
		}
	}
	public  List<UserPosting> getUserPostings(Connection connection, int num, String start_date, String end_date,  String findCategory) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			if(StringUtils.isEmpty(findCategory)==false) {
				sql.append("SELECT * FROM bbs.users_postings WHERE insert_date >= ? AND insert_date <= ? and category= ? ");
				sql.append("ORDER BY insert_date DESC limit " + num);
				ps = connection.prepareStatement(sql.toString());
				ps.setString(1, start_date);
				ps.setString(2, (end_date+"-23.59.59'"));
				ps.setString(3, findCategory);


			} else {
				sql.append("SELECT * FROM bbs.users_postings WHERE insert_date >=? AND insert_date <=? ");
				sql.append("ORDER BY insert_date DESC limit " + num);
				ps = connection.prepareStatement(sql.toString());
				ps.setString(1, start_date);
				ps.setString(2, (end_date+"-23.59.59'"));

			}
			ResultSet rs = ps.executeQuery();
			List<UserPosting> ret = toUserPostingList(rs);
			return ret;

		} catch(SQLException e) {
			throw new SQLRuntimeException(e);

		} finally {
			close(ps);
		}
	}
	private List<UserPosting> toUserPostingList(ResultSet rs) throws SQLException {
		List<UserPosting> ret = new ArrayList<UserPosting>();
		try {
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String subject = rs.getString("subject");
				String category = rs.getString("category");
				String text = rs.getString("text");
				Timestamp insert_date = rs.getTimestamp("insert_date");
				int user_id = rs.getInt("user_id");
				int branch_id = rs.getInt("branch_id");
				int department_id = rs.getInt("department_id");

				UserPosting posting = new UserPosting();
				posting.setId(id);
				posting.setName(name);
				posting.setSubject(subject);
				posting.setCategory(category);
				posting.setText(text);
				posting.setInsert_date(insert_date);
				posting.setUser_id(user_id);
				posting.setBranch_id(branch_id);
				posting.setDepartment_id(department_id);
				ret.add(posting);
			}
			return ret;

		} finally {
			close(rs);
		}
	}


}
