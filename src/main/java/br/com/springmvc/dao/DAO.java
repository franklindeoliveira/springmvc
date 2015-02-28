package br.com.springmvc.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DAO {

	private Connection connection;
	
	@Autowired
	public DAO(DataSource ds) {
		try {
			this.connection = ds.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	
	public void conexaoEstabelecida() {
		if (connection != null) {
			System.out.println("Conexão com o banco de dados estabelecida com sucesso.");
		}
	}
	
}
