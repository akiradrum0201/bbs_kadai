package bbs.dao;

import static bbs.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bbs.beans.Comment;
import bbs.exception.SQLRuntimeException;

public class CommentDao {


	public void insert(Connection connection, Comment comment) {

		PreparedStatement ps = null;
			try {
				StringBuilder sql = new StringBuilder();
				sql.append("INSERT INTO bbs.comments ( ");
				sql.append("posting_id");
				sql.append(", text");
				sql.append(", update_date");
				sql.append(", user_id");
				sql.append(" ) VALUES ( ");

				sql.append(" ?");
				sql.append(", ?");
				sql.append(" ,CURRENT_TIMESTAMP");
				sql.append(", ?");
				sql.append(")");

				ps = connection.prepareStatement(sql.toString());

				ps.setInt(1, comment.getPosting_id());
				ps.setString(2, comment.getText());
				ps.setInt(3, comment.getUser_id());

				ps.executeUpdate();
			} catch(SQLException e) {
				throw new SQLRuntimeException(e);

			} finally {
				close(ps);

			}
	}

	public void delete(Connection connection, int deleteComment) {

		PreparedStatement ps = null;
			try {
				StringBuilder sql = new StringBuilder();

				sql.append( "DELETE  FROM bbs.comments WHERE id = ?");
				ps = connection.prepareStatement(sql.toString());
				ps.setInt(1, deleteComment);
				ps.executeUpdate();

			} catch(SQLException e) {
				throw new SQLRuntimeException(e);

			} finally {
				close(ps);
		}
	}
}

