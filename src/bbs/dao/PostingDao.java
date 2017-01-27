package bbs.dao;
import static bbs.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bbs.beans.Posting;
import bbs.exception.SQLRuntimeException;

public class PostingDao {
	public void insert(Connection connection, Posting posting) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO postings (");
			sql.append("user_id");
			sql.append(", subject");
			sql.append(", text");
			sql.append(", category");
			sql.append(", update_date");
			sql.append(", insert_date");

			sql.append(") VALUES (");
			sql.append("?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", CURRENT_TIMESTAMP");
			sql.append(", CURRENT_TIMESTAMP");
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());
			ps.setInt(1, posting.getUser_id());
			ps.setString(2, posting.getSubject());
			ps.setString(3,posting.getText());
			ps.setString(4,posting.getCategory());
			ps.executeUpdate();

		} catch(SQLException e) {
			throw new SQLRuntimeException(e);

		} finally {
			close(ps);

		}
	}
	public void delete(Connection connection, int deletePosting) {
		PreparedStatement ps = null;
			try {
				StringBuilder sql = new StringBuilder();
				sql.append( "DELETE  FROM postings WHERE id = ?");
				ps = connection.prepareStatement(sql.toString());
				ps.setInt(1, deletePosting);
				ps.executeUpdate();

			} catch(SQLException e) {
				throw new SQLRuntimeException(e);

			} finally {
				close(ps);
		}
	}
}