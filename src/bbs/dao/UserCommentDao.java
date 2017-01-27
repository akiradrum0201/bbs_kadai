package bbs.dao;

import static bbs.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bbs.beans.UserComment;
import bbs.exception.SQLRuntimeException;

public class UserCommentDao {

	public static List<UserComment> getUserComment(Connection connection, int num) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users_comments ");
			sql.append("ORDER BY insert_date DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			List<UserComment> ret = toUserCommentList(rs);
			return ret;

		} catch(SQLException e) {
			throw new SQLRuntimeException(e);

		} finally {
			close(ps);
		}
	}

	private static List<UserComment> toUserCommentList(ResultSet rs) throws SQLException {
		List<UserComment> ret = new ArrayList<UserComment>();
		try {
			while(rs.next()) {
				int id = rs.getInt("id");
				int comment_id = rs.getInt("comment_id");
				String name = rs.getString("name");
				String text = rs.getString("text");
				Timestamp insert_date = rs.getTimestamp("insert_date");
				int branch_id = rs.getInt("branch_id");
				int department_id = rs.getInt("department_id");
				int user_id = rs.getInt("user_id");
				UserComment comment = new UserComment();


				comment.setId(id);
				comment.setComment_id(comment_id);
				comment.setName(name);
				comment.setText(text);
				comment.setInsert_date(insert_date);
				comment.setBranch_id(branch_id);
				comment.setDepartment_id(department_id);
				comment.setUser_id(user_id);
				ret.add(comment);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public void stopped(Connection connection, int stopped) {
		PreparedStatement ps = null;
			try {
				StringBuilder sql = new StringBuilder();
				sql.append( "DELETE  FROM comments WHERE id = ?");
				ps = connection.prepareStatement(sql.toString());
				ps.setInt(1, stopped);
				ps.executeUpdate();

			} catch(SQLException e) {
				throw new SQLRuntimeException(e);

			} finally {
				close(ps);
		}
	}

}
