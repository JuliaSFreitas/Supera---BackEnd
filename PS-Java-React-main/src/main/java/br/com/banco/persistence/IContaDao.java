package br.com.banco.persistence;

import java.sql.SQLException;

import br.com.banco.model.Conta;

public interface IContaDao {

	public Conta abrirConta(String conta) throws SQLException, ClassNotFoundException;
	
}
