package net.javaguides.usermanagement.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.javaguides.usermanagement.Model.User;

public class UserDAO {
	private String url = "jdbc:mysql://localhost:3306/demo";
	private String username = "root";
	private String password = "meenagaming12@#";

	private static final String Insert_User_Sql = "insert into users" + "(name,email,country) VALUES" + "(?,?,?);";
	private static final String Select_User_By_Id = "select * from users where id =?;";
	private static final String Select_All_User = "select * from users;";
	private static final String Delete_Users_Sql = "delete from users where id=?;";
	private static final String Update_Users_Sql = "update users set name=?,email=?,country=? where id=?;";

	protected Connection getConnection() throws ClassNotFoundException {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return con;
	}

	// create or insert user
	public void insertUser(User user) {
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(Insert_User_Sql);

			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getCountry());

			stmt.executeUpdate();
//			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// update user
	public boolean updateUser(User user) throws ClassNotFoundException {
		boolean rowUpdated = false;
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(Update_Users_Sql);

			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getCountry());
			stmt.setInt(4, user.getId());

			rowUpdated = stmt.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowUpdated;
	}

	// select user by id
	public User userById(int id) {
		User user = null;
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(Select_User_By_Id);

			stmt.setInt(1, id);
			System.out.println(stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("country");
				user = new User(name,email,country);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	// select users
	public List<User> selectUser() {
		List<User> users = new ArrayList<>();
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(Select_All_User);

			System.out.println(stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("country");
				users.add( new User(id,name,email,country));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	// delete users
	public boolean deleteUser(int id) throws ClassNotFoundException {
		boolean rowDeleted = false;
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(Delete_Users_Sql);

						stmt.setInt(1, id);

			rowDeleted = stmt.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();	
		}
		return rowDeleted;
	}
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}
