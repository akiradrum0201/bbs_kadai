package bbs.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import bbs.exception.SQLRuntimeException;

public class CloseableUtil {
	public static void close(PreparedStatement ps) {
		if (ps == null)
			return;
		try {
			ps.close();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}


	public static void close(Connection connection) {
		if (connection == null)
			return;
		try {
			connection.close();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}

	public static void close(Statement statement) {
		if (statement == null)
			return;
		try {
			statement.close();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}

	public static void close(ResultSet rs) {
		if (rs == null)
			return;
		try {
			rs.close();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}
}
