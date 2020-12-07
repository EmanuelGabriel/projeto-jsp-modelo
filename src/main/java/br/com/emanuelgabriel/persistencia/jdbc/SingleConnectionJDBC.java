package br.com.emanuelgabriel.persistencia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingleConnectionJDBC {

	// Driver de conexão do banco de dados: MySQL
	private static final String DRIVER_MYSQL_JDBC = "com.mysql.cj.jdbc.Driver";
	// URL da base de dados
	private static final String URL_BASE = "jdbc:mysql://localhost:3306/projeto";
	private static final String URL_BASE_USUARIO = "root";
	private static final String URL_BASE_SENHA = "gomesgomes";
	private static Connection conexao = null;

	static {
		conectar();
	}

	public SingleConnectionJDBC() {
		conectar();
	}

	private static void conectar() {

		try {

			if (conexao == null) {
				Class.forName(DRIVER_MYSQL_JDBC);
				conexao = DriverManager.getConnection(URL_BASE, URL_BASE_USUARIO, URL_BASE_SENHA);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Connection getConnection() {
		return conexao;
	}

	public static Connection criarConexao() throws SQLException {
		conexao = DriverManager.getConnection(URL_BASE, URL_BASE_USUARIO, URL_BASE_SENHA);
		return conexao;
	}

}
