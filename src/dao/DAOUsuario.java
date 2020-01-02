package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanCursoJsp;
import connection.SingleConnection;

public class DAOUsuario {

	private Connection connection;

	public DAOUsuario() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(BeanCursoJsp usuario) {
		try {
			String sql = "INSERT INTO users (login,password) VALUES (?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, usuario.getLogin());
			statement.setString(2, usuario.getSenha());
			statement.execute();

			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
	}

	public List<BeanCursoJsp> listar() throws Exception {
		List<BeanCursoJsp> list = new ArrayList<BeanCursoJsp>();

		String sql = "SELECT * FROM users";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			BeanCursoJsp bean = new BeanCursoJsp();
			bean.setId(resultSet.getLong("id"));
			bean.setLogin(resultSet.getString("login"));
			bean.setSenha(resultSet.getString("password"));
			list.add(bean);
		}

		return list;
	}

	public void remover(String id) {
		Long teste = Long.parseLong(id);
		try {
			String sql = "DELETE FROM users WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, teste);
			statement.execute();

			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	public BeanCursoJsp consultar(String id) throws Exception {
		
		Long teste = Long.parseLong(id);
		
		String sql = "SELECT * FROM users WHERE id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, teste);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			BeanCursoJsp bean = new BeanCursoJsp();
			bean.setId(resultSet.getLong("id"));
			bean.setLogin(resultSet.getString("login"));
			bean.setSenha(resultSet.getString("password"));

			return bean;
		}

		return null;
	}

	public void atualizar(BeanCursoJsp usuario) {
		String sql = "update users set login = ?, password = ? where id = ? ";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, usuario.getLogin());
			preparedStatement.setString(2, usuario.getSenha());
			preparedStatement.setLong(3, usuario.getId());
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}
	
	public boolean validarLogin(String login, String id) throws Exception {
		
		String sql = "SELECT COUNT(1) AS qtd FROM users WHERE login = ? AND id <> ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, login);
		statement.setLong(2, Long.parseLong(id));
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			
			return resultSet.getInt("qtd") <= 0; //return true

		}

		return false;
	}

}
