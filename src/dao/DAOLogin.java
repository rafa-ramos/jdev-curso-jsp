package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnection;

public class DAOLogin {
	
	private Connection connection;
	
	public DAOLogin() {
		connection = SingleConnection.getConnection();
	}
	
	public boolean validarLogin(String login, String password) throws Exception {
		
		//sql injection
		String sql = "SELECT * FROM users WHERE login = '" + login + "' and password = '" + password + "'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultset = statement.executeQuery();
		if (resultset.next()) {
			return true;
		} else {
			return false;
		}
	}

}
