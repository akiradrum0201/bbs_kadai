package bbs.dao;

import static bbs.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import bbs.beans.User;
import bbs.exception.SQLRuntimeException;

public class UserDao {

	public void stopped(Connection connection, int stopped) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append( "UPDATE  users SET is_stopped = 1 WHERE id = ?");
			ps = connection.prepareStatement(sql.toString());
			ps.setInt(1, stopped);
			ps.executeUpdate();

		} catch(SQLException e) {
			throw new SQLRuntimeException(e);

		} finally {
			close(ps);
		}
	}

	public void resurrection(Connection connection, int resurrection) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append( "UPDATE  users SET is_stopped = 0 WHERE id = ?");
			ps = connection.prepareStatement(sql.toString());
			ps.setInt(1, resurrection);
			ps.executeUpdate();

		} catch(SQLException e) {
			throw new SQLRuntimeException(e);

		} finally {
			close(ps);
		}
	}

	public User getSettingUser(Connection connection, int id) {
		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE id = ? ";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);

			if(userList.isEmpty() == true) {
				return null;

			} else if(2 <= userList.size()){
				throw new IllegalStateException("2 <= userList.size()");

			} else {
				return userList.get(0);

			}
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	public List<User> getUser(Connection connection) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users ");
			ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			List<User> ret = toUserList(rs);
			return ret;

		} catch(SQLException e) {
			throw new SQLRuntimeException(e);

		} finally {
			close(ps);
		}
	}
	public User getUser(Connection connection, String login_id) {
		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE login_id = ?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, login_id);
			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);


			if(userList.isEmpty() == true) {
				return null;

			} else if(2 <= userList.size()){
				throw new IllegalStateException("2 <= userList.size()");

			} else {
				return userList.get(0);
			}
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);

		} finally {
			close(ps);
		}
	}

	public User getUser(Connection connection, int id) {
		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE id = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);

			if(userList.isEmpty() == true) {
				return null;

			} else if(2 <= userList.size()){
				throw new IllegalStateException("2 <= userList.size()");

			} else {
				return userList.get(0);
			}
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);

		} finally {
			close(ps);
		}
	}

	public User getUser(Connection connection, String login_id, String password) {
		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE(login_id = ? AND password = ?)";
			ps = connection.prepareStatement(sql);
			ps.setString(1, login_id);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);

			if(userList.isEmpty() == true) {
				return null;

			} else if(2 <= userList.size()){
				throw new IllegalStateException("2 <= userList.size()");

			} else {
				return userList.get(0);
			}
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	private List<User> toUserList(ResultSet rs)throws SQLException {
		List<User> ret = new ArrayList<User>();
		try {
			while(rs.next()) {
				int id = rs.getInt("id");
				int branch_id = rs.getInt("branch_id");
				int department_id = rs.getInt("department_id");
				String name = rs.getString("name");
				String login_id = rs.getString("login_id");
				String password = rs.getString("password");
				int is_stopped = rs.getInt("is_stopped");
				User user = new User();

				user.setId(id);
				user.setBranch_id(branch_id);
				user.setDepartment_id(department_id);
				user.setName(name);
				user.setLogin_id(login_id);
				user.setPassword(password);
				user.setIs_stopped(is_stopped);
				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
	public void insert(Connection connection, User user) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO users (");
			sql.append("login_id");
			sql.append(", password");
			sql.append(", name");
			sql.append(", branch_id");
			sql.append(", department_id");
			sql.append(", is_stopped");

			sql.append(") VALUES (");
			sql.append("?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, user.getLogin_id());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setInt(4, user.getBranch_id());
			ps.setInt(5, user.getDepartment_id());
			ps.setInt(6, user.getIs_stopped());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);

		} finally {
			close(ps);
		}
	}
	public void userSetting(Connection connection, User user) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append( "UPDATE  users SET");
			sql.append(" login_id=?");
			sql.append(", name =?");
			sql.append(", branch_id =?");
			sql.append(", department_id =?");
			if(StringUtils.isEmpty(user.getPassword()) == false) {
				sql.append(", password =?");
			}
			sql.append(" WHERE");
			sql.append(" id =?");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getLogin_id());
			ps.setString(2, user.getName());
			ps.setInt(3, user.getBranch_id());
			ps.setInt(4, user.getDepartment_id());
			if(StringUtils.isEmpty(user.getPassword()) == false) {
				ps.setString(5, user.getPassword());
				ps.setInt(6, user.getId());
			} else {
				ps.setInt(5, user.getId());
			}
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);

		} finally {
			close(ps);
		}
	}

}
