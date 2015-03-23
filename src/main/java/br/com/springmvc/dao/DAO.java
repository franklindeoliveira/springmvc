package br.com.springmvc.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class DAO {

	private Connection connection;

	@Autowired
	public DAO(@Qualifier("mysqlDataSource") DataSource ds) {
		try {
			this.connection = ds.getConnection();
			System.out.println("Conexão com o banco de dados estabelecida com sucesso.");
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public void create() {
		try {
			connection.prepareStatement("INSERT INTO tabela VALUES ('teste')").execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void read() {
		try {
			connection.prepareStatement("SELECT * FROM tabela").executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		try {
			connection.prepareStatement("UPDATE tabela SET coluna1 = 'coluna-update' WHERE coluna1 = 'coluna'").execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete() {
		try {
			connection.prepareStatement("DELETE FROM tabela").execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
