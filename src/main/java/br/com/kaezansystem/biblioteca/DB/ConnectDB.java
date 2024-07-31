package br.com.kaezansystem.biblioteca.DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {
	private static final String DB_URL = "jdbc:postgresql://localhost:5000/BibliotecaV3";
	private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "tutu1236";
	
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(DB_DRIVER);
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			System.out.println("Connectado com sucesso");
			return conn;
		}catch (Exception e) {
			System.out.println("Erro de conexão com o banco de dados");
			System.out.println(e);
		}
		return conn;
	}
}
